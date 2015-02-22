package com.vinpos.pos;


import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinson
 */
public class SettingPane extends javax.swing.JDialog {

    /**
     * Creates new form EDITDialog
     */
    
    public SettingPane(java.awt.Frame parent, boolean modal, MyQuery myQ_) {
        super(parent, modal);
        initComponents();
        isEmpty = false;
        myQ=myQ_;
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
    }
    public boolean runAndSetVisible(int tableID_, int userID_){
        tableID=tableID_;
        userID=userID_;
        loadConnectionSettingToPanel();
        this.setVisible(true);
        return isUpdated;
        
        
    }
    public void loadConnectionSettingToPanel(){
        JsonSimple js = new JsonSimple();
        ArrayList<String> setting =js.getAllSetting();
        if(!ip.getText().equals("")){ 
            ip.setText(setting.get(0));       
        } 
        if(!port.getText().equals("")){
            port.setText(setting.get(1));
        }
        if(!database.getText().equals("")){
            database.setText(setting.get(2));
        }
        if(!username.getText().equals("")){
            username.setText(setting.get(3));
        }
        if(!password.getText().equals("")){
            password.setText(setting.get(4));
        }
        isSERVER.setSelected(Boolean.parseBoolean(setting.get(5)));
        
    }
    
    
    
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        ip = new javax.swing.JTextField();
        port = new javax.swing.JTextField();
        database = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        isSERVER = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        preset1 = new javax.swing.JButton();
        preset3 = new javax.swing.JButton();
        save = new javax.swing.JButton();
        cancle = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 153));
        jLabel2.setText("Name");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 153));
        jLabel4.setText("price");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 2147483647));
        setPreferredSize(new java.awt.Dimension(240, 400));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setMaximumSize(new java.awt.Dimension(500, 2147483647));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 350));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        ip.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        ip.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        ip.setText("IP Address");
        ip.setToolTipText("IP Address");
        ip.setMaximumSize(new java.awt.Dimension(300, 50));
        ip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ipMousePressed(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ipMouseEntered(evt);
            }
        });
        ip.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ipFocusGained(evt);
            }
        });
        ip.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ipKeyPressed(evt);
            }
        });
        jPanel2.add(ip);

        port.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        port.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        port.setText("Port");
        port.setToolTipText("Port");
        port.setMaximumSize(new java.awt.Dimension(300, 50));
        port.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                portMousePressed(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                portMouseEntered(evt);
            }
        });
        port.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                portFocusGained(evt);
            }
        });
        port.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                portKeyPressed(evt);
            }
        });
        jPanel2.add(port);

        database.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        database.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        database.setText("database");
        database.setToolTipText("database");
        database.setMaximumSize(new java.awt.Dimension(300, 50));
        database.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                databaseMousePressed(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                databaseMouseEntered(evt);
            }
        });
        database.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                databaseFocusGained(evt);
            }
        });
        database.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                databaseKeyPressed(evt);
            }
        });
        jPanel2.add(database);

        username.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        username.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        username.setText("User Name");
        username.setToolTipText("User Name");
        username.setMaximumSize(new java.awt.Dimension(300, 50));
        username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                usernameMousePressed(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usernameMouseEntered(evt);
            }
        });
        username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameFocusGained(evt);
            }
        });
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
        });
        jPanel2.add(username);

        password.setFont(new java.awt.Font("SimSun", 1, 14)); // NOI18N
        password.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        password.setText("Password");
        password.setToolTipText("Password");
        password.setMaximumSize(new java.awt.Dimension(300, 50));
        password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                passwordMousePressed(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                passwordMouseEntered(evt);
            }
        });
        password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFocusGained(evt);
            }
        });
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });
        jPanel2.add(password);

        isSERVER.setFont(new java.awt.Font("Adobe Garamond Pro", 0, 18)); // NOI18N
        isSERVER.setForeground(new java.awt.Color(255, 255, 255));
        isSERVER.setText("Server");
        isSERVER.setMaximumSize(new java.awt.Dimension(300, 50));
        jPanel2.add(isSERVER);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 190, 190));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        preset1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        preset1.setText("Win7");
        preset1.setMaximumSize(new java.awt.Dimension(90, 29));
        preset1.setMinimumSize(new java.awt.Dimension(90, 29));
        preset1.setPreferredSize(new java.awt.Dimension(80, 30));
        preset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preset1ActionPerformed(evt);
            }
        });
        jPanel4.add(preset1);

        preset3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        preset3.setText("IMAC");
        preset3.setMaximumSize(new java.awt.Dimension(90, 29));
        preset3.setMinimumSize(new java.awt.Dimension(90, 29));
        preset3.setPreferredSize(new java.awt.Dimension(80, 30));
        preset3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preset3ActionPerformed(evt);
            }
        });
        jPanel4.add(preset3);

        save.setBackground(new java.awt.Color(102, 255, 51));
        save.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        save.setText("save");
        save.setMaximumSize(new java.awt.Dimension(90, 29));
        save.setMinimumSize(new java.awt.Dimension(90, 29));
        save.setPreferredSize(new java.awt.Dimension(80, 30));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel4.add(save);

        cancle.setBackground(new java.awt.Color(255, 102, 102));
        cancle.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        cancle.setText("cancel");
        cancle.setMaximumSize(new java.awt.Dimension(90, 29));
        cancle.setMinimumSize(new java.awt.Dimension(90, 29));
        cancle.setPreferredSize(new java.awt.Dimension(80, 30));
        cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleActionPerformed(evt);
            }
        });
        jPanel4.add(cancle);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 90, 130));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Setting Panel");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, -1));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if(ip.getText().equals("")){ 
            ip.setText("127.0.0.1");
                
        }
        if(database.getText().equals("")){
            database.setText("r");
        }
        if(port.getText().equals("")){
            port.setText("3306");
        }
        if(username.getText().equals("")){
            username.setText("root");
        }
        if(password.getText().equals("")){
            password.setText("");
        }
        
        
        ArrayList<Object> nametag =  new ArrayList<Object>();
        nametag.add("ip");
        nametag.add("port");
        nametag.add("database");
        nametag.add("username");
        nametag.add("password");
        nametag.add("isSERVER");
        
        ArrayList<Object> value =  new ArrayList<Object>();
        value.add(ip.getText());
        value.add(port.getText());
        value.add(database.getText());
        value.add(username.getText());
        value.add(password.getText());
        value.add(isSERVER.isSelected()+"");
        
        JsonSimple js = new JsonSimple();
        js.writeJsonFile(nametag, value);
        new Msg("This program is closing inorder to save new data, Please manually open the program again");
        System.exit(0);
        this.setVisible(false);
    }//GEN-LAST:event_saveActionPerformed

    private void cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancleActionPerformed
    
    private void ipMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ipMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ipMouseEntered

    private void ipMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ipMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipMousePressed

    private void ipFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ipFocusGained
        database.setForeground(Color.black);
    }//GEN-LAST:event_ipFocusGained

    private void ipKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ipKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ipKeyPressed

    private void usernameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameMouseEntered

    private void usernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameMousePressed

    private void usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameFocusGained
         database.setForeground(Color.black);
    }//GEN-LAST:event_usernameFocusGained

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameKeyPressed

    private void passwordMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordMouseEntered

    private void passwordMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordMousePressed

    private void passwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFocusGained

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordKeyPressed

    private void portMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_portMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_portMousePressed

    private void portMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_portMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_portMouseEntered

    private void portFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_portFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_portFocusGained

    private void portKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_portKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_portKeyPressed

    private void databaseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databaseMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseMousePressed

    private void databaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_databaseMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseMouseEntered

    private void databaseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_databaseFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseFocusGained

    private void databaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_databaseKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_databaseKeyPressed

    private void preset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preset1ActionPerformed
        
            ip.setText("127.0.0.1");
                
        
       
            database.setText("r");
        
       
            port.setText("3306");
        
        
            username.setText("root");
       
        
            password.setText("password");
            
            this.isSERVER.setSelected(true);
            
            
           
        
    }//GEN-LAST:event_preset1ActionPerformed

    private void preset3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preset3ActionPerformed
         
            ip.setText("192.168.1.198");
                
        
            database.setText("r");
        
       
            port.setText("3306");
        
        
            username.setText("root");
       
        
            password.setText("");
            
            this.isSERVER.setSelected(false);
    }//GEN-LAST:event_preset3ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancle;
    private javax.swing.JTextField database;
    private javax.swing.JTextField ip;
    private javax.swing.JCheckBox isSERVER;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField password;
    private javax.swing.JTextField port;
    private javax.swing.JButton preset1;
    private javax.swing.JButton preset3;
    private javax.swing.JButton save;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables

    private int id =0;
    private Calendar theTime;
    int tableID;
    int userID;
    private MyQuery myQ ;
    private boolean isUpdated = false;
    private boolean isEmpty;
}



 
