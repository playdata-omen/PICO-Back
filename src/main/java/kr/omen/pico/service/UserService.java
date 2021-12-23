package kr.omen.pico.service;

import javassist.NotFoundException;
import kr.omen.pico.config.SecurityUtil;
import kr.omen.pico.config.jwt.TokenProvider;
import kr.omen.pico.dao.PhotographerRepository;
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
    private final PhotographerRepository photographerRepository;
    private final TokenProvider tokenProvider;
    
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
                    .isRegister(user.getIsRegister())
                    .isPhotographer(user.getIsPhotographer())
                    .build();

            if (user.getIsRegister()) {
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

    // 토큰 유저 객체로 변경
    public User getUser() {
        return userRepository.findById(SecurityUtil.getCurrentUserIdx()).get();
    }

    // 유저 일반 회원가입(isRegister가 false일때) 및 회원정보 수정
    public UserDTO.LoginInfo userRegister(UserDTO.UserInfo data) {
        User user = userRepository.findById(data.getUserIdx()).get();
        UserDTO.LoginInfo result = null;

        if (user != null) {
            user.update(data);

            user = userRepository.save(user);

            result = UserDTO.LoginInfo.builder()
                    .userIdx(user.getUserIdx())
                    .name(user.getName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .nickName(user.getNickName())
                    .isRegister(user.getIsRegister())
                    .isPhotographer(user.getIsPhotographer())
                    .build();

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(user.getUserIdx(), null, AuthorityUtils.createAuthorityList("ROLE_USER"));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.createToken(authentication);
            result.setAccessToken(jwt);

        }
        return result;
    }

    // 채팅에서 쓰인거로 추정되며 채팅 수정시 삭제예정
    public User findOne(Long userIdx) throws NotFoundException{
        User user = userRepository.findByUserIdx(userIdx);
        return user;
    }

    public UserDTO.UserInfo photographerIdxGetUser(Long photographerIdx) {
        User user = userRepository.findById(photographerRepository.findById(photographerIdx).get().getUser().getUserIdx()).get();

        return UserDTO.UserInfo.builder()
                .userIdx(user.getUserIdx())
                .name(user.getName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .isPhotographer(user.getIsPhotographer())
                .build();
    }

    public UserDTO.UserInfo userIdxGetUser(Long userIdx) {
        User user = userRepository.findById(userIdx).get();

        return UserDTO.UserInfo.builder()
                .userIdx(user.getUserIdx())
                .name(user.getName())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .isPhotographer(user.getIsPhotographer())
                .build();
    }
}
