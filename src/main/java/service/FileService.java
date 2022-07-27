package service;

import entities.Student;

import java.util.ArrayList;
import java.util.List;

public interface FileService {
    ArrayList<Student> getAllStudent();


    void del(String v);

    void add(Object message);


    void update(Object student, Object id);

}
