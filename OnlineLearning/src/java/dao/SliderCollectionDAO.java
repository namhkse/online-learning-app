package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Gender;
import model.SliderCollection;

public class SliderCollectionDAO extends DBContext {

    public ArrayList<SliderCollection> getAllSliderCollections() {
        ArrayList<SliderCollection> sliderCollections = new ArrayList<>();
        try {
            String sql = "SELECT * FROM dbo.SliderCollection JOIN dbo.Account ON Account.AccountID = SliderCollection.OwnerID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SliderCollection sc = new SliderCollection();
                sc.setSliderCollectionID(rs.getInt("SliderCollectionID"));
                sc.setName(rs.getString("Name"));
                sc.setCreatedTime(rs.getTimestamp("CreatedTime"));
                sc.setUpdatedTime(rs.getTimestamp("UpdatedTime"));
                sc.setOrder(rs.getInt("Order"));
                sc.setStatus(rs.getBoolean("Status"));

                Account account = new Account();
                account.setAccountID(rs.getInt("AccountID"));
                account.setFirstName(rs.getString("FirstName"));
                account.setLastName(rs.getString("LastName"));
                account.setEmail(rs.getString("Email"));
                account.setProfilePictureUrl(rs.getString("ProfilePictureUrl"));
                account.setCreatedTime(rs.getTimestamp("CreatedTime"));
                account.setModifiedTime(rs.getTimestamp("ModifiedTime"));
                account.setPhone(rs.getString("Phone"));
                account.setAddress(rs.getString("Address"));
                account.setGender(Gender.of(rs.getBoolean("Gender")));
                account.setBalance(rs.getBigDecimal("Balance"));
                
                sc.setOwnerID(account);
                sliderCollections.add(sc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderCollectionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliderCollections;
    }
}
