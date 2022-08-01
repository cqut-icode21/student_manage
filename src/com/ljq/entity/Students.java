package ljq.entity;

import ljq.annotation.Column;
import ljq.annotation.Id;
import ljq.annotation.Table;

/*学生类*/
@Table(tableName = "students", label = "学生表")
public class Students {
    @Id(idName = "id")
    @Column(columnName = "id", label = "学生学号")
    private String id;

    @Column(columnName = "name", label = "学生名字")
    private String name;

    @Column(columnName = "sex", label = "学生性别")
    private String sex;

    @Column(columnName = "college", label = "学生学院")
    private String college;

    @Column(columnName = "professional", label = "学生专业")
    private String professional;

    @Column(columnName = "grade", label = "学生年级")
    private String grade;

    @Column(columnName = "class", label = "学生班级")
    private String clazz;

    @Column(columnName = "age", label = "学生年龄")
    private String age;

    public Students() {
    }

    public Students(String id, String name, String sex, String college, String professional, String grade, String clazz, String age) {
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
        return "Students{" +
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

//    public String get(int i) {
//        switch (i) {
//            case 1:
//                return id;
//            case 2:
//                return name;
//            case 3:
//                return sex;
//            case 4:
//                return college;
//            case 5:
//                return professional;
//            case 6:
//                return grade;
//            case 7:
//                return clazz;
//            case 8:
//                return age;
//        }
//        return "";
//    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getCollege() {
        return college;
    }

    public String getProfessional() {
        return professional;
    }

    public String getGrade() {
        return grade;
    }

    public String getClazz() {
        return clazz;
    }

    public String getAge() {
        return age;
    }
}
