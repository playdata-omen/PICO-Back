package kr.omen.pico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.User;

public interface EstimateRepository extends JpaRepository<Estimate ,Long> {
    List<Estimate> findAllByUser(User user);
}
