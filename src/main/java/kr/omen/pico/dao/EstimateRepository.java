package kr.omen.pico.dao;

import kr.omen.pico.service.domain.Estimate;
import kr.omen.pico.service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstimateRepository extends JpaRepository<Estimate ,Long> {
    List<Estimate> findAllByUser(User user);
}
