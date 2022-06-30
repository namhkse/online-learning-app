package dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.CoursePricePackage;
import model.PricePackage;
import model.Subject;

public class PricePackageDAO extends DBContext {

    public ArrayList<PricePackage> getAllPricePackages(int subjectID) {
        ArrayList<PricePackage> pricePackages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [PricePackage] WHERE SubjectID = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, subjectID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PricePackage pricePackage = new PricePackage();
                pricePackage.setPriceID(rs.getInt("PriceID"));
                pricePackage.setName(rs.getString("Name"));
                if (rs.getObject("AccessDuration") != null) {
                    pricePackage.setAccessDuration(rs.getInt("AccessDuration"));
                } else {
                    pricePackage.setAccessDuration(-1);
                }
                pricePackage.setStatus(rs.getBoolean("Status"));
                pricePackage.setListPrice(rs.getBigDecimal("ListPrice"));
                pricePackage.setSalePrice(rs.getBigDecimal("SalePrice"));
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));
                pricePackage.setSubjectID(subject);
                pricePackages.add(pricePackage);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pricePackages;
    }

    public PricePackage getPricePackageByID(int id) {
        try {
            String sql = "SELECT * FROM [PricePackage] WHERE PriceID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                PricePackage pricePackage = new PricePackage();
                pricePackage.setPriceID(rs.getInt("PriceID"));
                pricePackage.setName(rs.getString("Name"));
                if (rs.getObject("AccessDuration") != null) {
                    pricePackage.setAccessDuration(rs.getInt("AccessDuration"));
                } else {
                    pricePackage.setAccessDuration(-1);
                }
                pricePackage.setStatus(rs.getBoolean("Status"));
                pricePackage.setListPrice(rs.getBigDecimal("ListPrice"));
                pricePackage.setSalePrice(rs.getBigDecimal("SalePrice"));
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));
                pricePackage.setSubjectID(subject);
                return pricePackage;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateStatusPricePackage(int id, boolean status) {
        try {
            String sql = "UPDATE [PricePackage] SET [Status] = ? WHERE PriceID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletePricePackage(int id) {
        try {
            String sql = "DELETE FROM [PricePackage] WHERE PriceID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertPricePackage(PricePackage pricePackage) {
        try {
            String sql = "INSERT INTO [PricePackage]\n"
                    + "           ([Name]\n"
                    + "           ,[AccessDuration]\n"
                    + "           ,[Status]\n"
                    + "           ,[ListPrice]\n"
                    + "           ,[SalePrice]\n"
                    + "           ,[SubjectID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pricePackage.getName());
            if (pricePackage.getAccessDuration() == -1) {
                stm.setNull(2, Types.INTEGER);
            } else {
                stm.setInt(2, pricePackage.getAccessDuration());
            }
            stm.setBoolean(3, pricePackage.isStatus());
            stm.setBigDecimal(4, pricePackage.getListPrice());
            stm.setBigDecimal(5, pricePackage.getSalePrice());
            stm.setInt(6, pricePackage.getSubjectID().getSubjectId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePricePackage(PricePackage pricePackage) {
        try {
            String sql = "UPDATE [dbo].[PricePackage]\n"
                    + "   SET [Name] = ?\n"
                    + "      ,[AccessDuration] = ?\n"
                    + "      ,[Status] = ?\n"
                    + "      ,[ListPrice] = ?\n"
                    + "      ,[SalePrice] = ?\n"
                    + "      ,[SubjectID] = ?\n"
                    + " WHERE PriceID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pricePackage.getName());
            if (pricePackage.getAccessDuration() == -1) {
                stm.setNull(2, Types.INTEGER);
            } else {
                stm.setInt(2, pricePackage.getAccessDuration());
            }
            stm.setBoolean(3, pricePackage.isStatus());
            stm.setBigDecimal(4, pricePackage.getListPrice());
            stm.setBigDecimal(5, pricePackage.getSalePrice());
            stm.setInt(6, pricePackage.getSubjectID().getSubjectId());
            stm.setInt(7, pricePackage.getPriceID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<CoursePricePackage> getListPricePackageOfCourse(int courseId) {
        ArrayList<CoursePricePackage> listPriceOfCourse = new ArrayList<>();
        try {
            String sql = "select * from coursepricepackage where courseid = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CoursePricePackage coursePricePackage = new CoursePricePackage();
                coursePricePackage.setPriceId(rs.getInt("PriceID"));
                coursePricePackage.setName(rs.getString("Name"));
                if (rs.getObject("AccessDuration") != null) {
                    coursePricePackage.setAccessDuration(rs.getInt("AccessDuration"));
                } else {
                    coursePricePackage.setAccessDuration(-1);
                }
                coursePricePackage.setStatus(rs.getBoolean("Status"));
                coursePricePackage.setListPrice(rs.getBigDecimal("ListPrice"));
                coursePricePackage.setSalePrice(rs.getBigDecimal("SalePrice"));
                Course course = new Course();
                course.setCourseId(rs.getInt("CourseID"));
                coursePricePackage.setCourseId(course);
                
                listPriceOfCourse.add(coursePricePackage);
            }
            return listPriceOfCourse;
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
