package kr.omen.pico.service;

import javassist.NotFoundException;
import kr.omen.pico.config.SecurityUtil;
import kr.omen.pico.config.jwt.TokenProvider;
import kr.omen.pico.dao.UserRepository;
import kr.omen.pico.domain.User;
import kr.omen.pico.domain.dto.UserDTO;
import kr.omen.pico.domain.dto.oauth.OauthUserInfo;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    
    private final TokenProvider tokenProvider;
    
    // naver or google or kakao
    public UserDTO.Register userLogin(String code, String provider) throws Exception {

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

        User user = userRepository.findById(SecurityUtil.getCurrentUserIdx()).get();
        System.out.println(user);
        return user;
    }

    // 유저 일반 회원가입(isRegister가 false일때)
    public UserDTO.LoginInfo userRegister(UserDTO.UserInfo data) {
        User user = userRepository.findById(data.getUserIdx()).get();
        UserDTO.LoginInfo result = null;

        if (user != null) {
            user.setEmail(data.getEmail());
            user.setName(data.getName());
            user.setNickName(data.getNickName());
            user.setPhone(data.getPhone());
            user.setPhotographer(data.getIsPhotographer());
            user.setRegister(true);

            user = userRepository.save(user);

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
        return result;
    }

    public User findOne(Long userIdx) throws NotFoundException{
        User user = userRepository.findByUserIdx(userIdx);
        return user;
    }
}
