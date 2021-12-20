package kr.omen.pico.service;

import kr.omen.pico.domain.dto.oauth.KakaoUserInfo;
import kr.omen.pico.domain.dto.oauth.NaverUserInfo;
import kr.omen.pico.domain.dto.oauth.OauthUserInfo;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Social {

    public static OauthUserInfo kakao (String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("grant_type", "authorization_code");
        data.add("client_id", "6e429db2f6dd73b43889d2bdb5b47ad7");
        data.add("redirect_uri", "http://localhost:5501/testtest/login.html");
//        data.add("redirect_uri", "http://localhost:3000/oauth/callback/kakao");
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

        OauthUserInfo oauthUserInfo = new KakaoUserInfo(response.getBody());


        return oauthUserInfo;
    }

    public static OauthUserInfo naver (String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("grant_type", "authorization_code");
        data.add("client_id", "DfreIVmVCHEh9U5dI5zU");
        data.add("redirect_uri", "http://localhost:5501/testtest/login.html");
//        data.add("redirect_uri", "http://localhost:3000/oauth/callback/naver");
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

        OauthUserInfo oauthUserInfo = new NaverUserInfo(response.getBody());

        return oauthUserInfo;
    }

    public static OauthUserInfo google (String code) throws UnsupportedEncodingException {


        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        String code2 = URLDecoder.decode(code, "UTF-8");
        data.add("code", code2);
        data.add("client_id", "575105020669-jq0rvnltn9eedtg5il8q1mhtrs5m0qu7.apps.googleusercontent.com");
        data.add("client_secret", "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");
        data.add("redirect_uri", "http://localhost:5501/testtest/login.html");
        data.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> googleRequest =
                new HttpEntity<>(data, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<JSONObject> response = restTemplate.exchange(
                    "https://oauth2.googleapis.com/token",
                    HttpMethod.POST,
                    googleRequest,
                    JSONObject.class
            );
            System.out.println(response.getBody());
            System.out.println(response.getBody().get("id_token"));
            System.out.println("------------------------------------------");
            String idToken = (String) response.getBody().get("id_token");
            String[] splitToken = idToken.split("\\.");
            System.out.println(splitToken[1]);
            

//            OauthUserInfo oauthUserInfo = new GoogleUserInfo(response.getBody());
        } catch ( HttpStatusCodeException e ) {
            System.out.println(e.getResponseBodyAsString());
        }


        // 성공ver
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded");
//
//        // body
//        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
//        data.add("grant_type", "authorization_code");
//        data.add("client_id", "575105020669-jq0rvnltn9eedtg5il8q1mhtrs5m0qu7.apps.googleusercontent.com");
//        data.add("redirect_uri", "http://localhost:5500/testtest/login.html");
////        data.add("redirect_uri", "http://localhost:3000/oauth/callback/google");
//        data.add("code", code);
//        data.add("client_secret", "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");
//        System.out.println(1);
//
//        HttpEntity<MultiValueMap<String, String>> googleRequest =
//                new HttpEntity<>(headers);
//
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            String url = URLDecoder.decode("https://oauth2.googleapis.com/token?grant_type=authorization_code&client_id=575105020669-jq0rvnltn9eedtg5il8q1mhtrs5m0qu7.apps.googleusercontent.com&redirect_uri=http://localhost:5500/testtest/login.html&code="+code+"&client_secret=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", "UTF-8");
//
//            ResponseEntity<JSONObject> response = restTemplate.exchange(
//                    url,
//                    HttpMethod.POST,
//                    googleRequest,
//                    JSONObject.class
//            );
//            System.out.println(3);
//            System.out.println(response);
//            JSONObject responseBody = response.getBody();
//            System.out.println("me ---------------- " + response.getBody());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch ( HttpStatusCodeException e ) {
//            System.out.println(e.getResponseBodyAsString());
//        }


//        OauthUserInfo oauthUserInfo = new GoogleUserInfo(response.getBody());

//        return oauthUserInfo;

        return null;
    }
}
