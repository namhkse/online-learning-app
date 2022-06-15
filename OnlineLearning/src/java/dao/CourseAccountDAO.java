package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Course;
import model.CourseAccount;

public class CourseAccountDAO extends DBContext {

    public ArrayList<CourseAccount> getBestRatedCourses() {
        ArrayList<CourseAccount> listCourseAccount = new ArrayList<>();
        try {
            String sql = "SELECT AccountID, FirstName, LastName, ProfilePictureUrl,\n"
                    + "c.CourseID, c.Name CourseName, c.Description, c.TinyPictureUrl,\n"
                    + "c.ThumbnailUrl, c.CreatedDate, c.ModifiedDate, c.Price, c.Status, ca.[Total Rate] / People as Star\n"
                    + "FROM dbo.Course c JOIN (SELECT TOP 3 coac.[CourseID], \n"
                    + "SUM(Rating) [Total Rate], COUNT(AccountID) People FROM [CourseAccount] coac join Course co\n"
                    + "on coac.CourseID = co.CourseID where co.Status = 1\n"
                    + "GROUP BY coac.CourseID ORDER BY [total rate] DESC ) ca ON ca.CourseID = c.CourseID\n"
                    + "JOIN dbo.Account ON Account.AccountID = c.InstructorID WHERE [Status] = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account instructor = new Account();
                instructor.setAccountID(rs.getInt("AccountID"));
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

                CourseAccount courseAccount = new CourseAccount();
                courseAccount.setCourseId(course);
                courseAccount.setRating(rs.getInt("Star"));

                listCourseAccount.add(courseAccount);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCourseAccount;
    }

    public ArrayList<CourseAccount> getListCourseAccount(int accountID) {
        ArrayList<CourseAccount> listCourseAccount = new ArrayList<>();
        try {
            String sql = "select a.AccountID, a.FirstName, a.LastName, a.ProfilePictureUrl, \n"
                    + "ins.FirstName as FirstNameIns, ins.LastName as LastNameIns, \n"
                    + "ins.AccountID as AccountIDIns, ins.ProfilePictureUrl as ProfilePictureUrlIns, \n"
                    + "c.*, ca.EnrollDate, ca.Rating  \n"
                    + "from courseaccount ca join account a \n"
                    + "on ca.AccountID = a.AccountID join Course c\n"
                    + "on c.CourseID = ca.CourseID \n"
                    + "join account ins \n"
                    + "on ins.AccountID = c.InstructorID\n"
                    + "where a.AccountID = ? and c.Status = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account user = new Account();
                user.setAccountID(rs.getInt("AccountID"));
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setProfilePictureUrl(rs.getString("ProfilePictureUrl"));

                Account instructor = new Account();
                instructor.setAccountID(rs.getInt("AccountIDIns"));
                instructor.setFirstName(rs.getString("FirstNameIns"));
                instructor.setLastName(rs.getString("LastNameIns"));
                instructor.setProfilePictureUrl(rs.getString("ProfilePictureUrlIns"));

                Course course = new Course();
                course.setCourseId(rs.getInt("CourseID"));
                course.setName(rs.getString("Name"));
                course.setDescription(rs.getString("Description"));
                course.setInstructorId(instructor);

                course.setTinyPictureUrl(rs.getString("TinyPictureUrl"));
                course.setThumbnailUrl(rs.getString("ThumbnailUrl"));
                course.setCreatedDate(rs.getDate("CreatedDate"));
                course.setModifiedDate(rs.getDate("ModifiedDate"));
                course.setPrice(rs.getBigDecimal("Price"));
                course.setStatus(rs.getBoolean("Status"));

                CourseAccount courseAccount = new CourseAccount();
                courseAccount.setCourseId(course);
                courseAccount.setAccountId(user);
                courseAccount.setEnrollDate(rs.getDate("EnrollDate"));
                courseAccount.setRating(rs.getInt("Rating"));
                int numAllLesson = new CourseDAO().getNumberAllLessonInCourse(user.getAccountID(), course.getCourseId());
                int numLessonLearning = new CourseDAO().getNumberLessonLearning(user.getAccountID(), course.getCourseId());
                int process = 0;
                try {
                    double numTemp = (double) numLessonLearning / numAllLesson;
                    process = (int) (numTemp * 100);
                } catch (Exception e) {
                    process = 0;
                }
                courseAccount.setProcess(process);
                listCourseAccount.add(courseAccount);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCourseAccount;
    }
    
    public boolean isRegisterCourse(int accountID, int courseID) {
        try {
            String sql = "SELECT * FROM CourseAccount \n"
                    + "WHERE AccountID = ? AND CourseID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
