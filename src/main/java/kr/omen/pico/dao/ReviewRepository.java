package kr.omen.pico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Review;
import kr.omen.pico.domain.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByUserAndPhotographer(User user, Photographer photographer);
    List<Review> findAllByPhotographer(Photographer photographer);
}
