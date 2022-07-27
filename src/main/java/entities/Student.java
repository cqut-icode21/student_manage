package entities;

import annotation.Column;
import annotation.Id;
import annotation.Table;;import java.util.List;

@Table(tableName = "student", label = "学生表")
public class Student  {
    @Id(idName = "id")
    @Column(columnName = "id", label = "学生学号")
    private String S_id;
    @Column(columnName = "name", label = "学生姓名")
    private String S_name;
    @Column(columnName = "gender", label = "学生性别")
    private String S_gender;
    @Column(columnName = "college", label = "学生学院")
    private String S_college;
    @Column(columnName = "major", label = "学生专业")
    private String S_major;
    @Column(columnName = "grade", label = "学生年级")
    private String S_grade;
    @Column(columnName = "class", label = "学生班级")
    private String S_class1;
    @Column(columnName = "age", label = "学生年龄")
    private Integer S_age;

    public Student() {
    }

    public Student(String S_id, String S_name,String S_gender,String S_college,String S_major,String S_grade,String
            S_class1,Integer S_age) {
        this.S_id = S_id;
        this.S_name = S_name;
        this.S_gender=S_gender;
        this.S_college=S_college;
        this.S_major=S_major;
        this.S_grade=S_grade;
        this.S_class1=S_class1;
        this.S_age= S_age;
    }

    @Override
    public String toString() {
        return "student{" +
                "S_id=" + S_id +
                ", S_name=" +S_name + ", S_gender=" +S_gender +",S_college="+S_college+",S_major="+S_major+",S_grade="+
                S_grade+",S_class="+S_class1+
                ", S_age=" + S_age +
                '}';
    }

    public String getId() {
        return S_id;
    }

    public void setId(String S_id) {
        this.S_id =S_id;
    }

    public String getName() {
        return S_name;
    }

    public void setName(String S_name) {
        this.S_name = S_name;
    }

    public Integer getAge() {
        return S_age;
    }

    public void setAge(Integer S_age) {
        this.S_age = S_age;
    }

    public String getGender() {
        return S_gender;
    }

    public void setGender(String S_gender) {
        this.S_gender = S_gender;
    }

    public String getCollege() {
        return S_college;
    }

    public void setCollege(String S_college) {
        this.S_college = S_college;
    }

    public String getMajor() {
        return S_major;
    }

    public void setMajor(String S_major) {
        this.S_major = S_major;
    }

    public String getGrade() {
        return S_grade;
    }

    public void setGrade(String S_grade) {
        this.S_grade= S_grade;
    }

    public String getClass1() {
        return S_class1;
    }

    public void setClass1(String S_class1) {
        this.S_class1 = S_class1;
    }
}