package Rservation.vacation.project.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationDto {

    private String username;
    private int people;
    private LocalDateTime localDateTime;

    @QueryProjection
    public ReservationDto(String username, int people, LocalDateTime localDateTime) {
        this.username = username;
        this.people = people;
        this.localDateTime = localDateTime;
    }
}
