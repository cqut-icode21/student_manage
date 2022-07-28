package com.Simon.service;

import com.Simon.dao.impl.DatabaseReflect;
import com.Simon.entities.Student;

public class DeleteService {
    private final String[] ids;

    public DeleteService(String[] ids) {
        this.ids = ids;
    }

    public void delete() {
        for (int i = 0; i < ids.length; i++) {
            DatabaseReflect databaseReflect = new DatabaseReflect();
            databaseReflect.delete(Student.class, ids[i]);
        }
    }
}
