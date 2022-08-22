package cn.anntek.springbootjpamysqlstudentcrud.repository;

import cn.anntek.springbootjpamysqlstudentcrud.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {
    // 简单查询方法
    List<Student> findByNameAndGender(String name, int gender);
    List<Student> findStudentByNameContaining(String name);
    List<Student> getStudentByNameContainingOrderByNameDesc(String name);
    List<Student> getStudentByNameContainingOrderByNameAsc(String name);
    // 使用@Query自定义SQL查询
    @Query(value = "select * from students order by name desc", nativeQuery = true)
    List<Student> queryStudentsOrderByNameDescSQL();
    @Query("select s from Student s order by s.name asc")
    List<Student> queryStudentsOrderByNameAscHQL();
    // 顺序参数
    @Query(value = "select * from students where name like %?1%", nativeQuery = true)
    List<Student> queryStudentsByNameContainingSQL(String name);
    @Query(value = "select s from Student s where s.name like %?1%")
    List<Student> queryStudentsByNameContainingHQL(String name);
    // 命名参数
    @Query(value = "select * from students where name like %:name%", nativeQuery = true)
    List<Student> queryStudentsByNameContainingSQL_(String name);
    @Query(value = "select s from Student s where s.name like %:name%")
    List<Student> queryStudentsByNameContainingHQL_(String name);

    // 修改记录
    @Modifying
    @Query("update Student s set s.name=:name where s.id=:id")
    int updateStudentNameByIdHQL(@Param("id") Long studentId,
                                 @Param("name") String studentName);
    // 删除记录
    @Modifying
    @Query("delete from Student s where s.id=:id")
    int deleteStudentByIdHQL(Long id);
    //排序和限制
    //取第一条
    Student findFirstByOrderByNameAsc();
    //查所有记录按姓名降序前2条
    List<Student> findFirst2ByOrderByNameDesc();
    //按姓名模糊查询降序排列前2名
    List<Student> findFirst2ByNameContainsOrderByNameDesc(String name);
    //按姓名模糊查找，查找结果进行分页
    Page<Student> findFirst2ByNameContains(String name, Pageable pageable);
}
