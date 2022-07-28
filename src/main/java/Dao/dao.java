package Dao;

import Person.Student;
import Person.Teacher;
import com.mysql.cj.jdbc.Driver;
import note.Table;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface dao{

    //获取一页的内容
    List<Student> selectAll() throws SQLException;

    void addStudent(Student stu)throws SQLException;
    void deleteStudent(String[] id) throws SQLException;

    void changeStudent(Student stu,String id2) throws SQLException;

    List<Student> scout(String inf)throws SQLException;
}
