package Rservation.vacation.project.api;

import Rservation.vacation.project.domain.Date;
import Rservation.vacation.project.domain.Reservation;
import Rservation.vacation.project.repository.ReservationRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReservationApi {

    private final ReservationRepository reservationRepository;

    @GetMapping("/api/v1/reservations")
    public result reservationV1(){
        List<Reservation> all = reservationRepository.findAllWithJoinFetch();
        List<reservationSimpleDto> collect = all.stream()
                .map(o -> new reservationSimpleDto(o.getName(), o.getPeopleCount(), o.getReservationTime()))
                .collect(Collectors.toList());
        return new result(collect.size(),collect);
    }


    @Data
    @Getter
    static class result<T>{
        private int count;
        private T data;
        public result(int count, T data) {
            this.count = count;
            this.data = data;
        }
    }

    @Data
    static class reservationSimpleDto{
        private String name;
        private int peopleCount;
        private LocalDateTime localDateTime;
        private List<Date> dates;

        public reservationSimpleDto(String name, int peopleCount, LocalDateTime localDateTime) {
            this.name = name;
            this.peopleCount = peopleCount;
            this.localDateTime = localDateTime;
        }
    }

}
