package com.vinpos.pos.CakeOrderPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.vinpos.pos.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
/**
 *
 * @author vinson
 */
public class CakeTable extends javax.swing.JDialog {

    /**
     * Creates new form CakeOrderPan
     */
    private SelectDate dialog;
    private SelectCake cake;
    private String name;
    private String size;
    private Double price;
    private String date;
    private String handwriting;
    private String customername;
    private String contactnumber;
    
    private ArrayList<Object> dataObjectForSubTable;
    private int selectedRowsWid;
    
    public CakeTable(java.awt.Frame parent, boolean modal, MyQuery myQ_, int userID_) {
        super(parent, modal);
        initComponents();
        myQ=myQ_;
        userID= userID_;
        dialog= new SelectDate(new javax.swing.JFrame(), true);
        cake = new SelectCake(new javax.swing.JFrame(), true);
        this.setLocationRelativeTo(null);       
        name="";
        size="";
        price=0.00;
        date="";
        handwriting="";
        customername="";
        contactnumber="";
        selectedRowsWid=-1;
        this.selectDefault();
        this.reflashJTable();
        
    }
    public void reflashJTable(){
        if(defaultF.getBackground().equals(Color.blue)){
            this.getTodayAndFuturesOrderIntoJTable();
        }else if(allF.getBackground().equals(Color.blue)){
            this.getAllOrderIntoJTable();
        }else if(tomorrowF.getBackground().equals(Color.blue)){
            this.getTomorrowsOrderIntoJTable();
        }else if(todayF.getBackground().equals(Color.blue)){
            this.getTodayOrderIntoJTable();
        }
    }
    public void selectDefault(){
        defaultF.setBackground(Color.blue);
        defaultF.setForeground(Color.yellow);
        allF.setBackground(Color.white);
        allF.setForeground(Color.blue);
        tomorrowF.setBackground(Color.white);
        tomorrowF.setForeground(Color.blue);
        todayF.setBackground(Color.white);
        todayF.setForeground(Color.blue);
        
    }
     public void selectAll(){
        defaultF.setBackground(Color.white);
        defaultF.setForeground(Color.blue);
        allF.setBackground(Color.blue);
        allF.setForeground(Color.yellow);
        tomorrowF.setBackground(Color.white);
        tomorrowF.setForeground(Color.blue);
        todayF.setBackground(Color.white);
        todayF.setForeground(Color.blue);
        
    }
     public void selectTomorrow(){
        defaultF.setBackground(Color.white);
        defaultF.setForeground(Color.blue);
        allF.setBackground(Color.white);
        allF.setForeground(Color.blue);
        tomorrowF.setBackground(Color.blue);
        tomorrowF.setForeground(Color.yellow);
        todayF.setBackground(Color.white);
        todayF.setForeground(Color.blue);
        
    }
      public void selectToday(){
        defaultF.setBackground(Color.white);
        defaultF.setForeground(Color.blue);
        allF.setBackground(Color.white);
        allF.setForeground(Color.blue);
        tomorrowF.setBackground(Color.white);
        tomorrowF.setForeground(Color.blue);
        todayF.setBackground(Color.blue);
        todayF.setForeground(Color.yellow);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datebtn = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        receiptNum = new javax.swing.JFormattedTextField();
        tomorrowF = new javax.swing.JButton();
        defaultF = new javax.swing.JButton();
        allF = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        todayF = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        datebtn.setBackground(new java.awt.Color(255, 102, 51));
        datebtn.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        datebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17937-01.png"))); // NOI18N
        datebtn.setText("Select Date");
        datebtn.setBorder(null);
        datebtn.setBorderPainted(false);
        datebtn.setFocusPainted(false);
        datebtn.setIconTextGap(20);
        datebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datebtnActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 102, 51));
        jButton5.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17935-01.png"))); // NOI18N
        jButton5.setText("Select Cake");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setFocusPainted(false);
        jButton5.setIconTextGap(20);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 102, 51));
        jButton8.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17936-01.png"))); // NOI18N
        jButton8.setText("Select Writing");
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setFocusPainted(false);
        jButton8.setIconTextGap(20);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 102, 51));
        jButton6.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17939-01.png"))); // NOI18N
        jButton6.setText("Contact & Notes");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setFocusPainted(false);
        jButton6.setIconTextGap(20);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 102, 51));
        jButton9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17938-01.png"))); // NOI18N
        jButton9.setText("Final Total");
        jButton9.setActionCommand("Step 3 Final Total");
        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.setFocusPainted(false);
        jButton9.setIconTextGap(20);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));
        jPanel1.setForeground(new java.awt.Color(153, 153, 255));
        jPanel1.setToolTipText("");
        jPanel1.setMinimumSize(new java.awt.Dimension(588, 980));
        jPanel1.setPreferredSize(new java.awt.Dimension(588, 980));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(204, 255, 102));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setForeground(new java.awt.Color(0, 51, 51));
        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 80, 40));

        receiptNum.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        receiptNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        receiptNum.setText("0");
        receiptNum.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jPanel12.add(receiptNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 140, 40));

        tomorrowF.setForeground(new java.awt.Color(0, 51, 51));
        tomorrowF.setText("Pick Up Tomorrow");
        tomorrowF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomorrowFActionPerformed(evt);
            }
        });
        jPanel12.add(tomorrowF, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 190, 40));

        defaultF.setForeground(new java.awt.Color(0, 51, 51));
        defaultF.setText("Default");
        defaultF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultFActionPerformed(evt);
            }
        });
        jPanel12.add(defaultF, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 10, 110, 40));

        allF.setForeground(new java.awt.Color(0, 51, 51));
        allF.setText("All Orders");
        allF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allFActionPerformed(evt);
            }
        });
        jPanel12.add(allF, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, 110, 40));

        jLabel2.setText("Number");
        jPanel12.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 50, 20));

        jLabel3.setText("Receipt");
        jPanel12.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 20));

        todayF.setForeground(new java.awt.Color(0, 51, 51));
        todayF.setText("Pick Up Today");
        todayF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayFActionPerformed(evt);
            }
        });
        jPanel12.add(todayF, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 130, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 255));
        jLabel5.setText("Filters:");
        jPanel12.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 80, 40));

        jPanel1.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 830, 1180, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 0));

        jScrollPane1.setBackground(new java.awt.Color(102, 0, 102));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(51, 255, 204));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 1180, 680));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 0, 70, 60));

        jButton11.setBackground(new java.awt.Color(255, 102, 102));
        jButton11.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 0, 70, 60));

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Cake Orders Panel");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1202, 70));

        jButton7.setBackground(new java.awt.Color(102, 255, 102));
        jButton7.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 51, 51));
        jButton7.setText("New Order");
        jButton7.setActionCommand("Step 4 Print Out");
        jButton7.setIconTextGap(20);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 160, 80));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 255, 204));
        jLabel4.setText("Click orders in the list to view or edit");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 110, 400, 50));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 255, 204));
        jLabel6.setText("<---- Click here to Create Cake Order");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 430, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

         
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void datebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datebtnActionPerformed

    }//GEN-LAST:event_datebtnActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        CakeOrderPan order = new CakeOrderPan(new java.awt.Frame(), true, myQ);
        selectedRowsWid= order.initNewOrder(userID);
        order.setVisible(true);
        this.reflashJTable();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try{
                   CakeOrderPan order = new CakeOrderPan(new java.awt.Frame(), true, myQ);                  
                   order.reOpenOrder(Integer.parseInt(receiptNum.getText()));
                   order.setVisible(true);
                   CakeTable.this.reflashJTable();
       }catch(NullPointerException e){};
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tomorrowFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomorrowFActionPerformed
        this.selectTomorrow();
        this.reflashJTable();
    }//GEN-LAST:event_tomorrowFActionPerformed

    private void defaultFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultFActionPerformed
        this.selectDefault();
        this.reflashJTable();
    }//GEN-LAST:event_defaultFActionPerformed

    private void allFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allFActionPerformed
        this.selectAll();
        this.reflashJTable();
    }//GEN-LAST:event_allFActionPerformed

    private void todayFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayFActionPerformed
        this.selectToday();
        this.reflashJTable();
    }//GEN-LAST:event_todayFActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        
            this.setVisible(false);
        
    }//GEN-LAST:event_jButton11ActionPerformed
    public void getTodayOrderIntoJTable(){
        dataObjectForSubTable = new ArrayList<Object>();
        ArrayList<Object> temp=myQ.addTodaysWholeCakeOrdersToDataObject(); 
        this.addSubTableRowsToDataObject(temp);       
        Collections.reverse(dataObjectForSubTable);
        Object[][] finalData = dataObjectForSubTable.toArray(new Object[dataObjectForSubTable.size()][]);
        this.buildJtableAfterMapData(finalData);
    }
    public void getTomorrowsOrderIntoJTable(){
        dataObjectForSubTable = new ArrayList<Object>();
        ArrayList<Object> temp=myQ.addTomorrowsWholeCakeOrdersToDataObject(); 
        this.addSubTableRowsToDataObject(temp);       
        Collections.reverse(dataObjectForSubTable);
        Object[][] finalData = dataObjectForSubTable.toArray(new Object[dataObjectForSubTable.size()][]);
        this.buildJtableAfterMapData(finalData);
    }
    public void getTodayAndFuturesOrderIntoJTable(){
        dataObjectForSubTable = new ArrayList<Object>();
        ArrayList<Object> temp=myQ.addTodaysAndFuturesWholeCakeOrdersToDataObject(); 
        this.addSubTableRowsToDataObject(temp);       
        Collections.reverse(dataObjectForSubTable);
        Object[][] finalData = dataObjectForSubTable.toArray(new Object[dataObjectForSubTable.size()][]);
        this.buildJtableAfterMapData(finalData);
    }
    public void getAllOrderIntoJTable(){
          dataObjectForSubTable = new ArrayList<Object>();
          ArrayList<Object> temp=myQ.addWholeCakeOrdersToDataObject(); 
          this.addSubTableRowsToDataObject(temp);       
          Collections.reverse(dataObjectForSubTable);
          Object[][] finalData = dataObjectForSubTable.toArray(new Object[dataObjectForSubTable.size()][]);
          this.buildJtableAfterMapData(finalData);
          System.out.printf("updateJTable button pressed");
    }
    
    public void addSubTableRowsToDataObject(ArrayList<Object> temp){
            SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy / MM / dd    E");
             
            for(int i=0;i<temp.size(); i++){
                temp.get(i);
                Object[] o = (Object[])temp.get(i);
                String t1 = "";
                String t2 = "";
                String t3 = "";
                String t4 = "";
                String t5 = "";
                String t6 = "";
                String t7 = "";
                String t8 = "";
                String t9 = "";
                
                try{
                    t1=o[0].toString();
                }catch(NullPointerException e){}
                 try{
                    Date date = new Date();
                    date=(Date)o[1];
                    t2=dateFormater.format(date);
                }catch(NullPointerException e){}
                  try{
                    t3=o[2].toString();
                }catch(NullPointerException e){}
                   try{
                    t4=o[3].toString();
                }catch(NullPointerException e){}
                 try{
                    t5=o[4].toString();
                }catch(NullPointerException e){}
                 try{
                    t6=o[5].toString();
                }catch(NullPointerException e){}
                 try{
                    t7=o[6].toString();
                }catch(NullPointerException e){}
                try{
                    int paid =(Integer)o[7];
                    if(paid==0){
                        t8="Not Paid Yet";
                    }else{
                        t8="Paid";
                    }
                }catch(NullPointerException e){}
                try{
                    t9=o[8].toString();
                }catch(NullPointerException e){} 
                
                Object[] s = new Object[]{t1, t2, t3,t4,t5,t6,t7,t8, t9};
                dataObjectForSubTable.add(s);
            }
    }
    
    public void buildJtableAfterMapData(Object[][] data) {
        DefaultTableModel model;
        final ColorTable colorTable;
        
        model = new DefaultTableModel(data,
                new Object[]{"Receipt#", "Pick up Date", "pick up time","Cake Name","Size","Customer","Phone","Paid","Server"});
        colorTable = new ColorTable(model);
        colorTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        colorTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        colorTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        colorTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        colorTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        colorTable.getColumnModel().getColumn(5).setPreferredWidth(50);
        colorTable.getColumnModel().getColumn(6).setPreferredWidth(50);
        colorTable.getColumnModel().getColumn(7).setPreferredWidth(50);
        colorTable.getColumnModel().getColumn(8).setPreferredWidth(50);
        colorTable.setRowHeight(30);

        colorTable.getTableHeader().setReorderingAllowed(false);
        colorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        colorTable.setFillsViewportHeight(true);
        colorTable.setRowColor(getRowByValue(colorTable.getModel(), ""+selectedRowsWid), Color.YELLOW);
        colorTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable source = (JTable)e.getSource();
                int row = source.rowAtPoint( e.getPoint() );
                //int column = source.columnAtPoint( e.getPoint() ); // column number of the table 
                colorTable.setRowColor(row, Color.GREEN);
                int wid = getOIDFromSelectedRow(colorTable);
                selectedRowsWid = wid;
                 
                
                if(SwingUtilities.isLeftMouseButton(e))
                {

                        if( e.getModifiers()==18 || e.getModifiers()==24){ // control = 18 // alt =24    
                            myQ.voidEmptyWholeCakeCheck(wid);
                            CakeTable.this.reflashJTable();
                        }
                       
                        else if (wid != -1) {
                        // call a method check sent or not, if sent then check account, if not admin then require admin password
                           CakeOrderPan order = new CakeOrderPan(new java.awt.Frame(), true, myQ);
                           order.reOpenOrder(wid);
                           order.setVisible(true);
                           CakeTable.this.reflashJTable();
                        } 
                         

                }
                
                
               
                
                
            }
        });
        jScrollPane1.getViewport().add(colorTable);  
    }
    public int getRowNumberWithWid(JTable from) {
        int oid = -1;
        int[] rows = from.getSelectedRows();
        if (rows.length > 0) {
            try{
                Object object =from.getValueAt(rows[0], 0);
                
                oid = Integer.valueOf(((String) object).substring(4,10));
                //oid = Integer.parseInt(from.getValueAt(rows[0], 0));
            }catch(ClassCastException e){
                System.out.println("getOIDFromSelectedRow:"+from.getValueAt(rows[0], 0)+e+" "+e.getMessage());
            }catch(java.lang.NumberFormatException e){
                System.out.println("getOIDFromSelectedRow:"+from.getValueAt(rows[0], 0)+e+" "+e.getMessage());
            }

        } 
        from.clearSelection();
        return oid;

    }
    public int getRowByValue(TableModel model, Object value) {
        for (int i = model.getRowCount() - 1; i >= 0; --i) {
            for (int j = model.getColumnCount() - 1; j >= 0; --j) {
                if (  ((String)model.getValueAt(i, j)).contains((String)value)) {
                    // what if value is not unique?
                    return i;
                }
            }
        }
        return -1;
    }    
 
    public int getOIDFromSelectedRow(JTable from) {
        int oid = -1;
        int[] rows = from.getSelectedRows();
        if (rows.length > 0) {
            try{
                Object object =from.getValueAt(rows[0], 0);
                
                oid = Integer.valueOf(((String) object).substring(4,10));
                //oid = Integer.parseInt(from.getValueAt(rows[0], 0));
            }catch(ClassCastException e){
                System.out.println("getOIDFromSelectedRow:"+from.getValueAt(rows[0], 0)+e+" "+e.getMessage());
            }catch(java.lang.NumberFormatException e){
                System.out.println("getOIDFromSelectedRow:"+from.getValueAt(rows[0], 0)+e+" "+e.getMessage());
            }

        } 
        from.clearSelection();
        return oid;

    }
    /**
     * @param args the command line arguments
     */
    public void run(){
        
        this.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton allF;
    private javax.swing.JButton datebtn;
    private javax.swing.JButton defaultF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField receiptNum;
    private javax.swing.JButton todayF;
    private javax.swing.JButton tomorrowF;
    // End of variables declaration//GEN-END:variables
private PrintTest3 s4 = new PrintTest3();
private PrintForCustomerCakeOrder s = new PrintForCustomerCakeOrder();
private String paidOrNot ="Not Paid Yet";
private NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
private MyQuery myQ;
private int userID;
}
