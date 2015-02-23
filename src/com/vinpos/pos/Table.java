package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.vinpos.connection.PrintConn;
import com.vinpos.pos.CakeOrderPanel.CakeOrderPan;
import com.vinpos.pos.CakeOrderPanel.CakeTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.io.*;
import java.net.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 *
 * @author sd
 */
public class Table extends javax.swing.JFrame {

    /**
     * Creates new form Table
     */
    public Process p;
    public Timer timer;
    public Timer timer2;
    public Timer timer3;
    public MyTimer myTime;
    public MyRestartTimer myRestartTimer;
    public MyPrinterServerTimer myPrinterServerTimer;
    public MyRegisterPrinterServerTimer  myRegisterPrinterServerTimer;
    public Thread t;
    
    public Table() {
        initial();
        
    }
    
    public void initial(){
        
        isOrderPanelOpen=false;
        databasePrinterSetting = new JsonSimple();
        myQ = new MyQuery(databasePrinterSetting);
        initComponents();
        optionJD = new JD(new java.awt.Frame(), true);
        optionJD_app = new JD_App(new java.awt.Frame(), true);
        selectWindow= new JD1(new java.awt.Frame(), true);
        fourCourseMenu = new FourCourseMenu1(new java.awt.Frame(), true, optionJD);
        threeCourseMenu1 = new ThreeCourseMenu1(new java.awt.Frame(), true, optionJD);
        threeCourseMenu2 = new ThreeCourseMenu2(new java.awt.Frame(), true, optionJD);
        printOptionPanel = new PrintOptionPanel(new java.awt.Frame(), true, myQ);
        edit4course = new Edit4course(new java.awt.Frame(), true, myQ);
        editDialog = new EDITDialog(new java.awt.Frame(), true, myQ);
        newOrderPanel = new NewOrderPanel(new java.awt.Frame(), true, myQ);
        seperateCheckJD = new SeperateCheckJD(new java.awt.Frame(), true, myQ);
        couponJD = new Coupon(new java.awt.Frame(), true, myQ);
        cakeInfoPanel = new CakeInfoPanel(new java.awt.Frame(), true, myQ);
        cakeOrderPan = new CakeOrderPan(new java.awt.Frame(), true, myQ);
        settingPanel = new SettingPane(new java.awt.Frame(), true, myQ);
        ordering = new JD2(new java.awt.Frame(), true, myQ, selectWindow, fourCourseMenu, threeCourseMenu1, threeCourseMenu2, optionJD, optionJD_app, printOptionPanel, edit4course, editDialog, newOrderPanel, seperateCheckJD, couponJD);
        l = new LOGINNOW(new java.awt.Frame(),true);
        myQ.cacheStatement();
        //myQ.recoveryMyCheckFromStorage();
 
        this.updateTableStatus();
        this.clearUpTableCountLabel();
        this.updateTablefourcourseCount();
        //this.updateServerAndTablesArea();
        this.updateWeekDayLabel();
        this.updatethreecourseDayCount();
        this.setLocationRelativeTo(getRootPane());


        timer= new Timer();
        timer2= new Timer();
        timer3= new Timer();
        myTime = new MyTimer();
        myRestartTimer = new MyRestartTimer();
        myPrinterServerTimer = new MyPrinterServerTimer();
        ArrayList<Object> componentForDayCourt = new ArrayList<Object>();
        componentForDayCourt.add(jPanel1);
        componentForDayCourt.add(DayCountLabelPanel);
        componentForDayCourt.add(dayCountDate);
        componentForDayCourt.add(dayCountDay);
        componentForDayCourt.add(dayCountHead);
        componentForDayCourt.add(dayCountDate1);
        componentForDayCourt.add(dayCountDay1);
        componentForDayCourt.add(dayCountHead1);
        componentForDayCourt.add(dayCountDate2);
        componentForDayCourt.add(dayCountDay2);
        componentForDayCourt.add(dayCountHead2);
        t = new Thread(new PaintStaticBars(componentForDayCourt, myQ));
        t.start();
       
        //myRegisterPrinterServerTimer = new MyRegisterPrinterServerTimer();
       
    }
    
    class MyTimer extends TimerTask{
        
      
        public MyTimer() {
            timer.scheduleAtFixedRate(this, new java.util.Date(), 2000);
        }
        public void run(){
           Table.this.updateTableStatus();  // reopen this when has multiple computer and user.
           Table.this.clearUpTableCountLabel();
           Table.this.updateTablefourcourseCount();
           Table.this.updateServerAndTablesArea();
           Table.this.updateTodaysHeadCount();
           Table.this.updateTodaysHalfDayHeadCount();
           Table.this.updatefourcourseDayCount();
           Table.this.updatethreecourseDayCount();
           
           //test path
//           try{
//                    String path = new File(".").getCanonicalPath();
//                    MessageDialog m = new MessageDialog(path);
//           }catch(Exception e){
//                    System.out.print("Restart program fail");
//           }
        }
           
           
    }
    class MyRegisterPrinterServerTimer extends TimerTask{
        String url = "http://vin.meteor.com/api/endpoint";
        String charset = "UTF-8"; 
        //InputStream response;
        URLConnection connection;
         String clientSentence;
        
        public MyRegisterPrinterServerTimer() {
            try{
                connection = new URL(url).openConnection();
                connection.setRequestProperty("Accept-Charset", charset);
                //response = new URL(url).openStream();
            }catch(java.net.BindException e){
                new Msg("FAIL TO CONNECT TO VIN.METEOR.COM/isPrintable");
                
            }catch(Exception e){
                e.printStackTrace();
            }
           
            timer3.scheduleAtFixedRate(this, new java.util.Date(), 100); // this make it run  when setting is open server printer again, 
            
        }
        public void run(){
             try{
                    
                    BufferedReader respon =
                               new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    clientSentence = respon.readLine();
                    //String[] tokens = this.stringToArray(clientSentence);
                    System.out.println("respond:"+clientSentence);
             }catch(BindException e){
                          e.printStackTrace();
                      }catch(Exception e){
                          e.printStackTrace();
             }
        }
    }
    class MyPrinterServerTimer extends TimerTask{
        
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket;
        Socket connectionSocket;
        public MyPrinterServerTimer() {
            try{
                welcomeSocket = new ServerSocket(6789);
            }catch(java.net.BindException e){
                new Msg("You have already running the program. This is closing");
                System.exit(0);
            }catch(Exception e){
                e.printStackTrace();
            }
           
            timer3.scheduleAtFixedRate(this, new java.util.Date(), 100); // this make it run  when setting is open server printer again, 
            
        }
        public void run(){
            
              if(databasePrinterSetting.isThisPrintServer){
                     System.out.println("printer server is running ....");
                  
//                   Socket connectionSocket = welcomeSocket.accept();
//                   BufferedReader inFromClient =
//                      new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
//                   //DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
//                   clientSentence = inFromClient.readLine();
//                   System.out.println("Received: " + clientSentence);
//                   String[] tokens = this.stringToArray(clientSentence);
//                   if(tokens[0].equals("kitchen")){
//                       printToKitchen(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
//                   }
//                   else if(tokens[0].equals("check")){
//                       printCheck(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]));
//                   }
                   
                   // send respond to client
                   //capitalizedSentence = "OrderPrinted" + '\n';
                   //outToClient.writeBytes(capitalizedSentence);
                    try{
                        
                         
                         boolean run = true;
                         while(run)
                         {
                            
                            if(!databasePrinterSetting.isThisPrintServer){
                                run = false; // break the while loop if setting is changed by user
                            }
                            connectionSocket = welcomeSocket.accept();
                            BufferedReader inFromClient =
                               new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                            clientSentence = inFromClient.readLine();
                            String[] tokens = this.stringToArray(clientSentence);
                            if(tokens[0].equals("kitchen")){
                                printToKitchen(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]));
                            }
                            else if(tokens[0].equals("check")){
                                printCheck(Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]));
                            }
                            else if(clientSentence.contains("cakeinformationforprint123456789")){
                                String text = clientSentence.replace("cakeinformationforprint123456789", "");
                                PrintForTheCakeInfor s2 = new PrintForTheCakeInfor();
                                String[] lines = text.split("PPPPP");
                          
                                s2.printForTheCakeInfor(lines);
                            }
                            else if(clientSentence.contains("cakeorderforprint123456789")){
                                String text = clientSentence.replace("cakeorderforprint123456789", "");
                                PrintForCustomerCakeOrder s = new PrintForCustomerCakeOrder();
                                String[] lines = text.split("PPPPP");
                                s.printCakeOrderForCustomer(lines);
                            }else if(clientSentence.contains("cakecheckforprint123456789")){
                                String text = clientSentence.replace("cakecheckforprint123456789", "");
                                PrintTest3 s = new PrintTest3();
                                String[] lines = text.split("PPPPP");
                                s.printCheck3(lines);
                            }
                            System.out.println("Received: " + clientSentence);
                            capitalizedSentence = clientSentence.toUpperCase() + '\n';
                            outToClient.writeBytes(capitalizedSentence);
                            
                         }
                      
                      }catch(BindException e){
                          e.printStackTrace();
                      }catch(Exception e){
                          e.printStackTrace();
                      }
              }// end if (printer server)
            
        }
        // this method is for printer server to print out
        public void printToKitchen(int tableID, int userID, int PRINT_TYPE){
            int DEFAULT_PRINT =1;
            int PRINT_ALL     =2;
            int PRINT_SEAFOOD =3;
            int PRINT_FOOD    =4;
            int PRINT_DRINK   =5;
            int PRINT_DESSERT =6;
            PrintCheckJJStyle p = new PrintCheckJJStyle(myQ);
            p.print(tableID,userID,PRINT_TYPE,myQ);
            myQ.updateSentTime(tableID);
            myQ.updateSent(tableID);
            int status = 1;
            myQ.updateTableStatus(status,tableID);
            myQ.setTableChecked("NO", tableID);
        }
        // this method is for printer server to print out
        public void printCheck(int tableID, int userID, int checkNum){
            if(checkNum!=0){
                PrintCheckJJStyle p = new PrintCheckJJStyle(myQ);
                if (!myQ.isTableChecked(tableID)){
                    myQ.insertNewCheck(tableID);
                }
                int cid = myQ.getCheckIDWithTID(tableID);
                myQ.updateOrderCid(cid, tableID);
                //myQ.copyOrderToStorage(tableID);
                int status = 2;
                myQ.updateTableStatus(status,tableID);
                myQ.setTableChecked("YES",tableID);

                ArrayList<Integer> a =myQ.getSortedSubCheckNumber(tableID);
                if(checkNum==-1){ //-1 means print all subtable check button is selected
                    for(int i=0; i<myQ.countSubCheck(tableID); i++){
                        p.printCheck(tableID,userID,cid,a.get(i));

                    }

                }else{ // else then print single selected subtable check
                   for(int i=0; i<myQ.countSubCheck(tableID); i++){
                        if(a.get(i)== checkNum){
                            p.printCheck(tableID,userID,cid,checkNum);
                        }
                    }
                }

            }// end of checkNumber is not selected by user
                    
        }
        public void printCakeInfor(String text){
            PrintForTheCakeInfor s2 = new PrintForTheCakeInfor();
            String[] lines = text.split("\\n");
            s2.printForTheCakeInfor(lines);
        }
        String[] stringToArray(String wordString) {
            String[] result;
            int i = 0;     // index into the next empty array element

            //--- Declare and create a StringTokenizer
            StringTokenizer st = new StringTokenizer(wordString);

            //--- Create an array which will hold all the tokens.
            result = new String[st.countTokens()];

            //--- Loop, getting each of the tokens
            while (st.hasMoreTokens()) {
                result[i++] = st.nextToken();
            }

            return result;
        }
           
           
    }
    class MyRestartTimer extends TimerTask{
        
      
        public MyRestartTimer() {
            timer2.scheduleAtFixedRate(this, new java.util.Date(), 10000);
        }
        public void run(){
//            try {
//                Thread.sleep(2000000);
//                //Thread.sleep(3000);
//            } catch(InterruptedException ex) {
//                Thread.currentThread().interrupt();
//            }
//            if(!isOrderPanelOpen){
//                try{
//                    String path = new File(".").getCanonicalPath();
//                    p = Runtime.getRuntime().exec("java -jar \""+path+"\\NEW_JJ.jar\"");
//                    p = Runtime.getRuntime().exec("java -jar \"C:\\Users\\jj\\Desktop\\jjFolder\\NEW_JJ 11.17.13\\dist\\NEW_JJ.jar\"");
//                    myQ.closeCo(); 
//                    System.exit(0);
//                }catch(Exception e){
//                    System.out.print("Restart program fail");
//                }
//            }
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

        jLabel1 = new javax.swing.JLabel();
        fourCourseCount1 = new javax.swing.JLabel();
        fourCourseCount2 = new javax.swing.JLabel();
        fourCourseCount3 = new javax.swing.JLabel();
        fourCourseCount4 = new javax.swing.JLabel();
        fourCourseCount5 = new javax.swing.JLabel();
        fourCourseCount6 = new javax.swing.JLabel();
        fourCourseCount7 = new javax.swing.JLabel();
        fourCourseCount8 = new javax.swing.JLabel();
        fourCourseCount9 = new javax.swing.JLabel();
        fourCourseCount10 = new javax.swing.JLabel();
        fourCourseCount11 = new javax.swing.JLabel();
        fourCourseCount12 = new javax.swing.JLabel();
        fourCourseCount13 = new javax.swing.JLabel();
        fourCourseCount14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lunchheadCountLabel = new javax.swing.JLabel();
        lunchheadCountLabel1 = new javax.swing.JLabel();
        lunchheadCountLabel2 = new javax.swing.JLabel();
        lunchheadCountLabel3 = new javax.swing.JLabel();
        lunchheadCountLabel4 = new javax.swing.JLabel();
        lunchheadCountLabel5 = new javax.swing.JLabel();
        lunchheadCountLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        D1 = new javax.swing.JButton();
        D1F = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        G4F = new javax.swing.JLabel();
        T9F = new javax.swing.JLabel();
        B3F = new javax.swing.JLabel();
        C1F = new javax.swing.JLabel();
        C3F = new javax.swing.JLabel();
        B2F = new javax.swing.JLabel();
        C2F = new javax.swing.JLabel();
        B4F = new javax.swing.JLabel();
        C4F = new javax.swing.JLabel();
        B1F = new javax.swing.JLabel();
        A1F = new javax.swing.JLabel();
        A2F = new javax.swing.JLabel();
        T2F = new javax.swing.JLabel();
        T1F = new javax.swing.JLabel();
        S3F = new javax.swing.JLabel();
        S2F = new javax.swing.JLabel();
        S1F = new javax.swing.JLabel();
        S8F = new javax.swing.JLabel();
        S6F = new javax.swing.JLabel();
        S7F = new javax.swing.JLabel();
        S9F = new javax.swing.JLabel();
        S10F = new javax.swing.JLabel();
        S4F = new javax.swing.JLabel();
        S5F = new javax.swing.JLabel();
        T10F = new javax.swing.JLabel();
        G3F = new javax.swing.JLabel();
        G2F = new javax.swing.JLabel();
        G1F = new javax.swing.JLabel();
        C5F = new javax.swing.JLabel();
        T2 = new javax.swing.JButton();
        T1 = new javax.swing.JButton();
        EXIT = new javax.swing.JButton();
        S10 = new javax.swing.JButton();
        T10 = new javax.swing.JButton();
        T9 = new javax.swing.JButton();
        A1 = new javax.swing.JButton();
        A2 = new javax.swing.JButton();
        C1 = new javax.swing.JButton();
        B1 = new javax.swing.JButton();
        C2 = new javax.swing.JButton();
        B2 = new javax.swing.JButton();
        C3 = new javax.swing.JButton();
        B3 = new javax.swing.JButton();
        C4 = new javax.swing.JButton();
        B4 = new javax.swing.JButton();
        C5 = new javax.swing.JButton();
        S1 = new javax.swing.JButton();
        S2 = new javax.swing.JButton();
        S3 = new javax.swing.JButton();
        S4 = new javax.swing.JButton();
        S5 = new javax.swing.JButton();
        S6 = new javax.swing.JButton();
        S7 = new javax.swing.JButton();
        S8 = new javax.swing.JButton();
        S9 = new javax.swing.JButton();
        G1 = new javax.swing.JButton();
        G2 = new javax.swing.JButton();
        G3 = new javax.swing.JButton();
        G4 = new javax.swing.JButton();
        close = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ServerAndTables = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        W1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        W2 = new javax.swing.JButton();
        headCountLabel = new javax.swing.JLabel();
        day1 = new javax.swing.JLabel();
        day2 = new javax.swing.JLabel();
        headCountLabel1 = new javax.swing.JLabel();
        day3 = new javax.swing.JLabel();
        headCountLabel2 = new javax.swing.JLabel();
        day4 = new javax.swing.JLabel();
        headCountLabel3 = new javax.swing.JLabel();
        day5 = new javax.swing.JLabel();
        headCountLabel4 = new javax.swing.JLabel();
        day6 = new javax.swing.JLabel();
        headCountLabel5 = new javax.swing.JLabel();
        day7 = new javax.swing.JLabel();
        headCountLabel6 = new javax.swing.JLabel();
        DayCountLabelPanel = new javax.swing.JPanel();
        dayCountDate = new javax.swing.JLabel();
        dayCountDay = new javax.swing.JLabel();
        dayCountHead = new javax.swing.JLabel();
        dayCountDate1 = new javax.swing.JLabel();
        dayCountDay1 = new javax.swing.JLabel();
        dayCountHead1 = new javax.swing.JLabel();
        dayCountDate2 = new javax.swing.JLabel();
        dayCountDay2 = new javax.swing.JLabel();
        dayCountHead2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Lucida Handwriting", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 51));
        jLabel1.setText("4Course Count:");
        jLabel1.setToolTipText("");

        fourCourseCount1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount1.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount1.setText("count");

        fourCourseCount2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount2.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount2.setText("count");

        fourCourseCount3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount3.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount3.setText("count");

        fourCourseCount4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount4.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount4.setText("count");

        fourCourseCount5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount5.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount5.setText("count");

        fourCourseCount6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount6.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount6.setText("count");

        fourCourseCount7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount7.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount7.setText("count");

        fourCourseCount8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount8.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount8.setText("count");

        fourCourseCount9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount9.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount9.setText("count");

        fourCourseCount10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount10.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount10.setText("count");

        fourCourseCount11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount11.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount11.setText("count");

        fourCourseCount12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount12.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount12.setText("count");

        fourCourseCount13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount13.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount13.setText("count");

        fourCourseCount14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        fourCourseCount14.setForeground(new java.awt.Color(0, 255, 0));
        fourCourseCount14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fourCourseCount14.setText("count");

        jLabel4.setFont(new java.awt.Font("Lucida Handwriting", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 51));
        jLabel4.setText("3Course Count:");
        jLabel4.setToolTipText("");

        lunchheadCountLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunchheadCountLabel.setForeground(new java.awt.Color(0, 255, 0));
        lunchheadCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lunchheadCountLabel.setText("count");

        lunchheadCountLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunchheadCountLabel1.setForeground(new java.awt.Color(0, 255, 0));
        lunchheadCountLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lunchheadCountLabel1.setText("count");

        lunchheadCountLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunchheadCountLabel2.setForeground(new java.awt.Color(0, 255, 0));
        lunchheadCountLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lunchheadCountLabel2.setText("count");

        lunchheadCountLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunchheadCountLabel3.setForeground(new java.awt.Color(0, 255, 0));
        lunchheadCountLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lunchheadCountLabel3.setText("count");

        lunchheadCountLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunchheadCountLabel4.setForeground(new java.awt.Color(0, 255, 0));
        lunchheadCountLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lunchheadCountLabel4.setText("count");

        lunchheadCountLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunchheadCountLabel5.setForeground(new java.awt.Color(0, 255, 0));
        lunchheadCountLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lunchheadCountLabel5.setText("count");

        lunchheadCountLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lunchheadCountLabel6.setForeground(new java.awt.Color(0, 255, 0));
        lunchheadCountLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lunchheadCountLabel6.setText("count");

        jLabel3.setFont(new java.awt.Font("Lucida Handwriting", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 51));
        jLabel3.setText("Lunch Count :");

        D1.setBackground(new java.awt.Color(255, 255, 255));
        D1.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        D1.setText("D1");
        D1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        D1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        D1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                D1MousePressed(evt);
            }
        });

        D1F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        D1F.setForeground(new java.awt.Color(0, 0, 204));
        D1F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D1F.setText("D1F");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1170, 900));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setMinimumSize(new java.awt.Dimension(1170, 900));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        G4F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        G4F.setForeground(new java.awt.Color(0, 0, 204));
        G4F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        G4F.setText("G4F");
        jPanel2.add(G4F, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 470, 80, 50));

        T9F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        T9F.setForeground(new java.awt.Color(0, 0, 255));
        T9F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T9F.setText("T9F");
        jPanel2.add(T9F, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 80, 50));

        B3F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        B3F.setForeground(new java.awt.Color(0, 0, 255));
        B3F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B3F.setText("B3F");
        jPanel2.add(B3F, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 340, 80, 50));

        C1F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        C1F.setForeground(new java.awt.Color(0, 0, 255));
        C1F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        C1F.setText("C1F");
        jPanel2.add(C1F, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 80, 50));

        C3F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        C3F.setForeground(new java.awt.Color(0, 0, 255));
        C3F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        C3F.setText("C3F");
        jPanel2.add(C3F, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 80, 50));

        B2F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        B2F.setForeground(new java.awt.Color(0, 0, 255));
        B2F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B2F.setText("B2F");
        jPanel2.add(B2F, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 240, 80, 50));

        C2F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        C2F.setForeground(new java.awt.Color(0, 0, 255));
        C2F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        C2F.setText("C2F");
        jPanel2.add(C2F, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 80, 50));

        B4F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        B4F.setForeground(new java.awt.Color(0, 0, 255));
        B4F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B4F.setText("B4F");
        jPanel2.add(B4F, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 450, 80, 50));

        C4F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        C4F.setForeground(new java.awt.Color(0, 0, 255));
        C4F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        C4F.setText("C4F");
        jPanel2.add(C4F, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 450, 80, 50));

        B1F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        B1F.setForeground(new java.awt.Color(0, 0, 255));
        B1F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B1F.setText("B1F");
        jPanel2.add(B1F, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, 80, 50));

        A1F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        A1F.setForeground(new java.awt.Color(0, 0, 255));
        A1F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        A1F.setText("A1F");
        jPanel2.add(A1F, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 80, 50));

        A2F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        A2F.setForeground(new java.awt.Color(0, 0, 255));
        A2F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        A2F.setText("A2F");
        jPanel2.add(A2F, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 80, 50));

        T2F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        T2F.setForeground(new java.awt.Color(0, 0, 255));
        T2F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T2F.setText("T2F");
        jPanel2.add(T2F, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 80, 50));

        T1F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        T1F.setForeground(new java.awt.Color(0, 0, 255));
        T1F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T1F.setText("T1F");
        jPanel2.add(T1F, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 80, 50));

        S3F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        S3F.setForeground(new java.awt.Color(0, 0, 255));
        S3F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        S3F.setText("S3F");
        jPanel2.add(S3F, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 110, 80, 50));

        S2F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        S2F.setForeground(new java.awt.Color(0, 0, 255));
        S2F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        S2F.setText("S2F");
        jPanel2.add(S2F, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 240, 80, 50));

        S1F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        S1F.setForeground(new java.awt.Color(0, 0, 255));
        S1F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        S1F.setText("S1F");
        jPanel2.add(S1F, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 370, 80, 50));

        S8F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        S8F.setForeground(new java.awt.Color(0, 0, 255));
        S8F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        S8F.setText("S8F");
        jPanel2.add(S8F, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 310, 80, 50));

        S6F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        S6F.setForeground(new java.awt.Color(0, 0, 255));
        S6F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        S6F.setText("S6F");
        jPanel2.add(S6F, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 390, 80, 50));

        S7F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        S7F.setForeground(new java.awt.Color(0, 0, 255));
        S7F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        S7F.setText("S7F");
        jPanel2.add(S7F, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 440, 80, 50));

        S9F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        S9F.setForeground(new java.awt.Color(0, 0, 255));
        S9F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        S9F.setText("S9F");
        jPanel2.add(S9F, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 190, 80, 50));

        S10F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        S10F.setForeground(new java.awt.Color(0, 0, 255));
        S10F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        S10F.setText("S10F");
        jPanel2.add(S10F, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 60, 90, 60));

        S4F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        S4F.setForeground(new java.awt.Color(0, 0, 255));
        S4F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        S4F.setText("S4F");
        jPanel2.add(S4F, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 60, 80, 50));

        S5F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        S5F.setForeground(new java.awt.Color(0, 0, 255));
        S5F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        S5F.setText("S5F");
        jPanel2.add(S5F, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 230, 80, 50));

        T10F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        T10F.setForeground(new java.awt.Color(0, 0, 255));
        T10F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        T10F.setText("T10F");
        jPanel2.add(T10F, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 80, 50));

        G3F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        G3F.setForeground(new java.awt.Color(0, 0, 204));
        G3F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        G3F.setText("G3F");
        jPanel2.add(G3F, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, 80, 50));

        G2F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        G2F.setForeground(new java.awt.Color(0, 0, 204));
        G2F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        G2F.setText("G2F");
        jPanel2.add(G2F, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, 80, 50));

        G1F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        G1F.setForeground(new java.awt.Color(0, 0, 204));
        G1F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        G1F.setText("G1F");
        jPanel2.add(G1F, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 80, 50));

        C5F.setFont(new java.awt.Font("Monospaced", 1, 36)); // NOI18N
        C5F.setForeground(new java.awt.Color(0, 0, 204));
        C5F.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        C5F.setText("C5F");
        jPanel2.add(C5F, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 550, 80, 50));

        T2.setBackground(new java.awt.Color(255, 255, 255));
        T2.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        T2.setText("T2");
        T2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        T2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        T2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                T2MousePressed(evt);
            }
        });
        jPanel2.add(T2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 80, 70));

        T1.setBackground(new java.awt.Color(255, 255, 255));
        T1.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        T1.setText("T1");
        T1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        T1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        T1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                T1MousePressed(evt);
            }
        });
        jPanel2.add(T1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 80, 70));

        EXIT.setBackground(new java.awt.Color(255, 255, 0));
        EXIT.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        EXIT.setText("EXIT");
        EXIT.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        EXIT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EXIT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EXITActionPerformed(evt);
            }
        });
        jPanel2.add(EXIT, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 600, 80, 60));

        S10.setBackground(new java.awt.Color(255, 255, 255));
        S10.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        S10.setText("S10");
        S10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        S10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        S10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                S10MousePressed(evt);
            }
        });
        jPanel2.add(S10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 80, 80, 70));

        T10.setBackground(new java.awt.Color(255, 255, 255));
        T10.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        T10.setText("T10");
        T10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        T10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        T10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                T10MousePressed(evt);
            }
        });
        jPanel2.add(T10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 80, 70));

        T9.setBackground(new java.awt.Color(255, 255, 255));
        T9.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        T9.setText("T9");
        T9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        T9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        T9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                T9MousePressed(evt);
            }
        });
        jPanel2.add(T9, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 80, 70));

        A1.setBackground(new java.awt.Color(255, 255, 255));
        A1.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        A1.setText("A1");
        A1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        A1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        A1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                A1MousePressed(evt);
            }
        });
        jPanel2.add(A1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, 80, 70));

        A2.setBackground(new java.awt.Color(255, 255, 255));
        A2.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        A2.setText("A2");
        A2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        A2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        A2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                A2MousePressed(evt);
            }
        });
        jPanel2.add(A2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 80, 70));

        C1.setBackground(new java.awt.Color(255, 255, 255));
        C1.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        C1.setText("C1");
        C1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        C1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C1MousePressed(evt);
            }
        });
        jPanel2.add(C1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 80, 70));

        B1.setBackground(new java.awt.Color(255, 255, 255));
        B1.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        B1.setText("B1");
        B1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        B1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        B1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B1MousePressed(evt);
            }
        });
        jPanel2.add(B1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 150, 80, 70));

        C2.setBackground(new java.awt.Color(255, 255, 255));
        C2.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        C2.setText("C2");
        C2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        C2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C2MousePressed(evt);
            }
        });
        jPanel2.add(C2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 80, 70));

        B2.setBackground(new java.awt.Color(255, 255, 255));
        B2.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        B2.setText("B2");
        B2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        B2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        B2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B2MousePressed(evt);
            }
        });
        jPanel2.add(B2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, 80, 70));

        C3.setBackground(new java.awt.Color(255, 255, 255));
        C3.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        C3.setText("C3");
        C3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        C3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C3MousePressed(evt);
            }
        });
        jPanel2.add(C3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 350, 80, 70));

        B3.setBackground(new java.awt.Color(255, 255, 255));
        B3.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        B3.setText("B3");
        B3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        B3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        B3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B3MousePressed(evt);
            }
        });
        jPanel2.add(B3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, 80, 70));

        C4.setBackground(new java.awt.Color(255, 255, 255));
        C4.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        C4.setText("C4");
        C4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        C4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C4MousePressed(evt);
            }
        });
        jPanel2.add(C4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 460, 80, 70));

        B4.setBackground(new java.awt.Color(255, 255, 255));
        B4.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        B4.setText("B4");
        B4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        B4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        B4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                B4MousePressed(evt);
            }
        });
        jPanel2.add(B4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 460, 80, 70));

        C5.setBackground(new java.awt.Color(255, 255, 255));
        C5.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        C5.setText("C5");
        C5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        C5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        C5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                C5MousePressed(evt);
            }
        });
        jPanel2.add(C5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 560, 80, 70));

        S1.setBackground(new java.awt.Color(255, 255, 255));
        S1.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        S1.setText("S1");
        S1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        S1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        S1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                S1MousePressed(evt);
            }
        });
        jPanel2.add(S1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 380, 80, 70));

        S2.setBackground(new java.awt.Color(255, 255, 255));
        S2.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        S2.setText("S2");
        S2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        S2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        S2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                S2MousePressed(evt);
            }
        });
        jPanel2.add(S2, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 250, 80, 70));

        S3.setBackground(new java.awt.Color(255, 255, 255));
        S3.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        S3.setText("S3");
        S3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        S3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        S3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                S3MousePressed(evt);
            }
        });
        jPanel2.add(S3, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 120, 80, 70));

        S4.setBackground(new java.awt.Color(255, 255, 255));
        S4.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        S4.setText("S4");
        S4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        S4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        S4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                S4MousePressed(evt);
            }
        });
        jPanel2.add(S4, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 70, 80, 70));

        S5.setBackground(new java.awt.Color(255, 255, 255));
        S5.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        S5.setText("S5");
        S5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        S5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        S5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                S5MousePressed(evt);
            }
        });
        jPanel2.add(S5, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 240, 80, 70));

        S6.setBackground(new java.awt.Color(255, 255, 255));
        S6.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        S6.setText("S6");
        S6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        S6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        S6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                S6MousePressed(evt);
            }
        });
        jPanel2.add(S6, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 400, 80, 70));

        S7.setBackground(new java.awt.Color(255, 255, 255));
        S7.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        S7.setText("S7");
        S7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        S7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        S7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                S7MousePressed(evt);
            }
        });
        jPanel2.add(S7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 450, 80, 70));

        S8.setBackground(new java.awt.Color(255, 255, 255));
        S8.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        S8.setText("S8");
        S8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        S8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        S8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                S8MousePressed(evt);
            }
        });
        jPanel2.add(S8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 320, 80, 70));

        S9.setBackground(new java.awt.Color(255, 255, 255));
        S9.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        S9.setText("S9");
        S9.setToolTipText("");
        S9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        S9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        S9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                S9MousePressed(evt);
            }
        });
        jPanel2.add(S9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 200, 80, 70));

        G1.setBackground(new java.awt.Color(255, 255, 255));
        G1.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        G1.setText("G1");
        G1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G1MousePressed(evt);
            }
        });
        jPanel2.add(G1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 80, 60));

        G2.setBackground(new java.awt.Color(255, 255, 255));
        G2.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        G2.setText("G2");
        G2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G2MousePressed(evt);
            }
        });
        jPanel2.add(G2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, 80, 60));

        G3.setBackground(new java.awt.Color(255, 255, 255));
        G3.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        G3.setText("G3");
        G3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G3MousePressed(evt);
            }
        });
        jPanel2.add(G3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, 80, 60));

        G4.setBackground(new java.awt.Color(255, 255, 255));
        G4.setFont(new java.awt.Font("Lithos Pro Regular", 1, 18)); // NOI18N
        G4.setText("G4");
        G4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        G4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        G4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                G4MousePressed(evt);
            }
        });
        jPanel2.add(G4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 480, 80, 60));

        close.setBackground(new java.awt.Color(255, 102, 51));
        close.setFont(new java.awt.Font("Lithos Pro Regular", 1, 12)); // NOI18N
        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        jPanel2.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 90, 60));

        jButton2.setBackground(new java.awt.Color(0, 153, 255));
        jButton2.setFont(new java.awt.Font("Lithos Pro Regular", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 51));
        jButton2.setText("Cake");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 90, 60));

        jButton3.setBackground(new java.awt.Color(255, 153, 102));
        jButton3.setFont(new java.awt.Font("Lithos Pro Regular", 1, 11)); // NOI18N
        jButton3.setText("Infor");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 90, 60));

        jScrollPane1.setBorder(null);

        ServerAndTables.setBackground(new java.awt.Color(51, 51, 51));
        ServerAndTables.setColumns(20);
        ServerAndTables.setFont(new java.awt.Font("Lithos Pro", 0, 24)); // NOI18N
        ServerAndTables.setForeground(new java.awt.Color(255, 204, 51));
        ServerAndTables.setLineWrap(true);
        ServerAndTables.setRows(5);
        ServerAndTables.setAutoscrolls(false);
        ServerAndTables.setMaximumSize(new java.awt.Dimension(300, 2147483647));
        ServerAndTables.setMinimumSize(new java.awt.Dimension(200, 30));
        jScrollPane1.setViewportView(ServerAndTables);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 0, 300, 500));

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 0));
        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 730, 1460, 170));

        jButton1.setFont(new java.awt.Font("Lithos Pro Regular", 1, 11)); // NOI18N
        jButton1.setText("Count");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 90, 60));

        jButton4.setText("Setting");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        jLabel8.setText("Formagio");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 80, 40));

        W1.setFont(new java.awt.Font("Lithos Pro Regular", 1, 11)); // NOI18N
        W1.setForeground(new java.awt.Color(255, 255, 255));
        W1.setText("W1");
        W1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                W1MousePressed(evt);
            }
        });
        W1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                W1ActionPerformed(evt);
            }
        });
        jPanel2.add(W1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 100, 60));

        jLabel9.setText("Hbachi");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 50, 40));

        W2.setFont(new java.awt.Font("Lithos Pro Regular", 1, 11)); // NOI18N
        W2.setForeground(new java.awt.Color(255, 255, 255));
        W2.setText("W2");
        W2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                W2MousePressed(evt);
            }
        });
        W2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                W2ActionPerformed(evt);
            }
        });
        jPanel2.add(W2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 100, 60));

        headCountLabel.setFont(new java.awt.Font("MS PGothic", 3, 24)); // NOI18N
        headCountLabel.setForeground(new java.awt.Color(0, 255, 0));
        headCountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headCountLabel.setText("count");
        jPanel2.add(headCountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 70, 30));

        day1.setFont(new java.awt.Font("MS PGothic", 3, 12)); // NOI18N
        day1.setForeground(new java.awt.Color(255, 204, 0));
        day1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day1.setText("Day");
        day1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(day1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 540, 130, 30));

        day2.setFont(new java.awt.Font("MS PGothic", 3, 12)); // NOI18N
        day2.setForeground(new java.awt.Color(255, 204, 0));
        day2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day2.setText("Day");
        day2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(day2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 130, 30));

        headCountLabel1.setFont(new java.awt.Font("MS PGothic", 3, 24)); // NOI18N
        headCountLabel1.setForeground(new java.awt.Color(0, 255, 0));
        headCountLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headCountLabel1.setText("count");
        jPanel2.add(headCountLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 570, 70, 30));

        day3.setFont(new java.awt.Font("MS PGothic", 3, 12)); // NOI18N
        day3.setForeground(new java.awt.Color(255, 204, 0));
        day3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day3.setText("Day");
        day3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(day3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 130, 30));

        headCountLabel2.setFont(new java.awt.Font("MS PGothic", 3, 24)); // NOI18N
        headCountLabel2.setForeground(new java.awt.Color(0, 255, 0));
        headCountLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headCountLabel2.setText("count");
        jPanel2.add(headCountLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 570, 70, 30));

        day4.setFont(new java.awt.Font("MS PGothic", 3, 12)); // NOI18N
        day4.setForeground(new java.awt.Color(255, 204, 0));
        day4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day4.setText("Day");
        day4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(day4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 540, 130, 30));

        headCountLabel3.setFont(new java.awt.Font("MS PGothic", 3, 24)); // NOI18N
        headCountLabel3.setForeground(new java.awt.Color(0, 255, 0));
        headCountLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headCountLabel3.setText("count");
        jPanel2.add(headCountLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 570, 70, 30));

        day5.setFont(new java.awt.Font("MS PGothic", 3, 12)); // NOI18N
        day5.setForeground(new java.awt.Color(255, 204, 0));
        day5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day5.setText("Day");
        day5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(day5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 540, 130, 30));

        headCountLabel4.setFont(new java.awt.Font("MS PGothic", 3, 24)); // NOI18N
        headCountLabel4.setForeground(new java.awt.Color(0, 255, 0));
        headCountLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headCountLabel4.setText("count");
        jPanel2.add(headCountLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 570, 70, 30));

        day6.setFont(new java.awt.Font("MS PGothic", 3, 12)); // NOI18N
        day6.setForeground(new java.awt.Color(255, 204, 0));
        day6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day6.setText("Day");
        day6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(day6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 540, 130, 30));

        headCountLabel5.setFont(new java.awt.Font("MS PGothic", 3, 24)); // NOI18N
        headCountLabel5.setForeground(new java.awt.Color(0, 255, 0));
        headCountLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headCountLabel5.setText("count");
        jPanel2.add(headCountLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 570, 70, 30));

        day7.setFont(new java.awt.Font("MS PGothic", 3, 12)); // NOI18N
        day7.setForeground(new java.awt.Color(255, 204, 0));
        day7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day7.setText("Day");
        day7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(day7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 540, 130, 30));

        headCountLabel6.setFont(new java.awt.Font("MS PGothic", 3, 24)); // NOI18N
        headCountLabel6.setForeground(new java.awt.Color(0, 255, 0));
        headCountLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headCountLabel6.setText("count");
        jPanel2.add(headCountLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 570, 70, 30));

        DayCountLabelPanel.setBackground(new java.awt.Color(51, 51, 51));
        DayCountLabelPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dayCountDate.setForeground(new java.awt.Color(255, 153, 51));
        dayCountDate.setText("Date");
        dayCountDate.setToolTipText("Date");
        DayCountLabelPanel.add(dayCountDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 30));

        dayCountDay.setForeground(new java.awt.Color(255, 153, 51));
        dayCountDay.setText("Week");
        dayCountDay.setToolTipText("Week");
        DayCountLabelPanel.add(dayCountDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 70, 30));

        dayCountHead.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        dayCountHead.setForeground(new java.awt.Color(255, 0, 153));
        dayCountHead.setText("PPL");
        dayCountHead.setToolTipText("PPL");
        DayCountLabelPanel.add(dayCountHead, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 120, 30));

        dayCountDate1.setForeground(new java.awt.Color(255, 255, 102));
        dayCountDate1.setText("Date");
        dayCountDate1.setToolTipText("Date");
        DayCountLabelPanel.add(dayCountDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 90, 30));

        dayCountDay1.setForeground(new java.awt.Color(255, 255, 51));
        dayCountDay1.setText("Week");
        dayCountDay1.setToolTipText("Week");
        DayCountLabelPanel.add(dayCountDay1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 70, 30));

        dayCountHead1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        dayCountHead1.setForeground(new java.awt.Color(255, 255, 204));
        dayCountHead1.setText("PPL");
        dayCountHead1.setToolTipText("PPL");
        DayCountLabelPanel.add(dayCountHead1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 120, 30));

        dayCountDate2.setForeground(new java.awt.Color(255, 255, 102));
        dayCountDate2.setText("Date");
        dayCountDate2.setToolTipText("Date");
        DayCountLabelPanel.add(dayCountDate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 90, 30));

        dayCountDay2.setForeground(new java.awt.Color(255, 255, 51));
        dayCountDay2.setText("Week");
        dayCountDay2.setToolTipText("Week");
        DayCountLabelPanel.add(dayCountDay2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 70, 30));

        dayCountHead2.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        dayCountHead2.setForeground(new java.awt.Color(255, 255, 204));
        dayCountHead2.setText("PPL");
        dayCountHead2.setToolTipText("PPL");
        DayCountLabelPanel.add(dayCountHead2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 120, 30));

        jPanel2.add(DayCountLabelPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 510, 300, 130));

        jButton5.setBackground(new java.awt.Color(204, 0, 153));
        jButton5.setForeground(new java.awt.Color(255, 255, 102));
        jButton5.setText("Refresh HeadCount Data");
        jButton5.setToolTipText("");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 640, 300, 40));

        jLabel2.setForeground(new java.awt.Color(255, 51, 255));
        jLabel2.setText("Click on the bars to see the detail head count data on the right panel ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 680, 470, 30));

        jLabel5.setForeground(new java.awt.Color(255, 204, 204));
        jLabel5.setText("Pink bar is last year");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, 150, 20));

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 10, 20));

        jPanel4.setBackground(new java.awt.Color(102, 255, 102));
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 660, -1, 20));

        jLabel6.setBackground(new java.awt.Color(255, 0, 255));
        jLabel6.setForeground(new java.awt.Color(255, 153, 255));
        jLabel6.setText("Dec");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 710, 30, 20));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("the 100 ppl white marking line");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 210, 30));

        jLabel10.setBackground(new java.awt.Color(255, 0, 255));
        jLabel10.setForeground(new java.awt.Color(255, 153, 255));
        jLabel10.setText("Jan");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 710, 30, 20));

        jLabel11.setBackground(new java.awt.Color(255, 0, 255));
        jLabel11.setForeground(new java.awt.Color(255, 153, 255));
        jLabel11.setText("Feb");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 710, 30, 20));

        jLabel12.setBackground(new java.awt.Color(255, 0, 255));
        jLabel12.setForeground(new java.awt.Color(255, 153, 255));
        jLabel12.setText("Mar");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 710, 30, 20));

        jLabel13.setBackground(new java.awt.Color(255, 0, 255));
        jLabel13.setForeground(new java.awt.Color(255, 153, 255));
        jLabel13.setText("Apr");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 710, 30, 20));

        jLabel14.setBackground(new java.awt.Color(255, 0, 255));
        jLabel14.setForeground(new java.awt.Color(255, 153, 255));
        jLabel14.setText("May");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 710, 30, 20));

        jLabel15.setBackground(new java.awt.Color(255, 0, 255));
        jLabel15.setForeground(new java.awt.Color(255, 153, 255));
        jLabel15.setText("June");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 710, 30, 20));

        jLabel16.setBackground(new java.awt.Color(255, 0, 255));
        jLabel16.setForeground(new java.awt.Color(255, 153, 255));
        jLabel16.setText("July");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 710, 30, 20));

        jLabel17.setBackground(new java.awt.Color(255, 0, 255));
        jLabel17.setForeground(new java.awt.Color(255, 153, 255));
        jLabel17.setText("Aug");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 710, 30, 20));

        jLabel18.setBackground(new java.awt.Color(255, 0, 255));
        jLabel18.setForeground(new java.awt.Color(255, 153, 255));
        jLabel18.setText("Sep");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 710, 30, 20));

        jLabel19.setBackground(new java.awt.Color(255, 0, 255));
        jLabel19.setForeground(new java.awt.Color(255, 153, 255));
        jLabel19.setText("Oct");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 710, 30, 20));

        jLabel20.setBackground(new java.awt.Color(255, 0, 255));
        jLabel20.setForeground(new java.awt.Color(255, 153, 255));
        jLabel20.setText("Nov");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 710, 30, 20));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, -1, 10));

        jLabel21.setForeground(new java.awt.Color(0, 255, 102));
        jLabel21.setText("Green bar is this year");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 660, 150, 20));

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 1460, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1460, 900));
        jPanel2.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void f5TableColor(JButton tableName, int status){
        if(status==1){tableName.setBackground(Color.GREEN);}
        else if(status==2){tableName.setBackground(Color.YELLOW);}
        else if(status==3){tableName.setBackground(Color.GREEN);} 
        else {tableName.setBackground(Color.LIGHT_GRAY);}
    }
    private void EXITActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EXITActionPerformed
        
        
        //Close close = new Close(new java.awt.Frame(), true);
        // if(close.getStatus().equals(true)){
           myQ.closeCo(); 
             this.dispose(); 
             System.exit(0);
       // }
         
    }//GEN-LAST:event_EXITActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
       ArrayList<Integer> a =myQ.getAllCheckedTableID();
        for(int i=0; i<a.size(); i++){
            int cid = myQ.getCheckIDWithTID(a.get(i));
            myQ.updateOrderCid(cid, a.get(i));
            myQ.copyOrderToStorage(a.get(i));
            myQ.clearUpOneTablesOrder(a.get(i));
            myQ.resetOneTablesAllstatus(a.get(i));
            this.updateTableStatus();
        }    
        
            
    }//GEN-LAST:event_closeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(l.isLogin()){                         // log in first
                    //openOrderPanel(l.getID());
            CakeTable table = new CakeTable(new java.awt.Frame(), true, myQ, l.getID());
            table.setVisible(true);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        cakeInfoPanel.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DatabaseCheckup databasecheckup = new DatabaseCheckup(new java.awt.Frame(), true, myQ);
        databasecheckup.setContent();
        databasecheckup.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void T10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T10MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_T10MousePressed

    private void T9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T9MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_T9MousePressed

    private void S7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S7MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_S7MousePressed

    private void S8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S8MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_S8MousePressed

    private void S9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S9MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_S9MousePressed

    private void S10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S10MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_S10MousePressed

    private void S4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S4MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_S4MousePressed

    private void S5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S5MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_S5MousePressed

    private void S6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S6MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_S6MousePressed

    private void S1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S1MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_S1MousePressed

    private void S2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S2MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_S2MousePressed

    private void S3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_S3MousePressed
        runOnEveryTable(evt);
    }//GEN-LAST:event_S3MousePressed

    private void B4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B4MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_B4MousePressed

    private void B3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B3MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_B3MousePressed

    private void B2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B2MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_B2MousePressed

    private void B1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_B1MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_B1MousePressed

    private void C4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C4MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_C4MousePressed

    private void C3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C3MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_C3MousePressed

    private void C2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C2MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_C2MousePressed

    private void C1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C1MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_C1MousePressed

    private void A2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A2MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_A2MousePressed

    private void A1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_A1MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_A1MousePressed

    private void D1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_D1MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_D1MousePressed

    private void C5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_C5MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_C5MousePressed

    private void T2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T2MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_T2MousePressed

    private void T1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T1MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_T1MousePressed

    private void G1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G1MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_G1MousePressed

    private void G2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G2MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_G2MousePressed

    private void G3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G3MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_G3MousePressed

    private void G4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_G4MousePressed
        runOnEveryTable(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_G4MousePressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
     
       settingPanel.runAndSetVisible(tableID, 1);
//                try{
//                    String path = new File(".").getCanonicalPath();
//                    p = Runtime.getRuntime().exec("java -jar \""+path+"\\NEW_JJ.jar\"");
//                    //p = Runtime.getRuntime().exec("java -jar \"C:\\Users\\jj\\Desktop\\jjFolder\\NEW_JJ 11.17.13\\dist\\NEW_JJ.jar\"");
//                    myQ.closeCo(); 
//                    System.exit(0);
//                }catch(Exception e){
//                    System.out.print("Restart program fail");
//                }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void W1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_W1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_W1ActionPerformed

    private void W1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_W1MousePressed
         runOnEveryTable(evt); 
    }//GEN-LAST:event_W1MousePressed

    private void W2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_W2MousePressed
         runOnEveryTable(evt);
    }//GEN-LAST:event_W2MousePressed

    private void W2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_W2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_W2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(t.isAlive()){
            t.interrupt();
        }t.start();
    }//GEN-LAST:event_jButton5ActionPerformed
     public void test()
         throws SocketException {
            Enumeration<NetworkInterface> nets =
              NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
              displayInterfaceInformation(netint);
            }
     }

     private static void displayInterfaceInformation(
            NetworkInterface netint) throws SocketException {
          System.out.printf(
              "Display name: %s%n", netint.getDisplayName());
          System.out.printf("Name: %s%n", netint.toString());
          Enumeration<InetAddress> inetAddresses = 
              netint.getInetAddresses();
          for (InetAddress inetAddress : Collections.list(
              inetAddresses)) {
          System.out.printf("InetAddress: %s%n", inetAddress);
          }
         System.out.printf("%n");
     }
    public void print(int tableID, int userID ){
             
             if (myQ.checkWhetherHasOrder(tableID)) {  //check if there are orders in this table; output and warning box tell u didnot order anything;
                
                if(myQ.countSubCheck(tableID)==1){  // check if there is subcheck otherwise need to loop and print all the subcheck
                    PrintCheckJJStyle p = new PrintCheckJJStyle(myQ);
                    if (!myQ.isTableChecked(tableID)){

                        myQ.insertNewCheck(tableID);

                    }
                    int cid = myQ.getCheckIDWithTID(tableID);
                    myQ.updateOrderCid(cid, tableID);

                    int status = 2;
                    
                    myQ.updateTableStatus(status,tableID);
                    myQ.setTableChecked("YES",tableID);
                    p.printCheck(tableID,userID,cid,myQ.getSortedSubCheckNumber(tableID).get(0));
                    
                }else{      // loop and print all the subcheck                          
                    PrintCheckJJStyle p = new PrintCheckJJStyle(myQ);
                    // check if checkID 
                    if (!myQ.isTableChecked(tableID)){        
                        myQ.insertNewCheck(tableID);
                    }
                    int cid = myQ.getCheckIDWithTID(tableID);
                    myQ.updateOrderCid(cid, tableID);
                    //myQ.copyOrderToStorage(tableID);
                    int status = 2;
                    myQ.updateTableStatus(status,tableID);
                    myQ.setTableChecked("YES",tableID);
                    ArrayList<Integer> a =myQ.getSortedSubCheckNumber(tableID);
                    for(int i=0; i<myQ.countSubCheck(tableID); i++){
                       p.printCheck(tableID,userID,cid,a.get(i));
                    }    
                    

                }
            this.updateTableStatus();
            } else { // output and warning box tell u didnot order anything
                Msg  m = new Msg("Man! You didnot order anything yet");
            }
            
    }
    public int checkNightButton(){
        
        time = Calendar.getInstance();
        int now = time.get(Calendar.HOUR_OF_DAY);
        if(now>=17){
            nightMenuDollarMore=1;
        }
        if(now<17) {
            nightMenuDollarMore=0;
        }

        return nightMenuDollarMore;
    }
   
    public void runOnEveryTable(java.awt.event.MouseEvent evt){
        isOrderPanelOpen=true;  // when restarting the program, this true will stop the program restart;
        
        String tableName = ((JButton)evt.getSource()).getText();   // get table Name string from Jbutton;
        int tID =myQ.getTableID(tableName);
        if(SwingUtilities.isLeftMouseButton(evt))
        {
            
               // System.out.println("mouse modifier:"+evt.getModifiers());
                 if( evt.getModifiers()==18 || evt.getModifiers()==24){ // control = 18 // alt =24
                    if(!this.databasePrinterSetting.isThisPrintServer){// if yes then local print
                            if(myQ.countSubCheck(myQ.getTableID(tableName))==1){
                                  
                            }else{// if not send to server to print
                                    if (myQ.checkWhetherHasOrder(tID)) { 
                                        ArrayList<Integer> a =myQ.getSortedSubCheckNumber(tID);
                                        for(int i=0; i<myQ.countSubCheck(tID); i++){
                                            sendPrintOrderToServer("check "+ tID +" 0 "+ a.get(i));//user is 0 because floor plan print check there is no user check in before print out.

                                        }
                                    }else{
                                        System.out.println("no order in this table");
                                    }
                            }
                    }else{
                          print(myQ.getTableID(tableName),0);
                    }
                    
                }
               else if(l.isLogin()){                         // log in first
                    openOrderPanel(l.getID(), tableName);
               }
                    
        }
       
        else if(SwingUtilities.isRightMouseButton(evt))
        {
                if(this.databasePrinterSetting.isThisPrintServer){// if yes then local print
                        print(myQ.getTableID(tableName),0);
                }      // without log in
                else{// else print out to server
                        ArrayList<Integer> a =myQ.getSortedSubCheckNumber(tID);
                        for(int i=0; i<myQ.countSubCheck(tID); i++){
                            sendPrintOrderToServer("check "+ tID+" 0 "+ a.get(i));//user is 0 because floor plan print check there is no user check in before print out.

                        }
                }
        }
        
        isOrderPanelOpen=false;   // when restarting the program, this true will stop the program restart;
    }
    /**
     * 
     * @param orderString  String format: "check tableid userid subcheck#"  for example: "check "+ tID +" 0 "+ a.get(i) 
     */
    public void sendPrintOrderToServer(String orderString){

        PrintConn con = new PrintConn();
        con.createSocket();
        Socket pritnerSocket = con.giveMePrinterSocket();

        try{
            System.out.println("Print Order initial");
            System.out.println("OrderString:"+orderString);
            System.out.println("test socket connection 1");
            DataOutputStream outToServer = new DataOutputStream(pritnerSocket.getOutputStream());
            System.out.println("test outToserver connection 2");
            outToServer.writeBytes(orderString+"\n");  // set out message
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
    
    public void openOrderPanel(int aid, String tableNum){
        
                  // perform when user success log in
            try{
                this.getTableIDandPPL(tableNum);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"error Unable to connect to database when get table id and ppl number at class table #650 \n"+e);
            }
            ////////myQ.activateTable(tableID);

            int peopleNum = pplNum;


            if(peopleNum==0){ 
                //JD1 selectWindow = new JD1(new java.awt.Frame(), true);
                int pickedNum =selectWindow.run();


                if(pickedNum!=-1){            // open new order
                    try{
                        myQ.setTablePPL(pickedNum,tableID); // updata database table ppl number
                        myQ.setTableAID(aid,tableID);/* need to add - update who created the table */
                        myQ.setTableUserID(aid,tableID);
                    }catch(Exception e){
                        System.out.println("setTablePPL error:"+e);
                    }
                    myQ.setTableCreateTime(this.getDateTime(), tableID);
                    
                    ordering.run(tableID);
                    this.updateTableStatus();
                }

            }else{                 // open the old order
                myQ.setTableUserID(aid,tableID);
                ordering.runAG(tableID);
                this.updateTableStatus();
            }


            ///////myQ.deActivateTable(tableID);
    }
    
    public void getTableIDandPPL(String tableNum){
        Integer[] o =myQ.getTableIDandPPL(tableNum);
        tableID = o[0];
        pplNum=o[1];
        
    }
   
    public void updateTableStatus(){
         ArrayList<Object> a =myQ.getOpenedTableStatusAndTname();
         for(int i =0; i<a.size();i++){
            Object[] o = (Object[])a.get(i);
            this.matchTablenameAndButtonThenSetStatus((Integer)o[0],(String)o[1]);
            
         }
    }
    public void updateTodaysHeadCount(){
        //System.out.println(myQ.getTodaysHeadCount(6));
        headCountLabel .setText(""+myQ.getTodaysHeadCount(6));
        headCountLabel1.setText(""+myQ.getTodaysHeadCount(5));
        headCountLabel2.setText(""+myQ.getTodaysHeadCount(4));
        headCountLabel3.setText(""+myQ.getTodaysHeadCount(3));
        headCountLabel4.setText(""+myQ.getTodaysHeadCount(2));
        headCountLabel5.setText(""+myQ.getTodaysHeadCount(1));
        headCountLabel6.setText(""+myQ.getTodaysHeadCount(0));
       
    }
    public void updateTodaysHalfDayHeadCount(){
        lunchheadCountLabel .setText(""+myQ.getTodaysHalfDayHeadCount(6));
        lunchheadCountLabel1.setText(""+myQ.getTodaysHalfDayHeadCount(5));
        lunchheadCountLabel2.setText(""+myQ.getTodaysHalfDayHeadCount(4));
        lunchheadCountLabel3.setText(""+myQ.getTodaysHalfDayHeadCount(3));
        lunchheadCountLabel4.setText(""+myQ.getTodaysHalfDayHeadCount(2));
        lunchheadCountLabel5.setText(""+myQ.getTodaysHalfDayHeadCount(1));
        lunchheadCountLabel6.setText(""+myQ.getTodaysHalfDayHeadCount(0));
    }
    
    public void updateWeekDayLabel(){
        day7.setText(transferWeekdayToString(myQ.getWeekDayInFriendlyFormat()%7)+" (Today)");
        day6.setText(transferWeekdayToString((myQ.getWeekDayInFriendlyFormat()+6)%7));
        day5.setText(transferWeekdayToString((myQ.getWeekDayInFriendlyFormat()+5)%7));
        day4.setText(transferWeekdayToString((myQ.getWeekDayInFriendlyFormat()+4)%7));
        day3.setText(transferWeekdayToString((myQ.getWeekDayInFriendlyFormat()+3)%7));
        day2.setText(transferWeekdayToString((myQ.getWeekDayInFriendlyFormat()+2)%7));
        day1.setText(transferWeekdayToString((myQ.getWeekDayInFriendlyFormat()+1)%7));
              
        
    }
    public void updatefourcourseDayCount(){
        fourCourseCount1.setText(""+myQ.fourCourseDayCount(6));
        fourCourseCount2.setText(""+myQ.fourCourseDayCount(5));
        fourCourseCount3.setText(""+myQ.fourCourseDayCount(4));
        fourCourseCount4.setText(""+myQ.fourCourseDayCount(3));
        fourCourseCount5.setText(""+myQ.fourCourseDayCount(2));
        fourCourseCount6.setText(""+myQ.fourCourseDayCount(1));
        fourCourseCount7.setText(""+myQ.fourCourseDayCount(0));
    }
    
    
    public void updatethreecourseDayCount(){
        fourCourseCount8.setText(""+myQ.threeCourseDayCount(6));
        fourCourseCount9.setText(""+myQ.threeCourseDayCount(5));
        fourCourseCount10.setText(""+myQ.threeCourseDayCount(4));
        fourCourseCount11.setText(""+myQ.threeCourseDayCount(3));
        fourCourseCount12.setText(""+myQ.threeCourseDayCount(2));
        fourCourseCount13.setText(""+myQ.threeCourseDayCount(1));
        fourCourseCount14.setText(""+myQ.threeCourseDayCount(0));
    }
    public String transferWeekdayToString(int weekday){
        if(weekday==1){ return "Sunday";}
        else if(weekday==2){ return "Monday";}
        else if(weekday==3){ return "Tuesday";}
        else if(weekday==4){ return "Wensday";}
        else if(weekday==5){ return "Thursday";}
        else if(weekday==6){ return "Friday";}
        else if(weekday==0){ return "Saturday";}
        else{return "Error";}
        
    }
    
    public void matchTablenameAndButtonThenSetStatus(int status, String tableName){
             if(tableName.equals("T1" )){ T1 .setBackground(this.getColor(status));}
        else if(tableName.equals("T2" )){ T2 .setBackground(this.getColor(status));}   
        else if(tableName.equals("T9" )){ T9 .setBackground(this.getColor(status));}  
        else if(tableName.equals("T10")){ T10.setBackground(this.getColor(status));}  
        else if(tableName.equals("A1" )){ A1 .setBackground(this.getColor(status));}  
        else if(tableName.equals("A2" )){ A2 .setBackground(this.getColor(status));}
        else if(tableName.equals("B1" )){ B1 .setBackground(this.getColor(status));}
        else if(tableName.equals("B2" )){ B2 .setBackground(this.getColor(status));}
        else if(tableName.equals("B3" )){ B3 .setBackground(this.getColor(status));}
        else if(tableName.equals("B4" )){ B4 .setBackground(this.getColor(status));}
        else if(tableName.equals("C1" )){ C1 .setBackground(this.getColor(status));}
        else if(tableName.equals("C2" )){ C2 .setBackground(this.getColor(status));}
        else if(tableName.equals("C3" )){ C3 .setBackground(this.getColor(status));}
        else if(tableName.equals("C4" )){ C4 .setBackground(this.getColor(status));}
        else if(tableName.equals("C5" )){ C5 .setBackground(this.getColor(status));}
        else if(tableName.equals("D1" )){ D1 .setBackground(this.getColor(status));}
        else if(tableName.equals("S1" )){ S1 .setBackground(this.getColor(status));}
        else if(tableName.equals("S2" )){ S2 .setBackground(this.getColor(status));}
        else if(tableName.equals("S3" )){ S3 .setBackground(this.getColor(status));}
        else if(tableName.equals("S4" )){ S4 .setBackground(this.getColor(status));}
        else if(tableName.equals("S5" )){ S5 .setBackground(this.getColor(status));}
        else if(tableName.equals("S6" )){ S6 .setBackground(this.getColor(status));}
        else if(tableName.equals("S7" )){ S7 .setBackground(this.getColor(status));}
        else if(tableName.equals("S8" )){ S8 .setBackground(this.getColor(status));}
        else if(tableName.equals("S9" )){ S9 .setBackground(this.getColor(status));}
        else if(tableName.equals("S10")){ S10.setBackground(this.getColor(status));}
        else if(tableName.equals("G1" )){ G1 .setBackground(this.getColor(status));}
        else if(tableName.equals("G2" )){ G2 .setBackground(this.getColor(status));}
        else if(tableName.equals("G3" )){ G3 .setBackground(this.getColor(status));}
        else if(tableName.equals("G4" )){ G4 .setBackground(this.getColor(status));}
        else if(tableName.equals("W1" )){ W1 .setBackground(this.getColor(status));}
        else if(tableName.equals("W2" )){ W2 .setBackground(this.getColor(status));}
       
             
           
             
    
    }
    public Color getColor(int status){
        if(status==0){return Color.WHITE;}
        else if(status==1){return Color.GREEN;}
        else if(status==2){return Color.ORANGE;}
        else {return Color.magenta;}
    }
    
    //public void reloadTableStatus(){
        
    //}
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
    public void updateTablefourcourseCount(){
        temp1 = myQ.getFourcrouseCount();
        tname = (ArrayList<String>) temp1.get(0);
        count = (ArrayList<String>) temp1.get(1);
      
        for(int i =0; i<tname.size(); i++){
            
            this.matchTablenameAndButtonThenSetCountStatus(count.get(i).toString(), tname.get(i).toString());
        }
        
        
    }
    public void matchTablenameAndButtonThenSetCountStatus(String count, String tableName){
             if(tableName.equals("T1" )){ T1F.setText(count);}
        else if(tableName.equals("T2" )){ T2F.setText(count);}   
        else if(tableName.equals("T9" )){ T9F.setText(count);}  
        else if(tableName.equals("T10")){ T10F.setText(count);}  
        else if(tableName.equals("A1" )){ A1F.setText(count);}  
        else if(tableName.equals("A2" )){ A2F.setText(count);}
        else if(tableName.equals("B1" )){ B1F.setText(count);}
        else if(tableName.equals("B2" )){ B2F.setText(count);}
        else if(tableName.equals("B3" )){ B3F.setText(count);}
        else if(tableName.equals("B4" )){ B4F.setText(count);}
        else if(tableName.equals("C1" )){ C1F.setText(count);}
        else if(tableName.equals("C2" )){ C2F.setText(count);}
        else if(tableName.equals("C3" )){ C3F.setText(count);}
        else if(tableName.equals("C4" )){ C4F.setText(count);}
        else if(tableName.equals("C5" )){ C5F.setText(count);}
        else if(tableName.equals("D1" )){ D1F.setText(count);}
        else if(tableName.equals("S1" )){ S1F.setText(count);}
        else if(tableName.equals("S2" )){ S2F.setText(count);}
        else if(tableName.equals("S3" )){ S3F.setText(count);}
        else if(tableName.equals("S4" )){ S4F.setText(count);}
        else if(tableName.equals("S5" )){ S5F.setText(count);}
        else if(tableName.equals("S6" )){ S6F.setText(count);}
        else if(tableName.equals("S7" )){ S7F.setText(count);}
        else if(tableName.equals("S8" )){ S8F.setText(count);}
        else if(tableName.equals("S9" )){ S9F.setText(count);}
        else if(tableName.equals("S10")){ S10F.setText(count);}
        else if(tableName.equals("G1" )){ G1F.setText(count);}
        else if(tableName.equals("G2" )){ G2F.setText(count);}
        else if(tableName.equals("G3" )){ G3F.setText(count);}
        else if(tableName.equals("G4" )){ G4F.setText(count);}
        
             
           
             
    
    }
    public void clearUpTableCountLabel(){
        T1F.setText ("");
        T2F.setText ("");   
        T9F.setText ("");  
        T10F.setText("");  
        A1F.setText ("");  
        A2F.setText ("");
        B1F.setText ("");
        B2F.setText ("");
        B3F.setText ("");
        B4F.setText ("");
        C1F.setText ("");
        C2F.setText ("");
        C3F.setText ("");
        C4F.setText ("");
        C5F.setText ("");
        D1F.setText ("");
        S1F.setText ("");
        S2F.setText ("");
        S3F.setText ("");
        S4F.setText ("");
        S5F.setText ("");
        S6F.setText ("");
        S7F.setText ("");
        S8F.setText ("");
        S9F.setText ("");
        S10F.setText("");
        G1F.setText ("");
        G2F.setText ("");
        G3F.setText ("");
        G4F.setText ("");
        
             
           
             
    
    }
    
    public void updateServerAndTablesArea(){
        temp="";
        temp = myQ.getServerAndTable();
        ServerAndTables.setText(temp);
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Table.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Table().setVisible(true);
               
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton A1;
    private javax.swing.JLabel A1F;
    private javax.swing.JButton A2;
    private javax.swing.JLabel A2F;
    private javax.swing.JButton B1;
    private javax.swing.JLabel B1F;
    private javax.swing.JButton B2;
    private javax.swing.JLabel B2F;
    private javax.swing.JButton B3;
    private javax.swing.JLabel B3F;
    private javax.swing.JButton B4;
    private javax.swing.JLabel B4F;
    private javax.swing.JButton C1;
    private javax.swing.JLabel C1F;
    private javax.swing.JButton C2;
    private javax.swing.JLabel C2F;
    private javax.swing.JButton C3;
    private javax.swing.JLabel C3F;
    private javax.swing.JButton C4;
    private javax.swing.JLabel C4F;
    private javax.swing.JButton C5;
    private javax.swing.JLabel C5F;
    private javax.swing.JButton D1;
    private javax.swing.JLabel D1F;
    private javax.swing.JPanel DayCountLabelPanel;
    private javax.swing.JButton EXIT;
    private javax.swing.JButton G1;
    private javax.swing.JLabel G1F;
    private javax.swing.JButton G2;
    private javax.swing.JLabel G2F;
    private javax.swing.JButton G3;
    private javax.swing.JLabel G3F;
    private javax.swing.JButton G4;
    private javax.swing.JLabel G4F;
    private javax.swing.JButton S1;
    private javax.swing.JButton S10;
    private javax.swing.JLabel S10F;
    private javax.swing.JLabel S1F;
    private javax.swing.JButton S2;
    private javax.swing.JLabel S2F;
    private javax.swing.JButton S3;
    private javax.swing.JLabel S3F;
    private javax.swing.JButton S4;
    private javax.swing.JLabel S4F;
    private javax.swing.JButton S5;
    private javax.swing.JLabel S5F;
    private javax.swing.JButton S6;
    private javax.swing.JLabel S6F;
    private javax.swing.JButton S7;
    private javax.swing.JLabel S7F;
    private javax.swing.JButton S8;
    private javax.swing.JLabel S8F;
    private javax.swing.JButton S9;
    private javax.swing.JLabel S9F;
    private javax.swing.JTextArea ServerAndTables;
    private javax.swing.JButton T1;
    private javax.swing.JButton T10;
    private javax.swing.JLabel T10F;
    private javax.swing.JLabel T1F;
    private javax.swing.JButton T2;
    private javax.swing.JLabel T2F;
    private javax.swing.JButton T9;
    private javax.swing.JLabel T9F;
    private javax.swing.JButton W1;
    private javax.swing.JButton W2;
    private javax.swing.JButton close;
    private javax.swing.JLabel day1;
    private javax.swing.JLabel day2;
    private javax.swing.JLabel day3;
    private javax.swing.JLabel day4;
    private javax.swing.JLabel day5;
    private javax.swing.JLabel day6;
    private javax.swing.JLabel day7;
    private javax.swing.JLabel dayCountDate;
    private javax.swing.JLabel dayCountDate1;
    private javax.swing.JLabel dayCountDate2;
    private javax.swing.JLabel dayCountDay;
    private javax.swing.JLabel dayCountDay1;
    private javax.swing.JLabel dayCountDay2;
    private javax.swing.JLabel dayCountHead;
    private javax.swing.JLabel dayCountHead1;
    private javax.swing.JLabel dayCountHead2;
    private javax.swing.JLabel fourCourseCount1;
    private javax.swing.JLabel fourCourseCount10;
    private javax.swing.JLabel fourCourseCount11;
    private javax.swing.JLabel fourCourseCount12;
    private javax.swing.JLabel fourCourseCount13;
    private javax.swing.JLabel fourCourseCount14;
    private javax.swing.JLabel fourCourseCount2;
    private javax.swing.JLabel fourCourseCount3;
    private javax.swing.JLabel fourCourseCount4;
    private javax.swing.JLabel fourCourseCount5;
    private javax.swing.JLabel fourCourseCount6;
    private javax.swing.JLabel fourCourseCount7;
    private javax.swing.JLabel fourCourseCount8;
    private javax.swing.JLabel fourCourseCount9;
    private javax.swing.JLabel headCountLabel;
    private javax.swing.JLabel headCountLabel1;
    private javax.swing.JLabel headCountLabel2;
    private javax.swing.JLabel headCountLabel3;
    private javax.swing.JLabel headCountLabel4;
    private javax.swing.JLabel headCountLabel5;
    private javax.swing.JLabel headCountLabel6;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lunchheadCountLabel;
    private javax.swing.JLabel lunchheadCountLabel1;
    private javax.swing.JLabel lunchheadCountLabel2;
    private javax.swing.JLabel lunchheadCountLabel3;
    private javax.swing.JLabel lunchheadCountLabel4;
    private javax.swing.JLabel lunchheadCountLabel5;
    private javax.swing.JLabel lunchheadCountLabel6;
    // End of variables declaration//GEN-END:variables

    
   
    private Storage tableStorage = new Storage();
    private int nightMenuDollarMore = 0;
    private Calendar time;
    private int tableID=0;
    private int pplNum=0;
    private MyQuery myQ;
     private Calendar theTime;
    private JD1 selectWindow;
    private JD2 ordering;
    private FourCourseMenu1 fourCourseMenu;
    private ThreeCourseMenu1 threeCourseMenu1;
    private ThreeCourseMenu2 threeCourseMenu2;
    private JD optionJD;
    private PrintOptionPanel printOptionPanel;
    private LOGINNOW l;
    private String temp = "";
    private ArrayList<Object> temp1;
    private ArrayList<String> tname;
    private ArrayList<String> count;
    public boolean isOrderPanelOpen;
    private JD_App optionJD_app;
    private JsonSimple databasePrinterSetting;
    private Edit4course edit4course;
    private EDITDialog editDialog;
    private NewOrderPanel newOrderPanel;
    private SeperateCheckJD seperateCheckJD;
    private Coupon couponJD;
    private CakeInfoPanel cakeInfoPanel;
    private CakeOrderPan cakeOrderPan;
    private SettingPane settingPanel;
}

    class PaintStaticBars implements Runnable{
        public JPanel j;
        public MyQuery myQ;
        public ArrayList<Object> component;

        public PaintStaticBars(  ArrayList<Object> _component, MyQuery myq){
            component=_component;
            myQ = myq;
            j=(JPanel) _component.get(0);

        }
        public void run(){
            for(int i =1; i<366; i++){
                
                 j.add(new Bar(component,i,myQ));
                 j.validate();
                 j.repaint();
            }
            
        }
    }
    class Bar extends JPanel{
        int width = 3;
        int height = 179;
        Double max = 250.00;
        int data;
        int data2;
        int day;
        Double percentageData=0.00;
        Double percentageData2=0.00;
        public ArrayList<Object> component;
        public MyQuery myQ;
        Random rand = new Random();
        public Bar(ArrayList<Object> _component, int i, MyQuery myq){
            super();
            myQ = myq;
            day = i;
             // try to make this some kind of member variable
            //so you don't make a new random object every time
            //int between10and100 = rand.nextInt(91)+10;
            //data = between10and100 ;
            data = myQ.getYYearsMassHeadCount(0, i);
            data2= myQ.getYYearsMassHeadCount(1,i);
            component=_component;
            percentageData=((data*1.0)/max)*(height*1.0);
            percentageData2=((data2*1.0)/max)*(height*1.0);
            this.setPreferredSize(new Dimension(width,height));
            this.setBackground(Color.darkGray);
            this.setToolTipText("People:"+data);
            this.addMouseListener(new MouseAdapter() {
                private Color background;
                @Override
                public void mousePressed(MouseEvent e) {
                    setBorder(BorderFactory.createLineBorder(Color.white,1));
                    background=((JPanel) component.get(1)).getBackground();
                    ((JPanel) component.get(1)).setBackground(Color.green);
                    ((JLabel) component.get(2)).setText(""+myQ.getAllDaysInOneYearDateDBNoTimeFormat(0, day));
                    ((JLabel) component.get(3)).setText(""+myQ.getYearsDateDBDayEnglishNameFormat(0,day));
                    ((JLabel) component.get(4)).setText(""+data+"人");
                    ((JLabel) component.get(5)).setText(""+myQ.getAllDaysInOneYearDateDBNoTimeFormat(1, day));
                    ((JLabel) component.get(6)).setText(""+myQ.getYearsDateDBDayEnglishNameFormat(1,day));
                    ((JLabel) component.get(7)).setText(""+myQ.getYYearsMassHeadCount(1,day)+"人");
                    ((JLabel) component.get(8)).setText(""+myQ.getAllDaysInOneYearDateDBNoTimeFormat(2, day));
                    ((JLabel) component.get(9)).setText(""+myQ.getYearsDateDBDayEnglishNameFormat(2,day));
                    ((JLabel) component.get(10)).setText(""+myQ.getYYearsMassHeadCount(2,day)+"人");
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    setBorder(BorderFactory.createLineBorder(Color.black,0));
                    ((JPanel) component.get(1)).setBackground(background);
                    repaint();
                }
            });
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.PINK);
            g.fillRect(0, 179-percentageData2.intValue(), width, percentageData2.intValue());
            g.setColor(Color.green);
            g.fillRect(0, 179-percentageData.intValue(), width, percentageData.intValue());
             g.setColor(Color.white);
            g.fillRect(0, 108, width, 1);
            
        }
    }
    

