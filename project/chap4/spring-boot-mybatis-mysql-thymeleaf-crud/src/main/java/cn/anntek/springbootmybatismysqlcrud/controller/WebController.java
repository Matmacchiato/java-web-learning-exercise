package cn.anntek.springbootmybatismysqlcrud.controller;

import cn.anntek.springbootmybatismysqlcrud.model.Employee;
import cn.anntek.springbootmybatismysqlcrud.repository.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {
    @Autowired
    EmployeeMapper mapper;

    @GetMapping("")
    public String index(Model model){
        var employees =mapper.findAll();
        model.addAttribute("employees",employees);
        model.addAttribute("title","深圳移动互联公司");
        return "index";
    }

    @GetMapping("/add")
    public String add(){
        return "edit";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id,Model model){
        var employee=mapper.findById(id);
        if(employee==null){
            model.addAttribute("message","查找员工失败!");
            return "error";
        }else {
            model.addAttribute("employee",employee);
            model.addAttribute("id",id);
            return "edit";
        }
    }

    @PostMapping("/create")
    public String create(@RequestParam String name,
                         @RequestParam String email,
                         @RequestParam String address,
                         @RequestParam Double salary,
                         Model model){
        if(mapper.insert(new Employee(name,email,address,salary))>0){
            return "redirect:/";
        }else{
            model.addAttribute("message","添加员工出错!");
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateById(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String address,
                             @RequestParam double salary,
                             @PathVariable Long id,
                             Model model){
        var employee=new Employee(name,email,address,salary);
        if(mapper.updateById(employee,id)>0){
            return "redirect:/";
        }else{
            model.addAttribute("message","修改员工失败!");
            return "error";
        }

    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id,Model model){
        if(mapper.deleteById(id)>0){
            return "redirect:/";
        }else {
            model.addAttribute("message","删除员工失败!");
            return "error";
        }
    }
}
