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
import java.util.List;

@WebServlet("/scout")
public class ScoutStudent extends HttpServlet {
    private StudentDao studentDao = new StudentDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String inf = req.getParameter("inf");
        System.out.println(inf);
        try {
            List<Student> information = studentDao.scout(inf);
            System.out.println(information);
            String json = JSON.toJSONString(information);
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(json);
        } catch (SQLException e) {
            resp.getWriter().write("{}");
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
