package Rservation.vacation.project.repository.query;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class reservationSimpleDtos {
    private String name;
    private int peopleCount;
    private LocalDateTime localDateTime;

    public reservationSimpleDtos(String name, int peopleCount, LocalDateTime localDateTime) {
        this.name = name;
        this.peopleCount = peopleCount;
        this.localDateTime = localDateTime;
    }
}
