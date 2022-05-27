/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author midni
 */
public class PasswordDAO extends DBContext {

    public void changPassword(int accountID, String newPassword) {
        String sql = "UPDATE [dbo].[Password] SET [PasswordHash] = ? WHERE AccountID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, newPassword);
            stm.setInt(2, accountID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PasswordDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasswordDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PasswordDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
