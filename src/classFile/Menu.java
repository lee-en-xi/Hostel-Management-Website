package classFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu
{
    private static final FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
    private String ItemID;
    private String ItemName;
    private String StoreID;
    private double Price;
    private String filePath = fileHandlerImp.createTextFile("menu.txt");
    
    public Menu() {
    }
    
    public boolean AddMenuItem(String StoreID, String ItemName, double Price) throws IOException 
    {
        int nextItemID = getNextItemID(filePath, StoreID);
        this.setItemName(ItemName);
        this.setStoreID(StoreID);
        this.setPrice(Price);

        String formattedItemID = String.format("itID%02d", nextItemID);
        String newItem = StoreID + ":::" + formattedItemID + ":::" + ItemName + ":::" + Price;

        return fileHandlerImp.writeFileNewLine(filePath, newItem);
    }
    
    private int getNextItemID(String filePath, String StoreID) throws IOException {
        int maxItemID = 0;

        // Assuming FileHandlerImp (or FileHandler interface) has a method readWholeFile
        List<String> fileLines = fileHandlerImp.readWholeFile(filePath);

        Pattern pattern = Pattern.compile(StoreID + ":::itID(\\d+)"); // Pattern to match item IDs

        for (String line : fileLines) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int itemIDNumber = Integer.parseInt(matcher.group(1));
                maxItemID = Math.max(maxItemID, itemIDNumber);
            }
        }
        return maxItemID + 1; // Increment the maximum item ID by 1
    }
    
    public boolean deleteItem(String StoreID, String ItemID) 
    {
        String unique = StoreID + ":::" + ItemID;
        String result = fileHandlerImp.deleteFileLine(filePath, unique);
        return result != null && !result.equals("false");
    }
    
    public static List<String[]> readMenu(String storeId) {
        String filePath = "src/textFile/menu.txt";

        List<String[]> dataList = fileHandlerImp.readKeyLinesData(filePath, storeId);
        List<String[]> menuList = new ArrayList<>();

        String[] menuColumn = {"ItemID", "Item Name", "Price"};
        menuList.add(0, menuColumn);

        for(String[] menuData : dataList) {
            String[] menu = {menuData[1], menuData[2], menuData[3]};
            menuList.add(menu);
        }

        return menuList;
    }

    public static List<String> getStore() {
        String filePath = "src/textFile/store.txt";
        List<String> storeList = fileHandlerImp.readWholeFile(filePath);
        return storeList;
    }

    public String getItemID() {return ItemID;}

    public void setItemID(String ItemID) {this.ItemID = ItemID;}

    public String getItemName() {return ItemName;}

    public void setItemName(String ItemName) {this.ItemName = ItemName;}

    public String getStoreID() {return StoreID;}

    public void setStoreID(String StoreID) {this.StoreID = StoreID;}

    public double getPrice() {return Price;}

    public void setPrice(double Price) {this.Price = Price;}
}