package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
protected Connection connection;
    public DBContext()
    {
        try {
            String user = "sa";
            String pass = "hoangdung1110";
            String url = "jdbc:sqlserver://HOANGDUNG\\SQLEXPRESS:1433;databaseName=Online_Learning_Temp";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
        }
    }
}