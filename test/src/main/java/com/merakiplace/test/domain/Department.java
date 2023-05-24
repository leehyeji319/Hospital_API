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
 * fileName       : Department
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
public class Department {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private String departmentName;

	@OneToMany(mappedBy = "department")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<DoctorDepartment> doctorDepartments = new ArrayList<>();

	@Builder(toBuilder = true)
	public Department(String departmentName, List<DoctorDepartment> doctorDepartments) {
		this.departmentName = departmentName;
		this.doctorDepartments = doctorDepartments;
	}
}
