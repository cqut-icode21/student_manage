package utils;

import dao.LoginDaoImpl;
import entities.User;

public class test {
    public static void main(String[] args) {
        String id="12109990907";
        String pas="123456";
        User u=new User();
        u.setUsername(id);
        u.setPassword(pas);

        LoginDaoImpl l=new LoginDaoImpl();
        User retUse=l.LoginDao(u);

        if (retUse!=null){
            System.out.println(retUse.toString());
            System.out.println("账户和密码一致");
        }else {
            System.out.println("账户和密码不一致");
        }
    }
}
