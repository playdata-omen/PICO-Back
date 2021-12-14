package kr.omen.pico.service;

import javassist.NotFoundException;
import kr.omen.pico.config.exception.Exception;
import kr.omen.pico.dao.PhotographerRepository;
import kr.omen.pico.domain.Photographer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotographerService {
    private final PhotographerRepository photographerRepository;

    public Photographer findOne(Long photographerIdx) throws NotFoundException{
        Photographer photographer = null;
        try {
            photographer = photographerRepository.findById(photographerIdx).orElseThrow(() -> new Exception.NotFoundException("Photographer with idx: " + photographerIdx + "is not valid"));
        } catch (Exception.NotFoundException e) {
//            e.printStackTrace();
        }
        return photographer;
    }
}
