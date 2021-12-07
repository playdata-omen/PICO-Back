package kr.omen.pico.service;

import kr.omen.pico.dao.*;
import kr.omen.pico.domain.*;
import kr.omen.pico.domain.dto.EstimateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstimateService {

    @Autowired
    private EstimateRepository estimateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private ApplyRepository applyRepository;

    //여기서 현재는 수동으로 입력받는 useridx는 추후에 sns 시큐리티 적용되면,
    // principaldetail인가? 거기서 getUser 형식으로 가져와서 사용 할 것으로 예상됨.
    public EstimateDTO createGlobalEstimate(EstimateDTO estimateDTO){

        Estimate estimate = new Estimate();
        User user = userRepository.findById(estimateDTO.getUser()).get();
        Category category = categoryRepository.findById(estimateDTO.getCategory()).get();

        estimate.setAddress(estimateDTO.getAddress());
        estimate.setCity(estimateDTO.getCity());
        estimate.setCategory(category);
        estimate.setUser(user);
        estimate.setStartDate(estimateDTO.getStartDate());
        estimate.setEndDate(estimateDTO.getEndDate());
        estimate.setContent(estimateDTO.getContent());
        estimate.setStatus(estimateDTO.getStatus());
        estimateRepository.save(estimate);

        //글로벌 견적 요청 후 저장 시, 해당 견적서에 설정된 City가 주 활동지역인 작가들의 list 뽑아낸 후
        //해당 작가들은 강제로 Status가 '1'인 상태로 해당 견적서에 지원하게 됨.(Status 1 == 글로벌 견적요청 할당됨)
        List<Photographer> list = photographerRepository.findByCity(estimate.getCity());
        for(Photographer photographer : list){
            Apply apply = new Apply();
            apply.setEstimate(estimate);
            apply.setStatus("1");
            apply.setPhotographer(photographer);
            applyRepository.save(apply);
        }
        return new EstimateDTO(estimate);
    }

}
