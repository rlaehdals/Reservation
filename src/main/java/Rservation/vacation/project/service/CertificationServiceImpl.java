package Rservation.vacation.project.service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional
public class CertificationServiceImpl implements CertificationService{
    @Override
    public void certifiedPhoneNumber(String phoneNumber, String numStr) {
        String api_key="NCS7JKJZHTQD4A0H";
        String api_secret="1G7WZXFSKJIQTLJRGEL6KFGBOQUAZIJV";
        Message coolSMS = new Message(api_key,api_secret);

        HashMap<String ,String > params = new HashMap<>();

        params.put("to", phoneNumber);
        params.put("from", "01029905871");
        params.put("type", "SMS");
        params.put("text", "휴대폰인증 테스트 메시지 : 인증번호는" + "["+numStr+"]" + "입니다.");
        params.put("vacation.project", "test app 2.2");
        try {
            JSONObject obj = (JSONObject) coolSMS.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
