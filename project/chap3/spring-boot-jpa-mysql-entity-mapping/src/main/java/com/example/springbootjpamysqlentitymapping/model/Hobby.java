package com.example.springbootjpamysqlentitymapping.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hobbies")
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String name;

    @ManyToMany(mappedBy = "hobbies", fetch = FetchType.LAZY)
    private Set<EmployeeDetail> employeeDetailSet;

    public Hobby(){
    }

    public Hobby(String name){
        this.name = name;
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
}
