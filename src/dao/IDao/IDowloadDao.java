package dao.IDao;

import vo.Download;

import java.sql.SQLException;

public interface IDowloadDao {
    Download get(String id) throws SQLException;
}
