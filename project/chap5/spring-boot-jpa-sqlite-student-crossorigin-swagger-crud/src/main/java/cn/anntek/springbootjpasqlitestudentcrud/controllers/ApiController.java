package cn.anntek.springbootjpasqlitestudentcrud.controllers;


import cn.anntek.springbootjpasqlitestudentcrud.models.ResponseResult;
import cn.anntek.springbootjpasqlitestudentcrud.models.ResponseStatus;
import cn.anntek.springbootjpasqlitestudentcrud.models.Student;
import cn.anntek.springbootjpasqlitestudentcrud.repository.StudentJpaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping("/students")
@CrossOrigin
public class ApiController {
    @Autowired
    private StudentJpaRepository studentJpaRepository;

    //获取学生列表
    @GetMapping("")
    @ApiOperation(value = "获取所有学生列表")
    public List<Student> findAll(){
        return studentJpaRepository.findAll();
    }

    //添加学生
    @PostMapping("")
    @ApiOperation(value = "添加学生")
    public Object createStudent(@RequestBody Student student){
        ResponseResult result = new ResponseResult();
        try{
            Student createdStudent = studentJpaRepository.save(student);
            result.setData(createdStudent);
            result.setSuccess(true);
            result.setStatus(ResponseStatus.CREATED);
        }catch (Exception e){
            result.setStatus(ResponseStatus.ERROR_400);
        }
        return result;
    }

    //修改
    @PutMapping("/{id}")
    @ApiOperation(value = "修改学生")
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
                result.setStatus(ResponseStatus.UPDATED);
            }catch (Exception e){
                result.setStatus(ResponseStatus.ERROR_400);
            }
        }
        return result;
    }

    //删除
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除学生")
    public Object deleteStudent(@PathVariable Long id){
        ResponseResult result = new ResponseResult();
        try{
            studentJpaRepository.deleteById(id);
            result.setSuccess(true);
            result.setStatus(ResponseStatus.DELETED);
        }catch (Exception e){
            result.setStatus(ResponseStatus.ERROR_400);
        }
        return result;
    }
}
