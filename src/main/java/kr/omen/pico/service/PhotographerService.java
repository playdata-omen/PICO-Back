package kr.omen.pico.service;

import kr.omen.pico.dao.PhotographerRepository;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.PhotographerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PhotographerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhotographerRepository photographerRepository;

    public PhotographerDTO.PhotographerInfo photographerRegister(PhotographerDTO.PhotographerRegister data) {
        User user = userRepository.findById(data.getUserIdx()).get();
        Photographer photographer = photographerRepository.findByUser(user);
        if (!Objects.isNull(photographer)) {
            data.setPhotographerIdx(photographer.getPhotographerIdx());
        }
        photographer = photographerRepository.save(data.toEntity(user));
        PhotographerDTO.PhotographerInfo result = PhotographerDTO.PhotographerInfo.builder()
                .photographerIdx(photographer.getPhotographerIdx())
                .grade(photographer.getGrade())
                .isStudio(photographer.isStudio())
                .activityCity(photographer.getActivityCity())
                .activityAddress(photographer.getActivityAddress())
                .studioCity(photographer.getStudioCity())
                .studioAddress(photographer.getStudioAddress())
                .otherArea(photographer.isOtherArea())
                .build();

        return result;
    }
}
