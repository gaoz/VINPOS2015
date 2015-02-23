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
import javax.swing.JTextArea;

public class PrintTest extends DeleteFile {

  public static void printCheckWithArray(String[] s){
    
    PrinterJob pj = PrinterJob.getPrinterJob();

    PageFormat pf = pj.defaultPage();
    Paper paper = new Paper();
    double margin = 9;
    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight()
        + margin );
    pf.setPaper(paper);

    pj.setPrintable(new MyPrintable(s), pf);
    //if (pj.printDialog()) {
      try {
        pj.print();
      } catch (PrinterException e) {
        System.out.println(e);
     // }
    }
  }//end of printcheckwitharrylist
      public static void writeTextAreaForPrintjob(JTextArea jta, String fileName){
       FileWriter writer = null;
        try {
            writer = new FileWriter(fileName);
        //writer = new FileWriter("C:\\JJTEMP\\"+fileName);
        jta.write(writer);
        } catch (IOException exception) {
        System.err.println("Can't Save oops");
        exception.printStackTrace();
        } finally {
        if (writer != null) {
            try {
            writer.close();
            } catch (IOException exception) {
            System.err.println("Error closing writer");
            exception.printStackTrace();
            }
        }
        }
    }
       public static  ArrayList<String> loadTextAreaForPrintjob(String s){
        ArrayList<String> textarea1 = new ArrayList<String>();
      
        try 
            { 
                
                String strLine; 
                File f = new File(s);
                  //File f = new File("C:\\JJTEMP\\"+s);
                 BufferedReader br = new BufferedReader(new FileReader(f)); 
 
                while((strLine = br.readLine()) != null) 
                { 
                    textarea1.add(strLine+ "\n"); 
 
                    //System.out.println(strLine); 
 
                } 
            } 
            catch(Exception e) 
            { 
                System.err.println("Error: " + e.getMessage()); 
            } 
    return textarea1;
    }
}

class MyPrintable implements Printable {
  String[] str;
  public MyPrintable(String[] getStr)
  {
     str = getStr;
  }
  public int print(Graphics g, PageFormat pf, int pageIndex) {
    if (pageIndex != 0)
      return NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D) g;
    //header
    BufferedImage img = null;
    try {
        img = ImageIO.read(new File("logo2.jpg"));
        
    } catch (IOException e) {
        System.out.print("PrintTest.java logo for printer not found");
    }

   g2.drawImage(img, -40, -80, null);
   //
   g2.setFont(new Font("Dialog", Font.BOLD, 10));
   g2.fillRect(0, 1, 126, 2); // top rect line
   g2.fillRect(0, 2, 1, 76);  // left rect line
   g2.fillRect(0, 76, 126, 2);// bot rect line
   g2.drawString("JJ French Pastry & Bistro",2,12);
   g2.drawString("3347 Waialae",2,22);
   g2.drawString("Honolulu Hi 96816",2,32);
   g2.drawString("Tel:(808)739-0993",2,42);
   g2.drawString("Fax:(323)454-7088",2,52);
   g2.drawString("Mon-Sat:10-9/Sun:12-9",2,62);
   g2.drawString("www.jjfrenchpastry.com",2,72);
    
    
    
    
    
    //end of header
    g2.setFont(new Font("Dialog", Font.BOLD, 10));
    g2.setPaint(Color.black);
    int i=10;
    for (String s:str){
        if(s.contains("Total:")&&!s.contains("SubTotal:")){
            g2.setFont(new Font("Dialog", Font.BOLD, 20));
            g2.drawString(s, 10, 80+i+9);i=i+20;
            g2.setFont(new Font("Dialog", Font.BOLD, 10));
        }else{
            g2.drawString(s, 10, 80+i);i=i+11; 
        }
    }
    
    //Rectangle2D outline = new Rectangle2D.Double(pf.getImageableX(), pf.getImageableY(), pf
    //    .getImageableWidth(), pf.getImageableHeight());
    //g2.draw(outline);
    return PAGE_EXISTS;
  }
  
}

/*

import java.awt.print.*;
import javax.swing.JTextArea;


public class PrintTest extends Storage2{ 
                private static volatile boolean complete = false;         
                private static volatile String message;
    public static void printCheck(String p){
        JTextArea j = new JTextArea();
        j.setText(p);
        try{
            j.print(null, null, true, null, null, false);
          
        }
        catch (PrinterException ex) {
                message = "Sorry, a printer error occurred";
        }catch (SecurityException ex) {
                message =
                    "Sorry, cannot access the printer due to security reasons";
         }
        
    }
}

*/
