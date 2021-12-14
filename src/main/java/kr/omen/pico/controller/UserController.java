package kr.omen.pico.controller;

import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.UserDTO;
import kr.omen.pico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDTO.LoginInfo userLogin(@RequestBody UserDTO.Login data) throws Exception {
        return userService.userLogin(data.getCode(), data.getProvider());
    }

    @PostMapping("/user/register")
    public UserDTO.LoginInfo userRegister(@RequestBody UserDTO.UserInfo data) {
        return userService.userRegister(data);
    }

    @GetMapping("/user")
    public User getUser() {
        User result = userService.getUser();

        return result;
    }

}
