package com.merakiplace.test.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *packageName    : com.merakiplace.test.dto
 * fileName       : ReservationSaveResponseDto
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
public class ReservationInformationRequestDto {

	private long reservationId;
	private String patientName;
	private String doctorName;
	private LocalDateTime targetReservationDateTime;
	private LocalDateTime expiredReservationApprovedDateTime;


	public ReservationInformationRequestDto(long reservationId, String patientName, String doctorName,
		LocalDateTime hopeReservationDateTime, LocalDateTime expiredReservationDateTime) {
		this.reservationId = reservationId;
		this.patientName = patientName;
		this.doctorName = doctorName;
		this.targetReservationDateTime = hopeReservationDateTime;
		this.expiredReservationApprovedDateTime = expiredReservationDateTime;
	}

}
