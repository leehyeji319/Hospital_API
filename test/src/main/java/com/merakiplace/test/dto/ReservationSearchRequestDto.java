package com.merakiplace.test.dto;

import java.time.LocalDateTime;

import com.merakiplace.test.domain.Status;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *packageName    : com.merakiplace.test.dto
 * fileName       : ReservationSearchRequestDto
 * author         : modsiw
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        modsiw       최초 생성
 */

@Data
@RequiredArgsConstructor
public class ReservationSearchRequestDto {

	private long reservationId;
	private String patientName;
	private LocalDateTime targetReservationDateTime;
	private LocalDateTime expiredReservationApprovedDateTime;
	private Status status;

	public ReservationSearchRequestDto(long reservationId, String patientName,
		LocalDateTime targetReservationDateTime, LocalDateTime expiredReservationApprovedDateTime,
		Status status) {
		this.reservationId = reservationId;
		this.patientName = patientName;
		this.targetReservationDateTime = targetReservationDateTime;
		this.expiredReservationApprovedDateTime = expiredReservationApprovedDateTime;
		this.status = status;
	}
}
