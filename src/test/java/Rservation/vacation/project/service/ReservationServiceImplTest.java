package Rservation.vacation.project.service;

import Rservation.vacation.project.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReservationServiceImplTest {
    @Autowired ReservationService reservationService;
    @Autowired UserService userService;
    @Autowired DateService dateService;

    @Test
    void join_검증(){
        Address address =new Address("a", "a" ,"a");
        UserInfo userInfo = new UserInfo("a","A","a","a","a", address);
        Long userId = userService.save(userInfo);
        Date date = Date.createDate("1", "1", "1", "1", "10", "1");
        Long dateId = dateService.join(date);
        Long reservationId = reservationService.join(userId, dateId, 4);
        Reservation findReservation = reservationService.findOne(reservationId);
        Assertions.assertThat(reservationId).isEqualTo(findReservation.getId());
    }
    @Test
    void join_실패검증(){
        Address address =new Address("a", "a" ,"a");
        UserInfo userInfo = new UserInfo("a","A","a","a","a", address);
        Long userId = userService.save(userInfo);
        Date date = Date.createDate("1", "1", "1", "1", "1", "1");
        Long dateId = dateService.join(date);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> reservationService.join(userId, dateId, 5));
        Assertions.assertThat(e.getMessage()).isEqualTo("5명 이상은 안됩니다.");
    }
    @Test
    void cancel_확인(){
        Address address =new Address("a", "a" ,"a");
        UserInfo userInfo = new UserInfo("a","A","a","a","a", address);
        Long userId = userService.save(userInfo);
        Date date = Date.createDate("2021", "2", "23", "12", "1", "1");
        Long dateId = dateService.join(date);
        Long reservationId = reservationService.join(userId, dateId, 4);
        reservationService.cancelReservation(reservationId);
        Assertions.assertThat(reservationService.findOne(reservationId).getReservationStatus()).isEqualTo(ReservationStatus.CANCEL);
    }
}