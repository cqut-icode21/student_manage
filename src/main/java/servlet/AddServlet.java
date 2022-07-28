package servlet;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.util.BeanUtils;
import dao.impl.DatabaseReflect;
import enties.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        Student student = new Student();
        String id =req.getParameter("number");

        DatabaseReflect databaseReflect = new DatabaseReflect();
        Student student1 = (Student) databaseReflect.findById(Student.class,id);

        if (student1.getNumber() == null) {   //如果student不存在则添加

            student.setNumber(id);
            student.setName(req.getParameter("name"));
            student.setAge(req.getParameter("age"));
            student.setCla(req.getParameter("cla"));
            student.setCollege(req.getParameter("college"));
            student.setGender(req.getParameter("gender"));
            student.setMajor(req.getParameter("major"));
            student.setGrade(req.getParameter("grade"));

            databaseReflect.add(student);

            resp.getWriter().write("true");
        }else resp.getWriter().write("false");
    }
}
