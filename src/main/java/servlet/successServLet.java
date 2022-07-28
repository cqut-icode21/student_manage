package servlet;

import com.alibaba.fastjson.JSON;
import dao.impl.DatabaseReflect;
import enties.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/successServLet")
public class successServLet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        //获取所有数据
        DatabaseReflect databaseReflect = new DatabaseReflect();
        ArrayList<Student> list = (ArrayList<Student>) databaseReflect.findAll(Student.class);

        resp.getWriter().write(JSON.toJSONString(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
