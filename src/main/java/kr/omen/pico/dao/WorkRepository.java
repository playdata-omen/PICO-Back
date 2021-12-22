package kr.omen.pico.dao;


import kr.omen.pico.service.domain.Photographer;
import kr.omen.pico.service.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<Work,Long> {
    List<Work> findByPhotographer(Photographer photographer);
}
