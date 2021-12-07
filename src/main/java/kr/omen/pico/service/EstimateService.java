package kr.omen.pico.service;

import kr.omen.pico.dao.CategoryRepository;
import kr.omen.pico.dao.EstimateRepository;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.EstimateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstimateService {

    @Autowired
    private EstimateRepository estimateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

        estimateRepository.save(estimate);

        return new EstimateDTO(estimate);
    }
}
