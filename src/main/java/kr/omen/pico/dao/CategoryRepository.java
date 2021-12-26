package kr.omen.pico.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.omen.pico.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

}
