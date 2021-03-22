package Rservation.vacation.project.repository;

import Rservation.vacation.project.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "select r from Reservation r join fetch r.userInfo")
    Page<Reservation> findAllWithJoinFetch(Pageable pageable);

    @Override
    @QueryHints(value = @QueryHint(name="org.hibernate.readOnly", value = "true"))
    List<Reservation> findAll();
}
