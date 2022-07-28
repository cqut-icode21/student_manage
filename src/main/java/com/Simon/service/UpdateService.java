package com.Simon.service;

import com.Simon.dao.impl.DatabaseReflect;
import com.Simon.entities.Student;

public class UpdateService {
    private final String id;
    private final String name;
    private final String sex;
    private final String college;
    private final String professional;
    private final String grade;
    private final String CLASS;
    private final String age;
    private final Student student;


    public UpdateService(String[] properties) {
        id = properties[0];
        name = properties[1];
        sex = properties[2];
        college = properties[3];
        professional = properties[4];
        grade = properties[5];
        CLASS = properties[6];
        age = properties[7];
        student = new Student(id, name, sex, college, professional, grade, CLASS, age);
    }


    public boolean update() {
        DatabaseReflect databaseReflect = new DatabaseReflect();
        return databaseReflect.update(student, id);
    }
}
