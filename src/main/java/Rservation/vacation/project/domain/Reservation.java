package Rservation.vacation.project.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Reservation {

    @Id @GeneratedValue
    @Column(name="reservation_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="userinfo_id")
    private UserInfo userInfo;

    private String name;

    private LocalDateTime reservationTime;

    private int peopleCount;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @OneToMany(mappedBy = "reservation")
    List<Date> dates= new ArrayList<>();

    public void setUserInfo(UserInfo info){
        this.userInfo=info;
        userInfo.getReservations().add(this);
    }
    public void addDate(Date date){
        dates.add(date);
    }

    public static Reservation createReservation(UserInfo userInfo, Date date, int peopleCount){
        Reservation reservation = new Reservation();
        reservation.setUserInfo(userInfo);
        reservation.addDate(date);
        reservation.setReservationStatus(ReservationStatus.COMPLETE);
        reservation.setReservationTime(LocalDateTime.now());
        reservation.setPeopleCount(peopleCount);
        return reservation;
    }
    public void cancel(){
        Duration time = Duration.between(this.reservationTime.toLocalTime(), LocalDateTime.now().toLocalTime());
        if(time.isNegative() || time.getSeconds()>3600){
            this.setReservationStatus(ReservationStatus.CANCEL);
            for(Date date: dates){
                date.cancel();
            }
        }

    }
}
