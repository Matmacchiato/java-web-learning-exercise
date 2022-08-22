package cn.anntek.springbootmybatismysqlcrud.repository;

import cn.anntek.springbootmybatismysqlcrud.model.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Select("select * from employees")
    @Results({
            @Result(property = "email",column = "email_address"),
    })
    public List<Employee> findAll();

    @Select("select * from employees where id=#{id}")
    @Results({
            @Result(property = "email",column = "email_address"),
    })
    public Employee findById(@Param("id") long id);

    @Select("select * from employees where name like concat('%',#{1},'%')")
    @Results({
            @Result(property = "email", column = "email_address"),
    })
    public List<Employee> findByNameContaining(String name);
    @Delete("delete from employees where id=#{id}")
    public int deleteById(@Param("id") long id);

    @Insert("insert ignore into employees(name, email_address, address, salary)" + " values(#{name}, #{email}, #{address}, #{salary})")
    public int insert(Employee employee);

    @Update("update ignore employees set name=#{employee.name}," +
            "email_address=#{employee.email}, address=#{employee.address}," +
            "salary=#{employee.salary} where id=#{id}")
    public int updateById(Employee employee, Long id);
}
