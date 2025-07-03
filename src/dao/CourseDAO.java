package dao;

import model.Course;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public List<Course> getAllCourses() throws Exception {
        List<Course> courses = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM courses";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Course c = new Course(
                rs.getInt("course_id"),
                rs.getString("title"),
                rs.getInt("credits"),
                rs.getString("department"),
                rs.getString("prerequisites"),
                rs.getInt("max_capacity")
            );
            courses.add(c);
        }
        con.close();
        return courses;
    }

    public void addCourse(Course course) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO courses (title, credits, department, prerequisites, max_capacity) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, course.getTitle());
        ps.setInt(2, course.getCredits());
        ps.setString(3, course.getDepartment());
        ps.setString(4, course.getPrerequisites());
        ps.setInt(5, course.getMaxCapacity());
        ps.executeUpdate();
        con.close();
    }

    // Update a course
public void updateCourse(Course course) throws Exception {
    Connection con = DBConnection.getConnection();
    String sql = "UPDATE courses SET title=?, credits=?, department=?, prerequisites=?, max_capacity=? WHERE course_id=?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, course.getTitle());
    ps.setInt(2, course.getCredits());
    ps.setString(3, course.getDepartment());
    ps.setString(4, course.getPrerequisites());
    ps.setInt(5, course.getMaxCapacity());
    ps.setInt(6, course.getCourseId());
    ps.executeUpdate();
    con.close();
}

// Delete a course
public void deleteCourse(int courseId) throws Exception {
    Connection con = DBConnection.getConnection();
    String sql = "DELETE FROM courses WHERE course_id=?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, courseId);
    ps.executeUpdate();
    con.close();
}

}
