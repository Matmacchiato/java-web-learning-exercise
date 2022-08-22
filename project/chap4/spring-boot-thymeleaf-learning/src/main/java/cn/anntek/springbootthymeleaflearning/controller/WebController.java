package cn.anntek.springbootthymeleaflearning.controller;

import cn.anntek.springbootthymeleaflearning.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class WebController {
    @GetMapping("")
    public String index(Model model){
        var student1 = new Student("2001040143","郑豪",0,
                "image/1.png","20移动互联3-1班","软件学院","1998-03-17");
        var student2 = new Student("1801250112","郭秋洁",1,
                "image/2.png","20移动互联3-1班","电通学院","1998-03-18");
        var student3 = new Student("1801250113","郭秋桀",0,
                "image/3.png","20移动互联3-1班","计算机学院","1998-03-19");
        var list=new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        model.addAttribute("students",list);
        model.addAttribute("caption","学生花名册");
        return "student-info";
    }
}
