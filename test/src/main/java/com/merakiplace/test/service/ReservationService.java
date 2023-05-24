package com.merakiplace.test.service;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.merakiplace.test.domain.BusinessHours;
import com.merakiplace.test.domain.Days;
import com.merakiplace.test.domain.Doctor;
import com.merakiplace.test.domain.Patient;
import com.merakiplace.test.domain.Reservation;
import com.merakiplace.test.domain.Status;
import com.merakiplace.test.dto.ReservationInformationRequestDto;
import com.merakiplace.test.dto.ReservationSearchRequestDto;
import com.merakiplace.test.repository.BusinessHoursRepository;
import com.merakiplace.test.repository.DoctorRepository;
import com.merakiplace.test.repository.PatientRepository;
import com.merakiplace.test.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

/**
 *packageName    : com.merakiplace.test.service
 * fileName       : ReservationService
 * author         : modsiw
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        modsiw       최초 생성
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

	private final ReservationRepository reservationRepository;
	private final SearchService searchService;
	private final DoctorRepository doctorRepository;
	private final PatientRepository patientRepository;
	private final BusinessHoursRepository businessHoursRepository;

	//진료 요청
	@Transactional
	public ReservationInformationRequestDto addReservation(long doctorId, long patientId, String date, String time) {

		Doctor findDoctor = doctorRepository.findById(doctorId).orElseThrow(
			() -> new IllegalArgumentException("해당 ID의 의사가 존재하지 않습니다. doctorId : " + doctorId));
		Patient findPatient = patientRepository.findById(patientId).orElseThrow(
			() -> new IllegalArgumentException("해당 ID의 환자가 존재하지 않습니다. doctorId : " + patientId));

		// ===== 진료 요청 로직 ===== //
		//해당 날짜에 의사가 영업중인지 확인
		String year = date.substring(0, 4);
		String month = date.substring(4, 6);
		String day = date.substring(6, 8);
		String hour = time.substring(0, 2);
		String minute = time.substring(2, 4);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		//예약할 날짜
		LocalDateTime targetDateTime = LocalDateTime.parse(
			year + "-" + month + "-" + day + " " + hour + ":" + minute, formatter);

		List<String> doctorWithDate = searchService.findDoctorWithDate(year, month, day, hour, minute);

		//의사의 이름이 해당 진료일에 들어있는지 확인
		if (!doctorWithDate.contains(findDoctor.getDoctorName())) {
			throw new IllegalArgumentException("해당 날짜에 요청하신 의사의 영업일이 아닙니다! doctorId : " + doctorId);
		}

		//====== 진료 요청 만료 로직 ======//

		//DB에 담길 만료 시간
		LocalDateTime expiredRequestDateTime = null;
		// 진료 요청이 일단 들어온 시간 (=현재시간)
		LocalDateTime requestDateTime = LocalDateTime.now();
		// 현재 시간의 요일을 가져오기
		DayOfWeek requestDayOfWeek = requestDateTime.getDayOfWeek();
		String targetDayOfWeek = requestDayOfWeek.toString().substring(0, 3);
		// 현재 시간에서 시간만 가져오기
		DateTimeFormatter timeFormatterWithHourMinute = DateTimeFormatter.ofPattern("HH:mm");
		String requestTime = requestDateTime.format(timeFormatterWithHourMinute);
		// 현재 시간에서 오늘 날짜만 가져오기
		DateTimeFormatter dateFormatterWithYearMonthDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String requestDate = requestDateTime.format(dateFormatterWithYearMonthDay);

		// 요청 당일의 요일과 현재 시간으로 검색한다.
		//요일과, 그 시간에 영업중인 의사 목록 들고오기
		List<String> findDoctorsInBusinessHours =
			doctorRepository.findDoctorByTimeAndDay(requestTime, targetDayOfWeek);

		List<String> lunchTimeDoctorTimeAndDay =
			doctorRepository.findLunchTimeDoctorTimeAndDay(requestTime, targetDayOfWeek);

		List<String> doctorListNowBusiness = new ArrayList<>(findDoctorsInBusinessHours);
		doctorListNowBusiness.removeAll(lunchTimeDoctorTimeAndDay);

		// 만약 요청 들어온 시간에 의사가 영업중이면 그냥 만료시간 + 20해준다.
		if (doctorListNowBusiness.contains(findDoctor.getDoctorName())) {
			expiredRequestDateTime = requestDateTime.plusMinutes(20);

		} else { //만약 요청시간에 영업중이 아니라면 -> 점심시간인지? 아니면 아예 영업시간이 아닌지? 판단한다.
			if (lunchTimeDoctorTimeAndDay.contains(findDoctor.getDoctorName())) {
				//만약 점심시간이면 해당일의 점심시간 끝나는 시간 + 20분으로 요청 만료시간 설정
				Time lunchEndTimeByIdAndDays = doctorRepository.findLunchEndTimeByIdAndDays(doctorId, targetDayOfWeek);
				LocalTime lunchEndTime = lunchEndTimeByIdAndDays.toLocalTime().plusMinutes(15);
				//점심시간 +20분 한 시간
				String expriedRequestDateTimeAfterLunch =
					requestDate + " " + lunchEndTime.format(timeFormatterWithHourMinute);
				//제대로 나온 expiredDateTime = Type : LocalDateTime
				expiredRequestDateTime = LocalDateTime.parse(expriedRequestDateTimeAfterLunch, formatter);

			} else { //만약 점심시간이 아니라 그냥 영업시간이 아니라면

				//생각할 것
				//1. 가장 빠른 영업일이 언제인지? 무작정 다음날로 하면 안됨 -> 다음날의 오프닝 시간이 과연 영업일인가 해야함.
				//2. 요청시간이 들어온 오늘의 요일을 구하고 while문을 돌려서 해당 요일 + 1부터 null이 아닌 날의 요일을 구한다.
				//3. 그리고 해당 요일의 날짜를 구한다. abs(현재 요청한 날짜 - 오픈시간이 null이 아닌 요일의 차이) 만큼을 date에 ++해준다.
				//4. 더해준 날짜 + 오프닝 시간을 더해주고 expiredRequestDateTime에 저장해준다.

				DayOfWeek findDayOfWeek = requestDayOfWeek.plus(1);
				int countDay = 1;
				boolean flag = true;
				//현재 요일부터 ++ 해서 null 아닌 날짜 구하기
				while (flag) {
					boolean existsDayOfWeek = false;
					System.out.println(Days.valueOf(findDayOfWeek.toString().substring(0, 3)));
					BusinessHours findBusinessHours =
						businessHoursRepository.findByDaysAndDoctorId(
								Days.valueOf(findDayOfWeek.toString().substring(0, 3)), doctorId)
							.orElseThrow(() -> new IllegalArgumentException("해당 요일과 의사의 ID와 일치하는 것이 없습니다."));

					if (findBusinessHours.getOpeningTime() != null) {
						existsDayOfWeek = true;
					}

					if (existsDayOfWeek) { //만약 해당 요일과 의사아이디로 찾았는데 true라면
						Time openingTimeByDaysAndDoctorId = businessHoursRepository.findOpeningTimeByDaysAndDoctorId(
							Days.valueOf(findDayOfWeek.toString().substring(0, 3)), doctorId);
						LocalTime plusTime = openingTimeByDaysAndDoctorId.toLocalTime().plusMinutes(15);
						String plusTimeToString = plusTime.format(timeFormatterWithHourMinute);
						LocalDateTime newDate = requestDateTime.plusDays(countDay);
						String plusDateToString = newDate.format(dateFormatterWithYearMonthDay);
						String plusFullDateString = plusDateToString + " " + plusTimeToString;
						expiredRequestDateTime = LocalDateTime.parse(plusFullDateString, formatter);
						flag = false;
					}
					countDay++;
					findDayOfWeek = findDayOfWeek.plus(1);

				}

			}
		}
		Reservation savedReservation = reservationRepository.save(Reservation.builder()
			.doctor(findDoctor)
			.patient(findPatient)
			.reservationDateTime(targetDateTime)
			.expiredDateTime(expiredRequestDateTime)
			.status(Status.NONE)
			.build());

		return addReservationInformationDto(savedReservation);
	}

	//진료 요청 검색
	public List<ReservationSearchRequestDto> findReservationByDoctorId(long doctorId) {
		return reservationRepository.findByDoctorId(doctorId);

	}

	//진료 수락
	@Transactional
	public Object modifyReservationBeApprove(long reservationId) {
		String message = "";
		//진료 수락을 누른 시간
		LocalDateTime approveRequestTime = LocalDateTime.now();

		Reservation findReservation = reservationRepository.findById(reservationId)
			.orElseThrow(() -> new IllegalArgumentException
				("해당 예약의 아이디가 존재하지 않습니다. reservationId : " + reservationId));
		LocalDateTime expiredDateTime = findReservation.getExpiredDateTime();

		if (approveRequestTime.isAfter(expiredDateTime)) {
			reservationRepository.save(
				findReservation.toBuilder()
					.id(findReservation.getId())
					.status(Status.EXPIRED)
					.build()
			);
		} else {
			if (findReservation.getStatus().equals(Status.APPROVED)) {
				throw new IllegalArgumentException("이미 진료 요청이 수락된 예약입니다. reservationId : " + reservationId);
			}
			Reservation modifiedReservation = reservationRepository.save(
				findReservation.toBuilder()
					.id(findReservation.getId())
					.status(Status.APPROVED)
					.build()
			);

			return addReservationInformationDto(modifiedReservation);
		}
		return message = "이미 진료 요청 시간이 초과되었습니다. 진료 요청 시간 : " + approveRequestTime
			+ " " + "진료 요청 만료 시간 : " + findReservation.getExpiredDateTime();
	}

	private ReservationInformationRequestDto addReservationInformationDto(Reservation savedReservation) {
		ReservationInformationRequestDto responseDto = new ReservationInformationRequestDto();
		responseDto.setReservationId(savedReservation.getId());
		responseDto.setDoctorName(savedReservation.getDoctor().getDoctorName());
		responseDto.setPatientName(savedReservation.getPatient().getName());
		responseDto.setTargetReservationDateTime(savedReservation.getReservationDateTime());
		responseDto.setExpiredReservationApprovedDateTime(savedReservation.getExpiredDateTime());

		return responseDto;
	}

}
