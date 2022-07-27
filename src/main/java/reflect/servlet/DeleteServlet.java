package reflect.servlet;


import reflect.entities.Student;
import reflect.impl.BaseDaoImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteData")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String[] count = req.getParameterValues("count[]");
        BaseDaoImpl baseDao = new BaseDaoImpl();
        baseDao.delete(Student.class, count);
    }
}
