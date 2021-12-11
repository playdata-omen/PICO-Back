package kr.omen.pico.domain.dto.oauth;

import java.util.Map;

public class GoogleUserInfo implements OauthUserInfo {
    private Map<String, Object> attributes; //oAuth2User.getAttributes()

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public String getProvider() {
        return "google";
    }
}
