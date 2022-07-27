package zhz.servlet;

import com.alibaba.fastjson.JSONObject;
import zhz.dao.imp.BaseDaoImp;
import zhz.pojo.Student;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author 龙
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        String str = req.getParameter("str");
        BaseDaoImp search = new BaseDaoImp();
        ArrayList<Student> students = search.search(Student.class, str);
        JSONObject json = new JSONObject();
        json.put("data", students);
        try {
            resp.getWriter().write(json.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
