package Rservation.vacation.project.service;

import Rservation.vacation.project.controller.UserDto;
import Rservation.vacation.project.domain.UserInfo;

import java.util.List;

public interface UserService {
    List<UserInfo> findAll();
    Long save(UserInfo userInfo);
}
