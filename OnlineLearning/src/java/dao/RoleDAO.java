package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Role;

public class RoleDAO extends DBContext {

    private void mapping(Role role, ResultSet rs) throws SQLException {
        role.setId(rs.getInt("RoleID"));
        role.setName(rs.getString("Name"));
        role.setOrder(rs.getInt("Order"));
        if (rs.getInt("Status") == 1) {
            role.setStatus(true);
        } else {
            role.setStatus(false);
        }
    }

    public ArrayList<Role> findAll() throws SQLException {
        ArrayList<Role> roles = new ArrayList<>();
        String sql = "select [RoleID], [Name], [Order], Status from Role";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Role role = new Role();
                mapping(role, rs);
                roles.add(role);
            }
        }

        return roles;
    }

    public HashMap<Integer, Role> getRoleTable() throws SQLException {
        ArrayList<Role> ls = findAll();
        HashMap<Integer, Role> hm = new HashMap<>();
        for (Role r : ls) {
            hm.put(r.getId(), r);
        }
        return hm;
    }

    public Role find(int id) throws SQLException {
        String sql = "select [RoleID], [Name], [Order], Status from Role where [RoleID] = ?";
        Role role = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                role.setId(rs.getInt("RoleID"));
                role.setName(rs.getString("Name"));
                role.setOrder(rs.getInt("Order"));
                if (rs.getInt("Status") == 1) {
                    role.setStatus(true);
                } else {
                    role.setStatus(false);
                }
            }
        }
        return role;
    }

    public Role findByName(String name) {
        String sql = "select [RoleID], [Name] from Role where [Name] = ?";
        Role role = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                role = new Role(rs.getInt("RoleID"), rs.getString("Name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return role;
    }
    
    public void insertRole(Role s) {
        String sql = "INSERT INTO [Role]\n"
                + "           ([Name]\n"
                + "           ,[Order]\n"
                + "           ,[Status]\n"
                + "           ,[type])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, s.getName());
            stm.setInt(2, s.getOrder());
            stm.setBoolean(3, s.isStatus());
            stm.setString(4, s.getType());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void updateRole(Role s) {
        String sql = "UPDATE [Role]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Order] = ?\n"
                + "      ,[Status] = ?\n"
                + "      ,[type] = ?\n"
                + " WHERE [RoleID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(5, s.getId());
            stm.setString(1, s.getName());
            stm.setInt(2, s.getOrder());
            stm.setBoolean(3, s.isStatus());
            stm.setString(4, s.getType());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void deleteRole(int id) {
        String sql = "DELETE Role"
                + " WHERE RoleID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public Role getRoleLast() {
        try {
            String sql = "SELECT * FROM Role WHERE RoleID = (SELECT MAX(RoleID) FROM Role)";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("RoleID"));
                role.setOrder(rs.getInt("Order"));
                role.setStatus(rs.getBoolean("Status"));
                role.setName(rs.getString("Name"));
                role.setType(rs.getString("type"));

                return role;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
