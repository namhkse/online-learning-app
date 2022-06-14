package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CourseDAO extends DBContext {
    
    public int getNumberAllLessonInCourse(int accountID, int courseID) {
        int numLesson = -1;
        try {
            String sql = "SELECT COUNT(*) as NumAllLesson FROM Lesson l JOIN Course c\n"
                    + "ON c.CourseID = l.CourseID JOIN CourseAccount ca\n"
                    + "ON ca.CourseID = c.CourseID \n"
                    + "WHERE ca.AccountID = ? AND c.CourseID = ? AND l.LessonTypeID != 2";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                numLesson = rs.getInt("NumAllLesson");
            }     
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numLesson;
    }
    
    public int getNumberLessonLearning(int accountID, int courseID) {
        int numCurrentLesson = 0;
        try {
            String sql = "SELECT COUNT(*) as NumCurrentLesson FROM CompletedLesson cl JOIN Lesson l\n"
                    + "ON l.LessonID = cl.LessonID JOIN Course c\n"
                    + "ON c.CourseID = l.CourseID \n"
                    + "WHERE cl.AccountID = ? AND c.CourseID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                numCurrentLesson = rs.getInt("NumCurrentLesson");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numCurrentLesson;
    }
    
}
