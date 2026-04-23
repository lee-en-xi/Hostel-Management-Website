package classFile;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public abstract class User {

    private String userID;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private LocalDate dob;
    private String gender;
    private String role;

    public User() {
    }

    public User(String username, String email, String phoneNumber, String dobStr, String gender, String role) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = getLocalDateDob(dobStr);
        this.gender = gender;
        this.role = role;
    }

    public User(String userID, String username, String email, String phoneNumber, String dobStr, String gender, String role) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = getLocalDateDob(dobStr);
        this.gender = gender;
        this.role = role;
    }

    public User(String userID, String username, String password, String email, String phoneNumber, String dobStr, String gender, String role) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = getLocalDateDob(dobStr);
        this.gender = gender;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(String dobStr) {
        this.dob = getLocalDateDob(dobStr);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getLocalDateDob(String dobStr) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dobStr, dtf);
    }

    public String toUserString() {
        return userID + ":::" + username + ":::" + password + ":::" + email + ":::" + phoneNumber
                + ":::" + dob + ":::" + gender + ":::" + role;
    }

    public static String validate(String id, String pw) {
        String filePath = "src/textFile/user.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        List<String[]> userData = fileHandlerImp.readWholeData(filePath);

        for (String[] line : userData) {
            if (line[0].equals(id) && line[2].equals(pw)) {
                return line[7]; // Return the role if credentials match
            }
        }

        // If no matching credentials found, display a warning message
        String title = "Login";
        String message = "Id and password are not correct, please try again";
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);

        return null;
    }

    public abstract void login(String id);

}
