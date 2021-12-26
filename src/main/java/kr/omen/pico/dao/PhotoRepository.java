package kr.omen.pico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.omen.pico.domain.Photo;
import kr.omen.pico.domain.Work;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
    List<Photo> findByWork(Work work);
}
