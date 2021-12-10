package kr.omen.pico.service;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class Social {

//    private static RestTemplate restTemplate = new RestTemplate();

    public static JSONObject kakao (String code) {
        System.out.println("kakao start----------");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("grant_type", "authorization_code");
        data.add("client_id", "6e429db2f6dd73b43889d2bdb5b47ad7");
        data.add("redirect_uri", "http://localhost:5500/testtest/login.html");
        data.add("code", code);
        data.add("client_secret", "LBBCcs9hdryi9wZrOm8z1fvV6POuST2E");

        HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<>(data, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONObject> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoRequest,
                JSONObject.class
        );

        JSONObject responseBody = response.getBody();

        System.out.println(response);
        System.out.println(responseBody);


        headers.clear();
        data.clear();
        headers.add("Authorization", "Bearer "+responseBody.get("access_token"));
        kakaoRequest = new HttpEntity<>(data, headers);
        response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoRequest,
                JSONObject.class
        );

        System.out.println("kakao me : " + response);
//        UserDTO.Register().
        return responseBody;
    }

    public static JSONObject naver (String code) {
        System.out.println("naver start");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("grant_type", "authorization_code");
        data.add("client_id", "DfreIVmVCHEh9U5dI5zU");
        data.add("redirect_uri", "http://localhost:5500/testtest/login.html");
        data.add("code", code);
        data.add("client_secret", "ULP15nQ8nr");

        HttpEntity<MultiValueMap<String, String>> naverRequest =
                new HttpEntity<>(data, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JSONObject> response = restTemplate.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                naverRequest,
                JSONObject.class
        );

        JSONObject responseBody = response.getBody();
        System.out.println(response);
        System.out.println(responseBody);

        headers.clear();
        data.clear();
        headers.add("Authorization", "Bearer "+responseBody.get("access_token"));
        naverRequest = new HttpEntity<>(data, headers);
        response = restTemplate.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST,
                naverRequest,
                JSONObject.class
        );

        System.out.println("naver me : " + response);
        return responseBody;
    }

    public JSONObject google (String code) {
        return null;
    }
}
