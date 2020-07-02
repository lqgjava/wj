package com.evan.wj.controller;

import com.evan.wj.pojo.User;
import com.evan.wj.result.Result;
import com.evan.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());

        if (user == null) {
            System.out.println("登录失败===================================");
            return new Result(400);
        } else {
            System.out.println("登录成功===================================");
            /*为了保存登录状态，我们可以把用户信息存在 Session 对象中（当用户在应用程序的 Web 页之间跳转时，存储在 Session 对象中的变量不会丢失），
            这样在访问别的页面时，可以通过判断是否存在用户变量来判断用户是否登录*/
            session.setAttribute("user",user);
            return new Result(200);
        }

    }
}
