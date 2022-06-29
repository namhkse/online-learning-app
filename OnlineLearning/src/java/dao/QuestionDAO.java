package dao;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Question;
import model.QuestionLevel;

public class QuestionDAO extends DBContext {

    static Question mapping(Question q, ResultSet rs) throws SQLException {
        q.setId(rs.getInt("QuestionID"));
        q.setText(rs.getString("QuestionText"));
        q.setImageUrl(rs.getString("QuestionImageUrl"));
        q.setLessonId(rs.getInt("LessonID"));
        q.setOrder(rs.getInt("Order"));
        q.setActive(rs.getBoolean("Status"));
        return q;
    }

    public List<Question> findAll() {
        List<Question> ls = new ArrayList<>();
        String sql = "SELECT q.QuestionID, q.QuestionText, q.QuestionImageUrl, q.LessonID, q.QuestionLevelID, q.[Order], "
                + "q.Status, l.LevelName FROM Question AS q LEFT JOIN QuestionLevel AS l "
                + "ON q.QuestionLevelID = l.QuestionLevelID";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                QuestionLevel level = QuestionLevelDAO.mapping(new QuestionLevel(), rs);
                Question question = mapping(new Question(), rs);
                question.setLevel(level);
                ls.add(question);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ls;
    }

    public List<Question> findAll(Comparator<Question> sorter) {
        List<Question> ls = findAll();
        ls.sort(sorter);
        return ls;
    }

    public Question findById(int id) {
        String sql = "SELECT q.QuestionID, q.QuestionText, q.QuestionImageUrl, q.LessonID, q.QuestionLevelID, q.[Order], "
                + "q.Status, l.LevelName FROM Question AS q LEFT JOIN QuestionLevel AS l "
                + "ON q.QuestionLevelID = l.QuestionLevelID WHERE QuestionID = ?";
        Question question = null;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                QuestionLevel level = QuestionLevelDAO.mapping(new QuestionLevel(), rs);
                question = mapping(new Question(), rs);
                question.setLevel(level);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return question;
    }

    public List<Question> findByLessonId(int id) {
        AnswerDAO answerDAO = new AnswerDAO();
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT q.QuestionID, q.QuestionText, q.QuestionImageUrl, q.LessonID, q.QuestionLevelID, q.[Order], "
                + "q.Status, l.LevelName FROM Question AS q LEFT JOIN QuestionLevel AS l "
                + "ON q.QuestionLevelID = l.QuestionLevelID where LessonID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Question q = new Question();
                mapping(q, rs);
                q.setAnswers(answerDAO.findByQuestionId(q.getId()));
                questions.add(q);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return questions;
    }

    public ArrayList<Question> listQuestions(int lessonId, int questionid) {
        ArrayList<Question> list = new ArrayList<>();
        try {
            String sql = "select q.* from [Question] q where q.LessonID = ? and q.QuestionID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonId);
            stm.setInt(2, questionid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question question = new Question();

                question.setId(rs.getInt("QuestionID"));
                question.setText(rs.getString("QuestionText"));
                question.setImageUrl(rs.getString("QuestionImageUrl"));
                question.setLessonId(rs.getInt("LessonID"));

                QuestionLevel questionLevel = new QuestionLevel(rs.getInt("QuestionLevelID"));

                question.setLevel(questionLevel);
                question.setOrder(rs.getInt("Order"));
                question.setActive(rs.getBoolean("Status"));
                list.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int countbyID(int lessonID) {
        try {
            String sql = "SELECT count(*) as Total FROM Question\n"
                    + "WHERE LessonID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Question> listAllBlogByPage(int lessonId, int questionid) {
        ArrayList<Question> list = new ArrayList<>();
        try {
            String sql = "select q.* from [Question] q where q.LessonID = ? and q.QuestionID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonId);
            stm.setInt(2, questionid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question question = new Question();

                question.setId(rs.getInt("QuestionID"));
                question.setText(rs.getString("QuestionText"));
                question.setImageUrl(rs.getString("QuestionImageUrl"));
                question.setLessonId(rs.getInt("LessonID"));

                QuestionLevel questionLevel = new QuestionLevel(rs.getInt("QuestionLevelID"));

                question.setLevel(questionLevel);
                question.setOrder(rs.getInt("Order"));
                question.setActive(rs.getBoolean("Status"));
                list.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Question> total(int lessonId) {
        ArrayList<Question> list = new ArrayList<>();
        try {
            String sql = "select q.* from [Question] q where q.LessonID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question question = new Question();

                question.setId(rs.getInt("QuestionID"));
                question.setText(rs.getString("QuestionText"));
                question.setImageUrl(rs.getString("QuestionImageUrl"));
                question.setLessonId(rs.getInt("LessonID"));

                QuestionLevel questionLevel = new QuestionLevel(rs.getInt("QuestionLevelID"));

                question.setLevel(questionLevel);
                question.setOrder(rs.getInt("Order"));
                question.setActive(rs.getBoolean("Status"));
                list.add(question);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int count(int lessonID) {
        try {
            String sql = "SELECT count(*) as Total FROM Question where lessonID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    private List<Integer> getIdsOfTrueAnswerInQuizLesson(int quizId) {
        String sql = "SELECT a.AnswerID FROM Question AS q INNER JOIN Answer AS a ON a.QuestionID = q.QuestionID\n"
                + "WHERE a.Status = 1 AND q.LessonID = ?";
        List<Integer> ls = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ls.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ls;
    }

    private void clearOldAnswer(int accountId, int quizId) throws SQLException {
        String sql = "DELETE FROM CompletedQuestion WHERE AccountID = ? "
                + "AND QuestionID IN (SELECT QuestionID FROM Question WHERE LessonID = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.setInt(2, quizId);
            stmt.executeLargeUpdate();
        }
    }

    /**
     * Json format: { "quizId":"4", "answers":[
     * {"questionId":118,"answerIds":["475"]},
     * {"questionId":109,"answerIds":["440","441"]} ] }
     *
     * @param accountId
     * @param jsonElement
     */
    public void saveAnswer(int accountId, JsonElement jsonElement) throws SQLException {
        JsonObject json = (JsonObject) jsonElement;
        int quizId = json.get("quizId").getAsInt();
        System.out.println(">>>>>>>");
        PreparedStatement stmt = null;
        try {
            clearOldAnswer(accountId, quizId);
            List<Integer> trueAnswerIds = getIdsOfTrueAnswerInQuizLesson(quizId);
            stmt = connection.prepareStatement("insert into CompletedQuestion values (?, ?, ?, ?)");
            stmt.setInt(1, accountId);
            
            for (JsonElement e : json.get("answers").getAsJsonArray()) {
                JsonObject jAnswer = (JsonObject) e;
                int questionId = jAnswer.get("questionId").getAsInt();
                stmt.setInt(2, questionId);
                
                for (JsonElement j : jAnswer.get("answerIds").getAsJsonArray()) {
                    int id = j.getAsInt();
                    stmt.setInt(3, id);
                    stmt.setBoolean(4, trueAnswerIds.contains(id));
//                    System.out.format("%s %s %s %s\n", )
                    stmt.executeUpdate();
                }
            }
        } finally {
            if(stmt != null) {
                stmt.close();
            }
        }
    }
}
