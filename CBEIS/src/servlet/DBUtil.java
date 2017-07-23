package servlet;

import java.sql.*;

public class DBUtil {
	// table  
    public static final String TABLE_NAME = "crawler_data";  
  
    // connect to MySql database  
    public static Connection getConnect() {  
        String url = "jdbc:mysql://localhost:3306/cbeis"; // ���ݿ��Url  
        Connection connecter = null;  
        try {  
            Class.forName("com.mysql.jdbc.Driver"); // java���䣬�̶�д��  
            connecter = (Connection) DriverManager.getConnection(url, "root", "root");  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            System.out.println("SQLException: " + e.getMessage());  
            System.out.println("SQLState: " + e.getSQLState());  
            System.out.println("VendorError: " + e.getErrorCode());  
        }  
        return connecter;  
    }
}
