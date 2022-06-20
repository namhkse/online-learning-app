package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SubjectCategory;
import model.SubjectMainCategory;

public class SubjectCategoryDAO extends DBContext {

    public ArrayList<SubjectCategory> getAllSubjectCategory() {
        ArrayList<SubjectCategory> list = new ArrayList<>();
        try {
            String sql = "SELECT SubjectCategory.*, SubjectMainCategory.Name SubjectMainCategoryName "
                    + "FROM SubjectCategory JOIN SubjectMainCategory ON SubjectCategory.MainCategoryID = SubjectMainCategory.MainCategoryID";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SubjectCategory sc = new SubjectCategory();
                sc.setCategoryID(rs.getInt("CategoryID"));
                sc.setName(rs.getString("Name"));
                
                SubjectMainCategory mainCategory=new SubjectMainCategory();
                mainCategory.setMainCategoryID(rs.getInt("MainCategoryID"));
                mainCategory.setName(rs.getString("SubjectMainCategoryName"));
                sc.setMainCategoryID(mainCategory);
                
                list.add(sc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
