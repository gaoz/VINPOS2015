package com.vinpos.pos.CakeOrderPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mysql.jdbc.StringUtils;
import com.vinpos.connection.PrintConn;
import com.vinpos.pos.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
/**
 *
 * @author vinson
 */
public class CakeOrderPan extends javax.swing.JDialog {

    /**
     * Creates new form CakeOrderPan
     */
    private SelectDate date;
    private SelectCake cake;
    private SelectWriting writing;
    private ContactNotes contactNote;
    private SelectPaid paidPanel;
    private NumberFormat moneyVariable;
    
    private boolean thisIsNewCheck;
    
    private int wid;  
    private String dateString;
    private String timeString;
    private String cakeString;
    private String sizeString;  
    private String handwriting;
    private String customername;
    private String contactnumber;
    private String notes;
    
    private int userID;
    
    private Double price;
    private Double addon;
    private Double tax;
    private Double total;
    private int printed;
    private int voided;
    private int paid;
    private NumberFormat myFormat;
    
    public CakeOrderPan(java.awt.Frame parent, boolean modal, MyQuery myQ_) {
        super(parent, modal);
        initComponents();
        myQ=myQ_;
        date= new SelectDate(new javax.swing.JFrame(), true);
        cake = new SelectCake(new javax.swing.JFrame(), true);
        writing = new SelectWriting(new javax.swing.JFrame(),true);
        contactNote = new ContactNotes(new javax.swing.JFrame(),true);
        paidPanel= new SelectPaid(new javax.swing.JFrame(),true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        moneyVariable = NumberFormat.getCurrencyInstance(Locale.US);
        myFormat = new DecimalFormat("#000000");
        thisIsNewCheck=false;
        wid=0;
        cakeString="";
        sizeString="";
        dateString="";
        timeString="";
        handwriting="";
        customername="";
        contactnumber="";
        
        notes="";
        userID = 1;
        price=0.00;
        addon=0.00;
        tax=0.00;
        total=0.00;
        printed=0;
        voided=0;
        paid=2;
        
        
    }
    public void newOrder(int wid_){  // new check
        thisIsNewCheck=true;

        this.loadDateFromDB(wid_);
        this.updateBtnLableColor(wid_);
      
    }
    public void reOpenOrder(int wid_){ // old check
        
        thisIsNewCheck=false;
       
        this.loadDateFromDB(wid_);
        this.updateBtnLableColor(wid_);
        
        
    }
     public void reloadAfterEditOrder(int wid_){ // default (new or old ) check

        
        this.loadDateFromDB(wid_);
        this.updateBtnLableColor(wid_);
        this.resetTotal();
    }
    
    public void updateBtnLableColor(int wid_){
         
        if(cakeString!= null && !cakeString.isEmpty()){
            cakeS.setBackground(Color.green);
        }else{
            cakeS.setBackground(Color.white);
        }
        if(dateString!= null && !dateString.isEmpty()){
            dateS.setBackground(Color.green);
        }else{
            dateS.setBackground(Color.white);
        }
        if(   (customername!= null && !customername.isEmpty())   ||   (contactnumber!= null && !contactnumber.isEmpty()) || (notes!= null && !notes.isEmpty())){
            contactS.setBackground(Color.green);
        }else{
            contactS.setBackground(Color.white);
        }
        if(    (handwriting!= null && !handwriting.isEmpty())   ||  (addon!=null && addon!=0.00 )    ){
            writingS.setBackground(Color.green);
        }else{
            writingS.setBackground(Color.white);
        }
        if(paid==2 ){
            paidS.setBackground(Color.white);
        }else{
            paidS.setBackground(Color.green);
        }
        
    }
    public void loadDateFromDB(int wid_){
        SimpleDateFormat dateFormater = new SimpleDateFormat("YYYY-MM-dd");
        Object[] temp=myQ.getWholeCakeOrderWithWid(wid_);
        cakeString   =(String)temp[0] ;
        sizeString =(String)temp[1] ;
        Date date = new Date();
        date = (Date)temp[2];
        try{dateString =dateFormater.format(date) ;}catch(NullPointerException e){}
        timeString =(String)temp[3] ;
        handwriting =(String)temp[4] ;
        customername =(String)temp[5] ;
        contactnumber=(String)temp[6] ;
        notes =(String)temp[7] ;
        userID =(Integer)temp[8] ;
        price =(Double)temp[9] ;
        addon =(Double)temp[10];
        tax =(Double)temp[11];
        total =(Double)temp[12];
        wid =wid_;
        printed =(Integer)temp[14];
        voided =(Integer)temp[15];
        paid =(Integer)temp[16];
        
        this.refreshAllField();
    }
    
    private void refreshAllField(){
        CakeF.setText(cakeString);
        sizeF.setText(sizeString);
        dateF.setText(dateString);
        timeF.setText(timeString);
        writingF.setText(handwriting);
        customerF.setText(customername);
        phoneF.setText(contactnumber);
        noteF.setText(notes);
        serverF.setText(myQ.getServerName(userID));
        cakePriceF.setText(moneyVariable.format(price));
        writingAddonF.setText(moneyVariable.format(addon));
        taxF.setText(moneyVariable.format(tax));
        finalTotalF.setText(moneyVariable.format(total));
        receiptNumberF.setText(myFormat.format(wid));
        if(paid==0){
            paidF.setText("No");
        }else if(paid ==1 ){
            paidF.setText("Yes");
        }
    }
    public int initNewOrder(int _userID){
        this.userID=_userID;
        this.insertWholeCakeCheck();
        wid=myQ.getWholeCakeNewReceiptNumber();
        this.newOrder(wid);
        return wid;
    }
    
    public void CheckColorForButtons(){
        
    }
    
    private void insertWholeCakeCheck(){
        myQ.insertNewWholeCakeCheck(userID);
    }
    private void refreshReceiptNumber(){
        
        wid=myQ.getWholeCakeNewReceiptNumber();
        this.receiptNumberF.setText(myFormat.format(wid));
    }
    private void updateOrderDetail(){
        Object[] temp = new Object[17];

        temp[0] =cakeString;
        temp[1] =sizeString;
        temp[2] =dateString;
        temp[3] =timeString;
        temp[4] =handwriting;
        temp[5] =customername;
        temp[6] =contactnumber;
        temp[7] =notes;
        temp[8] =userID;
        temp[9] =price;
        temp[10]=addon;
        temp[11]=((price+addon)*0.04712);
        temp[12]=price+addon+((price+addon)*0.04712);
        temp[13]=wid;
        temp[14]=printed;
        temp[15]=voided;
        temp[16]=paid;
        //System.out.print(cakeString+sizeString+dateString+timeString+handwriting+customername+contactnumber+notes+userID+price+addon+tax+total+wid+printed+voided);
        myQ.insertWholeCakeOrder(temp);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datebtn = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        totalP = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cakeP = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        writingP = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        contactP = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        dateP = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        dateS = new javax.swing.JPanel();
        cakeS = new javax.swing.JPanel();
        writingS = new javax.swing.JPanel();
        contactS = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        paidP = new javax.swing.JPanel();
        paidS = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        receiptNumberF = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        taxF = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        fsdafad = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        dateF = new javax.swing.JLabel();
        CakeF = new javax.swing.JLabel();
        sizeF = new javax.swing.JLabel();
        writingF = new javax.swing.JLabel();
        customerF = new javax.swing.JLabel();
        phoneF = new javax.swing.JLabel();
        serverF = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        fsdafad1 = new javax.swing.JLabel();
        fsdafad2 = new javax.swing.JLabel();
        fsdafad3 = new javax.swing.JLabel();
        finalTotalF = new javax.swing.JLabel();
        cakePriceF = new javax.swing.JLabel();
        writingAddonF = new javax.swing.JLabel();
        noteF = new javax.swing.JLabel();
        fsdafad4 = new javax.swing.JLabel();
        paidF = new javax.swing.JLabel();
        timeF = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        datebtn.setBackground(new java.awt.Color(255, 102, 51));
        datebtn.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        datebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17937-01.png"))); // NOI18N
        datebtn.setText("Select Date");
        datebtn.setBorder(null);
        datebtn.setBorderPainted(false);
        datebtn.setFocusPainted(false);
        datebtn.setIconTextGap(20);
        datebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datebtnActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 102, 51));
        jButton5.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17935-01.png"))); // NOI18N
        jButton5.setText("Select Cake");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setFocusPainted(false);
        jButton5.setIconTextGap(20);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 102, 51));
        jButton8.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17936-01.png"))); // NOI18N
        jButton8.setText("Select Writing");
        jButton8.setBorder(null);
        jButton8.setBorderPainted(false);
        jButton8.setFocusPainted(false);
        jButton8.setIconTextGap(20);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(255, 102, 51));
        jButton6.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17939-01.png"))); // NOI18N
        jButton6.setText("Contact & Notes");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setFocusPainted(false);
        jButton6.setIconTextGap(20);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 102, 51));
        jButton9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17938-01.png"))); // NOI18N
        jButton9.setText("Final Total");
        jButton9.setActionCommand("Step 3 Final Total");
        jButton9.setBorder(null);
        jButton9.setBorderPainted(false);
        jButton9.setFocusPainted(false);
        jButton9.setIconTextGap(20);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(204, 204, 255));
        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17938-01.png"))); // NOI18N
        jLabel7.setText("Caculate Total");

        totalP.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout totalPLayout = new javax.swing.GroupLayout(totalP);
        totalP.setLayout(totalPLayout);
        totalPLayout.setHorizontalGroup(
            totalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        totalPLayout.setVerticalGroup(
            totalPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setForeground(new java.awt.Color(153, 153, 255));
        jPanel1.setToolTipText("");
        jPanel1.setMinimumSize(new java.awt.Dimension(588, 980));
        jPanel1.setPreferredSize(new java.awt.Dimension(588, 980));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setBackground(new java.awt.Color(255, 153, 153));
        jButton3.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton3.setText("Go Back");
        jButton3.setIconTextGap(20);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 850, 370, 80));

        jLabel13.setBackground(new java.awt.Color(153, 153, 153));
        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Cake Order Panel");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 330, 60));

        jPanel2.setBackground(new java.awt.Color(255, 217, 198));
        jPanel2.setForeground(new java.awt.Color(0, 153, 153));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17935-01.png"))); // NOI18N
        jLabel2.setText("Select Cake");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 340, 70));

        cakeP.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout cakePLayout = new javax.swing.GroupLayout(cakeP);
        cakeP.setLayout(cakePLayout);
        cakePLayout.setHorizontalGroup(
            cakePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        cakePLayout.setVerticalGroup(
            cakePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel2.add(cakeP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 340, 70));

        jLabel4.setBackground(new java.awt.Color(204, 204, 255));
        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17936-01.png"))); // NOI18N
        jLabel4.setText(" Select Writing");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 340, 70));

        writingP.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout writingPLayout = new javax.swing.GroupLayout(writingP);
        writingP.setLayout(writingPLayout);
        writingPLayout.setHorizontalGroup(
            writingPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        writingPLayout.setVerticalGroup(
            writingPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel2.add(writingP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 340, 70));

        jLabel5.setBackground(new java.awt.Color(204, 204, 255));
        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 102, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("1. Select Cake");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 340, 70));

        jLabel6.setBackground(new java.awt.Color(204, 204, 255));
        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17939-01.png"))); // NOI18N
        jLabel6.setText("Contact & Notes");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 340, 70));

        contactP.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout contactPLayout = new javax.swing.GroupLayout(contactP);
        contactP.setLayout(contactPLayout);
        contactPLayout.setHorizontalGroup(
            contactPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        contactPLayout.setVerticalGroup(
            contactPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel2.add(contactP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 340, 70));

        jLabel11.setBackground(new java.awt.Color(204, 204, 255));
        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/17937-01.png"))); // NOI18N
        jLabel11.setText("Select Date");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 340, 70));

        dateP.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout datePLayout = new javax.swing.GroupLayout(dateP);
        dateP.setLayout(datePLayout);
        datePLayout.setHorizontalGroup(
            datePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        datePLayout.setVerticalGroup(
            datePLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel2.add(dateP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 340, 70));

        jLabel12.setBackground(new java.awt.Color(204, 204, 255));
        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 102, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("1. Select Cake");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 340, 60));

        javax.swing.GroupLayout dateSLayout = new javax.swing.GroupLayout(dateS);
        dateS.setLayout(dateSLayout);
        dateSLayout.setHorizontalGroup(
            dateSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        dateSLayout.setVerticalGroup(
            dateSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel2.add(dateS, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 30, 70));

        javax.swing.GroupLayout cakeSLayout = new javax.swing.GroupLayout(cakeS);
        cakeS.setLayout(cakeSLayout);
        cakeSLayout.setHorizontalGroup(
            cakeSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        cakeSLayout.setVerticalGroup(
            cakeSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel2.add(cakeS, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, 70));

        javax.swing.GroupLayout writingSLayout = new javax.swing.GroupLayout(writingS);
        writingS.setLayout(writingSLayout);
        writingSLayout.setHorizontalGroup(
            writingSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        writingSLayout.setVerticalGroup(
            writingSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel2.add(writingS, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, 70));

        javax.swing.GroupLayout contactSLayout = new javax.swing.GroupLayout(contactS);
        contactS.setLayout(contactSLayout);
        contactSLayout.setHorizontalGroup(
            contactSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        contactSLayout.setVerticalGroup(
            contactSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel2.add(contactS, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, 70));

        jLabel9.setBackground(new java.awt.Color(204, 204, 255));
        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Paid Or Not Paid");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 340, 70));

        paidP.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout paidPLayout = new javax.swing.GroupLayout(paidP);
        paidP.setLayout(paidPLayout);
        paidPLayout.setHorizontalGroup(
            paidPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );
        paidPLayout.setVerticalGroup(
            paidPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel2.add(paidP, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 340, 70));

        javax.swing.GroupLayout paidSLayout = new javax.swing.GroupLayout(paidS);
        paidS.setLayout(paidSLayout);
        paidSLayout.setHorizontalGroup(
            paidSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        paidSLayout.setVerticalGroup(
            paidSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        jPanel2.add(paidS, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, -1, 70));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 550, 470));

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 2, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 255, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Receipt:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 110, 40));

        receiptNumberF.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        receiptNumberF.setForeground(new java.awt.Color(204, 255, 204));
        receiptNumberF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        receiptNumberF.setText("000000");
        jPanel1.add(receiptNumberF, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 430, 40));

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Cake:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 100, 30));

        taxF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        taxF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(taxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 70, 30));

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Server:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 100, 30));

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Size:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 100, 30));

        jLabel18.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Writing:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 100, 30));

        jLabel19.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setText("Customer:");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 100, 30));

        fsdafad.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        fsdafad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fsdafad.setText("Tax:");
        fsdafad.setToolTipText("");
        jPanel3.add(fsdafad, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 90, 30));

        jLabel20.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Phone:");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 100, 30));

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Date:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 30));

        dateF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        dateF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(dateF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 120, 30));

        CakeF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        CakeF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(CakeF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 310, 30));

        sizeF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        sizeF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(sizeF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 310, 30));

        writingF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        writingF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(writingF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 310, 30));

        customerF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        customerF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(customerF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 310, 30));

        phoneF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        phoneF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(phoneF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 310, 30));

        serverF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        serverF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(serverF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 310, 30));

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Notes:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 100, 30));

        fsdafad1.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        fsdafad1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fsdafad1.setText("Paid:");
        jPanel3.add(fsdafad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 70, 30));

        fsdafad2.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        fsdafad2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fsdafad2.setText("Cake Price:");
        jPanel3.add(fsdafad2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 70, 30));

        fsdafad3.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        fsdafad3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fsdafad3.setText(" Writing Addon:");
        jPanel3.add(fsdafad3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 90, 30));

        finalTotalF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        finalTotalF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(finalTotalF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 70, 30));

        cakePriceF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        cakePriceF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(cakePriceF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 70, 30));

        writingAddonF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        writingAddonF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(writingAddonF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 70, 30));

        noteF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        noteF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(noteF, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 310, 30));

        fsdafad4.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        fsdafad4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        fsdafad4.setText("Final Total:");
        jPanel3.add(fsdafad4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 70, 30));

        paidF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        paidF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(paidF, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 70, 30));

        timeF.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        timeF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(timeF, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 320, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 550, 170));

        jPanel4.setBackground(new java.awt.Color(255, 102, 102));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 550, 40));

        jPanel5.setBackground(new java.awt.Color(51, 153, 255));

        jButton7.setBackground(new java.awt.Color(102, 255, 102));
        jButton7.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vinpos/pos/CakeOrderPanel/1793122-01.png"))); // NOI18N
        jButton7.setText("Print Out");
        jButton7.setActionCommand("Step 4 Print Out");
        jButton7.setIconTextGap(20);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 750, 550, 190));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 0, 70, 60));

        jButton1.setBackground(new java.awt.Color(255, 102, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 0, 70, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
//        if(!dateF.getText().equals("")&&!CakeF.getText().equals("")){
//            
//                this.updateOrderDetail();
//            
//            this.setVisible(false);            
//        }else{
//            if(thisIsNewCheck){
//                myQ.voidEmptyWholeCakeCheck(wid);  
//            }
//            this.setVisible(false);
//        }
//         try{ 
//            if(dateF.getText().equals("") || CakeF.getText().equals("")){
//
//                //this.updateOrderDetail();
//                
//                if(thisIsNewCheck){
//                    myQ.voidEmptyWholeCakeCheck(wid);
//                }
//                this.setVisible(false);
//            }
//        }catch(NullPointerException e){
//                
//                if(thisIsNewCheck){
//                    myQ.voidEmptyWholeCakeCheck(wid);
//                }
//                this.setVisible(false);
//        }
         
         if(dateString!=null && !dateString.isEmpty() && cakeString!=null && !cakeString.isEmpty()){
            this.setVisible(false);
        }else{
            if(thisIsNewCheck){
                myQ.voidEmptyWholeCakeCheck(wid);
            }
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
         
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void datebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datebtnActionPerformed
        
        
    }//GEN-LAST:event_datebtnActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
//        if(!dateF.getText().equals("")&&!CakeF.getText().equals("")){
//            
//            this.updateOrderDetail();
//            
//            this.printCustomerReceipt();
//            this.printCakeKitchenReceipt();
//            
//            
//            this.setVisible(false);
//        }
//        
//         try{ 
//            if(dateF.getText().equals("") || CakeF.getText().equals("")){
//
//                //this.updateOrderDetail();
//                
//                if(thisIsNewCheck){
//                    myQ.voidEmptyWholeCakeCheck(wid);
//                }
//                this.setVisible(false);
//            }else{
//                
//                //this.updateOrderDetail();
//                this.printCustomerReceipt();
//                this.printCakeKitchenReceipt();
//                this.setVisible(false); 
//            }
//        }catch(NullPointerException e){
//                
//                if(thisIsNewCheck){
//                    myQ.voidEmptyWholeCakeCheck(wid);
//                }
//                this.setVisible(false);
//        }
         
         
         
        if(dateString!=null && !dateString.isEmpty() && cakeString!=null && !cakeString.isEmpty()){
                this.printCustomerReceipt();
                this.printCakeKitchenReceipt();
                this.setVisible(false); 
        }else{
            Msg msg = new Msg("Date and Cake Name must be filled");
            if(dateString==null || dateString.isEmpty()){
                dateS.setBackground(Color.red);
            }
            if(cakeString==null || cakeString.isEmpty()){
                cakeS.setBackground(Color.red);
            }
         
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        date.setWid(wid,myQ);
        date.setVisible(true);
        this.reloadAfterEditOrder(wid);
        /*try{
            String[] line = date.getData();
           
            datebtn.setBackground(Color.green);
            datebtn.setText("Selected: "+line[1]);
            dateString=line[3];
            timeString=line[2];
            dateF.setText(dateString + "   Pickup at: " + timeString);
            dateS.setBackground(Color.green);
            
        }catch(java.lang.NullPointerException e){
            
        }*/
        
    }//GEN-LAST:event_jLabel11MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
//        cake.setVisible(true);
//         
//             cakeString=cake.getCakeName();
//             price=cake.getCakePrice();
//             sizeString=cake.getCakeSize();
//        if(cakeString.equals("empty")||sizeString.equals("emtpy")||price==0.00){
//    
//        }else{
//             CakeF.setText(cakeString);
//             sizeF.setText(sizeString);
//             cakePriceF.setText(moneyVariable.format(price));
//             cakeS.setBackground(Color.green);
//             
//             this.resetTotal();
//             
//        }
        cake.setWid(wid,myQ);
        cake.loadData();
        cake.setVisible(true);
        this.reloadAfterEditOrder(wid);
        
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed
//        writing.setVisible(true);
//        String writingString=writing.getWriting(); 
//        handwriting = writingString;
//        addon= writing.getAddonPrice();
//        writingF.setText(handwriting);
//        writingS.setBackground(Color.green);
//        this.resetTotal();
        writing.setWid(wid,myQ);
        writing.loadData();
        writing.setVisible(true);
        this.reloadAfterEditOrder(wid);
        
        
    }//GEN-LAST:event_jLabel4MousePressed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
//        contactNote.setVisible(true);
//        String[] contact=contactNote.getContactNotes(); 
//        customername=contact[0];
//        contactnumber=contact[1];
//        notes=contact[2];
//        customerF.setText(contact[0]);
//        phoneF.setText(contact[1]);
//        noteF.setText(contact[2]);
//        contactS.setBackground(Color.green);
        
        contactNote.setWid(wid,myQ);
        contactNote.loadData();
        contactNote.setVisible(true);
        this.reloadAfterEditOrder(wid);
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
//        paidPanel.setVisible(true);
//        paid = paidPanel.getPaid();
//        if(paid==0){
//            paidF.setText("No");
//        }else if(paid ==1 ){
//            paidF.setText("Yes");
//        }
//        paidS.setBackground(Color.green);
        paidPanel.setWid(wid,myQ);
        paidPanel.loadData();
        paidPanel.setVisible(true);
        this.reloadAfterEditOrder(wid);
    }//GEN-LAST:event_jLabel9MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(dateString!=null && !dateString.isEmpty() && cakeString!=null && !cakeString.isEmpty()  ){
            this.setVisible(false);
        }else{
            if(thisIsNewCheck){
                myQ.voidEmptyWholeCakeCheck(wid);
            }
            this.setVisible(false);
        }
        
        /*
        try{ 
            if(dateF.getText().equals("") || CakeF.getText().equals("")){

                //this.updateOrderDetail();
                
                if(thisIsNewCheck){
                    myQ.voidEmptyWholeCakeCheck(wid);
                }
                this.setVisible(false);
            }
        }catch(NullPointerException e){
                
                if(thisIsNewCheck){
                    myQ.voidEmptyWholeCakeCheck(wid);
                }
                this.setVisible(false);
        }
        */
    }//GEN-LAST:event_jButton1ActionPerformed
    private void resetTotal(){
        Double subtotal = price+addon;
        tax = subtotal*0.04712;
        total = subtotal+tax;
        
        cakePriceF.setText(moneyVariable.format(price));
        writingAddonF.setText(moneyVariable.format(addon));
        taxF.setText(moneyVariable.format(tax));
        finalTotalF.setText(moneyVariable.format(total));
    }
    
        // customer receipt
        // customer receipt
        // customer receipt
        // customer receipt
        // customer receipt
           
    public void printCustomerReceipt(){
         
        Object[] temp=myQ.getWholeCakeOrderWithWid(wid);
        SimpleDateFormat week = new SimpleDateFormat("E");
        SimpleDateFormat monthday = new SimpleDateFormat("MM/dd");
        SimpleDateFormat dateFormater = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        date = (Date)temp[2];
        String paidString= "";
        paid = paidPanel.getPaid();
        if(paid==0){
            paidString="Not Paid Yet";
        }else if(paid ==1 ){
            paidString="Paid";
        }
        
        
        try{
            
                NumberFormat myFormat = new DecimalFormat("#00");
                String[] lines = new String[15];
                lines[0] =""+week.format(date);  // week day
                lines[7] =""+monthday.format(date); //date
                lines[8] =""+timeString; // pick up time
                lines[1] =""+sizeString; // size
                lines[2] =""+cakeString;// CakeName
                lines[3] =""+handwriting;// writing
                lines[4] =""+customername;//  contact name
                lines[5] =""+contactnumber; //contact number
                lines[6] =""+paidString;// paid;
                lines[9] =""+n.format(price+addon)+"+"+n.format((price+addon)*0.0475)+"(tax)="+n.format((price+addon)+(price+addon)*0.0475);
                lines[10]=""+myFormat.format(wid);
                lines[11]=""+price;
                lines[12]=""+addon;
                lines[13]=""+((price+addon)*0.0475);
                lines[14]=""+total;
                if(myQ.getJsonSimpleClassObject().isThisPrintServer){// if yes then local print
                    s.printCakeOrderForCustomer(lines);
                }else{
                    
                    String text="";
                    for(int i=0; i<lines.length; i++){
                        text += lines[i]+"PPPPP";
                    }
                    
                    PrintConn con = new PrintConn();
                    con.createSocket();
                    Socket pritnerSocket = con.giveMePrinterSocket();
                    
                    try{
                        System.out.println("Print Order initial");
                        System.out.println("OrderString:"+text);
                        System.out.println("test socket connection 1");
                        DataOutputStream outToServer = new DataOutputStream(pritnerSocket.getOutputStream());
                        System.out.println("test outToserver connection 2");
                        outToServer.writeBytes("cakeorderforprint123456789"+text+"\n");  // set out message
                        System.out.println("test weiteByetes connection 3");
                        System.out.println("Print Order Sent");

                        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(pritnerSocket.getInputStream()));
                        System.out.println("SERVER Responds: " + inFromServer.readLine());
                        inFromServer.close();
                        con.closeSocket();

                    }catch(Exception e){

                        System.out.println("Table.sendPrintOrderToServer() Exception Occured - Break");
                        e.printStackTrace();
                    }
                }
       
        
        
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please select a date first.");
        }// end of try
    }
    public void printCakeKitchenReceipt(){
        // kitchen receipt
        Object[] temp=myQ.getWholeCakeOrderWithWid(wid);
        SimpleDateFormat week = new SimpleDateFormat("E");
        SimpleDateFormat monthday = new SimpleDateFormat("MM/dd");
        SimpleDateFormat dateFormater = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        date = (Date)temp[2];
        String paidString= "";
        paid = paidPanel.getPaid();
        if(paid==0){
            paidString="Not Paid Yet";
        }else if(paid ==1 ){
            paidString="Paid";
        }
        
        try{
        
                NumberFormat myFormat = new DecimalFormat("#00");
                String[] lines = new String[17];
                lines[0] =""+week.format(date);  // week day
                lines[7] =""+monthday.format(date); //date
                lines[8] =""+timeString; // pick up time
                lines[1] =""+sizeString; // size
                lines[2] =""+cakeString;// CakeName
                lines[3] =""+handwriting;// writing
                lines[4] =""+customername;// contact number
                lines[5] =""+contactnumber; 
                lines[6] =""+paidString;// paid;
                lines[9] =""+n.format(price+addon)+"+"+n.format((price+addon)*0.0475)+"(tax)="+n.format((price+addon)+(price+addon)*0.0475);
                lines[10]=""+myFormat.format(wid);
                lines[11]=""+price;
                lines[12]=""+addon;
                lines[13]=""+tax;
                lines[14]=""+total;
                lines[15]=""+notes;
                lines[16]=""+myQ.getServerName(userID);
                
                //System.out.println(notes);
                
                if(myQ.getJsonSimpleClassObject().isThisPrintServer){// if yes then local print
                     s4.printCheck3(lines);
                }else{
                    
                    String text="";
                    for(int i=0; i<lines.length; i++){
                        text += lines[i]+"PPPPP";
                    }
                    
                    PrintConn con = new PrintConn();
                    con.createSocket();
                    Socket pritnerSocket = con.giveMePrinterSocket();
                    
                    try{
                        System.out.println("Print Order initial");
                        System.out.println("OrderString:"+text);
                        System.out.println("test socket connection 1");
                        DataOutputStream outToServer = new DataOutputStream(pritnerSocket.getOutputStream());
                        System.out.println("test outToserver connection 2");
                        outToServer.writeBytes("cakecheckforprint123456789"+text+"\n");  // set out message
                        System.out.println("test weiteByetes connection 3");
                        System.out.println("Print Order Sent");

                        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(pritnerSocket.getInputStream()));
                        System.out.println("SERVER Responds: " + inFromServer.readLine());
                        inFromServer.close();
                        con.closeSocket();

                    }catch(Exception e){

                        System.out.println("Table.sendPrintOrderToServer() Exception Occured - Break");
                        e.printStackTrace();
                    }
                }
           
         }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Please select a date first.");
        }// end of try    
        catch(NumberFormatException e){
             JOptionPane.showMessageDialog(null, "Please enter Numbers only in total and no \"$\" sign in total");
        }
    }
    /**
     * @param args the command line arguments
     */
    public void run(){
        
        this.setVisible(true);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CakeF;
    private javax.swing.JPanel cakeP;
    private javax.swing.JLabel cakePriceF;
    private javax.swing.JPanel cakeS;
    private javax.swing.JPanel contactP;
    private javax.swing.JPanel contactS;
    private javax.swing.JLabel customerF;
    private javax.swing.JLabel dateF;
    private javax.swing.JPanel dateP;
    private javax.swing.JPanel dateS;
    private javax.swing.JButton datebtn;
    private javax.swing.JLabel finalTotalF;
    private javax.swing.JLabel fsdafad;
    private javax.swing.JLabel fsdafad1;
    private javax.swing.JLabel fsdafad2;
    private javax.swing.JLabel fsdafad3;
    private javax.swing.JLabel fsdafad4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel noteF;
    private javax.swing.JLabel paidF;
    private javax.swing.JPanel paidP;
    private javax.swing.JPanel paidS;
    private javax.swing.JLabel phoneF;
    private javax.swing.JLabel receiptNumberF;
    private javax.swing.JLabel serverF;
    private javax.swing.JLabel sizeF;
    private javax.swing.JLabel taxF;
    private javax.swing.JLabel timeF;
    private javax.swing.JPanel totalP;
    private javax.swing.JLabel writingAddonF;
    private javax.swing.JLabel writingF;
    private javax.swing.JPanel writingP;
    private javax.swing.JPanel writingS;
    // End of variables declaration//GEN-END:variables
private PrintTest3 s4 = new PrintTest3();
private PrintForCustomerCakeOrder s = new PrintForCustomerCakeOrder();
private String paidOrNot ="Not Paid Yet";
private NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
private MyQuery myQ;
}
