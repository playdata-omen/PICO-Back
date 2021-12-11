package kr.omen.pico.service;

import kr.omen.pico.domain.dto.UserDTO;
import kr.omen.pico.domain.dto.oauth.OauthUserInfo;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class UserService {

//    @Autowired
//    Social social;

    // naver or google or kakao
//    public UserDTO.Register userLogin(String code, String provider) throws Exception {
    public String userLogin(String code, String provider) throws Exception {

        OauthUserInfo oauthUserInfo = null;
        UserDTO.Register test = null;
        try {
            Class social = Class.forName("kr.omen.pico.service.Social");
            Method method = social.getDeclaredMethod(provider, String.class);
            oauthUserInfo = (OauthUserInfo) method.invoke(null, code);

            test = UserDTO.Register.builder()
                .id(oauthUserInfo.getProviderId() + "@" + oauthUserInfo.getProvider() + ".social")
                    .name(oauthUserInfo.getName())
                    .phone(oauthUserInfo.getPhone())
                    .email(oauthUserInfo.getEmail())
                    .provider(oauthUserInfo.getProvider())
                    .providerId(oauthUserInfo.getProviderId())
                    .build();

        } catch (Exception e) {

        }

        return test.toString();

    }
}


