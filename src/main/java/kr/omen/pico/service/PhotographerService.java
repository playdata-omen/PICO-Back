package kr.omen.pico.service;

import kr.omen.pico.dao.PhotographerRepository;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.PhotographerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotographerService {

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private UserRepository userRepository;

    public PhotographerDTO getPhotographerInfo(Long uidx){
        User user = userRepository.findById(uidx).get();
        Photographer photographer = photographerRepository.findByUser(user);
        PhotographerDTO photographerDTO = new PhotographerDTO(photographer);
        return photographerDTO;
    }
}
