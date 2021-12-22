package kr.omen.pico.controller;

import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.ApplyDTO;
import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.service.ApplyService;
import kr.omen.pico.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    private final UserService userService;

    // 할당된 견적요청서에 지원.
    @PutMapping("estimate/{estimateIdx}/photographer/{photographerIdx}/apply")

    public ResponseDTO.EstimateChatRoomDetail applyEstimate(@PathVariable Long estimateIdx, @PathVariable Long photographerIdx){

        return applyService.applyEstimate(estimateIdx,photographerIdx);
    }

    //할당된 작가지정 견적 요청에 지원하지않음(거절함)
    @PutMapping("estimate/{estimateIdx}/photographer/{photographerIdx}/apply/reject")
    public ApplyDTO.Get rejectEstimate(@PathVariable Long estimateIdx,@PathVariable Long photographerIdx){
        return applyService.rejectEstimate(estimateIdx,photographerIdx);

    }

    @GetMapping("/apply/{applyIdx}")
    public ApplyDTO.Get getApply(@PathVariable Long applyIdx){
        return applyService.getApply(applyIdx);
    }

    @GetMapping("/apply")
    public List<ApplyDTO.Card> getApplies(){
        User user = userService.getUser();
        return applyService.getApplies(user.getUserIdx());
    }
}
