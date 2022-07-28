package Servlet;

import Dao.StudentDao;
import Person.Student;
import com.alibaba.fastjson2.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/add")
public class AddStudent extends HttpServlet {
    private StudentDao studentDao = new StudentDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student stu = new Student();
        stu.setId(req.getParameter("id"));
        stu.setName(req.getParameter("name"));
        stu.setGender(req.getParameter("gender"));
        stu.setCollege(req.getParameter("college"));
        stu.setMajor(req.getParameter("major"));
        stu.setGrade(req.getParameter("grade"));
        stu.setsClass(req.getParameter("sClass"));
        stu.setAge(Integer.parseInt(req.getParameter("age")));
        try {
            studentDao.addStudent(stu);
            resp.getWriter().write("true");
        } catch (SQLException e) {
            resp.getWriter().write("false");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
