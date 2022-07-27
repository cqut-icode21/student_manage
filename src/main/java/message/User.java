package message;


import annotation.Column;
import annotation.Table;


@Table(tableName = "user")
public class User {
    @Column
    private String userName = "root";
    @Column
    private String password = "zx673494350";

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userNAme='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userNAme) {
        this.userName = userNAme;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
