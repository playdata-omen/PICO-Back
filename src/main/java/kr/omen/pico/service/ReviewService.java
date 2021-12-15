package kr.omen.pico.service;

import kr.omen.pico.config.exception.Exception;
import kr.omen.pico.dao.ApplyRepository;
import kr.omen.pico.dao.PhotographerRepository;
import kr.omen.pico.dao.ReviewRepository;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.Apply;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Review;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ApplyRepository applyRepository;
    private final PhotographerRepository photographerRepository;

    public Long saveReview(Review review) throws Exception.ArgumentNullException{
        Review saveReview = null;

        if(review == null){
            throw new Exception.ArgumentNullException("Review can't be  null");
        }
        saveReview = reviewRepository.save(review);
        return saveReview.getReviewIdx();
    }

    public boolean isNotYetReview(User user, Photographer photographer){
        boolean flag = false;
        Review review = reviewRepository.findByUserAndPhotographer(user, photographer);
        flag = review == null;

        return flag;
    }

    public void saveReview(ReviewDTO.Create dto, Long pID) {

        Apply apply = applyRepository.findById(dto.getApplyIdx()).get();
        Photographer photographer = photographerRepository.findById(dto.getPhotographerIdx()).get();
        User user = userRepository.findById(apply.getEstimate().getUser().getUserIdx()).get();

        if(isNotYetReview(user, photographer)){
            Review review = dto.toEntity(user, photographer);
        }
    }
}
