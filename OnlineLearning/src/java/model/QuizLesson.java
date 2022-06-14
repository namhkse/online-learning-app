package model;

public class QuizLesson extends Lesson{
    private int examTimeInMinute = 5;
    public QuizLesson() {
    }
    
    public int getExamTimeInMinute() {
        return this.examTimeInMinute;
    }
}
