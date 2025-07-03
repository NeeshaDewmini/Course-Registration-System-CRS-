package controller;

public class LoginController {

    public enum Role {
        STUDENT,
        ADMIN,
        FACULTY,
        INVALID
    }

    public Role login(String username, String password) {
        
        if (username.equals("student") && password.equals("1234")) {
            return Role.STUDENT;
        } else if (username.equals("admin") && password.equals("admin")) {
            return Role.ADMIN;
        } else if (username.equals("faculty") && password.equals("faculty")) {
            return Role.FACULTY;
        } else {
            return Role.INVALID;
        }
    }
}
