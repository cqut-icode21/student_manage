package servlet;

import dao.imp.BaseDaoImp;
import message.Students;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteData")
public class DeleteDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] count = req.getParameterValues("count[]");
        BaseDaoImp delete = new BaseDaoImp();
        delete.delete(Students.class, count);
    }
}
