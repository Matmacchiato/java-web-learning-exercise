package cn.anntek.springbootjpamysqlstudentcrud;

import cn.anntek.springbootjpamysqlstudentcrud.models.Student;
import cn.anntek.springbootjpamysqlstudentcrud.repository.StudentJpaRepository;
import cn.anntek.springbootjpamysqlstudentcrud.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@SpringBootTest
public class JpaTest {
    @Autowired
    private StudentJpaRepository studentJpaRepository;

    private void printStudents(List<Student> students){
        for (var student: students){
            System.out.println(student);
        }
    }

    @Test
    //测试方法
    public void testDefaultMethods(){
        studentJpaRepository.save(new Student("1801","guoqiujie",0));
        studentJpaRepository.save(new Student("1802","wqiujie",1));
        studentJpaRepository.save(new Student("1803","zqiujie",1));
        studentJpaRepository.save(new Student("1804","lqiujie",0));
        List<Student> students=studentJpaRepository.findAll();
        for (var student: students){
            System.out.println(student);
        }
    }
    @Test
    //测试自定义方法
    public void testCustomMethods(){

        List<Student>students=studentJpaRepository.findByNameAndGender("guoqiujie",0);
        printStudents(students);
        students=studentJpaRepository.findByNameAndGender("guoqiujie",1);
        printStudents(students);
        students=studentJpaRepository.findStudentByNameContaining("j");
        printStudents(students);
        //按姓名查找记录并降序排序
        students=studentJpaRepository.getStudentByNameContainingOrderByNameDesc("j");
        printStudents(students);
        //按姓名查找记录并升序排序
        students=studentJpaRepository.getStudentByNameContainingOrderByNameAsc("j");
        printStudents(students);
    }

    @Test
    // 测试Query自定义SQL方法
    public void testQuerySqlMethods() {
        List<Student> students = studentJpaRepository.queryStudentsOrderByNameDescSQL();
        printStudents(students);

        students = studentJpaRepository.queryStudentsOrderByNameAscHQL();
        printStudents(students);

        students=studentJpaRepository.queryStudentsByNameContainingSQL("j");
        printStudents(students);

        students = studentJpaRepository.queryStudentsByNameContainingSQL_("j");
        printStudents(students);

        students = studentJpaRepository.queryStudentsByNameContainingHQL("j");
        printStudents(students);

        students = studentJpaRepository.queryStudentsByNameContainingHQL_("j");
        printStudents(students);
    }
    @Autowired
    private StudentService studentService;
    // 测试 @Modifying @Query 修改、删除
    @Test
    public void testUsingModifyingAnnotation(){
        int result=studentService.updateStudentById(1L,"wqiujie");
        System.out.println(result>=1?"修改成功！":"修改失败");

        result=studentService.deleteStudentById(2l);
        System.out.println(result>=1?"删除成功！":"删除失败");
    }
    // 测试分页和排序
    @Test
    public void testPageable() throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        Pageable pageable = PageRequest.of(0,3);
        Pageable sortPageable=PageRequest.of(1,3, Sort.by(Sort.Direction.ASC, "id"));
        Page<Student> pages= studentJpaRepository.findAll(pageable);
        System.out.println(mapper.writeValueAsString(pages));
        System.out.println(mapper.writeValueAsString(studentJpaRepository.findAll(sortPageable)));
    }
    // 测试排序和限制
    @Test
    public void testOrderLimit() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        Student student=studentJpaRepository.findFirstByOrderByNameAsc();
        System.out.println(objectMapper.writeValueAsString(student));
        System.out.println(objectMapper.writeValueAsString(studentJpaRepository.findFirst2ByOrderByNameDesc()));
        System.out.println(objectMapper.writeValueAsString(studentJpaRepository.findFirst2ByNameContainsOrderByNameDesc("j")));

        Pageable pageable = PageRequest.of(0,2, Sort.by(Sort.Direction.ASC, "id"));
        System.out.println(objectMapper.writeValueAsString(studentJpaRepository.findFirst2ByNameContains("j", pageable)));
    }
    //测试动态查询
    @Autowired
    StudentRepository studentRepository;
    @Test
    public void testDyanmicSql() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // 使用动态查询实现 按name模糊查询并且性别为女性的记录
        List<Student> lists = studentRepository.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.and(criteriaBuilder.like(root.get("name"),"%g%"),
                        criteriaBuilder.equal(root.get("gender"),0)
                );
            }
        });
        System.out.println(objectMapper.writeValueAsString(lists));

    }
}
