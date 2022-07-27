package Servlet;

import com.alibaba.fastjson.JSONObject;
import dao.FileDao;
import dao.FileDaoImpl;
import entities.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@WebServlet("/search")
public class FindServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");

        String v=req.getParameter("v");
        FileDao fd=new FileDaoImpl();
        List<Student> person=fd.getStudent(v);

        JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
        jsonObject.put("data", person);
        resp.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }



}
