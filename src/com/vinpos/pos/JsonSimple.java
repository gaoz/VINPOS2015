package com.vinpos.pos;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.Socket;
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
public class JsonSimple {
     public String database_IP;
     public String database_port;
     public String database_dbname;
     public String database_acc;
     public String database_pw;
     public String printerServerIP;
     public boolean isThisPrintServer;
     public boolean isThisPrintClient;
     public Socket clientSocket;
     public boolean isPrinterSocketOpen;
     public ArrayList<String> settingArray;
     public JsonSimple(){ 
        isPrinterSocketOpen = false;
        clientSocket=null;
         this.readConnectionSettingFile(); 
         
     }
     public void createSocket(){
        if(!isPrinterSocketOpen){

            
            try{
              
                    clientSocket = new Socket(database_IP, 6789);
                    isPrinterSocketOpen=true;
                
                
            }catch(Exception e){
                System.out.println("Cant create socket, Could not connect with current socket or ip: "+ database_IP);
                isPrinterSocketOpen=false;
            }
        }
         
     }
     public Socket giveMePrinterSocket(){
         return clientSocket;
     }
     public boolean isSocketReady(){
         return isPrinterSocketOpen;
     }
     public void closeSocket(){
         try{
                clientSocket.close();
                isPrinterSocketOpen=false;
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
          
                if(OSValidator.isMac()){
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
                }else if(OSValidator.isWindows()){
                    settingArray.add("127.0.0.1");
                    settingArray.add("3306");
                    settingArray.add("r");
                    settingArray.add("root");
                    settingArray.add("password");
                    settingArray.add("true");

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
                }
                else{
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
                }
                this.readConnectionSettingFile();
                
                
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
        //System.out.println("succesfully read connection setting file.\n");
      
     }
}
class OSValidator {
 
	private static String OS = System.getProperty("os.name").toLowerCase();
 
	/*public static void main(String[] args) {
 
		System.out.println(OS);
 
		if (isWindows()) {
			System.out.println("This is Windows");
		} else if (isMac()) {
			System.out.println("This is Mac");
		} else if (isUnix()) {
			System.out.println("This is Unix or Linux");
		} else if (isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}
	}*/
 
	public static boolean isWindows() {
 
		return (OS.indexOf("win") >= 0);
 
	}
 
	public static boolean isMac() {
 
		return (OS.indexOf("mac") >= 0);
 
	}
 
	public static boolean isUnix() {
 
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
 
	}
 
	public static boolean isSolaris() {
 
		return (OS.indexOf("sunos") >= 0);
 
	}
 
}