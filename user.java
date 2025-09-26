package com.campusgig.model;

public class user {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    // Convert to file-friendly format
    public String toFileString() {
        return username + "," + password + "," + email;
    }

    // Convert from file line to User object
    public static User fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 3) {
            return new User(parts[0], parts[1], parts[2]);
        }
        return null;
    }
}
