package cn.anntek.springbootmybatiesmysqlcrud;

import cn.anntek.springbootmybatiesmysqlcrud.mdel.Employee;
import cn.anntek.springbootmybatiesmysqlcrud.repository.EmployeeMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisTest {
    @Autowired
    EmployeeMapper employeeMapper;

    ObjectMapper objectMapper=new ObjectMapper();

    @Test
    public void testMybatisCrud() throws JsonProcessingException {
        employeeMapper.insert(new Employee("郑豪","1362478331@qq.com","深圳", 200000.0));
        employeeMapper.insert(new Employee("郭豪","1362478331@qq.com","北京", 300000.0));
        employeeMapper.insert(new Employee("郭明","1362478331@qq.com","广州", 400000.0));

        System.out.println(objectMapper.writeValueAsString(employeeMapper.findAll()));
        System.out.println(objectMapper.writeValueAsString(employeeMapper.findById(2L)));
        System.out.println(objectMapper.writeValueAsString(employeeMapper.findByNameContaining("豪")));

        employeeMapper.updateById(new Employee("郑豪","1362478331@qq.com","深圳", 500000.0), 1L);
        System.out.println(objectMapper.writeValueAsString(employeeMapper.findById(1L)));

        employeeMapper.deleteById(3L);
        System.out.println(objectMapper.writeValueAsString(employeeMapper.findAll()));
    }
}
