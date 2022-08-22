package cn.anntek.springbootenvproperty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class WebController {
    @Autowired
    private Environment env;

    @GetMapping("/env")
    public String getEnv(@Value("${web.app.version}") String version){
        return "作者: "+env.getProperty("web.app.auth")+", 版本:"+version;
    }
}
