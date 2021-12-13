package kr.omen.pico.dao;

import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface PhotographerRepository extends CrudRepository<Photographer,Long> {

    Photographer findByUser(User user);
}
