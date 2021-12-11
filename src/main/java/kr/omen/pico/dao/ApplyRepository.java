package kr.omen.pico.dao;

import kr.omen.pico.domain.Apply;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply,Long> {
    List<Apply> findAllByEstimate(Estimate estimate);
    List<Apply> findAllByPhotographer(Photographer photographer);
}
