package kr.omen.pico.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.omen.pico.domain.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserId(String id);
    User findByUserIdx(Long userIdx);
    List<User> findByIsPhotographerAndNameContainingOrNickNameContaining(Boolean isPhotographer, String name, String nickName);
}
