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
public class Login extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    private MyQuery myQ;
    private Integer enteredpassword;
    private String[] s;
    private String returnName;
    private String returnType;
    private String returnAID;
    private boolean in;
    public Login(java.awt.Frame parent, boolean modal, MyQuery myQ_) {
        super(parent, modal);
        myQ=myQ_;
        initComponents();
        label.setText("");
        enteredpassword =0;
        s = new String[3];
        returnName = "";
        returnType = "";
        returnAID = "";
        in =false;
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        
    }
    public boolean login(){
       
        boolean found = false;
        if(enteredpassword!=0){
            s=myQ.login(enteredpassword);
            if(!s[0].equals("not found") && !s[1].equals("not found") ){
                found = true;
            }
        }
        return found;
    }
    public boolean isLogin(){
        label.setText("");
        enteredpassword =0;
        s = new String[3];
        returnName = "";
        returnType = "";
        returnAID = "";
        in =false;
        this.setVisible(true);
        return in;
    }
    
    public int getID(){
        return Integer.valueOf(returnAID);
    }
    
    public String getName(){
        return returnName;
    }
    public String getType_(){
        return returnType;
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
        label = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        B8 = new javax.swing.JButton();
        B5 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        B0 = new javax.swing.JButton();
        B9 = new javax.swing.JButton();
        B6 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        login = new javax.swing.JButton();
        B7 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        B1 = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        label2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        creat.setBackground(new java.awt.Color(0, 0, 0));
        creat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label.setFont(new java.awt.Font("Lithos Pro", 0, 70)); // NOI18N
        label.setForeground(new java.awt.Color(255, 255, 0));
        label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label.setText("*****");
        creat.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 430, -1));

        jButton13.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        jButton13.setText("Create A New Login Name");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        creat.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 530, 380, 73));

        B8.setBackground(java.awt.Color.white);
        B8.setFont(new java.awt.Font("Mona Lisa Solid ITC TT", 1, 70)); // NOI18N
        B8.setText("8");
        B8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                B8MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B8MouseExited(evt);
            }
        });
        creat.add(B8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 110, 100));

        B5.setBackground(java.awt.Color.white);
        B5.setFont(new java.awt.Font("Mona Lisa Solid ITC TT", 1, 70)); // NOI18N
        B5.setText("5");
        B5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                B5MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B5MouseExited(evt);
            }
        });
        creat.add(B5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 110, 100));

        B3.setBackground(java.awt.Color.white);
        B3.setFont(new java.awt.Font("Mona Lisa Solid ITC TT", 1, 70)); // NOI18N
        B3.setText("3");
        B3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                B3MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B3MouseExited(evt);
            }
        });
        creat.add(B3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 110, 100));

        B0.setBackground(java.awt.Color.white);
        B0.setFont(new java.awt.Font("Mona Lisa Solid ITC TT", 1, 70)); // NOI18N
        B0.setText("0");
        B0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B0MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                B0MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B0MouseExited(evt);
            }
        });
        creat.add(B0, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 110, 100));

        B9.setBackground(java.awt.Color.white);
        B9.setFont(new java.awt.Font("Mona Lisa Solid ITC TT", 1, 70)); // NOI18N
        B9.setText("9");
        B9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B9MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                B9MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B9MouseExited(evt);
            }
        });
        creat.add(B9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 110, 100));

        B6.setBackground(java.awt.Color.white);
        B6.setFont(new java.awt.Font("Mona Lisa Solid ITC TT", 1, 70)); // NOI18N
        B6.setText("6");
        B6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                B6MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B6MouseExited(evt);
            }
        });
        creat.add(B6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 190, 110, 100));

        B2.setBackground(java.awt.Color.white);
        B2.setFont(new java.awt.Font("Mona Lisa Solid ITC TT", 1, 70)); // NOI18N
        B2.setText("2");
        B2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                B2MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B2MouseExited(evt);
            }
        });
        creat.add(B2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 110, 100));

        login.setBackground(java.awt.Color.cyan);
        login.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        login.setText("login");
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loginMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                loginMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginMouseExited(evt);
            }
        });
        creat.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 110, 100));

        B7.setBackground(java.awt.Color.white);
        B7.setFont(new java.awt.Font("Mona Lisa Solid ITC TT", 1, 70)); // NOI18N
        B7.setText("7");
        B7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                B7MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B7MouseExited(evt);
            }
        });
        creat.add(B7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 110, 100));

        B4.setBackground(java.awt.Color.white);
        B4.setFont(new java.awt.Font("Mona Lisa Solid ITC TT", 1, 70)); // NOI18N
        B4.setText("4");
        B4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                B4MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B4MouseExited(evt);
            }
        });
        creat.add(B4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 110, 100));

        B1.setBackground(java.awt.Color.white);
        B1.setFont(new java.awt.Font("Mona Lisa Solid ITC TT", 1, 70)); // NOI18N
        B1.setText("1");
        B1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                B1MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                B1MouseExited(evt);
            }
        });
        creat.add(B1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 110, 100));

        clear.setBackground(new java.awt.Color(255, 255, 255));
        clear.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        clear.setText("Clear");
        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clearMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clearMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clearMouseExited(evt);
            }
        });
        creat.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 110, 100));

        label2.setFont(new java.awt.Font("Lithos Pro", 1, 24)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 255, 0));
        creat.add(label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 330, 70));

        jPanel1.add(creat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 580, 620));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B7MouseExited
        B7.setBackground(Color.white);
    }//GEN-LAST:event_B7MouseExited

    private void B7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B7MousePressed
        B7.setBackground(Color.green);
        enteredpassword=enteredpassword*10+7;
        this.setLabel();
    }//GEN-LAST:event_B7MousePressed

    private void B7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B7MouseReleased
        B7.setBackground(Color.white);
    }//GEN-LAST:event_B7MouseReleased

    private void B8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B8MousePressed
        B8.setBackground(Color.green);
        enteredpassword=enteredpassword*10+8;
        this.setLabel();
    }//GEN-LAST:event_B8MousePressed

    private void B9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B9MousePressed
        B9.setBackground(Color.green);
        enteredpassword=enteredpassword*10+9;
        this.setLabel();
    }//GEN-LAST:event_B9MousePressed

    private void B4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B4MousePressed
        B4.setBackground(Color.green);
        enteredpassword=enteredpassword*10+4;
        this.setLabel();
    }//GEN-LAST:event_B4MousePressed

    private void B5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B5MousePressed
        B5.setBackground(Color.green);
        enteredpassword=enteredpassword*10+5;
        this.setLabel();
    }//GEN-LAST:event_B5MousePressed

    private void B6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B6MousePressed
        B6.setBackground(Color.green);
        enteredpassword=enteredpassword*10+6;
        this.setLabel();
    }//GEN-LAST:event_B6MousePressed

    private void B1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B1MousePressed
        B1.setBackground(Color.green);
        enteredpassword=enteredpassword*10+1;
        this.setLabel();
    }//GEN-LAST:event_B1MousePressed

    private void B3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B3MousePressed
        B3.setBackground(Color.green);
        enteredpassword=enteredpassword*10+3;
        this.setLabel();
    }//GEN-LAST:event_B3MousePressed

    private void B2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B2MousePressed
        B2.setBackground(Color.green);
        enteredpassword=enteredpassword*10+2;
        this.setLabel();
    }//GEN-LAST:event_B2MousePressed

    private void B0MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B0MousePressed
        B0.setBackground(Color.green);
        enteredpassword=enteredpassword*10+0;
        this.setLabel();
    }//GEN-LAST:event_B0MousePressed

    private void B8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B8MouseExited
        B8.setBackground(Color.white);
    }//GEN-LAST:event_B8MouseExited

    private void B9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B9MouseExited
        B9.setBackground(Color.white);
    }//GEN-LAST:event_B9MouseExited

    private void B4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B4MouseExited
        B4.setBackground(Color.white);
    }//GEN-LAST:event_B4MouseExited

    private void B5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B5MouseExited
        B5.setBackground(Color.white);
    }//GEN-LAST:event_B5MouseExited

    private void B6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B6MouseExited
        B6.setBackground(Color.white);
    }//GEN-LAST:event_B6MouseExited

    private void B1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B1MouseExited
        B1.setBackground(Color.white);
    }//GEN-LAST:event_B1MouseExited

    private void B2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B2MouseExited
        B2.setBackground(Color.white);
    }//GEN-LAST:event_B2MouseExited

    private void B3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B3MouseExited
        B1.setBackground(Color.white);
    }//GEN-LAST:event_B3MouseExited

    private void B0MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B0MouseExited
        B0.setBackground(Color.white);
    }//GEN-LAST:event_B0MouseExited

    private void B8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B8MouseReleased
        B8.setBackground(Color.white);
    }//GEN-LAST:event_B8MouseReleased

    private void B9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B9MouseReleased
        B9.setBackground(Color.white);
    }//GEN-LAST:event_B9MouseReleased

    private void B4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B4MouseReleased
        B4.setBackground(Color.white);
    }//GEN-LAST:event_B4MouseReleased

    private void B5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B5MouseReleased
        B5.setBackground(Color.white);
    }//GEN-LAST:event_B5MouseReleased

    private void B6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B6MouseReleased
        B6.setBackground(Color.white);
    }//GEN-LAST:event_B6MouseReleased

    private void B1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B1MouseReleased
        B1.setBackground(Color.white);
    }//GEN-LAST:event_B1MouseReleased

    private void B2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B2MouseReleased
        B2.setBackground(Color.white);
    }//GEN-LAST:event_B2MouseReleased

    private void B3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B3MouseReleased
        B3.setBackground(Color.white);
    }//GEN-LAST:event_B3MouseReleased

    private void B0MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B0MouseReleased
        B0.setBackground(Color.white);
    }//GEN-LAST:event_B0MouseReleased

    private void clearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseExited
        clear.setBackground(Color.white);
    }//GEN-LAST:event_clearMouseExited

    private void clearMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMouseReleased
        clear.setBackground(Color.white);
    }//GEN-LAST:event_clearMouseReleased

    private void loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseExited
        login.setBackground(Color.cyan);
    }//GEN-LAST:event_loginMouseExited

    private void loginMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseReleased
        login.setBackground(Color.cyan);
    }//GEN-LAST:event_loginMouseReleased

    private void loginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMousePressed
        login.setBackground(Color.green);
        if(this.login()){
            in = true;
            returnName = s[0];
            returnType = s[1];
            returnAID  = s[2];
            this.setVisible(false);
        }
        else{
            label2.setText("Incorrect");
            label.setText("");
            enteredpassword=0;
        }
    }//GEN-LAST:event_loginMousePressed

    private void clearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearMousePressed
        clear.setBackground(Color.green);
        enteredpassword=0;
        label.setText("");
    }//GEN-LAST:event_clearMousePressed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        CreatAccount ca = new CreatAccount(new java.awt.Frame(), true, myQ);
        
    }//GEN-LAST:event_jButton13ActionPerformed
    private void setLabel(){
        label2.setText("");
        int num = enteredpassword.toString().length();
        String s ="";
        for(int i=0; i<num; i++){
            s+="*";
        }
        label.setText(s);
    }
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B0;
    private javax.swing.JButton B1;
    private javax.swing.JButton B2;
    private javax.swing.JButton B3;
    private javax.swing.JButton B4;
    private javax.swing.JButton B5;
    private javax.swing.JButton B6;
    private javax.swing.JButton B7;
    private javax.swing.JButton B8;
    private javax.swing.JButton B9;
    private javax.swing.JButton clear;
    private javax.swing.JPanel creat;
    private javax.swing.JButton jButton13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label2;
    private javax.swing.JButton login;
    // End of variables declaration//GEN-END:variables
}
