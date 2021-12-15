package kr.omen.pico.controller;

import kr.omen.pico.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    //할당된 견적요청서에 지원.
    @PutMapping("apply/apply/{estimateId}/{photographerId}")
    public String applyEstimate(@PathVariable Long estimateId,@PathVariable Long photographerId){
        Boolean flag = applyService.applyEstimate(estimateId,photographerId);
        if(flag){
            return "지원 완료";
        }else{
            return "지원 실패";
        }
    }
}
