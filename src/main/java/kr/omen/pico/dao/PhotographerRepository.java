package kr.omen.pico.dao;

import kr.omen.pico.domain.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotographerRepository extends JpaRepository<Photographer,Long> {

}
