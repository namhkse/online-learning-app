package model;

import java.util.ArrayList;

public class Subject {

    private int subjectId;
    private String name;
    private SubjectCategory categoryID;
    private SubjectMainCategory mainCategoryID;
    private boolean featured;
    private boolean status;
    private String image;
    private String description;
    private int order;
    private String type;
    private Account ownerID;
    private ArrayList<SubjectCategory> listCategory;

    public ArrayList<SubjectCategory> getListCategory() {
        return listCategory;
    }

    public void setListCategory(ArrayList<SubjectCategory> listCategory) {
        this.listCategory = listCategory;
    }

    public SubjectMainCategory getMainCategoryID() {
        return mainCategoryID;
    }

    public void setMainCategoryID(SubjectMainCategory mainCategoryID) {
        this.mainCategoryID = mainCategoryID;
    }

    public Subject() {
    }

    public Subject(int subjectId, String name, SubjectCategory categoryID, SubjectMainCategory mainCategoryID, boolean featured, boolean status, String image, String description, int order, String type, Account ownerID, ArrayList<SubjectCategory> listCategory) {
        this.subjectId = subjectId;
        this.name = name;
        this.categoryID = categoryID;
        this.mainCategoryID = mainCategoryID;
        this.featured = featured;
        this.status = status;
        this.image = image;
        this.description = description;
        this.order = order;
        this.type = type;
        this.ownerID = ownerID;
        this.listCategory = listCategory;
    }

    public Account getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Account ownerID) {
        this.ownerID = ownerID;
    }

    public Subject(int subjectId, String name, int order, boolean status, String type) {
        this.subjectId = subjectId;
        this.name = name;
        this.categoryID = categoryID;
        this.featured = featured;
        this.status = status;
        this.image = image;
        this.description = description;
        this.order = order;
        this.type = type;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
