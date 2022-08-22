package cn.anntek.springbootthymeleaflearning.model;

public class Student {
    String sid;//学号
    String name;//姓名
    Integer gender;//性别0-女 1男
    String photo;//照片
    String className;//班级名称
    String department;//部门
    String birthday;//出生日期


public Student(){
}

    public Student(String sid, String name, Integer gender, String photo, String className, String department, String birthday) {
        this.sid = sid;
        this.name = name;
        this.gender = gender;
        this.photo = photo;
        this.className = className;
        this.department = department;
        this.birthday = birthday;
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
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
