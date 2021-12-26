package kr.omen.pico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work,Long> {
    List<Work> findByPhotographer(Photographer photographer);
}
