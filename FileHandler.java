package com.campusgig.utils;

import com.campusgig.model.User;
import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String FILE_NAME = "students.txt";

    // Save user to file
    public static void saveUser(User user) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
        writer.write(user.toFileString());
        writer.newLine();
        writer.close();
    }

    // Read all users
    public static List<User> getAllUsers() throws IOException {
        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = reader.readLine()) != null) {
            User user = User.fromFileString(line);
            if (user != null) users.add(user);
        }
        reader.close();
        return users;
    }

    // Check if user exists (for login)
    public static User validateUser(String username, String password) throws IOException {
        for (User u : getAllUsers()) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}
