package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sd
 */
public class FourCourseMenu1 extends java.awt.Dialog {

    /**
     * Creates new form Edit
     */
    public FourCourseMenu1(java.awt.Frame parent, boolean modal, JD optionJD_) {
        super(parent, modal);
        initComponents();
       
        
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        C= optionJD_;
        options = new JD_App(new java.awt.Frame(), true);
        //Toolkit tk = Toolkit.getDefaultToolkit();
        //Dimension screenSize = tk.getScreenSize();
        //final int WIDTH = screenSize.width;
        //final int HEIGHT = screenSize.height;
        //// Setup the frame accordingly
        //// This is assuming you are extending the JFrame //class
        //this.setLocation((WIDTH/3)+50, 0);

        
        
    }
    public void setJlabel(String s){
        jLabel1.setText(s);
    }

    public String[] getNameString(){ 
        nameString[0]="";
        nameString[1]="";
        nameString[2]="0";
        nameString[3]="0";
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

        jButton106 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jButton106.setBackground(new java.awt.Color(0, 102, 255));
        jButton106.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton106.setText("PadThai");
        jButton106.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton106ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButton4.setText("Seafood plate");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton46.setBackground(new java.awt.Color(255, 51, 204));
        jButton46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton46.setText("SP crab");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jButton60.setBackground(new java.awt.Color(255, 51, 204));
        jButton60.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton60.setText("2x Quail");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });

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
        jLabel1.setText("4 Course Menu");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 450, 80));

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
        jPanel2.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 130, 50));

        jButton36.setBackground(new java.awt.Color(0, 102, 255));
        jButton36.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton36.setText("HousePasta");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 130, 50));

        jButton38.setBackground(new java.awt.Color(0, 102, 255));
        jButton38.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton38.setText("ClassicPasta");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 130, 50));

        jButton39.setBackground(new java.awt.Color(0, 102, 255));
        jButton39.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jButton39.setText("KaoSoi");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 130, 50));

        jButton45.setBackground(new java.awt.Color(255, 51, 204));
        jButton45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton45.setText("Lobster PadThai");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton45, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, -1, 50));

        jButton37.setBackground(new java.awt.Color(0, 102, 255));
        jButton37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton37.setText("Liuguini");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 130, 50));

        jButton65.setBackground(new java.awt.Color(0, 102, 255));
        jButton65.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton65.setText("Garlic Pasta");
        jButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton65ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton65, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 130, 50));

        jButton40.setBackground(new java.awt.Color(255, 51, 255));
        jButton40.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton40.setText("ClamPasta");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 130, 50));

        jButton43.setBackground(new java.awt.Color(0, 255, 51));
        jButton43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton43.setText("Asian");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 120, 50));

        jButton49.setBackground(new java.awt.Color(0, 255, 51));
        jButton49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton49.setText("FM");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton49, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 340, 120, 50));

        jButton52.setBackground(new java.awt.Color(255, 51, 204));
        jButton52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton52.setText("Steak");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton52, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 140, 50));

        jButton50.setBackground(new java.awt.Color(0, 255, 51));
        jButton50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton50.setText("Ravioli");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton50, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, 120, 50));

        jButton51.setBackground(new java.awt.Color(0, 255, 51));
        jButton51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton51.setText("Brioche");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton51, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 520, 120, 50));

        jButton53.setBackground(new java.awt.Color(0, 255, 51));
        jButton53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton53.setText("Napoleon");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton53, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 120, 50));

        jButton54.setBackground(new java.awt.Color(0, 255, 51));
        jButton54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton54.setText("Lao Pie");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton54, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 120, 50));

        jButton55.setBackground(new java.awt.Color(0, 255, 51));
        jButton55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton55.setText("Hen");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton55, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 120, 50));

        jButton56.setBackground(new java.awt.Color(0, 255, 51));
        jButton56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton56.setText("salmon");
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton56ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton56, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, 120, 50));

        jButton57.setBackground(new java.awt.Color(0, 255, 51));
        jButton57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton57.setText("Opaka");
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton57ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton57, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 120, 50));

        jButton58.setBackground(new java.awt.Color(255, 51, 204));
        jButton58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton58.setText("Lamb W");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton58, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 140, 50));

        jButton2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButton2.setText("1x Grill Tail");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 140, 50));

        jButton3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButton3.setText("2x Grill Tail");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 140, 50));

        jButton59.setBackground(new java.awt.Color(255, 51, 204));
        jButton59.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton59.setText("1x Quail");
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton59, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 640, 120, 50));

        jButton5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButton5.setText("MainLobster+Pasta");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 170, 50));

        jButton6.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jButton6.setText("MainLobster");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 140, 50));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
         
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_closeDialog

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        nameString[0]="Ny Steak Wellington";
        nameString[1]="Steak";
        nameString[2]="5.25";
        nameString[3]="6";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        nameString[0]="Fisherman";
        nameString[1]="FM";
        
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        nameString[0]   ="Special Rav(Crab)";
        nameString[1]="SP.Crab";
       
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        nameString[0]="Asian";
        nameString[1]="Asian";
        
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        nameString[0]="Clam Pasta";
        nameString[1]="Clam Pasta";

        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
        nameString[0]="Garlic Shirmp Pasta";
        nameString[1]="G.S.Pasta";

        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton65ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        nameString[0]="Linguini";
        nameString[1]="ML";

        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        nameString[0]="Lobster PadThai";
        nameString[1]="Lobster PadThai";
        nameString[2]="8.00";
        nameString[3]="6";
        setVisible(false);
        setVisible(false);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton106ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton106ActionPerformed
        
        int i = C.setName("Veggie","Chicken","Seafood");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        else if(i==3){choice=" (Seafood)";choice1="(S)";}
        if(i!=-1){
            nameString[0]="PadThai"+choice;
            nameString[1]="PadThai"+choice1;
            
        }
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton106ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        
        
        int i = C.setName("Veggie","Chicken","Seafood");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        else if(i==3){choice=" (Seafood)";choice1="(S)";}
        if(i!=-1){
            nameString[0]="Khao Soi"+choice;
            nameString[1]="KaoSoi"+choice1;
        }
        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        
        
        int i = C.setName("Veggie","Chicken","Seafood");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        else if(i==3){choice=" (Seafood)";choice1="(S)";}
        if(i!=-1){
            nameString[0]="Classic Pasta"+choice;
            nameString[1]="Classic.Pasta"+choice1;
        }
        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        
        
        int i = C.setName("Veggie","Chicken","Seafood");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        else if(i==3){choice=" (Seafood)";choice1="(S)";}
        if(i!=-1){
            nameString[0]="House Pasta"+choice;
            nameString[1]="HS.Pasta"+choice1;
        }
        setVisible(false);
        setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        
        int i = C.setName("Veggie","Chicken","Seafood");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        else if(i==3){choice=" (Seafood)";choice1="(S)";}
        if(i!=-1){
            nameString[0]="Southeast a La Maison"+choice;
            nameString[1]="South"+choice1;
        }
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        
        int i = C.setName("Red","White","R+W");
        String choice="";
        if(i==1){choice="(R)";}
        else if(i==2){choice="(W)";}
        else if(i==3){choice="(Red&White)";}
        if(i!=-1){
            nameString[0]="Cheese Ravioli"+choice;
            nameString[1]="Rav"+choice;
        }
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        nameString[0]="Seafood Brioche";
        nameString[1]="Brioche";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        nameString[0]="Sea Scallop Napoleon";
        nameString[1]="Napoleon";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        
        int i = C.setName("Veggie","Chicken","Seafood");
        String choice="";
        String choice1="";
        if(i==1){choice=" (Veggie)";choice1="(V)";}
        else if(i==2){choice=" (Chicken)";choice1="(C)";}
        else if(i==3){choice=" (Seafood)";choice1="(S)";}
        if(i!=-1){
            nameString[0]="Lao Pot Pie"+choice;
            nameString[1]="LaoPie"+choice1;
        }
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        nameString[0]="Hen";
        nameString[1]="Hen";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton56ActionPerformed
        nameString[0]="Salmon";
        nameString[1]="Salmon";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton56ActionPerformed

    private void jButton57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton57ActionPerformed
        nameString[0]="Opaka";
        nameString[1]="Opaka";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton57ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        nameString[0]="Lamb";
        nameString[1]="Lamb";
        nameString[2]="5.25";
        nameString[3]="6.00";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton58ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        nameString[0]="Grilled Tail";
        nameString[1]="SingleTail";
        nameString[2]="8.00";
        nameString[3]="6.00";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        nameString[0]="Grilled 2x Tail";
        nameString[1]="DoubleTail";
        nameString[2]="15.25";
        nameString[3]="15.00";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       nameString[0]="Seafood Medley";
        nameString[1]="Seafood Medley";
        nameString[2]="5.25";
        nameString[3]="6.00";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
        nameString[0]="Quail Bello";
        nameString[1]="SingleQuail";       
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton59ActionPerformed

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        nameString[0]="Double Quail Bello";
        nameString[1]="DoubleQuail"; 
        nameString[2]="8.00";
        nameString[3]="6.00";
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int i = options.setName("(MB)","(GB)","(LP)","(JJ)","","","");
        String choice="";
        String choice1="";
        if(i==1){choice=" (MB)";choice1="(MB)";}
        else if(i==2){choice=" (GB)";choice1="(GB)";}
        else if(i==3){choice=" (LP)";choice1="(LP)";}
        else if(i==4){choice=" (JJ)";choice1="(JJ)";}
        if(i!=-1){
            nameString[0]="MainLobster w/pasta";
            nameString[1]="Main"+choice1+"+pasta";
            nameString[2]="16.25";
            nameString[3]="16.25";
        }
        
        
        
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int i = options.setName("(MB)","(GB)","(LP)","(JJ)","","","");
        String choice="";
        String choice1="";
        if(i==1){choice=" (MB)";choice1="(MB)";}
        else if(i==2){choice=" (GB)";choice1="(GB)";}
        else if(i==3){choice=" (LP)";choice1="(LP)";}
        else if(i==4){choice=" (JJ)";choice1="(JJ)";}
        if(i!=-1){
            nameString[0]="MainLobster";
            nameString[1]="Main"+choice1;
            nameString[2]="11.25";
            nameString[3]="11.25";
        }
        
        
        setVisible(false);
        setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed
    
    
    

    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton106;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton65;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
    private JD_App options;
    private JD C;
    private String[] nameString= new String[4];
}