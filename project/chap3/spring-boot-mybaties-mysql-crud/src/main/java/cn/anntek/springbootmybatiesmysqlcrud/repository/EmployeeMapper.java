package cn.anntek.springbootmybatiesmysqlcrud.repository;

import cn.anntek.springbootmybatiesmysqlcrud.mdel.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Select("select * from employees")
    @Results(id="EmployeeResult",value={
            @Result(property = "email", column = "email_address"),
    })
    public List<Employee> findAll();


    //命名参数
    @Select("select * from employees where id=#{id}")
    @ResultMap("EmployeeResult")
    public Employee findById(@Param("id") long id);

    //位置参数
    @Select("select * from employees where name like concat('%',#{1},'%')")
    @ResultMap("EmployeeResult")
    public List<Employee> findByNameContaining(String name);

    //命名参数
    @Delete("delete from employees where id=#{id}")
    public int deleteById(@Param("id") long id);

    //对象参数
    @Insert("insert ignore into employees(name, email_address, address, salary)" +
            " values(#{name}, #{email}, #{address}, #{salary})")
    public int insert(Employee employee);

    //对象参数，命名参数
    @Update("update ignore employees set name=#{employee.name}," +
            "email_address=#{employee.email}, address=#{employee.address}," +
            "salary=#{employee.salary} where id=#{id}")
    public int updateById(Employee employee, Long id);
}
