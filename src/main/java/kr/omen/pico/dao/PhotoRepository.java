package kr.omen.pico.dao;

import kr.omen.pico.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo,Long> {

}
