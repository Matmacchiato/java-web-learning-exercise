package cn.anntek.springbootuploadfile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class WebController {
    @GetMapping("")
    public String index(){
        return "index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public  String upload(@RequestParam("sid") String sid,
                          @RequestParam("name") String name,
                          @RequestParam("my-homework")MultipartFile file) {
        String message="文件不能为空";
        if(!file.isEmpty()){
            String fileName=sid+"-"+name+"-"+file.getOriginalFilename();
            try {
                Files.write(new File("uploaded-file/"+fileName).toPath(),file.getBytes());
                message="文件上传成功!";
            } catch (IOException e) {
                e.printStackTrace();
                message="文件上传失败!";
            }

        }
        return message;

    }
}