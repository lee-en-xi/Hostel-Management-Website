package GUIDesignVendor;

import classFile.*;
import java.awt.Color;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class VendorPage extends javax.swing.JFrame {

    public VendorPage(String UserID) {
        initComponents();
        hideallpanel();
        VendorID = UserID;
        StoreName = store.findStoreName(VendorID);
        lblTitle.setText(StoreName);
        btnMenu.setBackground(new Color(221, 190, 170));
        loadMenuItem(VendorID);
    }
    @SuppressWarnings("unchecked")
    private String VendorID;
    private String StoreName;
    private String notifilepath = "notification.txt";
    private Notification noti = new Notification();
    private Menu mn = new Menu();
    private Store store = new Store(VendorID, StoreName);
    private Review reviewPage = new Review();
    private FileHandlerImp file = new FileHandlerImp();
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SidePanel = new javax.swing.JPanel();
        btnMenu = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnReview = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnRev = new javax.swing.JButton();
        btnLogOut = new javax.swing.JButton();
        btnNoti = new javax.swing.JButton();
        btnProfile = new javax.swing.JButton();
        pagePanel = new javax.swing.JPanel();
        PanelMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnadd = new javax.swing.JButton();
        btndlt = new javax.swing.JButton();
        btnupdateitem = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        itemtable = new javax.swing.JTable();
        PanelOrder = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnhist = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        IncomingOrder = new javax.swing.JTable();
        btnAccept = new javax.swing.JButton();
        btnReject = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        PanelReview = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ReviewTable = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        PanelRevenue = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbyear = new javax.swing.JComboBox<>();
        cbmonth = new javax.swing.JComboBox<>();
        cbday = new javax.swing.JComboBox<>();
        btnloadRev = new javax.swing.JButton();
        lblrevenue = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        revenuetable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu");
        setMaximumSize(new java.awt.Dimension(1000, 600));
        setName("MenuFrame"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(218, 106, 0));
        jPanel1.setForeground(new java.awt.Color(148, 146, 158));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 600));

        SidePanel.setBackground(new java.awt.Color(104, 144, 77));
        SidePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        SidePanel.setFocusTraversalPolicyProvider(true);
        SidePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMenu.setBackground(new java.awt.Color(255, 245, 238));
        btnMenu.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnMenu.setForeground(new java.awt.Color(218, 106, 0));
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source/Menu logo.png"))); // NOI18N
        btnMenu.setText("Menu");
        btnMenu.setAutoscrolls(true);
        btnMenu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.gray, new java.awt.Color(104, 144, 77)));
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenu.setFocusPainted(false);
        btnMenu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu.setIconTextGap(30);
        btnMenu.setName(""); // NOI18N
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        SidePanel.add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 261, 229, 60));

        btnOrder.setBackground(new java.awt.Color(255, 245, 238));
        btnOrder.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnOrder.setForeground(new java.awt.Color(218, 106, 0));
        btnOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source/Order logo.png"))); // NOI18N
        btnOrder.setText("Order");
        btnOrder.setAutoscrolls(true);
        btnOrder.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.gray, new java.awt.Color(104, 144, 77)));
        btnOrder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOrder.setFocusPainted(false);
        btnOrder.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnOrder.setIconTextGap(30);
        btnOrder.setName(""); // NOI18N
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });
        SidePanel.add(btnOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 339, 229, 60));

        btnReview.setBackground(new java.awt.Color(255, 245, 238));
        btnReview.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnReview.setForeground(new java.awt.Color(218, 106, 0));
        btnReview.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source/Review logo.png"))); // NOI18N
        btnReview.setText("Review");
        btnReview.setAutoscrolls(true);
        btnReview.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.gray, new java.awt.Color(104, 144, 77)));
        btnReview.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReview.setFocusPainted(false);
        btnReview.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnReview.setIconTextGap(30);
        btnReview.setName(""); // NOI18N
        btnReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReviewActionPerformed(evt);
            }
        });
        SidePanel.add(btnReview, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 417, 229, 60));

        lblTitle.setBackground(new java.awt.Color(255, 245, 238));
        lblTitle.setFont(new java.awt.Font("Tw Cen MT", 0, 30)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 245, 238));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Vendor Name");
        SidePanel.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 192, 217, 57));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source/Light App logo.png"))); // NOI18N
        SidePanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        btnRev.setBackground(new java.awt.Color(255, 245, 238));
        btnRev.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnRev.setForeground(new java.awt.Color(218, 106, 0));
        btnRev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source/Revenue logo.png"))); // NOI18N
        btnRev.setText("Revenue");
        btnRev.setAutoscrolls(true);
        btnRev.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, java.awt.Color.gray, new java.awt.Color(104, 144, 77)));
        btnRev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRev.setFocusPainted(false);
        btnRev.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnRev.setIconTextGap(30);
        btnRev.setName(""); // NOI18N
        btnRev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevActionPerformed(evt);
            }
        });
        SidePanel.add(btnRev, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 495, 229, 60));

        btnLogOut.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnLogOut.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.disabledShadow"));
        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source/logout_48px.png"))); // NOI18N
        btnLogOut.setAutoscrolls(true);
        btnLogOut.setBorder(null);
        btnLogOut.setBorderPainted(false);
        btnLogOut.setContentAreaFilled(false);
        btnLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogOut.setFocusPainted(false);
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });
        SidePanel.add(btnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, -1, -1));

        btnNoti.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.disabledShadow"));
        btnNoti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source/noti11.png"))); // NOI18N
        btnNoti.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNoti.setBorderPainted(false);
        btnNoti.setContentAreaFilled(false);
        btnNoti.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNoti.setFocusPainted(false);
        btnNoti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotiActionPerformed(evt);
            }
        });
        SidePanel.add(btnNoti, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 590, 48, 50));

        btnProfile.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.disabledShadow"));
        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source/profile.png"))); // NOI18N
        btnProfile.setBorder(null);
        btnProfile.setBorderPainted(false);
        btnProfile.setContentAreaFilled(false);
        btnProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProfile.setFocusPainted(false);
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });
        SidePanel.add(btnProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 590, -1, -1));

        pagePanel.setBackground(new java.awt.Color(218, 106, 0));
        pagePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pagePanel.setLayout(new java.awt.CardLayout());

        PanelMenu.setBackground(new java.awt.Color(255, 245, 238));
        PanelMenu.setPreferredSize(new java.awt.Dimension(728, 618));

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(218, 106, 0));
        jLabel1.setText("Menu");

        btnadd.setBackground(new java.awt.Color(204, 204, 204));
        btnadd.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        btnadd.setText("Add Item");
        btnadd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnadd.setContentAreaFilled(false);
        btnadd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btndlt.setBackground(new java.awt.Color(204, 204, 204));
        btndlt.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        btndlt.setText("Delete Item");
        btndlt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btndlt.setContentAreaFilled(false);
        btndlt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btndlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndltActionPerformed(evt);
            }
        });

        btnupdateitem.setBackground(new java.awt.Color(204, 204, 204));
        btnupdateitem.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        btnupdateitem.setText("Update Item");
        btnupdateitem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnupdateitem.setContentAreaFilled(false);
        btnupdateitem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnupdateitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateitemActionPerformed(evt);
            }
        });

        itemtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        itemtable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(itemtable);

        javax.swing.GroupLayout PanelMenuLayout = new javax.swing.GroupLayout(PanelMenu);
        PanelMenu.setLayout(PanelMenuLayout);
        PanelMenuLayout.setHorizontalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMenuLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMenuLayout.createSequentialGroup()
                        .addGroup(PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4)
                            .addGroup(PanelMenuLayout.createSequentialGroup()
                                .addGap(301, 301, 301)
                                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btndlt, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addGap(34, 34, 34)
                                .addComponent(btnupdateitem, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)))
                        .addGap(24, 24, 24))))
        );
        PanelMenuLayout.setVerticalGroup(
            PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMenuLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(PanelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndlt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdateitem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        pagePanel.add(PanelMenu, "card2");

        PanelOrder.setBackground(new java.awt.Color(255, 245, 238));
        PanelOrder.setFocusTraversalPolicyProvider(true);
        PanelOrder.setPreferredSize(new java.awt.Dimension(728, 618));

        jLabel3.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(218, 106, 0));
        jLabel3.setText("Order");

        btnhist.setBackground(new java.awt.Color(255, 245, 238));
        btnhist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source/time_machine_50px.png"))); // NOI18N
        btnhist.setText("History");
        btnhist.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnhist.setContentAreaFilled(false);
        btnhist.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnhist.setFocusPainted(false);
        btnhist.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnhist.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnhist.setIconTextGap(20);
        btnhist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhistActionPerformed(evt);
            }
        });

        IncomingOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(IncomingOrder);

        btnAccept.setBackground(new java.awt.Color(204, 204, 204));
        btnAccept.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        btnAccept.setText("Accept");
        btnAccept.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAccept.setContentAreaFilled(false);
        btnAccept.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAccept.setFocusTraversalPolicyProvider(true);
        btnAccept.setRequestFocusEnabled(false);
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        btnReject.setBackground(new java.awt.Color(204, 204, 204));
        btnReject.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        btnReject.setText("Reject");
        btnReject.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnReject.setContentAreaFilled(false);
        btnReject.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReject.setFocusTraversalPolicyProvider(true);
        btnReject.setRequestFocusEnabled(false);
        btnReject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdate.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnUpdate.setContentAreaFilled(false);
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.setFocusTraversalPolicyProvider(true);
        btnUpdate.setRequestFocusEnabled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelOrderLayout = new javax.swing.GroupLayout(PanelOrder);
        PanelOrder.setLayout(PanelOrderLayout);
        PanelOrderLayout.setHorizontalGroup(
            PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOrderLayout.createSequentialGroup()
                .addGroup(PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelOrderLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnReject, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelOrderLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                            .addGroup(PanelOrderLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnhist, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21))
        );
        PanelOrderLayout.setVerticalGroup(
            PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOrderLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(btnhist, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReject, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        pagePanel.add(PanelOrder, "card3");

        PanelReview.setBackground(new java.awt.Color(255, 245, 238));
        PanelReview.setPreferredSize(new java.awt.Dimension(728, 618));

        jLabel4.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(218, 106, 0));
        jLabel4.setText("Review");

        ReviewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        ReviewTable.setColumnSelectionAllowed(true);
        ReviewTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(ReviewTable);
        ReviewTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnRefresh.setBackground(new java.awt.Color(255, 245, 238));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Source/reset_48px.png"))); // NOI18N
        btnRefresh.setBorder(null);
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.setFocusPainted(false);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelReviewLayout = new javax.swing.GroupLayout(PanelReview);
        PanelReview.setLayout(PanelReviewLayout);
        PanelReviewLayout.setHorizontalGroup(
            PanelReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelReviewLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelReviewLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(655, Short.MAX_VALUE))
                    .addGroup(PanelReviewLayout.createSequentialGroup()
                        .addGroup(PanelReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
                            .addGroup(PanelReviewLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))))
        );
        PanelReviewLayout.setVerticalGroup(
            PanelReviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelReviewLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        pagePanel.add(PanelReview, "card4");

        PanelRevenue.setBackground(new java.awt.Color(255, 245, 238));
        PanelRevenue.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PanelRevenue.setPreferredSize(new java.awt.Dimension(728, 618));

        jLabel5.setFont(new java.awt.Font("Dubai", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(218, 106, 0));
        jLabel5.setText("Revenue");

        cbyear.setBackground(new java.awt.Color(255, 245, 238));
        cbyear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbyear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2024", "2025", "2026" }));
        cbyear.setToolTipText("");
        cbyear.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(104, 144, 77)));

        cbmonth.setBackground(new java.awt.Color(255, 245, 238));
        cbmonth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbmonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbmonth.setSelectedIndex(-1);
        cbmonth.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(104, 144, 77)));
        cbmonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cbday.setBackground(new java.awt.Color(255, 245, 238));
        cbday.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbday.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cbday.setSelectedIndex(-1);
        cbday.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(104, 144, 77)));

        btnloadRev.setBackground(new java.awt.Color(204, 204, 204));
        btnloadRev.setText("Load");
        btnloadRev.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnloadRev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloadRevActionPerformed(evt);
            }
        });

        lblrevenue.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Year");

        jLabel8.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Month");

        jLabel9.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Day");

        revenuetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(revenuetable);

        jLabel6.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel6.setText("Total Revenue :");

        javax.swing.GroupLayout PanelRevenueLayout = new javax.swing.GroupLayout(PanelRevenue);
        PanelRevenue.setLayout(PanelRevenueLayout);
        PanelRevenueLayout.setHorizontalGroup(
            PanelRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRevenueLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(PanelRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(PanelRevenueLayout.createSequentialGroup()
                        .addGroup(PanelRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnloadRev, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbyear, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblrevenue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PanelRevenueLayout.createSequentialGroup()
                                .addGroup(PanelRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(cbmonth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanelRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addComponent(cbday, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 699, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelRevenueLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel7, jLabel8, jLabel9});

        PanelRevenueLayout.setVerticalGroup(
            PanelRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelRevenueLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cbmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnloadRev)
                .addGap(18, 18, 18)
                .addGroup(PanelRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelRevenueLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblrevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        PanelRevenueLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel7, jLabel8, jLabel9});

        pagePanel.add(PanelRevenue, "card5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(SidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleParent(this);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        hideallpanel();
        resetColor();
        PanelMenu.setVisible(true);
        btnMenu.setBackground(new Color(221, 190, 170));
        loadMenuItem(VendorID);
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
        hideallpanel();
        resetColor();
        PanelOrder.setVisible(true);
        btnOrder.setBackground(new Color(221, 190, 170));
        loadIncomingOrder();
    }//GEN-LAST:event_btnOrderActionPerformed

    private void btnReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReviewActionPerformed
        hideallpanel();
        resetColor();
        PanelReview.setVisible(true);
        btnReview.setBackground(new Color(221, 190, 170));

    }//GEN-LAST:event_btnReviewActionPerformed

    private void btnRevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevActionPerformed
        hideallpanel();
        resetColor();
        PanelRevenue.setVisible(true);
        btnRev.setBackground(new Color(221, 190, 170));
    }//GEN-LAST:event_btnRevActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        JOptionPane logout = new JOptionPane();
        int choice = logout.showConfirmDialog(
                this,
                "Are you sure you want to log out?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );
        if (choice == JOptionPane.YES_OPTION) {
            this.dispose();
            LoginPage loginPage = new LoginPage(); // Create an instance of the LoginPage
            loginPage.setVisible(true);
        }
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        DefaultTableModel table = (DefaultTableModel) ReviewTable.getModel();
        table.setRowCount(0);
        if (table.getColumnCount() == 0) {
            table.addColumn("ReviewID");
            table.addColumn("ItemID");
            table.addColumn("CustomerID");
            table.addColumn("Review");
            table.addColumn("Rating");
        }
        List<Review> reviews = reviewPage.readReviews(VendorID);
        TableColumnModel columnModel = ReviewTable.getColumnModel();
        int[] columnWidths = {90, 60, 60, 320, 50};
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }
        for (Review review : reviews) {
            String[] rowData = {
                review.getReviewID().toString(),
                review.getItemID(),
                review.getCustomerID(),
                review.getReviewDes(),
                review.getRating()
            };
            table.addRow(rowData);
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        AddItemPage aip = new AddItemPage(VendorID);
        this.dispose();
        aip.setVisible(true);
    }//GEN-LAST:event_btnaddActionPerformed

    private void btndltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndltActionPerformed

        DefaultTableModel model = (DefaultTableModel) itemtable.getModel();
        int selectedrow = itemtable.getSelectedRow();
        if (selectedrow == -1) {
            JOptionPane.showMessageDialog(null, "Please select an item",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete?",
                    "Confirm delete",
                    JOptionPane.YES_NO_OPTION
            );
            if (choice == JOptionPane.YES_OPTION) {
                String selectedItem = model.getValueAt(selectedrow, 1).toString();
                boolean status = mn.deleteItem(VendorID, selectedItem);

                if (status) {
                    JOptionPane.showMessageDialog(null, "Item Deleted Successfully",
                            "Succeed", JOptionPane.INFORMATION_MESSAGE);
                    loadMenuItem(VendorID);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete the item.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while selecting the item: " + e.getMessage(),
                    "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btndltActionPerformed

    private void btnupdateitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateitemActionPerformed
        DefaultTableModel model = (DefaultTableModel) itemtable.getModel();
        int selectedrow = itemtable.getSelectedRow();
        if (selectedrow == -1) {
            JOptionPane.showMessageDialog(null, "Please select an item !",
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to update?",
                "Confirm Update",
                JOptionPane.YES_NO_OPTION
        );
        if (choice == JOptionPane.YES_OPTION) {
            String storeID = model.getValueAt(selectedrow, 0).toString();
            String itemID = model.getValueAt(selectedrow, 1).toString();
            String itemName = model.getValueAt(selectedrow, 2).toString();
            String price = model.getValueAt(selectedrow, 3).toString();

            String updatedLine = storeID + ":::" + itemID + ":::" + itemName + ":::" + price;

            boolean status = file.updateFileLine("src/textFile/menu.txt", itemID, updatedLine);
            if (status) {
                JOptionPane.showMessageDialog(null, "Updated Successfully",
                        "Succeed", JOptionPane.INFORMATION_MESSAGE);
                loadMenuItem(VendorID);
            } else {
                JOptionPane.showMessageDialog(null, "Update Failed",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_btnupdateitemActionPerformed

    private void btnNotiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotiActionPerformed
        noti.readNotificationBriefs("src/textFile/notification.txt", VendorID);
    }//GEN-LAST:event_btnNotiActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        ProfileUpdate pu = new ProfileUpdate(VendorID);
        this.dispose();
        pu.setVisible(true);

    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnloadRevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloadRevActionPerformed
        int year, month, day;
        String dateStr;

        if (cbyear.getSelectedItem() != null && cbmonth.getSelectedItem() != null && cbday.getSelectedItem() != null) {
            day = Integer.parseInt(cbday.getSelectedItem().toString());
            month = Integer.parseInt(cbmonth.getSelectedItem().toString());
            year = Integer.parseInt(cbyear.getSelectedItem().toString());
            dateStr = String.format("%04d%02d%02d", year, month, day);
            boolean status = isValidDate(year, month, day);
            if (status) {
                loadRevenue(dateStr);
                double revenue = calculate(dateStr);
                lblrevenue.setText("RM " + Double.toString(revenue));
            } else {
                JOptionPane.showMessageDialog(null, "Please Select a valid date", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (cbyear.getSelectedItem() != null && cbmonth.getSelectedItem() != null) {
            month = Integer.parseInt(cbmonth.getSelectedItem().toString());
            year = Integer.parseInt(cbyear.getSelectedItem().toString());
            dateStr = String.format("%04d%02d", year, month);
            loadRevenue(dateStr);
            double revenue = calculate(dateStr);
            lblrevenue.setText("RM " + Double.toString(revenue));
        } else {
            year = Integer.parseInt(cbyear.getSelectedItem().toString());
            dateStr = String.format("%04d", year);
            loadRevenue(dateStr);
            double revenue = calculate(dateStr);
            lblrevenue.setText("RM " + Double.toString(revenue));
        }

        cbday.setSelectedIndex(-1);
        cbmonth.setSelectedIndex(-1);

    }//GEN-LAST:event_btnloadRevActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        DefaultTableModel table = (DefaultTableModel) IncomingOrder.getModel();
        int selectedRow = IncomingOrder.getSelectedRow();

        if (selectedRow >= 0) {
            String orderID = table.getValueAt(selectedRow, 0).toString();
            String receiverID = table.getValueAt(selectedRow, 1).toString();
            int status = Order.getOrderStatus(orderID);
            String statusStr = Order.getOrdertype(orderID);

            if (status == Order.PENDING && statusStr.equals("Delivery")) {
                Order.updateOrderStatus(orderID, Order.PENDING);
                JOptionPane.showMessageDialog(this, "Updated Successful", "Succeed",
                        JOptionPane.INFORMATION_MESSAGE);
                Notification notification = new Notification(VendorID, receiverID, "Order Placed", "We're preparing your Food");
                noti.sendNotification(notification, notifilepath);

                String taskLine = file.readFileLine("src/textFile/delivery.txt", orderID);
                if (taskLine != null && !taskLine.equals("false")) {
                    Task.appendTask(taskLine);
                } else {
                    JOptionPane.showMessageDialog(null, "Warning!(task line is null)", "Warning", JOptionPane.WARNING_MESSAGE);
                }

                loadIncomingOrder();
            } else if (status == Order.PENDING && !statusStr.equals("Delivery")) {
                Order.updateOrderStatus(orderID, Order.PREPARING);
                JOptionPane.showMessageDialog(this, "Updated Successful", "Succeed",
                        JOptionPane.INFORMATION_MESSAGE);
                Notification notification = new Notification(VendorID, receiverID, "Order Placed", "We're preparing your Food");
                noti.sendNotification(notification, notifilepath);
                loadIncomingOrder();
            } else {
                JOptionPane.showMessageDialog(this, "You can only ACCEPT orders in PENDING status.", "Invalid Operation", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to reject.", "No Order Selected", JOptionPane.WARNING_MESSAGE);
        }


    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnRejectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectActionPerformed
        DefaultTableModel table = (DefaultTableModel) IncomingOrder.getModel();
        int selectedRow = IncomingOrder.getSelectedRow();
        if (selectedRow >= 0) {
            String orderID = table.getValueAt(selectedRow, 0).toString();
            String receiverID = table.getValueAt(selectedRow, 1).toString();
            int status = Order.getOrderStatus(orderID);
            if (status == Order.PENDING) {
                Order.updateOrderStatus(orderID, Order.REJECT);
                JOptionPane.showMessageDialog(this, "Updated Successful", "Succeed",
                        JOptionPane.INFORMATION_MESSAGE);
                Notification notification = new Notification(VendorID, receiverID, "Order Rejected", "Sorry, your order has been rejected..");
                noti.sendNotification(notification, notifilepath);
                loadIncomingOrder();
            } else {
                JOptionPane.showMessageDialog(this, "You can only reject orders in PENDING status.", "Invalid Operation", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to reject.", "No Order Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnRejectActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        DefaultTableModel table = (DefaultTableModel) IncomingOrder.getModel();
        int selectedRow = IncomingOrder.getSelectedRow();
        if (selectedRow >= 0) {
            String orderID = table.getValueAt(selectedRow, 0).toString();
            String receiverID = table.getValueAt(selectedRow, 1).toString();

            int status = Order.getOrderStatus(orderID);
            String statusStr = Order.getOrdertype(orderID);

            if (status == Order.PREPARING && !statusStr.equals("Delivery")) {
                Order.updateOrderStatus(orderID, Order.DONE);
                Order.saveToOrderHistory(orderID);
                JOptionPane.showMessageDialog(this, "Updated Successful", "Succeed", JOptionPane.INFORMATION_MESSAGE);
                Notification notification = new Notification(VendorID, receiverID, "Order is ready", "Your order is ready to serve. Enjoy~");
                noti.sendNotification(notification, notifilepath);
                loadIncomingOrder();

            } else if (status == Order.RUNNERACCEPTED && statusStr.equals("Delivery")) {
                String TaskID = file.readFileLine("src/textFile/task.txt", orderID).split(":::")[0];
                String runnerID = Order.getRunnerID(orderID);
                
                Order.updateOrderStatus(orderID, Order.DELIVERING);
                JOptionPane.showMessageDialog(this, "Updated Successful", "Succeed", JOptionPane.INFORMATION_MESSAGE);
                Notification notiCus = new Notification(VendorID, receiverID, "Order is ready", "Driver is OTW to deliver your Order...");
                noti.sendNotification(notiCus, notifilepath);
                Notification notiRunner = new Notification(VendorID, runnerID, "FoodReady", TaskID + ",Order is ready to pick up");
                noti.sendNotification(notiRunner, notifilepath);
                loadIncomingOrder();

            } else {
                JOptionPane.showMessageDialog(this, "You can only update delivery orders after Runner accepted", "Invalid Operation", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please select an order to reject.", "No Order Selected", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnhistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhistActionPerformed
        Order.loadAndDisplayOrderHistory(VendorID);
    }//GEN-LAST:event_btnhistActionPerformed

    private double calculate(String dateStr) {
        List<String[]> transactionlist = Transaction.readTransaction(VendorID);
        double totalAmount = 0.0;

        for (String[] transactionData : transactionlist) {
            if (transactionData.length >= 6) {
                String transactionId = transactionData[0];

                double amount = Double.parseDouble(transactionData[3]);
                if (transactionId.contains(dateStr)) {
                    if (transactionData[2].contains(VendorID)) {
                        totalAmount += amount;
                    }
                    if (transactionData[1].contains(VendorID)) {
                        totalAmount -= amount;
                    }
                }
            }
        }
        return totalAmount;
    }

    private boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    private void loadMenuItem(String vendorID) {
        String filePath = file.createTextFile("menu.txt");
        List<String[]> menuLines = file.readKeyLinesData(filePath, vendorID);

        DefaultTableModel table = new DefaultTableModel(new String[]{"Vendor ID", "ItemID", "Item Name", "Price"}, 0);
        itemtable.setModel(table);
        for (String[] line : menuLines) {
            table.addRow(line);
        }
        itemtable.setModel(table);
    }

    private void loadIncomingOrder() {
        DefaultTableModel table = (DefaultTableModel) IncomingOrder.getModel();
        table.setRowCount(0);
        String[] columnNames = {"OrderID", "CustomerID", "Order Type", "Details", "Amount", "Status"};
        table.setColumnIdentifiers(columnNames);
        int[] columnWidths = {80, 80, 80, 260, 80, 80};
        TableColumnModel columnModel = IncomingOrder.getColumnModel();
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

        List<String[]> orders = Order.loadOrder();

        for (String[] order : orders) {
            if (order[2].equals(VendorID)) {
                String status = order[8].toString();
                if (status != "Done" && status != "Reject") {
                    String[] rowData = {order[0], order[1], order[3], order[5], order[6], order[8]};
                    table.addRow(rowData);
                }
            }
        }
    }

    private void loadRevenue(String dateStr) {
        List<String[]> transactiondata = Transaction.readTransaction(VendorID);
        DefaultTableModel table = (DefaultTableModel) revenuetable.getModel();
        table.setRowCount(0);
        String[] columnNames = {"TransactionID", "Amount", "Time", "Description"};
        table.setColumnIdentifiers(columnNames);
        TableColumnModel columnModel = revenuetable.getColumnModel();
        int[] columnWidths = {100, 80, 80, 240};
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }

        for (String[] rowData : transactiondata) {
            if (table.getColumnCount() >= 4) {
                String transactionDate = rowData[0].substring(0, 8); // Extract the date portion
                if (transactionDate.contains(dateStr)) {
                    if (rowData[1].contains(VendorID)) {
                        rowData[3] = "-" + rowData[3];
                    }
                    String[] tempData = {rowData[0], rowData[3], rowData[4], rowData[5]};
                    table.addRow(tempData);
                }
            }
        }
    }

    private void hideallpanel() {
        PanelMenu.setVisible(false);
        PanelOrder.setVisible(false);
        PanelRevenue.setVisible(false);
        PanelReview.setVisible(false);
    }

    private void resetColor() {
        btnMenu.setBackground(new Color(255, 245, 238));
        btnOrder.setBackground(new Color(255, 245, 238));
        btnRev.setBackground(new Color(255, 245, 238));
        btnReview.setBackground(new Color(255, 245, 238));
    }

    public static void main(String userID) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendorPage(userID).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable IncomingOrder;
    private javax.swing.JPanel PanelMenu;
    private javax.swing.JPanel PanelOrder;
    private javax.swing.JPanel PanelRevenue;
    private javax.swing.JPanel PanelReview;
    private javax.swing.JTable ReviewTable;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnNoti;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnProfile;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnReject;
    private javax.swing.JButton btnRev;
    private javax.swing.JButton btnReview;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndlt;
    private javax.swing.JButton btnhist;
    private javax.swing.JButton btnloadRev;
    private javax.swing.JButton btnupdateitem;
    private javax.swing.JComboBox<String> cbday;
    private javax.swing.JComboBox<String> cbmonth;
    private javax.swing.JComboBox<String> cbyear;
    private javax.swing.JTable itemtable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblrevenue;
    private javax.swing.JPanel pagePanel;
    private javax.swing.JTable revenuetable;
    // End of variables declaration//GEN-END:variables
}
