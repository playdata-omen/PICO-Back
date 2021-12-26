package kr.omen.pico.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import kr.omen.pico.dao.ApplyRepository;
import kr.omen.pico.dao.ChatRoomRepository;
import kr.omen.pico.dao.EstimateRepository;
import kr.omen.pico.dao.PhotographerRepository;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.Apply;
import kr.omen.pico.domain.ChatRoom;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.ApplyDTO;
import kr.omen.pico.domain.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final EstimateRepository estimateRepository;

    private final PhotographerRepository photographerRepository;

    private final ApplyRepository applyRepository;

    private final UserRepository userRepository;

    private final EstimateService estimateService;

    private final ChatRoomRepository chatRoomRepository;

    public Apply findOne(Long applyIdx) throws NotFoundException{
        Apply apply = null;
            apply = applyRepository.findById(applyIdx).get();
        return apply;
    }

    public ResponseDTO.EstimateDetailResponse applyEstimate(Long estimateIdx, Long photographerIdx){

        Estimate estimate = estimateRepository.findById(estimateIdx).get();
        User user = userRepository.findById(estimate.getUser().getUserIdx()).get();

        Photographer photographer = photographerRepository.findById(photographerIdx).get();
        Apply apply = applyRepository.findByPhotographerAndEstimate(photographer,estimate);
        apply.updateApplied(true);
        applyRepository.save(apply);


        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.builder()
                        .estimate(estimate)
                        .user(user)
                        .photographer(photographer)
                        .build());


//        return new ResponseDTO.EstimateChatRoomDetail(estimateService.getUserOneEstimate(estimateIdx),chatRoom);

        return estimateService.getUserOneEstimate(estimateIdx);
    }

    public ApplyDTO.Get rejectEstimate(Long estimateIdx, Long photographerIdx){
        Boolean flag = false;
        Estimate estimate = estimateRepository.findById(estimateIdx).get();
        Photographer photographer = photographerRepository.findById(photographerIdx).get();
        Apply apply = applyRepository.findByPhotographerAndEstimate(photographer,estimate);
        apply.update(8);
        applyRepository.save(apply);
        estimate.updateStatus(5);
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
