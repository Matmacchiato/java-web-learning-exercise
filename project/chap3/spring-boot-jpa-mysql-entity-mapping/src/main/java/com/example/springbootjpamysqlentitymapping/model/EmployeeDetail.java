package com.example.springbootjpamysqlentitymapping.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employee_details")
public class EmployeeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    Date birthday;
    @Column(nullable = false)
    String addr;//住址

    @Column(nullable = false)
    String phone;//电话

    @Column(nullable = true)
    String photo;//相片
    @Column(nullable = false)
    Double salary;//工资
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_hobbies",
            joinColumns = {@JoinColumn(name = "employee_detail_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "hobbies_id",referencedColumnName = "id")})
    private Set<Hobby> hobbies;
    public EmployeeDetail(){
    }

    public EmployeeDetail(Date birthday, String addr, String phone, String photo, Double salary) {
        this.birthday = birthday;
        this.addr = addr;
        this.phone = phone;
        this.photo = photo;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    public Set<Hobby> getHobbies() {
        return hobbies;
    }
    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
}
