package zhz.servlet;


import zhz.dao.imp.BaseDaoImp;
import zhz.pojo.Student;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @author 龙
 */
@WebServlet("/deleteData")
public class DeleteDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        ArrayList<String> id = new ArrayList<>();

        id.add(req.getParameter("id"));
        BaseDaoImp delete = new BaseDaoImp();
        delete.delete(Student.class, id.get(0));
    }
}
