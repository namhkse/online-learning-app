package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContext {
protected Connection connection;
    public DBContext()
    {
        try {
            String user = "votai";
            String pass = "123456";
            String url = "jdbc:sqlserver://TAI:1433;databaseName=Online_Learning_Temp";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}