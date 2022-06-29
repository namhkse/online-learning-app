package model;

public class BlogSubCategory {
    private int blogSubCategoryId;
    private String subCategoryName;
    private BlogCategory blogCategoryId;

    public BlogSubCategory() {
    }

    public BlogSubCategory(int blogSubCategoryId, String subCategoryName, BlogCategory blogCategoryId) {
        this.blogSubCategoryId = blogSubCategoryId;
        this.subCategoryName = subCategoryName;
        this.blogCategoryId = blogCategoryId;
    }
    
    public int getBlogSubCategoryId() {
        return blogSubCategoryId;
    }

    public void setBlogSubCategoryId(int blogSubCategoryId) {
        this.blogSubCategoryId = blogSubCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public BlogCategory getBlogCategoryId() {
        return blogCategoryId;
    }

    public void setBlogCategoryId(BlogCategory blogCategoryId) {
        this.blogCategoryId = blogCategoryId;
    }
    
}
