package Rservation.vacation.project.controller;

import Rservation.vacation.project.domain.Date;
import Rservation.vacation.project.domain.Reservation;
import Rservation.vacation.project.domain.UserInfo;
import Rservation.vacation.project.service.DateService;
import Rservation.vacation.project.service.ReservationService;
import Rservation.vacation.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;
    private final DateService dateService;

    @GetMapping("/login/reservation")
    public String createReservationForm(Model model){
        List<UserInfo> userInfo =  userService.findAll();
        List<Date> date = dateService.findAll();

        model.addAttribute("userInfo", userInfo);
        model.addAttribute("date", date);

        return "/create/reservation";
    }
    @PostMapping("/login/reservation")
    public String createReservation(@RequestParam("userInfoId") Long userInfoId,
                                    @RequestParam("dateId") Long dateId,
                                    @RequestParam("peopleCount") int peopleCount) throws Exception{
        reservationService.join(userInfoId,dateId,peopleCount);
        return "/login/user";
    }

    @PostMapping("/reservation/cancel")
    public String cancel(@PathVariable("reservationId") Long reservationId){
        reservationService.cancelReservation(reservationId);
        return "/login/user";
    }
    @GetMapping("/login/reservation/config/{id}")
    public String reservationConfig(@PathVariable("id") Long userId, Model model){
        UserInfo user = userService.findById(userId);
        List<Reservation> reservation = user.getReservations().stream().collect(Collectors.toList());
        model.addAttribute("reservation",reservation);
        return "/login/reservationList";
    }
}
