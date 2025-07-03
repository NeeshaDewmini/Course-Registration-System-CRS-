package view;

import controller.LoginController;
import controller.LoginController.Role;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginView {
    public static void main(String[] args) {
        JFrame frame = new JFrame("CRS Login");
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);
        JButton loginButton = new JButton("Login");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(loginButton);

        frame.setContentPane(panel);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        loginButton.addActionListener((ActionEvent e) -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            LoginController loginController = new LoginController();
            Role role = loginController.login(username, password);

            switch (role) {
                case STUDENT:
                    JOptionPane.showMessageDialog(frame, "Welcome Student!");
                    frame.dispose();
                    StudentDashboardView.showStudentDashboard(username);
                    break;
                case ADMIN:
                    JOptionPane.showMessageDialog(frame, "Welcome Admin!");
                    frame.dispose();
                    AdminDashboardView.showAdminDashboard();
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Invalid credentials!");
            }
        });
    }
}
