package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Lesson;
import model.LessonType;
import model.SubLesson;

public class LessonDAO extends DBContext {

    public ArrayList<Lesson> getListLessonById(int id) {
        ArrayList<Lesson> list = new ArrayList<>();
        try {
            String sql = "select l.StartLearningTime, l.LessonID, l.Name as LessonName, l.CreatedTime, \n"
                    + "l.UpdatedTime, l. WideImageUrl, l.TinyImageUrl, l.[Order], \n"
                    + "l.VideoUrl, l.[Status], sl.SubLessonID, sl.Name as SubLessonName,\n"
                    + "lt.LessonTypeID, lt.Name as LessonTypeName, c.*\n"
                    + "from Lesson l join SubLesson sl\n"
                    + "on l.SubLessonID = sl.SubLessonID\n"
                    + "join LessonType lt \n"
                    + "on lt.LessonTypeID = l.LessonTypeID\n"
                    + "join Course c on c.CourseID = l.CourseID\n"
                    + "where l.CourseID = ? and c.[Status] = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("CourseID"));
                course.setName(rs.getString("CourseName"));
                course.setDescription(rs.getString("Description"));
                course.setTinyPictureUrl(rs.getString("TinyPictureUrl"));
                course.setThumbnailUrl(rs.getString("ThumbnailUrl"));
                course.setCreatedDate(rs.getDate("CreatedDate"));
                course.setModifiedDate(rs.getDate("ModifiedDate"));
                course.setPrice(rs.getBigDecimal("Price"));
                course.setStatus(rs.getBoolean("Status"));

                LessonType type = new LessonType();
                type.setLessonTypeID(rs.getInt("LessonTypeID"));
                type.setName(rs.getString("LessonTypeName"));

                SubLesson sub = new SubLesson();
                sub.setSubLessonID(rs.getInt("SubLessonID"));
                sub.setCourseID(course);
                sub.setName(rs.getString("SubLessonName"));

                Lesson lesson = new Lesson();
                lesson.setLessonID(rs.getInt("LessonID"));
                lesson.setCourseID(course);
                lesson.setLessonTypeID(type);
                lesson.setName(rs.getString("LessonName"));
                lesson.setCreatedTime(rs.getTimestamp("CreatedTime"));
                lesson.setUpdatedTime(rs.getTimestamp("UpdatedTime"));
                lesson.setWideImageUrl(rs.getString("WideImageUrl"));
                lesson.setTinyImageUrl(rs.getString("TinyImageUrl"));
                lesson.setOrder(rs.getInt("Order"));
                lesson.setSubLessonID(sub);
                lesson.setVideoUrl(rs.getString("VideoUrl"));
                lesson.setStatus(rs.getBoolean("Status"));
                lesson.setStartLearningTime(rs.getTimestamp("StartLearningTime"));

                list.add(lesson);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    ArrayList<Lesson> getListLessonBySubLessonID(int subLesson) {
        ArrayList<Lesson> list = new ArrayList<>();
        try {
            String sql = "select l.StartLearningTime, l.LessonID, l.Name as LessonName, l.CreatedTime, \n"
                    + "l.UpdatedTime, l. WideImageUrl, l.TinyImageUrl, l.[Order], \n"
                    + "l.VideoUrl, l.[Status], sl.SubLessonID, sl.Name as SubLessonName,\n"
                    + "lt.LessonTypeID, lt.Name as LessonTypeName, c.*\n"
                    + "from Lesson l join SubLesson sl\n"
                    + "on l.SubLessonID = sl.SubLessonID\n"
                    + "join LessonType lt \n"
                    + "on lt.LessonTypeID = l.LessonTypeID\n"
                    + "join Course c on c.CourseID = l.CourseID\n"
                    + "where c.[Status] = 1 and sl.SubLessonID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subLesson);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                Course course = new Course();
                course.setCourseId(rs.getInt("CourseID"));
                course.setName(rs.getString("Name"));
                course.setDescription(rs.getString("Description"));
                course.setTinyPictureUrl(rs.getString("TinyPictureUrl"));
                course.setThumbnailUrl(rs.getString("ThumbnailUrl"));
                course.setCreatedDate(rs.getDate("CreatedDate"));
                course.setModifiedDate(rs.getDate("ModifiedDate"));
                course.setPrice(rs.getBigDecimal("Price"));
                course.setStatus(rs.getBoolean("Status"));

                LessonType type = new LessonType();
                type.setLessonTypeID(rs.getInt("LessonTypeID"));
                type.setName(rs.getString("LessonTypeName"));

                Lesson lesson = new Lesson();
                lesson.setLessonID(rs.getInt("LessonID"));
                lesson.setCourseID(course);
                lesson.setLessonTypeID(type);
                lesson.setName(rs.getString("LessonName"));
                lesson.setCreatedTime(rs.getTimestamp("CreatedTime"));
                lesson.setUpdatedTime(rs.getTimestamp("UpdatedTime"));
                lesson.setWideImageUrl(rs.getString("WideImageUrl"));
                lesson.setTinyImageUrl(rs.getString("TinyImageUrl"));
                lesson.setOrder(rs.getInt("Order"));
                lesson.setVideoUrl(rs.getString("VideoUrl"));
                lesson.setStatus(rs.getBoolean("Status"));
                lesson.setStartLearningTime(rs.getTimestamp("StartLearningTime"));

                list.add(lesson);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Lesson getLessonPlay(int accountID, int courseID, int lessonID) {
        try {
            String sql = "select l.* from Lesson l join Course c\n"
                    + "on l.CourseID = c.CourseID join CourseAccount ca\n"
                    + "on ca.CourseID = c.CourseID join Account a\n"
                    + "on a.AccountID = ca.AccountID\n"
                    + "where a.AccountID = ? and ca.CourseID = ? and l.LessonID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, courseID);
            stm.setInt(3, lessonID);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                Lesson lesson = new Lesson();
                lesson.setLessonID(rs.getInt("LessonID"));
                lesson.setName(rs.getString("Name"));
                lesson.setCreatedTime(rs.getTimestamp("CreatedTime"));
                lesson.setUpdatedTime(rs.getTimestamp("UpdatedTime"));
                lesson.setWideImageUrl(rs.getString("WideImageUrl"));
                lesson.setTinyImageUrl(rs.getString("TinyImageUrl"));
                lesson.setOrder(rs.getInt("Order"));
                lesson.setVideoUrl(rs.getString("VideoUrl"));
                lesson.setStatus(rs.getBoolean("Status"));
                lesson.setStartLearningTime(rs.getTimestamp("StartLearningTime"));

                return lesson;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Lesson getLessonLearning(int accountID, int courseID) {
        try {
            String sql = "select top 1 l.* from lesson l join Course c\n"
                    + "on c.CourseID = l.CourseID join CourseAccount ca\n"
                    + "on ca.CourseID = c.CourseID join Account a\n"
                    + "on a.AccountID = ca.AccountID\n"
                    + "where a.AccountID = ? and c.CourseID = ? and LessonTypeID = 1 and LessonID not in (\n"
                    + "select l.lessonid from Lesson l join CompletedLesson cl\n"
                    + "on l.LessonID = cl.LessonID )\n"
                    + "order by l.[order] asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, courseID);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                Lesson lesson = new Lesson();
                lesson.setLessonID(rs.getInt("LessonID"));
                lesson.setName(rs.getString("Name"));
                lesson.setCreatedTime(rs.getTimestamp("CreatedTime"));
                lesson.setUpdatedTime(rs.getTimestamp("UpdatedTime"));
                lesson.setWideImageUrl(rs.getString("WideImageUrl"));
                lesson.setTinyImageUrl(rs.getString("TinyImageUrl"));
                lesson.setOrder(rs.getInt("Order"));
                lesson.setVideoUrl(rs.getString("VideoUrl"));
                lesson.setStatus(rs.getBoolean("Status"));
                lesson.setStartLearningTime(rs.getTimestamp("StartLearningTime"));

                return lesson;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertCompletedLesson(int accountID, int lessonID) {
        try {
            String sql = "INSERT INTO [CompletedLesson]([AccountID],\n"
                    + "[LessonID],[Score],[Status])\n"
                    + "VALUES(?,?,10,1)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, lessonID);

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNumberLessonInCourse(int courseID) {
        try {
            String sql = "SELECT COUNT(*) AS TotalNumLesson FROM dbo.Lesson l JOIN dbo.Course c\n"
                    + "ON l.CourseID = c.CourseID JOIN dbo.CourseAccount ca\n"
                    + "ON ca.CourseID = c.CourseID \n"
                    + "WHERE c.CourseID = ? AND l.LessonTypeID = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalNumLesson");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int getNumberLessonLearningInCourse(int accountID, int courseID) {
        try {
            String sql = "SELECT COUNT(*) AS LearningNumLesson "
                    + "FROM dbo.CompletedLesson cl JOIN dbo.Lesson l\n"
                    + "ON l.LessonID = cl.LessonID \n"
                    + "WHERE cl.AccountID = ? AND l.CourseID = ? AND l.LessonTypeID = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("LearningNumLesson");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getLastLesson(int courseID) {
        try {
            String sql = "SELECT TOP 1 LessonID FROM dbo.Lesson \n"
                    + "WHERE CourseID = ? AND LessonTypeID = 1 \n"
                    + "ORDER BY [Order] DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("LessonID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Lesson getLessonNext(int courseID, int lessonID) {
        try {
            String sql = "select top 1 * from lesson\n"
                    + "where lessonTypeID = 1 and courseid = ? and [order] > (\n"
                    + "	select [order] from lesson where lessonid = ?\n"
                    + ")\n"
                    + "order by [order] asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            stm.setInt(2, lessonID);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                Lesson lesson = new Lesson();
                lesson.setLessonID(rs.getInt("LessonID"));
                lesson.setName(rs.getString("Name"));
                lesson.setCreatedTime(rs.getTimestamp("CreatedTime"));
                lesson.setUpdatedTime(rs.getTimestamp("UpdatedTime"));
                lesson.setWideImageUrl(rs.getString("WideImageUrl"));
                lesson.setTinyImageUrl(rs.getString("TinyImageUrl"));
                lesson.setOrder(rs.getInt("Order"));
                lesson.setVideoUrl(rs.getString("VideoUrl"));
                lesson.setStatus(rs.getBoolean("Status"));
                lesson.setStartLearningTime(rs.getTimestamp("StartLearningTime"));

                return lesson;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Lesson getLessonFirstOfCourse(int courseID) {
        try {
            String sql = "select top 1 * from lesson \n"
                    + "where courseid = ? and LessonTypeID = 1 and [status] = 1\n"
                    + "order by [order] asc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                Lesson lesson = new Lesson();
                lesson.setLessonID(rs.getInt("LessonID"));
                lesson.setName(rs.getString("Name"));
                lesson.setCreatedTime(rs.getTimestamp("CreatedTime"));
                lesson.setUpdatedTime(rs.getTimestamp("UpdatedTime"));
                lesson.setWideImageUrl(rs.getString("WideImageUrl"));
                lesson.setTinyImageUrl(rs.getString("TinyImageUrl"));
                lesson.setOrder(rs.getInt("Order"));
                lesson.setVideoUrl(rs.getString("VideoUrl"));
                lesson.setStatus(rs.getBoolean("Status"));
                lesson.setStartLearningTime(rs.getTimestamp("StartLearningTime"));

                return lesson;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Lesson getLessonByID(int id) {
        try {
            String sql = "select Name from Lesson "
                    + "where LessonID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                Lesson lesson = new Lesson();
                lesson.setName(rs.getString("Name"));
                return lesson;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
