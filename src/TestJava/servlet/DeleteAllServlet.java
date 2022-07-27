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

@WebServlet("/deleteAll")
public class DeleteAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         resp.setContentType("text/html");
         resp.setCharacterEncoding("utf-8");
         String[] ids=req.getParameterValues("array[]");
        Student student= SinglePeople.getStudent();
        List<Object> students=null;
        DateBaseReact dateBaseReact=new DateBaseReact();
        for (String id: ids) {
            try {
                dateBaseReact.delete(student.getClass(),Integer.parseInt(id));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            students=dateBaseReact.findAll(student.getClass());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String s= JSON.toJSONString(students);
        resp.getWriter().write(s);
    }
}
