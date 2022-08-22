package cn.anntek.springbootjpamysqlstudentcrud.repository;

import cn.anntek.springbootjpamysqlstudentcrud.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaSpecificationExecutor<Student>, JpaRepository<Student, Long> {
}
