package Rservation.vacation.project.service;

import Rservation.vacation.project.controller.UserDto;
import Rservation.vacation.project.domain.UserInfo;
import Rservation.vacation.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException((email)));
    }

    @Override
    public Long save(UserInfo userInfo){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        duplicateValid(userInfo);
        return userRepository.save(UserInfo.builder()
        .email(userInfo.getEmail())
                .auth(userInfo.getAuth())
                .password(userInfo.getPassword()).build()).getId();
    }
    private void duplicateValid(UserInfo info) {
        userRepository.findByEmail(info.getEmail()).ifPresent(m-> {throw new IllegalStateException("이미 등록됌");});
    }
    @Transactional(readOnly = true)
    @Override
    public List<UserInfo> findAll(){
        return userRepository.findAll();
    }
}
