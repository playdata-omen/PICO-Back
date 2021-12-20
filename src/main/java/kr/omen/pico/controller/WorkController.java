package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.domain.dto.WorkDTO;
import kr.omen.pico.service.WorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @PostMapping("work/add")
    public ResponseDTO.WorkResponse insertWork(@RequestBody WorkDTO.Create dto){
        ResponseDTO.WorkResponse response = workService.insertWork(dto);
        return response;
    }

    @PostMapping("/uploadWork")
    public Map<String, Object> addData(@RequestBody WorkDTO.Create data) throws IOException {
        System.out.println("-------------------------------요청 성공");
        System.out.println(data.toString());
        System.out.println(data);
        Map<String, Object> result = workService.uploadWork(data);
        return result;
    }

}
