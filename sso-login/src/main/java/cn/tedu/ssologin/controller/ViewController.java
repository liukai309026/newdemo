package cn.tedu.ssologin.controller;

import cn.tedu.ssologin.entity.User;
import cn.tedu.ssologin.utils.LoginCacheUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/view")
public class ViewController {
    @GetMapping("/login")
    public String toLogin(@RequestParam(required = false,defaultValue = "") String target, HttpSession session,
                          @CookieValue(required = false,value = "TOKEN") Cookie cookie){
        if(StringUtils.isEmpty(target)){
            target="http://www.shopcode.com:9000";
        }
        //如果是已经登录的用户再次访问登录系统时，就要重定向
        if(cookie != null){
            String value = cookie.getValue();
            User user = LoginCacheUtil.loginUser.get(value);
            if(user != null){
                return "redirect:" + target;
            }
        }

        //TODO 地址合法校验
        //重定向地址
        session.setAttribute("target",target);
        return "login";
    }
}
