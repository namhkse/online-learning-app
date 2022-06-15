package model;

public class QuizLesson {
    
    private String name;
    private int lessonID;
    private String note;
    private int passScore;
    private int examTimeInMinute;
    
    public QuizLesson() {
        
    }
    
    public QuizLesson(int LessonID, String Note, int PassScore) {
        this.lessonID = LessonID;
        this.note = Note;
        this.passScore = PassScore;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    /**
     * Use getId()
     * @return
     * @deprecated
     */
    @Deprecated
    public int getLessonID() {
        return lessonID;
    }

    /**
     * Use setId()
     * @param LessonID
     * @deprecated
     */
    @Deprecated
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

    public void setExamTimeInMinute(int minute) {
        this.examTimeInMinute = minute;
    }
    
    public int getExamTimeInMinute() {
        return this.examTimeInMinute;
    }
    
    public void setId(int id) {
        this.lessonID = id;
    }
    
    public int getId() {
        return this.lessonID;
    }
}
