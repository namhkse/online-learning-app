package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Slider;
import model.SliderCollection;

public class SliderDAO extends DBContext {

    public ArrayList<Slider> getSlidersDisplay() {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "SELECT s.SliderID, s.Title, s.SubTitle, s.Description, s.NavigationLink, s.ImageUrl, s.[Order], s.Status, s.SliderCollectionID\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "WHERE sc.Status = 1 AND s.Status = 1\n"
                    + "ORDER BY s.SliderID DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("SliderID"));
                slider.setTitle(rs.getString("Title"));
                slider.setSubTitle(rs.getString("SubTitle"));
                slider.setDescription(rs.getString("Description"));
                slider.setNavigationLink(rs.getString("NavigationLink"));
                slider.setImageUrl(rs.getString("ImageUrl"));
                slider.setOrder(rs.getInt("Order"));
                slider.setStatus(rs.getBoolean("Status"));
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }

    public ArrayList<Slider> getAllSliders(int page) {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "SELECT s.*, sc.Name\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "ORDER BY s.SliderID DESC OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("SliderID"));
                slider.setTitle(rs.getString("Title"));
                slider.setSubTitle(rs.getString("SubTitle"));
                slider.setDescription(rs.getString("Description"));
                slider.setNavigationLink(rs.getString("NavigationLink"));
                slider.setImageUrl(rs.getString("ImageUrl"));
                slider.setOrder(rs.getInt("Order"));
                slider.setStatus(rs.getBoolean("Status"));

                SliderCollection sc = new SliderCollection();
                sc.setSliderCollectionID(rs.getInt("SliderCollectionID"));
                sc.setName(rs.getString("Name"));

                slider.setSliderCollectionID(sc);
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }

    public int countAllSliders() {
        try {
            String sql = "SELECT COUNT(SliderID) SlideQuantity FROM dbo.Slider s"
                    + " INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("SlideQuantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Slider getSliderById(int id) {
        Slider slider = new Slider();
        try {
            String sql = "SELECT s.*, sc.Name\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "WHERE s.SliderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                slider.setSliderID(rs.getInt("SliderID"));
                slider.setTitle(rs.getString("Title"));
                slider.setSubTitle(rs.getString("SubTitle"));
                slider.setDescription(rs.getString("Description"));
                slider.setNavigationLink(rs.getString("NavigationLink"));
                slider.setImageUrl(rs.getString("ImageUrl"));
                slider.setOrder(rs.getInt("Order"));
                slider.setStatus(rs.getBoolean("Status"));

                SliderCollection sc = new SliderCollection();
                sc.setSliderCollectionID(rs.getInt("SliderCollectionID"));
                sc.setName(rs.getString("Name"));

                slider.setSliderCollectionID(sc);
                return slider;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return slider;
    }

    public ArrayList<Slider> getSlidersByCollectionIdAndStatus(int id, String status, int page) {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "SELECT s.*, sc.Name\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "WHERE sc.SliderCollectionID = ? AND s.Status = ? ORDER BY s.SliderID DESC OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setBoolean(2, Boolean.parseBoolean(status));
            stm.setInt(3, page);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("SliderID"));
                slider.setTitle(rs.getString("Title"));
                slider.setSubTitle(rs.getString("SubTitle"));
                slider.setDescription(rs.getString("Description"));
                slider.setNavigationLink(rs.getString("NavigationLink"));
                slider.setImageUrl(rs.getString("ImageUrl"));
                slider.setOrder(rs.getInt("Order"));
                slider.setStatus(rs.getBoolean("Status"));

                SliderCollection sc = new SliderCollection();
                sc.setSliderCollectionID(rs.getInt("SliderCollectionID"));
                sc.setName(rs.getString("Name"));

                slider.setSliderCollectionID(sc);
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }

    public int countSlidersByCollectionIdAndStatus(int id, String status) {
        try {
            String sql = "SELECT COUNT(SliderID) SlideQuantity\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "WHERE sc.SliderCollectionID = ? AND s.Status = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setBoolean(2, Boolean.parseBoolean(status));
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("SlideQuantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Slider> getSlidersByCollectionId(int id, int page) {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "SELECT s.*, sc.Name\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "WHERE sc.SliderCollectionID = ? ORDER BY s.SliderID DESC OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, page);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("SliderID"));
                slider.setTitle(rs.getString("Title"));
                slider.setSubTitle(rs.getString("SubTitle"));
                slider.setDescription(rs.getString("Description"));
                slider.setNavigationLink(rs.getString("NavigationLink"));
                slider.setImageUrl(rs.getString("ImageUrl"));
                slider.setOrder(rs.getInt("Order"));
                slider.setStatus(rs.getBoolean("Status"));

                SliderCollection sc = new SliderCollection();
                sc.setSliderCollectionID(rs.getInt("SliderCollectionID"));
                sc.setName(rs.getString("Name"));

                slider.setSliderCollectionID(sc);
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }

    public int countSlidersByCollectionId(int id) {
        try {
            String sql = "SELECT COUNT(SliderID) SlideQuantity\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "WHERE sc.SliderCollectionID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("SlideQuantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Slider> getSlidersByStatus(String status, int page) {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "SELECT s.*, sc.Name\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "WHERE s.Status = ? ORDER BY s.SliderID DESC OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, Boolean.parseBoolean(status));
            stm.setInt(2, page);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("SliderID"));
                slider.setTitle(rs.getString("Title"));
                slider.setSubTitle(rs.getString("SubTitle"));
                slider.setDescription(rs.getString("Description"));
                slider.setNavigationLink(rs.getString("NavigationLink"));
                slider.setImageUrl(rs.getString("ImageUrl"));
                slider.setOrder(rs.getInt("Order"));
                slider.setStatus(rs.getBoolean("Status"));

                SliderCollection sc = new SliderCollection();
                sc.setSliderCollectionID(rs.getInt("SliderCollectionID"));
                sc.setName(rs.getString("Name"));

                slider.setSliderCollectionID(sc);
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }

    public int countSlidersByStatus(String status) {
        try {
            String sql = "SELECT COUNT(SliderID) SlideQuantity\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "WHERE s.Status = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, Boolean.parseBoolean(status));
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("SlideQuantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Slider> getSlidersByTitleOrBacklink(String text, int page) {
        ArrayList<Slider> sliders = new ArrayList<>();
        try {
            String sql = "SELECT s.*, sc.Name\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "WHERE Title LIKE ? OR NavigationLink LIKE ? ORDER BY s.SliderID DESC OFFSET ? ROWS FETCH NEXT 5 ROWS ONLY";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + text + "%");
            stm.setString(2, "%" + text + "%");
            stm.setInt(3, page);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Slider slider = new Slider();
                slider.setSliderID(rs.getInt("SliderID"));
                slider.setTitle(rs.getString("Title"));
                slider.setSubTitle(rs.getString("SubTitle"));
                slider.setDescription(rs.getString("Description"));
                slider.setNavigationLink(rs.getString("NavigationLink"));
                slider.setImageUrl(rs.getString("ImageUrl"));
                slider.setOrder(rs.getInt("Order"));
                slider.setStatus(rs.getBoolean("Status"));

                SliderCollection sc = new SliderCollection();
                sc.setSliderCollectionID(rs.getInt("SliderCollectionID"));
                sc.setName(rs.getString("Name"));

                slider.setSliderCollectionID(sc);
                sliders.add(slider);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sliders;
    }

    public int countSlidersByTitleOrBacklink(String text) {
        try {
            String sql = "SELECT COUNT(SliderID) SlideQuantity\n"
                    + "FROM dbo.Slider s INNER JOIN dbo.SliderCollection sc ON sc.SliderCollectionID = s.SliderCollectionID\n"
                    + "WHERE Title LIKE ? OR NavigationLink LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + text + "%");
            stm.setString(2, "%" + text + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("SlideQuantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void setStatusSlider(int id, boolean status) {
        try {
            String sql = "UPDATE [dbo].[Slider] SET [Status] = ? WHERE SliderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteSlider(int id) {
        try {
            String sql = "DELETE FROM [dbo].[Slider] WHERE SliderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertSlider(Slider slider) {
        try {
            String sql = "INSERT INTO [dbo].[Slider]\n"
                    + "           ([Title]\n"
                    + "           ,[SubTitle]\n"
                    + "           ,[Description]\n"
                    + "           ,[NavigationLink]\n"
                    + "           ,[ImageUrl]\n"
                    + "           ,[Order]\n"
                    + "           ,[Status]\n"
                    + "           ,[SliderCollectionID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,1\n"
                    + "           ,1\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, slider.getTitle());
            stm.setString(2, slider.getSubTitle());
            stm.setString(3, slider.getDescription());
            stm.setString(4, slider.getNavigationLink());
            stm.setString(5, slider.getImageUrl());
            stm.setInt(6, slider.getSliderCollectionID().getSliderCollectionID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSlider(Slider slider) {
        try {
            String sql = "UPDATE [dbo].[Slider]\n"
                    + "   SET [Title] = ?\n"
                    + "      ,[SubTitle] = ?\n"
                    + "      ,[Description] = ?\n"
                    + "      ,[NavigationLink] = ?\n"
                    + "      ,[ImageUrl] = ?\n"
                    + "      ,[SliderCollectionID] = ?\n"
                    + " WHERE SliderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, slider.getTitle());
            stm.setString(2, slider.getSubTitle());
            stm.setString(3, slider.getDescription());
            stm.setString(4, slider.getNavigationLink());
            stm.setString(5, slider.getImageUrl());
            stm.setInt(6, slider.getSliderCollectionID().getSliderCollectionID());
            stm.setInt(7, slider.getSliderID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
