package cn.tedu.ssomain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/view")
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    private final String LOGIN_INFO_ADDRESS = "http://login.shopcode.com:9010/login/info?token=";

    @GetMapping("/index")
    public  String toIndex(@CookieValue(required = false,value = "TOKEN") Cookie cookie, HttpSession session){
        if(cookie != null){
            String token = cookie.getValue();
            Map result = restTemplate.getForObject(LOGIN_INFO_ADDRESS+token, Map.class);
            session.setAttribute("loginUser",result);
        }
        return "index";
    }
}
