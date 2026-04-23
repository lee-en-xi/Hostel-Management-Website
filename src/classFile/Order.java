package classFile;

import interfaceFile.IDHandler;
import interfaceFile.DateTimeFormatHandler;
import java.awt.BorderLayout;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Order implements DateTimeFormatHandler, IDHandler {

    private String orderID;
    private String senderID;
    private String receiverID;
    private String orderType;
    private String address;
    private String orderDetail;
    private double amount;
    private LocalDateTime timestamp;
    private int status;

    public static final String[] type = {"Dine-in", "Delivery", "Take Away"};
    public static final String[] orderStatus = {"Pending", "Preparing", "RunnerAccepted", "Delivering", "Done", "Reject"};
    public static final int PENDING = 0;
    public static final int PREPARING = 1;
    public static final int RUNNERACCEPTED = 2;
    public static final int DELIVERING = 3;
    public static final int DONE = 4;
    public static final int REJECT = 5;

    public Order() {
    }

    public Order(String senderID, String receiverID, String orderType, String address, String orderDetail, double amount) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.orderType = orderType;
        this.address = address;
        this.orderDetail = orderDetail;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.orderID = generateID();
        this.status = Order.PENDING;
    }

    public Order(String orderID, String senderID, String receiverID, String orderType, String address, String orderDetail, double amount, LocalDateTime timestamp, int status) {
        this.orderID = orderID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.orderType = orderType;
        this.address = address;
        this.orderDetail = orderDetail;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return orderID + ":::"
                + "send-" + senderID + ":::"
                + "receive-" + receiverID + ":::"
                + orderType + ":::"
                + address + ":::"
                + orderDetail + ":::"
                + amount + ":::"
                + getFormattedTime() + ":::"
                + status;
    }

    @Override
    public String getFormattedTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dtf.format(timestamp);
    }

    @Override
    public String generateID() {
        try {
            Thread.sleep(1000);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String time = dtf.format(timestamp);
            return "order" + time;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String[]> loadOrder() {
        List<String[]> orderList = new ArrayList<>();
        FileHandlerImp fileHandlerImp = new FileHandlerImp();

        List<String[]> orderDataList = fileHandlerImp.readWholeData("src/textFile/order.txt");

        for (String[] data : orderDataList) {
            if (data.length == 9) {
                String orderID = data[0];
                String senderID = data[1].substring(5);
                String receiverID = data[2].substring(8);
                String orderType = data[3];
                String address = data[4];
                String orderDetail = data[5];
                double amount = Double.parseDouble(data[6]);
                String timestampStr = data[7];
                LocalDateTime timestamp = LocalDateTime.parse(timestampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                String status = Order.orderStatus[Integer.parseInt(data[8])];

                // Create a string array to store the order data
                String[] orderData = {orderID, senderID, receiverID, orderType, address, orderDetail, String.valueOf(amount), timestampStr, String.valueOf(status)};

                orderList.add(orderData);
            }
        }

        return orderList;
    }

    public static String getcusAddress(String orderID) {
        String filePath = "src/textFile/order.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String orders = fileHandlerImp.readFileLine(filePath, orderID);
        String[] splittedData = orders.split(":::");
        String address = splittedData[4];
        return address;
    }

    public static String getRunnerID(String OrderID) {
        String taskFile = "src/textFile/task.txt";
        String runnertaskFile = "src/textFile/runner_task.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String task = fileHandlerImp.readFileLine(taskFile, OrderID);
        String[] splittedT = task.split(":::");
        String TaskID = splittedT[0];
        String RunnerTask = fileHandlerImp.readFileLine(runnertaskFile, TaskID);
        String[] splittedRT = RunnerTask.split(":::");
        return splittedRT[1];
    }

    //get order type based the orderID
    public static String getOrdertype(String orderID) {
        String filePath = "src/textFile/order.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String orders = fileHandlerImp.readFileLine(filePath, orderID);
        String[] splittedData = orders.split(":::");
        String type = splittedData[3];

        return type;
    }

    public static String placeOrder(String fileName, Order order) {
        FileHandlerImp fileHandlerImp = new FileHandlerImp();
        String title = "Order-Place";
        String message = "Order Placed Successfully";

        String filePath = fileHandlerImp.createTextFile(fileName);
        boolean b = false;

        if (filePath == null) {
            title += "(File)";
            message = "File Creation Fails";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        } else {
            b = fileHandlerImp.writeFileNewLine(filePath, order.toString());
            if (b) {
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
            } else {
                message = "Order Placed Unsuccessful";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
            }
        }
        return filePath;
    }

    public static boolean cancelOrder(String orderId) {
        String filePath = "src/textFile/order.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String title = "Order-Cancel";
        String message = "Order Cancel Successfully";

        String line = fileHandlerImp.readFileLine(filePath, orderId);
        boolean b = !(line == null);

        if (b) {
            String[] data = line.split(":::");
            int status = Integer.parseInt(data[data.length - 1]);
            double amount = Double.parseDouble((data[data.length - 3]));
            String customerID = data[1].replace("send-", "");
            String vendorID = data[2].replace("receive-", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime ldt = LocalDateTime.parse(data[7], formatter);

            if (status != Order.PENDING) {
                message = "Cancel Unsuccessful\nOrder is already being taken";
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
                return false;
            }
            Order order = new Order(orderId, customerID, vendorID, data[3], data[4], data[5], amount, ldt, status);
            //refund to customer
            Customer customer = Customer.readCustomerProfile(customerID);
            customer.refund(order);

            fileHandlerImp.deleteFileLine(filePath, orderId);
            fileHandlerImp.deleteFileLine("src/textFile/notification.txt", orderId);
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);

        } else {
            message = "Cancel Unsuccessful\nOrder not found";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static int deleteOrder(String orderId) {
        // return -1: no delete
        String filePath = "src\\textFile\\order.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        String line = fileHandlerImp.readFileLine(filePath, orderId);
        if (line == null) {
            // Order not found
            String message = "Delete Unsuccessful\nOrder not found";
            JOptionPane.showMessageDialog(null, message, "Order-Delete", JOptionPane.WARNING_MESSAGE);
            return -1;
        }

        String[] data = line.split(":::");
        int status = Integer.parseInt(data[data.length - 1]);

        // Check if the status is DONE, REJECTED, or PENDING
        if (status != Order.DONE && status != Order.REJECT && status != Order.PENDING) {
            return -1;
        }

        // Attempt to delete the order
        String str = fileHandlerImp.deleteFileLine(filePath, orderId);

        if (str.equals("false")) {
            String message = "Delete Unsuccessful\nOrder not found";
            JOptionPane.showMessageDialog(null, message, "Order-Delete", JOptionPane.WARNING_MESSAGE);
            return -1;
        }

        return status;
    }

    public static List<String[]> readOrderStatus(String customerId) {
        String filePath = "src/textFile/order.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        List<String[]> orderStatusList = fileHandlerImp.readKeyLinesData(filePath, "send-" + customerId);

        String[] orderStatusData = {"OrderID", "Status"};
        orderStatusList.add(0, orderStatusData);

        return orderStatusList;
    }

    public static int getOrderStatus(String orderID) {
        String filePath = "src/textFile/order.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String orders = fileHandlerImp.readFileLine(filePath, orderID);
        String[] splittedData = orders.split(":::");
        String statusStr = splittedData[8];
        int status = Integer.parseInt(statusStr);
        return status;
    }

    public static String getOrderDetailMessage(String customerId, String type, String location, String orderDetailStr) {
        String orderDetail = orderDetailStr.replace(",", "<newline>");
        return "CustomerID: " + customerId + "<newline>Order Type: " + type + "<newline>Location: " + location + "<newline>Order Detail:<newline>" + orderDetail;
    }

    public static boolean hasOrderHistory(String orderId) {
        String filePath = "src/textFile/orderHistory.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        List<String> orderHistoryLines = fileHandlerImp.readWholeFile(filePath);

        for (String line : orderHistoryLines) {
            if (line.contains(orderId)) {
                return true;
            }
        }

        return false;
    }

    public static void updateOrderStatus(String orderID, int newStatus) {
        List<Order> loadedOrders = new ArrayList<>();
        for (Order order : loadedOrders) {
            if (order.getOrderID().equals(orderID)) {
                // Update the status of the matching order
                order.setStatus(newStatus);
                break;
            }
        }

        String filePath = "src/textFile/order.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        // Read all lines from the text file
        List<String> lines = fileHandlerImp.readWholeFile(filePath);

        // Find the line corresponding to the orderID
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.startsWith(orderID)) {
                // Update the status part of the line with the new status
                String[] parts = line.split(":::");
                if (parts.length == 9) {
                    parts[8] = String.valueOf(newStatus);
                    // Reconstruct the updated line
                    String updatedLine = String.join(":::", parts);
                    // Replace the old line with the updated line
                    lines.set(i, updatedLine);
                    break; // No need to continue searching once found
                }
            }
        }

        // Write the updated lines back to the text file
        if (!fileHandlerImp.writeWholeFile(filePath, lines)) {
            System.err.println("Error updating order status in the text file.");
        }
    }

    public static void saveToOrderHistory(String orderId) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String line = fileHandlerImp.readFileLine("src/textFile/order.txt", orderId);
        String title = "Order History-Save";
        String message = "Order History Save Successfully";

        String[] part = line.split(":::");
        int status = Integer.parseInt(part[part.length - 1]);

        if (status == Order.REJECT) {
            deleteOrder(orderId);
            return;
        }

        if (status != Order.DONE) {
            message = "Order Not Done";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        } else {
            String fileName = "orderHistory.txt";
            String filePath = fileHandlerImp.createTextFile(fileName);
            if (!hasOrderHistory(orderId)) {
                fileHandlerImp.writeFileNewLine(filePath, line);
            }
            deleteOrder(orderId);
        }
    }

    public static void saveOrder() {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        List<String[]> orderList = fileHandlerImp.readWholeData("src/textFile/order.txt");

        for (String[] orderData : orderList) {
            if (Integer.parseInt(orderData[orderData.length - 1]) == Order.DONE) {
                saveToOrderHistory(orderData[0]);
            }
        }
    }

    public static List<String[]> readOrderHistory(String customerId) {
        String filePath = "src/textFile/orderHistory.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        List<String[]> dataList = fileHandlerImp.readKeyLinesData(filePath, "send-" + customerId);
        List<String[]> orderHistoryList = new ArrayList<>();

        String[] orderHistoryData = {"OrderID", "VendorID", "Order Type", "Detail", "Amount", "Time"};
        orderHistoryList.add(0, orderHistoryData);

        for (String[] data : dataList) {
            data[5] = data[5].replace(",", " ");
            String[] arr = {data[0], data[2].replace("receive-", ""), data[3], data[5], data[6], data[7]};
            orderHistoryList.add(arr);
        }

        return orderHistoryList;
    }

    //read order history for vendor
    public static void loadAndDisplayOrderHistory(String VendorID) {
        String filePath = "src/textFile/orderHistory.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        List<String[]> orderHistoryList = fileHandlerImp.readKeyLinesData(filePath, "receive-" + VendorID);

        // Check if there are rows of data in the list
        if (!orderHistoryList.isEmpty()) {
            String[] orderHistoryData = {"OrderID", "VendorID", "Order Type", "Detail", "Amount", "Time"};

            // Create a new list to hold the filtered data
            List<String[]> filteredOrderHistory = new ArrayList<>();

            // Add the column names to the filtered list
            filteredOrderHistory.add(orderHistoryData);

            // Iterate through the loaded data and extract the desired columns
            for (String[] rowData : orderHistoryList) {
                String[] filteredRowData = new String[6];
                filteredRowData[0] = rowData[0]; // Order ID
                filteredRowData[1] = rowData[1]; // Vendor ID
                filteredRowData[2] = rowData[2]; // Order Type
                filteredRowData[3] = rowData[5]; // Detail
                filteredRowData[4] = rowData[6]; // Amount
                filteredRowData[5] = rowData[7]; // Time
                filteredOrderHistory.add(filteredRowData);
            }

            // Create a JTable to display the filtered order history
            DefaultTableModel tableModel = new DefaultTableModel(filteredOrderHistory.toArray(new Object[0][0]), orderHistoryData);
            JTable orderTable = new JTable(tableModel);
            int[] columnWidths = {120, 90, 100, 250, 70, 120};

            // Assuming orderTable is your JTable instance
            TableColumnModel columnModel = orderTable.getColumnModel();

            for (int i = 0; i < columnWidths.length; i++) {
                TableColumn column = columnModel.getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }
            // Create a JFrame to display the table
            JFrame frame = new JFrame("Order History");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(800, 400);
            frame.getContentPane().add(new JScrollPane(orderTable), BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

    public List<String[]> readOrderHistoryDetail(String orderDetail) {
        List<String[]> orderHistoryDetailList = new ArrayList<>();

        String[] orderData = orderDetail.split("<newline>");

        for (String order : orderData) {
            String[] data = order.split("\\|");
            orderHistoryDetailList.add(data);
        }

        String[] orderHistoryDetailData = {"ItemID", "ItemName", "Quantity", "SubAmount"};
        orderHistoryDetailList.add(0, orderHistoryDetailData);

        return orderHistoryDetailList;
    }

    public static void deleteOrderHistory(String orderID) {
        String filePath = "src/textFile/orderHistory.txt";
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();

        String result = fileHandlerImp.deleteFileLine(filePath, orderID);
        boolean b = !(result.equals("false"));

        if (!b) {
            String title = "Order History-Delete";
            String message = "Order History Delete Unsuccessful";
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void reorder(String orderId) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        String line = fileHandlerImp.readFileLine("src\\TextFile\\orderHistory.txt", orderId);
        //Exit if the order is not found
        if (line == null || line.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Order not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        
        String[] part = line.split(":::");
        int input = JOptionPane.showConfirmDialog(null, "Do you sure to reorder?", "Reorder", JOptionPane.YES_NO_CANCEL_OPTION);
        //If the user confirm to reorder
        if (input == JOptionPane.YES_OPTION) {
            //Get the order type
            String orderType = promptForOrderType();
            //No order type -> end
            if (orderType.equals("")) {
                return;
            }
            
            String address = "";
            double deliveryFee = 0;
            String requestedPickUpTime = Task.generateTime(10, 20);
            String requestedDeliveryTime = Task.generateTime(40, 10);
            //If order type -> Delivery
            if ("Delivery".equals(orderType)) {
                //Get the location
                address = promptForAddress();
                //Exit if no address is provided
                if (address == null) {
                    return; 
                }
                //Generate delivery fee
                deliveryFee = Double.parseDouble(Task.generateDeliveryFee(requestedPickUpTime, requestedDeliveryTime));
            }
            
            double newAmount = Double.parseDouble(part[6]) + deliveryFee;
            String message = "Your order amount is: RM " + newAmount + "\nDo you sure to place the order?";
            String title = "Confirmation";
            int choice = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                String senderId = part[1].replace("send-", "");
                String receiverId = part[2].replace("receive-", "");
                String detail = part[5];
                Order order = new Order(senderId, receiverId, orderType, address, detail, newAmount);
                Customer customer = Customer.readCustomerProfile(senderId);
                if (!customer.isSufficientBalance(newAmount)) {
                    JOptionPane.showMessageDialog(null, "Your balance is not sufficient\nPlease top up first to place the order","Balance Insufficient", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                customer.makePayment(order);
            }
        }
    }

    private static String promptForOrderType() {
        String[] orderTypeArr = {"Dine In", "Take Away", "Delivery"};
        int option = JOptionPane.showOptionDialog(null, "Please select your order type:", "Order Type", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, orderTypeArr, orderTypeArr[0]);
        if (option == JOptionPane.CLOSED_OPTION) {
            // Handle cancellation here
            JOptionPane.showMessageDialog(null, "Order type selection canceled.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
            // Return a default value or handle it based on your requirement
            return ""; // Default return value or handle cancellation
        } else if (option >= 0 && option < orderTypeArr.length) {
            return orderTypeArr[option];
        }

        return ""; // Default return value or handle cancellation
    }

    private static String promptForAddress() {
        while (true) {
            String address = JOptionPane.showInputDialog(null, "Please input your location:", "Location");
            if (address == null || address.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "You cannot input an empty location.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int choice2 = JOptionPane.showConfirmDialog(null, "Please confirm your location: " + address, "Confirm Location", JOptionPane.YES_NO_OPTION);
                if (choice2 == JOptionPane.YES_OPTION) {
                    return address;
                }
            }
        }
    }

    public static void orderRejected(String line, String customerId) {
        String[] orderData = line.split(":::");

        Customer customer = Customer.readCustomerProfile(customerId);

        if (Integer.parseInt(orderData[orderData.length - 1]) == Order.REJECT) {
            String orderID = orderData[0];
            String senderId = orderData[1].replace("send-", "");
            String receiverId = orderData[2].replace("receive-", "");
            String type = orderData[3];
            String address = orderData[4];
            String orderDetail = orderData[5];
            double amount = Double.parseDouble(orderData[6]);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime ldt = LocalDateTime.parse(orderData[7], formatter);

            int status = Integer.parseInt(orderData[orderData.length - 1]);
            Order order = new Order(orderID, senderId, receiverId, type, address, orderDetail, amount, ldt, status);
            customer.refund(order);
        }
    }
}
