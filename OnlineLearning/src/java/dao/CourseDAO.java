package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Course;
import model.CoursePricePackage;
import model.Subject;

public class CourseDAO extends DBContext {

    public Course mappingData(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setFirstName(rs.getString("FirstName"));
        account.setLastName(rs.getString("LastName"));
        account.setProfilePictureUrl(rs.getString("ProfilePictureUrl"));

        Course course = new Course();
        course.setCourseId(rs.getInt("courseID"));
        course.setName(rs.getString("Name"));
        course.setDescription(rs.getString("Description"));
        course.setInstructorId(account);
        course.setTinyPictureUrl(rs.getString("TinyPictureUrl"));
        course.setThumbnailUrl(rs.getString("ThumbnailUrl"));
        course.setCreatedDate(rs.getDate("CreatedDate"));
        course.setModifiedDate(rs.getDate("ModifiedDate"));
        course.setPrice(rs.getBigDecimal("Price"));

        return course;
    }

    public int getNumberAllLessonInCourse(int accountID, int courseID) {
        int numLesson = -1;
        try {
            String sql = "SELECT COUNT(*) as NumAllLesson FROM Lesson l JOIN Course c\n"
                    + "ON c.CourseID = l.CourseID JOIN CourseAccount ca\n"
                    + "ON ca.CourseID = c.CourseID \n"
                    + "WHERE ca.AccountID = ? AND c.CourseID = ? AND l.LessonTypeID = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                numLesson = rs.getInt("NumAllLesson");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numLesson;
    }

    public int getNumberLessonLearning(int accountID, int courseID) {
        int numCurrentLesson = 0;
        try {
            String sql = "SELECT COUNT(*) as NumCurrentLesson FROM CompletedLesson cl JOIN Lesson l\n"
                    + "ON l.LessonID = cl.LessonID JOIN Course c\n"
                    + "ON c.CourseID = l.CourseID \n"
                    + "WHERE cl.AccountID = ? AND c.CourseID = ? AND l.lessonTypeID = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            stm.setInt(2, courseID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                numCurrentLesson = rs.getInt("NumCurrentLesson");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numCurrentLesson;
    }

    public Course getCourseByLessonID(int lessonID) {
        try {
            String sql = "select c.Name from lesson l join sublesson sl\n"
                    + "on l.SubLessonID = sl.SubLessonID join course c\n"
                    + "on c.CourseID = sl.CourseID where l.LessonID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Course course = new Course();
                course.setName(rs.getString("Name"));
                return course;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Course> getAllCourseBySubjectID(int subjectID, int status) {
        ArrayList<Course> list = new ArrayList<>();
        try {
            String sql = "select c.*,a.FirstName, a.LastName, a.ProfilePictureUrl, a.Email from Course c, SubjectCourse sc, Account a "
                    + "where c.CourseID = sc.CourseID and a.AccountID = c.InstructorID and sc.SubjectID = ?";
            if (status != -1) {
                sql += " and c.[Status] = ?";
            }
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subjectID);
            if (status != -1) {
                stm.setInt(2, status);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();

                c.setCourseId(rs.getInt("CourseID"));
                c.setName(rs.getString("Name"));
                c.setPrice(rs.getBigDecimal("Price"));
                c.setStatus(rs.getBoolean("Status"));

                Account ac = new Account();
                ac.setFirstName(rs.getString("FirstName"));
                ac.setLastName(rs.getString("LastName"));
                ac.setEmail(rs.getString("Email"));
                ac.setProfilePictureUrl(rs.getString("ProfilePictureUrl"));

                c.setInstructorId(ac);

                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Course> getAllCourse() {
        ArrayList<Course> listCourse = new ArrayList<>();
        try {
            String sql = "select c.*, a.FirstName, a.LastName, a.ProfilePictureUrl \n"
                    + "from Course c join account a\n"
                    + "on a.AccountID = c.InstructorID\n"
                    + "where [status] = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = mappingData(rs);
                ArrayList<Subject> listSubject = new SubjectDAO().getSubjectsByCourseID(course.getCourseId());
                course.setListSubject(listSubject);
                int star = getStarOfCourse(course.getCourseId());
                course.setStar(star);
                int people = getNumberPeopleLearningInCourse(course.getCourseId());
                course.setNumberPeopleLearning(people);
                ArrayList<CoursePricePackage> listPrice = new PricePackageDAO().getListPricePackageOfCourse(course.getCourseId());
                course.setListPrice(listPrice);
                listCourse.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCourse;
    }

    public int getStarOfCourse(int courseId) {
        try {
            String sql = "select sum(rating) / count(*) as Star \n"
                    + "from CourseAccount ca where CourseID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Star");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getNumberPeopleLearningInCourse(int courseId) {
        try {
            String sql = "select count(*) as Number \n"
                    + "from CourseAccount ca where CourseID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Number");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Course> getListCourseBySubject(ArrayList<Integer> listSearchId) {
        ArrayList<Course> listCourse = new ArrayList<>();

        String sql = "select c.*, a.FirstName, a.LastName, a.ProfilePictureUrl\n"
                + "from Course c join account a\n"
                + "on c.InstructorID = a.AccountID\n"
                + "where c.CourseID in (\n"
                + "	select c.CourseID from Course c join account a\n"
                + "	on a.AccountID = c.InstructorID join SubjectCourse sc\n"
                + "	on sc.CourseID = c.CourseID join [Subject] s\n"
                + "	on s.SubjectID = sc.SubjectID join SubjectCategory scate\n"
                + "	on scate.CategoryID = s.CategoryID\n"
                + "	where c.[Status] = 1 \n";

        if (listSearchId.size() == 1) {
            sql += " AND s.SubjectID = ? ";
        } else if (listSearchId.size() >= 2) {
            for (int i = 0; i < listSearchId.size(); i++) {
                if (i == 0) {
                    sql += " AND ( s.SubjectID = ? ";
                } else if (i == listSearchId.size() - 1) {
                    sql += " OR s.SubjectID = ? )";
                } else {
                    sql += " OR s.SubjectID = ? ";
                }
            }
        }
        sql += "group by c.courseID)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i = 0; i < listSearchId.size(); i++) {
                ps.setInt(i + 1, listSearchId.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = mappingData(rs);
                ArrayList<Subject> listSubject = new SubjectDAO().getSubjectsByCourseID(course.getCourseId());
                course.setListSubject(listSubject);
                int star = getStarOfCourse(course.getCourseId());
                course.setStar(star);
                int people = getNumberPeopleLearningInCourse(course.getCourseId());
                course.setNumberPeopleLearning(people);
                ArrayList<CoursePricePackage> listPrice = new PricePackageDAO().getListPricePackageOfCourse(course.getCourseId());
                course.setListPrice(listPrice);
                listCourse.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCourse;
    }

    public ArrayList<Course> getListCourseByText(String txtSearch) {
        ArrayList<Course> listCourse = new ArrayList<>();

        String sql = "select c.*, a.FirstName, a.LastName, a.ProfilePictureUrl\n"
                + "from Course c join account a\n"
                + "on a.AccountID = c.InstructorID\n"
                + "where [status] = 1 and c.name like ? ";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = mappingData(rs);
                ArrayList<Subject> listSubject = new SubjectDAO().getSubjectsByCourseID(course.getCourseId());
                course.setListSubject(listSubject);
                int star = getStarOfCourse(course.getCourseId());
                course.setStar(star);
                int people = getNumberPeopleLearningInCourse(course.getCourseId());
                course.setNumberPeopleLearning(people);
                ArrayList<CoursePricePackage> listPrice = new PricePackageDAO().getListPricePackageOfCourse(course.getCourseId());
                course.setListPrice(listPrice);
                listCourse.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCourse;
    }

    public ArrayList<Course> getListCourseByPrice(ArrayList<String> listPrice) {
        ArrayList<Course> listCourse = new ArrayList<>();

        String sql = "select c.*, a.FirstName, a.LastName, a.ProfilePictureUrl\n"
                + "from Course c join account a\n"
                + "on a.AccountID = c.InstructorID\n"
                + "where [status] = 1 ";
        if (listPrice.size() != 2) {
            if (listPrice.get(0).equalsIgnoreCase("Free")) {
                sql += " AND Price = 0 ";
            } else {
                sql += " AND Price > 0 ";
            }
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = mappingData(rs);
                ArrayList<Subject> listSubject = new SubjectDAO().getSubjectsByCourseID(course.getCourseId());
                course.setListSubject(listSubject);
                int star = getStarOfCourse(course.getCourseId());
                course.setStar(star);
                int people = getNumberPeopleLearningInCourse(course.getCourseId());
                course.setNumberPeopleLearning(people);
                ArrayList<CoursePricePackage> listPricePackage = new PricePackageDAO().getListPricePackageOfCourse(course.getCourseId());
                course.setListPrice(listPricePackage);
                listCourse.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCourse;
    }

    public Course getCourseByCourseId(int id) {

        String sql = "select c.*, a.FirstName, a.LastName, a.ProfilePictureUrl\n"
                + "from Course c join account a\n"
                + "on a.AccountID = c.InstructorID\n"
                + "where [status] = 1 and courseID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Course course = mappingData(rs);
                course.setVideoIntroduce(rs.getString("VideoIntroduce"));
                ArrayList<String> objectives = getObjectives(course.getCourseId());
                course.setObjectives(objectives);
                course.setAboutCourse(rs.getString("AboutCourse"));
                ArrayList<Subject> listSubject = new SubjectDAO().getSubjectsByCourseID(course.getCourseId());
                course.setListSubject(listSubject);
                int star = getStarOfCourse(course.getCourseId());
                course.setStar(star);
                int people = getNumberPeopleLearningInCourse(course.getCourseId());
                course.setNumberPeopleLearning(people);
                return course;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean isRegister(int accountID, int id) {
        String sql = "select * from courseaccount\n"
                + "where accountid = ? and CourseID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, accountID);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<String> getObjectives(int courseId) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "select * from Objective\n"
                + "where CourseID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("ObjectiveName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void deleteCourse(int id) {
        try {
            String sql = "DELETE [Course] WHERE [CourseID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getlCourseIdsublessonID(int subLessonId) {
        Course c = new Course();
        try {
            String sql = "  select s.CourseID from SubLesson s where s.SubLessonID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, subLessonId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                c.setCourseId(rs.getInt("CourseID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c.getCourseId();
    }

    public ArrayList<Course> getTopFeatureCourse(int courseId) {
        ArrayList<Course> listCourse = new ArrayList<>();
        try {
            String sql = "select c.*, a.FirstName, a.LastName, a.ProfilePictureUrl from course c join account a  on\n"
                    + "c.instructorid = a.accountid\n"
                    + "where courseid != ? and courseid in (\n"
                    + "select distinct c.courseid from Course c join account a\n"
                    + "on a.AccountID = c.InstructorID\n"
                    + "join subjectcourse sc\n"
                    + "on sc.courseid = c.courseid\n"
                    + "where [status] = 1 and sc.subjectid in (\n"
                    + "	select subjectid from course c join subjectcourse sc\n"
                    + "	on c.courseid = sc.courseid\n"
                    + "	where c.courseid = ?\n"
                    + "))";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            stm.setInt(2, courseId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = mappingData(rs);
                ArrayList<Subject> listSubject = new SubjectDAO().getSubjectsByCourseID(course.getCourseId());
                course.setListSubject(listSubject);
                int star = getStarOfCourse(course.getCourseId());
                course.setStar(star);
                int people = getNumberPeopleLearningInCourse(course.getCourseId());
                course.setNumberPeopleLearning(people);
                listCourse.add(course);
            }

            Collections.sort(listCourse, new Comparator<Course>() {
                @Override
                public int compare(Course t, Course t1) {
                    if (t.getNumberPeopleLearning() < t1.getNumberPeopleLearning()) {
                        return 1;
                    } else if (t.getNumberPeopleLearning() == t1.getNumberPeopleLearning()) {
                        if (t.getStar() < t1.getStar()) {
                            return 1;
                        }
                    } else {
                        return 0;
                    }
                    return -1;
                }

            });
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        ArrayList<Course> listCourseFeature = new ArrayList<>();
        for (Course course : listCourse) {
            if (count < 3) {
                listCourseFeature.add(course);
                count++;
            }
        }
        return listCourseFeature;
    }

    public ArrayList<Course> getAllTopFeatureCourse() {
        ArrayList<Course> listCourse = getAllCourse();
        Collections.sort(listCourse, new Comparator<Course>() {
            @Override
            public int compare(Course t, Course t1) {
                if (t.getNumberPeopleLearning() < t1.getNumberPeopleLearning()) {
                    return 1;
                } else if (t.getNumberPeopleLearning() == t1.getNumberPeopleLearning()) {
                    if (t.getStar() < t1.getStar()) {
                        return 1;
                    }
                } else {
                    return 0;
                }
                return -1;
            }

        });
        int count = 0;
        ArrayList<Course> listCourseFeature = new ArrayList<>();
        for (Course course : listCourse) {
            if (count < 3) {
                listCourseFeature.add(course);
                count++;
            }
        }
        return listCourseFeature;
    }
}
