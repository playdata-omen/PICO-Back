package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.UserDTO;
import org.springframework.web.bind.annotation.PostMapping;

public class PhotographerController {
    @PostMapping("/photographer/register")
    public UserDTO.LoginInfo userRegister() {

        return null;
    }
}
