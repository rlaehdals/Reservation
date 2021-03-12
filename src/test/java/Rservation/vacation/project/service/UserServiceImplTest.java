package Rservation.vacation.project.service;

import Rservation.vacation.project.domain.Address;
import Rservation.vacation.project.domain.UserInfo;
import Rservation.vacation.project.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceImplTest {
    @Autowired UserRepository userRepository;
    @Autowired UserService userService;

    @Test
    void 회원가입(){
        Address address = new Address("a","a","a");
        UserInfo userInfo= new UserInfo("a","a","a","a","a",address);
        Long id = userService.save(userInfo);
        Optional<UserInfo> byId = userRepository.findById(id);

        Assertions.assertThat(id).isEqualTo(byId.get().getId());
    }
    @Test
    void 중복가입_이메일로_판별(){
        Address address = new Address("a","a","a");
        UserInfo userInfo= new UserInfo("a","a","a","a","a",address);
        Long id = userService.save(userInfo);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                userService.save(userInfo));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 등록됌");
    }
    @Test
    void load_판별(){
        Address address = new Address("a","a","a");
        UserInfo userInfo= new UserInfo("a","a","a","a","a",address);
        Long id = userService.save(userInfo);
        UserInfo user = (UserInfo)userService.loadUserByUsername("a");
        Assertions.assertThat(id).isEqualTo(user.getId());
    }
    @Test
    void load_오류_판별(){
        Address address = new Address("a","a","a");
        UserInfo userInfo= new UserInfo("a","a","a","a","a",address);
        Long id = userService.save(userInfo);
        UsernameNotFoundException e = assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("b"));
        Assertions.assertThat(e.getMessage()).isEqualTo("b");
    }
}