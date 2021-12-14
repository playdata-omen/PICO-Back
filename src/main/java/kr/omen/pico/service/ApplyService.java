package kr.omen.pico.service;

import javassist.NotFoundException;
import kr.omen.pico.config.exception.Exception;
import kr.omen.pico.dao.ApplyRepository;
import kr.omen.pico.domain.Apply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;

    public Apply findOne(Long applyIdx) throws NotFoundException{
        Apply apply = null;
        System.out.println("서비스전");
            apply = applyRepository.findById(applyIdx).get();
        System.out.println(apply.getApplyIdx());
        return apply;
    }
}
