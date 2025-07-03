package view;

import controller.RegistrationController;
import model.Course;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminDashboardView {

    public static void showAdminDashboard() {
        JFrame frame = new JFrame("Admin Dashboard");
        frame.setLayout(new BorderLayout());

        RegistrationController rc = new RegistrationController();

        // Tabs: Students & Courses
        JTabbedPane tabbedPane = new JTabbedPane();

        // === Students Panel ===
        JPanel studentsPanel = new JPanel(new BorderLayout());
        JTable studentsTable = new JTable();
        DefaultTableModel studentModel = new DefaultTableModel();
        studentModel.setColumnIdentifiers(new String[]{
                "Student ID", "Name", "DOB", "Program", "Year", "Contact"
        });
        studentsTable.setModel(studentModel);

        JPanel studentButtonPanel = new JPanel();
        JButton addStudentBtn = new JButton("Add Student");
        JButton editStudentBtn = new JButton("Edit Selected Student");
        JButton deleteStudentBtn = new JButton("Delete Selected Student");
        studentButtonPanel.add(addStudentBtn);
        studentButtonPanel.add(editStudentBtn);
        studentButtonPanel.add(deleteStudentBtn);

        studentsPanel.add(new JScrollPane(studentsTable), BorderLayout.CENTER);
        studentsPanel.add(studentButtonPanel, BorderLayout.SOUTH);

        // === Courses Panel ===
        JPanel coursesPanel = new JPanel(new BorderLayout());
        JTable coursesTable = new JTable();
        DefaultTableModel courseModel = new DefaultTableModel();
        courseModel.setColumnIdentifiers(new String[]{
                "Course ID", "Title", "Credits", "Department", "Prerequisites", "Max Capacity"
        });
        coursesTable.setModel(courseModel);

        JPanel courseButtonPanel = new JPanel();
        JButton addCourseBtn = new JButton("Add Course");
        JButton editCourseBtn = new JButton("Edit Selected Course");
        JButton deleteCourseBtn = new JButton("Delete Selected Course");
        courseButtonPanel.add(addCourseBtn);
        courseButtonPanel.add(editCourseBtn);
        courseButtonPanel.add(deleteCourseBtn);

        coursesPanel.add(new JScrollPane(coursesTable), BorderLayout.CENTER);
        coursesPanel.add(courseButtonPanel, BorderLayout.SOUTH);

        tabbedPane.add("Students", studentsPanel);
        tabbedPane.add("Courses", coursesPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            List<Student> students = rc.getAllStudents();
            for (Student s : students) {
                studentModel.addRow(new Object[]{
                        s.getStudentId(), s.getName(), s.getDob(), s.getProgram(), s.getYear(), s.getContact()
                });
            }

            List<Course> courses = rc.getAllCourses();
            for (Course c : courses) {
                courseModel.addRow(new Object[]{
                        c.getCourseId(), c.getTitle(), c.getCredits(), c.getDepartment(),
                        c.getPrerequisites(), c.getMaxCapacity()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // === STUDENT CRUD ===
        addStudentBtn.addActionListener(ae -> {
            JTextField nameField = new JTextField();
            JTextField dobField = new JTextField();
            JTextField programField = new JTextField();
            JTextField yearField = new JTextField();
            JTextField contactField = new JTextField();

            Object[] message = {
                    "Name:", nameField,
                    "DOB (yyyy-mm-dd):", dobField,
                    "Program:", programField,
                    "Year:", yearField,
                    "Contact:", contactField
            };

            int option = JOptionPane.showConfirmDialog(frame, message, "Add Student", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    Student s = new Student();
                    s.setName(nameField.getText());
                    s.setDob(dobField.getText());
                    s.setProgram(programField.getText());
                    s.setYear(Integer.parseInt(yearField.getText()));
                    s.setContact(contactField.getText());
                    rc.getStudentDAO().addStudent(s);
                    // Refresh table to get correct IDs
                    studentModel.setRowCount(0);
                    List<Student> students = rc.getAllStudents();
                    for (Student st : students) {
                        studentModel.addRow(new Object[]{
                                st.getStudentId(), st.getName(), st.getDob(), st.getProgram(), st.getYear(), st.getContact()
                        });
                    }
                    JOptionPane.showMessageDialog(frame, "Student added successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error adding student.");
                }
            }
        });

        editStudentBtn.addActionListener(ae -> {
            int row = studentsTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Please select a student.");
                return;
            }
            int studentId = (int) studentModel.getValueAt(row, 0);

            JTextField nameField = new JTextField(studentModel.getValueAt(row,1).toString());
            JTextField dobField = new JTextField(studentModel.getValueAt(row,2).toString());
            JTextField programField = new JTextField(studentModel.getValueAt(row,3).toString());
            JTextField yearField = new JTextField(studentModel.getValueAt(row,4).toString());
            JTextField contactField = new JTextField(studentModel.getValueAt(row,5).toString());

            Object[] message = {
                    "Name:", nameField,
                    "DOB:", dobField,
                    "Program:", programField,
                    "Year:", yearField,
                    "Contact:", contactField
            };

            int option = JOptionPane.showConfirmDialog(frame, message, "Edit Student", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    Student s = new Student();
                    s.setStudentId(studentId);
                    s.setName(nameField.getText());
                    s.setDob(dobField.getText());
                    s.setProgram(programField.getText());
                    s.setYear(Integer.parseInt(yearField.getText()));
                    s.setContact(contactField.getText());
                    rc.getStudentDAO().updateStudent(s);

                    // Refresh table to get correct data and IDs
                    studentModel.setRowCount(0);
                    List<Student> students = rc.getAllStudents();
                    for (Student st : students) {
                        studentModel.addRow(new Object[]{
                                st.getStudentId(), st.getName(), st.getDob(), st.getProgram(), st.getYear(), st.getContact()
                        });
                    }

                    JOptionPane.showMessageDialog(frame, "Student updated!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        deleteStudentBtn.addActionListener(ae -> {
            int row = studentsTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Please select a student.");
                return;
            }
            int studentId = (int) studentModel.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(frame, "Delete student?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    rc.getStudentDAO().deleteStudent(studentId);
                    studentModel.removeRow(row);
                    JOptionPane.showMessageDialog(frame, "Student deleted!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // === COURSE CRUD ===
        addCourseBtn.addActionListener(ae -> {
            JTextField titleField = new JTextField();
            JTextField creditsField = new JTextField();
            JTextField deptField = new JTextField();
            JTextField prereqField = new JTextField();
            JTextField maxCapField = new JTextField();

            Object[] message = {
                    "Title:", titleField,
                    "Credits:", creditsField,
                    "Department:", deptField,
                    "Prerequisites:", prereqField,
                    "Max Capacity:", maxCapField
            };

            int option = JOptionPane.showConfirmDialog(frame, message, "Add Course", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    Course c = new Course();
                    c.setTitle(titleField.getText());
                    c.setCredits(Integer.parseInt(creditsField.getText()));
                    c.setDepartment(deptField.getText());
                    c.setPrerequisites(prereqField.getText());
                    c.setMaxCapacity(Integer.parseInt(maxCapField.getText()));
                    rc.getCourseDAO().addCourse(c);
                    // Refresh table to get correct IDs
                    courseModel.setRowCount(0);
                    List<Course> courses = rc.getAllCourses();
                    for (Course co : courses) {
                        courseModel.addRow(new Object[]{
                                co.getCourseId(), co.getTitle(), co.getCredits(), co.getDepartment(),
                                co.getPrerequisites(), co.getMaxCapacity()
                        });
                    }
                    JOptionPane.showMessageDialog(frame, "Course added!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        editCourseBtn.addActionListener(ae -> {
            int row = coursesTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Please select a course.");
                return;
            }
            int courseId = (int) courseModel.getValueAt(row, 0);

            JTextField titleField = new JTextField(courseModel.getValueAt(row,1).toString());
            JTextField creditsField = new JTextField(courseModel.getValueAt(row,2).toString());
            JTextField deptField = new JTextField(courseModel.getValueAt(row,3).toString());
            JTextField prereqField = new JTextField(courseModel.getValueAt(row,4).toString());
            JTextField maxCapField = new JTextField(courseModel.getValueAt(row,5).toString());

            Object[] message = {
                    "Title:", titleField,
                    "Credits:", creditsField,
                    "Department:", deptField,
                    "Prerequisites:", prereqField,
                    "Max Capacity:", maxCapField
            };

            int option = JOptionPane.showConfirmDialog(frame, message, "Edit Course", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try {
                    Course c = new Course();
                    c.setCourseId(courseId);
                    c.setTitle(titleField.getText());
                    c.setCredits(Integer.parseInt(creditsField.getText()));
                    c.setDepartment(deptField.getText());
                    c.setPrerequisites(prereqField.getText());
                    c.setMaxCapacity(Integer.parseInt(maxCapField.getText()));
                    rc.getCourseDAO().updateCourse(c);

                    // Refresh table to get correct data and IDs
                    courseModel.setRowCount(0);
                    List<Course> courses = rc.getAllCourses();
                    for (Course co : courses) {
                        courseModel.addRow(new Object[]{
                                co.getCourseId(), co.getTitle(), co.getCredits(), co.getDepartment(),
                                co.getPrerequisites(), co.getMaxCapacity()
                        });
                    }

                    JOptionPane.showMessageDialog(frame, "Course updated!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        deleteCourseBtn.addActionListener(ae -> {
            int row = coursesTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Please select a course.");
                return;
            }
            int courseId = (int) courseModel.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(frame, "Delete course?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    rc.getCourseDAO().deleteCourse(courseId);
                    courseModel.removeRow(row);
                    JOptionPane.showMessageDialog(frame, "Course deleted!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
