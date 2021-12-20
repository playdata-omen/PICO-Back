package kr.omen.pico.controller;

import javassist.NotFoundException;
import kr.omen.pico.domain.Review;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.domain.dto.ReviewDTO;
import kr.omen.pico.service.ApplyService;
import kr.omen.pico.service.PhotographerService;
import kr.omen.pico.service.ReviewService;
import kr.omen.pico.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final ApplyService applyService;
    private final PhotographerService photographerService;


    @PostMapping("/review")
//    public ResponseDTO.Create saveReview(@RequestBody ReviewDTO.Create dto, @PathVariable Long pID) {
    public ResponseDTO.Create saveReview(@RequestBody ReviewDTO.Create dto) {

        Review saveReview = reviewService.saveReview(dto);

        boolean result = false;
        Long reviewIdx = null;

        try {
            reviewIdx = saveReview.getReviewIdx();
            result = true;
        }catch (NullPointerException | NoSuchElementException e){
//            e.printStackTrace();
        }
        System.out.println("11111111111111111111111");
        return new ResponseDTO.Create(reviewIdx, result);
    }

    @DeleteMapping("/review/delete/{reviewIdx}/photographer/{photographerIdx}")
    public ResponseDTO.Delete deleteReview(@PathVariable Long reviewIdx, @PathVariable Long photographerIdx){
        boolean result = reviewService.deleteReview(reviewIdx, photographerIdx);
        return new ResponseDTO.Delete(result);
    }

    @PutMapping("/review/update")
//    @PutMapping("/review/update/{reviewIdx}/photographer/{photographerIdx}/user/{userIdx}")
    public ResponseDTO.Update updateReview(@RequestBody ReviewDTO.Update dto){
//    public ResponseDTO.Update updateReview(@RequestBody ReviewDTO.Update dto, @PathVariable Long reviewIdx, @PathVariable Long photographerIdx, @PathVariable Long userIdx){
        User user = userService.getUser();
        boolean result = reviewService.updateReview(dto, user.getUserIdx());
//        boolean result = reviewService.updateReview(dto, reviewIdx, photographerIdx, userIdx);

        return new ResponseDTO.Update(result);
    }

//    photographerIdx userIdx로 찾아오기
//    @GetMapping("/review/select/{photographerIdx}")
    @GetMapping("/reviewList/{userIdx}")
    public ResponseDTO.reviewListResponse findAllByPhotographer(@PathVariable Long userIdx) {

        List<ReviewDTO.Card> reviewList = reviewService.reviewListByPhotographer(userIdx);
        System.out.println(reviewList);
        return new ResponseDTO.reviewListResponse(reviewList);
    }
}
