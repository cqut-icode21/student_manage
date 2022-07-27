package TestJava.people;
import TestJava.com.Col;
import TestJava.com.Table;
import TestJava.com.id;
@Table(tableName = "student" ,label ="学生类" )
public class Student {
    @id(sid = "number")
    @Col(colName = "number",label = "学号")
    private Integer number;
    @Col(colName = "name",label = "学生姓名")
    private  String name;

    @Col(colName = "age",label = "学生年龄")
    private  String age;
    @Col(colName = "gender",label = "学生")
    private String gender;
    @Col(colName = "college",label = "学校")
    private String college;
    @Col(colName = "Class",label = "班级")
    private String Classes;
    @Col(colName = "grade",label = "年级")
    private String  grade;
    @Col(colName = "major",label = "专业")
    private String major;
    public Student(){

    }
    public Student(Integer number, String name, String age,String gender,String college,String Classes,String grade,String major){
        this.number=number;
        this.age=age;
        this.name=name;
        this.Classes=Classes;
        this.major=major;
        this.college=college;
        this.gender=gender;
        this.grade=grade;
    }
    public void setNumber(Integer number){
        this.number=number;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public Integer getNumber() {
        return number;
    }

    public String getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getClasses() {
        return Classes;
    }

    public void setClass(String Classes) {
        this.Classes = Classes;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    @Override
    public String toString() {
        return "{ 'number': '" + this.number + "'  , 'name': '" + this.name + "' , 'gender': '" + this.gender +
                "' , 'college': '" + this.college + "' , 'major': '" + this.major + "', 'grade': '" + this.grade+
        "' ,'class': '" + this.Classes + "' , 'age': '" +this.age+ "' }";
    }
}
