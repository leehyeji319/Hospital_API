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
	private String redisHashId;
}
