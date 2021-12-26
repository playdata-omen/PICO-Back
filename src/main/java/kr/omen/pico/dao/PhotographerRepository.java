package kr.omen.pico.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer,Long> {
    Photographer findByUser(User user);

}
