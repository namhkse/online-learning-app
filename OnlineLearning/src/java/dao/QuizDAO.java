package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.QuizLesson;

public class QuizDAO extends DBContext {

    public QuizLesson getQuizLessonByID(int id) {
        try {
            String sql = "select LessonID, note, PassScore from QuizLesson \n"
                    + "where LessonID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                QuizLesson quizLesson = new QuizLesson();
                quizLesson.setLessonID(rs.getInt("LessonID"));
                quizLesson.setNote(rs.getString("note"));
                quizLesson.setPassScore(rs.getInt("PassScore"));
                return quizLesson;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
