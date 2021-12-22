package kr.omen.pico.dao;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Review;
import kr.omen.pico.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByUserAndPhotographer(User user, Photographer photographer);
//    List<Review> findReviewListByPhotographerIdx(Long photographerIdx);
    List<Review> findAllByPhotographer(Photographer photographer);


}
