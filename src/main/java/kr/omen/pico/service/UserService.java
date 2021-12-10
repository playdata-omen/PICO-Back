package kr.omen.pico.service;

import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class UserService {

//    @Autowired
//    Social social;

    // naver or google or kakao
    public JSONObject userLogin(String code, String provider) throws Exception {

        JSONObject response = null;
        System.out.println(provider); // kakao

        try {
            Class social = Class.forName("kr.omen.pico.service.Social");
            Method method = social.getDeclaredMethod(provider, String.class);
            response = (JSONObject) method.invoke(null, code);
            System.out.println("----------- " + response);
        } catch (Exception e) {

        }

        return test;

    }
}

