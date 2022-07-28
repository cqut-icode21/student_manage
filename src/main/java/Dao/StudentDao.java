package Dao;

import Person.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements dao {
    private Connection connection = null;

    private PreparedStatement pre = null;

    private ResultSet resultSet = null;
    @Override
    public List<Student> selectAll() throws SQLException {
        connection = ConnectionUtils.setConnection();
        List<Student> ans = new ArrayList<>();//返回的结果
        //创建sql语句
        pre = connection.prepareStatement("SELECT * FROM student");
        //获取结果集
        resultSet = pre.executeQuery();
        //遍历
        while(resultSet.next()){
            Student temp = new Student();
            temp.setId(resultSet.getString("stu_id"));
            temp.setName(resultSet.getString("stu_name"));
            temp.setGender(resultSet.getString("stu_gender"));
            temp.setCollege(resultSet.getString("stu_college"));
            temp.setMajor(resultSet.getString("stu_major"));
            temp.setGrade(resultSet.getString("stu_grade"));
            temp.setsClass(resultSet.getString("stu_class"));
            temp.setAge(resultSet.getInt("stu_age"));
            //加入结果集
            ans.add(temp);
        }
        ConnectionUtils.closeAll(connection,pre,resultSet);
        return ans;
    }
    @Override
    public void addStudent(Student stu) throws SQLException {
        connection = ConnectionUtils.setConnection();
        pre = connection.prepareStatement("INSERT INTO student VALUES (?,?,?,?,?,?,?,?)");
        pre.setObject(1,stu.getId());
        pre.setObject(2,stu.getName());
        pre.setObject(3,stu.getGender());
        pre.setObject(4,stu.getCollege());
        pre.setObject(5,stu.getMajor());
        pre.setObject(6,stu.getGrade());
        pre.setObject(7,stu.getsClass());
        pre.setObject(8,stu.getAge());
        int cnt = pre.executeUpdate();
        ConnectionUtils.closeAll(connection,pre,resultSet);
    }

    @Override
    public void deleteStudent(String[] id) throws SQLException {
        connection = ConnectionUtils.setConnection();
        int len = id.length;
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM STUDENT WHERE ");
        for (int i = 0; i < len; i++) {
            if(i!=len-1){
                sql.append(" stu_id=? or ");
            }else{
                sql.append(" stu_id=?");
            }
        }
        pre = connection.prepareStatement(sql.toString());
        for(int i = 0;i<len;i++){
            pre.setObject(i+1,id[i]);
        }
        int cnt = pre.executeUpdate();
        ConnectionUtils.closeAll(connection,pre,resultSet);
    }

    @Override
    public void changeStudent(Student stu, String id2) throws SQLException {
        connection = ConnectionUtils.setConnection();
        String sql = "UPDATE STUDENT SET stu_id=?,stu_name=?,stu_gender=?," +
                "stu_college=?,stu_major=?,stu_grade=?,stu_class=?,stu_age=? WHERE stu_id=?";
        pre = connection.prepareStatement(sql);
        pre.setObject(1,stu.getId());
        pre.setObject(2,stu.getName());
        pre.setObject(3,stu.getGender());
        pre.setObject(4,stu.getCollege());
        pre.setObject(5,stu.getMajor());
        pre.setObject(6,stu.getGrade());
        pre.setObject(7,stu.getsClass());
        pre.setObject(8,stu.getAge());
        pre.setObject(9,id2);
        int cnt = pre.executeUpdate();
        ConnectionUtils.closeAll(connection,pre,resultSet);
    }

    @Override
    public List<Student> scout(String inf) throws SQLException {
        System.out.println(inf);
        List<Student> ans = new ArrayList<>();
        connection = ConnectionUtils.setConnection();

        pre = connection.prepareStatement("SELECT * FROM STUDENT");
        resultSet = pre.executeQuery();
        while(resultSet.next()){
            boolean flag = false;
            Student temp = new Student();
            temp.setId(resultSet.getString("stu_id"));
            if(temp.getId().contains(inf)){
                flag = true;
            }
            temp.setName(resultSet.getString("stu_name"));
            System.out.println(temp.getName().matches(inf));
            if(!flag&&temp.getName().contains(inf)){
                flag = true;
            }
            temp.setGender(resultSet.getString("stu_gender"));
            if(!flag&&temp.getGender().contains(inf)){
                flag = true;
            }
            temp.setCollege(resultSet.getString("stu_college"));
            if(!flag&&temp.getCollege().contains(inf)){
                flag = true;
            }
            temp.setMajor(resultSet.getString("stu_major"));
            if(!flag&&temp.getMajor().contains(inf)){
                flag = true;
            }
            temp.setGrade(resultSet.getString("stu_grade"));
            if(!flag&&temp.getGrade().contains(inf)){
                flag = true;
            }
            temp.setsClass(resultSet.getString("stu_class"));
            if(!flag&&temp.getsClass().contains(inf)){
                flag = true;
            }
            temp.setAge(resultSet.getInt("stu_age"));
            if(flag){
                ans.add(temp);
            }
        }
        ConnectionUtils.closeAll(connection,pre,resultSet);
        return ans;
    }

}
