package Rservation.vacation.project.repository;

import Rservation.vacation.project.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    void save(Reservation reservation);
    Reservation findById(Long id);
    List<Reservation> findByName(String name);
    List<Reservation> findAll();

    List<Reservation> findAllWithJoinFetch();
}
