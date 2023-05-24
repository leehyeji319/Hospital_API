package com.merakiplace.test.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merakiplace.test.service.SearchService;

import lombok.RequiredArgsConstructor;

/**
 * packageName : com.merakiplace.test.controller
 * fileName : HospitalController
 * author : modsiw
 * date : 2023/05/18
 * description :
 * ===========================================================
 * DATE AUTHOR NOTE
 * -----------------------------------------------------------
 * 2023/05/18 modsiw 최초 생성
 * 2023/05/24 modsiw 요구사항 1번 의사검색 - 문자열 검색 추가
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospital")
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/search")
	public ResponseEntity<?> getDoctorWithFullText(@RequestParam("keyword") String keyword) {
		return new ResponseEntity<>(searchService.findDoctorWithFullText(keyword), HttpStatus.OK);
	}

	@GetMapping("/search/{year}/{month}/{day}/{hour}/{minute}")
	public ResponseEntity<?> getDoctorWithDate(
			@PathVariable("year") String year, @PathVariable("month") String month,
			@PathVariable("day") String day, @PathVariable("hour") String hour, @PathVariable("minute") String minute) {
		return new ResponseEntity<>(searchService.findDoctorWithDate(year, month, day, hour, minute), HttpStatus.OK);
	}

	@GetMapping("/testapi")
	public ResponseEntity<?> test() {
		return new ResponseEntity<>(searchService.findDoctorWithDate("2023", "05", "29", "22", "50"), HttpStatus.OK);
	}

}
