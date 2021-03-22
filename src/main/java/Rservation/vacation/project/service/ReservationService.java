package Rservation.vacation.project.service;

import Rservation.vacation.project.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Long join(Long UserId, Long dateId,int peopleCount);

    Optional<Reservation> findOne(Long reservationId);

    List<Reservation> findAll();

    void cancelReservation(Long id);

}
