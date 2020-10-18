package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    private static String driver="com.mysql.jdbc.Driver";
//    private static String url="jdbc:mysql:///:3306:excise?unicode=true&character=utf-8";
    private static String url="jdbc:mysql:///excise";
    private static String user="root";
    private static String password="123456";
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("exception");
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url,user,password);
    }
    public static void close(Connection connection)throws Exception{
        if (connection != null) {
            connection.close();
        }

    }
}
