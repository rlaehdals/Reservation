package Rservation.vacation.project.controller;

import Rservation.vacation.project.domain.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UserDto {

    @NotEmpty(message ="이름은 필수 입니다.")
    private String name;
    @NotEmpty(message = "이메일은 필수 입니다.")
    private String email;
    @NotEmpty(message = "비밀 번호는 필수 입니다.")
    private String password;
    @NotEmpty(message = "번호 입력은 필수입니다.")
    private String phoneNumber;
    private String city;
    private String street;
    private String zipCode;
    private String auth="USER";

}
