package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.CompletedQuestion;
import model.Question;

public class AnswerDAO extends DBContext {

    public ArrayList<Answer> listAllAnsByQues(int lessonId, int questionID) {
        ArrayList<Answer> list = new ArrayList<>();
        try {
            String sql = "select a.* from [Question] q, [Answer] a  "
                    + "where q.LessonID = ? and a.QuestionID = ?  and q.QuestionID = a.QuestionID";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonId);
            stm.setInt(2, questionID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setAnswerID(rs.getInt("AnswerID"));
                answer.setAnswerText(rs.getString("AnswerText"));
                answer.setExplain(rs.getString("Explain"));
                answer.setStatus(rs.getInt("Status"));

                Question question = new Question();
                question.setId(rs.getInt("QuestionID"));
                answer.setQuestionID(question);
                list.add(answer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Answer> listquestionbyQuestionID(int lessonId, int questionID) {
        ArrayList<Answer> list = new ArrayList<>();
        try {
            String sql = "select a.* from [Question] q, [Answer] a  "
                    + "where q.LessonID = ? and a.QuestionID = ?  and q.QuestionID = a.QuestionID and a.[Status] = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonId);
            stm.setInt(2, questionID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setAnswerID(rs.getInt("AnswerID"));
                answer.setAnswerText(rs.getString("AnswerText"));
                answer.setExplain(rs.getString("Explain"));
                answer.setStatus(rs.getInt("Status"));

                Question question = new Question();
                question.setId(rs.getInt("QuestionID"));
                answer.setQuestionID(question);
                list.add(answer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
