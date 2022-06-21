package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Subject;
import model.SubjectCategory;

public class SubjectDAO extends DBContext {

    public ArrayList<Subject> getAllSubjects() {
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT Subject.*, Account.FirstName, Account.LastName, SubjectCategory.Name CategoryName\n"
                    + "FROM [Subject] JOIN Account ON Subject.OwnerID = Account.AccountID\n"
                    + "JOIN SubjectCategory ON Subject.CategoryID = SubjectCategory.CategoryID\n"
                    + "ORDER BY SubjectID DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));
                subject.setName(rs.getString("Name"));

                SubjectCategory category = new SubjectCategory();
                category.setCategoryID(rs.getInt("CategoryID"));
                category.setName(rs.getString("CategoryName"));

                subject.setCategoryID(category);
                subject.setFeatured(rs.getBoolean("Featured"));
                subject.setStatus(rs.getBoolean("Status"));
                subject.setImage(rs.getString("Image"));
                subject.setDescription(rs.getString("Description"));

                Account account = new Account();
                account.setAccountID(rs.getInt("OwnerID"));
                account.setFirstName(rs.getString("FirstName"));
                account.setLastName(rs.getString("LastName"));
                subject.setOwnerID(account);

                subject.setOrder(rs.getInt("Order"));
                if (rs.getInt("Status") == 1) {
                    subject.setStatus(true);
                } else {
                    subject.setStatus(false);
                }
                subjects.add(subject);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public ArrayList<Subject> getSubjectsByCategoryAndStatus(String[] categoryID, String status) {
        ArrayList<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT Subject.*, Account.FirstName, Account.LastName, SubjectCategory.Name CategoryName\n"
                    + "FROM [Subject] JOIN Account ON Subject.OwnerID = Account.AccountID\n"
                    + "JOIN SubjectCategory ON Subject.CategoryID = SubjectCategory.CategoryID\n";
            if (categoryID != null && status == null) {
                sql += "WHERE ";
                for (int i = 0; i < categoryID.length; i++) {
                    if (i == categoryID.length - 1) {
                        sql += "Subject.CategoryID = ? ";
                    } else {
                        sql += "Subject.CategoryID = ? OR ";
                    }
                }
            } else if (categoryID != null && status != null) {
                sql += "WHERE (";
                for (int i = 0; i < categoryID.length;) {
                    if (i == categoryID.length - 1) {
                        sql += "Subject.CategoryID = ? ";
                    } else {
                        sql += "Subject.CategoryID = ? OR ";
                    }
                    i++;
                }
                sql += ") AND Subject.Status = ?\n";
            } else if (categoryID == null && status != null) {
                sql += "WHERE Subject.Status = ?\n";
            }
            sql += "ORDER BY SubjectID DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (categoryID != null && status == null) {
                for (int i = 0; i < categoryID.length; i++) {
                    String get = categoryID[i];
                    stm.setInt(i + 1, Integer.parseInt(get));
                }
            } else if (categoryID != null && status != null) {
                int index = 1;
                for (int i = 0; i < categoryID.length; i++) {
                    String get = categoryID[i];
                    stm.setInt(index, Integer.parseInt(get));
                    index++;
                }
                stm.setBoolean(index, Boolean.parseBoolean(status));
            } else if (categoryID == null && status != null) {
                stm.setBoolean(1, Boolean.parseBoolean(status));
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));
                subject.setName(rs.getString("Name"));

                SubjectCategory category = new SubjectCategory();
                category.setCategoryID(rs.getInt("CategoryID"));
                category.setName(rs.getString("CategoryName"));

                subject.setCategoryID(category);
                subject.setFeatured(rs.getBoolean("Featured"));
                subject.setStatus(rs.getBoolean("Status"));
                subject.setImage(rs.getString("Image"));
                subject.setDescription(rs.getString("Description"));

                Account account = new Account();
                account.setAccountID(rs.getInt("OwnerID"));
                account.setFirstName(rs.getString("FirstName"));
                account.setLastName(rs.getString("LastName"));
                subject.setOwnerID(account);

                subject.setOrder(rs.getInt("Order"));
                if (rs.getInt("Status") == 1) {
                    subject.setStatus(true);
                } else {
                    subject.setStatus(false);
                }
                subjects.add(subject);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public ArrayList<Integer> countCourseForAllSubject(ArrayList<Subject> subjects) {
        ArrayList<Integer> numberCourse = new ArrayList<>();
        try {
            String sql = "SELECT Subject.SubjectID, COUNT(CourseID) NumberCourse "
                    + "FROM [Subject] LEFT JOIN SubjectCourse ON Subject.SubjectID = SubjectCourse.SubjectID "
                    + "GROUP BY Subject.SubjectID ";
            if (subjects.size() != 0) {
                sql += "HAVING ";
            }
            for (int i = 0; i < subjects.size(); i++) {
                if (i == subjects.size() - 1) {
                    sql += "Subject.SubjectID = ? ";
                } else {
                    sql += "Subject.SubjectID = ? OR ";
                }
            }
            sql += "ORDER BY Subject.SubjectID DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            for (int i = 0; i < subjects.size();) {
                Subject get = subjects.get(i);
                stm.setInt(i + 1, get.getSubjectId());
                i++;
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                numberCourse.add(rs.getInt("NumberCourse"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numberCourse;
    }

    public void insertSubject(Subject s) {
        try {
            String sql = "INSERT INTO [dbo].[Subject]\n"
                    + "           ([Name]\n"
                    + "           ,[CategoryID]\n"
                    + "           ,[Featured]\n"
                    + "           ,[Status]\n"
                    + "           ,[Image]\n"
                    + "           ,[Description]\n"
                    + "           ,[Order]\n"
                    + "           ,[type]\n"
                    + "           ,[OwnerID])\n"
                    + "     VALUES\n"
                    + "           (<Name, varchar(200),>\n"
                    + "           ,<CategoryID, int,>\n"
                    + "           ,<Featured, bit,>\n"
                    + "           ,<Status, bit,>\n"
                    + "           ,<Image, varchar(3000),>\n"
                    + "           ,<Description, nvarchar(2000),>\n"
                    + "           ,<Order, int,>\n"
                    + "           ,<type, varchar(200),>\n"
                    + "           ,<OwnerID, int,>)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getName());
            stm.setInt(2, s.getCategoryID().getCategoryID());
            stm.setBoolean(3, s.isFeatured());
            stm.setBoolean(4, s.isStatus());
            stm.setString(5, s.getImage());
            stm.setString(6, s.getDescription());
            stm.setInt(7, s.getOrder());
            stm.setString(8, s.getType());
            stm.setInt(9, s.getOwnerID().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSubject(Subject s) {
        try {
            String sql = "UPDATE [Subject]\n"
                    + "   SET [Name] = ?\n"
                    + "      ,[Order] = ?\n"
                    + "      ,[Status] = ?\n"
                    + "      ,[type] = ?\n"
                    + " WHERE [SubjectID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(5, s.getSubjectId());
            stm.setString(1, s.getName());
            stm.setInt(2, s.getOrder());
            stm.setBoolean(3, s.isStatus());
            stm.setString(4, s.getType());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteSubject(int id) {
        try {
            String sql = "DELETE Subject WHERE SubjectID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
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
}
