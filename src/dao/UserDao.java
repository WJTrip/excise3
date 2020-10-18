package dao;

import dao.IDao.IUserDao;
import tools.JdbcUtil;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements IUserDao {

    @Override
    public User get(String userName) throws SQLException {
        User user =null;
        Connection con= JdbcUtil.getConnection();
        //3。创建语句
        String sql="select * from t_user where userName=?";
        PreparedStatement pst=con.prepareStatement(sql);
        //4.执行语句
        pst.setString(1,userName);
        ResultSet rs=pst.executeQuery();
        //5.响应处理
        if(rs.next()){
            user = new User(rs.getString("userName"),rs.getString("passWord"),
                    rs.getString("chrName"),rs.getString("role"));
        }
        //6.关闭连接
        con.close();
        return user;
    }
}
