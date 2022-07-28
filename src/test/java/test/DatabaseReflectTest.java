package test;

import dao.impl.DatabaseReflect;
import enties.Student;
import org.junit.Test;

import java.util.List;

public class DatabaseReflectTest {
    DatabaseReflect databaseReflect = new DatabaseReflect();

    @Test
    public void findAll() {
        List<Student> list = databaseReflect.findAll(Student.class);
        for (Student student : list){
            System.out.println(student);
        }
    }

    @Test
    public void findById() {
        System.out.println(databaseReflect.findById(Student.class, "11921380131"));
        System.out.println("\n");
    }

    @Test
    public void delete() {
        System.out.println(databaseReflect.delete(Student.class, "11921380131"));
    }

    @Test
    public void add() {
        Student student = new Student();
        System.out.println(databaseReflect.add(student));
    }

    @Test
    public void update() {
        Student student = (Student) databaseReflect.findById(Student.class, "11921380131");
        student.setName("某某某");
        databaseReflect.update(student,"11921380131");
    }
}
