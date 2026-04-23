package classFile;

import GUIDesignCustomer.CustomerMainFrame;
import java.text.DecimalFormat;
import javax.swing.*;

public class Customer extends User {

    private double balance;

    public Customer() {
    }

    public Customer(String username, String email, String phoneNumber, String dobStr, String gender, String role) {
        super(username, email, phoneNumber, dobStr, gender, role);
        setInitialBalance();
    }

    public Customer(String userID, String username, String password, String email, String phoneNumber, String dobStr, String gender, String role) {
        super(userID, username, password, email, phoneNumber, dobStr, gender, "customer");
        setInitialBalance();
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setInitialBalance() {
        DecimalFormat df = new DecimalFormat("#.00");
        this.balance = Double.parseDouble(df.format(0));
    }

    @Override
    public void login(String id) {
        CustomerMainFrame customerMainFrame = new CustomerMainFrame(id);
        customerMainFrame.setVisible(true);
    }

    @Override
    public String toString() {
        return getUserID() + ":::"
                + getUsername() + ":::"
                + getEmail() + ":::"
                + getPhoneNumber() + ":::"
                + getDob() + ":::"
                + getGender() + ":::"
                + balance;
    }

    public static void requestTopUp(String customerId, double topUpAmount) {
        //Create a notification
        final String receiverId = "a000001";
        String briefNotificationMessage = "Request Top-Up";
        String detailNotificationMessage = "Customer (id:" + customerId + ") request to top-up amount RM " + topUpAmount;
        Notification notification = new Notification(customerId, receiverId, briefNotificationMessage, detailNotificationMessage);

        //Send notification to customer
        String result = Notification.sendNotification("notification.txt", notification);

        String title = "Request Top-Up";
        String message = "Top-Up Request Send Successful";
        if (!(result == null)) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
        } else {
            message = "Top-Up Request Send Unsuccessful";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        }
    }

    public static Customer readCustomerProfile(String id) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String filePath = "src/textFile/customer.txt";
        String filePath2 = "src/textFile/user.txt";

        Customer customer = new Customer();
        String line = fileHandlerImp.readFileLine(filePath, id);
        String line2 = fileHandlerImp.readFileLine(filePath2, id);
        if (line != null && line2 != null) {
            String[] data = line.split(":::");
            String[] data2 = line2.split(":::"); //0-id, 1-name, 2-pw, 3-email, 4-phone.no, 5-dob, 6-gender, 7-role
            customer.setUserID(id); //id
            customer.setUsername(data[1].trim()); //name
            customer.setPassword(data2[2]);
            customer.setEmail(data[2].trim()); //email
            customer.setPhoneNumber(data[3].trim()); //phone number
            customer.setDob(data[4].trim()); //dob
            customer.setGender(data[5].trim()); //gender
            customer.setBalance(Double.parseDouble(data[6]));
            customer.setRole(data2[7]);
            return customer;
        }
        return null;
    }

    public boolean isSufficientBalance(double amount) {
        return amount < balance;
    }

    public static boolean hasMakeOrder(String id) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String filePath = "src/textFile/order.txt";
        String result = fileHandlerImp.readFileLine(filePath, "send-" + id);
        if (result == null) {
            return false;
        } else {
            return true;
        }
    }

    //For reorder
    public void makePayment(Order order) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String filePath = "src/textFile/store.txt";
        
        double amount = order.getAmount();
        String vendorID = order.getReceiverID().replace("receive-","");
        
        //If order type = delivery
        if (order.getOrderType().equals("Delivery")) {
            //Check if it has available runner
            if (Runner.haveAvailableRunner()) { //If have runner
                //Save a task string to delivery file
                String store = fileHandlerImp.readFileLine(filePath, order.getReceiverID());
                String storeName = store.split(":::")[1];
                Task task = new Task(order.getOrderID(), order.getReceiverID(), storeName, order.getAddress(), super.getUsername(), super.getUserID());
                Task.appendTaskToDelivery(task);
                
                double delivery = Double.parseDouble(task.getDeliveryFee());
                balance -= delivery;
                //Create a transaction to runner who accepted the task
                Transaction transactionToRunner = new Transaction(getUserID(), "", delivery, "Delivery Fee");
                fileHandlerImp.writeFileNewLine("src/textFile/deliveryTransaction.txt", order.getOrderID() + ":::" + transactionToRunner.toString());
            } else { //no runner
                String[] options = {"Dine In", "Take Away"};
                String message = "No runner available, please reselect your order type";
                int choice = JOptionPane.showOptionDialog(null, message, "Select Order Type",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                // Ensure the user selects a valid option
                while (choice == JOptionPane.CLOSED_OPTION) {
                    choice = JOptionPane.showOptionDialog(null, message, "Select Order Type",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                }
                
                switch (choice) {
                    case 0: // Dine In
                        order.setOrderType("Dine In");
                        order.setAddress("");
                        break;
                    case 1: // Take Away
                        order.setOrderType("Take Away");
                        order.setAddress("");
                        break;
                    default:
                        break;
                }
            }
        }
        
        //Place order to vendor
        Order.placeOrder("order.txt", order);
        //Deduct amount
        balance -= amount;
 
        //Record transaction and send notification
        Transaction transactionToVendor = new Transaction(getUserID(), vendorID, amount, "Place Order");

        String briefNotificationMessage = "Order: Customer (id: " + getUserID() + ") place an order";
        String detailNotificationMessage = order.getOrderDetailMessage(order.getSenderID(),order.getOrderType(), order.getAddress(), order.getOrderDetail());
        Notification notification = new Notification(getUserID(), vendorID, briefNotificationMessage, detailNotificationMessage);

        transactionToVendor.recordTransaction(notification);
        //Update customer's balance
        fileHandlerImp.updateFileLine("src/textFile/customer.txt", super.getUserID(), toString());
    }
    
    //For order
    public void makePayment(Order order, double delivery, String requestedPickUpTime, String requestedDeliveryTime) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String filePath = "src/textFile/store.txt";
        double amount = order.getAmount();
        String vendorID = order.getReceiverID().replace("receive-","");
        
        //If order type = delivery
        if (order.getOrderType().equals("Delivery")) {
            //Check if it has available runner
            if (Runner.haveAvailableRunner()) { //If have runner
                //Save a task string to delivery file
                String store = fileHandlerImp.readFileLine(filePath, order.getReceiverID());
                String storeName = store.split(":::")[1];
                Task task = new Task(order.getOrderID(), order.getReceiverID(), storeName, order.getAddress(), super.getUsername(), super.getUserID(), requestedPickUpTime, requestedDeliveryTime, Double.toString(delivery));
                Task.appendTaskToDelivery(task);
                
                balance -= delivery;
                //Create a transaction to runner who accepted the task
                Transaction transactionToRunner = new Transaction(getUserID(), "", delivery, "Delivery Fee");
                fileHandlerImp.writeFileNewLine("src/textFile/deliveryTransaction.txt", order.getOrderID() + ":::" + transactionToRunner.toString());
                
            } else { //no runner
                System.out.println("203");
                String[] options = {"Dine In", "Take Away"};
                String message = "No runner available, please reselect your order type";
                int choice = JOptionPane.showOptionDialog(null, message, "Select Order Type",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                // Ensure the user selects a valid option
                while (choice == JOptionPane.CLOSED_OPTION) {
                    choice = JOptionPane.showOptionDialog(null, message, "Select Order Type",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                }
                
                switch (choice) {
                    case 0: // Dine In
                        order.setOrderType("Dine In");
                        order.setAddress("");
                        break;
                    case 1: // Take Away
                        order.setOrderType("Take Away");
                        order.setAddress("");
                        break;
                    default:
                        break;
                }
            }
        }
        
        //Place order to vendor
        Order.placeOrder("order.txt", order);
        //Deduct amount
        balance -= amount;
 
        //Record transaction and send notification
        Transaction transactionToVendor = new Transaction(getUserID(), vendorID, amount, "Place Order");

        String briefNotificationMessage = "Order: Customer (id: " + getUserID() + ") place an order";
        String detailNotificationMessage = order.getOrderDetailMessage(order.getSenderID(),order.getOrderType(), order.getAddress(), order.getOrderDetail());
        Notification notification = new Notification(getUserID(), vendorID, briefNotificationMessage, detailNotificationMessage);

        transactionToVendor.recordTransaction(notification);
        //Update customer's balance
        fileHandlerImp.updateFileLine("src/textFile/customer.txt", super.getUserID(), toString());
    }

    public void refund(Order order) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        double amount = order.getAmount();
        String vendorID = order.getReceiverID().replace("receive-","");

        balance += amount;
        DecimalFormat df = new DecimalFormat("#.00");
        balance =  Double.parseDouble(df.format(balance));

        Transaction transaction = new Transaction(vendorID, getUserID(), amount, "Refund("+ order.getOrderID() + ")");
        transaction.recordTransaction();
        
        String orderType = order.getOrderType();
        if (orderType.equals("Delivery")) {
            String line = fileHandlerImp.deleteFileLine("src/textFile/deliveryTransaction.txt", order.getOrderID());

            String deliveryStr = line.split(":::")[4];
            double delivery = Double.parseDouble(deliveryStr);
            balance += delivery;
            //Delete the task and transaction in the temp file
            fileHandlerImp.deleteFileLine("src/textFile/delivery.txt", order.getOrderID());
            //fileHandlerImp.deleteFileLine("src/textFile/deliveryTransaction.txt", order.getOrderID());
        }
        
        fileHandlerImp.updateFileLine("src/textFile/customer.txt", super.getUserID(), toString());
    }
}
