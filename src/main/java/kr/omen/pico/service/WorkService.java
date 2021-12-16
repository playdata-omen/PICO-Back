package kr.omen.pico.service;

import kr.omen.pico.dao.WorkRepository;
import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.domain.dto.WorkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;

    public ResponseDTO.WorkResponse insertWork(WorkDTO.Create dto){
        return null;
    }

}
