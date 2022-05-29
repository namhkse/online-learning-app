package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Course;
import model.Subject;

public class CourseDAO extends DBContext {

    public ArrayList<Course> getBestRatedCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            String sql = "SELECT AccountID, FirstName, LastName, ProfilePictureUrl, "
                    + "c.CourseID, c.Name CourseName, c.Description, c.TinyPictureUrl, "
                    + "c.ThumbnailUrl, c.CreatedDate, c.ModifiedDate, c.Price, c.Status\n"
                    + "FROM dbo.Course c JOIN (SELECT TOP 4 [CourseID], SUM(Rating) [total rate] FROM [CourseAccount] c \n"
                    + "GROUP BY CourseID ORDER BY [total rate] DESC) ca ON ca.CourseID = c.CourseID\n"
                    + "JOIN dbo.Account ON Account.AccountID = c.InstructorID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account instructor = new Account();
                instructor.setAccountId(rs.getInt("AccountID"));
                instructor.setFirstName(rs.getString("FirstName"));
                instructor.setLastName(rs.getString("LastName"));
                instructor.setProfilePictureUrl(rs.getString("ProfilePictureUrl"));

                Course course = new Course();
                course.setCourseId(rs.getInt("CourseID"));
                course.setName(rs.getString("CourseName"));
                course.setDescription(rs.getString("Description"));
                course.setInstructorId(instructor);
                course.setTinyPictureUrl(rs.getString("TinyPictureUrl"));
                course.setThumbnailUrl(rs.getString("ThumbnailUrl"));
                course.setCreatedDate(rs.getDate("CreatedDate"));
                course.setModifiedDate(rs.getDate("ModifiedDate"));
                course.setPrice(rs.getBigDecimal("Price"));
                course.setStatus(rs.getBoolean("Status"));
                courses.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courses;
    }
}
