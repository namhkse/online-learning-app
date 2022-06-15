package model;

import com.google.gson.annotations.Expose;

public class Answer {

    @Expose private int id;
    @Expose private String text;
    @Expose private String explain;
    private     int status;
    
    private Question questionID;

    public Answer() {
    }

    public Answer(int id, String text, String explain) {
        this.id = id;
        this.text = text;
        this.explain = explain;
    }

    public Answer(int AnswerID, String AnswerText, String Explain, int Status, Question QuestionID) {
        this.id = AnswerID;
        this.text = AnswerText;
        this.explain = Explain;
        this.status = Status;
        this.questionID = QuestionID;
    }
    
    /**
     * Use getId()
     * @return
     * @deprecated
     */
    @Deprecated
    public int getAnswerID() {
        return id;
    }
    
    /**
     * Use setId()
     * @param AnswerID
     * @deprecated
     */
    @Deprecated
    public void setAnswerID(int AnswerID) {
        this.id = AnswerID;
    }
    
    /**
     * Use getText()
     * @return
     * @deprecated
     */
    @Deprecated
    public String getAnswerText() {
        return text;
    }

    /**
     * Use setText()
     * @param AnswerText 
     */
    @Deprecated
    public void setAnswerText(String AnswerText) {
        this.text = AnswerText;
    }

    public String getExplain() {
        return explain;
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

    public int getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Answer other = (Answer) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
