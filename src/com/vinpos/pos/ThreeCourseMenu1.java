package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sd
 */
public class ThreeCourseMenu1 extends java.awt.Dialog {

    /**
     * Creates new form Edit
     */
    public ThreeCourseMenu1(java.awt.Frame parent, boolean modal, JD optionJD_) {
        super(parent, modal);
        initComponents();
        
        nameString[0]="";
        nameString[1]="";
        nameString[2]="0";
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        C=optionJD_;
        //Toolkit tk = Toolkit.getDefaultToolkit();
        //Dimension screenSize = tk.getScreenSize();
        //final int WIDTH = screenSize.width;
        //final int HEIGHT = screenSize.height;
        //// Setup the frame accordingly
        //// This is assuming you are extending the JFrame //class
        //this.setLocation((WIDTH/3)+50, 0);

        
        
    }
    

    public String[] getNameString(){
        nameString[0]="";
        nameString[1]="";
        nameString[2]="0";
        this.setVisible(true);
        return nameString;
    }


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton106 = new javax.swing.JButton();
        jButton107 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton66 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton108 = new javax.swing.JButton();

        setLocationRelativeTo(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setMinimumSize(new java.awt.Dimension(750, 850));
        jPanel2.setPreferredSize(new java.awt.Dimension(920, 900));
        jPanel2.setVerifyInputWhenFocusTarget(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Three Course Menu #1");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 450, 80));

        jButton1.setBackground(new java.awt.Color(204, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("EXIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 120, 50));

        jButton35.setBackground(new java.awt.Color(0, 102, 255));
        jButton35.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton35.setText("SouthEast");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 130, 50));

        jButton36.setBackground(new java.awt.Color(0, 102, 255));
        jButton36.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton36.setText("HousePasta");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 130, 50));

        jButton38.setBackground(new java.awt.Color(0, 102, 255));
        jButton38.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton38.setText("ClassicPasta");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 130, 50));

        jButton39.setBackground(new java.awt.Color(0, 102, 255));
        jButton39.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton39.setText("KaoSoi");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 130, 50));

        jButton106.setBackground(new java.awt.Color(0, 102, 255));
        jButton106.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton106.setText("PadThai");
        jButton106.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton106ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton106, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 130, 50));

        jButton107.setBackground(new java.awt.Color(255, 51, 255));
        jButton107.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton107.setText("G.S Pizza");
        jButton107.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton107ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton107, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 600, 130, 50));

        jButton41.setBackground(new java.awt.Color(255, 51, 255));
        jButton41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton41.setText("Angel Pizza");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 130, 50));

        jButton66.setBackground(new java.awt.Color(255, 51, 255));
        jButton66.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton66.setText("NY Pizza");
        jButton66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton66ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton66, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 130, 50));

        jButton42.setBackground(new java.awt.Color(255, 51, 255));
        jButton42.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton42.setText("Lao Pizza");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, 130, 50));

        jButton44.setBackground(new java.awt.Color(255, 51, 255));
        jButton44.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton44.setText("Veggie Pizza");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton44, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 130, 50));

        jButton47.setBackground(new java.awt.Color(255, 51, 255));
        jButton47.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton47.setText("Pepporoni");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton47, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 130, 50));

        jButton48.setBackground(new java.awt.Color(255, 51, 255));
        jButton48.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton48.setText("Cheese Pizza");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton48, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 130, 50));

        jButton108.setBackground(new java.awt.Color(255, 51, 255));
        jButton108.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton108.setText("House Pizza");
        jButton108.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton108ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton108, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 530, 130, 50));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
         nameString[0]="";
        nameString[1]="";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_closeDialog

    private void jButton106ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton106ActionPerformed
        
        int i = C.setName("Veggie","Chicken","");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        if(i!=-1){
            nameString[0]="PadThai"+choice;
            nameString[1]="PadThai"+choice1;
            
        }
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton106ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        
        
        int i = C.setName("Veggie","Chicken","");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        
        if(i!=-1){
            nameString[0]="Khao Soi"+choice;
            nameString[1]="KaoSoi"+choice1;
        }
        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        
        
        int i = C.setName("Veggie","Chicken","");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        
        if(i!=-1){
            nameString[0]="Classic Pasta"+choice;
            nameString[1]="Classic.Pasta"+choice1;
        }
        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        
        
        int i = C.setName("Veggie","Chicken","");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        
        if(i!=-1){
            nameString[0]="House Pasta"+choice;
            nameString[1]="HS.Pasta"+choice1;
        }
        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        
        int i = C.setName("Veggie","Chicken","");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        
        if(i!=-1){
            nameString[0]="Southeast"+choice;
            nameString[1]="South"+choice1;
        }
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        nameString[0]="";
        nameString[1]="";

        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton107ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton107ActionPerformed
        nameString[0]="Garlic Pizza";
        nameString[1]="G.S.Pizza";

        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton107ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        nameString[0]="Angel Pizza";
        nameString[1]="Angel.Pizza";

        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton66ActionPerformed
        nameString[0]="NewYork Pizza";
        nameString[1]="NY.Pizza";

        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton66ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        nameString[0]="Lao Pizza";
        nameString[1]="Lao.Pizza";

        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        nameString[0]="Veggie Pizza";
        nameString[1]="Veg.Pizza";

        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        nameString[0]="Pepporoni Pizza";
        nameString[1]="Pepp.Pizza";
     
        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
       nameString[0]="Cheese Pizza";
        nameString[1]="Cheese.Pizza";
 
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton108ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton108ActionPerformed
        nameString[0]="House Pizza";
        nameString[1]="HS.Pizza";

        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton108ActionPerformed
    
    
    

    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton106;
    private javax.swing.JButton jButton107;
    private javax.swing.JButton jButton108;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton66;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables


    private String[] nameString= new String[3];
    private JD C;
}
