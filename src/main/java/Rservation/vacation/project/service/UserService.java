package Rservation.vacation.project.service;

import Rservation.vacation.project.controller.UserDto;
import Rservation.vacation.project.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService{
    List<UserInfo> findAll();
    Long save(UserInfo userInfo);
    boolean userEmailCheck(String userEmail, String userName);
}
