package model;

public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private String grade;
    private String semester;

    public Enrollment() {
    }

    public Enrollment(int enrollmentId, int studentId, int courseId, String grade, String semester) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
        this.semester = semester;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "Enrollment [enrollmentId=" + enrollmentId + ", studentId=" + studentId +
               ", courseId=" + courseId + ", grade=" + grade + ", semester=" + semester + "]";
    }
}
