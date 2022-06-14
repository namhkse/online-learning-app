package model;

public class QuizLesson {

    private int lessonID;
    private String note;
    private int passScore;

    public QuizLesson() {
    }

    public QuizLesson(int LessonID, String Note, int PassScore) {
        this.lessonID = LessonID;
        this.note = Note;
        this.passScore = PassScore;
    }

    public int getLessonID() {
        return lessonID;
    }

    public void setLessonID(int LessonID) {
        this.lessonID = LessonID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String Note) {
        this.note = Note;
    }

    public int getPassScore() {
        return passScore;
    }

    public void setPassScore(int PassScore) {
        this.passScore = PassScore;
    }

}
