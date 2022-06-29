package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SubjectMainCategory;

public class SubjectMainCategoryDAO extends DBContext {

    public ArrayList<SubjectMainCategory> getAllSubjectMainCategories() {
        ArrayList<SubjectMainCategory> mainCategories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM SubjectMainCategory";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SubjectMainCategory mainCategory = new SubjectMainCategory();
                mainCategory.setMainCategoryID(rs.getInt("MainCategoryID"));
                mainCategory.setName(rs.getString("Name"));
                mainCategories.add(mainCategory);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectMainCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mainCategories;
    }
}
