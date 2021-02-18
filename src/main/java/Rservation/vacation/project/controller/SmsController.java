package Rservation.vacation.project.controller;

import Rservation.vacation.project.service.CertificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequiredArgsConstructor
@Log4j2
public class SmsController {

    private final CertificationService certificationService;
    @GetMapping("/check/sendSMS")
    @ResponseBody
    public String sendSMS(String phoneNumber){
        log.info("sms인증 시도");
        Random rand = new Random();
        String numStr="";
        for(int i=0; i<4; i++){
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }
        System.out.println("수신자 번호 = " + phoneNumber);
        System.out.println("인증번호 = " + numStr);
        certificationService.certifiedPhoneNumber(phoneNumber,numStr);
        return numStr;
    }
}
