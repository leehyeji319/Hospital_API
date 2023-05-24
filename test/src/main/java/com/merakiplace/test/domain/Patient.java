package com.merakiplace.test.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *packageName    : com.merakiplace.test.domain
 * fileName       : Patient
 * author         : modsiw
 * date           : 2023/05/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/18        modsiw       최초 생성
 */

@Entity
@Getter
@NoArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private String name;

	@Builder(toBuilder = true)
	public Patient(String name) {
		this.name = name;
	}
}
