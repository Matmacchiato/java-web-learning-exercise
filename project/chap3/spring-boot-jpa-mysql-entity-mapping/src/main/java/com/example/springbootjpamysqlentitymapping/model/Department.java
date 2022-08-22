package com.example.springbootjpamysqlentitymapping.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = {CascadeType.ALL})//增改删联动
    @JoinColumn(name = "detailId",referencedColumnName = "id")
    private DepartmentDetail departmentDetail;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Employee> employeeSet = new HashSet<>();

    public Department(String name){
        this.name = name;
    }
    public Department(){

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
    public DepartmentDetail getDepartmentDetail() {
        return departmentDetail;
    }
    public void setDepartmentDetail(DepartmentDetail departmentDetail) {
        this.departmentDetail = departmentDetail;
    }
}
