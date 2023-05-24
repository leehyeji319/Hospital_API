package com.merakiplace.test.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *packageName    : com.merakiplace.test.domain
 * fileName       : Hospital
 * author         : modsiw
 * date           : 2023/05/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/18        modsiw       최초 생성
 */

@Entity
public class Hospital {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private String hospitalName;

	@OneToMany(mappedBy = "hospital")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Doctor> doctors = new ArrayList<>();

	public void addDoctor(Doctor doctor) {
		doctor.setHospital(doctor.getHospital());
		doctors.add(doctor);
	}


}