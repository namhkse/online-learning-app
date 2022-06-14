package model;

public class Answer {

    private int answerID;
    private String answerText;
    private String explain;
    private int status;
    private Question questionID;

    public Answer() {
    }

    public Answer(int AnswerID, String AnswerText, String Explain, int Status, Question QuestionID) {
        this.answerID = AnswerID;
        this.answerText = AnswerText;
        this.explain = Explain;
        this.status = Status;
        this.questionID = QuestionID;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int AnswerID) {
        this.answerID = AnswerID;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String AnswerText) {
        this.answerText = AnswerText;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String Explain) {
        this.explain = Explain;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int Status) {
        this.status = Status;
    }

    public Question getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Question QuestionID) {
        this.questionID = QuestionID;
    }

}
