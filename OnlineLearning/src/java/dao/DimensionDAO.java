package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dimension;
import model.DimensionType;
import model.Subject;

public class DimensionDAO extends DBContext {

    public ArrayList<Dimension> getDimensionsBySubjectID(int id) {
        ArrayList<Dimension> dimensions = new ArrayList<>();
        try {
            String sql = "SELECT DimensionID, Dimension.Name, Description, Dimension.TypeID, DimensionType.Name Type, SubjectID\n"
                    + "FROM dbo.Dimension JOIN dbo.DimensionType ON DimensionType.TypeID = Dimension.TypeID\n"
                    + "WHERE SubjectID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Dimension dimension = new Dimension();
                dimension.setDimensionID(rs.getInt("DimensionID"));
                dimension.setName(rs.getString("Name"));
                dimension.setDescription(rs.getString("Description"));

                DimensionType dimensionType = new DimensionType();
                dimensionType.setTypeID(rs.getInt("TypeID"));
                dimensionType.setName(rs.getString("Type"));

                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));

                dimension.setTypeID(dimensionType);
                dimension.setSubjectID(subject);
                dimensions.add(dimension);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dimensions;
    }

    public Dimension getDimensionByID(int id) {
        try {
            String sql = "SELECT DimensionID, Dimension.Name, Description, Dimension.TypeID, DimensionType.Name Type, SubjectID\n"
                    + "FROM dbo.Dimension JOIN dbo.DimensionType ON DimensionType.TypeID = Dimension.TypeID\n"
                    + "WHERE DimensionID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Dimension dimension = new Dimension();
                dimension.setDimensionID(rs.getInt("DimensionID"));
                dimension.setName(rs.getString("Name"));
                dimension.setDescription(rs.getString("Description"));

                DimensionType dimensionType = new DimensionType();
                dimensionType.setTypeID(rs.getInt("TypeID"));
                dimensionType.setName(rs.getString("Type"));

                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("SubjectID"));

                dimension.setTypeID(dimensionType);
                dimension.setSubjectID(subject);
                return dimension;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteDimension(int dimensionID) {
        try {
            String sql = "DELETE FROM [dbo].[Dimension] WHERE DimensionID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, dimensionID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDimension(Dimension dimension) {
        try {
            String sql = "UPDATE [dbo].[Dimension]\n"
                    + "   SET [Name] = ?\n"
                    + "      ,[Description] = ?\n"
                    + "      ,[TypeID] = ?\n"
                    + " WHERE DimensionID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, dimension.getName());
            stm.setString(2, dimension.getDescription());
            stm.setInt(3, dimension.getTypeID().getTypeID());
            stm.setInt(4, dimension.getDimensionID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertDimension(Dimension dimension) {
        try {
            String sql = "INSERT INTO [dbo].[Dimension]\n"
                    + "           ([Name]\n"
                    + "           ,[Description]\n"
                    + "           ,[TypeID]\n"
                    + "           ,[SubjectID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, dimension.getName());
            stm.setString(2, dimension.getDescription());
            stm.setInt(3, dimension.getTypeID().getTypeID());
            stm.setInt(4, dimension.getSubjectID().getSubjectId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
