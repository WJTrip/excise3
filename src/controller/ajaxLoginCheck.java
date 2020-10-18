package controller;

import com.google.gson.Gson;
import dao.UserDao;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/ajaxLoginCheck")
public class ajaxLoginCheck extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求参数编码格式为utf-8 防止中文参数乱码
        request.setCharacterEncoding("utf-8");
        //1.按照表单的个元素的name属性值获取各请求参数值
        String userName=request.getParameter("userName");
        String passWord=request.getParameter("passWord");
        String userCode=request.getParameter("userCode");
        String checkBox=request.getParameter("checkbox");
        //2.获取HttpSession对象
        HttpSession session=request.getSession();
        //取出CreatVerifyImageController中存放的验证码字符串
        String VCode= (String) session.getAttribute("verityCode");
        //存放返回信息的Map
        Map<String,Object> map= new HashMap<String, Object>();
        //创建user对象
        User user=null;
        //比较输入的验证码和随机生成的验证码是否相同
        if (!userCode.equalsIgnoreCase(VCode)) {//不同
            //在map中存放返回数据
            map.put("code",1);
            map.put("info","验证码不正确！");
        }else{//验证码正确
            UserDao dao=new UserDao();
            try {
                user=dao.get(userName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (user==null) {//用户名不存在
                map.put("code",2);
                map.put("info","用户名不存在！");
            }else{//用户名存在
                if (!user.getPassWord().equals(passWord)) {//密码不正确
                    map.put("code",3);
                    map.put("info","密码不正确！");
                }else{//密码正确
                    //将需要传递的数据存放在session域范围中，一个绘画阶段的所有程序都可以从中获取
                    session.setAttribute("currentUser",user);
                    if(checkBox!=null){//自动登录选中
                        //设置cookie
                        Cookie userName_cookie=new Cookie("userName",userName);
                        Cookie passWord_cookie=new Cookie("passWord",passWord);
                        userName_cookie.setMaxAge(60*60*24*7); //设置有效期为7天
                        passWord_cookie.setMaxAge(60*60*24*7); //设置有效期为7天
                        response.addCookie(userName_cookie);
                        response.addCookie(passWord_cookie);
                    }
                    map.put("code",0);
                    map.put("info","登录成功");
                }
            }
        }

        //调用谷歌的Gson库将map类型数据转换为json字符串
        String jsonStr=new Gson().toJson(map);
        //字符串流输出字符串
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
