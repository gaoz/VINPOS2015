package com.vinpos.pos;


import java.awt.Color;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinson
 */
public class CreatAccount extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    private MyQuery myQ;
    private Integer enteredpassword =0;
    private String[] s = new String[3];
    private String type = "user";
    
    
    public CreatAccount(java.awt.Frame parent, boolean modal, MyQuery myQ_) {
        super(parent, modal);
        myQ=myQ_;
        initComponents();
        
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
    }
   
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        creat = new javax.swing.JPanel();
        create = new javax.swing.JButton();
        cancle = new javax.swing.JButton();
        Username = new javax.swing.JTextField();
        P1 = new javax.swing.JPasswordField();
        P2 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        creat.setBackground(new java.awt.Color(0, 0, 0));
        creat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        create.setBackground(java.awt.Color.cyan);
        create.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        create.setText("Create");
        create.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                createMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                createMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createMouseExited(evt);
            }
        });
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        creat.add(create, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 120, 80));

        cancle.setBackground(new java.awt.Color(255, 255, 255));
        cancle.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        cancle.setText("Cancle");
        cancle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancleMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancleMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancleMouseExited(evt);
            }
        });
        cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleActionPerformed(evt);
            }
        });
        creat.add(cancle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 120, 80));

        Username.setFont(new java.awt.Font("Lithos Pro", 0, 24)); // NOI18N
        Username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                UsernameMousePressed(evt);
            }
        });
        Username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UsernameKeyPressed(evt);
            }
        });
        creat.add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 260, 50));

        P1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        P1.setText("jPasswordField1");
        P1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                P1MousePressed(evt);
            }
        });
        P1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                P1ActionPerformed(evt);
            }
        });
        creat.add(P1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 260, 50));

        P2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        P2.setText("jPasswordField2");
        P2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                P2MousePressed(evt);
            }
        });
        creat.add(P2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 260, 50));

        jLabel1.setFont(new java.awt.Font("Lithos Pro", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Re-enter");
        creat.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 110, 50));

        jLabel2.setFont(new java.awt.Font("Lithos Pro", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name");
        creat.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 90, 50));

        jLabel3.setText("jLabel3");
        creat.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jLabel4.setFont(new java.awt.Font("Lithos Pro", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Numbers Only For password");
        creat.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 400, 40));

        jLabel5.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        creat.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 560, 60));

        jLabel6.setFont(new java.awt.Font("Lithos Pro", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Password");
        creat.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 110, 50));

        jLabel7.setFont(new java.awt.Font("Lithos Pro", 0, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Create New Account");
        creat.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 440, 70));

        jPanel1.add(creat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 580, 470));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 620, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancleMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleMouseExited
        cancle.setBackground(Color.white);
    }//GEN-LAST:event_cancleMouseExited

    private void cancleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleMouseReleased
        cancle.setBackground(Color.white);
    }//GEN-LAST:event_cancleMouseReleased

    private void cancleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancleMousePressed
        cancle.setBackground(Color.green);
        enteredpassword=0;
        
    }//GEN-LAST:event_cancleMousePressed

    private void UsernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsernameKeyPressed
        if(Username.getText().length()>=8)
        {
            Username.setText(Username.getText().substring(0, 7));
        }
    }//GEN-LAST:event_UsernameKeyPressed

    private void createMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createMouseExited
        create.setBackground(Color.cyan);
    }//GEN-LAST:event_createMouseExited

    private void createMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createMouseReleased
        create.setBackground(Color.cyan);
    }//GEN-LAST:event_createMouseReleased

    private void createMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createMousePressed
        create.setBackground(Color.green);
        
    }//GEN-LAST:event_createMousePressed

    private void P1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_P1ActionPerformed
        
    }//GEN-LAST:event_P1ActionPerformed

    private void P1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P1MousePressed
        P1.setText("");
    }//GEN-LAST:event_P1MousePressed

    private void P2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_P2MousePressed
        P2.setText("");
    }//GEN-LAST:event_P2MousePressed

    private void UsernameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsernameMousePressed
        Username.setText("");
    }//GEN-LAST:event_UsernameMousePressed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        char[] pa1 = P1.getPassword();
        char[] pa2 = P2.getPassword();
        String p1 = new String(pa1);
        String p2 = new String(pa2);
        if(!p1.equals("")&& !p2.equals("")&& !Username.getText().equals("")){
            System.out.println(p1+" "+p2);
            if(p1.equals(p2)){
                try{
                    if(!myQ.isPasswordAlreadyUsed(Integer.valueOf(p1))){
                        myQ.insertNewAccount(Username.getText(), p1, type);
                        this.setVisible(false);
                    }else{
                        jLabel5.setText("Password is too simple, try other one");
                    }
                }catch(Exception e){
                    jLabel5.setText("Your Password is not numbers");
                }
                
            }else{
                jLabel5.setText("Password not matching");
            }
            
                
        }else{
            jLabel5.setText("Username or password is empty!");
        }
    }//GEN-LAST:event_createActionPerformed

    private void cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancleActionPerformed
   
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField P1;
    private javax.swing.JPasswordField P2;
    private javax.swing.JTextField Username;
    private javax.swing.JButton cancle;
    private javax.swing.JPanel creat;
    private javax.swing.JButton create;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
