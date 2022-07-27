package servlet;

import com.alibaba.fastjson.JSONObject;
import dao.imp.BaseDaoImp;
import message.Students;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        String str = req.getParameter("data");
        BaseDaoImp search = new BaseDaoImp();
        ArrayList<Students> students = search.search(Students.class, str);
        JSONObject json = new JSONObject();
        json.put("data", students);
        resp.getWriter().write(json.toString());
    }
}
