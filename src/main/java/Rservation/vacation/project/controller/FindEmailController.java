package Rservation.vacation.project.controller;

import Rservation.vacation.project.service.SendEmailService;
import Rservation.vacation.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FindEmailController {

    private final UserService userService;
    private final SendEmailService sendEmailService;
    @GetMapping("/check/findPw")
    @ResponseBody
    public Map<String,Boolean> pw_find(String userEmail, String userName){
        Map<String, Boolean> json = new HashMap<>();
        boolean pwFindCheck = userService.userEmailCheck(userEmail, userName);

        json.put("check", pwFindCheck);
        return json;
    }
    @PostMapping("/check/findPw/sendEmail")
    @ResponseBody
    public void sendEmail(String userEmail, String userName){
        MailDto dto = sendEmailService.createMailAndChangePassword(userEmail, userName);
        sendEmailService.mailSend(dto);
    }



}
