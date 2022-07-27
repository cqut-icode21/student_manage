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


@WebServlet("/getData")
public class GetDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        StringBuffer url = req.getRequestURL();
        String temp = req.getParameter("currentPage");
        int currenPage = Integer.parseInt(temp);

        JSONObject json = new JSONObject();
        BaseDaoImp getData = new BaseDaoImp();

        int allTr = getData.allTr();
        ArrayList<Students> data = getData.getData(currenPage, allTr);

        json.put("allTr", allTr);
        json.put("data", data);

        resp.getWriter().write(json.toString());
        System.out.println("完成");

    }
}
