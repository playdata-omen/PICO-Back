package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.UserDTO;
import kr.omen.pico.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDTO.Info userLogin(@RequestBody UserDTO.Login data) throws Exception {
        UserDTO.Info result = userService.userLogin(data.getCode(), data.getProvider());

        return result;
    }

    @GetMapping("/user")
    public String getUser(HttpServletRequest request) {

        userService.getUser(request.getUserPrincipal().getName());

        return "성공";
    }

}
