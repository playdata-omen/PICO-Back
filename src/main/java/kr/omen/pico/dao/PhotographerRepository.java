package kr.omen.pico.dao;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotographerRepository extends JpaRepository<Photographer,Long> {
    Photographer findByUser(User user);
}
