package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
protected Connection connection;
    public DBContext()
    {
        try {
            String user = "ducdn";
            String pass = "2001";
            String url = "jdbc:sqlserver://MSINHATDUC\\SQLEXPRESS:1433;databaseName=Online_Learn2001";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
        }
    }
}