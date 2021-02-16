package Rservation.vacation.project.customsecurity;

import Rservation.vacation.project.domain.UserInfo;
import Rservation.vacation.project.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Log4j2
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Resource
    private final UserService userService;
    @NonNull
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String userEmail= authentication.getName();
        String userPw= (String)authentication.getCredentials();
        UserInfo user = (UserInfo)userService.loadUserByUsername(userEmail);
        if(!passwordEncoder.matches(userPw, user.getPassword())){
            throw new BadCredentialsException(user.getName()+"invalid password");
        }
        return new UsernamePasswordAuthenticationToken(user,userPw,user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
