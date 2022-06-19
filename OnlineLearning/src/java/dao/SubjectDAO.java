package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Subject;
import model.SubjectCategory;

public class SubjectDAO extends DBContext {

    public ArrayList<Subject> getAllSubjects() {
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            String sql = "select * from [subject]";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                SubjectCategory sc = new SubjectCategory();
                sc.setCategoryID(rs.getInt("CategoryID"));
                
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));
                subject.setName(rs.getString("Name"));           
                subject.setCategoryID(sc);
                subject.setFeatured(rs.getBoolean("Featured"));
                subject.setStatus(rs.getBoolean("Status"));
                subject.setImage(rs.getString("Image"));
                subject.setDescription(rs.getString("Description"));
                subject.setOrder(rs.getInt("Order"));
                subject.setType(rs.getString("type"));
                
                subjects.add(subject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public void insertSubject(Subject s) {
        String sql = "INSERT INTO [Subject]\n"
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
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void updateSubject(Subject s) {
        String sql = "UPDATE [Subject]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Order] = ?\n"
                + "      ,[Status] = ?\n"
                + "      ,[type] = ?\n"
                + " WHERE [SubjectID] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(5, s.getSubjectId());
            stm.setString(1, s.getName());
            stm.setInt(2, s.getOrder());
            stm.setBoolean(3, s.isStatus());
            stm.setString(4, s.getType());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void deleteSubject(int id) {
        String sql = "DELETE Subject"
                + " WHERE SubjectID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public Subject getSubjectLast() {
        try {
            String sql = "SELECT * FROM Subject WHERE SubjectID = (SELECT MAX(SubjectID) FROM Subject)";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));
                subject.setOrder(rs.getInt("Order"));
                subject.setStatus(rs.getBoolean("Status"));
                subject.setName(rs.getString("Name"));
                subject.setType(rs.getString("type"));

                return subject;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        ArrayList<Subject> list = new SubjectDAO().getAllSubjects();
        for (Subject subject : list) {
            System.out.println(subject.getName());
        }
    }
}
