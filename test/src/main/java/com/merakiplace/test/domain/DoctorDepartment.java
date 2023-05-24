package com.merakiplace.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *packageName    : com.merakiplace.test.domain
 * fileName       : DoctorDepartment
 * author         : modsiw
 * date           : 2023/05/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/23        modsiw       최초 생성
 */

@Entity
@Getter
@NoArgsConstructor
public class DoctorDepartment {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Doctor doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Department department;

	@Builder(toBuilder = true)
	public DoctorDepartment(Doctor doctor, Department department) {
		this.doctor = doctor;
		this.department = department;
	}
}
