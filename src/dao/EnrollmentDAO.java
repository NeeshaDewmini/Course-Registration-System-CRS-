package dao;

import model.Enrollment;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    public List<Enrollment> getAllEnrollments() throws Exception {
        List<Enrollment> enrollments = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM enrollments";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Enrollment e = new Enrollment(
                rs.getInt("enrollment_id"),
                rs.getInt("student_id"),
                rs.getInt("course_id"),
                rs.getString("grade"),
                rs.getString("semester")
            );
            enrollments.add(e);
        }
        con.close();
        return enrollments;
    }

    public void enrollStudent(Enrollment enrollment) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO enrollments (student_id, course_id, grade, semester) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, enrollment.getStudentId());
        ps.setInt(2, enrollment.getCourseId());
        ps.setString(3, enrollment.getGrade());
        ps.setString(4, enrollment.getSemester());
        ps.executeUpdate();
        con.close();
    }

    public void dropEnrollment(int enrollmentId) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, enrollmentId);
        ps.executeUpdate();
        con.close();
    }
}
