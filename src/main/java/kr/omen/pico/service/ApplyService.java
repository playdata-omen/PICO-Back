package kr.omen.pico.service;

import javassist.NotFoundException;
import kr.omen.pico.dao.*;
import kr.omen.pico.domain.*;
import kr.omen.pico.domain.dto.ApplyDTO;
import kr.omen.pico.domain.dto.ResponseDTO;
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


    private final ChatRoomRepository chatRoomRepo;

    private final EstimateService estimateService;

    private final ChatRoomRepository chatRoomRepository;

    // 뭐하는놈인지 물어보기
    public Apply findOne(Long applyIdx) throws NotFoundException{
        Apply apply = null;
            apply = applyRepository.findById(applyIdx).get();
        return apply;
    }


    public ResponseDTO.EstimateChatRoomDetail applyEstimate(Long estimateIdx, Long photographerIdx){

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


        return new ResponseDTO.EstimateChatRoomDetail(estimateService.getUserOneEstimate(estimateIdx),chatRoom);

//        return new ApplyDTO.Get(apply);
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
