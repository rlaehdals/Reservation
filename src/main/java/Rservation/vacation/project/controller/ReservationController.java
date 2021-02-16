package Rservation.vacation.project.controller;

import Rservation.vacation.project.domain.Date;
import Rservation.vacation.project.domain.UserInfo;
import Rservation.vacation.project.service.DateService;
import Rservation.vacation.project.service.ReservationService;
import Rservation.vacation.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;
    private final DateService dateService;

    @GetMapping("미정")
    public String createReservationForm(Model model){
        List<UserInfo> userInfo =  userService.findAll();
        List<Date> date = dateService.findAll();

        model.addAttribute("userInfo", userInfo);
        model.addAttribute("date", date);

        return "home";
    }
    @PostMapping("/asdfsd")
    public String createReservation(@RequestParam("userInfoId") Long userInfoId,
                                    @RequestParam("dateId") Long dateId,
                                    @RequestParam("peopleCount") int peopleCount) throws Exception{
        reservationService.join(userInfoId,dateId,peopleCount);
        return "home";
    }
    //조회를 넣을 예정인데 동적sql을 아직 할줄모름
    //@GetMapping("미정")
    //public String lookUp(Model model)


    @PostMapping("미정")
    public String cancel(@PathVariable("reservationId") Long reservationId){
        reservationService.cancelReservation(reservationId);
        return "home";
    }

}
