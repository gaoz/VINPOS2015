package com.vinpos.pos;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinson
 */




import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.print.PrintService;







public class PrintCheckJJStyle {
    private ArrayList<String> SplitCheckArrayofName;
    private ArrayList<Integer> SplitCheckArrayofQuantity;
    public boolean hasFood = false;
    public boolean hasSeafood = false;
    public boolean hasDrink = false;
    public boolean hasDessert=false;
    
    
    private MyQuery myQ;
    
    private static int DEFAULT_PRINT =1;
    private static int PRINT_ALL     =2;
    private static int PRINT_SEAFOOD =3;
    private static int PRINT_FOOD    =4;
    private static int PRINT_DRINK   =5;
    private static int PRINT_DESSERT =6;
    
    public PrintCheckJJStyle(MyQuery myq_){
        myQ = myq_;
    }
    
    public void print(int tableID, int userID, int IwannaPrintLikeThis, MyQuery myQ_){
        
        
        PrinterJob station = PrinterJob.getPrinterJob();
        
        
        PrintService[] printers = PrinterJob.lookupPrintServices();

        for(int i = 0; i<printers.length;i++){  //loop and assign the right printers to the right printerjobs
            
            if(printers[i].getName().equals("TSP143")){
                
                try{
                    station.setPrintService(printers[i]);
                        
                }catch(PrinterException e){
                    
                    Msg jd = new Msg("station1 Printer is not working:"+e);
                       
                }    
            }
        }
        
        PageFormat pf_station = station.defaultPage();
        
        Paper paper = new Paper();
        
        paper.setImageableArea(0, 0,paper.getWidth(), paper.getHeight());

        pf_station.setPaper(paper);
        
        
        try {
        // print orders
            if(IwannaPrintLikeThis==DEFAULT_PRINT){
                
                //this one is for checking is the order already printed, 
                // No means to retrieve those order are not send, and then check if it is food or seafood.
                hasFood=myQ.hasFood(tableID, "NO");  
                hasSeafood = myQ.hasSeafood(tableID,"NO");
                
                
                if(hasFood){

                    // check the kitchen tick number
                    int foodNum =myQ.getFoodNumber();    // the 50 queue number for the chef to check the order of tickets.
                    myQ.IncreaseFoodNumber(foodNum);
                                                                                                          // first second third seafood drink dessert 
                    station.setPrintable(new MyPrintableForKitchen_(tableID,userID,foodNum,myQ,"PrintNotSend",true,true,true,false,false,false), pf_station);
                    station.print();

                }
                
                
                if(hasSeafood){

                    int SeaNum = myQ.getSeaFoodNumber();  // the 50 queue number for the chef to check the order of tickets.
                    myQ.IncreaseSeaFoodNumber(SeaNum);
                                                                                                          // first second third seafood drink dessert 
                    station.setPrintable(new MyPrintableForKitchen_(tableID,userID,SeaNum,myQ,"PrintNotSend",false,false,false,true,false,false), pf_station);
                    station.print();
                
                }
            }
            
            
            else if(IwannaPrintLikeThis==PRINT_ALL){
                
                hasFood=myQ.hasFoodWithoutSentCheck(tableID);  // print order to kitchen, disregard it is already sent or not. just print all of them 
                hasSeafood = myQ.hasSeafoodWithoutSentCheck(tableID);  // print order to kitchen, disregard it is already sent or not. just print all of them 
                
                
                if(hasFood){

                    // check the kitchen tick number
                    int foodNum =myQ.getFoodNumber();    // the 50 queue number for the chef to check the order of tickets.
                    myQ.IncreaseFoodNumber(foodNum);
                                                                                                          // first second third seafood drink dessert 
                    station.setPrintable(new MyPrintableForKitchen_(tableID,userID,foodNum, myQ,"PrintAll",true,true,true,false,false,false), pf_station);
                    station.print();
                    
                }
                
                
                if(hasSeafood){

                    int SeaNum = myQ.getSeaFoodNumber();  // the 50 queue number for the chef to check the order of tickets.
                    myQ.IncreaseSeaFoodNumber(SeaNum);
                                                                                                          // first second third seafood drink dessert 
                    station.setPrintable(new MyPrintableForKitchen_(tableID,userID,SeaNum, myQ,"PrintAll",false,false,false,true,false,false), pf_station);
                    station.print();
                
                }
            }else if(IwannaPrintLikeThis==PRINT_SEAFOOD){
                hasSeafood = myQ.hasSeafoodWithoutSentCheck(tableID);
                if(hasSeafood){

                    int SeaNum = myQ.getSeaFoodNumber();  // the 50 queue number for the chef to check the order of tickets.
                    myQ.IncreaseSeaFoodNumber(SeaNum);
                                                                                                          // first second third seafood drink dessert 
                    station.setPrintable(new MyPrintableForKitchen_(tableID,userID,SeaNum, myQ,"PrintAll",false,false,false,true,false,false), pf_station);
                    station.print();
                
                }
                
            }else if(IwannaPrintLikeThis==PRINT_FOOD){
                hasFood=myQ.hasFoodWithoutSentCheck(tableID);
                if(hasFood){

                    // check the kitchen tick number
                    int foodNum =myQ.getFoodNumber();    // the 50 queue number for the chef to check the order of tickets.
                    myQ.IncreaseFoodNumber(foodNum);
                                                                                                          // first second third seafood drink dessert 
                    station.setPrintable(new MyPrintableForKitchen_(tableID,userID,foodNum, myQ,"PrintAll",true,true,true,false,false,false), pf_station);
                    station.print();
                   
                }
            }else if(IwannaPrintLikeThis==PRINT_DRINK){
                hasDrink=myQ.hasDrinkWithoutSentCheck(tableID);
                
                if(hasDrink){

                    // check the kitchen tick number
                    int drinkNum =myQ.getDrinkNumber();    // the 50 queue number for the chef to check the order of tickets.
                    //myQ.IncreaseFoodNumber(drinkNum);
                                                                                                          // first second third seafood drink dessert 
                    station.setPrintable(new MyPrintableForKitchen_(tableID,userID,drinkNum, myQ,"PrintAll",false,false,false,false,true,false), pf_station);
                    station.print();

                }
                
            }else if(IwannaPrintLikeThis==PRINT_DESSERT){
                hasDessert=myQ.hasDessertWithoutSentCheck(tableID);
          
                if(hasDessert){

                    // check the kitchen tick number
                    int dessertNum =myQ.getDessertNumber();    // the 50 queue number for the chef to check the order of tickets.
                    //myQ.IncreaseFoodNumber(dessertNum);
                                                                                                          // first second third seafood drink dessert 
                    station.setPrintable(new MyPrintableForKitchen_(tableID,userID,dessertNum, myQ,"PrintAll",false,false,false,false,false,true), pf_station);
                    station.print();

                }
            }
            
            
        }catch (PrinterException e) {
            System.out.println(e);
        }


    }// end of print to kitchen
    
    public void printCheck(int tableID, int serverID, int checkID, int sid){
        PrinterJob station = PrinterJob.getPrinterJob();
        
        PrintService[] printers = PrinterJob.lookupPrintServices();

        for(int i = 0; i<printers.length;i++){  //loop and assign the right printers to the right printerjobs
            if(printers[i].getName().equals("TSP143") ){
                 try{
                    station.setPrintService(printers[i]);
                 }catch(PrinterException e){
                        Msg jd = new Msg("station1 Printer is not working:"+e);
                        System.out.print("station1 Printer is not working:"+e);
                 }    
                
            }    
        }
        PageFormat pf_station = station.defaultPage();
        
        Paper paper = new Paper();
        //paper.setSize(3.15*72,1000*72);
        //double margin = 0;
        paper.setImageableArea(0, 0,paper.getWidth(), paper.getHeight());

        pf_station.setPaper(paper);
        


        try {
        // print orders
            station.setPrintable(new CheckFormatOfPrintable_(tableID,serverID,checkID,sid), pf_station);
            
            station.print();
            
            
        }catch (PrinterException e) {
            System.out.println(e);
        }


    }// end of printCheck2
    


class MyPrintableForKitchen_ implements Printable {
        int tid =0;
        int aid =0;
        //private MyQuery myQ = new MyQuery();
        //private myConnection myconnection = new myConnection();
        private ArrayList<String> firstCourse;
        private ArrayList<String> secondCourse;
        private ArrayList<String> thirdCourse;
        private ArrayList<String> seafood;
        private ArrayList<String> drink;
        private ArrayList<String> dessert;
        
        private ArrayList<Integer> quantity_firstcourse;
        private ArrayList<Integer> quantity_secondcourse;
        private ArrayList<Integer> quantity_thirdcourse;
        private ArrayList<Integer> quantity_seafood;
        private ArrayList<Integer> quantity_drink;
        private ArrayList<Integer> quantity_dessert;
        
        private ArrayList<String> seat_firstcourse;
        private ArrayList<String> seat_secondcourse;
        private ArrayList<String> seat_thirdcourse;
        private ArrayList<String> seat_seafood;
        private ArrayList<String> seat_drink;
        private ArrayList<String> seat_dessert;
        
        private int ticketNum;
        private MyQuery myQ;
        private boolean isFirstCourse;
        private boolean isSecondCourse;
        private boolean isThirdCourse;
        private boolean isSeafood;
        private boolean isDrink;
        private boolean isDessert;
        private String printUnsendOrAllOption;
        

        public MyPrintableForKitchen_(int tableID, int serverID, int ticketOrderNum, MyQuery myQ_, String printSentOrNotSendOrAll_, boolean isFirstCourse_, boolean isSecondCourse_, boolean isThirdCourse_, boolean isSeafood_, boolean isDrink_, boolean isDessert_ )
        {     
            tid = tableID;
            aid = serverID;
            ticketNum=ticketOrderNum;
            myQ = myQ_;
            isFirstCourse= isFirstCourse_;
            isSecondCourse=isSecondCourse_;
            isThirdCourse=isThirdCourse_;
            isSeafood=isSeafood_;
            isDrink=isDrink_;
            isDessert=isDessert_;
            printUnsendOrAllOption=printSentOrNotSendOrAll_;
            
            
        }
        public int print(Graphics g, PageFormat pf, int pageIndex) {
            if (pageIndex != 0)
            return NO_SUCH_PAGE;
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(Color.black);
            int header =100;
            g2.setFont(new Font("Dialog", Font.PLAIN, 10));   g2.drawString ("----"+myQ.getServerName(aid)+"----",60,header-18);
            int yPositionAfterTableNumber = 110;
            
            
            // get new orders sorted for ready to print
            
            /////////////////////////////////////////////////////////////////////////
            //if(isFood)
            //else if(isSeafood)
            //else if(isDrink)
            //else if(isDessert)
            /////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////

            
            if(isFirstCourse && isSecondCourse && isThirdCourse){
                
                /*******************************************************************
                * first course printing
                ******************************************************************/
                this.get1st2rd3thAndSeafoodOrders(tid, printUnsendOrAllOption);
                
                g2.setFont(new Font("Dialog", Font.BOLD, 35));  g2.drawString    (myQ.getTableName(tid),0,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 18));  g2.drawString("("+myQ.getTablePPL(tid)+")",58,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 18));   g2.drawString         (this.getTime(),88,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 35));   g2.drawString         (ticketNum+"",150,header);

                int i=10;
                for (int j =0; j<quantity_firstcourse.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 16));

                    String string = "";        
                    if(quantity_firstcourse.get(j)!=1 && quantity_firstcourse.get(j)!=0 ){  // try to not print when quantity_firstcourse is 1 or 0 only.

                        string = quantity_firstcourse.get(j)+"";
                    }

                    g2.drawString(string, 0, yPositionAfterTableNumber+i);
                    //print the seats
                    i=i+16;// making space
                }
                int ii=10;
                for (int j =0; j<quantity_firstcourse.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.BOLD, 16));
                    g2.drawString(firstCourse.get(j).toString(), 25, yPositionAfterTableNumber+ii);
                    //print the seats
                    ii=ii+16;// making space 
                }

                int iii=10;
                for (int j =0; j<quantity_firstcourse.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 12));

                    int count = seat_firstcourse.get(j).toString().length();
                    int seatSize = count*12;
                    int xPosition =180;
                    int orderName =firstCourse.get(j).toString().length()*15;
                    int paperWith =190;

                    while(count>4){
                        count-=3;
                        xPosition-=15;

                        if(xPosition<orderName || xPosition<75){
                            g2.setFont(new Font("Dialog", Font.PLAIN, 9));
                            break;
                        }
                    }
                //print the seats except those isnot assigned seat number, prevent printing 0 for seat number
                    if(!seat_firstcourse.get(j).toString().equals("[0]")){
                           g2.drawString(seat_firstcourse.get(j).toString(), xPosition, yPositionAfterTableNumber+iii);
                    } 

                    iii=iii+16;// making space 
                }
                /*******************************************************************
                * second course printing
                ******************************************************************/
                yPositionAfterTableNumber+=i;
                int i2=10;
                for (int j =0; j<quantity_secondcourse.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 16));

                    String string = "";        
                    if(quantity_secondcourse.get(j)!=1 && quantity_secondcourse.get(j)!=0 ){  // try to not print when quantity_firstcourse is 1 or 0 only.

                        string = quantity_secondcourse.get(j)+"";
                    }

                    g2.drawString(string, 0, yPositionAfterTableNumber+i2);
                    //print the seats
                    i2=i2+16;// making space
                }


                int ii2=10;
                for (int j =0; j<quantity_secondcourse.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.BOLD, 16));
                    g2.drawString(secondCourse.get(j).toString(), 35, yPositionAfterTableNumber+ii2);
                    //print the seats
                    ii2=ii2+16;// making space 
                }


                int iii2=10;
                for (int j =0; j<quantity_secondcourse.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 12));

                    int count = seat_secondcourse.get(j).toString().length();
                    int seatSize = count*12;
                    int xPosition =180;
                    int orderName =secondCourse.get(j).toString().length()*15;
                    int paperWith =190;

                    while(count>4){
                        count-=3;
                        xPosition-=15;

                        if(xPosition<orderName || xPosition<65){
                            g2.setFont(new Font("Dialog", Font.PLAIN, 9));
                            break;
                        }
                    }
                if(!seat_secondcourse.get(j).toString().equals("[0]")){
                           g2.drawString(seat_secondcourse.get(j).toString(), xPosition, yPositionAfterTableNumber+iii2);
                } 
                    //print the seats
                    iii2=iii2+16;// making space 
                }
                /*******************************************************************
                * Third course printing
                * 
                ******************************************************************/
                yPositionAfterTableNumber+=i2;
                int i3=10;
                for (int j =0; j<quantity_thirdcourse.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 16));

                    String string = "";        
                    if(quantity_thirdcourse.get(j)!=1 && quantity_thirdcourse.get(j)!=0 ){  // try to not print when quantity_firstcourse is 1 or 0 only.

                        string = quantity_thirdcourse.get(j)+"";
                    }

                    g2.drawString(string, 0, yPositionAfterTableNumber+i3);
                    //print the seats
                    i3=i3+16;// making space
                }


                int ii3=10;
                for (int j =0; j<quantity_thirdcourse.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.BOLD, 16));
                    g2.drawString(thirdCourse.get(j).toString(), 25, yPositionAfterTableNumber+ii3);
                    //print the seats
                    ii3=ii3+16;// making space 
                }


                int iii3=10;
                for (int j =0; j<quantity_thirdcourse.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 12));

                    int count = seat_thirdcourse.get(j).toString().length();
                    int seatSize = count*12;
                    int xPosition =180;
                    int orderName =thirdCourse.get(j).toString().length()*15;
                    int paperWith =190;

                    while(count>4){
                        count-=3;
                        xPosition-=15;

                        if(xPosition<orderName || xPosition<65){
                            g2.setFont(new Font("Dialog", Font.PLAIN, 9));
                            break;
                        }
                    }
                    if(!seat_thirdcourse.get(j).toString().equals("[0]")){
                        g2.drawString(seat_thirdcourse.get(j).toString(), xPosition, yPositionAfterTableNumber+iii3);
                    } 
                    //print the seats
                    iii3=iii3+16;// making space 
                }
            }// done of if(isFood)
            

        
        
            else if(isSeafood){     
                /*******************************************************************
                * seafood printing
                * 
                * 
                * 
                ******************************************************************/
                this.getSeafoodOrders(tid, printUnsendOrAllOption);
                
                
                g2.setFont(new Font("Dialog", Font.BOLD, 35));  g2.drawString    (myQ.getTableName(tid),0,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 18));  g2.drawString("("+myQ.getTablePPL(tid)+")",58,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 18));   g2.drawString         (this.getTime(),88,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 35));   g2.drawString         (ticketNum+"",150,header);

                int i4=10;
                for (int j =0; j<quantity_seafood.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 16));

                    String string = "";        
                    if(quantity_seafood.get(j)!=1 && quantity_seafood.get(j)!=0 ){  // try to not print when quantity_firstcourse is 1 or 0 only.

                        string = quantity_seafood.get(j)+"";
                    }

                    g2.drawString(string, 0, yPositionAfterTableNumber+i4);
                    //print the seats
                    i4=i4+16;// making space
                }


                int ii4=10;
                for (int j =0; j<quantity_seafood.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.BOLD, 16));
                    g2.drawString(seafood.get(j).toString(), 25, yPositionAfterTableNumber+ii4);
                    //print the seats
                    ii4=ii4+16;// making space 
                }


                int iii4=10;
                for (int j =0; j<quantity_seafood.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 12));

                    int count = seat_seafood.get(j).toString().length();
                    int seatSize = count*12;
                    int xPosition =180;
                    int orderName =seafood.get(j).toString().length()*15;
                    int paperWith =190;

                    while(count>4){
                        count-=3;
                        xPosition-=15;

                        if(xPosition<orderName || xPosition<65){
                            g2.setFont(new Font("Dialog", Font.PLAIN, 9));
                            break;
                        }
                    }

                    if(!seat_seafood.get(j).toString().equals("[0]")){
                        g2.drawString(seat_seafood.get(j).toString(), xPosition, yPositionAfterTableNumber+iii4);
                    }
                    iii4=iii4+16;// making space 
                }
            }// done of if(isSeafood)
            
            else if(isDrink){     
                /*******************************************************************
                * drink printing
                * 
                * 
                * 
                ******************************************************************/
                this.getDrinkOrders(tid, printUnsendOrAllOption);
                
                
                g2.setFont(new Font("Dialog", Font.BOLD, 35));  g2.drawString    (myQ.getTableName(tid),0,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 18));  g2.drawString("("+myQ.getTablePPL(tid)+")",58,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 18));   g2.drawString         (this.getTime(),88,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 35));   g2.drawString         (ticketNum+"",150,header);

                int i4=10;
                for (int j =0; j<quantity_drink.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 16));

                    String string = "";        
                    if(quantity_drink.get(j)!=1 && quantity_drink.get(j)!=0 ){  // try to not print when quantity_firstcourse is 1 or 0 only.

                        string = quantity_drink.get(j)+"";
                    }

                    g2.drawString(string, 0, yPositionAfterTableNumber+i4);
                    //print the seats
                    i4=i4+16;// making space
                }


                int ii4=10;
                for (int j =0; j<quantity_drink.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.BOLD, 16));
                    g2.drawString(drink.get(j).toString(), 25, yPositionAfterTableNumber+ii4);
                    //print the seats
                    ii4=ii4+16;// making space 
                }


                int iii4=10;
                for (int j =0; j<quantity_drink.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 12));

                    int count = seat_drink.get(j).toString().length();
                    int seatSize = count*12;
                    int xPosition =180;
                    int orderName =drink.get(j).toString().length()*15;
                    int paperWith =190;

                    while(count>4){
                        count-=3;
                        xPosition-=15;

                        if(xPosition<orderName || xPosition<65){
                            g2.setFont(new Font("Dialog", Font.PLAIN, 9));
                            break;
                        }
                    }

                    if(!seat_drink.get(j).toString().equals("[0]")){
                        g2.drawString(seat_drink.get(j).toString(), xPosition, yPositionAfterTableNumber+iii4);
                    }
                    iii4=iii4+16;// making space 
                }
            }// done of if(isDrink)
            
            else if(isDessert){     
                /*******************************************************************
                * dessert printing
                * 
                * 
                * 
                ******************************************************************/
                this.getDessertOrders(tid, printUnsendOrAllOption);
                
                
                g2.setFont(new Font("Dialog", Font.BOLD, 35));  g2.drawString    (myQ.getTableName(tid),0,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 18));  g2.drawString("("+myQ.getTablePPL(tid)+")",58,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 18));   g2.drawString         (this.getTime(),88,header);
                g2.setFont(new Font("Dialog", Font.PLAIN, 35));   g2.drawString         (ticketNum+"",150,header);

                int i4=10;
                for (int j =0; j<quantity_dessert.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 16));

                    String string = "";        
                    if(quantity_dessert.get(j)!=1 && quantity_dessert.get(j)!=0 ){  // try to not print when quantity_firstcourse is 1 or 0 only.

                        string = quantity_dessert.get(j)+"";
                    }

                    g2.drawString(string, 0, yPositionAfterTableNumber+i4);
                    //print the seats
                    i4=i4+16;// making space
                }


                int ii4=10;
                for (int j =0; j<quantity_dessert.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.BOLD, 16));
                    g2.drawString(dessert.get(j).toString(), 25, yPositionAfterTableNumber+ii4);
                    //print the seats
                    ii4=ii4+16;// making space 
                }


                int iii4=10;
                for (int j =0; j<quantity_dessert.size(); j++){          
                    // print the orders
                    g2.setFont(new Font("Dialog", Font.PLAIN, 12));

                    int count = seat_dessert.get(j).toString().length();
                    int seatSize = count*12;
                    int xPosition =180;
                    int orderName =dessert.get(j).toString().length()*15;
                    int paperWith =190;

                    while(count>4){
                        count-=3;
                        xPosition-=15;

                        if(xPosition<orderName || xPosition<65){
                            g2.setFont(new Font("Dialog", Font.PLAIN, 9));
                            break;
                        }
                    }

                    if(!seat_dessert.get(j).toString().equals("[0]")){
                        g2.drawString(seat_dessert.get(j).toString(), xPosition, yPositionAfterTableNumber+iii4);
                    }
                    iii4=iii4+16;// making space 
                }
            }// done of if(isDessert)
            
            
            /////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////
            
            
            
            
            
            // add plain line to the compact order to make the paper longer
            g2.setFont(new Font("Dialog", Font.PLAIN, 1));
            g2.drawString("            . ", 0, 150); // try to hide the * behind the paper


             return PAGE_EXISTS;
        }
        
        public void get1st2rd3thAndSeafoodOrders(int tid, String s){
            ArrayList<Object> temp = myQ.get1st2rd3thAndSeafoodOrders(tid, s);
            
            firstCourse                =   (ArrayList<String>  ) temp.get(0) ;
            secondCourse               =   (ArrayList<String>  ) temp.get(1) ;
            thirdCourse                =   (ArrayList<String>  ) temp.get(2) ;
            seafood                    =   (ArrayList<String>  ) temp.get(3) ;
            quantity_firstcourse       =   (ArrayList<Integer> ) temp.get(4) ;
            quantity_secondcourse      =   (ArrayList<Integer> ) temp.get(5) ;
            quantity_thirdcourse       =   (ArrayList<Integer> ) temp.get(6) ;
            quantity_seafood           =   (ArrayList<Integer> ) temp.get(7) ;
            seat_firstcourse           =   (ArrayList<String>  ) temp.get(8) ;
            seat_secondcourse          =   (ArrayList<String>  ) temp.get(9) ;
            seat_thirdcourse           =   (ArrayList<String>  ) temp.get(10);
            seat_seafood               =   (ArrayList<String>  ) temp.get(11);
            
//            for(String ss:seat_secondcourse){
//                System.out.println("seat:"+ss);
//            }
            
        }
        public void getSeafoodOrders(int tid, String s){
            ArrayList<Object> temp = myQ.getSeafoodOrders(tid, s);
            
            seafood                    =   (ArrayList<String>  ) temp.get(0) ;
            quantity_seafood           =   (ArrayList<Integer> ) temp.get(1) ;
            seat_seafood               =   (ArrayList<String>  ) temp.get(2);
            
        }
        public void getDrinkOrders(int tid, String s){
            ArrayList<Object> temp = myQ.getDrinkOrders(tid, s);
            
            drink                      =   (ArrayList<String>  ) temp.get(0) ;
            quantity_drink             =   (ArrayList<Integer> ) temp.get(1) ;
            seat_drink                 =   (ArrayList<String>  ) temp.get(2);
            
        }
        public void getDessertOrders(int tid, String s){
            ArrayList<Object> temp = myQ.getDessertOrders(tid, s);
            
            dessert                      =   (ArrayList<String>  ) temp.get(0) ;
            quantity_dessert             =   (ArrayList<Integer> ) temp.get(1) ;
            seat_dessert                 =   (ArrayList<String>  ) temp.get(2);
            
        }
        
        
        public String getTime(){
                Calendar theTime;
                NumberFormat myFormat = new DecimalFormat("#00");
                theTime = Calendar.getInstance();
                int d = theTime.get(Calendar.HOUR_OF_DAY);
                String a3=myFormat.format(d);
                int e = theTime.get(Calendar.MINUTE);
                String a4=myFormat.format(e);
                int f = theTime.get(Calendar.SECOND);
                String a5=myFormat.format(f);
                String dateTime= a3+":"+a4;
                return dateTime;
        }


}   //end of class





    class CheckFormatOfPrintable_ implements Printable 
    {

            int tid =0;    // table id
            int aid =0;   // account id
            int cid =0;  // check #
            int sid =0;  // subcheck

            private ArrayList<String> firstCourse;
            private ArrayList<Integer> quantity;
            private ArrayList<Double> sumPrice;
            NumberFormat myFormat = new DecimalFormat("#00");
            NumberFormat moneyVariable = NumberFormat.getCurrencyInstance(Locale.US);
            private Double State_Tax = 0.04712;

            public CheckFormatOfPrintable_(int tableID, int CurrentUser, int checkID , int subCheck)
            {     
                tid = tableID;
                aid = CurrentUser;
                cid = checkID;
                sid = subCheck;
            }
            public int print(Graphics g, PageFormat pf, int pageIndex) 
            {
                    if (pageIndex != 0)
                    return NO_SUCH_PAGE;
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setPaint(Color.black);
                    
                   
                    boolean isWholesale = false;
                    String wholeSaleAccountName = "";
                    if(tid==32){ isWholesale =true; wholeSaleAccountName="Formagio Wine Bar"; } 
                    else if(tid==34){ isWholesale =true; wholeSaleAccountName="Hbachi"; } 
                    
                    
                    
                    int header=-22;
                    
                    // CHECK
                    g2.setFont(new Font("Dialog", Font.BOLD, 40));
                    header+=40;
                    g2.drawString("CHECK",0, header+10);
                    g2.setFont(new Font("Dialog", Font.BOLD, 35));
                    g2.drawString(myQ.getTableName(tid),150,header+10);
                    // receipt #$
                    g2.setFont(new Font("宋体", Font.PLAIN, 10));
                    g2.drawString("Receipt#：", 2,header+23);g2.drawString(myQ.receiptNumberFormat(cid), 50,header+23);
                    // subcheck
                    g2.setFont(new Font("Dialog", Font.BOLD, 17));
                    g2.drawString("#"+sid,170,header+25);  // SUBCHECK#
                    //wholesale account name
                    if(isWholesale){
                        g2.setFont(new Font("Dialog", Font.BOLD, 8));
                        g2.drawString("Account:",0, header+50);
                        g2.setFont(new Font("Dialog", Font.PLAIN, 15));
                        g2.drawString(""+wholeSaleAccountName,50,header+=50);
                        
                    }
                    header+=25;
                    
                    // jj logo
                    
                    Image IMG =null;
                    URL myurl = this.getClass().getResource("images/logo2.jpg");
                    Toolkit tk = Toolkit.getDefaultToolkit();
                    IMG = tk.getImage(myurl);
                    
                    try{

                        //g2.drawImage(IMG, -40, -80, null);

                    }catch(NullPointerException e){
                        System.out.println("No image found :"+ e);
                    }
                    int logoY = header;
                    
                    g2.fillRect(0,  logoY+1, 200, 2); // top rect line
                    g2.fillRect(0,  logoY+2, 1, 76);  // left rect line
                    g2.fillRect(0,  logoY+76, 200,2);// bot rect line
                   // g2.fillRect(130, 2, 1, 76);  // mid rect line
                    g2.fillRect(200,  logoY+2, 1, 76);  // right rect line
                    
                    g2.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
                    g2.drawString("JJ French Bistro"                             ,10,logoY+28);
                    
                    g2.setFont(new Font("Dialog", Font.BOLD, 10));
                   
                    g2.drawString("  ",                                           2,logoY+32);
                    g2.drawString("3447 Waialae Honolulu Hi 96816"              ,20,logoY+42);
                    g2.drawString("Tel:(808)739-0993   Fax:(323)454-7088"        ,8,logoY+52);
                    g2.drawString("Mon-Sat:10-9/Sun:12-9"                       ,45,logoY+62);
                    g2.drawString("www.jjfrenchbistro.com"                      ,43,logoY+=72);
                    
                    header=logoY;
                    
                    // header content
                    g2.drawString("Table：", 0,header+30);g2.drawString(myQ.getTableName(tid), 30,header+30);
                    g2.drawString("Time：", 0,header+42);g2.drawString(myQ.getDateTimeInFriendlyFormat(), 30,header+42);
                    
                    //g2.drawString("卖单：", 130,header+30);g2.drawString(myQ.getServerName(aid), 160,header+30); 
                    //g2.drawString("开单：", 130,header+42);g2.drawString(myQ.getOpenCheckServerName(tid), 160,header+42);
                    // second line
                    g2.drawLine(0, header+50, 195, header+=50);
                    // header title
                    g2.setFont(new Font("Dialog", Font.BOLD, 10));
                    g2.drawString("Item", 3,header+15);
                    g2.drawString("Quantity", 110,header+15);
                    g2.drawString("Price", 160,header+15);
                    //orders
                    int yPositionAfterTableNumber = header+25;
                    // query 3 column first
                    quantity =myQ.getAllOrderQuantity(tid, sid);
                    firstCourse =myQ.getAllOrderName(tid,sid);
                    sumPrice = myQ.getPriceWithCalculatedTotalOnEachItemThatMoreThanOne(tid,sid);
                    int i=10;
                    // loop 
                    for (int j =0; j<quantity.size(); j++){          
                        // print the orders
                        g2.setFont(new Font("Dialog", Font.PLAIN, 9));
                        g2.drawString(""+quantity.get(j).toString(), 130, yPositionAfterTableNumber+i);
                        //print the seats
                        i=i+10;// making space for the server list
                    }
                    int i2=10;
                    for (int j =0; j<quantity.size(); j++){          
                        // print the orders
                        g2.setFont(new Font("宋体", Font.PLAIN, 9));
                        g2.drawString(firstCourse.get(j).toString(), 0, yPositionAfterTableNumber+i2);
                        //print the seats
                        i2=i2+10;// making space for the server list
                    }
                    int i3=10;
                    for (int j =0; j<quantity.size(); j++){          
                        // print the orders
                        g2.setFont(new Font("Dialog", Font.PLAIN, 9));
                        g2.drawString(moneyVariable.format(sumPrice.get(j)), 160, yPositionAfterTableNumber+i3);
                        //print the seats
                        i3=i3+10;// making space for the server list
                    }

                    // all calculation
                    
                    boolean isGroupon = myQ.isGroupon(tid,sid);
                    Double subTotal = myQ.calculateSubTotal(tid,sid);
                    Double tax = subTotal*State_Tax;
                    Double discount = myQ.getTableDiscount(tid,sid);
                    Double tip = myQ.getTableTips(tid,sid);
                    Double coupon = myQ.getTableCoupon(tid,sid);
                    Double grouponTotal = myQ.getGrouponTotal(tid, sid);
                    Double feast20PercentOff =0.00;
                    if (!isGroupon ){
                        //feast20PercentOff = myQ.getSeafood20PercentOffTotal(tid, sid); //take off 20% happy hour 
                    }
                    Double discounted_amount= (subTotal+tax)*discount;
                    Double total = subTotal + tax - discounted_amount;
                    Double tips = total*tip;
                    Double tippedTotal = total+tips;
                    Double couponedTotal = tippedTotal-coupon-feast20PercentOff-grouponTotal;
                    
                    String taxrate ="4.712";
                    
                    
                    if(isWholesale){
                        discount =0.4;
                        discounted_amount = subTotal*0.4;
                        tax = discounted_amount*0.005;
                        couponedTotal=subTotal+tax-discounted_amount;
                        taxrate = "0.500";
                    }

                    // initial the new y position
                    int calculateSectionY = i3+yPositionAfterTableNumber;

                    g2.setFont(new Font("Dialog", Font.PLAIN, 10));
                    g2.drawString("SubTotal:",   70, calculateSectionY+=15);
                    g2.drawString(moneyVariable.format(subTotal), 140, calculateSectionY);
                    g2.drawString(taxrate+"% Tax:", 57, calculateSectionY+=15);
                    g2.drawString("+", 130, calculateSectionY);
                    g2.drawString(moneyVariable.format(tax), 140, calculateSectionY);
                    //tip
                    if(tip!=0.00){
                        g2.drawString(myFormat.format(tip*100)+"% Service Charge:", 18, calculateSectionY+=15);
                        g2.drawString("+", 130, calculateSectionY);
                        g2.drawString(moneyVariable.format(tips),140, calculateSectionY);
                    }

                    //discount
                    String wholeSaleDiscount ="";
                    if(isWholesale){ wholeSaleDiscount= "WholeSale";}
                    if(discount!=0.00){
                        if(isWholesale){
                            g2.drawString(myFormat.format(discount*100)+"% WholeSale Discount:", 0, calculateSectionY+=15);
                            g2.drawString("-", 130, calculateSectionY);
                            g2.drawString(moneyVariable.format(discounted_amount),140, calculateSectionY);
                        }else{
                            g2.drawString(myFormat.format(discount*100)+"% Discount:", 48, calculateSectionY+=15);
                            g2.drawString("-", 130, calculateSectionY);
                            g2.drawString(moneyVariable.format(discounted_amount),140, calculateSectionY);
                        }
                    }

                    //Groupon discount
                    if(grouponTotal!=0.00){
                        g2.drawString("Groupon Discount:", 32, calculateSectionY+=15);
                        g2.setFont(new Font("Dialog", Font.PLAIN, 12));
                        g2.drawString("-", 130, calculateSectionY);
                        g2.setFont(new Font("Dialog", Font.PLAIN, 10));
                        g2.drawString(moneyVariable.format(grouponTotal),140, calculateSectionY);
                    }
                    //Coupon discount
                    if(coupon!=0.00){
                        g2.drawString("Coupon Discount:", 32, calculateSectionY+=15);
                        g2.setFont(new Font("Dialog", Font.PLAIN, 12));
                        g2.drawString("-", 130, calculateSectionY);
                        g2.setFont(new Font("Dialog", Font.PLAIN, 10));
                        g2.drawString(moneyVariable.format(coupon),140, calculateSectionY);
                    }
                    // 2013 aguest seafood feast happy hour 2-5pm. 20% off those seafood
                    if(feast20PercentOff!=0.00 && !isGroupon && coupon==0.00){
                        g2.drawString("Seafood Feast 20% off:", 5, calculateSectionY+=15);
                        g2.setFont(new Font("Dialog", Font.PLAIN, 12));
                        g2.drawString("-", 130, calculateSectionY);
                        g2.setFont(new Font("Dialog", Font.PLAIN, 10));
                        g2.drawString(moneyVariable.format(feast20PercentOff),140, calculateSectionY);
                    }
                    //draw line
                    g2.drawLine(118, calculateSectionY+=5, 190, calculateSectionY);

                    //Final total
                    g2.setFont(new Font("Dialog", Font.PLAIN, 20));
                    g2.drawString("Total:", 20, calculateSectionY+=35);
                    g2.setFont(new Font("Dialog", Font.BOLD, 30));
                    g2.drawString(moneyVariable.format(couponedTotal), 70, calculateSectionY);

                    // Discount included notices
                    int discountLabelSectionY = calculateSectionY;
                    if(discount!=0.00 && !isWholesale){
                        g2.setFont(new Font("Dialog", Font.PLAIN, 16));
                        g2.drawString("("+myFormat.format(discount*100)+"% Discount Is Included)",0,discountLabelSectionY+=20);
                    }
                    //initial endSectionY
                    int endSectionY =discountLabelSectionY;
                    //guitity guideline
                    if(!isGroupon && !isWholesale){
                        if(tip==0.00){
                            //draw a line
                            g2.drawLine(0, endSectionY+=15, 195, endSectionY);
                            g2.setFont(new Font("Dialog", Font.BOLD, 9));
                            g2.drawString("Tips guide:",70,endSectionY+=10);
                            g2.drawString("15% "+moneyVariable.format((total+discounted_amount)*0.15)+"   18% "+moneyVariable.format((total+discounted_amount)*0.18)+"   20% "+moneyVariable.format((total+discounted_amount)*0.20),20,endSectionY+=15);

                            //draw a line
                            g2.drawLine(0, endSectionY+=10, 195, endSectionY);

                        }
                    }
                    if(isWholesale){
                        
                        //draw a line
                        g2.drawLine(0, endSectionY+=15, 195, endSectionY);
                        g2.setFont(new Font("Dialog", Font.BOLD, 9));
                        g2.drawString("Signature:",70,endSectionY+=10);
                        g2.drawString("X: ",20,endSectionY+=30);

                        //draw a line
                        g2.drawLine(0, endSectionY+=20, 195, endSectionY);
                      
                        g2.setFont(new Font("Dialog", Font.BOLD, 8));
                        g2.drawString("Account:",0, endSectionY+10);
                        g2.setFont(new Font("Dialog", Font.PLAIN, 10));
                        g2.drawString(""+wholeSaleAccountName,50,endSectionY+=10);
                        
                    
                        //draw a line
                        g2.setFont(new Font("Dialog", Font.BOLD, 9));
                        g2.drawString("THANK YOU SO MUCH, HAVE A NICE DAY!",5, endSectionY+=40);
                    }

                    // tips included notices
                    int tipLabelSectionY = endSectionY;
                    if(tip!=0.00){
                        g2.setFont(new Font("Dialog", Font.PLAIN, 16));
                        g2.drawString("("+myFormat.format(tip*100)+"% Tips Already Included)",0,tipLabelSectionY+=20);
                    }

                    // groupon section
                    int grouponSectionY =tipLabelSectionY;
                    if(isGroupon){
                        
                        Image imgG =null;
                        
                        try {
                            URL myurlG = this.getClass().getResource("images/groupon.jpg");
                            myurlG = this.getClass().getResource("images/groupon.jpg");
                        
                            Toolkit tkG = Toolkit.getDefaultToolkit();
                            tkG = Toolkit.getDefaultToolkit();
                            
                            imgG = tkG.getImage(myurlG);
                            imgG = tkG.getImage(myurlG);
                            
                            g2.drawImage(imgG, 5, grouponSectionY+=12, null);
                            g2.drawImage(imgG, 5, grouponSectionY, null);
                            g2.drawImage(imgG, 5, grouponSectionY, null);
                           

                        } catch (NullPointerException e) {
                            
                            System.out.print("Groupon logo2 for printer not found"+e);
                        }

                        grouponSectionY+=85; // THE GROUPON LOGO HEIGHT
                        g2.setFont(new Font("Dialog", Font.BOLD, 14));
                        g2.drawString("Dear Groupon users &",0,grouponSectionY+=14);
                        g2.drawString("Dear HotDeal users:",0,grouponSectionY+=14);
                        g2.drawString("15% Tips",120,grouponSectionY+=14);
                        g2.drawString("will be automatically added in",0,grouponSectionY+=14);
                        g2.drawString("you bill.The tips and tax are",0,grouponSectionY+=14);
                        g2.drawString("based on the full amount of",0,grouponSectionY+=14);
                        g2.drawString("your bill (before discounts).",0,grouponSectionY+=14);
                        g2.drawString("Thank you so much for coming",0,grouponSectionY+=14);
                        g2.setFont(new Font("Dialog", Font.BOLD, 24));
                        g2.drawString("MAHALO",40,grouponSectionY+=24);
                        g2.setFont(new Font("Dialog", Font.PLAIN, 9));
                        g2.drawString("For staff only:"+moneyVariable.format(couponedTotal-tips)+"+"+moneyVariable.format(tips)+"="+moneyVariable.format(couponedTotal) ,0,grouponSectionY+=14);
                    }   
                return PAGE_EXISTS;
            }


    } // end of CheckFormatOfPrintable_ class
}// end of princheckjjstyle.java


