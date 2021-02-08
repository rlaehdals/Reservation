package Rservation.vacation.project.service;

import Rservation.vacation.project.domain.*;
import Rservation.vacation.project.repository.DateRepository;
import Rservation.vacation.project.repository.ReservationRepositoryImpl;
import Rservation.vacation.project.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReservationServiceImplTest {

    @Autowired ReservationServiceImpl reservationService;
    @Autowired
    ReservationRepositoryImpl reservationRepository;
    @Autowired EntityManager em;

    @Test
    void join(){
        Address address = new Address("A","A","A");
        UserInfo userInfo = new UserInfo("a","a","a","a","a",address);
        em.persist(userInfo);
        Date date = new Date();
        date.setDateTime(LocalDateTime.now());
        em.persist(date);

        int peopleCount=3;
        Reservation reservation = Reservation.createReservation(userInfo, date, peopleCount);
        Assertions.assertThat(reservation.getReservationStatus()).isEqualTo(ReservationStatus.COMPLETE);




    }
    @Test
    void 인원초과(){
        Address address = new Address("A","A","A");
        UserInfo userInfo = new UserInfo("a","a","a","a","a",address);
        em.persist(userInfo);
        Date date = new Date();
        date.setDateTime(LocalDateTime.now());
        em.persist(date);

        int peopleCount=5;
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> reservationService.join(userInfo.getId(), date.getId(), peopleCount));
        Assertions.assertThat(e.getMessage()).isEqualTo("5명 이상은 안됩니다.");
    }
    @Test
    void 취소_상태_확인(){
        Address address = new Address("A","A","A");
        UserInfo userInfo = new UserInfo("a","a","a","a","a",address);
        em.persist(userInfo);
        Date date = new Date();

        date.setDateTime(LocalDateTime.now());
        em.persist(date);

        int peopleCount=4;
        Reservation reservation = Reservation.createReservation(userInfo, date, peopleCount);
        reservation.cancel();

        Assertions.assertThat(reservation.getReservationStatus()).isEqualTo(ReservationStatus.CANCEL);


    }

}