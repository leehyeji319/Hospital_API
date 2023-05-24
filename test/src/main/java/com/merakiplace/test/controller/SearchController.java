package com.merakiplace.test.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.merakiplace.test.service.SearchService;

import lombok.RequiredArgsConstructor;

/**
 *packageName    : com.merakiplace.test.controller
 * fileName       : HospitalController
 * author         : modsiw
 * date           : 2023/05/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/18        modsiw       최초 생성
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

	@GetMapping("/")

}
