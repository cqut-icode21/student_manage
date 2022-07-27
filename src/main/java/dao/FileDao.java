package dao;

import entities.Student;

import java.util.ArrayList;
import java.util.List;

public interface FileDao {
    ArrayList<Student> getAllStudent();

    List<Student> getStudent(String v);
}
