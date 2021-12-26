package kr.omen.pico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.omen.pico.domain.Apply;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.Photographer;

public interface ApplyRepository extends JpaRepository<Apply,Long> {
    List<Apply> findAllByEstimate(Estimate estimate);
    List<Apply> findAllByPhotographer(Photographer photographer);
    Apply findByPhotographerAndEstimate(Photographer photographer,Estimate estimate);
    Apply findByEstimateAndStatus(Estimate estimate,String status);
    Apply findByApplyIdx(Long applyIdx);
}
