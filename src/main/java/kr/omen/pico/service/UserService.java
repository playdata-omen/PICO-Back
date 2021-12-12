package kr.omen.pico.service;

import kr.omen.pico.config.jwt.TokenProvider;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.UserDTO;
import kr.omen.pico.domain.dto.oauth.OauthUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;

    // naver or google or kakao
    public UserDTO.Info userLogin(String code, String provider) throws Exception {

        OauthUserInfo oauthUserInfo = null;
        UserDTO.Register userDTO = null;
        UserDTO.Info result = null;

        try {
            Class social = Class.forName("kr.omen.pico.service.Social");
            Method method = social.getDeclaredMethod(provider, String.class);
            oauthUserInfo = (OauthUserInfo) method.invoke(null, code);

            userDTO = UserDTO.Register.builder()
                    .userId(oauthUserInfo.getProviderId() + "@" + oauthUserInfo.getProvider() + ".social")
                    .name(oauthUserInfo.getName())
                    .phone(oauthUserInfo.getPhone())
                    .email(oauthUserInfo.getEmail())
                    .provider(oauthUserInfo.getProvider())
                    .providerId(oauthUserInfo.getProviderId())
                    .build();

            User user = userRepository.findByUserId(userDTO.getUserId());

            if (Objects.isNull(user)) {
                System.out.println("isnull");
                System.out.println(userDTO.toString());
                user = userRepository.save(userDTO.toEntity());
            }

            result = UserDTO.Info.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .nickName(user.getNickName())
                    .isRegister(user.isRegister())
                    .isPhotographer(user.isPhotographer())
                    .build();

            System.out.println(1);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user.getUserId(), null, AuthorityUtils.createAuthorityList("ROLE_USER"));

            System.out.println(2);


            System.out.println(3);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(4);
            System.out.println("service ---- "+authentication);
            String jwt = tokenProvider.createToken(authentication);

            System.out.println(5);
            System.out.println(jwt);
            System.out.println(6);

        } catch (Exception e) {

        }

        return result;

    }

    public void getUser(String userId) {
        User user = userRepository.findByUserId(userId);
        System.out.println(user);
    }
}


