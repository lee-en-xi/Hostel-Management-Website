package GUIDesignVendor;

import classFile.FileHandlerImp;
import classFile.Vendor;
import classFile.Store;
import classFile.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

public class ProfileUpdate extends javax.swing.JFrame {

    public ProfileUpdate(String userID) {
        initComponents();
        VendorID = userID;
        String userData = file.readFileLine(filepath, userID);
        String storeData = file.readFileLine(filepath3, userID);
        String[] splittedData = userData.split(":::");
        String[] storename = storeData.split(":::");

        if (splittedData.length >= 7) {
            txtName.setText(splittedData[1]);
            txtemail.setText(splittedData[3]);
            txtpnum.setText(splittedData[4]);
            txtdob.setText(splittedData[5]);
            txtStoreName.setText(storename[1]);
            cbxgender.addItem("male");
            cbxgender.addItem("female");
            if ("male".equalsIgnoreCase(splittedData[6])) {
                cbxgender.setSelectedItem("male");
            } else {
                cbxgender.setSelectedItem("female");
            }

        } else {
            System.err.println("Error output");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtdob = new javax.swing.JTextField();
        txtpnum = new javax.swing.JTextField();
        txtopass = new javax.swing.JTextField();
        txtnpass = new javax.swing.JTextField();
        txtcnpass = new javax.swing.JTextField();
        cbxgender = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtStoreName = new javax.swing.JTextField();
        btnpassChange = new javax.swing.JButton();
        btnChange = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Profile Update");
        setMinimumSize(new java.awt.Dimension(600, 540));
        setResizable(false);
        setSize(new java.awt.Dimension(600, 540));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 245, 238));
        jPanel1.setMaximumSize(new java.awt.Dimension(600, 540));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 540));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 530));

        jLabel2.setFont(new java.awt.Font("Yu Gothic", 1, 24)); // NOI18N
        jLabel2.setText("Profile Update");

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel1.setText("User Name");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel3.setText("Email");

        jLabel4.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel4.setText("D.O.B");

        jLabel5.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel5.setText("Gender");

        jLabel6.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel6.setText("Phone Number");

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel7.setText("Old Password");

        jLabel8.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel8.setText("New Password");

        jLabel9.setFont(new java.awt.Font("Yu Gothic", 1, 12)); // NOI18N
        jLabel9.setText("Confirm new Password");

        jLabel10.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel10.setText("Store Name");

        btnpassChange.setText("Change Password");
        btnpassChange.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnpassChange.setContentAreaFilled(false);
        btnpassChange.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnpassChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpassChangeActionPerformed(evt);
            }
        });

        btnChange.setText("Apply Changes");
        btnChange.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnChange.setContentAreaFilled(false);
        btnChange.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(txtdob, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(cbxgender, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(txtpnum, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(txtStoreName, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(txtopass, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(txtnpass, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(txtcnpass, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(btnpassChange, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(106, 106, 106)))
                .addGap(105, 105, 105))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtdob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbxgender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtpnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtStoreName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(btnChange, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtopass, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtnpass, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtcnpass, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(btnpassChange, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private FileHandlerImp file = new FileHandlerImp();
    private String filepath = file.createTextFile("user.txt");
    private String filepath2 = file.createTextFile("vendor.txt");
    private String filepath3 = file.createTextFile("store.txt");
    private String VendorID, userName, email, dob, gender, phoneNum, oldPass, newPass, CnewPass, StoreName;

    private void btnpassChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpassChangeActionPerformed

        oldPass = txtopass.getText();
        newPass = txtnpass.getText();
        CnewPass = txtcnpass.getText();

        // Validate the old password
        String userData = file.readFileLine(filepath, VendorID);
        String[] splittedData = userData.split(":::");
        if (!oldPass.equals(splittedData[2])) {
            JOptionPane.showMessageDialog(this, "Old password is incorrect.", "Password Error", JOptionPane.ERROR_MESSAGE);
            resetpass();
            return;
        }
        if (newPass == null || newPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "New password can't be empty", "Password Error", JOptionPane.ERROR_MESSAGE);
            resetpass();
            return;
        }
        if (!newPass.equals(CnewPass)) {
            JOptionPane.showMessageDialog(this, "New passwords do not match.", "Password Error", JOptionPane.ERROR_MESSAGE);
            resetpass();
            return;
        }

        Vendor updatedUser = new Vendor(splittedData[0], splittedData[1], newPass, splittedData[3], splittedData[4], splittedData[5], splittedData[6]);
        String updatedUserData = updatedUser.toUserString();
        boolean updateuser = file.updateFileLine(filepath, VendorID, updatedUserData);

        if (updateuser) {
            JOptionPane.showMessageDialog(this, "Password Updated Successfully.", "Update Success", JOptionPane.INFORMATION_MESSAGE);
            resetpass();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update password.", "Update Error", JOptionPane.ERROR_MESSAGE);
            resetpass();
        }
    }//GEN-LAST:event_btnpassChangeActionPerformed

    private void btnChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeActionPerformed
        userName = txtName.getText();
        email = txtemail.getText();
        phoneNum = txtpnum.getText();
        dob = txtdob.getText();
        gender = cbxgender.getSelectedItem().toString();
        StoreName = txtStoreName.getText();

        if (!isValidDate(dob)) {
            JOptionPane.showMessageDialog(this, "Invalid Date format", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if the date is invalid
        }

        // Validate email format
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Invalid Email format, must contain '@' & '.com' ", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if the email is invalid
        }

        // Validate phone number format
        if (!isValidPhoneNumber(phoneNum)) {
            JOptionPane.showMessageDialog(this, "Invalid Phone Number format (only number allow!)", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop execution if the phone number is invalid
        }

        String userData = file.readFileLine(filepath, VendorID);
        String[] splittedData = userData.split(":::");

        Store updatedStore = new Store(VendorID, StoreName);
        User updatedUser = new Vendor(VendorID, userName, splittedData[2], email, phoneNum, dob, gender);
        Vendor updatedVendor = new Vendor(VendorID, userName, email, phoneNum, dob, gender);

        String updatedVendorData = updatedVendor.toString();
        String updatedUserData = updatedUser.toUserString();
        String updatedStoreData = updatedStore.toString();

        boolean updateUser = file.updateFileLine(filepath, VendorID, updatedUserData);
        boolean updateVendor = file.updateFileLine(filepath2, VendorID, updatedVendorData);
        boolean updateStore = file.updateFileLine(filepath3, VendorID, updatedStoreData);

        if (updateUser && updateVendor && updateStore) {
            JOptionPane.showMessageDialog(this, "Profile updated successfully.", "Update Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update profile.", "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnChangeActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
        VendorPage menu = new VendorPage(VendorID);
        menu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    public boolean isValidDate(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(dob, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isValidEmail(String email) {
        // Check if email contains "@" and ".com"
        return email != null && email.contains("@") && email.contains(".com");
    }

    public boolean isValidPhoneNumber(String phoneNum) {
        // Check if phone number consist only number
        return phoneNum != null && phoneNum.matches("\\d+");
    }

    private void resetpass() {
        txtnpass.setText("");
        txtopass.setText("");
        txtcnpass.setText("");
    }

    public static void main(String userID) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProfileUpdate(userID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChange;
    private javax.swing.JButton btnpassChange;
    private javax.swing.JComboBox<String> cbxgender;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtStoreName;
    private javax.swing.JTextField txtcnpass;
    private javax.swing.JTextField txtdob;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnpass;
    private javax.swing.JTextField txtopass;
    private javax.swing.JTextField txtpnum;
    // End of variables declaration//GEN-END:variables
}
