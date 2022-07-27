package zhz.pojo;

import zhz.annotation.Column;
import zhz.annotation.Table;

/**
 * @author 龙
 */
@Table(tableName = "user")
public class User {
    @Column
    private String userName = "root";
    @Column
    private String password = "root";

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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
