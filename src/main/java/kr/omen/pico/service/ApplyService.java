package kr.omen.pico.service;

import javassist.NotFoundException;
import kr.omen.pico.dao.ApplyRepository;
import kr.omen.pico.dao.EstimateRepository;
import kr.omen.pico.dao.PhotographerRepository;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.Apply;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.ApplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final EstimateRepository estimateRepository;

    private final PhotographerRepository photographerRepository;

    private final ApplyRepository applyRepository;

    private final UserRepository userRepository;

    public Apply findOne(Long applyIdx) throws NotFoundException{
        Apply apply = null;
        System.out.println("서비스전");
            apply = applyRepository.findById(applyIdx).get();
        System.out.println(apply.getApplyIdx());
        return apply;
    }

    public Boolean applyEstimate(Long estimateId,Long photographerId){
        Estimate estimate = estimateRepository.findById(estimateId).get();
        Photographer photographer = photographerRepository.findById(photographerId).get();
        Apply apply = applyRepository.findByPhotographerAndEstimate(photographer,estimate);
        if(apply.getIsApplied()){
           return false;
        }else{
            apply.updateApplied(true);
            applyRepository.save(apply);
            return true;
        }
    }

    public Boolean rejectEstimate(Long estimateId,Long photographerId){

        Boolean flag = false;
        Estimate estimate = estimateRepository.findById(estimateId).get();
        Photographer photographer = photographerRepository.findById(photographerId).get();
        Apply apply = applyRepository.findByPhotographerAndEstimate(photographer,estimate);
        try {
            apply.update("8");
            applyRepository.save(apply);
            estimate.updateStatus("5");
            estimateRepository.save(estimate);
            flag = true;
        }catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    public ApplyDTO.Get getApply(Long applyIdx){
        Apply apply = applyRepository.findById(applyIdx).get();
        ApplyDTO.Get dto = new ApplyDTO.Get(apply);
        return dto;
    }

    public List<ApplyDTO.Card> getApplies(Long userIdx){
        User user = userRepository.findById(userIdx).get();
        Photographer photographer=photographerRepository.findByUser(user);
        List<Apply> applies = applyRepository.findAllByPhotographer(photographer);
        List<ApplyDTO.Card> list = new ArrayList<>();
        for(Apply apply : applies){
            list.add(new ApplyDTO.Card(apply));
        }
        return list;
    }
}
