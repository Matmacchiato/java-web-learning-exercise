package cn.anntek.springbootjpasqlitestudentcrud.repository;

import cn.anntek.springbootjpasqlitestudentcrud.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
}
