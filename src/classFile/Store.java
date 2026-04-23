package classFile;

import java.util.List;

public class Store {

    private String VendorID;
    private String StoreName;
    private FileHandlerImp file = new FileHandlerImp();

    public Store(String VendorID, String StoreName) {
        this.VendorID = VendorID;
        this.StoreName = StoreName;

    }

    public String toString() {
        return this.VendorID + ":::" + this.StoreName;
    }

    public boolean writeStoreName(Store store) {
        String filePath = file.createTextFile("store.txt");
        return file.updateFileLine(filePath, VendorID, store.toString());
    }

    public String findStoreName(String storeID) {
        String filePath = file.createTextFile("store.txt");
        List<String[]> allStores = file.readWholeData(filePath);

        for (String[] storeData : allStores) {
            if (storeData.length > 0 && storeData[0].equals(storeID)) {
                return storeData[1];
            }
        }
        return null;
    }

    public String getVendorID() {
        return VendorID;
    }

    public void setVendorID(String StoreID) {
        this.VendorID = StoreID;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
    }

}
