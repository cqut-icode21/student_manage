package zhz.servlet;


import com.alibaba.fastjson.JSONObject;
import zhz.dao.imp.BaseDaoImp;
import zhz.pojo.Student;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * @author 龙
 */

@WebServlet("/getData")
public class GetDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("进入");
        JSONObject json = new JSONObject();
        BaseDaoImp getData = new BaseDaoImp();
        StringBuffer url = req.getRequestURL();
        System.out.println(url);
        int allTr = getData.allTr();
        ArrayList<Student> data = getData.getData();
        json.put("allTr", allTr);
        json.put("data", data);
        resp.getWriter().write(json.toString());
        System.out.println("完成");
        System.out.println("------------------");
        System.out.println("------------------");
    }

}