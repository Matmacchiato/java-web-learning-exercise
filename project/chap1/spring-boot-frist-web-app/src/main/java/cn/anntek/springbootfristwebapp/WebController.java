package cn.anntek.springbootfristwebapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/")
public class WebController {
    @ResponseBody
    @GetMapping("")
    public String index(){
        return "<h2>大家好，我是来自移动互联3-1班的郑豪</h2>";
    }
}