package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*; 
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author sd
 */
public class WriteFile extends LoadFile{
    private Calendar theTime;
    public void writeData(String filename)
   {  
      //File f = new File(filename);
      //FileWriter fw = null;
      try {
         //fw = new FileWriter(filename); 
         // = new FileWriter(filename);//"C:\\JJTEMP\\"+
         PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
         pw.println(toJSON2().toJSONString());
         pw.close();
      }  
      catch (IOException e) {
         JOptionPane.showMessageDialog(null, "IO Error. Unable to save json.", "Error", JOptionPane.ERROR_MESSAGE);
      } 
      //PrintWriter pw = new PrintWriter(fw,"UTF-8");
      
      
   }
   public void writeTextArea(JTextArea jta, String fileName){
       FileWriter writer = null;
        try {
        writer = new FileWriter("C:\\JJCHECKLIST\\"+fileName+".txt");
        jta.write(writer);
        } catch (IOException exception) {
        System.err.println("Can't Save textarea oops");
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
   public void writeReceiptNum(String s){
   
      File f = new File("C:\\JJTEMP\\Receipts.txt");
      FileWriter fw = null;
      try {
         fw = new FileWriter(f);
         
      }  
      catch (IOException e) {
         JOptionPane.showMessageDialog(null, "IO Error. Unable to save receipts.", "Error", JOptionPane.ERROR_MESSAGE);
      } 
      PrintWriter pw = new PrintWriter(fw);
      pw.println(s);
      
      pw.close();
      
   
   }
   public void writeTotalToJJTemp(Double s){
   
      File f = new File("C:\\JJTEMP\\Total.txt");
      FileWriter fw = null;
      try {
         fw = new FileWriter(f);
         
      }  
      catch (IOException e) {
         JOptionPane.showMessageDialog(null, "IO Error. Unable to save receipts.", "Error", JOptionPane.ERROR_MESSAGE);
      } 
      PrintWriter pw = new PrintWriter(fw);
      theTime = Calendar.getInstance();
      pw.println(""+s);
      pw.println(""+theTime.get(Calendar.DATE));
      pw.close();
      
   
   }
   
}
