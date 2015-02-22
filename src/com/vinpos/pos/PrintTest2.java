package com.vinpos.pos;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.PrintServiceLookup;
import javax.print.PrintService;

public class PrintTest2 extends PrintTest {
  public static void printCheck2(String tableNum, String billheader,String orderNames,String noteList, boolean b, int ppl, String time ){
 
    String[] ordersLines = orderNames.split("\\n");
    String[] noteLines = noteList.split("\\n");
    
    PrinterJob pj = PrinterJob.getPrinterJob();
    //PrintService[] printers = PrinterJob.lookupPrintServices();
    //System.out.println("name:"+printers[0].getName()+" numbers of printer"+printers.length);
    PageFormat pf = pj.defaultPage();
    Paper paper = new Paper();
    //paper.setSize(3.15*72,1000*72);
   
    double margin = 0;
    paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2);
    //paper.setImageableArea(margin, margin, paper.getWidth(), paper.getHeight());
    pf.setPaper(paper);
    // print header first
    try {
     // print orders
        pj.setPrintable(new MyPrintable2(ordersLines,noteLines,b,tableNum,ppl, time), pf);
        pj.print();
        pj.print();
        for(int i =0; i<ordersLines.length; i++){
            //System.out.println(ordersLines[i]+"count:"+i);
            pj.setPrintable(new MyPrintableForSplitOrders(ordersLines[i],b,tableNum,ppl, time), pf);
            pj.print();
        }
    }catch (PrinterException e) {
        System.out.println(e);
    }
    
  
  }// end of printCheck2
  
}
class MyPrintable2 implements Printable {
  String[] str;
  String[] note;
  boolean isAddon;
  String tableNumber;
  Integer ppl;
  String time;
  String orderStr  = "";
  String seatStr ="";
  int charSpaceToBeDeleted =0;
  
  public MyPrintable2( String[] getStr,String[] getNote ,boolean b, String s, int p, String t)
  {     
     tableNumber = s;
     isAddon = b;
     str = getStr;
     ppl=p;
     time=t;
     note=getNote;
  }
  public int print(Graphics g, PageFormat pf, int pageIndex) {
    if (pageIndex != 0)
      return NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D) g;
    g2.setFont(new Font("Dialog", Font.ITALIC, 30));
    g2.setPaint(Color.black);
    if(!isAddon){
        g2.setFont(new Font("宋体", Font.ITALIC, 14));
        g2.drawString("台号：    人数:"+ppl+" 时间"+time,10,67);
        g2.setFont(new Font("Dialog", Font.ITALIC, 35));
        g2.drawString("   "+tableNumber,10,67);
    }
    else{
        g2.setFont(new Font("宋体", Font.PLAIN, 30));
        g2.drawString("  ＊ 加单 ＊ ",10,67);
        g2.setFont(new Font("宋体", Font.PLAIN, 15));
        g2.drawString("台号："+tableNumber+"  时间："+ time,10,87);
    }
    g2.setFont(new Font("Dialog", Font.BOLD, 15));

    
    int yPositionAfterTableNumber = 92;
    
    int i=10;
    for (String s:str){          
        // print the orders
        g2.setFont(new Font("宋体", Font.PLAIN, 15));
        g2.drawString(s, 10, yPositionAfterTableNumber+i);
        //print the seats
        i=i+25;// making space for the server list
    }
    g2.setFont(new Font("Dialog", Font.PLAIN, 15));
    // add plain line to the compact order to make the paper longer
    g2.drawString("                         . ", 0, 150); // try to hide the * behind the paper
    

    return PAGE_EXISTS;
  }
 
}  

class MyPrintableForSplitOrders implements Printable {
  String str;
  boolean isAddon;
  String tableNumber;
  Integer ppl;
  String time;
  String orderStr  = "";
  String seatStr ="";
  
  
  public MyPrintableForSplitOrders( String getStr,boolean b, String s, int p, String t)
  {     
     tableNumber = s;
     isAddon = b;
     str = getStr;
     ppl=p;
     time=t;
     
  }
  public int print(Graphics g, PageFormat pf, int pageIndex) {
    if (pageIndex != 0)
      return NO_SUCH_PAGE;
    Graphics2D g2 = (Graphics2D) g;
    g2.setFont(new Font("Dialog", Font.ITALIC, 30));
    g2.setPaint(Color.black);
    if(!isAddon){
        g2.setFont(new Font("Dialog", Font.ITALIC, 30));
        g2.drawString(""+tableNumber,10,67);
        g2.setFont(new Font("Dialog", Font.ITALIC, 18));
        g2.drawString(""+time,90,67);
    }
    else{
        g2.setFont(new Font("宋体", Font.PLAIN, 30));
        g2.drawString(tableNumber+"＊加单 ＊ ",10,67);
        
    }
    g2.setFont(new Font("Dialog", Font.BOLD, 15));

    orderStr = str;
    int yPositionAfterTableNumber = 105;   // set initial order name y position after header
    g2.setFont(new Font("宋体", Font.PLAIN, 25));
    g2.drawString(orderStr, 10, yPositionAfterTableNumber);                                 
    
       
    g2.setFont(new Font("Dialog", Font.PLAIN, 15));

    // add plain line to the compact order to make the paper longer
    g2.drawString("                         . ", 0, 130); // try to hide the * behind the paper
    

    return PAGE_EXISTS;
  }

  
}






