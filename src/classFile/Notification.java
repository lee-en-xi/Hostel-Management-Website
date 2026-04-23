package classFile;

import interfaceFile.IDHandler;
import interfaceFile.DateTimeFormatHandler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Notification implements DateTimeFormatHandler, IDHandler {
    private String notificationID;
    private String senderID;
    private String receiverID;
    private String briefMessage;
    private String detailMessage;
    private LocalDateTime timestamp;
    private boolean readStatus;


    public Notification() {
    }

    public Notification(String senderID, String receiverID, String briefMessage, String detailMessage) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.briefMessage = briefMessage;
        this.detailMessage = detailMessage;
        this.timestamp = LocalDateTime.now();
        this.notificationID = generateID();
        this.readStatus = false;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    @Override
    public String generateID() {
        try {
            Thread.sleep(1000);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String time = dtf.format(timestamp);
            return "n" + time;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFormattedTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(timestamp);
    }

    @Override
    public String toString() {
        return notificationID + ":::" +
                "send-" + senderID + ":::" +
                "receive-" + receiverID + ":::" +
                briefMessage + ":::" +
                detailMessage + ":::" +
                getFormattedTime() + ":::" +
                readStatus;
    }

    //save the notification to file
    public static void sendNotification(Notification notification, String fileName) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        String title = "Notification-Send";
        String message = "Notification Send Successfully";

        String filePath = fileHandlerImp.createTextFile(fileName);
        boolean b = false;

        if (filePath == null) {
            title += "(File)";
            message = "File Creation Fails";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        } else {
            b = fileHandlerImp.writeFileNewLine(filePath, notification.toString());
            if(b) {
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
            } else {
                message = "Notification Send Unsuccessfully";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    //save the notification to file
    public static String sendNotification(String fileName, Notification notification) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        String title = "Notification-Send";
        String message = "Notification Send Successfully";

        String filePath = fileHandlerImp.createTextFile(fileName);
        boolean b = false;

        if (filePath == null) {
            title += "(File)";
            message = "File Creation Fails";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        } else {
            b = fileHandlerImp.writeFileNewLine(filePath, notification.toString());
            if(b) {
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
            } else {
                message = "Notification Send Unsuccessfully";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
            }
        }
        return filePath;
    }

    // Method to read brief notifications with a timestamp column
    public static void readNotificationBriefs(String filePath, String receiverID) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        List<String> allLines = fileHandlerImp.readWholeFile(filePath);

        // Table model with three columns: Brief Message, Timestamp, and Read Status
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Brief Message", "Timestamp", "Read Status"}, 0);
        JTable table = new JTable(model);

        // Adjust column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(200); // Wider brief message column
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Timestamp column
        table.getColumnModel().getColumn(2).setPreferredWidth(80);  // Read status column

        // Populate the table model
        for (String line : allLines) {
            String[] parts = line.split(":::");
            if (parts[2].equals("receive-" + receiverID)) {
                model.addRow(new Object[]{parts[3], parts[5], parts[6].equals("true") ? "Read" : "Unread"});
            }
        }

        // Create a read button
        JButton readButton = new JButton("Read");
        readButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String selectedBrief = (String) model.getValueAt(selectedRow, 1);
                String line = fileHandlerImp.readFileLine(filePath, selectedBrief);
                String[] parts = line.split(":::");
                StringBuilder detailMessage = new StringBuilder();
                detailMessage.append(parts[1]).append("\n");
                detailMessage.append(parts[2]).append("\n");
                detailMessage.append(parts[4].replace("<newline>", "\n"));
                String notificationID = parts[0];

                //Read the notification detail message
                readNotificationDetail(detailMessage.toString(), notificationID, filePath, model, selectedRow);

                //Update the read status
                String updatedLine = line.replace("false", "true");
                model.setValueAt("Read", selectedRow, 2);
                fileHandlerImp.updateFileLine(filePath, notificationID, updatedLine);

            }
        });

        // Adjust the button size and layout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(readButton);

        // Create and add the delete button as before
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String selectedNotification = (String) model.getValueAt(selectedRow, 0);
                String line = fileHandlerImp.readFileLine(filePath, selectedNotification);
                String notificationID = line.split(":::")[0];

                if (notificationID != null) {
                    fileHandlerImp.deleteFileLine(filePath, notificationID);
                    model.removeRow(selectedRow);
                }
            }
        });
        buttonPanel.add(deleteButton);

        // Setting up the frame
        JFrame frame = new JFrame("Notifications");
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Modified readNotificationDetail method
    public static void readNotificationDetail(String detailMessage, String notificationID, String filePath, DefaultTableModel model, int selectedRow) {
        JOptionPane.showMessageDialog(null, detailMessage, "Notification Detail", JOptionPane.INFORMATION_MESSAGE);
    }
}
