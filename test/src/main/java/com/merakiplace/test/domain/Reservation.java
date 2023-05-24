package com.merakiplace.test.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *packageName    : com.merakiplace.test.domain
 * fileName       : Reservation
 * author         : modsiw
 * date           : 2023/05/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/19        modsiw       최초 생성
 */

@Entity
@Getter
@NoArgsConstructor
public class Reservation {

	@Id @GeneratedValue
	@Column
	private long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Patient patient;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Doctor doctor;

	@Column
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column
	private LocalDateTime reservationDateTime;

	@Column
	private LocalDateTime expiredDateTime;

	@Column
	private String redisHashId;

	@Builder(toBuilder = true)
	public Reservation(long id, Patient patient, Doctor doctor, Status status, LocalDateTime reservationDateTime,
		LocalDateTime expiredDateTime, String redisHashId) {
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.status = status;
		this.reservationDateTime = reservationDateTime;
		this.expiredDateTime = expiredDateTime;
		this.redisHashId = redisHashId;
	}
}
