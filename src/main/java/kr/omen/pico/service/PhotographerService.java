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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotographerService {
  
    private final PhotographerRepository photographerRepository;
    
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PCategoryRepository pCategoryRepository;

    public PhotographerDTO.PhotographerInfo getPhotographerInfo(Long userIdx){
        List<Long> category = new ArrayList<>();

        User user = userRepository.findById(userIdx).get();

        Photographer photographer = photographerRepository.findByUser(user);

        for (PCategory pcategory : pCategoryRepository.findByPhotographer(photographer)) {
             category.add(pcategory.getCategory().getCategoryIdx());
        }

        PhotographerDTO.PhotographerInfo photographerInfo = PhotographerDTO.PhotographerInfo.builder()
                .photographerIdx(photographer.getPhotographerIdx())
                .grade(photographer.getGrade())
                .activityCity(photographer.getActivityCity())
                .activityAddress(photographer.getActivityAddress())
                .hasStudio(photographer.getHasStudio())
                .studioCity(photographer.getStudioCity())
                .studioAddress(photographer.getStudioAddress())
                .isOtherArea(photographer.getIsOtherArea())
                .category(category)
                .build();
        return photographerInfo;
    }

    // 채팅에서 쓰인거로 추정되며 채팅 수정시 삭제예정
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
        
        // 작가 등록과 수정을 하나의 api에서 사용하기 수정시에도 작가 카테고리가 중복 저장되는 현상이 있음
        // 임시로 해당 작가의 카테고리를 모두 삭제하고 새롭게 등록하는 것으로 수정
        List<PCategory> pCategory = pCategoryRepository.findByPhotographer(photographer);
        pCategoryRepository.deleteAll(pCategory);
        
        // 작가 카테고리 추가 로직
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
                .category(data.getCategory())
                .build();

        return result;
    }

    public void searchByCategoryPhotographer(Long categoryIdx) {
        List pCategoryList = pCategoryRepository.findAllByCategory(categoryIdx);

        System.out.println(pCategoryList);
    }
}
