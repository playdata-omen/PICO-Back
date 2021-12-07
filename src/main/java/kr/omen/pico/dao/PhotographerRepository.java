package kr.omen.pico.dao;

import kr.omen.pico.domain.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotographerRepository extends JpaRepository<Photographer,Long> {
    List<Photographer> findByCity(String City);
}
