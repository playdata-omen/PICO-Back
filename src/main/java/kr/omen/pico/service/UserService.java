package kr.omen.pico.service;

import kr.omen.pico.config.SecurityUtil;
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
    public UserDTO.LoginInfo userLogin(String code, String provider) throws Exception {

        OauthUserInfo oauthUserInfo = null;
        UserDTO.Register userDTO = null;
        UserDTO.LoginInfo result = null;

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
                user = userRepository.save(userDTO.toEntity());
            }

            result = UserDTO.LoginInfo.builder()
                    .userIdx(user.getUserIdx())
                    .name(user.getName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .nickName(user.getNickName())
                    .isRegister(user.isRegister())
                    .isPhotographer(user.isPhotographer())
                    .build();

            if (user.isRegister()) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user.getUserIdx(), null, AuthorityUtils.createAuthorityList("ROLE_USER"));


                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = tokenProvider.createToken(authentication);
                result.setAccessToken(jwt);
            }


        } catch (Exception e) {

        }

        return result;

    }

    public User getUser() {
        System.out.println("Security util ----------- " + SecurityUtil.getCurrentUserIdx());
        User user = userRepository.findByUserId(SecurityUtil.getCurrentUserIdx());
        return user;
    }

    // 유저 일반 회원가입(isRegister가 false일때)
    public UserDTO.LoginInfo userRegister(UserDTO.UserInfo data) {
        System.out.println("UserService--------- " + data.toString());
        User user = userRepository.findById(data.getUserIdx()).get();
        System.out.println("user ----------- " + user.toString());
        UserDTO.LoginInfo result = null;

        if (user != null) {
            user.setEmail(data.getEmail());
            user.setName(data.getName());
            user.setNickName(data.getNickName());
            user.setPhone(data.getPhone());
            user.setPhotographer(data.isPhotographer());
            user.setRegister(true);

            user = userRepository.save(user);

            if (!user.isPhotographer()) {
                result = UserDTO.LoginInfo.builder()
                        .userIdx(user.getUserIdx())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .nickName(user.getNickName())
                        .isRegister(user.isRegister())
                        .isPhotographer(user.isPhotographer())
                        .build();

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user.getUserIdx(), null, AuthorityUtils.createAuthorityList("ROLE_USER"));


                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = tokenProvider.createToken(authentication);
                result.setAccessToken(jwt);
            }
        }
        return result;
    }
}


