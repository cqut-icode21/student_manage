package servlet;

import dao.impl.DatabaseReflect;
import enties.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");

        String id =req.getParameter("number");

        DatabaseReflect databaseReflect = new DatabaseReflect();

        if (databaseReflect.delete(Student.class,id)){  //修改成功
            resp.getWriter().write("true");
        }else resp.getWriter().write("false");
    }
}
