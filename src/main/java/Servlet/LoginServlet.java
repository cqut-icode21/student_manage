package Servlet;

import entities.Student;
import entities.User;
import service.FileService;
import service.FileServiceImpl;
import service.LoginService;
import service.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/stu")
public class LoginServlet extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username=req.getParameter("username");
        String password=req.getParameter("password");
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);

        LoginService loginService=new LoginServiceImpl();
        User user1=loginService.LoginService(user);


    FileService fs=new FileServiceImpl();
    ArrayList<Student> arrStudent=fs.getAllStudent();


       if (user1!=null){
            req.setAttribute("user",user1);
            req.getRequestDispatcher("student.jsp").forward(req,resp);
        }else {
            req.setAttribute("error","账户或密码错误");
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
       this.doPost(req,resp);

    }
}
