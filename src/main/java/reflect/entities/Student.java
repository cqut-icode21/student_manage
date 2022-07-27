package reflect.entities;

import reflect.annotation.Column;
import reflect.annotation.Id;
import reflect.annotation.Table;

/**
 * 学生实体类
 */
@Table(tableName = "students")
public class Student {
    @Id(idName = "id")
    @Column(columnName = "id")
    private String id;
    @Column(columnName = "name")
    private String name;
    @Column(columnName = "sex")
    private String sex;

    @Column(columnName = "college")
    private String college;
    @Column(columnName = "professional")
    private String professional;

    @Column(columnName = "grade")
    private String grade;

    @Column(columnName = "class")
    private String clazz;
    @Column(columnName = "age")
    private String age;

    public Student() {

    }

    public Student(String id, String name, String sex, String college, String professional, String grade, String clazz, String age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.professional = professional;
        this.grade = grade;
        this.clazz = clazz;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", college='" + college + '\'' +
                ", professional='" + professional + '\'' +
                ", grade='" + grade + '\'' +
                ", clazz='" + clazz + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
