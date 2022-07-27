package dao;

import entities.Student;
import utils.DatebaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDaoImpl implements FileDao{
    @Override
    public ArrayList<Student> getAllStudent() {
       ArrayList<Student> arr=new ArrayList<>();
        Connection connection= DatebaseUtil.getConnection();
        PreparedStatement pre=null;
        ResultSet res=null;

        String sql="SELECT t.*\n" +
                "      FROM test.student t\n";
        try{
            pre= connection.prepareStatement(sql);
            res= pre.executeQuery();

            while (res.next()){
                Student student=new Student();
                student.setId(res.getString("id"));
                student.setName(res.getString("name"));
                student.setGender(res.getString("gender"));
                student.setCollege(res.getString("college"));
                student.setMajor(res.getString("major"));
                student.setGrade(res.getString("grade"));
                student.setClass1(res.getString("class"));
                 student.setAge (res.getInt("age"));
                arr.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arr;

    }

    @Override
    public List<Student> getStudent(String v) {

       ArrayList<Student>arr=new ArrayList<>();
       Student student=new Student();
        Connection connection= DatebaseUtil.getConnection();
        PreparedStatement pre=null;
        ResultSet res=null;

        String sql="SELECT t.*\n" +
                "      FROM test.student t\n ";
        try{
            pre= connection.prepareStatement(sql);
           // pre.setObject(1,v);
            res= pre.executeQuery();
            while (res.next()){
                Student student1=new Student();
                student1.setId(res.getString("id"));
                student1.setName(res.getString("name"));
                student1.setGender(res.getString("gender"));
                student1.setCollege(res.getString("college"));
                student1.setMajor(res.getString("major"));
                student1.setGrade(res.getString("grade"));
                student1.setClass1(res.getString("class"));
                student1.setAge (res.getInt("age"));

                student.setName(res.getString("name"));
                if (student.getName().contains(v)){
                    arr.add(student1);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;

    }
}
