package enties;

import annotation.Column;

public class User {

    @Column(columnName = "userName" , label = "用户名")
    private String userName = "xie";
    @Column(columnName = "passWord" , label = "用户密码")
    private String password = "123456";

    public User(String userName, String passWord){
        this.userName = userName;
        this.password = passWord;
    }

    public User() {
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

    public void setPassword(String passWord) {
        this.password = passWord;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + password + '\'' +
                '}';
    }
}
