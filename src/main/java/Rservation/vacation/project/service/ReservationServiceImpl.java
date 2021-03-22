package Rservation.vacation.project.service;

import Rservation.vacation.project.domain.Date;
import Rservation.vacation.project.domain.Reservation;
import Rservation.vacation.project.domain.UserInfo;
import Rservation.vacation.project.repository.DateRepository;
import Rservation.vacation.project.repository.ReservationRepository;
import Rservation.vacation.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final DateRepository dateRepository;

    @Transactional
    @Override
    public Long join(Long userId, Long dateId, int peopleCount) {
        Optional<UserInfo> userInfo = userRepository.findById(userId);
        Optional<Date> date = dateRepository.findById(dateId);
        overCount(peopleCount);
        Reservation reservation = Reservation.createReservation(userInfo.get(), date.stream().findAny().get(), peopleCount);
        reservationRepository.save(reservation);
        return reservation.getId();
    }
    private void overCount(int peopleCount) {
        if(peopleCount>=5){
            throw new IllegalStateException("5명 이상은 안됩니다.");
        }
    }


    @Override
    public Optional<Reservation> findOne(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional
    public void cancelReservation(Long id) {
        Optional<Reservation> findId = reservationRepository.findById(id);
        findId.get().cancel();
    }
}
