package reflect.servlet;

import com.alibaba.fastjson.JSONObject;
import reflect.entities.Student;
import reflect.impl.BaseDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        String str = req.getParameter("data");
        BaseDaoImpl baseDao = new BaseDaoImpl();
        ArrayList<Student> students = baseDao.search(Student.class, str);
        int length = students.size();
        JSONObject json = new JSONObject();
        json.put("students", students);
        json.put("length", length);
        resp.getWriter().write(json.toString());
    }
}
