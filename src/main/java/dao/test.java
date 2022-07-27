package dao;

import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;

import java.util.LinkedHashMap;

public class test {
    public static void main(String[] args) {
        FileDao fd=new FileDaoImpl();
        JSONObject jsonObject = new JSONObject(new LinkedHashMap<>());
        jsonObject.put("date",fd.getAllStudent());
        System.out.println(fd.getAllStudent());
        System.out.println("---------------------------");
        System.out.println(jsonObject);
    }
}
