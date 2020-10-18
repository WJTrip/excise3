package controller;

import dao.CreateImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet(urlPatterns ="/CreateVerifyImageController")
public class CreateVerifyImageController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.生成一个4位长度的字符串
        CreateImage createVCodeImage =new CreateImage();
        String vCode =createVCodeImage.createCode(); //写一个类和方法来实现
        //2.将生成的字符串保存在session范围中
        //获取session对象
        HttpSession session =request.getSession();
        //存放(存放在session中的数据在一个绘画范围内，多个程序全局共享)，以便验证
        session.setAttribute("verityCode",vCode);
        //设置返回的内容
        response.setContentType("img/jpeg");
        //浏览器不缓存响应内容————验证码图片，点一次就要刷新一次，所以不能有缓存出现
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        //设置验证码失效时间
        response.setDateHeader("Expires",0);
        //以字节流发过去，交给img的src书香去解析即可
        BufferedImage VCodeImg=createVCodeImage.CreateImage(vCode);
        ServletOutputStream out=response.getOutputStream();
        ImageIO.write(VCodeImg,"JPEG",out);
        out.flush();
        out.close();

    }
}
