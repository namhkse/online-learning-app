package model;

import java.util.ArrayList;

public class Subject {

    private int subjectId;
    private String name;
    private SubjectCategory categoryID;
    private int order;
    private String type;
    private ArrayList<SubjectCategory> listCategory;

    public ArrayList<SubjectCategory> getListCategory() {
        return listCategory;
    }

    public void setListCategory(ArrayList<SubjectCategory> listCategory) {
        this.listCategory = listCategory;
    }

    public Subject() {
    }

    public Subject(int subjectId, String name, SubjectCategory categoryID, int order, String type) {
        this.subjectId = subjectId;
        this.name = name;
        this.categoryID = categoryID;
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
