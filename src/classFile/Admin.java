package classFile;

import GUIDesignAdmin.AdminMainFrame;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Admin extends User {

    private static final FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

    ;

    public Admin() {
    }

    public Admin(String username, String password, String email, String phoneNumber, String dobStr, String gender, String role) {
        super(username, password, email, phoneNumber, dobStr, gender, role);
    }

    public Admin(String userID, String username, String password, String email, String phoneNumber, String dobStr, String gender, String role) {
        super(userID, username, password, email, phoneNumber, dobStr, gender, role);
    }

    //Login to Admin Page
    @Override
    public void login(String id) {
        AdminMainFrame adminMainFrame = new AdminMainFrame(id);
        adminMainFrame.setVisible(true);
    }

    @Override
    public String toString() {
        return getUserID() + ":::"
                + getUsername() + ":::"
                + getEmail() + ":::"
                + getPhoneNumber() + ":::"
                + getDob() + ":::"
                + getGender();
    }

    //Register user
    public void register(User u) {
        if (u.getPassword() == null) {
            u.setPassword(AdminUtil.defaultPassword);
        }
        if (u.getUserID() == null) {
            u.setUserID(AdminUtil.generateID(u.getRole()));
        }
        if (u instanceof Runner) {
            ((Runner) u).setWorkStatus("offDuty");
        }

        if (u instanceof Vendor) {
            String filePath = fileHandlerImp.createTextFile("store.txt");
            fileHandlerImp.writeFileNewLine(filePath, u.getUserID() + ":::" + u.getUserID());
        }

        String filePath1 = "user.txt";
        String filePath2 = u.getRole() + ".txt";

        String title = "Registration";
        String message = "User (" + u.getRole() + ") Register Successfully";

        filePath1 = fileHandlerImp.createTextFile(filePath1);
        filePath2 = fileHandlerImp.createTextFile(filePath2);

        if (!(filePath1 == null || filePath2 == null)) {
            boolean result = fileHandlerImp.writeFileNewLine(filePath1, u.toUserString()) && fileHandlerImp.writeFileNewLine(filePath2, u.toString());

            if (result) {
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
            } else {
                message = "Register Unsuccessful";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
            }
        } else {
            title += "(File(s))";
            message = "File(s) Creation Fail(s)";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        }
    }

    //Update the information of user
    public void update(User u) {
        System.out.println("update method(u): " + u);
        System.out.println("update method(u): " + u.toUserString());
        String filePath1 = "src/textFile/user.txt";
        String filePath2 = "src/textFile/" + u.getRole() + ".txt";

        boolean result = fileHandlerImp.updateFileLine(filePath1, u.getUserID(), u.toUserString()) && fileHandlerImp.updateFileLine(filePath2, u.getUserID(), u.toString());

        String title = "Update";
        String message = "User (" + u.getRole() + ") Update Successfully";
        if (result) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
        } else {
            message = "Update Unsuccessful";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        }
    }

    //Read the users' data from the text file
    public List<String[]> readUsers(String role) {
        String filePath = "src/textFile/" + role + ".txt";

        List<String[]> rows = new ArrayList<>();
        List<String> usersList = new FileHandlerImp().readWholeFile(filePath);

        if (role.equals("customer")) {
            String[] columns = {"UserID", "Username", "Email", "Phone.No", "DOB", "Gender", "Balance(RM)"};
            rows.add(columns);
            for (String userLine : usersList) {
                rows.add(userLine.split(":::"));
            }
        } else if (role.equals("vendor")) {
            String[] columns = {"UserID", "Username", "Email", "Phone.No", "DOB", "Gender"};
            rows.add(columns);
            for (String userLine : usersList) {
                rows.add(userLine.split(":::"));
            }
        } else if (role.equals("runner")) {
            String[] columns = {"UserID", "Username", "Email", "Status"};
            rows.add(columns);
            for (String userLine : usersList) {
                rows.add(userLine.split(":::"));
            }
        }

        return rows;
    }

    //Delete user
    public void delete(String id) {
        String filePath1 = "src/textFile/user.txt";

        String role = AdminUtil.getUserRole(filePath1, id);

        String title = "Delete";
        String message = "User (" + role + ") Delete Successfully";

        if (role == null) {
            message = "Delete Unsuccessful\nUser not found";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
            return;
        }

        String filePath2 = "src/textFile/" + role + ".txt";
        String result1 = fileHandlerImp.deleteFileLine(filePath1, id);
        String result2 = fileHandlerImp.deleteFileLine(filePath2, id);

        boolean result = false;
        if (!(result1.equals("false") || result2.equals("false"))) {
            result = true;
        }

        if (result) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
        } else {
            message = "Delete Unsuccessful\nUser not found";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        }

    }

    public void dltstorename(String id) {
        fileHandlerImp.deleteFileLine("src/textFile/store.txt", id);
    }

    //Read the data of Admin
    public static Admin readAdminProfile(String id) {
        Admin admin = new Admin();
        String filePath = "src/textFile/user.txt";

        String line = fileHandlerImp.readFileLine(filePath, id);
        String[] data = line.split(":::"); //0-id, 1-name, 2-pw, 3-email, 4-phone.no, 5-dob, 6-gender, 7-role
        admin.setUserID(id); //id
        admin.setUsername(data[1].trim()); //name
        admin.setPassword(data[2].trim()); //password
        admin.setEmail(data[3].trim()); //email
        admin.setPhoneNumber(data[4].trim()); //phone number
        admin.setDob(data[5].trim()); //dob
        admin.setGender(data[6].trim()); //gender
        admin.setRole("admin"); //role

        return admin;
    }

    public void topUp(String id, double topUpAmount) {
        String filePath = "src/textfile/customer.txt";

        String line = fileHandlerImp.readFileLine(filePath, id);

        String title = "Top-Up";
        String message = "Top-Up Successful";

        String[] data = new String[7];
        if (line == null) {
            message = "User Not Found";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        } else {
            data = line.split(":::");
            //top-up
            double newBalance = Double.parseDouble(data[data.length - 1].trim()) + topUpAmount;
            //set format
            DecimalFormat df = new DecimalFormat("#.00");
            data[data.length - 1] = df.format(newBalance);

            StringJoiner sj = new StringJoiner(":::");
            for (String element : data) {
                sj.add(element);
            }
            String updatedData = sj.toString();

            boolean b = fileHandlerImp.updateFileLine(filePath, id, updatedData);
            if (b) {
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
            } else {
                message = "Top-Up Unsuccessful";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
            }
        }

        Transaction transaction = new Transaction(getUserID(), data[0].trim(), topUpAmount, "Top-Up");

        String briefNotificationMessage = "Top-Up: Customer (id: " + data[0].trim() + ") top-up amount RM " + topUpAmount + " successful";
        String detailNotificationMessage = transaction.getReceipt();
        Notification notification = new Notification(getUserID(), data[0].trim(), briefNotificationMessage, detailNotificationMessage);

        transaction.recordTransaction(notification);
    }
}
