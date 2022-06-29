package model;

import java.util.ArrayList;

public class SubjectCategory {

    private int categoryID;
    private String name;
    private SubjectMainCategory mainCategoryID;
    
    private ArrayList<Subject> listSubject;

    public ArrayList<Subject> getListSubject() {
        return listSubject;
    }

    public void setListSubject(ArrayList<Subject> listSubject) {
        this.listSubject = listSubject;
    }
    
    public SubjectCategory() {
    }

    public SubjectCategory(int categoryID, String name, SubjectMainCategory mainCategoryID) {
        this.categoryID = categoryID;
        this.name = name;
        this.mainCategoryID = mainCategoryID;
    }

    public SubjectMainCategory getMainCategoryID() {
        return mainCategoryID;
    }

    public void setMainCategoryID(SubjectMainCategory mainCategoryID) {
        this.mainCategoryID = mainCategoryID;
    }

    public SubjectCategory(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
