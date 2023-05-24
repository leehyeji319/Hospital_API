package com.merakiplace.test.repository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.merakiplace.test.domain.Days;
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
			+ "WHERE\n"
			+ "  CONCAT(d.doctor_name, ' ', h.hospital_name, ' ', de.department_name, ' ', IFNULL(d.non_benefit, '')) LIKE %:keyword%",
		nativeQuery = true
	)
	List<Optional<String>> findDoctorByKeyword(@Param("keyword") String keyword);



	// 시간 검색
	// 해당 시간과 요일에 영업중인 의사들
	@Query(value =
		"SELECT doctor_name " +
			"FROM ( " +
			"   SELECT doctor_name, days, CONCAT(opening_time, ' - ', closing_time) AS business_hours, CONCAT(lunch_start_time, ' - ', lunch_end_time) AS lunch_hours " +
			"   FROM doctor " +
			"   JOIN business_hours ON doctor.id = business_hours.doctor_id " +
			") AS subquery " +
			"WHERE " +
			"   :time BETWEEN SUBSTRING_INDEX(business_hours, ' - ', 1) AND SUBSTRING_INDEX(business_hours, ' - ', -1) " +
			"   AND days = :day"
		, nativeQuery = true)
	List<String> findDoctorByTimeAndDay(@Param("time") String time, @Param("day") String day);


	//해당요일과 시간에 점심시간인 의사목록들
	@Query(value =
		"SELECT doctor_name " +
			"FROM ( " +
			"   SELECT doctor_name, days, CONCAT(opening_time, ' - ', closing_time) AS business_hours, CONCAT(lunch_start_time, ' - ', lunch_end_time) AS lunch_hours " +
			"   FROM doctor " +
			"   JOIN business_hours ON doctor.id = business_hours.doctor_id " +
			") AS subquery " +
			"WHERE " +
			"   :time BETWEEN SUBSTRING_INDEX(business_hours, ' - ', 1) AND SUBSTRING_INDEX(business_hours, ' - ', -1) " +
			"   AND days = :day " +
			"   AND :time BETWEEN SUBSTRING_INDEX(lunch_hours, ' - ', 1) AND SUBSTRING_INDEX(lunch_hours, ' - ', -1)"
		, nativeQuery = true)
	List<String> findLunchTimeDoctorTimeAndDay(@Param("time") String time, @Param("day") String day);


	//해당 요일과 의사의 ID로 점심시간 언제끝나는지 가져오기
	@Query("select bh.lunchEndTime from BusinessHours bh where bh.doctor.id = :doctorId and bh.days = :days")
	Time findLunchEndTimeByIdAndDays(@Param("doctorId") long doctorId, @Param("day") String day);

	// void existByIdAndDay(long doctorId, String targetDayOfWeek);

}
