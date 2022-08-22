package cn.anntek.springbootjpasqlitestudentcrud.models;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true)
    private String sid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int gender; //0-女，0-男

    public Student(){
    }
    public Student(String sid, String name,int gender){
        this.sid = sid;
        this.name = name;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
}
