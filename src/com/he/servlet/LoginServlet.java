package com.he.servlet;

import com.he.service.LoginService;
import com.he.service.impl.OperationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        username = new String(username.getBytes("iso8859-1"), "UTF-8");
        String password = req.getParameter("password");
        boolean b = LOGIN_SER.loginSuccess(username, password);
        resp.getWriter().write(String.valueOf(b));
    }

//    protected void service(HttpServletRequest req, HttpServletResponse resp)
//             {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        // 标记是否记住密码
//        String remPwd = req.getParameter("remPwd");
//
//
//        // 登录成功
//        if (LOGIN_SER.loginSuccess(username, password)) {
//            if (remPwd != null) { // 记住密码
//                // cookie 保存姓名, 密码
//                Cookie cookie1 = new Cookie("username", username);
//                Cookie cookie2 = new Cookie("password", password);
//                // cookie存在浏览器一个小时, 保存浏览器的文件下
//                cookie1.setMaxAge(60 * 60);
//
//                // 保存cookie到浏览器
//                resp.addCookie(cookie1);
//                resp.addCookie(cookie2);
//            }
//        }
//    }
}
