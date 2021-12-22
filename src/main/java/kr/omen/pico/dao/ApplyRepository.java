package kr.omen.pico.dao;

import kr.omen.pico.service.domain.Apply;
import kr.omen.pico.service.domain.Estimate;
import kr.omen.pico.service.domain.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyRepository extends JpaRepository<Apply,Long> {
    List<Apply> findAllByEstimate(Estimate estimate);
    List<Apply> findAllByPhotographer(Photographer photographer);
    Apply findByPhotographerAndEstimate(Photographer photographer,Estimate estimate);
    Apply findByEstimateAndStatus(Estimate estimate,String status);
    Apply findByApplyIdx(Long applyIdx);
}
