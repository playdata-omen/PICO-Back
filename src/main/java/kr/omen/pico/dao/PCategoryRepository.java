package kr.omen.pico.dao;

import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.PCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PCategoryRepository extends JpaRepository<PCategory,Long> {
    List<PCategory> findByCategory(Category category);
}
