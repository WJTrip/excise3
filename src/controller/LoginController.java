package controller;


import dao.UserDao;
import vo.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;


@WebServlet(urlPatterns ="/LoginController")
public class LoginController extends HttpServlet {
//    private UserDao userDao=new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String userName=request.getParameter("userName");
        String passWord=request.getParameter("passWord");
        //用户输入的验证码
        String VCode=request.getParameter("userCode");
//        System.out.println(VCode);
        //跳转页面地址
        String forwardPath="";
        HttpSession session=request.getSession();
        //系统生成的验证码
        String saveCode=(String)session.getAttribute("verityCode");
//        System.out.println(saveCode);
        if(!VCode.equalsIgnoreCase(saveCode)){ //验证码错误
//            System.out.println(VCode);
//            System.out.println(saveCode);
            request.setAttribute("info","验证码错误");
            forwardPath="/error.jsp";
        } else{//正确
            UserDao dao=new UserDao();
            User user= null;
            try {
                user = dao.get(userName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(user==null){
                request.setAttribute("info","用户名不存在");
                forwardPath="/error.jsp";
            } else{//用户名存在

                if(!user.getPassWord().equals(passWord)){//密码错误
                    request.setAttribute("info","密码错误");
                    forwardPath="/error.jsp";
                }
                else{//密码正确
                    session.setAttribute("currentUserName",user.getChrName());
                    try {
                        session.setAttribute("currentUser",dao.get(userName));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    forwardPath="/main.jsp";
                    if(request.getParameter("checkbox").equals("true"))
                    {
                        userName= URLEncoder.encode(userName,"utf-8");
                        Cookie username_cookie=new Cookie("username",userName);
                        Cookie password_cookie=new Cookie("password",passWord);
                        username_cookie.setMaxAge(60*60*24*7);
                        password_cookie.setMaxAge(60*60*24*7);
                        username_cookie.setPath(request.getContextPath());
                        password_cookie.setPath(request.getContextPath());
                        response.addCookie(username_cookie);
                        response.addCookie(password_cookie);
//                        System.out.println(request.getParameter("checkbox"));
                    }
                }

            }
        }
//        System.out.println(forwardPath);
//        System.out.println(request.getAttribute("errorInfo"));
        RequestDispatcher rd=request.getRequestDispatcher(forwardPath);
        rd.forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("as");
        doPost(request,response);

    }
}
