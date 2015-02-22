/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vinpos.pos.CakeOrderPanel;

import com.vinpos.pos.MyQuery;
import java.awt.Color;
import javax.swing.WindowConstants;

/**
 *
 * @author jj
 */
public class SelectPaid extends javax.swing.JDialog {

    /**
     * Creates new form SelectTime
     */
    private int paid;
    private int wid;
    private MyQuery myQ;
    public SelectPaid(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        wid=0;
        paid=-1;
    }
    public void setWid(int wid_, MyQuery myQ_){
        wid=wid_;
        myQ=myQ_;
    }
    public void loadData(){
        Object[] temp = myQ.getWholeCakeOrderWithWid(wid);
        paid =(Integer)temp[16];
        
        if(paid==0){
            lable.setText("Not Paid Yet Selected");
            notPaidBtn.setBackground(Color.green);
             paidBtn.setBackground(Color.white);
        }else if (paid==1){
            lable.setText("Paid Selected");
            paidBtn.setBackground(Color.green);
            notPaidBtn.setBackground(Color.white);
        }
    
             
        
       
        
        
        
        
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
        paidBtn = new javax.swing.JButton();
        notPaidBtn = new javax.swing.JButton();
        lable = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 0, 204));
        jPanel1.setMinimumSize(new java.awt.Dimension(492, 423));
        jPanel1.setSize(new java.awt.Dimension(492, 423));

        paidBtn.setBackground(new java.awt.Color(255, 255, 255));
        paidBtn.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        paidBtn.setText("Paid");
        paidBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidBtnActionPerformed(evt);
            }
        });

        notPaidBtn.setBackground(new java.awt.Color(255, 255, 255));
        notPaidBtn.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        notPaidBtn.setText("Not Paid Yet");
        notPaidBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notPaidBtnActionPerformed(evt);
            }
        });

        lable.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
        lable.setForeground(new java.awt.Color(255, 255, 255));
        lable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lable.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(paidBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(notPaidBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(lable, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paidBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(notPaidBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void paidBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidBtnActionPerformed
        myQ.updatePaidOrderWithWID(wid, 1);
        this.setVisible(false);
        
    }//GEN-LAST:event_paidBtnActionPerformed

    private void notPaidBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notPaidBtnActionPerformed
         myQ.updatePaidOrderWithWID(wid, 0);
        this.setVisible(false);
    }//GEN-LAST:event_notPaidBtnActionPerformed
    public int getPaid(){
        return paid;
    }
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lable;
    private javax.swing.JButton notPaidBtn;
    private javax.swing.JButton paidBtn;
    // End of variables declaration//GEN-END:variables
}