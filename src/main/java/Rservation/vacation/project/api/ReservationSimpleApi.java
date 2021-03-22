package Rservation.vacation.project.api;

import Rservation.vacation.project.domain.Reservation;
import Rservation.vacation.project.repository.ReservationRepository;
import Rservation.vacation.project.repository.query.ReservationQueryRepository;
import Rservation.vacation.project.repository.query.reservationSimpleDtos;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReservationSimpleApi {

    private final ReservationRepository reservationRepository;
    private final ReservationQueryRepository reservationQueryRepository;
    @GetMapping("/api/v1/simpleReservations")
    public List<Reservation> reservationV1(){
        List<Reservation> all = reservationRepository.findAll();
        for (Reservation reservation : all) {
            reservation.getUserInfo().getEmail();
            reservation.getDates().forEach(o->o.getDateTime());
        }
        return all;
    }
    @GetMapping("/api/v2/simpleReservations")
    public result reservationV2(){
        List<Reservation> all = reservationRepository.findAll();

        List<reservationSimpleDto> collect = all.stream()
                .map(o -> new reservationSimpleDto(o.getName(), o.getPeopleCount(), o.getReservationTime()))
                .collect(Collectors.toList());
        return new result(collect.size(), collect);
    }
    @GetMapping("/api/v3/simpleReservations")
    public result reservationV3(){
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "reservationTime"));
        Page<Reservation> all = reservationRepository.findAllWithJoinFetch(pageRequest);
        List<reservationSimpleDto> collect = all.stream()
                .map(o -> new reservationSimpleDto(o.getName(), o.getPeopleCount(), o.getReservationTime()))
                .collect(Collectors.toList());
        return new result(collect.size(), collect);
    }
    @GetMapping("/api/v4/simpleOrders")
    public result reservationV4(){
        List<reservationSimpleDtos> reservationDto = reservationQueryRepository.findReservationDto();
        return new result(reservationDto.size(),reservationDto);
    }
    @Data
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

        public reservationSimpleDto(String name, int peopleCount, LocalDateTime localDateTime) {
            this.name = name;
            this.peopleCount = peopleCount;
            this.localDateTime = localDateTime;
        }
    }
}
