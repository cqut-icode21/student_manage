package servlet;

import dao.impl.DatabaseReflect;
import enties.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/change")
public class ChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        String id =req.getParameter("number");

        DatabaseReflect databaseReflect = new DatabaseReflect();

        Student student = new Student();  //添加学生
        student.setNumber(id);
        student.setName(req.getParameter("name"));
        student.setAge(req.getParameter("age"));
        student.setCla(req.getParameter("cla"));
        student.setCollege(req.getParameter("college"));
        student.setGender(req.getParameter("gender"));
        student.setMajor(req.getParameter("major"));
        student.setGrade(req.getParameter("grade"));

        if (databaseReflect.update(student,id)){  //修改成功
            resp.getWriter().write("true");
    }else resp.getWriter().write("false");


    }
}
