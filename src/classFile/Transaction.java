package classFile;

import interfaceFile.IDHandler;
import interfaceFile.DateTimeFormatHandler;
import javax.swing.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class Transaction implements DateTimeFormatHandler, IDHandler {
    private String transactionID;
    private String senderID;
    private String receiverID;
    private double amount;
    private LocalDateTime timestamp;
    private String description;

    public Transaction() {
    }

    public Transaction(String senderID, String receiverID, double amount, String description) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.description = description;
        this.transactionID = generateID();
    }
    
    public Transaction(String transactionID , String senderID, String receiverID, double amount, LocalDateTime timestamp, String description) {
        this.transactionID = transactionID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.amount = amount;
        this.timestamp = timestamp;
        this.description = description;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String generateID() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String transactionTime = dtf.format(timestamp);
        int random = new Random().nextInt(1000);
        return transactionTime + random;
    }

    public String getFormattedTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(timestamp);
    }

    public String getFormattedStringAmount() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(amount);
    }

    public double getFormattedAmount() {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(amount));
    }

    /*
    public void printReceipt() {
        String info = "Transaction ID: " + transactionID + "\n"
                + "Amount: RM" + getFormattedStringAmount() + "\n"
                + "Timestamp: " + getFormattedTime() + "\n"
                + "Description: " + description + "\n";
        JOptionPane.showMessageDialog(null, info, "Receipt", JOptionPane.INFORMATION_MESSAGE);
    }
    */

    public String getReceipt() {
        return "Transaction ID: " + transactionID + "<newline>"
                + "SenderID: " + senderID + "<newline>"
                + "ReceiverID: " + receiverID + "<newline>"
                + "Amount: RM" + getFormattedStringAmount() + "<newline>"
                + "Time: " + getFormattedTime() + "<newline>"
                + "Description: " + description + "<newline>";
    }

    @Override
    public String toString() {
        return transactionID + ":::" +
                "send-" + senderID + ":::" +
                "receive-" + receiverID + ":::" +
                getFormattedAmount() + ":::" +
                getFormattedTime() + ":::" +
                description;
    }

    public void recordTransaction() {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String fileName = "transaction.txt";

        String title = "Transaction";

        String filePath = fileHandlerImp.createTextFile(fileName);

        if (filePath == null) {
            title += "(File)";
            String message = "File Creation Fails";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        } else {
            boolean b = fileHandlerImp.writeFileNewLine(filePath, toString());
            if(!b) {
                String message = "Transaction Recorded Unsuccessfully";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void recordTransaction(Notification notification) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String fileName = "transaction.txt";

        String title = "Transaction";

        String filePath = fileHandlerImp.createTextFile(fileName);;

        if (filePath == null) {
            title += "(File)";
            String message = "File Creation Fails";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        } else {
            boolean b = fileHandlerImp.writeFileNewLine(filePath, toString());
            if(b) {
                //JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
                Notification.sendNotification("notification.txt", notification);
            } else {
                String message = "Transaction Recorded Unsuccessfully";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static List<String[]> readTransaction(String userId) {
        String filePath = "src/textFile/transaction.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        List<String[]> userTransactionList = fileHandlerImp.readKeyLinesData(filePath, userId);

        return userTransactionList;
    }
}
