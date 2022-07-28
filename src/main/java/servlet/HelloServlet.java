package servlet;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import enties.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/text;charset=utf-8");

        //获取用户密码
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");


        User user1 = new User();

        boolean j = user1.getUserName().equals(userName) && user1.getPassword().equals(password);  //验证是否正确

        if (j) {   //登录成功
            //            req.getRequestDispatcher("/successServLet").forward(req,resp);                //转发
           resp.sendRedirect("StudentSystem.html");
        }
        else {   //登录失败

            resp.getWriter().write("登陆失败，用户名或密码错误");

        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
