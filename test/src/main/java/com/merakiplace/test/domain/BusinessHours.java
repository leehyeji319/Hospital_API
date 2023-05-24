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

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@Getter
@NoArgsConstructor
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

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Builder(toBuilder = true)
	public BusinessHours(Days days, Time openingTime, Time closingTime, Time lunchStartTime,
		Time lunchEndTime, Doctor doctor) {
		this.days = days;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.lunchStartTime = lunchStartTime;
		this.lunchEndTime = lunchEndTime;
		this.doctor = doctor;
	}
}
