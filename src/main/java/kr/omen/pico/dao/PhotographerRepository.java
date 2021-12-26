package kr.omen.pico.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;

public interface PhotographerRepository extends JpaRepository<Photographer,Long> {
    Photographer findByUser(User user);

}
