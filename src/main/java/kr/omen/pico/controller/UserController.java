package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.UserDTO;
//import kr.omen.pico.model.LoginInfo;
import kr.omen.pico.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public UserDTO.LoginInfo userLogin(@RequestBody UserDTO.Login data) throws Exception {
        return userService.userLogin(data.getCode(), data.getProvider());
    }

    @PostMapping("/user/register")
    public UserDTO.LoginInfo userRegister(@RequestBody UserDTO.UserInfo data) {
        return userService.userRegister(data);
    }

    @GetMapping("/user/{photographerIdx}")
    public UserDTO.UserInfo photographerIdxGetUser(@PathVariable Long photographerIdx) {
        return userService.photographerIdxGetUser(photographerIdx);
    }
}
