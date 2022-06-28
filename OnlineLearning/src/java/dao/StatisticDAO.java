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

public class StatisticDAO extends DBContext {

    private static final Logger LOG = Logger.getLogger(StatisticDAO.class.getName());

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

    /**
     * Calculate income up to now.
     *
     * @return income up to now
     */
    public double getAllEarning() {
        String sql = "select sum(Amount) from TransactionHistory";
        double income = 0;
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            income = rs.next() ? rs.getDouble(1) : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return income;
    }

    /**
     * Only used for json serialize.
     */
    private class RevenueArgs {

        private int month;
        private int year;
        private double revenue;

        public RevenueArgs(int month, int year, double revenue) {
            this.month = month;
            this.year = year;
            this.revenue = revenue;
        }
    }

    public List calculateRevenues(int m1, int y1, int m2, int y2) throws SQLException {
        List<RevenueArgs> ls = new ArrayList<>();
        String sql = "select\n"
                + "	MONTH(TrasactionTime) [Month],\n"
                + "     YEAR(TrasactionTime) [Year],\n"
                + "	SUM(Amount) [Revenue]\n"
                + "from TransactionHistory\n"
                + "where \n"
                + "	Year(TrasactionTime) between ? and ?\n"
                + "group by MONTH(TrasactionTime), YEAR(TrasactionTime)\n"
                + "order by [Month], [Year]\n"
                + "\n"
                + "\n"
                + "select * from TransactionHistory\n"
                + "";

        for (int year = y1; year <= y2; year++) {
            int start = 1;
            int end = 12;
            if (year == y1) {
                start = m1;
            }
            if (year == y2) {
                end = m2;
            }
            for (int m = start; m <= end; m++) {
                RevenueArgs r = new RevenueArgs(m, year, 0);
                ls.add(r);
            }
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, y1);
            stmt.setInt(2, y2);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int m = rs.getInt("Month");
                int y = rs.getInt("Year");
                double revenue = rs.getDouble("Revenue");

                for (RevenueArgs r : ls) {
                    if (r.month == m && r.year == y) {
                        r.revenue = revenue;
                        break;
                    }
                }
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
    @Deprecated
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
            LOG.warning(ex.getMessage());
        }

        return map;
    }

    public int countNewAccount(int month, int year) {
        String sql = "select count(AccountID) from Account "
                + "where MONTH(CreatedTime) = ? and YEAR(CreatedTime) = ?";
        int n = 0;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, month);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();
            n = rs.next() ? rs.getInt(1) : 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return n;
    }

    /**
     * Returns the number access the web app in a day.
     *
     * @param date
     * @return the number access in the passed date.
     */
    public int getNumberVisitPage(LocalDate date) {
        int counter = 0;
        String sql = "select * from PageViewCounter where date = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            counter = rs.next() ? rs.getInt(2) : 0;
        } catch (Exception ex) {
            LOG.warning(ex.getMessage());
        }
        return counter;
    }

    public void plusNumberVisitPage(LocalDate date, int n) {
        String sql = "select * from PageViewCounter where [date] = ?";
        try (PreparedStatement stmt
                = connection.prepareCall(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            stmt.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int newNumberVisitPage = rs.getInt(2) + n;
                rs.updateInt(2, newNumberVisitPage);
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

    class AmountAccountEnrollInSubjectArgs {

        private int subjectId;
        private String subjectName;
        private int amountEnrolled;
        private int totalAccount;

        public AmountAccountEnrollInSubjectArgs(int subjectId, String subjectName, int amountEnrolled, int totalAccount) {
            this.subjectId = subjectId;
            this.subjectName = subjectName;
            this.amountEnrolled = amountEnrolled;
            this.totalAccount = totalAccount;
        }
    }

    public List<AmountAccountEnrollInSubjectArgs> getAmountEnrollInAllSubject() {
        String sqlCountTotalAccount = "select count(AccountID) from Account";
        int totalAccount = 0;
        List<AmountAccountEnrollInSubjectArgs> ls = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sqlCountTotalAccount)) {
            if (rs.next()) {
                totalAccount = rs.getInt(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (totalAccount == 0) {
            return ls;
        }

        String sqlSelectSubject = "select SubjectID, Name from Subject";

        String sqlCountAccountOfSubject = "select "
                + "	count(t.AccountID) [AmountAccountEnroll]\n"
                + "from TransactionHistory as t\n"
                + "	inner join Course as c on t.CourseID = c.CourseID\n"
                + "	inner join  SubjectCourse as sc on t.CourseID = sc.CourseID\n"
                + "where SubjectID = ?\n"
                + "group by sc.SubjectID";
        try (PreparedStatement countAccountStmt = connection.prepareStatement(sqlCountAccountOfSubject);
                Statement selectSubjectStmt = connection.createStatement();
                ResultSet subjectTable = selectSubjectStmt.executeQuery(sqlSelectSubject)) {
            while (subjectTable.next()) {
                int subjectId = subjectTable.getInt(1);
                String subjectName = subjectTable.getString(2);

                countAccountStmt.setInt(1, subjectId);
                ResultSet rs = countAccountStmt.executeQuery();
                int amountAccountEnroll = (rs.next()) ? rs.getInt(1) : 0;
                ls.add(new AmountAccountEnrollInSubjectArgs(subjectId, subjectName, amountAccountEnroll, totalAccount));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ls;
    }
}
