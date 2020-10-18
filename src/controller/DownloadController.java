package controller;

import dao.DownloadDao;
import vo.Download;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/DownloadController")
public class DownloadController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id= request.getParameter("id");
        String path=null;
        Download download=null;
        DownloadDao dao=new DownloadDao();
        try {
            download=dao.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert download != null;
        path=request.getServletContext().getRealPath(download.getPath());
        String fileName=path.substring(path.lastIndexOf("\\")+1);
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
        InputStream in =new FileInputStream(path);
        int len=0;
        byte[] buffer=new byte[1024];
        ServletOutputStream out =response.getOutputStream();
        while((len=in.read(buffer))!= -1){
            out.write(buffer,0,len);
        }

        in.close();;
        out.close();
    }
}
