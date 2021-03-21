package Rservation.vacation.project.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    public void setName(UserInfo userInfo){
        this.name= userInfo.getName();
    }
    public void cancel(){
        Date date1 = dates.get(0);
        LocalDateTime localDateTime = date1.getDateTime();
        LocalDateTime now = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalDate nowDate = now.toLocalDate();
        if(nowDate.isBefore(localDate)||localDateTime.getHour()-now.getHour()>0){
            this.setReservationStatus(ReservationStatus.CANCEL);
            for(Date date: dates){
                date.cancel();
            }
        }
        dates.remove(0);
    }
}
