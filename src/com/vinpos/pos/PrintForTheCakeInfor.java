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
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.print.PrintService;
import javax.swing.JTextArea;

public class PrintForTheCakeInfor {//extends PrintForCustomerCakeOrder {
  public static void printForTheCakeInfor(String[] orders){
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
    pj.setPrintable(new MyPrintable5(orders), pf);
      try {
        pj.print();
      } catch (PrinterException e) {
        System.out.println(e);
    }
  }// end of printCheck2
  
  
  
  
}// end of class

class MyPrintable5 implements Printable {
  String[] str;
  public MyPrintable5( String[] getStr)
  {
     str = getStr;
  }
  public int print(Graphics g, PageFormat pf, int pageIndex) {
    if (pageIndex != 0)
      return NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D) g;
    
    //logo
    //BufferedImage img = null;
    //try {
        //img = ImageIO.read(new File("logo2.jpg"));  //g2.drawImage(img, -40, -80, null);
        
    //} catch (IOException e) {
        System.out.print("filenotfound");
    //}
      int yPositionAfterTableNumber = 0; 
    // store imformation
      int logoY = yPositionAfterTableNumber;
                  
      
      g2.setFont(new Font("Edwardian Script ITC", Font.PLAIN, 30));
      g2.drawString("JJ French Bistro"                             ,10,logoY+28);
      
      g2.setFont(new Font("Dialog", Font.BOLD, 10));
      
      g2.drawString("  ",                                           2,logoY+32);
      g2.drawString("3447 Waialae Honolulu Hi 96816"              ,20,logoY+42);
      g2.drawString("Tel:(808)739-0993   Fax:(323)454-7088"        ,8,logoY+52);
      g2.drawString("Mon-Sat:10-9/Sun:12-9"                       ,45,logoY+62);
      g2.drawString("www.jjfrenchBistro.com"                      ,43,logoY+72);
      
      g2.fillRect(0,  logoY+1, 200, 2); // top rect line
      g2.fillRect(0,  logoY+2, 1, 76);  // left rect line
      g2.fillRect(200,  logoY+2, 1, 76);  // right rect line
      g2.fillRect(0,  logoY+=76, 200,2);// bot rect line
      
      yPositionAfterTableNumber=logoY;
    // pick up string
    
    // print the text
     g2.setFont(new Font("Dialog", Font.PLAIN, 14));
     for(int i=0; i<str.length; i++){
         g2.drawString(str[i], 0, yPositionAfterTableNumber+=14);
     }
    // extend the paper
    g2.drawString(".", 0, yPositionAfterTableNumber+=30); // extend the space of the check

    
    

    return PAGE_EXISTS;
  }// end of print
  
}// end of MyPrintable3




