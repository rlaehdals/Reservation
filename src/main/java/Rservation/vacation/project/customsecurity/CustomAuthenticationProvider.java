package Rservation.vacation.project.customsecurity;

import Rservation.vacation.project.domain.UserInfo;
import Rservation.vacation.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Log4j2
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String userEmail = token.getName();
        String userPw = (String)token.getPrincipal();
        UserInfo userInfo = (UserInfo) userService.loadUserByUsername(userEmail);
        if(!passwordEncoder.matches(userPw,userInfo.getPassword())){
            throw new BadCredentialsException(userEmail+"잘못된 비밀번입니다.");
        }
        return new UsernamePasswordAuthenticationToken(userInfo,userPw,userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}