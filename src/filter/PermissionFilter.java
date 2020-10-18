package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "permissionCheckFilter",urlPatterns = "/*.jsp")
public class PermissionFilter implements Filter {
    private String notCheckPath;
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("运行过滤器");
        //需要HttpServletRequest类型参数，需要将父类Servlet类型参数转换
        HttpServletRequest request=(HttpServletRequest) req;
        //获取请求的url-pattern地址
        String path=request.getServletPath();
        System.out.println("请求地址url-pattern:"+path);
        //若请求地址不在不需过滤的列表范围内，需要判断是否已经登录
        if(!notCheckPath.contains(path)){
            HttpSession session=request.getSession();
            if(session.getAttribute("currentUser")==null){//没有登录
                request.setAttribute("errorInfo","没有权限访问");
                request.getRequestDispatcher("/error.jsp").forward(request,resp);
            } else{ //已经登录，直接放行
                chain.doFilter(req,resp);
            }
        } else{ //请求地址在不需过滤的列表范围内，直接放行
            chain.doFilter(req,resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
        notCheckPath=config.getInitParameter("notCheckPath") ;
    }

}
