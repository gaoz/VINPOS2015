package com.vinpos.pos.print;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.vinpos.pos.*;
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

public class PrintForJJRegister{// extends PrintTest3 {
  public static void printRegisterOrderForCustomer(String[] orders){
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
  public String fillNull(String s){
      if(s==null){
          return "item A";
      }else if(s.equals("undefined")){
          return "item A";
      }else if(s.equals("")){
          return "item A";
      }else{
          return s;
      }
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
   
    // date
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    g2.setPaint(Color.black);
    g2.drawString("Pick up at:", 0, yPositionAfterTableNumber+=15);          
    g2.setFont(new Font("Dialog", Font.BOLD, 12));
    g2.drawString(""+str[0]+" "+str[7]+" "+str[8], 0, yPositionAfterTableNumber+=15);     
     
    
    int i=10;
    // loop 
    for (int j =1; j<str.length; j++){          
        // print the orders
        g2.setFont(new Font("Dialog", Font.PLAIN, 9));
        g2.drawString("1", 130, yPositionAfterTableNumber+i);
        //print the quantities
        j++;
        i=i+10;// making space for the server list
    }
    int i2=10;
    for (int j =1; j<str.length; j++){          
        // print the orders
        g2.setFont(new Font("宋体", Font.PLAIN, 9));
        g2.drawString(fillNull(str[j]), 0, yPositionAfterTableNumber+i2);
        //print the seats
        j++;
        i2=i2+10;// making space for the server list
    }
    int i3=10;
    for (int j =2; j<str.length; j++){          
        // print the order names
        g2.setFont(new Font("Dialog", Font.PLAIN, 9));
        
        g2.drawString(moneyVariable.format(fillNull(str[j])), 160, yPositionAfterTableNumber+i3);
        j++;
        //print the prices
        i3=i3+10;// making space for the server list
    } 
    
    //calculate the subtotal first
    long subTotal = 0;
        for (int j =2; j<str.length; j++){   
            subTotal +=  Long.parseLong(str[j]);
        }
    
    // initial the new y position
    int calculateSectionY = i3+yPositionAfterTableNumber;

    g2.setFont(new Font("Dialog", Font.PLAIN, 10));
    g2.drawString("SubTotal:",   70, calculateSectionY+=15);
    g2.drawString(moneyVariable.format(subTotal), 140, calculateSectionY);
    g2.drawString(4.712+"% Tax:", 57, calculateSectionY+=15);
    g2.drawString("+", 130, calculateSectionY);
    g2.drawString(moneyVariable.format(subTotal*0.04712), 140, calculateSectionY);
    
    //draw line
    g2.drawLine(118, calculateSectionY+=5, 190, calculateSectionY);

    //Final total
    g2.setFont(new Font("Dialog", Font.PLAIN, 20));
    g2.drawString("Total:", 20, calculateSectionY+=35);
    g2.setFont(new Font("Dialog", Font.BOLD, 30));
    g2.drawString(moneyVariable.format(subTotal+subTotal*0.04712), 70, calculateSectionY);
    
    // extend the paper
    //g2.drawString(".", 0, yPositionAfterTableNumber+=30); // extend the space of the check

    
    

    return PAGE_EXISTS;
  }// end of print
  
}// end of MyPrintable3




