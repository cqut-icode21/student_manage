package com.he.servlet;

import com.he.service.LoginService;
import com.he.service.impl.OperationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 13253
 * @date 2021/7/29 17:05
 * @className LoginServlet
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final LoginService LOGIN_SER = new OperationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("用户名" + username);
        System.out.println("密码" + password);
        boolean b = LOGIN_SER.loginSuccess(username, password);
        resp.getWriter().write(String.valueOf(b));
    }
}
