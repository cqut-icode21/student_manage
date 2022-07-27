package reflect.servlet;


import reflect.entities.Student;
import reflect.impl.BaseDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet("/updateReq")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        ArrayList<String> aStudent = new ArrayList<>();
        aStudent.add(req.getParameter("id"));
        aStudent.add(req.getParameter("name"));
        aStudent.add(req.getParameter("sex"));
        aStudent.add(req.getParameter("college"));
        aStudent.add(req.getParameter("professional"));
        aStudent.add(req.getParameter("grade"));
        aStudent.add(req.getParameter("clazz"));
        aStudent.add(req.getParameter("age"));
        BaseDaoImpl baseDao = new BaseDaoImpl();
        baseDao.update(Student.class, aStudent);
    }

}