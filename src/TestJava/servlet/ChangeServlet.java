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
import java.util.List;

@WebServlet("/change")
public class ChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        List<Object> students=null;
        Integer id= Integer.valueOf(req.getParameter("id"));
        Student student = SinglePeople.getStudent();
        student=filledStudent(req,student);
        DateBaseReact dateBaseReact=new DateBaseReact();
        try {
            if(dateBaseReact.update(student,id)){
                students=dateBaseReact.findAll(student.getClass());
                String s= JSON.toJSONString(students);
                resp.getWriter().write(s);
            };
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Student filledStudent(HttpServletRequest request, Student student){
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        String gender = request.getParameter("gender");
        String college = request.getParameter("college");
        String major = request.getParameter("major");
        String clazz = request.getParameter("class");
        String age = request.getParameter("age");
        String grade = request.getParameter("grade");
        student.setName(name);
        student.setMajor(major);
        student.setGender(gender);
        student.setGrade(grade);
        student.setAge(age);
        student.setCollege(college);
        student.setNumber(Integer.parseInt(number));
        student.setClass(clazz);
        return student;
    }
}
