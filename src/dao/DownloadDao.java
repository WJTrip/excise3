package dao;

import dao.IDao.IDowloadDao;
import tools.JdbcUtil;
import vo.Download;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DownloadDao implements IDowloadDao {
    @Override
    public Download get(String id) throws SQLException {
        Download download=null;
        Connection con= JdbcUtil.getConnection();
        String sql="select * from t_downloadlist where id=?";
        PreparedStatement pst=con.prepareStatement(sql);
        pst.setString(1,id);
        ResultSet rs=pst.executeQuery();
        //5.响应处理
        if(rs.next()){
            download = new Download(rs.getString("id"),rs.getString("name"),
                    rs.getString("path"),rs.getString("description"),
                    rs.getString("size"),rs.getString("start"),
                    rs.getString("image"));
        }
        //6.关闭连接
        con.close();
        return download;
    }
}
