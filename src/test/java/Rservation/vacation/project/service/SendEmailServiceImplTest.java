package Rservation.vacation.project.service;

import Rservation.vacation.project.controller.MailDto;
import Rservation.vacation.project.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SendEmailServiceImplTest {
    @Autowired UserRepository userRepository;
    @Autowired SendEmailService sendEmailService;

    @Test
    public void 메일전송확인(){
        MailDto dto = new MailDto();
        dto.setMessage("임시확인용");
        dto.setTitle("밍");
        dto.setAddress("받는사람");

        sendEmailService.mailSend(dto);
    }
}