package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sd
 */
public class Discount extends java.awt.Dialog {

    /**
     * Creates new form JD1
     */
    public Discount(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();

        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(51, 204, 0));
        jButton1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton1.setText("5% discount");
        jButton1.setBorder(new javax.swing.border.MatteBorder(null));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 249, 70));

        jButton2.setBackground(new java.awt.Color(51, 204, 0));
        jButton2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton2.setText("10% discount");
        jButton2.setBorder(new javax.swing.border.MatteBorder(null));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 249, 70));

        jButton3.setBackground(new java.awt.Color(51, 204, 0));
        jButton3.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton3.setText("15% discount");
        jButton3.setBorder(new javax.swing.border.MatteBorder(null));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 249, 70));

        jButton4.setBackground(new java.awt.Color(51, 204, 0));
        jButton4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton4.setText("20% discount");
        jButton4.setBorder(new javax.swing.border.MatteBorder(null));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 249, 70));

        jButton5.setBackground(new java.awt.Color(51, 204, 0));
        jButton5.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton5.setText("25% discount");
        jButton5.setBorder(new javax.swing.border.MatteBorder(null));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 249, 70));

        jButton10.setBackground(new java.awt.Color(51, 204, 0));
        jButton10.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton10.setText("50% discount");
        jButton10.setBorder(new javax.swing.border.MatteBorder(null));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 249, 70));

        jButton6.setBackground(new java.awt.Color(51, 204, 0));
        jButton6.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton6.setText("30% discount");
        jButton6.setBorder(new javax.swing.border.MatteBorder(null));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 249, 70));

        jButton7.setBackground(new java.awt.Color(51, 204, 0));
        jButton7.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton7.setText("35% discount");
        jButton7.setBorder(new javax.swing.border.MatteBorder(null));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 249, 70));

        jButton8.setBackground(new java.awt.Color(51, 204, 0));
        jButton8.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton8.setText("40% discount");
        jButton8.setBorder(new javax.swing.border.MatteBorder(null));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 249, 70));

        jButton9.setBackground(new java.awt.Color(51, 204, 0));
        jButton9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton9.setText("45% discount");
        jButton9.setBorder(new javax.swing.border.MatteBorder(null));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 249, 70));

        jButton11.setBackground(new java.awt.Color(51, 204, 0));
        jButton11.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton11.setText("55% discount");
        jButton11.setBorder(new javax.swing.border.MatteBorder(null));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 249, 70));

        jButton12.setBackground(new java.awt.Color(51, 204, 0));
        jButton12.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton12.setText("60% discount");
        jButton12.setBorder(new javax.swing.border.MatteBorder(null));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 249, 70));

        jButton13.setBackground(new java.awt.Color(51, 204, 0));
        jButton13.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton13.setText("65% discount");
        jButton13.setBorder(new javax.swing.border.MatteBorder(null));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 249, 70));

        jButton14.setBackground(new java.awt.Color(51, 204, 0));
        jButton14.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton14.setText("70% discount");
        jButton14.setBorder(new javax.swing.border.MatteBorder(null));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 249, 70));

        jButton15.setBackground(new java.awt.Color(51, 204, 0));
        jButton15.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton15.setText("75% discount");
        jButton15.setBorder(new javax.swing.border.MatteBorder(null));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 249, 70));

        jButton16.setBackground(new java.awt.Color(51, 204, 0));
        jButton16.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton16.setText("80% discount");
        jButton16.setBorder(new javax.swing.border.MatteBorder(null));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 249, 70));

        jButton17.setBackground(new java.awt.Color(51, 204, 0));
        jButton17.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton17.setText("85% discount");
        jButton17.setBorder(new javax.swing.border.MatteBorder(null));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 249, 70));

        jButton18.setBackground(new java.awt.Color(51, 204, 0));
        jButton18.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton18.setText("90% discount");
        jButton18.setBorder(new javax.swing.border.MatteBorder(null));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 490, 249, 70));

        jButton19.setBackground(new java.awt.Color(51, 204, 0));
        jButton19.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton19.setText("Cancle discount");
        jButton19.setBorder(new javax.swing.border.MatteBorder(null));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, 249, 70));

        jButton20.setBackground(new java.awt.Color(51, 204, 0));
        jButton20.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton20.setText("FREE");
        jButton20.setBorder(new javax.swing.border.MatteBorder(null));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 630, 249, 70));

        add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog

        setVisible(false);
    }//GEN-LAST:event_closeDialog

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        discount=0.05;
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       discount=0.10;
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        discount=0.15;
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        discount=0.20;
        setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        discount=0.25;
        setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        discount=0.30;
        setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        discount=0.35;
        setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        discount=0.40;
        setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
       discount=0.45;
        setVisible(false);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       discount=0.50;
        setVisible(false);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
       discount=0.55;
        setVisible(false);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
      discount=0.60;
        setVisible(false);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
      discount=0.65;
        setVisible(false);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
      discount=0.70;
        setVisible(false);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
      discount=0.75;
        setVisible(false);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
      discount=0.80;
        setVisible(false);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
      discount=0.85;
        setVisible(false);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
      discount=0.90;
        setVisible(false);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
      discount=0.00;
        setVisible(false);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
      discount=1.00;
        setVisible(false);
    }//GEN-LAST:event_jButton20ActionPerformed
    public Double getDiscount(){
        setVisible(true);
        return discount;
    }
    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
    private Double discount = -1.00;
}
