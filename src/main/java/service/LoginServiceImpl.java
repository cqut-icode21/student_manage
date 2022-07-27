package service;

import dao.LoginDao;
import dao.LoginDaoImpl;
import entities.User;

public class LoginServiceImpl implements LoginService {
    @Override
    public User LoginService(User user) {
        LoginDao loginDao=new LoginDaoImpl();
        return loginDao.LoginDao(user);
    }
}
