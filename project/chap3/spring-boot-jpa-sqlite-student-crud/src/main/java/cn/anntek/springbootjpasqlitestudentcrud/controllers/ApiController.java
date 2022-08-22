package cn.anntek.springbootjpasqlitestudentcrud.controllers;


import cn.anntek.springbootjpasqlitestudentcrud.models.ResponseResult;
import cn.anntek.springbootjpasqlitestudentcrud.models.Student;
import cn.anntek.springbootjpasqlitestudentcrud.repository.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping("/students")
public class ApiController {
    @Autowired
    private StudentJpaRepository studentJpaRepository;

    @GetMapping("")
    public List<Student> findAll(){
        return studentJpaRepository.findAll();
    }

    @PostMapping("")
    public Object createStudent(@RequestBody Student student){
        ResponseResult result = new ResponseResult();
        try{
            Student createdStudent = studentJpaRepository.save(student);
            result.setData(createdStudent);
            result.setSuccess(true);
        }catch (Exception e){
            result.setMessage("创建记录失败");
        }
        return result;
    }
    @PutMapping("/{id}")
    public Object updateStudent(@PathVariable Long id,
                                @RequestBody Student student){
        ResponseResult result = new ResponseResult();
        Optional<Student> stu = studentJpaRepository.findById(id);
        if (stu!=null){
            try{
                stu.get().setGender(student.getGender());
                stu.get().setName(student.getName());
                stu.get().setSid(student.getSid());
                result.setData(studentJpaRepository.save(stu.get()));
                result.setSuccess(true);
            }catch (Exception e){
                result.setMessage("修改记录失败");
            }
        }
        return result;
    }
    @DeleteMapping("/{id}")
    public Object deleteStudent(@PathVariable Long id){
        ResponseResult result = new ResponseResult();
        try{
            studentJpaRepository.deleteById(id);
            result.setSuccess(true);
        }catch (Exception e){
            result.setMessage("删除失败");
        }
        return result;
    }
}
