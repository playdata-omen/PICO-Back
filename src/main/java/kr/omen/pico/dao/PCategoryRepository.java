package kr.omen.pico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.PCategory;
import kr.omen.pico.domain.Photographer;

public interface PCategoryRepository extends JpaRepository<PCategory,Long> {
    List<PCategory> findByCategory(Category category);
    List<PCategory> findByPhotographer(Photographer photographer);
//    List<PCategory> findAllByCategory(Catego categoryIdx);
}
