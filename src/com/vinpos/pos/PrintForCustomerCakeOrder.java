package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.print.PrintService;

public class PrintForCustomerCakeOrder{// extends PrintTest3 {
  public static void printCakeOrderForCustomer(String[] orders){
    PrinterJob pj = PrinterJob.getPrinterJob();
    PrintService[] printers = PrinterJob.lookupPrintServices();

        for(int i = 0; i<printers.length;i++){  //loop and assign the right printers to the right printerjobs
            
            if(printers[i].getName().equals("TSP143")){
                
                try{
                    pj.setPrintService(printers[i]);
                        
                }catch(PrinterException e){
                    
                    Msg jd = new Msg("station1 Printer is not working:"+e);
                       
                }    
            }
        }
    PageFormat pf = pj.defaultPage();
    Paper paper = new Paper();
    double margin = 9;
    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()
        + margin);
    pf.setPaper(paper);
    // print orders
    pj.setPrintable(new MyPrintable4(orders), pf);
      try {
        pj.print();
      } catch (PrinterException e) {
        System.out.println(e);
    }
  }// end of printCheck2
  
  
  
  
}// end of class

class MyPrintable4 implements Printable {
  String[] str;
  NumberFormat moneyVariable = NumberFormat.getCurrencyInstance(Locale.US);
  public MyPrintable4( String[] getStr)
  {
     str = getStr;
  }
  public int print(Graphics g, PageFormat pf, int pageIndex) {
    if (pageIndex != 0)
      return NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D) g;
    
    //logo
    BufferedImage img = null;
    try {
        img = ImageIO.read(new File("logo2.jpg"));
        
    } catch (IOException e) {
        System.out.print("filenotfound");
    }
    int yPositionAfterTableNumber = 0; 
    //logo
    int logoY = yPositionAfterTableNumber;
                
    g2.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
    g2.drawString("JJ French Bistro"                             ,10,logoY+28);
    
    g2.setFont(new Font("Dialog", Font.BOLD, 10));
    
    g2.drawString("  ",                                           2,logoY+32);
    g2.drawString("3447 Waialae Honolulu Hi 96816"              ,20,logoY+42);
    g2.drawString("Tel:(808)739-0993   Fax:(323)454-7088"        ,8,logoY+52);
    g2.drawString("Mon-Sat:10-9/Sun:12-9"                       ,45,logoY+62);
    g2.drawString("www.jjfrenchbistro.com"                      ,43,logoY+72);
    
    g2.fillRect(0,  logoY+1, 200, 2); // top rect line
    g2.fillRect(0,  logoY+2, 1, 76);  // left rect line
    g2.fillRect(200,  logoY+2, 1, 76);  // right rect line
    g2.fillRect(0,  logoY+=76, 200,2);// bot rect line
    
    yPositionAfterTableNumber=logoY;
    // pick up string
    g2.setFont(new Font("Dialog", Font.PLAIN, 15));
    g2.drawString("RECEIPT FOR PICK UP",15, yPositionAfterTableNumber+=25 ); 
    
    //Receipt Number
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    g2.drawString("Receipt No:."+str[10],22, yPositionAfterTableNumber+=25 ); 
    
    // date
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    g2.setPaint(Color.black);
    g2.drawString("Pick up at:", 0, yPositionAfterTableNumber+=15);          
    g2.setFont(new Font("Dialog", Font.BOLD, 12));
     g2.drawString(""+str[0]+" "+str[7]+" "+str[8], 0, yPositionAfterTableNumber+=15);     
     
     
    // cake name
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    g2.drawString("Cake name: ", 0, yPositionAfterTableNumber+=15);  
    g2.setFont(new Font("Dialog", Font.BOLD, 12));
    
    String sizeAndName=str[2];
    if(sizeAndName.length()>60 && sizeAndName.length()<90){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=15);
        g2.drawString(sizeAndName.substring(30,60), 0, yPositionAfterTableNumber+=15);
        g2.drawString(sizeAndName.substring(60,sizeAndName.length()), 0, yPositionAfterTableNumber+=15);
    }
    else if(sizeAndName.length()>30 && sizeAndName.length()<61){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=15);
        g2.drawString(sizeAndName.substring(30,sizeAndName.length()), 0, yPositionAfterTableNumber+=15);
    }
    else if(sizeAndName.length()<31){
        g2.drawString(sizeAndName.substring(0,sizeAndName.length()), 0, yPositionAfterTableNumber+=15);       // name
    }
    
    
    
    // cake size
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    g2.drawString("Cake size: ", 0, yPositionAfterTableNumber+=15);
    g2.setFont(new Font("Dialog", Font.BOLD, 12));
    sizeAndName=str[1];
    if(sizeAndName.length()>60 && sizeAndName.length()<90){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=15);
        g2.drawString(sizeAndName.substring(30,60), 0, yPositionAfterTableNumber+=15);
        g2.drawString(sizeAndName.substring(60,sizeAndName.length()), 0, yPositionAfterTableNumber+=15);
    }
    else if(sizeAndName.length()>30 && sizeAndName.length()<61){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=15);
        g2.drawString(sizeAndName.substring(30,sizeAndName.length()), 0, yPositionAfterTableNumber+=15);
    }
    else if(sizeAndName.length()<31){
        g2.drawString(sizeAndName.substring(0,sizeAndName.length()), 0, yPositionAfterTableNumber+=15);       // name
    }        
    
    
    // writing
    g2.setFont(new Font("Dialog", Font.BOLD, 12));
    g2.drawString("Cake writing: ", 0, yPositionAfterTableNumber+=15);
    sizeAndName=str[3];
    if(sizeAndName.length()>60 && sizeAndName.length()<90){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=15);
        g2.drawString(sizeAndName.substring(30,60), 0, yPositionAfterTableNumber+=15);
        g2.drawString(sizeAndName.substring(60,sizeAndName.length()), 0, yPositionAfterTableNumber+=15);
    }
    else if(sizeAndName.length()>30 && sizeAndName.length()<61){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=15);
        g2.drawString(sizeAndName.substring(30,sizeAndName.length()), 0, yPositionAfterTableNumber+=15);
    }
    else if(sizeAndName.length()<31){
        g2.drawString(sizeAndName.substring(0,sizeAndName.length()), 0, yPositionAfterTableNumber+=15);       // name
    }         // writing
    
    
    
    // contact info
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    g2.drawString("Contact Info: ", 0, yPositionAfterTableNumber+=15);   
    g2.drawString("Name:", 2, yPositionAfterTableNumber+=15); // contact name
    g2.setFont(new Font("Dialog", Font.BOLD, 12));
    g2.drawString(" "+str[4], 40, yPositionAfterTableNumber); 
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    g2.drawString("Phone:", 2, yPositionAfterTableNumber+=15);// contact number
    g2.setFont(new Font("Dialog", Font.BOLD, 12));
    g2.drawString(" "+str[5], 40, yPositionAfterTableNumber); 
    
    // total
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    g2.drawString("Total: ", 0, yPositionAfterTableNumber+=15); 
    
    int calculateSectionY = yPositionAfterTableNumber;
    
    g2.setFont(new Font("Dialog", Font.PLAIN, 10));
    g2.drawString("Cake Price:",   60, calculateSectionY+=15);
    g2.drawString(moneyVariable.format(Double.parseDouble(str[11])), 140, calculateSectionY);
     g2.drawString("Writing Charge:",   50, calculateSectionY+=15);
    g2.drawString(moneyVariable.format(Double.parseDouble(str[12])), 140, calculateSectionY);
    g2.drawString("4.712% Tax:", 57, calculateSectionY+=15);
    g2.drawString("+", 130, calculateSectionY);
    g2.drawString(moneyVariable.format(Double.parseDouble(str[13])), 140, calculateSectionY);

     //draw line
    g2.drawLine(118, calculateSectionY+=5, 190, calculateSectionY);

    //Final total
    g2.setFont(new Font("Dialog", Font.PLAIN, 20));
    g2.drawString("Total:", 20, calculateSectionY+=35);
    g2.setFont(new Font("Dialog", Font.BOLD, 30));
    g2.drawString(moneyVariable.format(Double.parseDouble(str[14])), 70, calculateSectionY);

    
    // paid button
    g2.setFont(new Font("Dialog", Font.PLAIN, 20));
    if(str[6].equals("Paid")){
        
         g2.drawString("Already Paid", 50, calculateSectionY+40); // paid
    }
    else{
        g2.drawString("Pay On Pickup", 40, calculateSectionY+40); // not paid
    }
    // extend the paper
    //g2.drawString(".", 0, yPositionAfterTableNumber+=30); // extend the space of the check

    
    

    return PAGE_EXISTS;
  }// end of print
  
}// end of MyPrintable3




