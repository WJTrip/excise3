package filter;

import dao.UserDao;
import vo.User;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    private String notCheckPath;
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        String path=request.getServletPath();
        User user=null;
        if(!notCheckPath.contains(path)){
            Cookie[] cookies=request.getCookies();
            String username=null,password=null;
            HttpSession session=request.getSession();
            if(session.getAttribute("currentUser")==null){
                for (Cookie cookie : cookies) {
                    if("username".equals(cookie.getName())){
                        username=cookie.getValue();
                    }
                    else if("password".equals(cookie.getName())){
                        password=cookie.getValue();
                    }
                }
                try {
                    user=new UserDao().get(username);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(user!=null){
                    session.setAttribute("currentUser",user);
                    request.setAttribute("currentUserName",user.getChrName());
                }
                else {
                    request.setAttribute("errorInfo","您还没有登录");
                    request.getRequestDispatcher("/error.jsp").forward(request,response);
                }
            }
            else{
                user= (User) session.getAttribute("currentUser");
                request.setAttribute("currentUserName",user.getChrName());
                chain.doFilter(request,response);
            }
        }
        else{
            chain.doFilter(request,response);
        }

    }

    public void init(FilterConfig config) throws ServletException {
        notCheckPath=config.getInitParameter("notCheckPath");
    }

}
