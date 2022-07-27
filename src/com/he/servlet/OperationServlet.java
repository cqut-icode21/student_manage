package com.he.servlet;

import com.alibaba.fastjson.JSONObject;
import com.he.entity.Student;
import com.he.service.DataService;
import com.he.service.impl.OperationServiceImpl;

import javax.servlet.ServletException;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        System.out.println("进入了得到方法");
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        List<Student> data = DATA_SERVICE.findData(currentPage);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", data);
        resp.getWriter().write(jsonObject.toString());
        System.out.println("完成");
    }
}
