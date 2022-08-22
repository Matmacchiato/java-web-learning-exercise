package cn.anntek.springbootrequestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/api")
public class WebController {
    @GetMapping("/request")
    public String getMapping(){
        return "Get请求,路径: /request";
    }

    @PostMapping("/request")
    public String postMapping(){
        return "Post请求,路径: /request";
    }
    @PutMapping("/request")
    public String putMapping(){
        return  "Put请求,路径: /request";
    }
    @PatchMapping("/request")
    public String patchMapping(){
        return "Patch请求, 路径: /request";
    }
    @DeleteMapping("/request")
    public String deleteMapp(){
        return "Delete请求,路径: /request";
    }
}
