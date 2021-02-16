package Rservation.vacation.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CertificationServiceImplTest {

    @Autowired CertificationService certificationService;
    @Test
    void sms_인증(){
        Random rand = new Random();
        String num="";
        for(int i=0;i<4; i++){
            String temp = Integer.toString(rand.nextInt(10));
            num+=temp;
        }
        String phone="01029905871";
        certificationService.certifiedPhoneNumber(phone,num);
    }
}