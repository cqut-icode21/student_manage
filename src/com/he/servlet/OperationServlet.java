package com.he.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.he.entity.Student;
import com.he.service.DataService;
import com.he.service.impl.OperationServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 13253
 * @date 2021/7/29 20:27
 * @className OperationServlet
 */

@WebServlet("/operation")
public class OperationServlet extends HttpServlet {
    private final DataService<Student> DATA_SERVICE = new OperationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action=req.getParameter("action");
        if (action.equals("find"))
            find(req,resp);
        if (action.equals("add"))
            add(req,resp);
        if (action.equals("delete"))
            delete(req,resp);
        if (action.equals("see"))
            see(req,resp);
        if (action.equals("set"))
            set(req,resp);
    }

    private void set(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String num=req.getParameter("id");
        String name=req.getParameter("set-name");
        String sex=req.getParameter("set-sex");
        String college=req.getParameter("set-college");
        String major=req.getParameter("set-major");
        String grade=req.getParameter("set-grade");
        String clasS=req.getParameter("set-class");
        String age=req.getParameter("set-age");
        Student student=new Student(num,name,sex,college,major,grade,clasS,age);
        Boolean a=DATA_SERVICE.setStudent(student,num);
        resp.getWriter().write(String.valueOf(a));
    }

    private void see(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String get=req.getParameter("seeInfo");
        Student student=DATA_SERVICE.findStudent(new Student(),get);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", student);
        resp.getWriter().write(jsonObject.toString());
    }

    protected void find(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        List<Student> data = DATA_SERVICE.findData(currentPage);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", data);
        resp.getWriter().write(jsonObject.toString());
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String num=req.getParameter("add-num");
        String name=req.getParameter("add-name");
        String sex=req.getParameter("add-sex");
        String college=req.getParameter("add-college");
        String major=req.getParameter("add-major");
        String grade=req.getParameter("add-grade");
        String clasS=req.getParameter("add-class");
        String age=req.getParameter("add-age");
        Student student=new Student(num,name,sex,college,major,grade,clasS,age);
        Boolean a=DATA_SERVICE.addStudent(student);
        resp.getWriter().write(String.valueOf(a));
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String get=req.getParameter("deleteInfo");
        boolean a=DATA_SERVICE.deleteStudent(new Student(),get);
        resp.getWriter().write(String.valueOf(a));
    }
}
