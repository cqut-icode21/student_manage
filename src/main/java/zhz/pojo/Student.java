package zhz.pojo;

import zhz.annotation.Column;
import zhz.annotation.ID;
import zhz.annotation.Table;

/**
 * @author 龙
 */
@Table(tableName = "Student")
public class Student {
    @Column(columnName = "id")
    @ID(idName = "id")
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
    @Column(columnName = "clazz")
    private String clazz;
    @Column(columnName = "age")
    private String age;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id +
                ", name='" + name  +
                ", sex='" + sex  +
                ", college='" + college +
                ", professional='" + professional +
                ", grade=" + grade +
                ", clazz=" + clazz +
                ", age=" + age +
                '}';
    }

    public String get(int i) {
        switch (i) {
            case 1:
                return id;
            case 2:
                return name;
            case 3:
                return sex;
            case 4:
                return college;
            case 5:
                return professional;
            case 6:
                return grade;
            case 7:
                return clazz;
            case 8:
                return age;
            default:
        }
        return "";
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
