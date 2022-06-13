package model;

import java.util.Set;

public class Question {

    private int id;
    private String text;
    private String imageUrl;
    private int lessonId;
    private QuestionLevel level;
    private int order;
    private boolean active;
    private Set<Answer> answers;

    public Question() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public void setLevel(QuestionLevel level) {
        this.level = level;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getQuestionText() {
        return text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getLessonId() {
        return lessonId;
    }

    public QuestionLevel getLevel() {
        return level;
    }

    public int getOrder() {
        return order;
    }

    public boolean isActive() {
        return active;
    }

    public Set<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
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
        final Question other = (Question) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
