package com.vinpos.pos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinson
 */
public class EDITDialog extends javax.swing.JDialog {

    /**
     * Creates new form EDITDialog
     */
    
    public EDITDialog(java.awt.Frame parent, boolean modal, MyQuery q) {
        super(parent, modal);
        initComponents();
        myQ = q;
        this.groupUpButtons();
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
    }
    public boolean run(int oid, int tid, int aid){
        id=oid;
        tableID=tid;
        userID=aid;
        this.selectDB(oid);
        this.setVisible(true);
        return update;
        
    }
//    public void selectDB2(int id){
//        Connection co = null;
//        PreparedStatement ps= null;
//        ResultSet rs= null;
//        
//        try{
//            co = myconnection.getConnection(js);
//            String query = "Select * FROM R.order O WHERE O.oid=?"; 
//            ps = co.prepareStatement(query);
//            ps.setInt(1,id);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                text1.setText(rs.getString("name"));
//                String type = rs.getString("type");
//                type_=type;
//                text2.setText(rs.getString(type));
//                text3.setText(Double.toString(rs.getDouble("price")));
//                text4.setText(Integer.toString(rs.getInt("quantity")));
//                //text5.setText(rs.getString("note"));
//                text7.setText(""+rs.getInt("subtable"));
//                
//                if(rs.getInt("position")!=0){
//                        text6.setText(Integer.toString(rs.getInt("position")));
//                } 
//                
//                if(type.equals("firstCourse")){
//                    r1.setSelected(true);
//                }else if(type.equals("secondCourse")){
//                    r2.setSelected(true);
//                }else if(type.equals("thirdCourse")){
//                    r3.setSelected(true);
//                }else if(type.equals("dessert")){
//                    r4.setSelected(true);
//                }else if(type.equals("drink")){
//                    r5.setSelected(true);
//                }else if(type.equals("other")){
//                    r6.setSelected(true);
//                }
//                else if(type.equals("seafood")){
//                    r7.setSelected(true);
//                }
//            }
//            
//        }catch(Exception e){
//            System.out.println("error at jd romoveOrderWithOID():"+e);
//        }finally{
//         
//            if (rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) { /* ignored */}
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) { /* ignored */}
//            }
//            if (co != null) {
//                try {
//                    co.close();
//                } catch (SQLException e) { /* ignored */}
//            }
//        }
//    }
    
    public void selectDB(int oid){
        
        Hashtable<String,String> data = myQ.getSinlgeOrderDetailWithOid(oid);


        text1.setText(data.get("name"));
        String type = data.get("type");
        text2.setText(data.get(type));
        text3.setText(data.get("price"));
        text4.setText(data.get("quantity"));
        text7.setText(""+data.get("subtable"));
        if(!data.get("position").equals("0")){
            text6.setText(data.get("position"));
        }else{
            text6.setText(""); // this is for updating the old text no matter what
        }

        if(type.equals("firstCourse")){
            r1.setSelected(true);
        }else if(type.equals("secondCourse")){
            r2.setSelected(true);
        }else if(type.equals("thirdCourse")){
            r3.setSelected(true);
        }else if(type.equals("dessert")){
            r4.setSelected(true);
        }else if(type.equals("drink")){
            r5.setSelected(true);
        }else if(type.equals("other")){
            r6.setSelected(true);
        }
        else if(type.equals("seafood")){
            r7.setSelected(true);
        }
            
            
      
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
        
        return type;
    }
//    public void updateDB2(int id){
//        Connection co = null;
//        PreparedStatement ps= null;
//        ResultSet rs= null;
//        
//        
//        String type = this.getSelectButtonText();
//        
//        try{
//            co = myconnection.getConnection(js);
//            
//            String s =text6.getText();
//            int seat;
//            if(s.equals("")){
//                seat=0;
//            }else{
//                seat=Integer.valueOf(text6.getText());
//            }    
//            
//            String first="";
//            String second="";
//            String third="";
//            String seafood="";
//            String dessert="";
//            String drink="";
//            String other="";
//            if(type.equals("firstCourse")){first=text2.getText();}   
//            else if(type.equals("secondCourse")){second=text2.getText();}  
//            else if(type.equals("thirdCourse")){third=text2.getText();} 
//            else if(type.equals("seafood")){seafood=text2.getText();} 
//            else if(type.equals("dessert")){dessert=text2.getText();} 
//            else if(type.equals("drink")){drink=text2.getText();} 
//            else if(type.equals("other")){other=text2.getText();} 
//            
//            Double[] TDC = myQ.getTipDiscountCouponAtSubtable(tableID, Integer.parseInt(text7.getText()));
//            
//            String query = "UPDATE `R`.`order` SET `tip`='"+TDC[0]+"', `discount`='"+TDC[1]+"', `coupon`='"+TDC[2]+"', `name`='"+text1.getText()+"', `firstCourse`='"+first+"', `secondCourse`='"+second+"', `thirdCourse`='"+third+"', `seafood`='"+seafood+"', `dessert`='"+dessert+"', `drink`='"+drink+"', `other`='"+other+"', `price`='"+Double.parseDouble(text3.getText())+"', `quantity`='"+Integer.valueOf(text4.getText())+"', `position`='"+seat+"', `subtable`='"+text7.getText()+"', `type`='"+type+"'  WHERE  `oid` = '"+id+"'"; 
//            
//              
//           
//            ps = co.prepareStatement(query);
//            ps.executeUpdate();
//     
//            
//        }catch(Exception e){
//            System.out.println("error occur when update:"+e);
//        }finally{
//         
//            if (rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) { /* ignored */}
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException e) { /* ignored */}
//            }
//            if (co != null) {
//                try {
//                    co.close();
//                } catch (SQLException e) { /* ignored */}
//            }
//        }
//    }//end of editselect
    
    public void updateDB(int oid){
        Hashtable<String, String> data= new Hashtable<String, String>();
        data.put("name", text1.getText());
        data.put("type", getSelectButtonText());
        data.put(getSelectButtonText(), text2.getText());
        data.put("price", text3.getText());
        data.put("quantity", text4.getText());
        data.put("subtable", text7.getText());
        data.put("position", text6.getText());
        myQ.updateSingleOrderWithTidOidHashData(tableID, oid, data);
     
         
    }//end of editselect
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
        text3 = new javax.swing.JFormattedTextField();
        text4 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        text6 = new javax.swing.JTextField();
        text7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        r7 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("EDIT PANEL");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 170, 56));

        text1.setFont(new java.awt.Font("SimSun", 0, 24)); // NOI18N
        jPanel1.add(text1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 310, 50));

        text2.setFont(new java.awt.Font("SimSun", 0, 24)); // NOI18N
        jPanel1.add(text2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 170, 50));

        jButton1.setFont(new java.awt.Font("Lithos Pro", 1, 36)); // NOI18N
        jButton1.setText("save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 170, 90));

        jButton2.setFont(new java.awt.Font("Lithos Pro", 1, 36)); // NOI18N
        jButton2.setText("cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 190, 90));

        r1.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        r1.setText("firstCourse");
        jPanel1.add(r1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 230, 50));

        r2.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        r2.setText("secondCourse");
        jPanel1.add(r2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 230, 50));

        r3.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        r3.setText("thirdCourse");
        jPanel1.add(r3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 230, 50));

        r4.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        r4.setText("dessert");
        jPanel1.add(r4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 270, 240, 50));

        r5.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        r5.setText("drink");
        jPanel1.add(r5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 210, 50));

        r6.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        r6.setText("other");
        jPanel1.add(r6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, 200, 50));

        jLabel2.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        jLabel2.setText("Name Show to Customer");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 340, 40));

        jLabel3.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        jLabel3.setText("Name Print to Kitchen");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 340, 40));

        jLabel4.setFont(new java.awt.Font("Lithos Pro", 1, 18)); // NOI18N
        jLabel4.setText("$ price for one item");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 210, 20));

        jLabel5.setFont(new java.awt.Font("Lithos Pro", 1, 18)); // NOI18N
        jLabel5.setText("quantity#");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 110, -1));

        text3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        text3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jPanel1.add(text3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 110, 50));

        text4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        text4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jPanel1.add(text4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 60, 50));

        jLabel7.setFont(new java.awt.Font("Lithos Pro", 1, 18)); // NOI18N
        jLabel7.setText("seat#");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 90, -1));

        text6.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jPanel1.add(text6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 60, 50));
        jPanel1.add(text7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 60, 50));

        jLabel8.setFont(new java.awt.Font("Lithos Pro", 1, 18)); // NOI18N
        jLabel8.setText("Seperate Check#");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 160, -1));

        r7.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        r7.setText("seafood");
        jPanel1.add(r7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 220, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.updateDB(id);
        update=true;
        this.setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JTextField text6;
    private javax.swing.JTextField text7;
    // End of variables declaration//GEN-END:variables

    private int id =0;
    int tableID;
    int userID;
    boolean update = false;
    private MyQuery myQ;
}
