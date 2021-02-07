package Rservation.vacation.project.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class Date {
    @Id @GeneratedValue
    @Column(name="date_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private DateStatus dateStatus;



    //생성 메소드

    public static Date createDate(String year, String month, String day, String hour, String minute, String second){
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month)
                , Integer.parseInt(day), Integer.parseInt(hour),
                Integer.parseInt(minute), Integer.parseInt(second));
        date.dateTime=localDateTime;
        date.setDateStatus(DateStatus.ALREADY);
        return date;
    }
    //비즈니스 로직
    public void cancel(){
        this.setDateStatus(DateStatus.CAN);
    }



}
