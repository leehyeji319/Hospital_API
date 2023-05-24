package com.merakiplace.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merakiplace.test.domain.Hospital;

/**
 *packageName    : com.merakiplace.test.repository
 * fileName       : HospitalRepository
 * author         : modsiw
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        modsiw       최초 생성
 */
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
