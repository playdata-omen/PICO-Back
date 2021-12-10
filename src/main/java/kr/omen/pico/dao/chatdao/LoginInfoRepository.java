package kr.omen.pico.dao.chatdao;

import kr.omen.pico.model.LoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfo,Long> {
    LoginInfo findLoginInfoByName (String name);
}
