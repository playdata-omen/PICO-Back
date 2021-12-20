package kr.omen.pico.dao;


import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work,Long> {
    List<Work> findByPhotographer(Photographer photographer);
}
