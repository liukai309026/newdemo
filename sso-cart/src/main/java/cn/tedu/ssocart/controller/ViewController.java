package cn.tedu.ssocart.controller;

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
@RequestMapping("view")
public class ViewController {

    @Autowired
    private RestTemplate restTemplate;

    private final String LOGIN_USER_ADDRESS = "login.shopcode.com:9000/login/info?token=";

    @GetMapping("index")
    public String toCart(@CookieValue(required = false,value = "TOKEN") Cookie cookie, HttpSession session){
        if(cookie != null){
            String token = cookie.getValue();
            Map result = restTemplate.getForObject(LOGIN_USER_ADDRESS+token, Map.class);
            session.setAttribute("loginUser",result);
        }
        return "index";
    }
}
