package kr.omen.pico.dao;

import kr.omen.pico.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserId(String id);
    User findByUserIdx(Long userIdx);
    List<User> findByIsPhotographerAndNameContainingOrNickNameContaining(Boolean isPhotographer, String name, String nickName);
}
