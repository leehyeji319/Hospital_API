package com.merakiplace.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.merakiplace.test.domain.Doctor;

/**
 *packageName    : com.merakiplace.test.repository
 * fileName       : DoctorRepository
 * author         : modsiw
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        modsiw       최초 생성
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query(
		value = "SELECT DISTINCT \n"
			+ "  d.doctor_name\n"
			+ "FROM\n"
			+ "  doctor d\n"
			+ "  JOIN doctor_department dd ON d.id = dd.doctor_id\n"
			+ "  JOIN department de ON dd.department_id = de.id\n"
			+ "  JOIN hospital h ON d.hospital_id = h.id\n"
			+ "  JOIN business_hours bh ON d.id = bh.doctor_id\n"
			+ "WHERE\n"
			+ "  CONCAT(d.doctor_name, ' ', h.hospital_name, ' ', de.department_name, ' ', IFNULL(d.non_benefit, '')) LIKE %:keyword%",
		nativeQuery = true
	)
	List<Optional<String>> findDoctorByKeyword(@Param("keyword") String keyword);

}
