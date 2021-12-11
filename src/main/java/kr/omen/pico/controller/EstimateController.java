package kr.omen.pico.controller;

import kr.omen.pico.domain.dto.EstimateDTO;
import kr.omen.pico.service.EstimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class EstimateController {

    @Autowired
    private EstimateService estimateService;

    //Oauth 토큰 및 세션 등에 User 객체가 저장되지 않은상태로 수동으로 사용자 idx/ 카테고리 idx 입력받는 방식으로
    //진행한 test 코드. 추후에 메소드가 완성되려면
    //1. 세션이나 토큰 등 어딘가에 저장되어있는 User 정보를 불러와서 User 객체 set
    //2. Category를 radiobutton 등으로 체크해서 String으로 받아오고 그에 해당하는 카테고리 idx 기준으로 category 객체 생성 후 set
    //3. 대충 이런방식으로 생성된 estimate를 기준으로 활동 시/구 가 일치하는 작가들(임시)에게 해당 견적서 정보 발송하는 방식으로 구축하면
    //될 것으로 생각됨.
    @PostMapping("/estimate/addGE")
    public EstimateDTO insertGlobalEstimate(HttpServletRequest req){

        EstimateDTO estimateDTO = new EstimateDTO();
        estimateDTO.setUser(Long.parseLong(req.getParameter("useridx")));
        System.out.println(estimateDTO.getUser());
        estimateDTO.setAddress((String) req.getParameter("address"));
        estimateDTO.setCity((String)req.getParameter("city"));
        estimateDTO.setContent((String)req.getParameter("content"));
        estimateDTO.setStartDate(LocalDate.parse(req.getParameter("startdate"), DateTimeFormatter.ISO_DATE));
        estimateDTO.setEndDate(LocalDate.parse(req.getParameter("enddate"), DateTimeFormatter.ISO_DATE));
        estimateDTO.setCategory(Long.parseLong(req.getParameter("category")));
        estimateDTO.setStatus("1");
        estimateService.createGlobalEstimate(estimateDTO);


        return estimateDTO;
    }

    @PostMapping("/estimate/addPE")
    public EstimateDTO insertPickedEstimate(HttpServletRequest req){

        EstimateDTO estimateDTO = new EstimateDTO();
        long pidx = Long.parseLong(req.getParameter("photographer"));
        estimateDTO.setUser(Long.parseLong(req.getParameter("useridx2")));
        System.out.println(estimateDTO.getUser());
        estimateDTO.setAddress((String) req.getParameter("address2"));
        estimateDTO.setCity((String)req.getParameter("city2"));
        estimateDTO.setContent((String)req.getParameter("content2"));
        estimateDTO.setStartDate(LocalDate.parse(req.getParameter("startdate2"), DateTimeFormatter.ISO_DATE));
        estimateDTO.setEndDate(LocalDate.parse(req.getParameter("enddate2"), DateTimeFormatter.ISO_DATE));
        estimateDTO.setCategory(Long.parseLong(req.getParameter("category2")));
        estimateDTO.setStatus("2");
        estimateService.createPickedEstimate(estimateDTO,pidx);

        return estimateDTO;
    }
    @CrossOrigin
    @GetMapping("/test")
    public String teetResponse() {
        System.out.println("요청");
        return "호출 성공";
    }
}
