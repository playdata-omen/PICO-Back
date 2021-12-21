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

    // 뭐하는놈인지 물어보기
    public Apply findOne(Long applyIdx) throws NotFoundException{
        Apply apply = null;
            apply = applyRepository.findById(applyIdx).get();
        return apply;
    }

    public ApplyDTO.Get applyEstimate(Long estimateIdx,Long photographerIdx){
        Estimate estimate = estimateRepository.findById(estimateIdx).get();
        Photographer photographer = photographerRepository.findById(photographerIdx).get();
        Apply apply = applyRepository.findByPhotographerAndEstimate(photographer,estimate);
        apply.updateApplied(true);
        applyRepository.save(apply);
        return new ApplyDTO.Get(apply);
    }

    public ApplyDTO.Get rejectEstimate(Long estimateIdx,Long photographerIdx){

        Boolean flag = false;
        Estimate estimate = estimateRepository.findById(estimateIdx).get();
        Photographer photographer = photographerRepository.findById(photographerIdx).get();
        Apply apply = applyRepository.findByPhotographerAndEstimate(photographer,estimate);
        apply.update("8");
        applyRepository.save(apply);
        estimate.updateStatus("5");
        estimateRepository.save(estimate);

        ApplyDTO.Get dto = new ApplyDTO.Get(apply);
        return dto;
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
