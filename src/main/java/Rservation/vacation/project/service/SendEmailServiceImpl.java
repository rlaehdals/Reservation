package Rservation.vacation.project.service;

import Rservation.vacation.project.controller.MailDto;
import Rservation.vacation.project.domain.UserInfo;
import Rservation.vacation.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SendEmailServiceImpl implements SendEmailService{
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final String FROM_ADDRESS= "보내는 사람";
    @Override
    public MailDto createMailAndChangePassword(String userEmail, String userName) {
        String str= getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle(userName+"님의 임시 비밀번호 안내 이메일입니다.");
        dto.setMessage("안녕하세요. 임시 비밀번호 관련 이메일입니다."+userName+"님의 임시비밀번호는:"+str+"입니다.");
        updatePassword(str,userEmail);
        return dto;
    }

    @Override
    public void updatePassword(String str, String userEmail) {
        String pw = bCryptPasswordEncoder.encode(str);
        UserInfo userInfo = userRepository.findByEmail(userEmail).get();
        userInfo.setPassword(pw);
    }

    @Override
    public String getTempPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";
        int idx= 0;

        for(int i=0; i<10; i++){
            idx= (int) (charSet.length*Math.random());
            str+=charSet[idx];
        }
        return str;
    }

    @Override
    public void mailSend(MailDto dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(dto.getMessage());
        message.setFrom(FROM_ADDRESS);
        message.setSubject(dto.getTitle());
        message.setText(dto.getMessage());
        mailSender.send(message);
    }
}
