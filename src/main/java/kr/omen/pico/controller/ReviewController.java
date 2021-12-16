package kr.omen.pico.controller;

import kr.omen.pico.domain.Review;
import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.domain.dto.ReviewDTO;
import kr.omen.pico.service.ApplyService;
import kr.omen.pico.service.PhotographerService;
import kr.omen.pico.service.ReviewService;
import kr.omen.pico.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final ApplyService applyService;
    private final PhotographerService photographerService;

    //    자신과 매칭된 작가에 대한 리뷰 작성
//    @PostMapping("/review/enroll")
//    public Create saveReview(@RequestBody ReviewDTO.Create dto) {
//
//        boolean result = false;
//        Long saveId = null;
//        try {
//            Apply apply = applyService.findOne(dto.getApplyIdx());
//            Photographer photographer = photographerService.findOne(dto.getPhotographerIdx());
//            User user = userService.findOne(apply.getEstimate().getUser().getUserIdx());
//
//            if (reviewService.isNotYetReview(user, photographer)) {
//                try {
//                    saveId = reviewService.saveReview(new Review(user, photographer,dto.getCreated(), dto.getContent(), dto.getGrade()));
//                    result = true;
//                } catch (Exception.ArgumentNullException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                System.out.println("동일한 리뷰를 작성할 수 없습니다.");
//            }
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//        }
//        return new ResponseDTO.Create(saveId, result);
//    }
    @PostMapping("/review/enroll/{pID}")
    public ResponseDTO.Create saveReview(@RequestBody ReviewDTO.Create dto, @PathVariable Long pID) {
        Review saveReview = reviewService.saveReview(dto, pID);
        return new ResponseDTO.Create(saveReview.getReviewIdx(), true);
    }

    @DeleteMapping("/review/delete/{reviewIdx}/photographer/{photographerIdx}")
    public ResponseDTO.Delete deleteReview(@PathVariable Long reviewIdx, @PathVariable Long photographerIdx){
        boolean result = reviewService.deleteReview(reviewIdx, photographerIdx);
        return new ResponseDTO.Delete(result);
    }

    @PutMapping("/review/update/{reviewIdx}/photographer/{photographerIdx}")
    public ResponseDTO.Update deleteReview(@RequestBody ReviewDTO.Update dto, @PathVariable Long reviewIdx, @PathVariable Long photographerIdx){
        boolean result = reviewService.updateReview(dto, reviewIdx, photographerIdx);
        return new ResponseDTO.Update(result);
    }

    @GetMapping("/review/average/{photographerIdx}")
    public ResponseDTO.gradeAverage gradeAverage(@PathVariable Long photographerIdx){
        Float gradeAverage = reviewService.gradeAverage(photographerIdx);
        return new ResponseDTO.gradeAverage(gradeAverage);
    }

}
