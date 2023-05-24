package com.merakiplace.test.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.merakiplace.test.repository.DoctorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *packageName    : com.merakiplace.test.service
 * fileName       : SearchService
 * author         : modsiw
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        modsiw       최초 생성
 * 2023/05/24        modsiw       의사 문자열 검색 기능 추가
 * 2023/05/24        modsiw       날짜와 시간에 따른 영업중인 의사 반환 기능 추가
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class SearchService {

	private final DoctorRepository doctorRepository;

	public List<String> findDoctorWithFullText(String keyword) {
		String[] targetWords = keyword.split(" ");
		List<String> queryResult = new ArrayList<>();
		HashMap<String, Integer> countDoctorMap = new HashMap<>();

		for (String targetWord : targetWords) {

			List<Optional<String>> doctorByKeyword = doctorRepository.findDoctorByKeyword(targetWord);

			for (Optional<String> doctor : doctorByKeyword) {
				if (doctor.isPresent()) {
					queryResult.add(doctor.get());
				} else {
					throw new IllegalArgumentException(
						"해당되는 의사가 없습니다." + keyword + "에서 " + targetWord + "가 모든 의사에게 없음.");
				}
			}
		}

		// 의사별로 몇번의 카운트가 나왔는지 hashmap에 저장
		for (String r : queryResult) {
			countDoctorMap.put(r, countDoctorMap.getOrDefault(r, 0) + 1);
		}

		// 의사별로 가장 많이 등장한 횟수가 몇번인지
		int maxAppearCount = Collections.max(countDoctorMap.values());

		// 가장많이 등장한 횟수와 같은 등장횟수를 가진 의사들
		List<String> keysWithMaxCountDoctors;

		// 만약 가장많이 등장한 카운트가 split한 검색어의 배열길이와 같지 않으면
		// full text search와 같은게 없는것
		if (maxAppearCount != targetWords.length) {
			throw new IllegalArgumentException("검색어에 일치하는 의사가 존재하지 않습니다. 검색어 : " + keyword);
		} else {
			keysWithMaxCountDoctors = new ArrayList<>();
			for (Map.Entry<String, Integer> entry : countDoctorMap.entrySet()) {
				if (entry.getValue() == maxAppearCount) {
					keysWithMaxCountDoctors.add(entry.getKey());
				}
			}
		}

		return keysWithMaxCountDoctors;
	}

	public List<String> findDoctorWithDate(String year, String month, String day, String hour, String minute) {

		//해당 년월일이 현재보다 과거이면 exception
		LocalDateTime currentDateTime = LocalDateTime.now();

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime targetDateTime = LocalDateTime.parse(
				year + "-" + month + "-" + day + " " + hour + ":" + minute, formatter);
			// 날짜가 올바르게 파싱되었을 경우에 대한 로직 추가
			String targetTime = hour + ":" + minute;

			// 현재 시간과 주어진 년월일 비교
			if (targetDateTime.isBefore(currentDateTime)) {
				// 과거인 경우 예외 처리
				throw new IllegalArgumentException("주어진 날짜는 현재 시간보다 과거입니다.");
			}

			// 현재 시간의 요일을 가져오기
			DayOfWeek dayOfWeek = targetDateTime.getDayOfWeek();
			String targetDayOfWeek = dayOfWeek.toString().substring(0, 3);

			//요일과, 그 시간에 영업중인 의사 목록 들고오기
			List<String> findDoctorsInBusinessHours =
				doctorRepository.findDoctorByTimeAndDay(targetTime, targetDayOfWeek);

			List<String> lunchTimeDoctorTimeAndDay =
				doctorRepository.findLunchTimeDoctorTimeAndDay(targetTime, targetDayOfWeek);

			List<String> doctorListInBusiness = new ArrayList<>(findDoctorsInBusinessHours);
			doctorListInBusiness.removeAll(lunchTimeDoctorTimeAndDay);

			if (doctorListInBusiness.size() == 0) {
				throw new IllegalArgumentException("해당 날짜와 시간에 영업중인 의사가 존재하지 않습니다.");
			}

			return doctorListInBusiness;

		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("날짜 형식이 올바른지 다시 확인해주세요.");
		}
	}

}
