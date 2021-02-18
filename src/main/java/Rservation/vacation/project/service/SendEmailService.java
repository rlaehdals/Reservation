package Rservation.vacation.project.service;

import Rservation.vacation.project.controller.MailDto;

public interface SendEmailService{
    MailDto createMailAndChangePassword(String userEmail, String userName);

    void updatePassword(String str, String userEmail);

    String getTempPassword();

    void mailSend(MailDto dto);
}
