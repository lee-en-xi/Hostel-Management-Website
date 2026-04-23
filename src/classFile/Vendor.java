package classFile;

import GUIDesignVendor.VendorPage;

public class Vendor extends User {

    public Vendor() {
    }

    public Vendor(String userID, String username, String email, String phoneNumber, String dobStr, String gender) {
        super(userID, username, email, phoneNumber, dobStr, gender, "vendor");
    }

    public Vendor(String userID, String username, String password, String email, String phoneNumber, String dobStr, String gender) {
        super(userID, username, password, email, phoneNumber, dobStr, gender, "vendor");
    }

    @Override
    public void login(String userID) {
        VendorPage menu = new VendorPage(userID);
        menu.setVisible(true);
    }

    @Override
    public String toString() {
        return getUserID() + ":::" +
                getUsername() + ":::" +
                getEmail() + ":::" +
                getPhoneNumber() + ":::" +
                getDob() + ":::" +
                getGender();
    }
    
    public static Vendor readVendorProfile(String id) {
        FileHandlerImp fileHandlerImp = FileHandlerImp.getFileHandlerImpInstance();
        Vendor vendor = new Vendor();
        String userFilePath = "src/textFile/user.txt";
        
        String line = fileHandlerImp.readFileLine(userFilePath, id);
        
        if (line != null) {
            String[] userData = line.split(":::"); //0-id, 1-name, 2-pw, 3-email, 4-phone.no, 5-dob, 6-gender, 7-role

            vendor.setUserID(userData[0]);
            vendor.setUsername(userData[1]);
            vendor.setPassword(userData[2]);
            vendor.setEmail(userData[3]);
            vendor.setPhoneNumber(userData[4]);
            vendor.setDob(userData[5]);
            vendor.setGender(userData[6]);
            vendor.setRole(userData[7]);
        }
        
        return vendor;
    }
}
