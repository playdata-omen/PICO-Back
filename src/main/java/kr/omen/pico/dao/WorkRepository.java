package kr.omen.pico.dao;


import kr.omen.pico.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work,Long> {

}
