package Person;


import note.Column;
import note.Table;

@Table(tableName = "teacher",idLength = 7)
public class Teacher {
    @Column(columnName = "te_id",type = "varchar(7)",maxLength = 7)
    private String id;
    @Column(columnName = "te_name",type = "varchar(10)",maxLength = 10)
    private String name;
    @Column(columnName = "te_gender",type = "varchar(1)",maxLength = 1)
    private String gender;
    @Column(columnName = "te_faculty",type = "varchar(10)",maxLength = 10)
    private String faculty;
    @Column(columnName = "te_number",type = "varchar(11)",maxLength = 11)
    private String number;

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", faculty='" + faculty + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
