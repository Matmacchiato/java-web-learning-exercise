package cn.anntek.springbootinterceptoruserlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {
    //首页
    @GetMapping("")
    public String index(){
        return "index";
    }

    //登录页
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String valid(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletRequest request, Model model){
        // 模拟验证过程
        if ("123456".equals(password)){
            request.getSession().setAttribute("user",username);
            model.addAttribute("username",username);
            return "index";
        }
        return "redirect:/";
    }

    //登出
    @PostMapping("/logout")
    public String valid(HttpServletRequest request){
        request.getSession().setAttribute("user",null);
        return "redirect:/";
    }
}
