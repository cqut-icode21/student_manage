package enties;


import annotation.Column;
import annotation.Id;
import annotation.Table;

@Table(tableName = "students")
public class Student {

    @Column(columnName = "name", label = "学生姓名")
    private String name;

    @Column(columnName = "sex", label = "性别")
    private String gender;
    @Column(columnName = "age", label = "学生年龄")
    private String age;
    @Column(columnName = "college", label = "院系")
    private String college;
    @Id(idName = "id")
    @Column(columnName = "id", label = "学生学号")
    private String number;
    @Column(columnName = "professional", label = "专业")
    private String major;


    @Column(columnName = "grade", label = "年级")
    private String grade;

    @Column(columnName = "class", label = "班级")
    private String cla;

    public Student() {
    }

    public Student(String id, String name, String age, String sex, String dept) {
        this.number = id;
        this.name = name;
        this.age = age;
        this.college = dept;
        this.gender = sex;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + gender + '\'' +
                ", age=" + age +
                ", dept='" + college + '\'' +
                ", professional='" + major + '\'' +
                ", id='" + number + '\'' +
                ", grade='" + grade + '\'' +
                ", cla='" + cla + '\'' +
                '}';
    }
}
