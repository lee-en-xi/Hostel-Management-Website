package classFile;

public class OrderItem {
    private String itemID;
    private String itemName;
    private int quantity;
    private double subAmount;


    public OrderItem() {
    }

    public OrderItem(String itemID, String itemName, int quantity, double subAmount) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.subAmount = subAmount;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(double subAmount) {
        this.subAmount = subAmount;
    }

    public String toString() {
        return itemID + "|" + itemName + "|" + quantity + "|" + subAmount;
    }
    
    public String toPlaceOrderString() {
        return itemName + "*" + quantity;
    }

    // Static method to create an OrderItem from its string representation
    public static OrderItem fromString(String orderStr) {
        String[] parts = orderStr.split("\\|");
        if (parts.length == 4) {
            String itemID = parts[0];
            String itemName = parts[1];
            int quantity = Integer.parseInt(parts[2]);
            double subAmount = Double.parseDouble(parts[3]);
            return new OrderItem(itemID, itemName, quantity, subAmount);
        }
        return null; // Or handle this case as appropriate
    }
}
