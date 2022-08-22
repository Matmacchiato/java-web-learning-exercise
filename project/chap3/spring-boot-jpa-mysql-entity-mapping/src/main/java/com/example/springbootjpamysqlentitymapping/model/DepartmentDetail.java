package com.example.springbootjpamysqlentitymapping.model;

import javax.persistence.*;

@Entity
@Table
public class DepartmentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String chief;//主管

    @Column(nullable = false)
    private String address;//地址

    public DepartmentDetail(){

    }

    public DepartmentDetail(String chief, String address){
        this.chief = chief;
        this.address = address;
    }

    public String getChief() {
        return chief;
    }
    public void setChief(String chief) {
        this.chief = chief;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
