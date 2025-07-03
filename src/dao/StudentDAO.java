package dao;

import model.Student;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getAllStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM students";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Student s = new Student(
                rs.getInt("student_id"),
                rs.getString("name"),
                rs.getString("dob"),
                rs.getString("program"),
                rs.getInt("year"),
                rs.getString("contact")
            );
            students.add(s);
        }
        con.close();
        return students;
    }

    public void addStudent(Student student) throws Exception {
        Connection con = DBConnection.getConnection();
        String sql = "INSERT INTO students (name, dob, program, year, contact) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, student.getName());
        ps.setString(2, student.getDob());
        ps.setString(3, student.getProgram());
        ps.setInt(4, student.getYear());
        ps.setString(5, student.getContact());
        ps.executeUpdate();
        con.close();
    }


    // Update a student
public void updateStudent(Student student) throws Exception {
    Connection con = DBConnection.getConnection();
    String sql = "UPDATE students SET name=?, dob=?, program=?, year=?, contact=? WHERE student_id=?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setString(1, student.getName());
    ps.setString(2, student.getDob());
    ps.setString(3, student.getProgram());
    ps.setInt(4, student.getYear());
    ps.setString(5, student.getContact());
    ps.setInt(6, student.getStudentId());
    ps.executeUpdate();
    con.close();
}

// Delete a student
public void deleteStudent(int studentId) throws Exception {
    Connection con = DBConnection.getConnection();
    String sql = "DELETE FROM students WHERE student_id=?";
    PreparedStatement ps = con.prepareStatement(sql);
    ps.setInt(1, studentId);
    ps.executeUpdate();
    con.close();
}

}
