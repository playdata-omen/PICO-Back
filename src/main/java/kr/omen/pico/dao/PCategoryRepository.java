package kr.omen.pico.dao;

import kr.omen.pico.service.domain.Category;
import kr.omen.pico.service.domain.PCategory;
import kr.omen.pico.service.domain.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PCategoryRepository extends JpaRepository<PCategory,Long> {
    List<PCategory> findByCategory(Category category);
    List<PCategory> findByPhotographer(Photographer photographer);

    List<PCategory> findAllByCategory(Long categoryIdx);
}
