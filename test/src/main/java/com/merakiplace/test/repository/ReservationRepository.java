package com.merakiplace.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.merakiplace.test.domain.Reservation;
import com.merakiplace.test.dto.ReservationSearchRequestDto;

/**
 *packageName    : com.merakiplace.test.repository
 * fileName       : ReservationRepository
 * author         : modsiw
 * date           : 2023/05/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/05/24        modsiw       최초 생성
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("select new com.merakiplace.test.dto.ReservationSearchRequestDto"
		+ "(r.id, r.patient.name, r.reservationDateTime, r.expiredDateTime, r.status) "
		+ "from Reservation r where r.doctor.id = :doctorId and not r.status = 'APPROVED'")
	List<ReservationSearchRequestDto> findByDoctorId(@Param("doctorId") long doctorId);

}
