package kr.omen.pico.domain.dto.oauth;

public interface OauthUserInfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String getPhone();
}
