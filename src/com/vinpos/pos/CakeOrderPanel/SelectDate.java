/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vinpos.pos.CakeOrderPanel;

import com.vinpos.pos.MyQuery;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jj
 */
public class SelectDate extends javax.swing.JDialog {

    /**
     * Creates new form SelectDate
     */
    private String timeString;
    private int wid;
    private MyQuery myQ;
    public SelectDate(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        timeString="";
        wid=0;
        
    }
    public void setWid(int wid_, MyQuery myQ_){
        wid=wid_;
        myQ=myQ_;
    }
    public String[] getData(){
            Calendar cal = dateChooserPanel2.getSelectedDate();
            int datevar = cal.get(Calendar.DATE);
            int monthvar = cal.get(Calendar.MONTH);
            int yearvar = cal.get(Calendar.YEAR);
            int week = cal.get(Calendar.DAY_OF_WEEK);
            String weekString="";
            if(week==1){weekString="SUN";}
            else if(week==2){weekString="MON";}
            else if(week==3){weekString="TUE";}
            else if(week==4){weekString="WEN";}
            else if(week==5){weekString="THU";}
            else if(week==6){weekString="FRI";}
            else if(week==7){weekString="SAT";}
            NumberFormat myFormat = new DecimalFormat("#00");
            String[] lines = new String[12];
            lines[0] =""+weekString;  // week day
            lines[1] =""+myFormat.format(monthvar+1)+"/"+myFormat.format(datevar); //date
            lines[2] = timeString;
            lines[3] =yearvar+"-"+myFormat.format(monthvar+1)+"-"+myFormat.format(datevar);            
            return lines;
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
        dateChooserPanel2 = new datechooser.beans.DateChooserPanel();
        jLabel1 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dateChooserPanel2.setCalendarBackground(new java.awt.Color(255, 102, 102));
        dateChooserPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.LOWERED,
            (java.awt.Color)null,
            (java.awt.Color)null),
        "Select Pick Up Date",
        javax.swing.border.TitledBorder.CENTER,
        javax.swing.border.TitledBorder.ABOVE_TOP,
        (java.awt.Font)null,
        new java.awt.Color(153, 0, 153)));
dateChooserPanel2.setNavigateFont(new java.awt.Font("Serif", java.awt.Font.PLAIN, 24));
dateChooserPanel2.setShowOneMonth(true);
dateChooserPanel2.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
    public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
        dateChooserPanel2OnSelectionChange(evt);
    }
    });
    jPanel1.add(dateChooserPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 600, 441));

    jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("X");
    jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 70, 60));

    exit.setBackground(new java.awt.Color(255, 102, 102));
    exit.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
    exit.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            exitActionPerformed(evt);
        }
    });
    jPanel1.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 70, 60));

    jButton2.setBackground(new java.awt.Color(255, 153, 255));
    jButton2.setFont(new java.awt.Font("Lucida Grande", 0, 48)); // NOI18N
    jButton2.setText("Select Today");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });
    jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 600, 100));

    getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 650));

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dateChooserPanel2OnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserPanel2OnSelectionChange
        SelectTime time = new SelectTime(new javax.swing.JFrame(),true);
        time.setWid(wid, myQ);
        time.setDate((this.getData())[3]);
        time.setVisible(true);
        timeString=time.getTime();   
        this.setVisible(false);
    }//GEN-LAST:event_dateChooserPanel2OnSelectionChange

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
            
            this.setVisible(false);
        
    }//GEN-LAST:event_exitActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Date dt = new Date();
        Calendar c = Calendar.getInstance(); 
        c.setTime(dt); 
        dateChooserPanel2.setSelectedDate(c);
       
    }//GEN-LAST:event_jButton2ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserPanel dateChooserPanel2;
    private javax.swing.JButton exit;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
