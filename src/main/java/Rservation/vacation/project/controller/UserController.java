package Rservation.vacation.project.controller;

import Rservation.vacation.project.domain.Address;
import Rservation.vacation.project.domain.UserInfo;
import Rservation.vacation.project.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@Log4j2
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/login/user")
    public String createSignup(Model model){
        model.addAttribute("user", new UserDto());
        return "/login";
    }
    @PostMapping("/login/user")
    public String signup(@Valid UserDto infoDto, BindingResult result){
        log.info("로그인 시도");
        if(result.hasErrors()){
            log.info("실패");
            return "/login";
        }
        Address address = new Address(infoDto.getCity(), infoDto.getStreet(), infoDto.getZipCode());
        UserInfo userInfo = new UserInfo(infoDto.getEmail(), infoDto.getPassword(),infoDto.getAuth(), infoDto.getName()
        , infoDto.getPhoneNumber(), address);
        userServiceImpl.save(userInfo);
        log.info("로그인 성공");
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/";
    }
}
