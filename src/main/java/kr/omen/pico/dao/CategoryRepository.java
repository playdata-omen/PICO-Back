package kr.omen.pico.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.omen.pico.domain.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
