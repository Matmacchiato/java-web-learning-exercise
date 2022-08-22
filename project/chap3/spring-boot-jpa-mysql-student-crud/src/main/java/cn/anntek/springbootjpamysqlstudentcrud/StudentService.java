package cn.anntek.springbootjpamysqlstudentcrud;

import cn.anntek.springbootjpamysqlstudentcrud.repository.StudentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService {
    @Autowired
    private StudentJpaRepository studentJpaRepository;
    @Transactional
    //修改
    public int updateStudentById(Long id, String name){
        return studentJpaRepository.updateStudentNameByIdHQL(id, name);
    }
    //删除
    @Transactional
    public int deleteStudentById(Long id){
        return studentJpaRepository.deleteStudentByIdHQL(id);
    }
}
