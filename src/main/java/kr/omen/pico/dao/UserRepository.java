package kr.omen.pico.dao;

import kr.omen.pico.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserIdx(Long userIdx);
}
