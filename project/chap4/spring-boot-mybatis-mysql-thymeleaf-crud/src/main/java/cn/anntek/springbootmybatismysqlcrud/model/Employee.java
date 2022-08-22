package cn.anntek.springbootmybatismysqlcrud.model;

public class Employee {
    private Long id;
    private String name;
    private String email;
    private String address;
    private Double salary;

    public Employee(){
    }

    public Employee(String name,String email,String address,double salary){
        this.name = name;
        this.email = email;
        this.address = address;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
