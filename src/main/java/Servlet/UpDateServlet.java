package Servlet;

import dao.UpDate;
import dao.UpDateImpl;
import entities.Student;
import service.FileService;
import service.FileServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/update")
public class UpDateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String id=req.getParameter("id");
        String name=req.getParameter("name");
        String gender=req.getParameter("gender");
        String college=req.getParameter("college");
        String major=req.getParameter("major");
        String grade=req.getParameter("grade");
        String class1=req.getParameter("class");
        Integer age= Integer.parseInt(req.getParameter("age"));

        Student student=new Student(id,name,gender,college,major,grade,class1,age);
        FileService fs=new FileServiceImpl();
        fs.update(student,id);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
