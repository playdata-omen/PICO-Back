package kr.omen.pico.dao;

import kr.omen.pico.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUserId(String id);
    User findByUserIdx(Long userIdx);
}
