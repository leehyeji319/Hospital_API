package com.merakiplace.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merakiplace.test.domain.Patient;

/**
 *packageName    : com.merakiplace.test.repository
 * fileName       : PatientRepository
 * author         : modsiw
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        modsiw       최초 생성
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
