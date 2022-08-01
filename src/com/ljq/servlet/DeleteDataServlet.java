package ljq.servlet;

import ljq.dao.impl.DataBase;
import ljq.entity.Students;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteReq")
public class DeleteDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String[] count = req.getParameterValues("count[]");
//        System.out.println("count"+count.length+count[1]+count[2]);
        DataBase delete=new DataBase();
        delete.delete(Students.class,count);
    }
}
