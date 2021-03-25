package Rservation.vacation.project.repository;

import Rservation.vacation.project.domain.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReservationRepositoryCustom {

    List<Reservation> findAllWithJoinFetch(Pageable pageable);
}
