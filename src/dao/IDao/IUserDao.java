package dao.IDao;

import vo.User;

import java.sql.SQLException;

public interface IUserDao {
    User get(String username) throws SQLException;
}
