package com.Simon.entities;

import com.Simon.annotation.Column;
import com.Simon.annotation.Id;
import com.Simon.annotation.Table;
import com.alibaba.fastjson.annotation.JSONField;

@Table(tableName = "students", label = "学生表")
public class Student {
    @Id(idName = "id")
    @JSONField(ordinal = 1)
    @Column(columnName = "id", label = "学生学号")
    private String id;
    @JSONField(ordinal = 2)
    @Column(columnName = "name", label = "学生姓名")
    private String name;
    @JSONField(ordinal = 3)
    @Column(columnName = "sex", label = "学生性别")
    private String sex;
    @JSONField(ordinal = 4)
    @Column(columnName = "college", label = "学生学院")
    private String college;
    @JSONField(ordinal = 5)
    @Column(columnName = "professional", label = "学生专业")
    private String professional;
    @JSONField(ordinal = 6)
    @Column(columnName = "grade", label = "学生年级")
    private String grade;
    @JSONField(ordinal = 7)
    @Column(columnName = "CLASS", label = "学生班级")
    private String CLASS;
    @JSONField(ordinal = 8)
    @Column(columnName = "age", label = "学生年龄")
    private String age;

    public Student() {
    }

    public Student(String id, String name, String sex, String college, String professional, String grade, String Class, String age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.professional = professional;
        this.grade = grade;
        this.CLASS = Class;
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
                ", CLASS='" + CLASS + '\'' +
                ", age='" + age + '\'' +
                '}';
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

    public String getCLASS() {
        return CLASS;
    }

    public void setClass(String CLASS) {
        this.CLASS = CLASS;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
