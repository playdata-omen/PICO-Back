
package kr.omen.pico.domain.dto.oauth;

import java.util.Map;

public class NaverUserInfo implements OauthUserInfo {
    private Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        Map<String, Object> temp = (Map<String, Object>) attributes.get("response");
        this.attributes = temp;
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("id");
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
        return (String) attributes.get("mobile");
    }

    @Override
    public String getProvider() {
        return "naver";
    }
}

