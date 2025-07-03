package controller;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import model.Course;
import model.Enrollment;
import model.Student;

import java.util.List;

public class RegistrationController {
    private StudentDAO studentDAO = new StudentDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public StudentDAO getStudentDAO() {
    return studentDAO;
    }

    public CourseDAO getCourseDAO() {
        return courseDAO;
    }

    // list all students
    public List<Student> getAllStudents() throws Exception {
        return studentDAO.getAllStudents();
    }

    // list all courses
    public List<Course> getAllCourses() throws Exception {
        return courseDAO.getAllCourses();
    }

    // enroll student
    public String enrollStudent(int studentId, int courseId, String semester) throws Exception {
        // get course details
        Course course = null;
        for (Course c : courseDAO.getAllCourses()) {
            if (c.getCourseId() == courseId) {
                course = c;
                break;
            }
        }
        if (course == null) {
            return "Course not found.";
        }

        // check prerequisites
        if (course.getPrerequisites() != null && !course.getPrerequisites().isEmpty()) {
            boolean hasPrereq = false;
            List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
            for (Enrollment e : enrollments) {
                if (e.getStudentId() == studentId) {
                    for (Course c : courseDAO.getAllCourses()) {
                        if (c.getCourseId() == e.getCourseId() &&
                            c.getTitle().equalsIgnoreCase(course.getPrerequisites())) {
                            hasPrereq = true;
                            break;
                        }
                    }
                }
                if (hasPrereq) break;
            }
            if (!hasPrereq) {
                return "Prerequisite not satisfied.";
            }
        }

        // check course capacity
        int currentEnrollments = 0;
        for (Enrollment e : enrollmentDAO.getAllEnrollments()) {
            if (e.getCourseId() == courseId) {
                currentEnrollments++;
            }
        }
        if (currentEnrollments >= course.getMaxCapacity()) {
            return "Course is full.";
        }

        // if all good, enroll
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        enrollment.setSemester(semester);
        enrollment.setGrade("NA");
        enrollmentDAO.enrollStudent(enrollment);

        return "Enrollment successful!";
    }

    // drop enrollment
    public String dropEnrollment(int enrollmentId) throws Exception {
        enrollmentDAO.dropEnrollment(enrollmentId);
        return "Enrollment dropped.";
    }
}
