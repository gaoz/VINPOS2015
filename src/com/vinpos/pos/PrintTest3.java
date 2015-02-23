package com.vinpos.pos;



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
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.swing.JTextArea;

public class PrintTest3 extends PrintTest2 {
  public static void printCheck3(String[] orders){
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
    pj.setPrintable(new MyPrintable3(orders), pf);
      try {
        pj.print();
      } catch (PrinterException e) {
        System.out.println(e);
    }
  }// end of printCheck2
  
  
  
  
}// end of class

class MyPrintable3 implements Printable {
  String[] str;
  public MyPrintable3( String[] getStr)
  {
     str = getStr;
  }
  public int print(Graphics g, PageFormat pf, int pageIndex) {
    if (pageIndex != 0)
      return NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D) g;
    
   
    // week day
    
    g2.setFont(new Font("Dialog", Font.PLAIN, 20));
    g2.setPaint(Color.black);
    int yPositionAfterTableNumber = 0; 
              
    
    //Receipt Number  
    g2.setFont(new Font("Dialog", Font.PLAIN, 15));
    g2.drawString("Receipt No:."+str[10],22, yPositionAfterTableNumber+=15 ); 
    
    //date
    g2.drawRect(0, yPositionAfterTableNumber+=22, 115, 20);
    g2.drawString(str[0], 0, yPositionAfterTableNumber+=18);
    g2.drawString(str[7], 65, yPositionAfterTableNumber); 
    
    //time
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    g2.drawString(str[8], 130, yPositionAfterTableNumber);
    
    // cake name
    g2.setFont(new Font("Dialog", Font.BOLD, 12));
    
    String sizeAndName=str[2];
    if(sizeAndName.length()>60 && sizeAndName.length()<91){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=20);
        g2.drawString(sizeAndName.substring(30,60), 0, yPositionAfterTableNumber+=20);
        g2.drawString(sizeAndName.substring(60,sizeAndName.length()), 0, yPositionAfterTableNumber+=20);
    }
    else if(sizeAndName.length()>30 && sizeAndName.length()<61){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=20);
        g2.drawString(sizeAndName.substring(30,sizeAndName.length()), 0, yPositionAfterTableNumber+=20);
    }
    else if(sizeAndName.length()<31){
        g2.drawString(sizeAndName.substring(0,sizeAndName.length()), 0, yPositionAfterTableNumber+=20);       // size + name
    }
    //cake size
    sizeAndName=str[1];
    if(sizeAndName.length()>60 && sizeAndName.length()<91){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=20);
        g2.drawString(sizeAndName.substring(30,60), 0, yPositionAfterTableNumber+=20);
        g2.drawString(sizeAndName.substring(60,sizeAndName.length()), 0, yPositionAfterTableNumber+=20);
    }
    else if(sizeAndName.length()>30 && sizeAndName.length()<61){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=20);
        g2.drawString(sizeAndName.substring(30,sizeAndName.length()), 0, yPositionAfterTableNumber+=20);
    }
    else if(sizeAndName.length()<31){
        g2.drawString(sizeAndName.substring(0,sizeAndName.length()), 0, yPositionAfterTableNumber+=20);       // size + name
    }
    // writing
    sizeAndName=str[3];
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    if(sizeAndName.length()>60 && sizeAndName.length()<91){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=20);
        g2.drawString(sizeAndName.substring(30,60), 0, yPositionAfterTableNumber+=20);
        g2.drawString(sizeAndName.substring(60,sizeAndName.length()), 0, yPositionAfterTableNumber+=20);
    }
    else if(sizeAndName.length()>30 && sizeAndName.length()<61){
        g2.drawString(sizeAndName.substring(0,30), 0, yPositionAfterTableNumber+=20);
        g2.drawString(sizeAndName.substring(30,sizeAndName.length()), 0, yPositionAfterTableNumber+=20);
    }
    else if(sizeAndName.length()<31){
        g2.drawString(sizeAndName.substring(0,sizeAndName.length()), 0, yPositionAfterTableNumber+=20);       // size + name
    }        
    
    
    
    // contact number
    g2.drawRect(0, yPositionAfterTableNumber+=10, 115, 24);
    g2.drawRect(125, yPositionAfterTableNumber, 75, 24);
    g2.setFont(new Font("Dialog", Font.PLAIN, 10));
    g2.drawString(""+str[4], 2, yPositionAfterTableNumber+=10); // contact number
    g2.drawString(""+str[5], 2, yPositionAfterTableNumber+=10);
    
    // paid 
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    if(str[6].equals("Paid")){
        g2.drawString("Already Paid", 130, yPositionAfterTableNumber);
        //g2.fillRect(160, yPositionAfterTableNumber-10, 13, 13);
    }
    else{
        g2.drawString("Not Paid Yet", 130, yPositionAfterTableNumber);
        //g2.drawRect(160, yPositionAfterTableNumber-10, 13, 13);
    }
    // total
    g2.drawRect(0, yPositionAfterTableNumber+=10, 115, 20);
    g2.drawRect(125, yPositionAfterTableNumber, 75, 20);
    if(!str[9].equals("0.00")){
        g2.setFont(new Font("Dialog", Font.PLAIN, 8));
        g2.drawString(""+str[9], 2, yPositionAfterTableNumber+=15);
    }
    // server
    g2.setFont(new Font("Dialog", Font.PLAIN, 12));
    g2.drawString(str[16], 127, yPositionAfterTableNumber); // server name
    
    // note
    g2.drawRect(0, yPositionAfterTableNumber+=10, 200, 40);
    int y = yPositionAfterTableNumber-10;
    g2.setFont(new Font("Dialog", Font.PLAIN, 10));
    sizeAndName=str[15];
    System.out.printf("sizeAndName"+sizeAndName);
    if(sizeAndName.length()>81 ){
        g2.drawString(sizeAndName.substring(0,40), 5, yPositionAfterTableNumber+=10);
        g2.drawString(sizeAndName.substring(40,81), 5, yPositionAfterTableNumber+=10);
        g2.drawString(sizeAndName.substring(81,sizeAndName.length()), 5, yPositionAfterTableNumber+=10);
    }
    else if(sizeAndName.length()>40 && sizeAndName.length()<82){
        g2.drawString(sizeAndName.substring(0,40), 5, yPositionAfterTableNumber+=20);
        g2.drawString(sizeAndName.substring(40,sizeAndName.length()), 5, yPositionAfterTableNumber+=10);
    }
    else if(sizeAndName.length()<41){
        g2.drawString(sizeAndName.substring(0,sizeAndName.length()), 5, yPositionAfterTableNumber+=10);       //notes
    }          // writing
    
    
   
    // extend the paper
    //g2.drawString(".", 0, yPositionAfterTableNumber+=30); // extend the space of the check

    
    

    return PAGE_EXISTS;
  }// end of print
  
}// end of MyPrintable3




