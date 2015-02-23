package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.JTextArea;



/**
 *
 * @author sd
 */

public class LoadFile extends Storage2 {
    private Storage1 namenprice;
    private Calendar theTime;
    public void readData(String fileName){
        JSONParser parser = new JSONParser();
 
	try {
                BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));//"C:\\JJTEMP\\"+
		Object obj = parser.parse(in);
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray msg = (JSONArray) jsonObject.get("Soup");
		Iterator<String> iterator = msg.iterator();
                JSONArray msg1 = (JSONArray) jsonObject.get("Appetizer");
		Iterator<String> iterator1 = msg1.iterator();
		JSONArray msg2 = (JSONArray) jsonObject.get("Entree");
		Iterator<String> iterator2 = msg2.iterator();
                JSONArray msg3 = (JSONArray) jsonObject.get("Seat");
		Iterator<String> iterator3 = msg3.iterator();
                JSONArray msg4 = (JSONArray) jsonObject.get("Note");
		Iterator<String> iterator4 = msg4.iterator();
                JSONArray msg5 = (JSONArray) jsonObject.get("Order");
		Iterator<String> iterator5 = msg5.iterator();
                JSONArray msg6 = (JSONArray) jsonObject.get("Price");
		Iterator<Double> iterator6 = msg6.iterator();
		while (iterator.hasNext()) {
			namenprice=new Storage1(iterator.next(),iterator1.next(),iterator2.next(),iterator3.next(),iterator4.next(),iterator5.next(),iterator6.next());
                        items.add(namenprice);
		}
                in.close();

	} catch (FileNotFoundException e) {
            
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	} catch (NullPointerException e){
                e.printStackTrace();
        }
        
 
    }
    public String loadReceiptNum(){
        File f = new File("C:\\JJTEMP\\Receipts.txt");
        FileReader fr =null;
        String receiptNum ="";
        try{
            fr = new FileReader(f);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            System.err.println("Can't load receipt.txt oops"+e.getMessage());
        }
        BufferedReader infile = new BufferedReader(fr);
        try{
                receiptNum=infile.readLine();
                
        }
        catch(IOException e){
            e.printStackTrace();
            System.err.println("receipt.txt has nth to read oops");
        }
        
        return receiptNum;
    }
    public String[] loadTotalFromJJTemp(){
        
        File f = new File("C:\\JJTEMP\\Total.txt");
        FileReader fr =null;
        String[] receiptNum = new String[2];
        try{
            fr = new FileReader(f);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            System.err.println("Can't load receipt.txt oops"+e.getMessage());
        }
        BufferedReader infile = new BufferedReader(fr);
        try{
            
            
                receiptNum[0]=infile.readLine();
                receiptNum[1]=infile.readLine();
            
        }
        catch(IOException e){
            e.printStackTrace();
            System.err.println("receipt.txt has nth to read oops");
        }
        theTime = Calendar.getInstance();
        if(Double.parseDouble(receiptNum[1])!=theTime.get(Calendar.DATE)){
            receiptNum[0]="0";
        }
        
        return receiptNum;
    }
    public JTextArea loadReceipt(String s){
        JTextArea textarea1 = new JTextArea();
        try 
            { 
                
                String strLine; 
                  File f = new File("C:\\JJCHECKLIST\\"+s+".txt");
                 BufferedReader br = new BufferedReader(new FileReader(f)); 
 
                while((strLine = br.readLine()) != null) 
                { 
                    textarea1.append(strLine + "\n"); 
 
                    System.out.println(strLine); 
 
                } 
            } 
            catch(Exception e) 
            { 
                System.err.println("Error: " + e.getMessage()); 
            } 
    return textarea1;
    }
   
    

}
    
    
    
    
    
    
    
    
   /*  public void readData(String filename)
    {
       ObjectMapper mapper = new ObjectMapper();
       Storage1hash hash = null;
       try {
          hash = 
             mapper.readValue(new File(filename),Storage1hash.class);
          
       }
       catch (IOException  e){
          JOptionPane.showMessageDialog(null, "Unable to read "+ filename);
       }
    
       
       items=(hash.get("orders"));
    
    }
}*/

