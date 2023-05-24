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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *packageName    : com.merakiplace.test.domain
 * fileName       : Doctor
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
// @NoArgsConstructor
public class Doctor {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private String doctorName;

	@Column
	private String nonBenefit;

	@Builder.Default
	@OneToMany(mappedBy = "doctor")
	private List<DoctorDepartment> doctorDepartments = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospital_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Hospital hospital;

	@Builder.Default
	@OneToMany(mappedBy = "doctor")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<BusinessHours> businessHoursList = new ArrayList<>();

	public Doctor() {

	}

	public void addBusinessHours(BusinessHours businessHours) {
		businessHours.setDoctor(businessHours.getDoctor());
		businessHoursList.add(businessHours);
	}


	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Builder(toBuilder = true)
	public Doctor(String doctorName, String nonBenefit, Hospital hospital) {
		this.doctorName = doctorName;
		this.nonBenefit = nonBenefit;
		this.hospital = hospital;
	}
}
