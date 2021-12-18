package kr.omen.pico.service;

import kr.omen.pico.dao.CategoryRepository;
import kr.omen.pico.dao.PhotoRepository;
import kr.omen.pico.dao.PhotographerRepository;
import kr.omen.pico.dao.WorkRepository;
import kr.omen.pico.domain.Category;
import kr.omen.pico.domain.Photo;
import kr.omen.pico.domain.Photographer;
import kr.omen.pico.domain.Work;
import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.domain.dto.WorkDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;

    private final PhotographerRepository photographerRepository;

    private final CategoryRepository categoryRepository;

    private final PhotoRepository photoRepository;

    public ResponseDTO.WorkResponse insertWork(WorkDTO.Create dto){
        return null;
    }

    public Map<String, Object> uploadWork(WorkDTO.Create data) throws IOException{
        Map<String,Object> result = new HashMap<>();
        List<String> fileBase64 = data.getImages();
        List<String> type = new ArrayList<>();
        List<String> base64 = new ArrayList<>();
        for(String file : fileBase64){
            String[] base = file.split(",");
            int a = base[0].indexOf("/")+1;
            int b = base[0].indexOf(";");
            System.out.println(base[0].substring(a,b));
            type.add(base[0].substring(a,b));
            base64.add(base[1]);
        }

        // 파일이 업로드되지 않았거나 사이즈가 큰 경우를 체크합니다.
        // 사이즈는 일반 바이트에서 1.33을 곱하면 BASE64 사이즈가 대략 나옵니다.
        if(fileBase64 == null || fileBase64.equals("")) {
            result.put("isFileInserted", false);
            result.put("uploadStatus", "FileIsNull");
            return result;
        } else if(fileBase64.get(0).length() > 400000) {
            result.put("isFileInserted", false);
            result.put("uploadStatus", "FileIsTooBig");
            return result;
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        ZonedDateTime current = ZonedDateTime.now();
        // 저장할 파일 경로를 지정합니다.
        String path = "pico/src/main/resources/static/images/" + current.format(format);
        File file = new File(path);
        if(!file.exists()) {
            file.mkdir();
        }

        Photographer photographer = photographerRepository.findById(data.getPhotographerIdx()).get();
        Category category = categoryRepository.findById(data.getCategoryIdx()).get();

        Work work = workRepository.save(Work.builder()
                .content(data.getContent())
                .title(data.getTitle())
                .category(category)
                .photographer(photographer)
                .build());

        FileOutputStream fileOutputStream = null;
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            for(int i=0;i<base64.size();i++) {

                byte[] decodedBytes = decoder.decode(base64.get(i));
                String fileName = Long.toString(System.nanoTime()) + "."+ type.get(i); // 파일네임은 서버에서 결정하거나 JSON에서 받아옵니다.
                file = new File(path + "/" + fileName);
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(decodedBytes);
                Long bytes = file.length();
                photoRepository.save(
                        Photo.builder()
                                .fileSize(Long.toString(bytes))
                                .storedFilePath(path + "/" + fileName)
                                .title(work.getTitle())
                                .work(work)
                                .build());
                if(i==0){
                    work.updateThumbnail(path+"/"+fileName);
                }
            }
            fileOutputStream.close();
            result.put("isFileInserted", true);
            result.put("uploadStatus", "AllSuccess");
        } catch(IOException e) {
            System.err.println(e);
            result.put("uploadStatus", "FileIsNotUploaded");
            result.put("isTTSInserted", false);
        }
        return result;
    }

}
