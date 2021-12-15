package kr.omen.pico.service;

import kr.omen.pico.dao.*;
import kr.omen.pico.domain.*;
import kr.omen.pico.domain.dto.EstimateDTO;
import kr.omen.pico.domain.dto.ResponseDTO;
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
    public ResponseDTO.EstimateResponse createGlobalEstimate(EstimateDTO.Create estimateDTO){

        User user = userRepository.findById(estimateDTO.getUser()).get();
        Category category = categoryRepository.findById(estimateDTO.getCategory()).get();
        Estimate estimate = estimateRepository.save(estimateDTO.toEntity(user,category));
        ResponseDTO.EstimateResponse responsedto = new ResponseDTO.EstimateResponse(estimate);
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
            apply.setIsApplied(false);
            applyRepository.save(apply);
        }

        return responsedto;
    }

    //작가 지정 견적요청 API
    public ResponseDTO.EstimateResponse createPickedEstimate(EstimateDTO.Create estimateDTO){

        Photographer photographer = photographerRepository.findById(estimateDTO.getPidx()).get();
        Category category = categoryRepository.findById(estimateDTO.getCategory()).get();
        User user = userRepository.findById(estimateDTO.getUser()).get();

        Estimate estimate = estimateRepository.save(estimateDTO.toEntity(user,category));
        ResponseDTO.EstimateResponse responsedto = new ResponseDTO.EstimateResponse(estimate);

        Apply apply = new Apply();
        apply.setStatus("2");
        apply.setEstimate(estimate);
        apply.setPhotographer(photographer);
        apply.setIsApplied(false);
        applyRepository.save(apply);

        return responsedto;
    }

    //유저가 요청한 견적 요청 list조회
    public List<ResponseDTO.SimpleCard> getUserAllEstimate(Long idx){
        User user = userRepository.findById(idx).get();
        List<Estimate> list = estimateRepository.findAllByUser(user);
        List<ResponseDTO.SimpleCard> list2 = new ArrayList<>();

        for(Estimate estimate : list){
            list2.add(new ResponseDTO.SimpleCard(estimate));
        }

        System.out.println("--- " + list2);
        return list2;
    }

    //견적요청 상세 list 조회
    //해당하는 견적서 상세정보와 신청한 작가 list(Apply list)들 출력
    public ResponseDTO.DetailResponse getUserOneEstimate(Long estimateId){
        Estimate estimate = estimateRepository.findById(estimateId).get();
        List<Apply> applies = applyRepository.findAllByEstimate(estimate);
        List<ResponseDTO.SimplePhotographerCard> names = new ArrayList<>();
        //작가지정 견적서인 경우
        if(estimate.getStatus().equals("2")){
            for(Apply apply : applies){
                if(apply.getIsApplied()) {
                    Photographer photographer = photographerRepository.findById(apply.getPhotographer().getPhotographerIdx()).get();
                    List<PCategory> pCategories = pCategoryRepository.findByPhotographer(photographer);
                    List<Long> nums = new ArrayList<>();
                    for(int i=0;i<pCategories.size();i++){
                        nums.add(pCategories.get(i).getCategory().getCategoryIdx());
                    }
                    names.add(new ResponseDTO.SimplePhotographerCard(photographer,apply,nums));
                }
            }
        }
        //글로벌 견적서인 경우
        else if(estimate.getStatus().equals("1")){
            for (Apply apply : applies) {
                if (apply.getIsApplied()) {
                    Photographer photographer = photographerRepository.findById(apply.getPhotographer().getPhotographerIdx()).get();
                    List<PCategory> pCategories = pCategoryRepository.findByPhotographer(photographer);
                    List<Long> nums = new ArrayList<>();
                    for(int i=0;i<pCategories.size();i++){
                        nums.add(pCategories.get(i).getCategory().getCategoryIdx());
                    }
                    names.add(new ResponseDTO.SimplePhotographerCard(photographer,apply,nums));
                }
            }
        }

        return  new ResponseDTO.DetailResponse(estimate,names);
    }


    public List<ResponseDTO.SimpleCard> getPhotographerAllEstimate(Long pId){
        Photographer photographer = photographerRepository.findById(pId).get();
        List<Apply> applies = applyRepository.findAllByPhotographer(photographer);
        List<ResponseDTO.SimpleCard> estimates = new ArrayList<>();
        for(Apply apply : applies){
            if(apply.getStatus().equals("1") || apply.getStatus().equals("2")){
                estimates.add(
                        new ResponseDTO.SimpleCard(
                                estimateRepository.findById(apply.getEstimate().getEstimateIdx()).get()
                        )
                );
            }
        }
        return estimates;
    }

    public boolean deleteMyEstimate(Long estimateId){
        Estimate estimate = estimateRepository.findById(estimateId).get();
        List<Apply> list = applyRepository.findAllByEstimate(estimate);
        boolean cancel = false;
        try {
            for(Apply apply : list){
                apply.setStatus("6");
                applyRepository.save(apply);
            }
            estimateRepository.delete(estimate);
            cancel = true;
        }catch(Exception e){

        }
        return cancel;
    }

}
