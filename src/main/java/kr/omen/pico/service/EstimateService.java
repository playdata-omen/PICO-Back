package kr.omen.pico.service;

import kr.omen.pico.dao.*;
import kr.omen.pico.domain.*;
import kr.omen.pico.domain.dto.EstimateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EstimateService {

    @Autowired
    private EstimateRepository estimateRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PhotographerRepository photographerRepository;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private PCategoryRepository pCategoryRepository;

    //글로벌 견적요청 API
    //여기서 현재는 수동으로 입력받는 useridx는 추후에 sns 시큐리티 적용되면,
    // principaldetail인가? 거기서 getUser 형식으로 가져와서 사용 할 것으로 예상됨.
    public EstimateDTO createGlobalEstimate(EstimateDTO estimateDTO){

        Estimate estimate = new Estimate();
        User user = userRepository.findById(estimateDTO.getUser()).get();
        Category category = categoryRepository.findById(estimateDTO.getCategory()).get();

        estimate.setAddress(estimateDTO.getAddress());
        estimate.setCity(estimateDTO.getCity());
        estimate.setCategory(category);
        estimate.setUser(user);
        estimate.setStartDate(estimateDTO.getStartDate());
        estimate.setEndDate(estimateDTO.getEndDate());
        estimate.setContent(estimateDTO.getContent());
        estimate.setStatus(estimateDTO.getStatus());
        estimateRepository.save(estimate);

        //글로벌 견적 요청 후 저장 시, 해당 견적서에 설정된 City가 주 활동지역인 작가들의 list 뽑아낸 후
        //해당 작가들은 강제로 Status가 '1'인 상태로 해당 견적서에 지원하게 됨.(Status 1 == 글로벌 견적요청 할당됨)
        //1. findByCategoryidx -> 입력받은 견적서 category로 pcategory list 출력
        //2. 이중에서 City와 Address가 일치하며 ohter area == false 인 작가들의 리스트를 1순위로 리스트에 저장.
        //3. 여기서 걸리지않은 작가들 중 City만 일치하며 ohter area == false 인 작가들의 리스트를 2순위로 리스트에 저장.
        //4. 여기서도 걸리지 않은 작가들 중 ohter area == false 인 작가들의 리스트를 3순위로 리스트에 저장.
        //5  여기까지 했을때도 20개가 채워지지 않는다면, other area == true 인 작가들 중 아무나로 나머지 갯수 채움.
        List<PCategory> list1 = pCategoryRepository.findByCategory(estimate.getCategory());
        Iterator<PCategory> iter = list1.iterator();
        int i=0;
        List<Photographer> plist = new ArrayList<>();
        //1순위 저장(카테고리/시/구/ 3가지 매칭 시)
        while(iter.hasNext()){
            System.out.println("1순위 진입?");
            PCategory pc = iter.next();
            if(i>=5)
                break;
            Photographer photographer = photographerRepository.findById(pc.getPhotographer().getPhotographerIdx()).get();
            if(photographer == null)
                continue;
            if(photographer.getActivityCity().equals(estimate.getCity()) && photographer.getActivityAddress().equals(estimate.getAddress())){
                plist.add(photographer);
                iter.remove();
                i++;
                System.out.println("1순위");
            }
        }
        iter=list1.iterator();
        //2순위 저장(카테고리/시 2가지 매칭 시)
            while(iter.hasNext()){
                System.out.println("2순위 진입?");
                PCategory pc = iter.next();
                if (i >= 5)
                    break;
                Photographer photographer = photographerRepository.findById(pc.getPhotographer().getPhotographerIdx()).get();
                if (photographer == null)
                    continue;
                if (photographer.getActivityCity().equals(estimate.getCity())) {
                    plist.add(photographer);
                    iter.remove();
                    i++;
                    System.out.println("2순위");
                }
            }
        iter=list1.iterator();
        //3순위 저장(카테고리가 일치하며 타지역 협의여부 true로 설정 한 나머지)
            while(iter.hasNext()){
                System.out.println("3순위 진입?");
                PCategory pc = iter.next();
                if (i >= 5)
                    break;
                Photographer photographer = photographerRepository.findById(pc.getPhotographer().getPhotographerIdx()).get();
                if (photographer == null)
                    continue;
                if (photographer.isOtherArea()) {
                    plist.add(photographer);
                    iter.remove();
                    i++;
                    System.out.println("3순위");
                }
            }

        for(Photographer photographer : plist){
            Apply apply = new Apply();
            apply.setEstimate(estimate);
            apply.setStatus("1");
            apply.setPhotographer(photographer);
            applyRepository.save(apply);
        }

        return new EstimateDTO(estimate);
    }

    //작가 지정 견적요청 API
    public EstimateDTO createPickedEstimate(EstimateDTO estimateDTO,long photographerIdx){

        Photographer photographer = photographerRepository.findById(photographerIdx).get();
        Category category = categoryRepository.findById(estimateDTO.getCategory()).get();
        User user = userRepository.findById(estimateDTO.getUser()).get();

        Estimate estimate = new Estimate();
        estimate.setAddress(estimateDTO.getAddress());
        estimate.setCity(estimateDTO.getCity());
        estimate.setCategory(category);
        estimate.setUser(user);
        estimate.setStartDate(estimateDTO.getStartDate());
        estimate.setEndDate(estimateDTO.getEndDate());
        estimate.setContent(estimateDTO.getContent());
        estimate.setStatus(estimateDTO.getStatus());
        estimateRepository.save(estimate);

        Apply apply = new Apply();
        apply.setStatus("2");
        apply.setEstimate(estimate);
        apply.setPhotographer(photographer);
        applyRepository.save(apply);

        return new EstimateDTO(estimate);
    }
}
