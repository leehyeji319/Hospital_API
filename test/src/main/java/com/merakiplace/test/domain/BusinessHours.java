package com.merakiplace.test.domain;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *packageName    : com.merakiplace.test.domain
 * fileName       : BusinessHours
 * author         : modsiw
 * date           : 2023/05/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/19        modsiw       최초 생성
 */

@Entity
public class BusinessHours {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Enumerated(EnumType.STRING)
	private Days days;

	@Column
	private Time openingTime;

	@Column
	private Time closingTime;

	@Column
	private Time lunchStartTime;

	@Column
	private Time lunchEndTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Doctor doctor;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
