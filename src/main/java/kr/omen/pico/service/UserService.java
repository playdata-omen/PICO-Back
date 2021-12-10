package kr.omen.pico.service;

import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findOneUser(UserDTO userdto){
        User user = new User();
        user = userRepository.findById(userdto.getUserIdx()).get();

        return user;
    }

}
