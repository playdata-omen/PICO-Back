package kr.omen.pico.service;

import javassist.NotFoundException;
import kr.omen.pico.dao.ApplyRepository;
import kr.omen.pico.dao.EstimateRepository;
import kr.omen.pico.dao.PhotographerRepository;
import kr.omen.pico.domain.Apply;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.Photographer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final EstimateRepository estimateRepository;

    private final PhotographerRepository photographerRepository;

    private final ApplyRepository applyRepository;

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
}
