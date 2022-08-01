package ljq.servlet;

import com.alibaba.fastjson.JSONObject;
import ljq.dao.impl.BaseDao;
import ljq.dao.impl.DataBase;
import ljq.entity.Students;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        String str=req.getParameter("data");
        DataBase search=new DataBase();
        ArrayList<Students> list=search.search(Students.class,str);
        int length = list.size();
        JSONObject json = new JSONObject();
        json.put("students", list);
        json.put("length", length);
        resp.getWriter().write(json.toString());
    }
}
