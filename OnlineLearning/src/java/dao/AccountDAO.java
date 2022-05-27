/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Role;

/**
 *
 * @author midni
 */
public class AccountDAO extends DBContext {

    public void editProfile(Account acc) {
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [FirstName] = ?\n"
                + "      ,[LastName] = ?\n"
                + "      ,[ProfilePictureUrl] = ?\n"
                + "      ,[ModifiedTime] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Gender] = ?\n"
                + " WHERE AccountID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, acc.getFirstName());
            stm.setString(2, acc.getLastName());
            stm.setString(3, acc.getProfilePictureUrl());
            stm.setTimestamp(4, timestamp);
            stm.setString(5, acc.getPhone());
            stm.setString(6, acc.getAddress());
            stm.setBoolean(7, acc.isGender());
            stm.setInt(8, acc.getAccountId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public Account getAccount(String email, String password) {
        try {
            String sql = "SELECT a.*, p.PasswordHash FROM [Account] a JOIN [Password] p \n"
                    + "ON a.AccountID = p.AccountID\n"
                    + "WHERE [Email] = ? AND [PasswordHash] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                Role role = new Role();

                role.setId(rs.getInt("RoleID"));

                account.setAccountId(rs.getInt("AccountID"));
                account.setFirstName(rs.getString("FirstName"));
                account.setLastName(rs.getString("LastName"));
                account.setEmail(rs.getString("Email"));
                account.setProfilePictureUrl(rs.getString("ProfilePictureUrl"));
                account.setRole(role);
                account.setCreatedTime(rs.getTimestamp("CreatedTime"));
                account.setModifiedTime(rs.getTimestamp("ModifiedTime"));
                account.setPhone(rs.getString("Phone"));
                account.setAddress(rs.getString("Address"));
                account.setGender(rs.getBoolean("Gender"));
                account.setPassword(rs.getString("PasswordHash"));

                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
