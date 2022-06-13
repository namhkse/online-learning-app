package model;

public class Answer {

    private int id;
    private String text;
    private String explain;

    public Answer(int id, String text, String explain) {
        this.id = id;
        this.text = text;
        this.explain = explain;
    }

    public Answer() {
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getExplain() {
        return explain;
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
