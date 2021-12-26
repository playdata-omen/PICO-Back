package kr.omen.pico.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import kr.omen.pico.config.SecurityUtil;
import kr.omen.pico.dao.ApplyRepository;
import kr.omen.pico.dao.CategoryRepository;
import kr.omen.pico.dao.ChatRoomRepository;
import kr.omen.pico.dao.EstimateRepository;
import kr.omen.pico.dao.PCategoryRepository;
import kr.omen.pico.dao.PhotographerRepository;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.Apply;
import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.ChatRoom;
import kr.omen.pico.domain.Estimate;
import kr.omen.pico.domain.PCategory;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.ApplyDTO;
import kr.omen.pico.domain.dto.EstimateDTO;
import kr.omen.pico.domain.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstimateService {

    private final EstimateRepository estimateRepository;

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final PhotographerRepository photographerRepository;

    private final ApplyRepository applyRepository;

    private final PCategoryRepository pCategoryRepository;

    private final ChatRoomRepository chatRoomRepository;

    //글로벌 견적요청 API
    public ResponseDTO.EstimateResponse createGlobalEstimate(EstimateDTO.Create estimateDTO){

        User user = userRepository.findById(SecurityUtil.getCurrentUserIdx()).get();
        Category category = categoryRepository.findById(estimateDTO.getCategoryIdx()).get();
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
            if (i>=5) {
                break;
            }
            Photographer photographer = photographerRepository.findById(pc.getPhotographer().getPhotographerIdx()).get();
            if (photographer == null) {
                continue;
            }
            if(photographer.getActivityCity().equals(estimate.getCity()) && photographer.getActivityAddress().equals(estimate.getAddress())){
                plist.add(photographer);
                iter.remove();
                i++;
            }
        }

        iter=list1.iterator();
        System.out.println(iter.hasNext());
        //2순위 저장(카테고리/시 2가지 매칭 시)
        while(iter.hasNext()){
            PCategory pc = iter.next();
            if (i >= 5) {
                break;
            }
            Photographer photographer = photographerRepository.findById(pc.getPhotographer().getPhotographerIdx()).get();
            if (photographer == null) {
                continue;
            }
            if (photographer.getActivityCity().equals(estimate.getCity())) {
                plist.add(photographer);
                iter.remove();
                i++;
            }
        }

        iter=list1.iterator();
        //3순위 저장(카테고리가 일치하며 타지역 협의여부 true로 설정 한 나머지)
        while(iter.hasNext()){
            PCategory pc = iter.next();
            if (i >= 5) {
                break;
            }
            Photographer photographer = photographerRepository.findById(pc.getPhotographer().getPhotographerIdx()).get();
            if (photographer == null) {
                continue;
            }
            if (photographer.getIsOtherArea()) {
                plist.add(photographer);
                iter.remove();
                i++;
            }
        }

        for(Photographer photographer : plist){
            applyRepository.save(Apply.builder()
                    .estimate(estimate)
                    .status(1)
                    .photographer(photographer)
                    .isApplied(false)
                    .build());
        }

        return responsedto;
    }

    //작가 지정 견적요청 API
    public ResponseDTO.EstimateResponse createPickedEstimate(EstimateDTO.Create estimateDTO){

        Photographer photographer = photographerRepository.findById(estimateDTO.getPhotographerIdx()).get();
        Category category = categoryRepository.findById(estimateDTO.getCategoryIdx()).get();
        User user = userRepository.findById(estimateDTO.getUserIdx()).get();

        Estimate estimate = estimateRepository.save(estimateDTO.toEntity(user,category));
        ResponseDTO.EstimateResponse responseDTO = new ResponseDTO.EstimateResponse(estimate);

        applyRepository.save(Apply.builder()
                .status(2)
                .estimate(estimate)
                .photographer(photographer)
                .isApplied(false)
                .build());

        return responseDTO;
    }

    //유저가 요청한 견적 요청 list조회
    public List<ResponseDTO.SimpleCard> getUserAllEstimate(Long idx){
        User user = userRepository.findById(idx).get();
        List<Estimate> list = estimateRepository.findAllByUser(user);
        List<ResponseDTO.SimpleCard> list2 = new ArrayList<>();

        for(Estimate estimate : list){
            list2.add(new ResponseDTO.SimpleCard(estimate));
        }

        return list2;
    }

    // 견적요청 상세 list 조회
    // 해당하는 견적서 상세정보와 신청한 작가 list(Apply list)들 출력
    public ResponseDTO.EstimateDetailResponse getUserOneEstimate(Long estimateIdx){
        Estimate estimate = estimateRepository.findById(estimateIdx).get();
        List<Apply> applies = applyRepository.findAllByEstimate(estimate);
        List<ResponseDTO.SimplePhotographerCard> names = new ArrayList<>();
        //작가지정 견적서인 경우
        if(estimate.getStatus()==2){
            for(Apply apply : applies){
                if(apply.getIsApplied()) {
                    Photographer photographer = photographerRepository.findById(apply.getPhotographer().getPhotographerIdx()).get();
                    ChatRoom chatRoom = chatRoomRepository
                            .findByEstimateAndUserAndPhotographer(estimate,estimate.getUser(),photographer);
                    names.add(new ResponseDTO.SimplePhotographerCard(photographer,apply,chatRoom));
                }
            }
        }
        //글로벌 견적서인 경우
        else if(estimate.getStatus()==1){
            for (Apply apply : applies) {
                if (apply.getIsApplied()) {
                    Photographer photographer = photographerRepository.findById(apply.getPhotographer().getPhotographerIdx()).get();
                    ChatRoom chatRoom = chatRoomRepository
                            .findByEstimateAndUserAndPhotographer(estimate,estimate.getUser(),photographer);
                    names.add(new ResponseDTO.SimplePhotographerCard(photographer,apply,chatRoom));
                }
            }
        }
        //지원한작가가 선택받은 경우
        else if(estimate.getStatus()==3 || (estimate.getStatus()==4)){
            for(Apply apply : applies){
                if(apply.getIsApplied() && (apply.getStatus()==3 || apply.getStatus()==5 || apply.getStatus()==6)){
                    Photographer photographer = photographerRepository.findById(apply.getPhotographer().getPhotographerIdx()).get();
                    ChatRoom chatRoom = chatRoomRepository
                            .findByEstimateAndUserAndPhotographer(estimate,estimate.getUser(),photographer);
                    names.add(new ResponseDTO.SimplePhotographerCard(photographer,apply,chatRoom));
                }
            }
        }

        return  new ResponseDTO.EstimateDetailResponse(estimate,names);
    }


    public List<ResponseDTO.SimpleCard> getPhotographerAllEstimate(Long photographerIdx){
        Photographer photographer = photographerRepository.findById(photographerIdx).get();
        List<Apply> applies = applyRepository.findAllByPhotographer(photographer);
        List<ResponseDTO.SimpleCard> estimates = new ArrayList<>();
        for(Apply apply : applies){
            if(apply.getStatus()==1 || apply.getStatus()==2){
                estimates.add(
                        new ResponseDTO.SimpleCard(
                                estimateRepository.findById(apply.getEstimate().getEstimateIdx()).get()
                        )
                );
            }
        }
        return estimates;
    }

    public Boolean deleteMyEstimate(Long estimateIdx){
        Estimate estimate = estimateRepository.findById(estimateIdx).get();
        List<Apply> list = applyRepository.findAllByEstimate(estimate);
        Boolean cancel = false;
        try {
            for(Apply apply : list){
                apply.update(7);
                applyRepository.save(apply);
            }
            estimateRepository.delete(estimate);
            cancel = true;
        }catch(Exception e){

        }
        return cancel;
    }

    public ApplyDTO.Get confirmEstimate(Long estimateIdx, Long photographerIdx){
        Estimate estimate = estimateRepository.findById(estimateIdx).get();
        estimate.updateStatus(3);
        estimateRepository.save(estimate);
        Photographer photographer = photographerRepository.findById(photographerIdx).get();
        List<Apply> list = applyRepository.findAllByEstimate(estimate);
        ApplyDTO.Get result = null;
        try {
            for (Apply apply : list) {
                if (apply.getIsApplied() && apply.getPhotographer().getPhotographerIdx() != photographer.getPhotographerIdx()) {
                    apply.update(4);
                    applyRepository.save(apply);
                }
                if (apply.getIsApplied() && apply.getPhotographer().getPhotographerIdx() == photographer.getPhotographerIdx()) {
                    apply.update(3);
                    applyRepository.save(apply);

                    result = new ApplyDTO.Get(apply);
                }
                if(!apply.getIsApplied()){
                    apply.update(4);
                    applyRepository.save(apply);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public ApplyDTO.Get confirmOrder(Long estimateIdx,Long photographerIdx){
        ApplyDTO.Get result = null;
        Estimate estimate = estimateRepository.findById(estimateIdx).get();
        Photographer photographer = photographerRepository.findById(photographerIdx).get();

        Apply apply = applyRepository.findByPhotographerAndEstimate(photographer,estimate);
        try {
            if(apply.getStatus()==3 && estimate.getStatus()==3) {
                apply.update(5);
                applyRepository.save(apply);
                estimate.updateStatus(4);
                estimateRepository.save(estimate);

                result = new ApplyDTO.Get(apply);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
