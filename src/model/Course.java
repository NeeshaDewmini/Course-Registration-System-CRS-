package model;

public class Course {
    private int courseId;
    private String title;
    private int credits;
    private String department;
    private String prerequisites;
    private int maxCapacity;

    public Course() {
    }

    public Course(int courseId, String title, int credits, String department, String prerequisites, int maxCapacity) {
        this.courseId = courseId;
        this.title = title;
        this.credits = credits;
        this.department = department;
        this.prerequisites = prerequisites;
        this.maxCapacity = maxCapacity;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public String toString() {
        return "Course [courseId=" + courseId + ", title=" + title + ", credits=" + credits +
               ", department=" + department + ", prerequisites=" + prerequisites +
               ", maxCapacity=" + maxCapacity + "]";
    }
}
