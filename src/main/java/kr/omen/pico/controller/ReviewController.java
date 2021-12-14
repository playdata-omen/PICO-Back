package kr.omen.pico.controller;

import javassist.NotFoundException;
import kr.omen.pico.config.exception.Exception;
import kr.omen.pico.dao.ReviewRepository;
import kr.omen.pico.dao.chatdao.ChatRoomRepository;
import kr.omen.pico.domain.Apply;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Review;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.domain.dto.ResponseDTO.Create;
import kr.omen.pico.domain.dto.ReviewDTO;
import kr.omen.pico.service.ApplyService;
import kr.omen.pico.service.PhotographerService;
import kr.omen.pico.service.ReviewService;
import kr.omen.pico.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

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
        reviewService.saveReview(dto, pID);
    }
}
