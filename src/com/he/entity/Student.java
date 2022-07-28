package com.he.entity;

import com.he.annotation.Column;
import com.he.annotation.Table;

/**
 * @author 13253
 * @date 2021/7/29 18:40
 * @className Student
 */

@Table(tableName = "student")
public class Student {
    @Column(columnName = "id")
    private String id;
    @Column(columnName = "name")
    private String name;
    @Column(columnName = "sex")
    private String sex;
    @Column(columnName = "college")
    private String college;
    @Column(columnName = "professional")
    private String professional;
    @Column(columnName = "grade")
    private String grade;
    @Column(columnName = "class")
    private String sClass;
    @Column(columnName = "age")
    private String age;

    public Student() {
    }

    public Student(String id,String name,String sex,String college,String professional,String grade,String sClass,String age){
        this.id=id;
        this.age=age;
        this.college=college;
        this.name=name;
        this.grade=grade;
        this.sex=sex;
        this.professional=professional;
        this.sClass=sClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", college='" + college + '\'' +
                ", professional='" + professional + '\'' +
                ", grade='" + grade + '\'' +
                ", sClass='" + sClass + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

