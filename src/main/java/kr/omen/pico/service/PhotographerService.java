package kr.omen.pico.service;

import kr.omen.pico.dao.PhotographerRepository;
import javassist.NotFoundException;
import kr.omen.pico.config.exception.Exception;
import kr.omen.pico.dao.PhotographerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.PhotographerDTO;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PhotographerService {
  
    private final PhotographerRepository photographerRepository;
    
    private final UserRepository userRepository;
    
    public PhotographerDTO getPhotographerInfo(Long uidx){
        User user = userRepository.findById(uidx).get();
        Photographer photographer = photographerRepository.findByUser(user);
        PhotographerDTO photographerDTO = new PhotographerDTO(photographer);
        return photographerDTO;
    }
  
    public Photographer findOne(Long photographerIdx) throws NotFoundException{
        Photographer photographer = null;
        try {
            photographer = photographerRepository.findById(photographerIdx).orElseThrow(() -> new Exception.NotFoundException("Photographer with idx: " + photographerIdx + "is not valid"));
        } catch (Exception.NotFoundException e) {
//            e.printStackTrace();
        }
        return photographer;
    }

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
