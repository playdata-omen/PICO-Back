package kr.omen.pico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Work;

public interface WorkRepository extends JpaRepository<Work,Long> {
    List<Work> findByPhotographer(Photographer photographer);
}
