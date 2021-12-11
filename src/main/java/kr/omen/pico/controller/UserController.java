package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.UserDTO;
import kr.omen.pico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String userLogin(@RequestBody UserDTO.Login data) throws Exception {
        UserDTO.Register result = userService.userLogin(data.getCode(), data.getProvider());

        return result.toString();
    }

}