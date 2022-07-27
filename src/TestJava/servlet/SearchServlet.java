package TestJava.servlet;

import TestJava.dao.DateBaseReact;
import TestJava.people.SinglePeople;
import TestJava.people.Student;
import com.alibaba.fastjson2.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        List<Object> students=null;
        List<Object> findStudent=new ArrayList<>();
        String value=req.getParameter("Value");
        System.out.println(value);
        Student student = SinglePeople.getStudent();
        DateBaseReact dateBaseReact=new DateBaseReact();
        try {
            students=dateBaseReact.findAll(student.getClass());
            for (Object i: students  ) {
                Student stu=(Student) i;
                if(stu.getAge().matches(value)||stu.getName().matches(value)||String.valueOf(stu.getNumber()).matches(value)
                        ||stu.getGender().matches(value)|| stu.getGrade().matches(value)||stu.getClasses().matches(value)||
                        stu.getCollege().matches(value) ||stu.getMajor().matches(value)){
                        findStudent.add(i);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String s= JSON.toJSONString(findStudent);
        System.out.println(s);
        resp.getWriter().write(s);
    }
}
