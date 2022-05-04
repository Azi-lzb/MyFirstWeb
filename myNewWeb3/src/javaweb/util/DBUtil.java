package javaweb.util;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private static ResourceBundle rb = ResourceBundle.getBundle("javaweb.jdbc");
    private static String driver = rb.getString("driver");
    private static String url = rb.getString("url");
    private static String username = rb.getString("username");
    private static String password = rb.getString("password");
    private static Connection conn=null;
    static {
        try {
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        conn = DriverManager.getConnection(url,username,password);
        return conn;
    }
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        if (rs != null) {
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try{
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try{
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
