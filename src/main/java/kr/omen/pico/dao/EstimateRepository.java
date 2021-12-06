package kr.omen.pico.dao;

import kr.omen.pico.domain.Estimate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstimateRepository extends JpaRepository<Estimate ,Long> {

}
