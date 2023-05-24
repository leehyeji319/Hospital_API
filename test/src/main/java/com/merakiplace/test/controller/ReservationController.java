package com.merakiplace.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merakiplace.test.service.ReservationService;

import lombok.RequiredArgsConstructor;

/**
 *packageName    : com.merakiplace.test.controller
 * fileName       : ReservationController
 * author         : modsiw
 * date           : 2023/05/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/19        modsiw       최초 생성
 * 2023/05/24		 modsiw		  진료 요청 기능 추가
 */

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	// 진료요청
	@PostMapping("/doctors/{doctorId}/patients/{patientId}/{date}/{time}")
	public ResponseEntity<?> addReservation(
		@PathVariable("doctorId") long doctorId, @PathVariable("patientId") long patientId,
		@PathVariable("date") String date, @PathVariable("time") String time) {
		return new ResponseEntity<>(reservationService.addReservation(doctorId, patientId, date, time), HttpStatus.CREATED);
	}

	// 진료요청 검색
	@GetMapping("/doctors/{doctorId}")
	public ResponseEntity<?> getReservationByDoctorId(@PathVariable("doctorId") long doctorId) {
		return new ResponseEntity<>(reservationService.findReservationByDoctorId(doctorId), HttpStatus.OK);
	}

	// 진료요청 수락
	@PostMapping("/{reservationId}")
	public ResponseEntity<?> modifyReservationBeApprove(@PathVariable("reservationId") long reservationId) {
		return new ResponseEntity<>(reservationService.modifyReservationBeApprove(reservationId), HttpStatus.OK);
	}
}
