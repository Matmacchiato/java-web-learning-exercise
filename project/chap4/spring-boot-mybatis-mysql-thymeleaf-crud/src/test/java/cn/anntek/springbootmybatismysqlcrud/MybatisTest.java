package cn.anntek.springbootmybatismysqlcrud;

import cn.anntek.springbootmybatismysqlcrud.model.Employee;
import cn.anntek.springbootmybatismysqlcrud.repository.EmployeeMapper;
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
    public void testMyBatisCrud() throws JsonProcessingException{
        employeeMapper.insert(new Employee("郭秋杰","cjh@sziit.edu.cn","深圳",100000.0));
        employeeMapper.insert(new Employee("张三","6666666@sziit.edu.cn","广州",77700.0));
        employeeMapper.insert(new Employee("李四","1234567@sziit.edu.cn","北京",99900.0));

        System.out.println(objectMapper.writeValueAsString(employeeMapper.findAll()));
        System.out.println(objectMapper.writeValueAsString(employeeMapper.findById(2L)));
        System.out.println(objectMapper.writeValueAsString(employeeMapper.findByNameContaining("活")));

        employeeMapper.updateById(new Employee("李四","ls@sziit.edu.cn","北京",20000.0),3L);

        System.out.println(objectMapper.writeValueAsString(employeeMapper.findById(3L)));

        employeeMapper.deleteById(3L);
        System.out.println(objectMapper.writeValueAsString(employeeMapper.findAll()));
    }
}
