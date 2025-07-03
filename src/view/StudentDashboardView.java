package view;

import controller.RegistrationController;
import model.Course;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class StudentDashboardView {
    public static void showStudentDashboard(String username) {
        JFrame frame = new JFrame("Student Dashboard");
        RegistrationController rc = new RegistrationController();

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Course ID", "Title", "Credits", "Dept", "Prerequisites", "Max Capacity"});
        table.setModel(model);

        JButton enrollButton = new JButton("Enroll in Selected Course");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JScrollPane(table));
        panel.add(enrollButton);

        frame.setContentPane(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        try {
            List<Course> courses = rc.getAllCourses();
            for (Course c : courses) {
                model.addRow(new Object[]{
                    c.getCourseId(),
                    c.getTitle(),
                    c.getCredits(),
                    c.getDepartment(),
                    c.getPrerequisites(),
                    c.getMaxCapacity()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        enrollButton.addActionListener(ae -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int courseId = (int) model.getValueAt(row, 0);
                try {
                    String result = rc.enrollStudent(1, courseId, "Fall2025"); // Hardcoded student ID here
                    JOptionPane.showMessageDialog(frame, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a course to enroll.");
            }
        });
    }
}
