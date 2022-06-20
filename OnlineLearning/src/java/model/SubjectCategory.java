package model;

public class SubjectCategory {

    private int categoryID;
    private String name;
    private SubjectMainCategory mainCategoryID;

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
