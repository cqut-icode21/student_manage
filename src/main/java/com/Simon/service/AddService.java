package com.Simon.service;

import com.Simon.entities.Student;

public class AddService {

    private String id;
    private String name;
    private String sex;
    private String college;
    private String professional;
    private String grade;
    private String CLASS;
    private String age;
    private Student student;

    public AddService() {

    }

    public AddService(String id, String name, String sex, String college, String professional, String grade, String CLASS, String age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.professional = professional;
        this.grade = grade;
        this.CLASS = CLASS;
        this.age = age;
        student = getStudent();
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

    public String getCLASS() {
        return CLASS;
    }

    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Student getStudent() {
        return createStudent();

    }

    public Student createStudent() {
        return new Student(id, name, sex, college, professional, grade, CLASS, age);
    }

}
