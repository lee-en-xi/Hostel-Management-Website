package GUIDesignCustomer;

import classFile.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerMenu extends javax.swing.JPanel {
    private String customerId;
    private List<String> storeList;
    private String storeId;
    private String address;
    private String tempOrderStr;
    private double totalAmount;
    private List <String[]> dataList;
    private String requestedPickUpTime;
    private String requestedDeliveryTime;
    private double deliveryFee;
            
    private boolean isComboBoxInteracted = false; // New flag
    private String previousStoreSelection = null; // Field to store previous selection
    private boolean isDeliveryFeeAdded = false;

    public CustomerMenu() {
        tempOrderStr = "";
        initComponents();

        pnlMenu.setVisible(true);
        pnlCart.setVisible(false);

        setStoreList(Menu.getStore());
        addToComboBox();

        // Temporarily remove the action listener
        ActionListener[] listeners = cbStore.getActionListeners();
        for (ActionListener listener : listeners) {
            cbStore.removeActionListener(listener);
        }

        // Set the selected index
        cbStore.setSelectedIndex(-1);

        // Add the action listener back
        for (ActionListener listener : listeners) {
            cbStore.addActionListener(listener);
        }

        // Now that the component is initialized, set the flag to true
        isComboBoxInteracted = true;
        
    }

    public String getCustomerID() {
        return customerId;
    }
    
    public void setCustomerID(String customerId) {
        this.customerId = customerId;
        
        this.requestedPickUpTime = Task.generateTime(10, 20);   //Random Current Time + 10 to 30 Minutes
        this.requestedDeliveryTime = Task.generateTime(40, 10); //Random Current Time + 40 to 50 Minutes
        this.deliveryFee = Double.parseDouble(Task.generateDeliveryFee(requestedPickUpTime, requestedDeliveryTime));
        lblDeliveryFee.setText("Delivery Fee (RM): " + deliveryFee);
    }
    
    public List<String> getStoreList() {
        return storeList;
    }
    
    public void setStoreList(List<String> storeList) {
        this.storeList = storeList;
    }
    
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
        addToTable(tblMenu);
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getTempOrderStr() {
        return tempOrderStr;
    }

    public void setTempOrderStr(String tempOrderStr) {
        this.tempOrderStr = tempOrderStr;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List <String[]> getDataList() {
        return dataList;
    }

    public void setDataList(List <String[]> dataList) {
        this.dataList = dataList;
    }

    public void addOrder(OrderItem newOrderItem) {
        boolean itemExists = false;

        if (tempOrderStr != null && !tempOrderStr.isEmpty()) {
            String[] orders = tempOrderStr.split("<newline>");
            StringBuilder updatedOrders = new StringBuilder();

            for (String order : orders) {
                OrderItem existingOrderItem = OrderItem.fromString(order);
                if (existingOrderItem.getItemID().equals(newOrderItem.getItemID())) {
                    // Update the existing item's quantity and subtotal
                    int updatedQuantity = existingOrderItem.getQuantity() + newOrderItem.getQuantity();
                    double updatedSubtotal = existingOrderItem.getSubAmount() + newOrderItem.getSubAmount();
                    existingOrderItem.setQuantity(updatedQuantity);
                    existingOrderItem.setSubAmount(updatedSubtotal);

                    updatedOrders.append(existingOrderItem.toString()).append("<newline>");
                    itemExists = true;
                } else {
                    updatedOrders.append(order).append("<newline>");
                }
            }

            if (!itemExists) {
                // If the item doesn't exist in the cart, add it as a new line
                updatedOrders.append(newOrderItem.toString()).append("<newline>");
            }

            // Update tempOrderStr with the new or updated order
            tempOrderStr = updatedOrders.toString().trim();
        } else {
            tempOrderStr = newOrderItem.toString() + "<newline>";
        }

        // Update total amount
        this.totalAmount += newOrderItem.getSubAmount();
        spQuantity.setValue(1);
        updateTotalLabel(); // Make sure to update the total label
    }

    public void clearCart() {
        DefaultTableModel model = (DefaultTableModel)tblCart.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        this.tempOrderStr = null;
        this.totalAmount = 0;
    }
    
    private void updateStoreSelection() {
        Object selectedItem = cbStore.getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        String storeName = selectedItem.toString();
        for (String store : getStoreList()) {
            if (store.contains(storeName)) {
                previousStoreSelection = storeName; // Update previous selection
                String storeId = store.split(":::")[0];
                dataList = Menu.readMenu(storeId);
                setStoreId(storeId);
                break;
            }
        }
    }
    
    public String getSelectedType() {
        if(rbDineIn.isSelected()) {
            return rbDineIn.getText();
        } else if(rbTakeAway.isSelected()) {
            return rbTakeAway.getText();
        } else if(rbDelivery.isSelected()) {
            return rbDelivery.getText();
        }
        return null;
    }

    private void updateTotalLabel() {
        DecimalFormat df = new DecimalFormat("#.00");
        lblTotal.setText(df.format(totalAmount));
    }
        
    private void addToComboBox() {
        setStoreList(Menu.getStore());
        for(String store : getStoreList()) {
            String[] data = store.split(":::");
            cbStore.addItem(data[1]);
        }
        // Add action listener to update the flag when user interacts with the combobox
        cbStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isComboBoxInteracted = true;
            }
        });
    }
    
    private void addToTable(JTable table) {
        if(dataList.isEmpty()) {
            return;
        }
        
        String[] columns = dataList.get(0);
        
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        
        for (String column : columns) {
            model.addColumn(column);
        }
        
        for (int i = 1; i < dataList.size(); i++) {
            String[] row = dataList.get(i);
            if (row != null && row.length > 0 && !isRowEmpty(row)) {
                model.addRow(row);
            }
        }
    }
    
    private boolean isRowEmpty(String[] row) {
        for (String cell : row) {
            if (cell != null && !cell.trim().isEmpty()) {
                return false; // If any cell is non-empty, the row is not empty
            }
        }
        return true; // If all cells are empty, the row is empty
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cbStore = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMenu = new javax.swing.JTable();
        btnAddToCart = new javax.swing.JButton();
        btnReadReview = new javax.swing.JButton();
        btnViewCart = new javax.swing.JButton();
        spQuantity = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        pnlCart = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        btnPlaceOrder = new javax.swing.JButton();
        rbDineIn = new javax.swing.JRadioButton();
        rbTakeAway = new javax.swing.JRadioButton();
        rbDelivery = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnReturn = new javax.swing.JButton();
        lblDeliveryFee = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        pnlMenu.setBackground(new java.awt.Color(245, 199, 125));

        jLabel1.setFont(new java.awt.Font("French Script MT", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 51, 0));
        jLabel1.setText("Menu");

        jPanel2.setBackground(new java.awt.Color(255, 157, 0));

        cbStore.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        cbStore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbStoreActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 51, 0));
        jLabel2.setText("Store:");

        tblMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblMenu);

        btnAddToCart.setBackground(new java.awt.Color(252, 231, 196));
        btnAddToCart.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnAddToCart.setForeground(new java.awt.Color(153, 51, 0));
        btnAddToCart.setText("Add To Cart");
        btnAddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToCartActionPerformed(evt);
            }
        });

        btnReadReview.setBackground(new java.awt.Color(252, 231, 196));
        btnReadReview.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnReadReview.setForeground(new java.awt.Color(153, 51, 0));
        btnReadReview.setText("Read Review");
        btnReadReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadReviewActionPerformed(evt);
            }
        });

        btnViewCart.setBackground(new java.awt.Color(252, 231, 196));
        btnViewCart.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnViewCart.setForeground(new java.awt.Color(153, 51, 0));
        btnViewCart.setText("View Cart");
        btnViewCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewCartActionPerformed(evt);
            }
        });

        spQuantity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spQuantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel3.setFont(new java.awt.Font("STSong", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 51, 0));
        jLabel3.setText("Quantity:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cbStore, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnViewCart))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(btnReadReview, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(spQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbStore, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnViewCart, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReadReview, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spQuantity)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(pnlMenu, "card2");

        pnlCart.setBackground(new java.awt.Color(245, 199, 125));

        jLabel4.setFont(new java.awt.Font("French Script MT", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 51, 0));
        jLabel4.setText("Cart");

        jPanel3.setBackground(new java.awt.Color(255, 157, 0));

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblCart);

        btnPlaceOrder.setBackground(new java.awt.Color(252, 231, 196));
        btnPlaceOrder.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnPlaceOrder.setForeground(new java.awt.Color(153, 51, 0));
        btnPlaceOrder.setText("Place Order");
        btnPlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlaceOrderActionPerformed(evt);
            }
        });

        rbDineIn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        rbDineIn.setForeground(new java.awt.Color(153, 51, 0));
        rbDineIn.setSelected(true);
        rbDineIn.setText("Dine In");
        rbDineIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDineInActionPerformed(evt);
            }
        });

        rbTakeAway.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        rbTakeAway.setForeground(new java.awt.Color(153, 51, 0));
        rbTakeAway.setText("Take Away");
        rbTakeAway.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTakeAwayActionPerformed(evt);
            }
        });

        rbDelivery.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        rbDelivery.setForeground(new java.awt.Color(153, 51, 0));
        rbDelivery.setText("Delivery");
        rbDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDeliveryActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 51, 0));
        jLabel5.setText("Total Amount (RM):");

        lblTotal.setFont(new java.awt.Font("Arial Narrow", 1, 24)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(153, 51, 0));
        lblTotal.setText("0.00");

        btnReturn.setBackground(new java.awt.Color(252, 231, 196));
        btnReturn.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnReturn.setForeground(new java.awt.Color(153, 51, 0));
        btnReturn.setText("Return to Menu");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        lblDeliveryFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDeliveryFee.setText("Delivery Fee (RM): 5.00 ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rbDineIn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rbTakeAway)
                                .addGap(35, 35, 35)
                                .addComponent(rbDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblDeliveryFee))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnPlaceOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(lblTotal)
                                .addGap(35, 35, 35))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbDineIn)
                    .addComponent(rbDelivery)
                    .addComponent(rbTakeAway)
                    .addComponent(lblDeliveryFee))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPlaceOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(lblTotal))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCartLayout = new javax.swing.GroupLayout(pnlCart);
        pnlCart.setLayout(pnlCartLayout);
        pnlCartLayout.setHorizontalGroup(
            pnlCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlCartLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCartLayout.setVerticalGroup(
            pnlCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCartLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(pnlCart, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void rbDineInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDineInActionPerformed
        if (rbDelivery.isSelected()) {
            totalAmount -= deliveryFee;
            isDeliveryFeeAdded = false;
        }
        rbDineIn.setSelected(true);
        rbTakeAway.setSelected(false);
        rbDelivery.setSelected(false);
        updateTotalLabel();
    }//GEN-LAST:event_rbDineInActionPerformed

    private void rbTakeAwayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTakeAwayActionPerformed
        if (rbDelivery.isSelected()) {
            totalAmount -= deliveryFee;
            isDeliveryFeeAdded = false;
        }
        rbTakeAway.setSelected(true);
        rbDineIn.setSelected(false);
        rbDelivery.setSelected(false);
        updateTotalLabel();
    }//GEN-LAST:event_rbTakeAwayActionPerformed

    private void rbDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDeliveryActionPerformed
        if (rbDineIn.isSelected() || rbTakeAway.isSelected()) {
            totalAmount += deliveryFee;
            isDeliveryFeeAdded = true;
        }
        rbDelivery.setSelected(true);
        rbDineIn.setSelected(false);
        rbTakeAway.setSelected(false);
        updateTotalLabel();
    }//GEN-LAST:event_rbDeliveryActionPerformed

    private void btnViewCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewCartActionPerformed
        List<String[]> list = new ArrayList<>();
        String[] columns = {"ItemID", "Item", "Quantity", "Subtotal"};
        list.add(columns);
        String[] orders = tempOrderStr.split("<newline>");
        for(String order :  orders) {
            String[] orderData = order.split("\\|");
            list.add(orderData);
        }
        dataList = list;
        
        pnlCart.setVisible(true);
        pnlMenu.setVisible(false);
        
        DecimalFormat df = new DecimalFormat("#.00");
        lblTotal.setText(df.format(totalAmount));
        addToTable(tblCart);
    }//GEN-LAST:event_btnViewCartActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        pnlCart.setVisible(false);
        pnlMenu.setVisible(true);
    }//GEN-LAST:event_btnReturnActionPerformed

    private void cbStoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbStoreActionPerformed
        if (!isComboBoxInteracted) {
            previousStoreSelection = (String) cbStore.getSelectedItem();
            return;
        }

        if (tempOrderStr == null || tempOrderStr.isEmpty()) {
            previousStoreSelection = (String) cbStore.getSelectedItem();
            updateStoreSelection();
            return;
        }

        String title = "Clear Cart Warning";
        String message = "This operation will clear your cart\nAre you sure you want to do so?";
        int choice = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choice == JOptionPane.YES_OPTION) {
            clearCart();
            previousStoreSelection = (String) cbStore.getSelectedItem();
            updateStoreSelection();
        } else {
            // Revert the combobox selection to the previous item
            cbStore.setSelectedItem(previousStoreSelection);
        }
    }//GEN-LAST:event_cbStoreActionPerformed

    private void btnReadReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadReviewActionPerformed
        int selectedRow = tblMenu.getSelectedRow();
        if (selectedRow != -1) {
            String selectedItemId = (String)tblMenu.getValueAt(selectedRow, 0);
            String selectedItemName = (String)tblMenu.getValueAt(selectedRow, 1);
            
            String storeName = cbStore.getSelectedItem().toString();
            String storeId = null;
            for (String store : storeList) {
                if (store.contains(storeName)) {
                    storeId = store.split(":::")[0];
                    break;
                }
            }
            
            if (selectedItemId == null || storeId == null) {
                // Handle the case where either ID is null
                JOptionPane.showMessageDialog(this, "Item or Store ID is missing.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            new CustomerReadReview(selectedItemId + " " + selectedItemName, storeId).setVisible(true);
        }
    }//GEN-LAST:event_btnReadReviewActionPerformed

    private void btnAddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToCartActionPerformed
        int selectedRow = tblMenu.getSelectedRow();
        if (selectedRow != -1) {
            String selectedItemId = (String)tblMenu.getValueAt(selectedRow, 0);
            String selectedItemName = (String)tblMenu.getValueAt(selectedRow, 1);
            int quantity = (int)spQuantity.getValue();
            double selectedItemPrice = Double.parseDouble(tblMenu.getValueAt(selectedRow, 2).toString());
            double subtotal = selectedItemPrice * quantity;
            OrderItem orderItem = new OrderItem(selectedItemId, selectedItemName, quantity, subtotal);
            addOrder(orderItem);
        }
    }//GEN-LAST:event_btnAddToCartActionPerformed

    private void btnPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlaceOrderActionPerformed
        if (!tempOrderStr.equals("")) {
            Customer customer = Customer.readCustomerProfile(customerId);
            if (!customer.isSufficientBalance(totalAmount)) {
                JOptionPane.showMessageDialog(null, "Your balance is not enough.\nPlease top up to continue the payment.");
                return;
            }
            int choice1 = JOptionPane.showConfirmDialog(null, "Are you sure you want to make a payment?", "Place Order", JOptionPane.YES_NO_OPTION);
            if (choice1 == JOptionPane.YES_OPTION) {
                String type = getSelectedType();
                String address = "";
                if (type.equals("Delivery")) {
                    boolean locationConfirmed = false;
                    while (!locationConfirmed) {
                        address = JOptionPane.showInputDialog(null, "Please input your delivery location:", "");
                        if (address == null || address.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "You cannot input an empty location.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            int choice2 = JOptionPane.showConfirmDialog(null, "Please confirm your location: " + address, "Confirm Location", JOptionPane.YES_NO_OPTION);
                            // Confirm location
                            if (choice2 == JOptionPane.YES_OPTION) {
                                locationConfirmed = true;
                            }
                        }
                    }
                }

                List<String[]> orderList = new ArrayList<>();
                String[] orders = tempOrderStr.split("<newline>");
                //To form a string that has a format: itemName1*quantity,itemName2*quantity, ...
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < orders.length; i++) {
                    String order = orders[i];
                    String[] orderData = order.split("\\|");
                    sb.append(orderData[1]).append("*").append(orderData[2]);
                    if (i != orders.length -1 ) {
                        sb.append(",");
                    }
                    orderList.add(orderData);
                }
                String orderDetail = sb.toString();

                double delivery = 0;
                if(rbDelivery.isSelected()) {
                    totalAmount -= deliveryFee;
                    delivery = deliveryFee;
                }

                Order order = new Order(customerId, storeId, type, address, orderDetail, totalAmount);

                customer.makePayment(order, delivery, requestedPickUpTime, requestedDeliveryTime);

                //Clean up all things
                DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
                model.setRowCount(0);
                rbDineIn.setSelected(true);
                rbTakeAway.setSelected(false);
                rbDelivery.setSelected(false);
                lblTotal.setText("0.00");
                //generate new info
                this.requestedPickUpTime = Task.generateTime(10, 20);   //Random Current Time + 10 to 30 Minutes
                this.requestedDeliveryTime = Task.generateTime(40, 10); //Random Current Time + 40 to 50 Minutes
                this.deliveryFee = Double.parseDouble(Task.generateDeliveryFee(requestedPickUpTime, requestedDeliveryTime));
                lblDeliveryFee.setText("Delivery Fee (RM): " + deliveryFee);

                totalAmount = 0;
                tempOrderStr = "";
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please add something to the cart first");
        }
    }//GEN-LAST:event_btnPlaceOrderActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToCart;
    private javax.swing.JButton btnPlaceOrder;
    private javax.swing.JButton btnReadReview;
    private javax.swing.JButton btnReturn;
    private javax.swing.JButton btnViewCart;
    private javax.swing.JComboBox<String> cbStore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDeliveryFee;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlCart;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JRadioButton rbDelivery;
    private javax.swing.JRadioButton rbDineIn;
    private javax.swing.JRadioButton rbTakeAway;
    private javax.swing.JSpinner spQuantity;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblMenu;
    // End of variables declaration//GEN-END:variables

}
