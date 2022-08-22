package com.example.springbootjpamysqlentitymapping.model;


import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int gender;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "departmentId",referencedColumnName = "id")
    private Department department;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "detailId",referencedColumnName = "id")
    private EmployeeDetail employeeDetail;

    public Employee() {
    }

    public Employee(String name, int gender){
        this.name = name;
        this.gender = gender;
    }

    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeDetail getEmployeeDetail() {
        return employeeDetail;
    }
    public void setEmployeeDetail(EmployeeDetail employeeDetail) {
        this.employeeDetail = employeeDetail;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
