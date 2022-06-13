package model;

public class Subject {

    private int subjectId;
    private String name;
    private SubjectCategory categoryID;
    private boolean featured;
    private boolean status;
    private String image;
    private String description;

    public Subject() {
    }

    public Subject(int subjectId, String name, SubjectCategory categoryID, boolean featured, boolean status, String image, String description) {
        this.subjectId = subjectId;
        this.name = name;
        this.categoryID = categoryID;
        this.featured = featured;
        this.status = status;
        this.image = image;
        this.description = description;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectCategory getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(SubjectCategory categoryID) {
        this.categoryID = categoryID;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
