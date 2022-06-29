package model;

public class SubjectMainCategory {

    private int mainCategoryID;
    private String name;

    public SubjectMainCategory() {
    }

    public SubjectMainCategory(int mainCategoryID, String name) {
        this.mainCategoryID = mainCategoryID;
        this.name = name;
    }

    public int getMainCategoryID() {
        return mainCategoryID;
    }

    public void setMainCategoryID(int mainCategoryID) {
        this.mainCategoryID = mainCategoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
