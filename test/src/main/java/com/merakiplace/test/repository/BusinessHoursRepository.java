package com.merakiplace.test.repository;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.merakiplace.test.domain.BusinessHours;
import com.merakiplace.test.domain.Days;

/**
 *packageName    : com.merakiplace.test.repository
 * fileName       : BusinessHoursRepository
 * author         : modsiw
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        modsiw       최초 생성
 */
public interface BusinessHoursRepository extends JpaRepository<BusinessHours, Long> {

	@Query("SELECT bh FROM BusinessHours bh WHERE bh.days = :day AND bh.doctor.id = :doctorId")
	Optional<BusinessHours> findByDaysAndDoctorId(@Param("day") Days day, @Param("doctorId") long doctorId);


	// @Query("SELECT CASE WHEN COUNT(bh) > 0 THEN true ELSE false END " +
	// 	"FROM BusinessHours bh " +
	// 	"WHERE bh.days = :day " +
	// 	"AND bh.doctor.id = :doctorId")
	// boolean existsOpeningTimeByDaysAndDoctorId(@Param("day") Days day, @Param("doctorId") long doctorId);

	// @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END " +
	// 	"FROM business_hours bh " +
	// 	"WHERE bh.days = :day " +
	// 	"AND bh.doctor_id = :doctorId", nativeQuery = true)
	// BigInteger existsOpeningTimeByDaysAndDoctorId(@Param("day") String day, @Param("doctorId") long doctorId);


	@Query("select bh.openingTime from BusinessHours bh where bh.days = :day and bh.doctor.id = :doctorId")
	Time findOpeningTimeByDaysAndDoctorId(@Param("day") Days day, @Param("doctorId") long doctorId);
}
