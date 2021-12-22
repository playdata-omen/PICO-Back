package kr.omen.pico.dao;

import kr.omen.pico.service.domain.Photo;
import kr.omen.pico.service.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
    List<Photo> findByWork(Work work);
}
