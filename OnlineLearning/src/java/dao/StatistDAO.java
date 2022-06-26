package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class StatistDAO extends DBContext {

    private static final Logger LOG = Logger.getLogger(StatistDAO.class.getName());

    private class CourseSubjectArgs {

        private final String subject;
        private final int amount;

        public CourseSubjectArgs(String subjectName, int amontCourse) {
            this.subject = subjectName;
            this.amount = amontCourse;
        }
    }

    private class CourseEnrollArgs {

        private final int courseId;
        private final String courseName;
        private final int numberOfEnroll;

        public CourseEnrollArgs(int courseId, String courseName, int numberOfEnroll) {
            this.courseId = courseId;
            this.courseName = courseName;
            this.numberOfEnroll = numberOfEnroll;
        }
    }

    public List countCourseInAllSubject() throws SQLException {
        List<CourseSubjectArgs> ls = new ArrayList<>();
        String sql = "select s.Name [Subject], count(*) [Total]\n"
                + "from SubjectCourse sc inner join Subject s\n"
                + "on sc.SubjectID = s.SubjectID\n"
                + "group by s.Name";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ls.add(new CourseSubjectArgs(rs.getString("Subject"), rs.getInt("Total")));
            }
        }
        return ls;
    }

    public List<CourseEnrollArgs> countNumberEnrollInAllCourse() throws SQLException {
        List<CourseEnrollArgs> ls = new ArrayList<>();
        String sql = "select c.CourseID, c.Name [CourseName], count(*) [NumberOfEnroll]\n"
                + "from Course c left join CourseAccount ac\n"
                + "	on c.CourseID = ac.CourseID\n"
                + "group by c.CourseID, c.Name\n"
                + "order by NumberOfEnroll desc";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ls.add(new CourseEnrollArgs(rs.getInt("CourseID"), rs.getString("CourseName"), rs.getInt("NumberOfEnroll")));
            }
        }

        return ls;
    }

    public double getAllEarning() {
        String sql = "select sum(Amount) from TransactionHistory";
        double total = 0;
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            LOG.warning(ex.getMessage());
        }
        return total;
    }

    /**
     * Only used for json serialize.
     */
    private class RevenueArgs {

        private final String monthInYear;
        private final double revenue;

        public RevenueArgs(String monthInYear, double revenue) {
            this.monthInYear = monthInYear;
            this.revenue = revenue;
        }
    }

    public List calculateRevenues() throws SQLException {
        List<RevenueArgs> ls = new ArrayList<>();
        String sql = "select\n"
                + "	FORMAT(TrasactionTime, 'MM/yyyy') [MonthInYear],\n"
                + "	SUM(Amount) [Revenue]\n"
                + "from TransactionHistory\n"
                + "group by FORMAT(TrasactionTime, 'MM/yyyy')\n"
                + "order by MonthInYear";

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ls.add(new RevenueArgs(rs.getString("MonthInYear"), rs.getDouble("Revenue")));
            }
        }

        return ls;
    }

    public double calculateRevenueInYear(int year) {
        double revenue = 0;
        String sql = "select sum(Amount) [Amount]\n"
                + "from TransactionHistory\n"
                + "where YEAR(TrasactionTime) = ? \n"
                + "group by YEAR(TrasactionTime)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                revenue = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            LOG.warning(ex.getMessage());
        }
        return revenue;
    }

    private class BlogViewCounterArgs {

        private final int blogCategoryId;
        private final String blogCategoryName;
        private final int numberOfView;

        public BlogViewCounterArgs(int blogCategoryId, String blogCategoryName, int numberOfView) {
            this.blogCategoryId = blogCategoryId;
            this.blogCategoryName = blogCategoryName;
            this.numberOfView = numberOfView;
        }
    }

    public List countNumberViewOfAllBlogCategory() throws SQLException {
        String sql = "select a.BlogCategoryID, a.Name [BlogCategoryName],\n"
                + "		(select sum(b.NumberOfView)\n"
                + "	from BlogCategoryBlog bc inner join Blog b\n"
                + "		on bc.BlogID = b.BlogID\n"
                + "	where bc.BlogCategoryID = a.BlogCategoryID\n"
                + "	group by bc.BlogCategoryID) [NumberOfView]\n"
                + "from BlogCategory as a\n"
                + "order by NumberOfView";
        List<BlogViewCounterArgs> ls = new ArrayList();
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ls.add(new BlogViewCounterArgs(rs.getInt("BlogCategoryID"), rs.getString("BlogCategoryName"), rs.getInt("NumberOfView")));
            }
        }
        return ls;
    }

    /**
     * Returns a map has two key.
     * <ul>
     * <li>1. MonthInYear: MM_YYYY date format</li>
     * <li>2. NumberNewAccount: number of accounts were created in above
     * time.</li>
     * <ul>
     *
     * @param MM_YYYY date format MM/yyyy
     * @return
     * @throws SQLException
     */
    public Map<String, Integer> getNumberOfNewAccount(String MM_YYYY) {
        String sql = "select \n"
                + "	MonthInYear = FORMAT(CreatedTime, 'MM/yyyy') ,\n"
                + "	count(*) [NumberNewAccount]\n"
                + "from Account\n"
                + "where FORMAT(CreatedTime, 'MM/yyyy') = ?\n"
                + "group by FORMAT(CreatedTime, 'MM/yyyy')";
        Map map = new HashMap();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, MM_YYYY);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                map.put("MonthInYear", rs.getString("MonthInYear"));
                map.put("NumberNewAccount", rs.getInt("NumberNewAccount"));
            } else {
                map.put("MonthInYear", MM_YYYY);
                map.put("NumberNewAccount", 0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return map;
    }

    public int getNumberVisitPage(LocalDate date) {
        int counter = 0;
        String sql = "select * from ViewPage where date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                counter = rs.getInt(2);
            }
        } catch (Exception ex) {
            LOG.warning(ex.getMessage());
        }
        return counter;
    }

    public void plusNumberVisistPage(LocalDate date, int n) {
        String sql = "select * from ViewPage where [date] = ?";
        try (PreparedStatement stmt
                = connection.prepareCall(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            stmt.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int lastVisitNumber = rs.getInt(2);
                lastVisitNumber++;
                rs.updateInt(2, lastVisitNumber);
                rs.updateRow();
            } else {
                rs.moveToInsertRow();
                rs.updateDate(1, java.sql.Date.valueOf(date));
                rs.updateInt(2, n);
                rs.insertRow();
            }
        } catch (SQLException ex) {
            LOG.warning(ex.getMessage());
        }
    }
}
