package cn.anntek.springbootverificationcode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WebController {
    @GetMapping("/verification-image")
    public void getVerificationCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String verificationCode=VerificationCodeUtil.getVerificationCode();
        System.out.println(verificationCode);
        request.getSession().setAttribute("verification-code",verificationCode.toLowerCase());
        ImageIO.write(VerificationCodeUtil.getVerificationImage(verificationCode),"PNG",response.getOutputStream());
    }

    @PostMapping("/verify")
    @ResponseBody
    public Object verify(HttpServletRequest request, @RequestParam String code){
        return code.toLowerCase().equals(request.getSession().getAttribute("verification-code"));
    }

    @GetMapping("")
    public String index(){
        return "index";
    }
}
