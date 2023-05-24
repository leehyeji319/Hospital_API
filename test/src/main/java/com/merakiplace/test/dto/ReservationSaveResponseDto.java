package com.merakiplace.test.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class ReservationSaveResponseDto {

	private long reservationId;
	private String patientName;
	private String doctorName;
	private LocalDateTime hopeReservationDateTime;
	private LocalDateTime expiredReservationDateTime;


	public ReservationSaveResponseDto(long reservationId, String patientName, String doctorName,
		LocalDateTime hopeReservationDateTime, LocalDateTime expiredReservationDateTime) {
		this.reservationId = reservationId;
		this.patientName = patientName;
		this.doctorName = doctorName;
		this.hopeReservationDateTime = hopeReservationDateTime;
		this.expiredReservationDateTime = expiredReservationDateTime;
	}

}
