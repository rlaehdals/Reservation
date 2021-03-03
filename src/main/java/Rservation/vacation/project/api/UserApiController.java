package Rservation.vacation.project.api;

import Rservation.vacation.project.domain.Address;
import Rservation.vacation.project.domain.UserInfo;
import Rservation.vacation.project.service.UserService;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @GetMapping("/api/v1/members")
    public List<UserInfo> membersV1(){
        return userService.findAll();
    }

    @GetMapping("/api/v2/members")
    public createResponse membersV2(){
        List<UserInfo> all = userService.findAll();
        List<UserApiDto> result = all.stream()
                .map(o -> new UserApiDto(o.getEmail()))
                .collect(Collectors.toList());
        return new createResponse(result);

    }
    @PostMapping("/api/v1/members")
    public createResponse saveMemberV1(@RequestBody @Valid UserInfo userInfo){
        Long save = userService.save(userInfo);
        return new createResponse(save);
    }
    @PostMapping("/api/v2/members")
    public createResponse saveMemberV2(@RequestBody @Valid UserApiDto userApiDto){
        UserInfo userInfo = new UserInfo(userApiDto.getEmail());
        Long save = userService.save(userInfo);
        return new createResponse(save, userInfo.getEmail());
    }
    @PutMapping("/api/v1/members/{id}")
    public createResponse updateMemberV1(@PathVariable("id") Long userId,@RequestBody @Valid UserApiDto userApiDto){
        UserInfo userInfo = userService.findById(userId);
        userService.updateAddress(userInfo.getId(),userApiDto.getAddress());
        return new createResponse(userId,userInfo.getAddress());
    }

    @Data
    static class createResponse<T>{
        private T data;
        private String email;
        private Address address;
        public createResponse(T data) {
            this.data = data;
        }

        public createResponse(T data, String email) {
            this.data = data;
            this.email = email;
        }

        public createResponse(T data, Address address) {
            this.data = data;
            this.address = address;
        }
    }

    @Data
    @Getter
    static class UserApiDto{
        @NotEmpty
        private String email;
        private Address address;

        public UserApiDto(String email) {
            this.email = email;
        }
    }
}
