package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import model.Answer;

public class AnswerDAO extends DBContext {

    private static final Logger LOG = Logger.getLogger(AnswerDAO.class.getName());

    public Set<Answer> findByQuestionId(int id) {
        Set<Answer> answers = new HashSet<>();
        String sql = "select AnswerID, AnswerText, Explain from Answer where QuestionID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            LOG.info(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Answer a = new Answer();
                a.setId(rs.getInt("AnswerID"));
                a.setText(rs.getString("AnswerText"));
                a.setExplain(rs.getString("Explain"));
                answers.add(a);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return answers;
    }
}
