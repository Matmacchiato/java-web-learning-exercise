package com.example.springbootjpamysqlentitymapping;

import com.example.springbootjpamysqlentitymapping.model.*;
import com.example.springbootjpamysqlentitymapping.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;

@SpringBootTest
public class JpaEntityMappingTest {
    private ObjectMapper objectMapper=new ObjectMapper();
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentDetailRepository departmentDetailRepository;


    @Autowired
    private EmployeeDetailRepository employeeDetailRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private HobbyRepository hobbyRepository;

    // Department--departmentDetail 一对一关系测试
    @Test
    public void testDepartmentOneByOne() throws JsonProcessingException, ParseException {
        departmentRepository.save(new Department("人事部"));
        departmentRepository.save(new Department("软件开发部"));
        departmentRepository.save(new Department("项目推广部"));

        DepartmentDetail departmentDetail1=new DepartmentDetail("郭秋杰","17F");
        DepartmentDetail departmentDetail2=new DepartmentDetail("王秋杰","16F");
        DepartmentDetail departmentDetail3=new DepartmentDetail("李秋杰","15F");
        departmentDetailRepository.save(departmentDetail1);
        departmentDetailRepository.save(departmentDetail2);
        departmentDetailRepository.save(departmentDetail3);

        Department department1=departmentRepository.findById(1L).orElse(null);
        Department department2=departmentRepository.findById(2L).orElse(null);
        Department department3=departmentRepository.findById(3L).orElse(null);

        department1.setDepartmentDetail(departmentDetail1);
        department2.setDepartmentDetail(departmentDetail2);
        department3.setDepartmentDetail(departmentDetail3);

        departmentRepository.save(department1);
        departmentRepository.save(department2);
        departmentRepository.save(department3);



        System.out.print(objectMapper.writeValueAsString(departmentRepository.findAll()));

        departmentRepository.deleteById(3L);
        System.out.println(objectMapper.writeValueAsString(departmentRepository.findAll()));

        //测试一对多 多对一 多对多
        var hobby1 = new Hobby("游泳");
        var hobby2 = new Hobby("书法");
        var hobby3 = new Hobby("绘画");
        var hobby4 = new Hobby("看电视");
        var hobby5 = new Hobby("电脑游戏");
        hobbyRepository.save(hobby1);
        hobbyRepository.save(hobby2);
        hobbyRepository.save(hobby3);
        hobbyRepository.save(hobby4);
        hobbyRepository.save(hobby5);
        var hobbySet1=new HashSet<Hobby>();
        var hobbySet2=new HashSet<Hobby>();
        var hobbySet3=new HashSet<Hobby>();

        hobbySet1.add(hobby1);hobbySet1.add(hobby3);
        hobbySet2.add(hobby2);hobbySet2.add(hobby4);
        hobbySet3.add(hobby1);hobbySet3.add(hobby5);
        var employeeDetail1=new EmployeeDetail(new SimpleDateFormat("yyyy-MM-dd").parse("1998-03-17"),
                "深圳","1801250111","",10000.0);
        var employeeDetail2=new EmployeeDetail(new SimpleDateFormat("yyyy-MM-dd").parse("2000-03-17"),
                "北京","1801250112","",20000.0);
        var employeeDetail3=new EmployeeDetail(new SimpleDateFormat("yyyy-MM-dd").parse("2002-03-17"),
                "广州","1801250113","",30000.0);

        employeeDetail1.setHobbies(hobbySet1);
        employeeDetail2.setHobbies(hobbySet2);
        employeeDetail3.setHobbies(hobbySet3);

        employeeDetailRepository.save(employeeDetail1);
        employeeDetailRepository.save(employeeDetail2);
        employeeDetailRepository.save(employeeDetail3);

        var employee1 = new Employee("郭秋杰",0);
        var employee2 = new Employee("王秋杰",1);
        var employee3 = new Employee("李秋杰",1);
        employee1.setDepartment(department1);
        employee2.setDepartment(department2);
        employee3.setDepartment(department2);
        employee1.setEmployeeDetail(employeeDetail1);
        employee2.setEmployeeDetail(employeeDetail2);
        employee3.setEmployeeDetail(employeeDetail3);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);

        System.out.println(objectMapper.writeValueAsString(hobbyRepository.findAll().toArray()));
        System.out.println(objectMapper.writeValueAsString(employeeDetailRepository.findAll().toArray()));
        System.out.println(objectMapper.writeValueAsString(employeeRepository.findAll().toArray()));

    }
}
