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

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        List<Object> students=null;
        Student student = SinglePeople.getStudent();
        student=filledStudent(request,student);
        try {
            DateBaseReact dateBaseReact=new DateBaseReact();
            dateBaseReact.add(student);
            students=dateBaseReact.findAll(student.getClass());
            String s= JSON.toJSONString(students);
            response.getWriter().write(s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
   public Student filledStudent(HttpServletRequest request,Student student){
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