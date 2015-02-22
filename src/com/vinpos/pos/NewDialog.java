package com.vinpos.pos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinson
 */
public class NewDialog extends javax.swing.JDialog {

    /**
     * Creates new form EDITDialog
     */
    
    public NewDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.groupUpButtons();
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
    }
    public boolean run(int tableID_, int userID_){
        tableID=tableID_;
        userID=userID_;
        this.setVisible(true);
        return isUpdated;
        
        
    }
    
    public void groupUpButtons(){
        buttonGroup1.add(r1);
        buttonGroup1.add(r2);
        buttonGroup1.add(r3);
        buttonGroup1.add(r4);
        buttonGroup1.add(r5);
        buttonGroup1.add(r6);
        buttonGroup1.add(r7);
        
    }
    public String getSelectButtonText(){
        String type ="";
        if(r1.isSelected()==true){ type=r1.getText(); }
        else if(r2.isSelected()==true){ type=r2.getText(); }
        else if(r3.isSelected()==true){ type=r3.getText(); }
        else if(r4.isSelected()==true){ type=r4.getText(); }
        else if(r5.isSelected()==true){ type=r5.getText(); }
        else if(r6.isSelected()==true){ type=r6.getText(); }
        else if(r7.isSelected()==true){ type=r7.getText(); }
        else{ type= r1.getText();  }
        
        return type;
    }
    public void updateDB(int id){
        Connection co = null;
        PreparedStatement ps= null;
        ResultSet rs= null;
        
        
        String type = this.getSelectButtonText();
        
        try{
            JsonSimple js = new JsonSimple();
            co = myconnection.getConnection(js);
            
            String firstcourse ="";
            String secondcourse ="";
            String thirdcourse ="";
            String seafood ="";
            String other ="";
            String drink="";
            String dessert ="";
            
            if(r1.isSelected()){firstcourse= text2.getText();}
            else if(r2.isSelected()){secondcourse= text2.getText();} 
            else if(r3.isSelected()){thirdcourse= text2.getText();}
            else if(r7.isSelected()){seafood= text2.getText();} 
            else if(r4.isSelected()){dessert= text2.getText();} 
            else if(r5.isSelected()){drink= text2.getText();} 
            else if(r6.isSelected()){other= text2.getText();} 
            
            String dateTime = this.getDateTime();
            Double[] TDC = myQ.getTipDiscountCouponAtSubtable(tableID, Integer.parseInt(text7.getText()));
            
            String query = "INSERT `R`.`order` SET `tip`='"+TDC[0]+"', `discount`='"+TDC[1]+"', `coupon`='"+TDC[2]+"', `name`='"+text1.getText()+"', `firstCourse`='"+firstcourse+"', `secondCourse`='"+secondcourse+"', `thirdCourse`='"+thirdcourse+"', `seafood`='"+seafood+"', `dessert`='"+dessert+"', `drink`='"+drink+"', `other`='"+other+"', `price`='"+text3.getText()+"', `quantity`='"+text4.getText()+"', `position`='"+text6.getText()+"', `note`='"+text5.getText()+"', `type`='"+type+"', `tid`='"+tableID+"', `aid`='"+userID+"', `subtable`='"+text7.getText()+"', `time_in`='"+dateTime+"'"; 
            
            myQ.insertLog("New Custom Ordered: "+text4.getText()+"x "+text1.getText()+" ("+type+ ") $"+Double.parseDouble(text3.getText())*Integer.parseInt(text4.getText())+" [Seat #"+text6.getText()+"] [Check #"+text7.getText()+"] [Notes: "+text5.getText()+"]",tableID ,userID );
            ps = co.prepareStatement(query);
            ps.executeUpdate();
            isUpdated=true;
            
        }catch(Exception e){
            System.out.println("error occur when update:"+e);
        }finally{
         
            if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) { /* ignored */}
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (co != null) {
                try {
                    co.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        
    }//end of editselect
    
    public String getDateTime(){
        theTime = Calendar.getInstance();
        NumberFormat myFormat = new DecimalFormat("#00");
        int a = theTime.get(Calendar.YEAR);
        int b = theTime.get(Calendar.MONTH)+1;
        String a1 =myFormat.format(b);
        int c = theTime.get(Calendar.DATE);
        String a2=myFormat.format(c);
        int d = theTime.get(Calendar.HOUR_OF_DAY);
        String a3=myFormat.format(d);
        int e = theTime.get(Calendar.MINUTE);
        String a4=myFormat.format(e);
        int f = theTime.get(Calendar.SECOND);
        String a5=myFormat.format(f);
        
        String dateTime= a+"-"+a1+"-"+a2+" "+a3+"-"+a4+"-"+a5;
        
        return dateTime;
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        text1 = new javax.swing.JTextField();
        text2 = new javax.swing.JTextField();
        text5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        r1 = new javax.swing.JRadioButton();
        r2 = new javax.swing.JRadioButton();
        r3 = new javax.swing.JRadioButton();
        r4 = new javax.swing.JRadioButton();
        r5 = new javax.swing.JRadioButton();
        r6 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        text3 = new javax.swing.JFormattedTextField();
        text4 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        text6 = new javax.swing.JFormattedTextField();
        text7 = new javax.swing.JFormattedTextField();
        r7 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("New Custom Order");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 410, 56));

        text1.setFont(new java.awt.Font("SimSun", 0, 24)); // NOI18N
        text1.setText("Order");
        text1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                text1MouseClicked(evt);
            }
        });
        text1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text1ActionPerformed(evt);
            }
        });
        text1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                text1FocusGained(evt);
            }
        });
        jPanel1.add(text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 170, 50));

        text2.setFont(new java.awt.Font("SimSun", 0, 24)); // NOI18N
        text2.setText("Order");
        text2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                text2FocusGained(evt);
            }
        });
        jPanel1.add(text2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 170, 50));

        text5.setFont(new java.awt.Font("SimSun", 1, 18)); // NOI18N
        jPanel1.add(text5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 720, 50));

        jButton1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jButton1.setText("save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 150, 90));

        jButton2.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jButton2.setText("cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 140, 90));

        r1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        r1.setForeground(new java.awt.Color(255, 153, 153));
        r1.setSelected(true);
        r1.setText("firstCourse");
        jPanel1.add(r1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        r2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        r2.setForeground(new java.awt.Color(255, 153, 153));
        r2.setText("secondCourse");
        jPanel1.add(r2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, -1, -1));

        r3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        r3.setForeground(new java.awt.Color(255, 153, 153));
        r3.setText("thirdCourse");
        jPanel1.add(r3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        r4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        r4.setForeground(new java.awt.Color(255, 153, 153));
        r4.setText("dessert");
        jPanel1.add(r4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, -1, -1));

        r5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        r5.setForeground(new java.awt.Color(255, 153, 153));
        r5.setText("drink");
        jPanel1.add(r5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 200, -1, -1));

        r6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        r6.setForeground(new java.awt.Color(255, 153, 153));
        r6.setText("other");
        jPanel1.add(r6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, -1, -1));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 153));
        jLabel2.setText("item name on the check");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 170, 20));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 153));
        jLabel3.setText("nick name for kitchen");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 170, 20));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 153));
        jLabel4.setText("$ price for one item");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 140, 20));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 153));
        jLabel5.setText("quantity");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 60, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 153));
        jLabel6.setText("note");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 40, 20));

        text3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        text3.setText("0.00");
        text3.setToolTipText("0.00");
        text3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        text3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text3ActionPerformed(evt);
            }
        });
        text3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                text3FocusGained(evt);
            }
        });
        jPanel1.add(text3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 130, 50));

        text4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        text4.setText("1");
        text4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        text4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                text4FocusGained(evt);
            }
        });
        jPanel1.add(text4, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 90, 50));

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 153, 153));
        jLabel7.setText("check#");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 50, -1));

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 153, 153));
        jLabel8.setText("seat");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 40, -1));

        text6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        text6.setText("0");
        text6.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        text6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                text6FocusGained(evt);
            }
        });
        jPanel1.add(text6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, 80, 50));

        text7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        text7.setText("1");
        text7.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        text7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text7ActionPerformed(evt);
            }
        });
        text7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                text7FocusGained(evt);
            }
        });
        jPanel1.add(text7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 80, 50));

        r7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        r7.setForeground(new java.awt.Color(255, 153, 153));
        r7.setText("seafood");
        jPanel1.add(r7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 80, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(text1.getText().equals("")){ 
            text1.setText("New Order");
                
        }
        if(text2.getText().equals("")){
            text2.setText("New Order");
        }
        if(text3.getText().equals("")){
            text3.setText("0.00");
        }
        if(text4.getText().equals("") || text4.getText().equals("0")){
            text4.setText("1");
        }
        if(text6.getText().equals("")){
            text6.setText("0");
        }
        if(text7.getText().equals("") || text7.getText().equals("0") ){
            text7.setText("1");
        }
        
        this.updateDB(id);
        this.setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void text3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text3ActionPerformed

    private void text1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text1ActionPerformed

    private void text1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text1FocusGained
        
    }//GEN-LAST:event_text1FocusGained

    private void text2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text2FocusGained
        text2.setText("");
    }//GEN-LAST:event_text2FocusGained

    private void text3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text3FocusGained
        text3.setText("");
    }//GEN-LAST:event_text3FocusGained

    private void text1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_text1MouseClicked
        text1.setText("");
    }//GEN-LAST:event_text1MouseClicked

    private void text4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text4FocusGained
        text4.setText("");    }//GEN-LAST:event_text4FocusGained

    private void text6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text6FocusGained
        text6.setText(""); 
    }//GEN-LAST:event_text6FocusGained

    private void text7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text7FocusGained
        text7.setText(""); 
    }//GEN-LAST:event_text7FocusGained

    private void text7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text7ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r2;
    private javax.swing.JRadioButton r3;
    private javax.swing.JRadioButton r4;
    private javax.swing.JRadioButton r5;
    private javax.swing.JRadioButton r6;
    private javax.swing.JRadioButton r7;
    private javax.swing.JTextField text1;
    private javax.swing.JTextField text2;
    private javax.swing.JFormattedTextField text3;
    private javax.swing.JFormattedTextField text4;
    private javax.swing.JTextField text5;
    private javax.swing.JFormattedTextField text6;
    private javax.swing.JFormattedTextField text7;
    // End of variables declaration//GEN-END:variables
    private MyConnection myconnection =  new MyConnection();
    private int id =0;
    private Calendar theTime;
    int tableID;
    int userID;
    private JsonSimple js = new JsonSimple();
    private MyQuery myQ = new MyQuery(js);
    private boolean isUpdated = false;
}
