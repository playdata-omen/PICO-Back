package kr.omen.pico.service.domain.dto.oauth;

public interface OauthUserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String getPhone();
}
