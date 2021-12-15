package kr.omen.pico.service;

import javassist.NotFoundException;
import kr.omen.pico.config.exception.Exception;
import kr.omen.pico.dao.CategoryRepository;
import kr.omen.pico.dao.PCategoryRepository;
import kr.omen.pico.dao.PhotographerRepository;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.PCategory;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.PhotographerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotographerService {
  
    private final PhotographerRepository photographerRepository;
    
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PCategoryRepository pCategoryRepository;

    public PhotographerDTO.PhotographerInfo getPhotographerInfo(Long userIdx){
        User user = userRepository.findById(userIdx).get();
        Photographer photographer = photographerRepository.findByUser(user);
        PhotographerDTO.PhotographerInfo photographerInfo = PhotographerDTO.PhotographerInfo.builder()
                .photographerIdx(photographer.getPhotographerIdx())
                .grade(photographer.getGrade())
                .activityCity(photographer.getActivityCity())
                .activityAddress(photographer.getActivityAddress())
                .hasStudio(photographer.getHasStudio())
                .studioCity(photographer.getStudioCity())
                .studioAddress(photographer.getStudioAddress())
                .isOtherArea(photographer.getIsOtherArea())
                .build();
        return photographerInfo;
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
        if (photographer != null) {
            data.setPhotographerIdx(photographer.getPhotographerIdx());
        }

        photographer = photographerRepository.save(data.toEntity(user));

        // 작가 카테고리 추가 로직
        System.out.println(data.getCategory());
        for (Long categoryIdx : data.getCategory()) {
            Category category = categoryRepository.findById(categoryIdx).get();

            pCategoryRepository.save(
                    PCategory.builder()
                            .photographer(photographer)
                            .category(category)
                            .kind(category.getKind())
                            .build()
            );

        }

        PhotographerDTO.PhotographerInfo result = PhotographerDTO.PhotographerInfo.builder()
                .photographerIdx(photographer.getPhotographerIdx())
                .grade(photographer.getGrade())
                .hasStudio(photographer.getHasStudio())
                .activityCity(photographer.getActivityCity())
                .activityAddress(photographer.getActivityAddress())
                .studioCity(photographer.getStudioCity())
                .studioAddress(photographer.getStudioAddress())
                .isOtherArea(photographer.getIsOtherArea())
                .build();

        return result;
    }
}
