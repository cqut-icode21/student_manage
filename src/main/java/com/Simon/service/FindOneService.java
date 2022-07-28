package com.Simon.service;

import com.Simon.dao.impl.DatabaseReflect;
import com.Simon.entities.Student;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FindOneService {
    private final String id;

    public FindOneService(String id) {
        this.id = id;
    }

    public List find() throws IllegalAccessException {
        DatabaseReflect databaseReflect = new DatabaseReflect();
        Student student = (Student) databaseReflect.findById(Student.class, id);
        List values = new ArrayList<>();
        Field[] fields = Student.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            values.add(field.get(student));
        }
        return values;
    }
}
