package com.lly.util.user;

public class UserSession {
    private String UserName;
    private String Password;
    private String role;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
