/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vinpos.connection;

/**
 *
 * @author jj
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jj
 */
class DummyThread extends Thread{
    String printerIP="";
    public Socket socket=null;
    public void setIp(String ip){
        printerIP=ip;
    }
  
    @Override
    public void run() {
       createSocket();
    }
    public void createSocket() {
        
        try{
              
            socket = new Socket(printerIP, 6789);
            System.out.println("socket ready:"+printerIP);
                
        }catch(Exception e){
            System.out.println("Cant create socket, Could not connect with current socket or ip: "+ printerIP);
                
        }
       
    }
}
public class PrintConn {
     public String database_IP;
     public String database_port;
     public String database_dbname;
     public String database_acc;
     public String database_pw;
     public String printerServerIP;
     public boolean isThisPrintServer;
     public boolean isThisPrintClient;
     public Socket clientSocket;
     public ArrayList<String> settingArray;
     public PrintConn(){ 

        //printerServerIP="192.168.1.116"; //default printer ip since same as database ip so use database ip
        //isThisPrintServer=false;
        //isThisPrintClient=false;
        clientSocket=null;
         this.readConnectionSettingFile(); 
         
     }
    
     public void createSocket(){
         DummyThread d = new DummyThread() ;
        d.setIp(database_IP);
        d.start() ;
        try {
            Thread.sleep(1000) ;
        } catch (InterruptedException e) {e.printStackTrace();}
        clientSocket=d.socket ;
     }
     public Socket giveMePrinterSocket(){
         return clientSocket;
     }

     public void closeSocket(){
         
         try{   
            if(clientSocket!=null)
                clientSocket.close();
         
         }catch(Exception e){
                System.out.println("Printer Socket cant close. Socket IP: "+ printerServerIP);
               
            }
         
     }
     public void setDatabaseIP(String s){
         database_IP=s;
     }
     public String getDatabaseIP(){
         return database_IP;
     }
     public void setDatabasePort(String s){
         database_port=s;
     }
     public String getDatabasePort(){
         return database_port;
     }
     public void setDatabaseDbname(String s){
         database_dbname=s;
     }
     public String getDatabaseDbname(){
         return database_dbname;
     }
     public void setDatabaseAccName(String s){
         database_acc=s;
     }
     public String getDatabaseAccName(){
         return database_acc;
     }
     public void setDatabaseAccPW(String s){
         database_pw=s;
     }
     public String getDatabaseAccPW(){
         return database_pw;
     }
     public void setPrinterServerIP(String s){
         printerServerIP=s;
     }
     public String getPrinterServerIP(String s){
         return printerServerIP;
     }
     
     public void setThisPrintServer(boolean s){
         isThisPrintServer=s;
     }
     public boolean getThisPrintServer(){
         return isThisPrintServer;
     }
     public void setThisPrintClient(boolean s){
         isThisPrintClient=s;
     }
     public boolean getThisPrintClient(boolean s){
         return isThisPrintClient;
     }
     
     public void writeJsonFile(ArrayList<Object> nametag, ArrayList<Object> value) {
 
	JSONObject obj = new JSONObject();
 	
	for(int i=0; i<nametag.size(); i++){
            obj.put(nametag.get(i), value.get(i));  // example (nametag=ip, value=192.168.1.2)
        }
 
	try {
 
                
                //f.getParentFile().mkdirs();  //this line for the situation that folder/folder/ConnectionSetting.json. no use with folder directary;
		//PrintWriter file = new PrintWriter("ConnectionSetting.json", "UTF-8");
                
		//file.write(obj.toJSONString());
		//file.flush();
		//file.close();
                
                byte dataToWrite[] = obj.toJSONString().getBytes();
                FileOutputStream out = new FileOutputStream("ConnectionSetting");
                out.write(dataToWrite);
                out.close();
 
	} catch (IOException e) {
                
		e.printStackTrace();
	}
 
 
     }// end of write method
     public ArrayList<String> getAllSetting(){
         return settingArray;
     }
     
     public void readConnectionSettingFile() {
        settingArray= new ArrayList<String>();
	JSONParser parser = new JSONParser();
       
	try {
                //FileInputStream in = new FileInputStream("ConnectionSetting");
		//Object obj = parser.parse(new FileReader("ConnectionSetting.json"));
                Object myobj =parser.parse(new FileReader("ConnectionSetting"));
		JSONObject jsonObject = (JSONObject) myobj;
 
		String ip = (String) jsonObject.get("ip");
                String port = (String) jsonObject.get("port");
                String database = (String) jsonObject.get("database");
                String username = (String) jsonObject.get("username");
                String password = (String) jsonObject.get("password");
                String isSERVER = (String) jsonObject.get("isSERVER");
                
                database_IP=ip;
                database_port=port;
                database_dbname=database;
                database_acc=username;
                database_pw=password;
                isThisPrintServer=Boolean.parseBoolean(isSERVER);
                
                
                settingArray.add(ip);
                settingArray.add(port);
                settingArray.add(database);
                settingArray.add(username);
                settingArray.add(password);
                settingArray.add(isSERVER);
                
		//System.out.println("reading file:"+ip+" "+port+" "+database+" "+username+" "+password+"\n");
                

 
	} catch (FileNotFoundException e) {
                System.out.println("\nClass: JsonSimple.java");
                System.out.println("Fail to load \"ConnectionSetting.json\"."); 
                System.out.println("Auto initial c connection setting...\n");
          
                settingArray.add("192.168.1.199");
                settingArray.add("3306");
                settingArray.add("r");
                settingArray.add("root");
                settingArray.add("");
                settingArray.add("false");

                System.out.println("Default connection setting:\n"+"IP:"+settingArray.get(0)+"\nport:"+settingArray.get(1)+"\ndatabase:"+settingArray.get(2)+"\nusername:"+settingArray.get(3)+"\npassword:"+settingArray.get(4));
                
                //CREATE DEFAULT CONNECTIONSETTING.JSON
                ArrayList<Object> nametag =  new ArrayList<Object>();
                nametag.add("ip");
                nametag.add("port");
                nametag.add("database");
                nametag.add("username");
                nametag.add("password");
                nametag.add("isSERVER");

                ArrayList<Object> value =  new ArrayList<Object>();
                value.add(settingArray.get(0));
                value.add(settingArray.get(1));
                value.add(settingArray.get(2));
                value.add(settingArray.get(3));
                value.add(settingArray.get(4));
                value.add(settingArray.get(5));
                this.writeJsonFile(nametag, value);
                this.readConnectionSettingFile();

                
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
        //System.out.println("succesfully read connection setting file.\n");
      
     }
}
//
//example 
//        
//        JSONObject obj = new JSONObject();
//	obj.put("name", "mkyong.com");
//	obj.put("age", new Integer(100));
// 
//	JSONArray list = new JSONArray();
//	list.add("msg 1");
//	list.add("msg 2");
//	list.add("msg 3");
// 
//	obj.put("messages", list);
