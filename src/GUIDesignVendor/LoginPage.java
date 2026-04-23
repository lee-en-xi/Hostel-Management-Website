package GUIDesignVendor;



import GUIDesignRunner.RunnerFrame;
import classFile.Admin;
import classFile.Customer;
import classFile.Runner;
import classFile.User;
import classFile.Vendor;
import java.awt.Color;

public class LoginPage extends javax.swing.JFrame {

    public LoginPage() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    private String userID;
    private String password;
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        bgColor = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jtextUserID = new javax.swing.JTextField();
        jtextPassword = new javax.swing.JTextField();
        lbluserid = new javax.swing.JLabel();
        lblpassword = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        bgColor.setBackground(new java.awt.Color(104, 144, 77));
        bgColor.setForeground(new java.awt.Color(218, 106, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(390, 470));

        lblTitle.setFont(new java.awt.Font("Yu Gothic UI", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(218, 106, 0));
        lblTitle.setText("Welcome Back");

        jtextUserID.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jtextUserID.setForeground(new java.awt.Color(153, 153, 153));
        jtextUserID.setText("Enter User ID");
        jtextUserID.setCaretColor(new java.awt.Color(218, 106, 0));
        jtextUserID.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtextUserID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextUserIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextUserIDFocusLost(evt);
            }
        });
        jtextUserID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextUserIDActionPerformed(evt);
            }
        });

        jtextPassword.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        jtextPassword.setForeground(new java.awt.Color(153, 153, 153));
        jtextPassword.setText("Enter Password");
        jtextPassword.setCaretColor(new java.awt.Color(218, 106, 0));
        jtextPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jtextPassword.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jtextPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtextPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jtextPasswordFocusLost(evt);
            }
        });
        jtextPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtextPasswordActionPerformed(evt);
            }
        });

        lbluserid.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        lbluserid.setForeground(new java.awt.Color(218, 106, 0));
        lbluserid.setText("User ID");

        lblpassword.setFont(new java.awt.Font("Yu Gothic UI", 0, 16)); // NOI18N
        lblpassword.setForeground(new java.awt.Color(218, 106, 0));
        lblpassword.setText("Password");

        btnLogin.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(218, 106, 0));
        btnLogin.setText("Login");
        btnLogin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLogin.setContentAreaFilled(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitle)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lbluserid)
                                .addGap(73, 73, 73)
                                .addComponent(jtextUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblpassword)
                                .addGap(57, 57, 57)
                                .addComponent(jtextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(lblTitle)
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbluserid)
                    .addComponent(jtextUserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtextPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblpassword))
                .addGap(43, 43, 43)
                .addComponent(btnLogin)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bgColorLayout = new javax.swing.GroupLayout(bgColor);
        bgColor.setLayout(bgColorLayout);
        bgColorLayout.setHorizontalGroup(
            bgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgColorLayout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179))
        );
        bgColorLayout.setVerticalGroup(
            bgColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgColorLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jtextUserIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextUserIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextUserIDActionPerformed

    private void jtextPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtextPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtextPasswordActionPerformed

    private void jtextUserIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextUserIDFocusGained
        if (jtextUserID.getText().equals("Enter User ID")) {
            jtextUserID.setText("");
            jtextUserID.setForeground(new Color(218, 106, 0));
        }
    }//GEN-LAST:event_jtextUserIDFocusGained

    private void jtextUserIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextUserIDFocusLost
        if (jtextUserID.getText().equals("")) {
            jtextUserID.setText("Enter User ID");
            jtextUserID.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtextUserIDFocusLost

    private void jtextPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextPasswordFocusGained
        if (jtextPassword.getText().equals("Enter Password")) {
            jtextPassword.setText("");
            jtextPassword.setForeground(new Color(218, 106, 0));
        }
    }//GEN-LAST:event_jtextPasswordFocusGained

    private void jtextPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtextPasswordFocusLost
        if (jtextPassword.getText().equals("")) {
            jtextPassword.setText("Enter Password");
            jtextPassword.setForeground(new Color(153, 153, 153));
        }
    }//GEN-LAST:event_jtextPasswordFocusLost

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        userID = jtextUserID.getText();
        password = jtextPassword.getText();
        String role = User.validate(userID, password);
        if (role == null) {
            return;
        }
        if (role.equals("admin")) {
            User user = new Admin();
            user.login(userID);
        }

        if (role.equals("vendor")) {
            User user = new Vendor();
            user.login(userID);
        }

        if (role.equals("runner")) {
            User user = new Runner();
            user.login(userID);
        }
        
        if (role.equals("customer")) {
            User user = new Customer();
            user.login(userID);
        }
        this.dispose();


    }//GEN-LAST:event_btnLoginActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgColor;
    private javax.swing.JButton btnLogin;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jtextPassword;
    private javax.swing.JTextField jtextUserID;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblpassword;
    private javax.swing.JLabel lbluserid;
    // End of variables declaration//GEN-END:variables
}
