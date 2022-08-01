package ljq.servlet;

import ljq.dao.impl.BaseDao;
import ljq.dao.impl.DataBase;
import ljq.entity.Students;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/updateReq")
public class UpdateDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        ArrayList<String> student=new ArrayList<>();
        student.add(req.getParameter("id"));
        student.add(req.getParameter("name"));
        student.add(req.getParameter("sex"));
        student.add(req.getParameter("college"));
        student.add(req.getParameter("professional"));
        student.add(req.getParameter("grade"));
        student.add(req.getParameter("clazz"));
        student.add(req.getParameter("age"));
        DataBase update=new DataBase();
        update.update(Students.class,student);
        System.out.println(student.toString());
    }
}
