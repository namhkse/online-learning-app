package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SubjectCategory;

public class SubjectCategoryDAO extends DBContext {

    public ArrayList<SubjectCategory> getAllSubjectCategory() {
        ArrayList<SubjectCategory> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM SubjectCategory";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SubjectCategory sc = new SubjectCategory();
                sc.setCategoryID(rs.getInt("CategoryID"));
                sc.setName(rs.getString("Name"));
                
                list.add(sc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
