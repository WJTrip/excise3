package controller;

import com.google.gson.Gson;
import dao.IDao.ProvinceCityDao;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/queryProvinceCity")
public class queryProvinceCity extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String provinceCode=request.getParameter("provinceCode");
        String jsonStr="";
        ProvinceCityDao dao=new ProvinceCityDao();
        if(provinceCode==null){
            ArrayList<province> list=dao.queryProvince();
            jsonStr=new Gson().toJson(list);
        }else{
            ArrayList<City> list=dao.queryCity(provinceCode);
            jsonStr =new Gson().toJson(list);
            PrintWriter out=response.getWriter();
            System.out.println(jsonStr);
            out.print(jsonStr);
            out.flush();
            out.close();
        }

        response.setContentType("text/html;charset=utf-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
