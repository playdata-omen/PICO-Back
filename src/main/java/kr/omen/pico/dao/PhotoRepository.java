package kr.omen.pico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.omen.pico.domain.Photo;
import kr.omen.pico.domain.Work;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
    List<Photo> findByWork(Work work);
}
