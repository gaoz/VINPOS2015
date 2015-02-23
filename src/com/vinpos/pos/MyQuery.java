package com.vinpos.pos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinson
 */
public class MyQuery {
        private ArrayList<String> SplitCheckArrayofName;
        private ArrayList<Integer> SplitCheckArrayofQuantity;
        private ArrayList<String> name;
        private ArrayList<String> firstCourse;
        
        private ArrayList<Integer> quantity;
        private ArrayList<Double> price;
        private MyConnection myconnection;
        private Connection co;
        private static Double State_Tax = 0.04712;
        private NumberFormat moneyVariable = NumberFormat.getCurrencyInstance(Locale.US);
        private NumberFormat moneyformatnosign = new DecimalFormat("#.00");
        private Calendar theTime;
        String query;
        String insert;
        JsonSimple databasePrinterSettingFile;// this one is for general connection data without need to create socket.  PrintConn is for opening PrintSocket
        
        public MyQuery(JsonSimple settingFile){
            
            databasePrinterSettingFile = settingFile;
            try{ 
                co = myconnection.getConnection(databasePrinterSettingFile);

            }catch(NullPointerException e)
            { 
                System.out.println("cant create co at myqery constructer: ");        
                e.printStackTrace();
            }
            catch(Exception e)
            {
                System.out.println("cant create co at myqery constructer: ");
                e.printStackTrace();
            }

        }
 
        public void closeCo(){
            try{
                        myconnection.closeRs(rs_getTodaysHeadCount);
                        myconnection.closeRs(rs_getOpenedTableStatusAndTname);
                        myconnection.closeRs(rs_getdistinctAccountName);
                        myconnection.closeRs(rs_getServerAndTable);
                        myconnection.closeRs(rs3_getFourcrouseCount);
                        myconnection.closeRs(rs_getFourcrouseDayCount);
                        
                        myconnection.closePs(ps_getTodaysHeadCount6);
                        myconnection.closePs(ps_getTodaysHeadCount5);
                        myconnection.closePs(ps_getTodaysHeadCount4);
                        myconnection.closePs(ps_getTodaysHeadCount3);
                        myconnection.closePs(ps_getTodaysHeadCount2);
                        myconnection.closePs(ps_getTodaysHeadCount1);
                        myconnection.closePs(ps_getTodaysHeadCount0);
                        myconnection.closePs(ps_getdistinctAccountName);
                        myconnection.closePs(ps_getOpenedTableStatusAndTname);
                        myconnection.closePs(ps_getServerAndTable);
                        myconnection.closePs(ps3_getFourcrouseCount);
                      
                        
                        myconnection.closePs(ps_fourCourseDayCount6);
                        myconnection.closePs(ps_fourCourseDayCount5);
                        myconnection.closePs(ps_fourCourseDayCount4);
                        myconnection.closePs(ps_fourCourseDayCount3);
                        myconnection.closePs(ps_fourCourseDayCount2);
                        myconnection.closePs(ps_fourCourseDayCount1);
                        myconnection.closePs(ps_fourCourseDayCount0);

                        myconnection.closeConnection(co);
                
            }catch(NullPointerException e){         }catch(Exception e){System.out.println("cant close co at MyQery constructer");}
        }
        public JsonSimple getJsonSimpleClassObject(){
            return databasePrinterSettingFile;
        }
        public String getTableName(int tid) throws NullPointerException{

            String tname ="";
            try{
                
                query = "SELECT tname FROM R.table WHERE R.table.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    tname=rs.getString("tname");
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableName:"+e);
            }
            return tname;
        }
         public int getTableID(int oid) throws NullPointerException{

            int tid =0;
            try{
                
                query = "SELECT tid FROM R.order WHERE R.order.oid='"+oid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    tid=rs.getInt("tid");
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableID:"+e);
            }
            return tid;
        }

        public String getOpenCheckServerName(int tid) throws NullPointerException{

            String aname ="";
            try{
                
                query = "SELECT aname FROM R.account LEFT JOIN R.table ON R.account.aid=R.table.aid Where R.table.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    aname=rs.getString("aname");
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getOpenCheckServerName:"+e);
            }
            return aname;
        }

        public String getServerName(int aid) throws NullPointerException{

            String aname ="";
            try{
                
                query = "SELECT aname FROM R.account WHERE R.account.aid='"+aid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    aname=rs.getString("aname");
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getServerName:"+e);
            }
            return aname;
        }

        public ArrayList<String> getNewFirstCourse(int tid) throws NullPointerException{

            firstCourse= new ArrayList<String>();
            try{
                
                query = "SELECT name FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.firstCourse<>''";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    firstCourse.add(rs.getString("name"));
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getNewFirstCourse():"+e);
            }
            return firstCourse;
        }
        public ArrayList<String> getAllOrderName(int tid,int sid) throws NullPointerException{

            name= new ArrayList<String>();
            try{
                
                query = "SELECT name FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.subtable='"+sid+"'"; ;
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    name.add(rs.getString("name"));
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getAllOrderName():"+e);
            }
            return name;
        }
        
        public ArrayList<Integer> getNewQuantity(int tid) throws NullPointerException{

            quantity= new ArrayList<Integer>();
            try{
                
                query = "SELECT quantity FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.firstCourse<>''";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    quantity.add(rs.getInt("quantity"));
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getNewQuantity():"+e);
            }
            return quantity;
        }
        public ArrayList<Integer> getAllOrderQuantity(int tid, int sid) throws NullPointerException{

            quantity= new ArrayList<Integer>();
            try{
                
                query = "SELECT quantity FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.subtable='"+sid+"'"; 
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    quantity.add(rs.getInt("quantity"));
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getAllOrderQuantity():"+e);
            }
            return quantity;
        }
        public ArrayList<Double> getAllOrderPrice(int tid, int sid) throws NullPointerException{

            price= new ArrayList<Double>();
            try{
                
                query = "SELECT price FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.subtable='"+sid+"'"; 
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    price.add(rs.getDouble("price"));
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getAllOrderPrice():"+e);
            }
            return price;
        } 
        public ArrayList<Double> getPriceWithCalculatedTotalOnEachItemThatMoreThanOne(int tid, int sid) throws NullPointerException{
            quantity = this.getAllOrderQuantity(tid,sid);
            price = this.getAllOrderPrice(tid,sid);
            ArrayList<Double> sumPrice = new ArrayList<Double>();
            for(int i=0; i<quantity.size(); i++){
                Double p = (double) quantity.get(i)* price.get(i);
                sumPrice.add(p);
            }
            return sumPrice;
        }
        public Double calculateTotal(int tid, int sid) throws NullPointerException{
            Double State_Tax = 0.04712;
            Double subTotal = this.calculateSubTotal(tid, sid);
            Double tax = subTotal*State_Tax;
            Double discount_percent = this.getTableDiscount(tid,sid);
            Double service_charge_percent = this.getTableTips(tid,sid);

            Double discounted_amount= (subTotal+tax)*discount_percent; 

            Double total = subTotal + tax - discounted_amount;

            Double tips = total*service_charge_percent;

            Double TippedTotal = total + tips;

            return TippedTotal;
        }
        
        public Double getTableTotal(int tid) throws NullPointerException{
  
            Double value =0.00;
            try{
                
                query = "SELECT total FROM R.table WHERE R.table.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    value=rs.getDouble("total");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableTotal:"+e);
            }

            return value;
        
        }
        public void setTableCoupon(int tid, Double p, int sid) throws NullPointerException{
            
            try{
                
                query = "UPDATE R.order SET R.order.coupon= ? WHERE R.order.tid=? AND R.order.subtable= ?";  
               
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, ""+p);
                ps.setString(2, ""+tid);
                ps.setString(3, ""+sid);
                
                ps.executeUpdate();
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! setTableCoupon():"+e);
            }
        }
        public Double getTableCoupon(int tid, int sid) throws NullPointerException{
  
            Double value =0.00;
            try{
                
                query = "SELECT coupon FROM R.order WHERE R.order.tid=? AND R.order.subtable=?";
                PreparedStatement ps = co.prepareStatement(query);
                
                ps.setString(1, ""+tid);
                ps.setString(2, ""+sid);
                
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    value=rs.getDouble("coupon");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableCoupon:"+e);
            }

            return value;
        
        }
        
        public Double getTableTax(int tid) throws NullPointerException{
  
            Double value =0.00;
            try{
                
                query = "SELECT tax FROM R.table WHERE R.table.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    value=rs.getDouble("tax");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableTax:"+e);
            }

            return value;

        }
        
        
        public Double getTableDiscount(int tid, int sid) throws NullPointerException{
  
            Double discount =0.00;
            try{
                
                query = "SELECT discount FROM R.order WHERE R.order.tid=? AND R.order.subtable=?";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1,""+tid);
                ps.setString(2,""+sid);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    discount=rs.getDouble("discount");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableDiscount:"+e);
            }

            return discount;

        }
        public Double getTableTips(int tid, int sid) throws NullPointerException{
  
            Double tips =0.00;
            try{
                
                query = "SELECT tip FROM R.order WHERE R.order.tid=? AND R.order.subtable=?";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1,""+tid);
                ps.setString(2,""+sid);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    tips=rs.getDouble("tip");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableTip"+e);
            }

            return tips;

        }
        public int getTablePPL(int tid) throws NullPointerException{
  
            int ppl=0;
            try{
                
                query = "SELECT ppl FROM R.table WHERE R.table.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    ppl=rs.getInt("ppl");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTablePPL"+e);
            }

            return ppl;

        }
        public Double calculateSubTotal(int tid,int sid) throws NullPointerException{
            Double value =0.00;
            try{
                
                query = "SELECT price, quantity FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.subtable='"+sid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    int quantity = rs.getInt("quantity");
                    Double price =rs.getDouble("price");
                    value += quantity*price;
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! calculateSubTotal:"+e);
            }

            return value;
        }
        public Double calculateSubTotal(int tableID) throws NullPointerException{
            Double value =0.00;
            try{
            
                query = "SELECT price, quantity FROM R.order WHERE R.order.tid='"+tableID+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    int quantity = rs.getInt("quantity");
                    Double price =rs.getDouble("price");
                    value += quantity*price;
                }
               ps=null;
               rs=null;
           



            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! calculateSubTotal:"+e);
            }

            return value;
        }


        public String getTime() throws NullPointerException{
                
                NumberFormat myFormat = new DecimalFormat("#00");
                theTime = Calendar.getInstance();
                int d = theTime.get(Calendar.HOUR_OF_DAY);
                String a3=myFormat.format(d);
                int e = theTime.get(Calendar.MINUTE);
                String a4=myFormat.format(e);
                int f = theTime.get(Calendar.SECOND);
                String a5=myFormat.format(f);
                String dateTime= a3+":"+a4;
                return dateTime;
        }
        public String getDateTimeInFriendlyFormat() throws NullPointerException{
                
                theTime = Calendar.getInstance();
                NumberFormat myFormat = new DecimalFormat("#00");
                int a = theTime.get(Calendar.YEAR);
                int b = theTime.get(Calendar.MONTH)+1;
                String a1 =myFormat.format(b);
                int c = theTime.get(Calendar.DATE);
                String a2=myFormat.format(c);
                int d = theTime.get(Calendar.HOUR_OF_DAY);
                String a3=myFormat.format(d);
                int e = theTime.get(Calendar.MINUTE);
                String a4=myFormat.format(e);
                String dateTime= a+"/"+a1+"/"+a2+" "+a3+":"+a4;

                return dateTime;
        
        }
        
        public int getWeekDayInFriendlyFormat() throws NullPointerException{
                
                theTime = Calendar.getInstance();
                return theTime.get(Calendar.DAY_OF_WEEK);
        
        }
        public String getDateTimeDBFormat() throws NullPointerException{
                
                theTime = Calendar.getInstance();
                NumberFormat myFormat = new DecimalFormat("#00");
                int a = theTime.get(Calendar.YEAR);
                int b = theTime.get(Calendar.MONTH)+1;
                String a1 =myFormat.format(b);
                int c = theTime.get(Calendar.DATE);
                String a2=myFormat.format(c);
                int d = theTime.get(Calendar.HOUR_OF_DAY);
                String a3=myFormat.format(d);
                int e = theTime.get(Calendar.MINUTE);
                String a4=myFormat.format(e);
                int f = theTime.get(Calendar.SECOND);
                String a5=myFormat.format(f);

                String dateTime= a+"-"+a1+"-"+a2+" "+a3+"-"+a4+"-"+a5;

                return dateTime;

        }public String getDateDBFormat() throws NullPointerException{
               
                theTime = Calendar.getInstance();
                NumberFormat myFormat = new DecimalFormat("#00");
                int a = theTime.get(Calendar.YEAR);
                int b = theTime.get(Calendar.MONTH)+1;
                String a1 =myFormat.format(b);
                int c = theTime.get(Calendar.DATE);
                String a2=myFormat.format(c);
                
                String dateTime= a+"-"+a1+"-"+a2;

                return dateTime;

        }
        public String getTomorrowsDateDBFormat() throws NullPointerException{
               
                theTime = Calendar.getInstance();
                theTime.add(Calendar.DATE, 1);
                NumberFormat myFormat = new DecimalFormat("#00");
                int a = theTime.get(Calendar.YEAR);
                int b = theTime.get(Calendar.MONTH)+1;
                String a1 =myFormat.format(b);
                int c = theTime.get(Calendar.DATE);   
                String a2=myFormat.format(c);
                
                String dateTime= a+"-"+a1+"-"+a2;

                return dateTime;

        }
        private Calendar onlyDate;
        int years1;
        int months1;
        int days1;
        public String getDateDBNoTimeFormat(int daysbefore) throws NullPointerException{
             myFormat_getDateDBFormat = new DecimalFormat("#00");
             
             onlyDate = Calendar.getInstance();
             onlyDate.add(Calendar.DAY_OF_MONTH, -daysbefore);
             years1 = onlyDate.get(Calendar.YEAR);
             months1= onlyDate.get(Calendar.MONTH)+1;
             days1= onlyDate.get(Calendar.DATE);
             
             return years1+"-"+myFormat_getDateDBFormat.format(months1)+"-"+myFormat_getDateDBFormat.format(days1);
        }
        public String getDateDBNoTimeFormat(int yearsbefore, int daysbefore) throws NullPointerException{
             myFormat_getDateDBFormat = new DecimalFormat("#00");
             
             onlyDate = Calendar.getInstance();
             onlyDate.add(Calendar.YEAR, -yearsbefore);
             onlyDate.add(Calendar.DAY_OF_MONTH, -daysbefore);
             years1 = onlyDate.get(Calendar.YEAR);
             months1= onlyDate.get(Calendar.MONTH)+1;
             days1=onlyDate.get(Calendar.DATE);
             
             return years1+"-"+myFormat_getDateDBFormat.format(months1)+"-"+myFormat_getDateDBFormat.format(days1);
        }
        public String getAllDaysInOneYearDateDBNoTimeFormat(int yearsbefore, int day) throws NullPointerException{
             myFormat_getDateDBFormat = new DecimalFormat("#00");
             Calendar testDate= Calendar.getInstance();
    
             onlyDate = Calendar.getInstance();
             onlyDate.add(Calendar.YEAR, -yearsbefore);
             onlyDate.add(Calendar.DAY_OF_YEAR, -(testDate.get(Calendar.DAY_OF_YEAR)-day));
             
             years1 = onlyDate.get(Calendar.YEAR);
             months1= onlyDate.get(Calendar.MONTH)+1;
             days1=onlyDate.get(Calendar.DATE);
             
             return years1+"-"+myFormat_getDateDBFormat.format(months1)+"-"+myFormat_getDateDBFormat.format(days1);
        }
        
        int getDateDBDayEnglishNameFormat_day;
        public String getDateDBDayEnglishNameFormat(int daysbefore) throws NullPointerException{
             onlyDate = Calendar.getInstance();
             onlyDate.add(Calendar.DAY_OF_MONTH, -daysbefore);
             getDateDBDayEnglishNameFormat_day=onlyDate.get(Calendar.DAY_OF_WEEK);
             if (getDateDBDayEnglishNameFormat_day==1){
                   return "Sunday";           
             }else if (getDateDBDayEnglishNameFormat_day==2){
                   return "Monday";           
             }else if (getDateDBDayEnglishNameFormat_day==3){
                   return "Tuesday";           
             }else if (getDateDBDayEnglishNameFormat_day==4){
                   return "Wensday";           
             }else if (getDateDBDayEnglishNameFormat_day==5){
                   return "Thursday";           
             }else if (getDateDBDayEnglishNameFormat_day==6){
                   return "Friday";           
             }else if (getDateDBDayEnglishNameFormat_day==7){
                   return "Saturday";           
             }else{
                 return "Error";
             }
     
        }
        public String getYearsDateDBDayEnglishNameFormat(int yearsbefore, int daysbefore) throws NullPointerException{
             onlyDate = Calendar.getInstance();
             Calendar testDate= Calendar.getInstance();
             onlyDate.add(Calendar.YEAR, -yearsbefore);
             onlyDate.add(Calendar.DAY_OF_YEAR, -(testDate.get(Calendar.DAY_OF_YEAR)-daysbefore));
             getDateDBDayEnglishNameFormat_day=onlyDate.get(Calendar.DAY_OF_WEEK);
             if (getDateDBDayEnglishNameFormat_day==1){
                   return "Sunday";           
             }else if (getDateDBDayEnglishNameFormat_day==2){
                   return "Monday";           
             }else if (getDateDBDayEnglishNameFormat_day==3){
                   return "Tuesday";           
             }else if (getDateDBDayEnglishNameFormat_day==4){
                   return "Wensday";           
             }else if (getDateDBDayEnglishNameFormat_day==5){
                   return "Thursday";           
             }else if (getDateDBDayEnglishNameFormat_day==6){
                   return "Friday";           
             }else if (getDateDBDayEnglishNameFormat_day==7){
                   return "Saturday";           
             }else{
                 return "Error";
             }
     
        }
        public String getDateDBDayEnglishNameFormat(int yearsbefore, int daysbefore) throws NullPointerException{
             onlyDate = Calendar.getInstance();
             onlyDate.add(Calendar.YEAR, -yearsbefore);
             onlyDate.add(Calendar.DAY_OF_MONTH, -daysbefore);
             getDateDBDayEnglishNameFormat_day=onlyDate.get(Calendar.DAY_OF_WEEK);
             if (getDateDBDayEnglishNameFormat_day==1){
                   return "Sunday";           
             }else if (getDateDBDayEnglishNameFormat_day==2){
                   return "Monday";           
             }else if (getDateDBDayEnglishNameFormat_day==3){
                   return "Tuesday";           
             }else if (getDateDBDayEnglishNameFormat_day==4){
                   return "Wensday";           
             }else if (getDateDBDayEnglishNameFormat_day==5){
                   return "Thursday";           
             }else if (getDateDBDayEnglishNameFormat_day==6){
                   return "Friday";           
             }else if (getDateDBDayEnglishNameFormat_day==7){
                   return "Saturday";           
             }else{
                 return "Error";
             }
                     
             
             
        }
        NumberFormat myFormat_getDateDBFormat;
        int a_getDateDBFormat;
        int b_getDateDBFormat;
        int c_getDateDBFormat;
        String a1_getDateDBFormat;
        String a2_getDateDBFormat;
        public String getDateDBFormat(int daysbefore) throws NullPointerException{
                a_getDateDBFormat=0;
                b_getDateDBFormat=0;
                c_getDateDBFormat=0;
                a1_getDateDBFormat="";
                a2_getDateDBFormat="";
                
                theTime = Calendar.getInstance();
                myFormat_getDateDBFormat = new DecimalFormat("#00");
                a_getDateDBFormat = theTime.get(Calendar.YEAR);
                b_getDateDBFormat = theTime.get(Calendar.MONTH)+1;
                c_getDateDBFormat = theTime.get(Calendar.DATE)-daysbefore;
                //System.out.println(c);
                if(c_getDateDBFormat < 1){
                    b_getDateDBFormat=b_getDateDBFormat-1;
                    if(b_getDateDBFormat==0){ // if month ==0 then rotated to 12
                        b_getDateDBFormat=12;
                        a_getDateDBFormat=a_getDateDBFormat-1; // if month go to last year dec, then minus 1 year
                    }
                    
                         if(b_getDateDBFormat==1){  c_getDateDBFormat = 31+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==2){  c_getDateDBFormat = 28+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==3){  c_getDateDBFormat = 31+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==4){  c_getDateDBFormat = 30+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==5){  c_getDateDBFormat = 31+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==6){  c_getDateDBFormat = 30+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==7){  c_getDateDBFormat = 31+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==8){  c_getDateDBFormat = 31+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==9){  c_getDateDBFormat = 30+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==10){ c_getDateDBFormat = 31+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==11){ c_getDateDBFormat = 30+c_getDateDBFormat;}
                    else if(b_getDateDBFormat==12){ c_getDateDBFormat = 31+c_getDateDBFormat;}
                    
                }
                a1_getDateDBFormat =myFormat_getDateDBFormat.format(b_getDateDBFormat);
                a2_getDateDBFormat=myFormat_getDateDBFormat.format(c_getDateDBFormat);
                
                
                
                return a_getDateDBFormat+"-"+a1_getDateDBFormat+"-"+a2_getDateDBFormat;
                
        }
        private ResultSet rs_getTodaysHalfDayHeadCount;
        private ResultSet rs_getTodaysHeadCount;
        private ResultSet rs_getOpenedTableStatusAndTname;
        private ResultSet rs_getdistinctAccountName;
        private ResultSet rs_getServerAndTable;
        private ResultSet rs3_getFourcrouseCount;
        private ResultSet rs_getFourcrouseDayCount;
        
        private PreparedStatement ps_getTodaysHeadCount6;
        private PreparedStatement ps_getTodaysHeadCount5;
        private PreparedStatement ps_getTodaysHeadCount4;
        private PreparedStatement ps_getTodaysHeadCount3;
        private PreparedStatement ps_getTodaysHeadCount2;
        private PreparedStatement ps_getTodaysHeadCount1;
        private PreparedStatement ps_getTodaysHeadCount0;
        
        private PreparedStatement ps_getTodaysHeadCount6_halfday;
        private PreparedStatement ps_getTodaysHeadCount5_halfday;
        private PreparedStatement ps_getTodaysHeadCount4_halfday;
        private PreparedStatement ps_getTodaysHeadCount3_halfday;
        private PreparedStatement ps_getTodaysHeadCount2_halfday;
        private PreparedStatement ps_getTodaysHeadCount1_halfday;
        private PreparedStatement ps_getTodaysHeadCount0_halfday;
        
        private PreparedStatement ps_getdistinctAccountName;
        private PreparedStatement ps_getOpenedTableStatusAndTname;
        private PreparedStatement ps_getServerAndTable;
        private PreparedStatement ps3_getFourcrouseCount;
        private PreparedStatement ps_fourCourseDayCount6;
        private PreparedStatement ps_fourCourseDayCount5;
        private PreparedStatement ps_fourCourseDayCount4;
        private PreparedStatement ps_fourCourseDayCount3;
        private PreparedStatement ps_fourCourseDayCount2;
        private PreparedStatement ps_fourCourseDayCount1;
        private PreparedStatement ps_fourCourseDayCount0;
        
     
     
     
     
     
     
     
        
        public void cacheStatement(){
            String _query6 = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(6)+" 00:00:00' AND '"+this.getDateDBFormat(6)+" 23:59:59'";
            String _query5 = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(5)+" 00:00:00' AND '"+this.getDateDBFormat(5)+" 23:59:59'";
            String _query4 = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(4)+" 00:00:00' AND '"+this.getDateDBFormat(4)+" 23:59:59'";
            String _query3 = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(3)+" 00:00:00' AND '"+this.getDateDBFormat(3)+" 23:59:59'";
            String _query2 = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(2)+" 00:00:00' AND '"+this.getDateDBFormat(2)+" 23:59:59'";
            String _query1 = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(1)+" 00:00:00' AND '"+this.getDateDBFormat(1)+" 23:59:59'";
            String _query0 = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(0)+" 00:00:00' AND '"+this.getDateDBFormat(0)+" 23:59:59'";
            
            String _query6_halfday = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(6)+" 00:00:00' AND '"+this.getDateDBFormat(6)+" 16:59:59'";
            String _query5_halfday = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(5)+" 00:00:00' AND '"+this.getDateDBFormat(5)+" 16:59:59'";
            String _query4_halfday = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(4)+" 00:00:00' AND '"+this.getDateDBFormat(4)+" 16:59:59'";
            String _query3_halfday = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(3)+" 00:00:00' AND '"+this.getDateDBFormat(3)+" 16:59:59'";
            String _query2_halfday = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(2)+" 00:00:00' AND '"+this.getDateDBFormat(2)+" 16:59:59'";
            String _query1_halfday = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(1)+" 00:00:00' AND '"+this.getDateDBFormat(1)+" 16:59:59'";
            String _query0_halfday = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(0)+" 00:00:00' AND '"+this.getDateDBFormat(0)+" 16:59:59'";
            try{
          
                ps3_getFourcrouseCount = co.prepareStatement("SELECT tname, COUNT(type) as p FROM R.order LEFT JOIN R.table ON R.order.tid=R.table.tid WHERE R.order.type ='FourCourseMenu' GROUP BY R.order.tid");
                ps_getServerAndTable = co.prepareStatement("SELECT tname, aname FROM R.table LEFT JOIN R.account ON R.account.aid=R.table.aid WHERE R.table.status<>'0'");
                ps_getOpenedTableStatusAndTname = co.prepareStatement("SELECT status, tname FROM R.table"); // the order of tables in database gotta match the order at matchTablenameAndButtonThenSetStatus() method 
                ps_getdistinctAccountName = co.prepareStatement("SELECT DISTINCT aname as daname FROM R.table LEFT JOIN R.account ON R.account.aid=R.table.aid WHERE R.table.status<>'0'");
                ps_getTodaysHeadCount6=co.prepareStatement(_query6);
                ps_getTodaysHeadCount5=co.prepareStatement(_query5);
                ps_getTodaysHeadCount4=co.prepareStatement(_query4);
                ps_getTodaysHeadCount3=co.prepareStatement(_query3);
                ps_getTodaysHeadCount2=co.prepareStatement(_query2);
                ps_getTodaysHeadCount1=co.prepareStatement(_query1);
                ps_getTodaysHeadCount0=co.prepareStatement(_query0);
                
                ps_getTodaysHeadCount6_halfday=co.prepareStatement(_query6_halfday);
                ps_getTodaysHeadCount5_halfday=co.prepareStatement(_query5_halfday);
                ps_getTodaysHeadCount4_halfday=co.prepareStatement(_query4_halfday);
                ps_getTodaysHeadCount3_halfday=co.prepareStatement(_query3_halfday);
                ps_getTodaysHeadCount2_halfday=co.prepareStatement(_query2_halfday);
                ps_getTodaysHeadCount1_halfday=co.prepareStatement(_query1_halfday);
                ps_getTodaysHeadCount0_halfday=co.prepareStatement(_query0_halfday);
                
                ps_fourCourseDayCount6=co.prepareStatement("SELECT type FROM R.storage WHERE R.storage.time_in BETWEEN '"+this.getDateDBFormat(6)+" 00:00:' AND '"+this.getDateDBFormat(6)+" 23:59:59'");
                ps_fourCourseDayCount5=co.prepareStatement("SELECT type FROM R.storage WHERE R.storage.time_in BETWEEN '"+this.getDateDBFormat(5)+" 00:00:' AND '"+this.getDateDBFormat(5)+" 23:59:59'");
                ps_fourCourseDayCount4=co.prepareStatement("SELECT type FROM R.storage WHERE R.storage.time_in BETWEEN '"+this.getDateDBFormat(4)+" 00:00:' AND '"+this.getDateDBFormat(4)+" 23:59:59'");
                ps_fourCourseDayCount3=co.prepareStatement("SELECT type FROM R.storage WHERE R.storage.time_in BETWEEN '"+this.getDateDBFormat(3)+" 00:00:' AND '"+this.getDateDBFormat(3)+" 23:59:59'");
                ps_fourCourseDayCount2=co.prepareStatement("SELECT type FROM R.storage WHERE R.storage.time_in BETWEEN '"+this.getDateDBFormat(2)+" 00:00:' AND '"+this.getDateDBFormat(2)+" 23:59:59'");
                ps_fourCourseDayCount1=co.prepareStatement("SELECT type FROM R.storage WHERE R.storage.time_in BETWEEN '"+this.getDateDBFormat(1)+" 00:00:' AND '"+this.getDateDBFormat(1)+" 23:59:59'");
                ps_fourCourseDayCount0=co.prepareStatement("SELECT type FROM R.storage WHERE R.storage.time_in BETWEEN '"+this.getDateDBFormat(0)+" 00:00:' AND '"+this.getDateDBFormat(0)+" 23:59:59'");
                
               
               
               
               
               
               
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTodaysHeadCount"+e);
            }
        }
        
      

        public int getMassHeadCount(int daysbefore){
            int headcount =0;
            //String testquery="SELECT ppl FROM R.check WHERE R.check.since = CURDATE()- INTERVAL ? DAY";//BETWEEN NOW() - INTERVAL ? DAY AND NOW() - INTERVAL ? DAY";
            String testquery = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBNoTimeFormat(daysbefore)+" 00:00:00' AND '"+this.getDateDBNoTimeFormat(daysbefore)+" 23:59:59'";
            ResultSet massheadcount_rs;
            
            PreparedStatement massheadcount_ps;
            try{
                //ps_getTodaysHeadCount6=co.prepareStatement("SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBFormat(daysbefore)+" 00:00:00' AND '"+this.getDateDBFormat(daysbefore)+" 23:59:59'");
                massheadcount_ps=co.prepareStatement(testquery);
                //massheadcount_ps.setString(1,""+daysbefore);
                //massheadcount_ps.setString(2,""+(daysbefore-1));
                massheadcount_rs = massheadcount_ps.executeQuery();
                
                while(massheadcount_rs.next()){
                    headcount+=massheadcount_rs.getInt("ppl");
                    //System.out.println(ppl_getTodaysHeadCount);
                }
                
                massheadcount_rs=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getMassHeadCount"+e);
            }

            return headcount;
            
        }
        public int getMassHeadCount(int yearsbefore, int daysbefore){
            int headcount =0;
         
            String testquery = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getDateDBNoTimeFormat(yearsbefore,daysbefore)+" 00:00:00' AND '"+this.getDateDBNoTimeFormat(yearsbefore,daysbefore)+" 23:59:59'";
            ResultSet massheadcount_rs;
            
            PreparedStatement massheadcount_ps;
            try{
               
                massheadcount_ps=co.prepareStatement(testquery);
                massheadcount_rs = massheadcount_ps.executeQuery();
                
                while(massheadcount_rs.next()){
                    headcount+=massheadcount_rs.getInt("ppl");
                }
                
                massheadcount_rs=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getMassHeadCount"+e);
            }

            return headcount;
            
        }
        public int getYYearsMassHeadCount(int yearsbefore, int day){
            int headcount =0;
           
            String testquery = "SELECT ppl FROM R.check WHERE R.check.since BETWEEN '"+this.getAllDaysInOneYearDateDBNoTimeFormat(yearsbefore,day)+" 00:00:00' AND '"+this.getAllDaysInOneYearDateDBNoTimeFormat(yearsbefore,day)+" 23:59:59'";
            ResultSet massheadcount_rs;
             System.out.println(testquery);
            PreparedStatement massheadcount_ps;
            try{
               
                massheadcount_ps=co.prepareStatement(testquery);
                massheadcount_rs = massheadcount_ps.executeQuery();
                
                while(massheadcount_rs.next()){
                    headcount+=massheadcount_rs.getInt("ppl");
                }
                
                massheadcount_rs=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getMassHeadCount"+e);
            }

            return headcount;
            
        }
        private int ppl_getTodaysHeadCount;
        public int getTodaysHeadCount(int daysbefore){
            query = "";
            ppl_getTodaysHeadCount=0;
            try{
                if(daysbefore==6){
                    rs_getTodaysHeadCount = ps_getTodaysHeadCount6.executeQuery();
                }else if(daysbefore==5){
                    rs_getTodaysHeadCount = ps_getTodaysHeadCount5.executeQuery();
                }else if(daysbefore==4){
                    rs_getTodaysHeadCount = ps_getTodaysHeadCount4.executeQuery();
                }else if(daysbefore==3){
                    rs_getTodaysHeadCount = ps_getTodaysHeadCount3.executeQuery();
                }else if(daysbefore==2){
                    rs_getTodaysHeadCount = ps_getTodaysHeadCount2.executeQuery();
                }else if(daysbefore==1){
                    rs_getTodaysHeadCount = ps_getTodaysHeadCount1.executeQuery();
                }else if(daysbefore==0){
                    rs_getTodaysHeadCount = ps_getTodaysHeadCount0.executeQuery();
                }
                


                while(rs_getTodaysHeadCount.next()){
                    ppl_getTodaysHeadCount+=rs_getTodaysHeadCount.getInt("ppl");
                    //System.out.println(ppl_getTodaysHeadCount);
                }
                
                
                rs_getTodaysHeadCount =null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTodaysHeadCount"+e);
            }

            return ppl_getTodaysHeadCount;
            
        }
        private int ppl_getTodaysHalfDayHeadCount;
        public int getTodaysHalfDayHeadCount(int daysbefore){
            query = "";
            ppl_getTodaysHalfDayHeadCount=0;
            try{
                if(daysbefore==6){
                    rs_getTodaysHalfDayHeadCount = ps_getTodaysHeadCount6_halfday.executeQuery();
                }else if(daysbefore==5){
                    rs_getTodaysHalfDayHeadCount = ps_getTodaysHeadCount5_halfday.executeQuery();
                }else if(daysbefore==4){
                    rs_getTodaysHalfDayHeadCount = ps_getTodaysHeadCount4_halfday.executeQuery();
                }else if(daysbefore==3){
                    rs_getTodaysHalfDayHeadCount = ps_getTodaysHeadCount3_halfday.executeQuery();
                }else if(daysbefore==2){
                    rs_getTodaysHalfDayHeadCount = ps_getTodaysHeadCount2_halfday.executeQuery();
                }else if(daysbefore==1){
                    rs_getTodaysHalfDayHeadCount = ps_getTodaysHeadCount1_halfday.executeQuery();
                }else if(daysbefore==0){
                    rs_getTodaysHalfDayHeadCount = ps_getTodaysHeadCount0_halfday.executeQuery();
                }
                


                while(rs_getTodaysHalfDayHeadCount.next()){
                    ppl_getTodaysHalfDayHeadCount+=rs_getTodaysHalfDayHeadCount.getInt("ppl");
                }
                
                
                rs_getTodaysHalfDayHeadCount =null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTodaysHalfDayHeadCount"+e);
            }

            return ppl_getTodaysHalfDayHeadCount;
            
        }
        
        private int ppl_fourCourseDayCount;
        public int fourCourseDayCount(int daysbefore){
            query = "";
            ppl_fourCourseDayCount=0;
            try{
                if(daysbefore==6){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount6.executeQuery();
                }else if(daysbefore==5){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount5.executeQuery();
                }else if(daysbefore==4){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount4.executeQuery();
                }else if(daysbefore==3){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount3.executeQuery();
                }else if(daysbefore==2){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount2.executeQuery();
                }else if(daysbefore==1){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount1.executeQuery();
                }else if(daysbefore==0){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount0.executeQuery();
                }
                


                while(rs_getFourcrouseDayCount.next()){
                    if(rs_getFourcrouseDayCount.getString("type").equals("FourCourseMenu")){
                        ppl_fourCourseDayCount+=1;
                    }
                }
                
                
                rs_getFourcrouseDayCount =null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! fourCourseDayCount"+e);
            }

            return ppl_fourCourseDayCount;
            
        }
        
        
        private int ppl_threeCourseDayCount;
        public int threeCourseDayCount(int daysbefore){
            query = "";
            ppl_threeCourseDayCount=0;
            try{
                if(daysbefore==6){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount6.executeQuery();
                }else if(daysbefore==5){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount5.executeQuery();
                }else if(daysbefore==4){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount4.executeQuery();
                }else if(daysbefore==3){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount3.executeQuery();
                }else if(daysbefore==2){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount2.executeQuery();
                }else if(daysbefore==1){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount1.executeQuery();
                }else if(daysbefore==0){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount0.executeQuery();
                }
                
                while(rs_getFourcrouseDayCount.next()){
                    if(rs_getFourcrouseDayCount.getString("type").equals("ThreeCourseMenu")){
                        ppl_threeCourseDayCount+=1;
                    }
                }
                rs_getFourcrouseDayCount =null;
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! threeCourseDayCount"+e);
            }
            return ppl_threeCourseDayCount;
        }
        
        
        public int topSellingEntreeCount(int daysbefore){
            query = "";
            ppl_threeCourseDayCount=0;
            try{
                if(daysbefore==6){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount6.executeQuery();
                }else if(daysbefore==5){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount5.executeQuery();
                }else if(daysbefore==4){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount4.executeQuery();
                }else if(daysbefore==3){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount3.executeQuery();
                }else if(daysbefore==2){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount2.executeQuery();
                }else if(daysbefore==1){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount1.executeQuery();
                }else if(daysbefore==0){
                    rs_getFourcrouseDayCount = ps_fourCourseDayCount0.executeQuery();
                }
                
                while(rs_getFourcrouseDayCount.next()){
                    if(rs_getFourcrouseDayCount.getString("type").equals("ThreeCourseMenu")){
                        ppl_threeCourseDayCount+=1;
                    }
                }
                rs_getFourcrouseDayCount =null;
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! threeCourseDayCount"+e);
            }
            return ppl_threeCourseDayCount;
        }
        
        
        public String receiptNumberFormat(int cid) throws NullPointerException{
            NumberFormat myFormat = new DecimalFormat("#000000");
            String string = myFormat.format(cid);
            return string;
        }
        public int getTableAID(int tid) throws NullPointerException{
            int aid=0;
            try{
                
                query = "SELECT aid FROM R.table WHERE R.table.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    aid=rs.getInt("aid");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableAID"+e);
            }

            return aid;
        }
        public int getTableUserID(int tid) throws NullPointerException{
            int aid=0;
            try{
                
                query = "SELECT userID FROM R.table WHERE R.table.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    aid=rs.getInt("userID");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableUserID"+e);
            }

            return aid;
        }
        public int getCheckID() throws NullPointerException{
            int cid=0;
            try{
                
                //query = "SELECT MAX(cid) cid FROM R.check WHERE R.check.tid='"+tid+"'";
                query = "SELECT MAX(cid) cid FROM R.check";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    cid=rs.getInt("cid");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getCheckID"+e);
            }

            return cid;
        }
        public int getCheckIDWithTID(int tid) throws NullPointerException{
            int cid=0;
            try{
                
                query = "SELECT MAX(cid) cid FROM R.check WHERE R.check.tid='"+tid+"'";
                //query = "SELECT MAX(cid) cid FROM R.check";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    cid=rs.getInt("cid");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getCheckID"+e);
            }

            return cid;
        }
        public void updateOrderCid(int cid, int tid) throws NullPointerException{
            
            try{
                
               
                query = "UPDATE R.order SET R.order.cid= '"+cid+"' WHERE R.order.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ps.executeUpdate();
                
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! updateOrderCid"+e);
            }

            
        }
        public void insertNewCheck(int tid) throws NullPointerException{
            
            PreparedStatement ps= null;
           
            try{
                int cid = this.getCidAtWheverMissedNumber();
                System.err.println(cid);
                insert = "INSERT INTO `R`.`check` (cid, aid, since, tax, total, ppl, tid) ";
                    insert += "VALUE ( '"+cid+"','"+this.getTableAID(tid)+"','"+this.getDateTimeDBFormat()+"','"+this.getTableTax(tid) +"','"+this.getTableTotal(tid) +"','"+this.getTablePPL(tid)+"','"+tid+"')";
                ps = co.prepareStatement(insert);
                ps.executeUpdate();
                 ps.close();
            }catch(NullPointerException e){         }catch(Exception e){
            System.out.println("error occur when insertNewCheck:"+e);
            }
        }
        
        public void insertNewWholeCakeCheck(int aid) throws NullPointerException{
            
            PreparedStatement ps= null;
           
            try{
                insert = "INSERT INTO `R`.`wholecake` (aid,since,paid) "; //aid,since,price,addon,tax,total,date,cake,size,writing,customername,contactnumber,notes,printed,void 
                    insert += "VALUE ( '"+aid+"','"+this.getDateTimeDBFormat()+"','"+2+"')";
                ps = co.prepareStatement(insert);
                ps.executeUpdate();
                ps.close();
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when insert item:"+e);
            }
        }
        public int getWholeCakeNewReceiptNumber() throws NullPointerException{
            int wid=0;
            try{
                
                query = "SELECT MAX(wid) wid FROM R.wholecake";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    wid=rs.getInt("wid");
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getCheckID"+e);
            }

            return wid;
        }
        public void insertWholeCakeOrder(Object[] temp){
//            temp[0] =cakeString;
//            temp[1] =sizeString;
//            temp[2] =dateString;
//            temp[3] =timeString;
//            temp[4] =handwriting;
//            temp[5] =customername;
//            temp[6] =contactnumber;
//            temp[7] =notes;
//            temp[8] =userID;
//            temp[9] =price;
//            temp[10]=addon;
//            temp[11]=tax;
//            temp[12]=total;
//            temp[13]=wid;
//            temp[14]=printed;
//            temp[15]=voided;
//            temp[16]=paid;
            PreparedStatement ps= null;
            
            try{
                String query = "UPDATE `R`.`wholecake` SET `aid`='"+(Integer)temp[8]+"', `since`='"+this.getDateTimeDBFormat()+"', `price`='"+moneyformatnosign.format((Double)temp[9])+"', `addon`='"+moneyformatnosign.format((Double)temp[10])+"', `tax`='"+moneyformatnosign.format((Double)temp[11])+"', `total`='"+moneyformatnosign.format((Double)temp[12])+"', `date`='"+temp[2]+"', `time`='"+(String)temp[3]+"',`cake`='"+(String)temp[0]+"', `size`='"+(String)temp[1]+"', `writing`='"+(String)temp[4]+"', `customername`='"+(String)temp[5]+"', `contactnumber`='"+(String)temp[6]+"', `notes`='"+(String)temp[7]+"', `printed`='"+(Integer)temp[14]+"', `void`='"+(Integer)temp[15]+"',`paid`='"+(Integer)temp[16]+"' WHERE  `wid` = '"+(Integer)temp[13]+"'"; 
                ps = co.prepareStatement(query);
                ps.executeUpdate();
                ps.close();
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when UPDATE whole Cake Order:"+e);
            }
        }
        public void updateDateTimeCakeOrderWithWID(int wid, String date, String time){

            PreparedStatement ps= null;
            
            try{
                String query = "UPDATE `R`.`wholecake` SET  `date`='"+date+"', `time`='"+time+"' WHERE  `wid` = '"+wid+"'"; 
                ps = co.prepareStatement(query);
                ps.executeUpdate();
                ps.close();
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when UPDATE whole Cake Order:"+e);
            }
        }
         public void updateCakeNameSizePriceOrderWithWID(int wid, String cake, String size, Double price){

            PreparedStatement ps= null;
            
            try{
                String query = "UPDATE `R`.`wholecake` SET  `cake`='"+cake+"', `size`='"+size+"', `price`='"+price+"' WHERE  `wid` = '"+wid+"'"; 
                ps = co.prepareStatement(query);
                ps.executeUpdate();
                ps.close();
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when UPDATE whole Cake Order:"+e);
            }
        }
        public void updateCustomeInfoAndNotesOrderWithWID(int wid, String name, String phone, String note){

            PreparedStatement ps= null;
            
            try{
                String query = "UPDATE `R`.`wholecake` SET  `customername`='"+name+"', `contactnumber`='"+phone+"', `notes`='"+note+"' WHERE  `wid` = '"+wid+"'"; 
                ps = co.prepareStatement(query);
                ps.executeUpdate();
                ps.close();
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when UPDATE whole Cake Order:"+e);
            }
        }
         public void updatePaidOrderWithWID(int wid, int paid){

            PreparedStatement ps= null;
            
            try{
                String query = "UPDATE `R`.`wholecake` SET  `paid`='"+paid+"' WHERE  `wid` = '"+wid+"'"; 
                ps = co.prepareStatement(query);
                ps.executeUpdate();
                ps.close();
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when UPDATE whole Cake Order:"+e);
            }
        }
         public void updateCakeWritingOrderWithWID(int wid, String writing, Double addon){

            PreparedStatement ps= null;
            
            try{
                String query = "UPDATE `R`.`wholecake` SET  `writing`='"+writing+"', `addon`='"+addon+"' WHERE  `wid` = '"+wid+"'"; 
                ps = co.prepareStatement(query);
                ps.executeUpdate();
                ps.close();
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when UPDATE whole Cake Order:"+e);
            }
        }
        
        public void copyOrderToStorage(int tid) throws NullPointerException{
            
            PreparedStatement ps= null;
            
            try{
                

                insert = "INSERT R.storage (iid, aid,cid,quantity,time_in,time_send,name,price,type,note,firstCourse,secondCourse,thirdCourse,seafood, other, drink, dessert, cake, sent, subtable,position) SELECT iid, aid,cid,quantity,time_in,time_send,name,price,type,note,firstCourse,secondCourse,thirdCourse,seafood, other, drink, dessert, cake, sent, subtable,position FROM R.order WHERE R.order.tid = '"+tid+"'";
                ps = co.prepareStatement(insert);
                ps.executeUpdate();
                ps.close();

            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when copyOrderToStorage():"+e);
            }
        }
        
        public void updateTableStatus(int status, int tid) throws NullPointerException{
            
            String name ="";
            try{
                
                query = "UPDATE R.table SET R.table.status= '"+status+"' WHERE R.table.tid='"+tid+"'"; // tables have to be activate;  
                // the order of tables in database gotta match the order at matchTablenameAndButtonThenSetStatus() method 
                PreparedStatement ps = co.prepareStatement(query);
                ps.executeUpdate();
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("MyQuery.class 644  updateTableStatus():"+e);
            }
        }
        /*
        public void activateTable(int tid){
            
            String name ="";
            try{
                
                query = "UPDATE R.table SET R.table.activate= '1' WHERE R.table.tid='"+tid+"'"; // tables have to be activate;  
                // the order of tables in database gotta match the order at matchTablenameAndButtonThenSetStatus() method 
                PreparedStatement ps = co.prepareStatement(query);
                ps.executeUpdate();
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! updateTableStatus():"+e);
            }
        }*/
         public void deActivateTable(int tid) throws NullPointerException{
            
            String name ="";
            try{
                
                query = "UPDATE R.table SET R.table.activate='0' WHERE R.table.tid='"+tid+"'"; // tables have to be activate;  
                // the order of tables in database gotta match the order at matchTablenameAndButtonThenSetStatus() method 
                PreparedStatement ps = co.prepareStatement(query);
                ps.executeUpdate();
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! updateTableStatus():"+e);
            }
        }
        public void setTableChecked(String s, int tid) throws NullPointerException{
            
            try{
                
                query = "UPDATE R.table SET R.table.checked= '"+s+"' WHERE R.table.tid='"+tid+"'"; // tables have to be activate;  
                // the order of tables in database gotta match the order at matchTablenameAndButtonThenSetStatus() method 
                PreparedStatement ps = co.prepareStatement(query);
                ps.executeUpdate();
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! setTableChecked():"+e);
            }
        }
        public boolean isTableChecked(int tid) throws NullPointerException{
            boolean b = false;
            try{
                
                query = "SELECT checked FROM R.table WHERE R.table.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    if(rs.getString("checked").equals("YES")) b=true;
                    
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableAID"+e);
            }

            return b;
        }
        public void clearUpOneTablesOrder(int tid) throws NullPointerException{
            boolean b = false;
            try{
                
                query = "DELETE FROM R.order WHERE R.order.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ps.executeUpdate();

                
                
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! clearUpOneTablesOrder"+e);
            }

           
        }
        public void resetOneTablesAllstatus(int tid) throws NullPointerException{
            String name ="";
            try{
                
                query = "UPDATE R.table SET R.table.aid='0', R.table.ppl='0', R.table.since='0000-00-00 00:00:00', R.table.discount='0.00', R.table.service_charge='0.00', R.table.tax='0.00', R.table.status='0', R.table.total='0.00', R.table.coupon='0.00', R.table.userID='NULL', R.table.timelock='00:00:00', R.table.checked='NO' WHERE R.table.tid='"+tid+"'"; // tables have to be activate;  
                // the order of tables in database gotta match the order at matchTablenameAndButtonThenSetStatus() method 
                PreparedStatement ps = co.prepareStatement(query);
                ps.executeUpdate();
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! resetOneTablesAllstatus():"+e);
            }
        }
        public int countOrdersInATable(int tid) throws NullPointerException{

            int count =0;
            try{
                
                query = "SELECT COUNT(*) AS c FROM R.order WHERE R.order.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    count=rs.getInt("c");
                }
                
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! countOrdersInATable():"+e);
            }
            return count;
        }
        
        public int countSubCheck(int tid) throws NullPointerException{

            int count=0;
            try{
                
                query = "SELECT COUNT(DISTINCT subtable) as distinct_subtable FROM R.order WHERE R.order.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    count= rs.getInt("distinct_subtable");
                }
                
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! countSubCheck():"+e);
            }
            return count;
        }
        public ArrayList<Integer> getSortedSubCheckNumber(int tid) throws NullPointerException{

            ArrayList<Integer> a = new ArrayList<Integer>();
            try{
                
                query = "SELECT DISTINCT subtable as distinct_subtable FROM R.order WHERE R.order.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    a.add(rs.getInt("distinct_subtable"));
                    
                }
                Collections.sort(a);
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getSortedSubCheckNumber():"+e);
            }
            return a;
        }
        public void setTableCreateTime(String s, int tid) throws NullPointerException{
            try{
                //System.out.println(s+" "+tid);
                query = "UPDATE R.table SET R.table.since=? WHERE R.table.tid=?"; // tables have to be activate;  
                // the order of tables in database gotta match the order at matchTablenameAndButtonThenSetStatus() method 
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1,""+s);
                ps.setString(2,""+tid);
                ps.executeUpdate();
               ps=null;
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! setTableCreateTime():"+e);
            }
        }
        public String getTableCreateTime(int tid) throws NullPointerException{
            String cid="";
            try{
                
                query = "SELECT since FROM R.table WHERE R.table.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    cid=rs.getString("since");
                }
               rs=null;
               ps=null;
                
                

            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableCreateTime"+e);
            }
            try{
                cid =String.valueOf(cid.subSequence(0, cid.length()-2));
                
            }catch (Exception e){
                System.out.println("error!!! getTableCreateTime"+e);
            }
            return cid;
        }
         public void insertLog(String s, int tid ,int aid) throws NullPointerException{
            
            PreparedStatement ps= null;
           
            try{
                
                insert = "INSERT INTO `R`.`log` (aid, since, msg, tid) ";
                    insert += "VALUE ( '"+aid+"','"+this.getDateTimeDBFormat()+"','"+s+"','"+tid+"')";
                ps = co.prepareStatement(insert);
                ps.executeUpdate();
                ps.close();
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when insertLog:"+e);
            }
        }
         public ArrayList<String> getOneTableLog(int tid) throws NullPointerException{
            ArrayList<String> array = new ArrayList<String>();
            String tname="";
            String aname="";
            String msg="";
            String time="";
            try{
                
                query = "SELECT * FROM R.log WHERE R.log.tid='"+tid+"'";//+" ORDER BY Lid DESC LIMIT 1";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    aname=this.getServerName(rs.getInt("aid"));
                    tname=this.getTableName(rs.getInt("tid"));
                    msg=rs.getString("msg");
                    time=rs.getString("since");
                    
                    try{
                        time =String.valueOf(time.subSequence(11, time.length()-2));

                    }catch (Exception e){
                       // System.out.println("error!!! getOneTableLog"+e);
                    }
                    
                    array.add("("+time+") <"+tname+"> {"+aname+"} -> "+msg);
                }
               rs=null;
               ps=null;
                
                

            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getOneTableLog"+e);
            }
            
            return array;
        }
         public String getOneTableLogAtLastOne(int tid) throws NullPointerException{
            String tname="";
            String aname="";
            String msg="";
            String time="";
            try{
               
                query = "SELECT * FROM R.log WHERE R.log.tid='"+tid+"'"+" ORDER BY Lid DESC LIMIT 1";
                //query = "SELECT * FROM R.log WHERE Lid IN (SELECT MAX(Lid) FROM R.log Where tid=?)";

                PreparedStatement ps = co.prepareStatement(query);
                //ps.setString(1,""+tid);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    aname=this.getServerName(rs.getInt("aid"));
                    tname=this.getTableName(rs.getInt("tid"));
                    msg=rs.getString("msg");
                    time=rs.getString("since");
                    
                    try{
                        time =String.valueOf(time.subSequence(11, time.length()-2));

                    }catch (Exception e){
                        System.out.println("error!!! getOneTableLogAtLastOne"+e);
                    }
                    
                    
                }
               rs=null;
               ps=null;
                
                

            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getOneTableLogAtLastOne"+e);
            }
            
            return "("+time+") <"+tname+"> {"+aname+"} -> "+msg;
        }
         
         public String getOneOrderNameAndSubCheck(int oid) throws NullPointerException{
            String temp="";
            String text1="", text2="";
            try{
                
                query = "SELECT * FROM R.order WHERE R.order.oid='"+oid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    text1=rs.getString("name");
                    text2=(""+rs.getInt("subtable"));
                
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableAID"+e);
            }

            return "("+text1 +") Check ("+ text2+")";
        }
         
         public ArrayList<String> getOneOrderAllInfor(int oid) throws NullPointerException{
            ArrayList<String> a = new ArrayList<String>();
            try{
                
                query = "SELECT * FROM R.order WHERE R.order.oid='"+oid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    a.add(rs.getString("name"));                    //0
                    a.add(rs.getString("type"));                    //1
                    a.add(Double.toString(rs.getDouble("price")));  //2
                    a.add(Integer.toString(rs.getInt("quantity"))); //3
                    a.add(rs.getString("note"));                    //4
                    a.add(""+rs.getInt("subtable"));                //5
                    
                    String position ="";
                    if(rs.getInt("position")!=0){
                        position=Integer.toString(rs.getInt("position"));  //6
                    }
                    
                    a.add(position);
                    
                }
               rs=null;
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getOneOrderAllInfor"+e);
            }
            
            
            return a;
        }
        public void romoveOrderWithOID(int id, int userID)  throws NullPointerException{
            
            PreparedStatement ps = null;
           
            int oid = id;
            try {
                this.insertLog("Deleted ["+this.getOneOrderAllInfor(oid).get(1)+"] ["+this.getOneOrderAllInfor(oid).get(0)+"] [quantity:"+this.getOneOrderAllInfor(oid).get(3)+"] [$"+Double.parseDouble(this.getOneOrderAllInfor(oid).get(2))*Double.parseDouble(this.getOneOrderAllInfor(oid).get(3))+"] [seat:"+this.getOneOrderAllInfor(oid).get(6)+"] [check#"+this.getOneOrderAllInfor(oid).get(5)+"] [notes:"+this.getOneOrderAllInfor(oid).get(4)+"]", this.getTableID(oid), userID);

                
                query = "DELETE FROM R.order WHERE R.order.oid='"+oid+"'";
                ps = co.prepareStatement(query);
                ps.executeUpdate();
                //ArrayList<String> a = this.getOneOrderAllInfor(oid);
                //System.out.print(a.get(0).toString());
                ps.close();
            } catch (Exception e) {
                System.out.println("error at jd romoveOrderWithOID():" + e);

            }
        }// end of removeorderwithoid method 
        public ArrayList<Integer> getAllOIDFromOneTable(int tid) throws NullPointerException{

            ArrayList<Integer> a= new ArrayList<Integer>();
            try{
                
                query = "SELECT oid FROM R.order WHERE R.order.tid='"+tid+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    a.add(rs.getInt("oid"));
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getAllOIDFromOneTable:"+e);
            }
            return a;
        }
        public boolean isThisOrderSend(int oid) throws NullPointerException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean hasNewOrder = false;
        try {
            
            query = "SELECT sent FROM R.order o WHERE o.oid = '"+oid+"' ";
            ps = co.prepareStatement(query);
            
            rs = ps.executeQuery();
            while(rs.next()){
                String sent = rs.getString("sent");
           
                if(sent.equals("YES")){
                    hasNewOrder = true;
                }
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("error at jd checkOrderWhetherSend:" + e);
        }
        return hasNewOrder;
    }
    public boolean isGroupon(int tid, int sid) throws NullPointerException{
        boolean b = false;
        try{
            
            query = "SELECT name FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.subtable='"+sid+"'";
            PreparedStatement ps = co.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                if(rs.getString("name").equals("Groupon Seafood For 2 People") || rs.getString("name").equals("Groupon Seafood For 4 People") ) b=true;

            }
           rs=null;
           ps=null;
            


        }catch(NullPointerException e){         }catch(Exception e){
            System.out.println("error!!! isGroupon"+e);
        }

        return b;
    }
    
     public Double getGrouponTotal(int tid, int sid) throws NullPointerException{
        
        Double total = 0.00;
        try{
            
            query = "SELECT * FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.subtable='"+sid+"' AND R.order.name='Groupon Seafood For 2 People' UNION SELECT * FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.subtable='"+sid+"' AND R.order.name='Groupon Seafood For 4 People'";
            PreparedStatement ps = co.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                int quan = rs.getInt("quantity");
                Double price = rs.getDouble("price");
                total+=(quan*price);
            }
           rs=null;
           ps=null;
            


        }catch(NullPointerException e){         }catch(Exception e){
            System.out.println("error!!! getGrouponTotal"+e);
        }

        return total;
    }
     public boolean is4Course(int oid) throws NullPointerException{
        boolean b = false;
        try{
            
            query = "SELECT type FROM R.order WHERE R.order.oid='"+oid+"'";
            PreparedStatement ps = co.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                if(rs.getString("type").equals("FourCourseMenu") || rs.getString("type").equals("ThreeCourseMenu") ) b=true;

            }
           rs=null;
           ps=null;
            


        }catch(NullPointerException e){         }catch(Exception e){
            System.out.println("error!!! is4Course"+e);
        }

        return b;
    }
        
    
    public boolean hasFood(int tid, String YesOrNo) throws NullPointerException{
        boolean b = false;
        try{
            
            query = "SELECT * FROM R.order WHERE R.order.tid=? AND R.order.sent=?";
            PreparedStatement ps = co.prepareStatement(query);
            ps.setString(1, ""+tid);
            ps.setString(2, ""+YesOrNo);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                if(!rs.getString("firstCourse").equals("") || !rs.getString("secondCourse").equals("") ||!rs.getString("thirdCourse").equals("")){
                    b=true;
                }
            }
            
            
           rs=null;
           ps=null;
            


        }catch(NullPointerException e){         }catch(Exception e){
            System.out.println("error!!! hasFood"+e);
        }

        return b;
    }
    public boolean hasFoodWithoutSentCheck(int tid) throws NullPointerException{
        boolean b = false;
        try{
            
            query = "SELECT * FROM R.order WHERE R.order.tid=?";
            PreparedStatement ps = co.prepareStatement(query);
            ps.setString(1, ""+tid);
      
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                if(!rs.getString("firstCourse").equals("") || !rs.getString("secondCourse").equals("") ||!rs.getString("thirdCourse").equals("")){
                    b=true;
                }
            }
            
            
           rs=null;
           ps=null;
            


        }catch(NullPointerException e){         }catch(Exception e){
            System.out.println("error!!! hasFood"+e);
        }

        return b;
    }
    public boolean hasSeafood(int tid,String YesOrNo) throws NullPointerException{
        boolean b = false;
        try{
            
            query = "SELECT COUNT(*) as seafood FROM R.order WHERE R.order.tid=? AND R.order.sent=? AND R.order.seafood<>''";
            PreparedStatement ps = co.prepareStatement(query);
            ps.setString(1, ""+tid);
            ps.setString(2, ""+YesOrNo);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                if(rs.getInt("Seafood")!=0){
                    b=true;
                }
            }
            
            
           rs=null;
           ps=null;
            


        }catch(NullPointerException e){         }catch(Exception e){
            System.out.println("error!!! hasSeafood"+e);
        }

        return b;
    }
    public boolean hasSeafoodWithoutSentCheck(int tid) throws NullPointerException{
        boolean b = false;
        try{
            
            query = "SELECT COUNT(*) as seafood FROM R.order WHERE R.order.tid=? AND R.order.seafood<>''";
            PreparedStatement ps = co.prepareStatement(query);
            ps.setString(1, ""+tid);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                if(rs.getInt("Seafood")!=0){
                    b=true;
                }
            }
            
            
           rs=null;
           ps=null;
            


        }catch(NullPointerException e){         }catch(Exception e){
            System.out.println("error!!! hasSeafood"+e);
        }

        return b;
    }
    
    public int getFoodNumber() throws NullPointerException{
        int number=0;
        try{
                
                query = "SELECT food FROM R.kitchen WHERE R.kitchen.id= '1'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                rs.next();
                number=rs.getInt("food");
                
                
               ps=null;
                


        }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getFoodNumber"+e);
        }
        return number;    
    }
    public int getDrinkNumber() throws NullPointerException{
        int number=0;
        try{
                
                query = "SELECT drink FROM R.kitchen WHERE R.kitchen.id= '1'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                rs.next();
                number=rs.getInt("drink");
                
                
               ps=null;
                


        }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getDrinkNumber"+e);
        }
        return number;    
    }
    public int getDessertNumber() throws NullPointerException{
        int number=0;
        try{
                
                query = "SELECT dessert FROM R.kitchen WHERE R.kitchen.id= '1'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                rs.next();
                number=rs.getInt("dessert");
                
                
               ps=null;
                


        }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getDessertNumber"+e);
        }
        return number;    
    }
    public void IncreaseFoodNumber(int num) throws NullPointerException{
       
        int temp=num;
        if(num==50){
           temp=0;
        }
        
        try{
     
               
           query = "UPDATE R.kitchen SET R.kitchen.food='"+(temp+1)+"' WHERE R.kitchen.id='1'";
           
           
           PreparedStatement ps = co.prepareStatement(query);
           ps.executeUpdate();
           
        
          ps=null;
           


        }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! IncreaseFoodNumber"+e);
        }
        
     }
    public int getSeaFoodNumber() throws NullPointerException{
        int number=0;
        try{
                
                query = "SELECT seafood FROM R.kitchen WHERE R.kitchen.id= '1'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                rs.next();
                number=rs.getInt("seafood");
                
               
                
               ps=null;
                


        }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getFoodNumber"+e);
        }
        return number;    
    }
     public void IncreaseSeaFoodNumber(int num) throws NullPointerException{
        int temp=num;
        if(num==50){
           temp=0;
        }
        
        try{
     
               
           query = "UPDATE R.kitchen SET R.kitchen.seafood='"+(temp+1)+"' WHERE R.kitchen.id='1'";
           
           
           PreparedStatement ps = co.prepareStatement(query);
           ps.executeUpdate();
           
        
          ps=null;
           


        }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getAndIncreaseSeaFoodNumber"+e);
        }
        
     } 
     public ArrayList<Integer> getAllCheckedTableID() throws NullPointerException{
            ArrayList<Integer> a = new ArrayList<Integer>();
            
            try{
                
                query = "SELECT tid FROM R.table WHERE R.table.checked='YES'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    a.add(rs.getInt("tid"));
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getAllCheckedTableID:"+e);
            }
            return a;
        }
     public ArrayList<Integer> getAllCheckedTableID(int aid) throws NullPointerException{
            ArrayList<Integer> a = new ArrayList<Integer>();
            
            try{
                
                query = "SELECT tid FROM R.table WHERE R.table.checked='YES' AND R.table.aid=?";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, aid+"");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    a.add(rs.getInt("tid"));
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getAllCheckedTableID:"+e);
            }
            return a;
        }
        public void copyStorageToOrder(int cid) throws NullPointerException{
            
            PreparedStatement ps= null;
            
            try{
                

                insert = "INSERT R.order (tid, iid, aid, quantity,time_in,time_send,name,price,type,note,firstCourse,secondCourse,thirdCourse,seafood, other, drink, dessert, cake, sent, subtable,position) SELECT tid, iid, aid, quantity,time_in,time_send,name,price,type,note,firstCourse,secondCourse,thirdCourse,seafood, other, drink, dessert, cake, sent, subtable,position FROM R.storage LEFT JOIN R.check ON R.storage.cid=R.check.cid WHERE R.storage.cid = '"+cid+"'";
                ps = co.prepareStatement(insert);
                ps.executeUpdate();
                ps.close();

            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when copyOrderToStorage():"+e);
            }
        }
        
        
        public void inSertSingleItem(String tip, String discount, String coupon, String itemName, String itemFirst, String itemSecond, String itemThird, String itemSeafood, String itemOther, String itemDessert, String itemDrink, String itemPrice, String num, String notes, String type, String position, String dateTime, String subcheck, String tableID, String userID) throws NullPointerException
        {    
            PreparedStatement ps= null;
            try{


                insert = "INSERT INTO `R`.`order` (tip, discount, coupon, name, firstCourse, secondCourse, thirdCourse, seafood, other, dessert, drink, price, quantity, note, type, position , time_in ,subtable, tid , aid ) ";
                    insert += "VALUE ( '"+tip+"','"+discount+"','"+coupon+"','"+itemName+"','"+itemFirst+"','"+itemSecond+"','"+itemThird+"','"+itemSeafood+"','"+itemOther+"','"+itemDessert+"','"+itemDrink+"','"+itemPrice+"','"+num+"','"+notes+"','"+type+"','"+position+"','"+dateTime+"','"+subcheck+"','"+tableID+"','"+userID+"')";

                //textAfterUpdate = "["+type+"]"+" ["+itemName+"] [qunatity:"+num+"] [$"+itemPrice+"] [seat:"+position+"] [check:"+subcheck+"] [notes:"+notes+"]";

                ps = co.prepareStatement(insert);
                ps.executeUpdate();
                ps.close();


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when insert item:"+e);
            }
        }
        
        public void removeAllOrders(int tableID) throws NullPointerException{
        
        PreparedStatement ps = null;
       

        try {
            //ArrayList<Integer> a =this.getAllOIDFromOneTable(tableID);
            /*
            myQ.insertLog("Deleted All: ["+myQ.getOneOrderAllInfor(a.get(0)).get(1)+"] ["+myQ.getOneOrderAllInfor(a.get(0)).get(0)+"] [quantity:"+myQ.getOneOrderAllInfor(a.get(0)).get(3)+"] [$"+Double.parseDouble(myQ.getOneOrderAllInfor(a.get(0)).get(2))*Double.parseDouble(myQ.getOneOrderAllInfor(a.get(0)).get(3))+"] [seat:"+myQ.getOneOrderAllInfor(a.get(0)).get(6)+"] [check#"+myQ.getOneOrderAllInfor(a.get(0)).get(5)+"] [notes:"+myQ.getOneOrderAllInfor(a.get(0)).get(4)+"]", myQ.getTableID(a.get(0)), userID);
            this.updateLogArea();
            for(int i =1; i<a.size(); i++){
               myQ.insertLog("                  ["+myQ.getOneOrderAllInfor(a.get(i)).get(1)+"] ["+myQ.getOneOrderAllInfor(a.get(i)).get(0)+"] [quantity:"+myQ.getOneOrderAllInfor(a.get(i)).get(3)+"] [$"+Double.parseDouble(myQ.getOneOrderAllInfor(a.get(i)).get(2))*Double.parseDouble(myQ.getOneOrderAllInfor(a.get(i)).get(3))+"] [seat:"+myQ.getOneOrderAllInfor(a.get(i)).get(6)+"] [check#"+myQ.getOneOrderAllInfor(a.get(i)).get(5)+"] [notes:"+myQ.getOneOrderAllInfor(a.get(i)).get(4)+"]", myQ.getTableID(a.get(i)), userID);
               this.updateLogArea();
            }*/
            
            
            query = "DELETE FROM R.order WHERE R.order.tid=" + tableID;
            ps = co.prepareStatement(query);
            ps.executeUpdate();
            ps.close();

        } catch (Exception e) {
            System.out.println("error at jd removeAllOrders():" + e);
        
        }
    }// end of removeorderwithoid method
        
    public void updateSent(int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET sent = ? " + 
            "WHERE sent = 'NO' AND R.order.tid=?";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, "YES");
        ps.setInt(2, tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateSent() error:"+e);
        }
    }
    public void updateSeafoodSent(int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET sent = ? " + 
            "WHERE sent = 'NO' AND R.order.tid=? AND R.order.type ='seafood' ";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, "YES");
        ps.setInt(2, tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateSent() error:"+e);
        }
    }
    public void updateDrinkSent(int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET sent = ? " + 
            "WHERE sent = 'NO' AND R.order.tid=? AND R.order.type ='drink' ";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, "YES");
        ps.setInt(2, tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateDrinkSent() error:"+e);
        }
    }
    public void updateDessertSent(int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET sent = ? " + 
            "WHERE sent = 'NO' AND R.order.tid=? AND R.order.type ='dessert' ";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, "YES");
        ps.setInt(2, tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateDessertSent() error:"+e);
        }
    }
    public void updateFoodandSeafoodSent(int tableID) throws NullPointerException{
        String sql = 
            "update R.order " +
            "SET sent = " +
            " ( " +
            " CASE " +
                "WHEN R.order.type = 'seafood' " +
                "THEN 'YES' "     +
                "WHEN R.order.type = 'firstCourse' " +
                "THEN 'YES' "     +
                "WHEN R.order.type = 'secondCourse' " +
                "THEN 'YES' "     +
                "WHEN R.order.type = 'thirdCourse' " +
                "THEN 'YES' "     +
                "ELSE 'NO' "  +
            " END " +
           " ) "     + 
            "WHERE sent = 'NO' AND R.order.tid=?" ;   
        try{
       
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, ""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateFoodandSeafoodSent() error:"+e);
        }
    }
    public void updateFoodSent(int tableID) throws NullPointerException{
        String sql = 
            "update R.order " +
            "SET sent = " +
            " ( " +
            " CASE " +
                "WHEN R.order.type = 'firstCourse' " +
                "THEN ? "     +
                "WHEN R.order.type = 'secondCourse' " +
                "THEN ? "     +
                "WHEN R.order.type = 'thirdCourse' " +
                "THEN ? "     +
            " END " +
           " ) "     + 
            "WHERE sent = 'NO' AND R.order.tid=?" ;   
        try{
       
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, "YES");
        ps.setString(2, "YES");
        ps.setString(3, "YES");
       
        ps.setString(4, ""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateFoodSent() error:"+e);
        }
    }
    public void updateSentTime(int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET time_send = ? " + 
            "WHERE sent = 'NO' AND R.order.tid=?";
        try{
       
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, this.getDateTimeDBFormat());
        ps.setString(2, ""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateSentTime() error:"+e);
        }
    }
   public void updateSeafoodSentTime(int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET time_send = ? " + 
            "WHERE sent = 'NO' AND R.order.tid=? AND R.order.type = 'seafood' ";
        try{
       
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, this.getDateTimeDBFormat());
        ps.setString(2, ""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateSentTime() error:"+e);
        }
    }
   
    public void updateDrinkSentTime(int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET time_send = ? " + 
            "WHERE sent = 'NO' AND R.order.tid=? AND R.order.type = 'drink' ";
        try{
       
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, this.getDateTimeDBFormat());
        ps.setString(2, ""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateSentTime() error:"+e);
        }
    }
    public void updateDessertSentTime(int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET time_send = ? " + 
            "WHERE sent = 'NO' AND R.order.tid=? AND R.order.type = 'dessert' ";
        try{
       
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, this.getDateTimeDBFormat());
        ps.setString(2, ""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateSentTime() error:"+e);
        }
    }
    public void updateFoodandSeafoodSentTime(int tableID) throws NullPointerException{
        String sql = 
            "update R.order " +
            "SET time_send = " +
            " ( " +
            " CASE " +
                "WHEN R.order.type = 'seafood' " +
                "THEN ? "     +
                "WHEN R.order.type = 'firstCourse' " +
                "THEN ? "     +
                "WHEN R.order.type = 'secondCourse' " +
                "THEN ? "     +
                "WHEN R.order.type = 'thirdCourse' " +
                "THEN ? "     +
            " END " +
           " ) "     + 
            "WHERE sent = 'NO' AND R.order.tid=?" ;   
        try{
       
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, this.getDateTimeDBFormat());
        ps.setString(2, this.getDateTimeDBFormat());
        ps.setString(3, this.getDateTimeDBFormat());
        ps.setString(4, this.getDateTimeDBFormat());
        ps.setString(5, ""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateSentTime() error:"+e);
        }
    }public void updateFoodSentTime(int tableID) throws NullPointerException{
        String sql = 
            "update R.order " +
            "SET time_send = " +
            " ( " +
            " CASE " +
                "WHEN R.order.type = 'firstCourse' " +
                "THEN ? "     +
                "WHEN R.order.type = 'secondCourse' " +
                "THEN ? "     +
                "WHEN R.order.type = 'thirdCourse' " +
                "THEN ? "     +
            " END " +
           " ) "     + 
            "WHERE sent = 'NO' AND R.order.tid=?" ;   
        try{
       
        PreparedStatement ps = co.prepareStatement(sql);
      
        ps.setString(1, this.getDateTimeDBFormat());
        ps.setString(2, this.getDateTimeDBFormat());
        ps.setString(3, this.getDateTimeDBFormat());
        ps.setString(4, ""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateSentTime() error:"+e);
        }
    }
    public void updateTips(Double d,int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.table " + 
            "  SET service_charge = ?"+
            "WHERE tid = ?";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, ""+d);
        ps.setString(2, ""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateTip error:"+e);
        }
    }
    public boolean checkWhetherHasUnsendOrder(int tableID) throws NullPointerException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean hasNewOrder = false;
        try {
            
            query = "SELECT sent FROM R.order o WHERE o.tid =? ";
            ps = co.prepareStatement(query);
            ps.setString(1, "" + tableID);
            rs = ps.executeQuery();
            while(rs.next()){
                String sent = rs.getString("sent");
            
                if(sent.equals("NO")){
                    hasNewOrder = true;
                }
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("error at jd checkWhetherHasUnsendOrder:" + e);
        }
        return hasNewOrder;
    }
    public void updateDiscount(Double d,int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.table " + 
            "  SET discount = '"+d+"' "+
            "WHERE tid = '"+tableID+"'";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateDiscount error:"+e);
        }
    }
    public void updateTax(Double subTotal, int tableID) throws NullPointerException{
       
        Double tax = subTotal*State_Tax; 
        String sql = 
            "UPDATE R.table " + 
            "  SET tax = '"+tax+"' "+
            "WHERE tid = '"+tableID+"'";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateTip error:"+e);
        }
    }
    public void updateTotal(Double total, int tableID) throws NullPointerException{
       
        String sql = 
            "UPDATE R.table " + 
            "  SET total = '"+total+"' "+
            "WHERE tid = '"+tableID+"'";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateTotal error:"+e);
        }
    }
    public ArrayList<Integer> getSubTablesNameintoArray(int tableID) throws NullPointerException{
        
        
        ArrayList<Integer> subtable = new ArrayList<Integer>();
        try {
            
            query = "SELECT DISTINCT subtable FROM R.order o WHERE o.tid =?";
            PreparedStatement ps = co.prepareStatement(query);
            ps.setString(1, tableID+"");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                 subtable.add(rs.getInt("subtable"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("error at myQuery getSubTablesNameintoArray:" + e);
        }
        return subtable;
    
    }
    public void updateToUnSent(int oid, int tableID, int userID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET sent = 'NO' " + 
            "WHERE R.order.oid = '"+oid+"'";
        try{
           
        
        PreparedStatement ps = co.prepareStatement(sql);
        ps.executeUpdate();
        this.insertLog("Modified:  "+this.getOneOrderAllInfor(oid).get(0)+" x"+this.getOneOrderAllInfor(oid).get(3)+" $"+this.getOneOrderAllInfor(oid).get(2)+" -> unsend", tableID, userID);
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateToUnSent() error:"+e);
        }
    }
    public void updateToSent(int oid, int tableID, int userID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET sent = 'YES' " + 
            "WHERE R.order.oid = '"+oid+"'";
        try{
            PreparedStatement ps = co.prepareStatement(sql);
            ps.executeUpdate();
            this.insertLog("Modified:  "+this.getOneOrderAllInfor(oid).get(0)+" x"+this.getOneOrderAllInfor(oid).get(3)+" $"+this.getOneOrderAllInfor(oid).get(2)+" -> send", tableID, userID);
           ps=null;
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateToSent() error:"+e);
        }
    }
    public void updateSentTimeToNull(int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.order " + 
            "  SET time_send = ? " + 
            "WHERE sent = 'NO' AND R.order.tid=?";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, "");
        ps.setString(2,""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateSentTimeToNull() error:"+e);
        }
    }
    public void setTablePPL(int ppl, int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.table " + 
            "  SET ppl = ? " + 
            "WHERE tid = ?";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        ps.setString(1, ""+ppl);
        ps.setString(2, ""+tableID);
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("setTablePPL() error:"+e);
        }
    }
    public void updateTotal(int total, int tableID) throws NullPointerException{
        String sql = 
            "UPDATE R.table " + 
            "  SET total = '"+total+"' "+
            "WHERE tid = '"+tableID+"'";
        try{
        
        PreparedStatement ps = co.prepareStatement(sql);
        
        ps.executeUpdate();
        
           ps=null;
            
        
        }
        catch(NullPointerException e){         }catch(Exception e){
            System.out.println("updateTip error:"+e);
        }
    }
    public boolean checkWhetherHasOrder(int tableID) throws NullPointerException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean hasOrder = false;
        try {
            
            query = "SELECT COUNT(oid) AS NumberOfOrder FROM R.order o WHERE o.tid =?";
            ps = co.prepareStatement(query);
            ps.setString(1, "" + tableID);
            rs = ps.executeQuery();
            rs.next();
            int number = rs.getInt("NumberOfOrder");
            
            if(number>0){
                hasOrder = true;
            }else{
                hasOrder = false;
            }
        } catch (Exception e) {
            System.out.println("error at jd checkWhetherHasOrder:" + e);
        }finally{
                try {rs.close();
                     ps.close();
                     
                } catch (SQLException e) { /* ignored */}
            }
        return hasOrder;
    }
    
    public ArrayList<Object> addSubTableRowsToDataObject(int subtable, int tableID) throws NullPointerException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Object> dataObjectForSubTable = new ArrayList<Object>();
        try {
            
            query = "SELECT oid, name, quantity, position, price, sent FROM R.order o WHERE o.subTable=? AND o.tid=?";
            ps = co.prepareStatement(query);
            ps.setString(1, ""+subtable);
            ps.setString(2, ""+tableID);
            rs = ps.executeQuery();
            Double[] TDC = this.getTipDiscountCouponAtSubtable(tableID, subtable);
            String tip = "";
            String discount="";
            String coupon = "";
            if(TDC[0]!=0.00){
                tip="  Tip:"+TDC[0]*100+"%";
            }
            if(TDC[1]!=0.00){
                discount="  Off:"+TDC[1]*100+"%";
            }
            if(TDC[2]!=0.00){
                coupon="-"+TDC[2];
            }
            
            Object[] subTableNumberRow = new Object[]{" "," ","check #"+subtable+" "+tip+" "+discount, coupon ,moneyVariable.format(this.getOneSubCheckTotal(tableID, subtable)),"title",};
            dataObjectForSubTable.add(subTableNumberRow);
            while (rs.next()) {
                String seat ="";  // this variable is created for empty the fill if no seat asided.
                if(rs.getInt("position")!=0){
                        seat=Integer.toString(rs.getInt("position"));
                } 
                // here is to plug in all the data into jtable.
                Object[] row = new Object[]{rs.getInt("oid"),
                    seat,
                    rs.getString("name"),
                    rs.getInt("quantity") + "x",
                    moneyVariable.format(rs.getDouble("price")*(rs.getInt("quantity"))),
                    rs.getString("sent"),};
                
                dataObjectForSubTable.add(row);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("error at jd addSubTableRowsToDataObject:" + e);
        }
        return dataObjectForSubTable;
        
    }
    public ArrayList<Object> addTodaysAndFuturesWholeCakeOrdersToDataObject() throws NullPointerException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        NumberFormat myFormat = new DecimalFormat("#000000");
        String today = this.getDateDBFormat();
        ArrayList<Object> dataObjectForSubTable = new ArrayList<Object>();
        try {
            
            query = "SELECT wid,aid,since,price,addon,tax,total,date,time,cake,size,writing,customername,contactnumber,notes,printed,void,paid FROM R.wholecake WHERE R.wholecake.date>='"+today+"'";
            ps = co.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                // here is to plug in all the data into jtable.
                Object[] row = new Object[]{"No:."+myFormat.format(rs.getInt("wid")),
                    rs.getDate("date"),
                    rs.getString("time"),
                    rs.getString("cake"),
                    rs.getString("size"),
                    rs.getString("customername"),
                    rs.getString("contactnumber"),
                    rs.getInt("paid"),
                    this.getServerName(rs.getInt("aid")),
                    //moneyVariable.format(rs.getDouble("price")),
                    };
                
                dataObjectForSubTable.add(row);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("error at jd addSubTableRowsToDataObject:" + e);
        }
        return dataObjectForSubTable;
        
    }
    public ArrayList<Object> addTomorrowsWholeCakeOrdersToDataObject() throws NullPointerException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        NumberFormat myFormat = new DecimalFormat("#000000");
        String tomorrow = this.getTomorrowsDateDBFormat();
        ArrayList<Object> dataObjectForSubTable = new ArrayList<Object>();
        try {
            
            query = "SELECT wid,aid,since,price,addon,tax,total,date,time,cake,size,writing,customername,contactnumber,notes,printed,void,paid FROM R.wholecake WHERE R.wholecake.date='"+tomorrow+"'";
            ps = co.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                // here is to plug in all the data into jtable.
                Object[] row = new Object[]{"No:."+myFormat.format(rs.getInt("wid")),
                    rs.getDate("date"),
                    rs.getString("time"),
                    rs.getString("cake"),
                    rs.getString("size"),
                    rs.getString("customername"),
                    rs.getString("contactnumber"),
                    rs.getInt("paid"),
                    this.getServerName(rs.getInt("aid")),
                    //moneyVariable.format(rs.getDouble("price")),
                    };
                
                dataObjectForSubTable.add(row);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("error at jd addSubTableRowsToDataObject:" + e);
        }
        return dataObjectForSubTable;
        
    }
    public ArrayList<Object> addTodaysWholeCakeOrdersToDataObject() throws NullPointerException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        NumberFormat myFormat = new DecimalFormat("#000000");
        String today = this.getDateDBFormat();
        ArrayList<Object> dataObjectForSubTable = new ArrayList<Object>();
        try {
            
            query = "SELECT wid,aid,since,price,addon,tax,total,date,time,cake,size,writing,customername,contactnumber,notes,printed,void,paid FROM R.wholecake WHERE R.wholecake.date='"+today+"'";
            ps = co.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                // here is to plug in all the data into jtable.
                Object[] row = new Object[]{"No:."+myFormat.format(rs.getInt("wid")),
                    rs.getDate("date"),
                    rs.getString("time"),
                    rs.getString("cake"),
                    rs.getString("size"),
                    rs.getString("customername"),
                    rs.getString("contactnumber"),
                    rs.getInt("paid"),
                    this.getServerName(rs.getInt("aid")),
                    //moneyVariable.format(rs.getDouble("price")),
                    };
                
                dataObjectForSubTable.add(row);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("error at jd addSubTableRowsToDataObject:" + e);
        }
        return dataObjectForSubTable;
        
    }
    public ArrayList<Object> addWholeCakeOrdersToDataObject() throws NullPointerException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        NumberFormat myFormat = new DecimalFormat("#000000");
        ArrayList<Object> dataObjectForSubTable = new ArrayList<Object>();
        try {
            
            query = "SELECT wid,aid,since,price,addon,tax,total,date,time,cake,size,writing,customername,contactnumber,notes,printed,void,paid FROM R.wholecake";
            ps = co.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                // here is to plug in all the data into jtable.
                Object[] row = new Object[]{"No:."+myFormat.format(rs.getInt("wid")),
                    rs.getDate("date"),
                    rs.getString("time"),
                    rs.getString("cake"),
                    rs.getString("size"),
                    rs.getString("customername"),
                    rs.getString("contactnumber"),
                    rs.getInt("paid"),
                    this.getServerName(rs.getInt("aid")),
                    //moneyVariable.format(rs.getDouble("price")),
                    };
                
                dataObjectForSubTable.add(row);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("error at jd addSubTableRowsToDataObject:" + e);
        }
        return dataObjectForSubTable;
        
    }
    public Object[] getWholeCakeOrderWithWid(int wid) throws NullPointerException{
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Object[] temp = new Object[17];
        try {
            
            query = "SELECT wid,aid,since,price,addon,tax,total,date,time,cake,size,writing,customername,contactnumber,notes,printed,void,paid FROM R.wholecake WHERE R.wholecake.wid='"+ wid+"'";
            ps = co.prepareStatement(query);
            rs = ps.executeQuery();
            rs.next();
                // here is to plug in all the data into jtable.
                    temp[0] =rs.getString("cake");
                    temp[1] =rs.getString("size");
                    temp[2] =rs.getDate("date");
                    temp[3] =rs.getString("time");
                    temp[4] =rs.getString("writing");
                    temp[5] =rs.getString("customername");
                    temp[6] =rs.getString("contactnumber");
                    temp[7] =rs.getString("notes");
                    temp[8] =rs.getInt("aid");
                    temp[9] =rs.getDouble("price");
                    temp[10]=rs.getDouble("addon");
                    temp[11]=rs.getDouble("tax");
                    temp[12]=rs.getDouble("total");
                    temp[13]=rs.getInt("wid");
                    temp[14]=rs.getInt("printed");
                    temp[15]=rs.getInt("void");
                    temp[16]=rs.getInt("paid");

                    
                

           
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("error at jd getWholeCakeOrderWithWid:" + e);
        }
        return temp;
        
    }
    public Integer[] getTableIDandPPL(String tableNum) throws NullPointerException{
        Integer[] o = new Integer[2];
        try{
           
            query = "SELECT tid,ppl FROM R.table WHERE R.table.tname='"+tableNum+"'";
            PreparedStatement ps = co.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        
            while(rs.next()){
                o[0]=rs.getInt("tid");
                o[1]=rs.getInt("ppl");
                
            }
           rs=null;
           ps=null;
            
            
        
        }catch(NullPointerException e){         }catch(Exception e){
            System.out.println("error!!! getTableIDandPPL:"+e);
        }
        return o;
        
    }
    private ArrayList<Object> o_getOpenedTableStatusAndTname = new ArrayList<Object>();
   
   
    public  ArrayList<Object> getOpenedTableStatusAndTname() throws NullPointerException{
        query = "";
     
        
        try{
            
            
            rs_getOpenedTableStatusAndTname = ps_getOpenedTableStatusAndTname.executeQuery();
        
            while(rs_getOpenedTableStatusAndTname.next()){
                Object[] ob = new Object[]{rs_getOpenedTableStatusAndTname.getInt("status"),rs_getOpenedTableStatusAndTname.getString("tname")};
                o_getOpenedTableStatusAndTname.add(ob);
            }
            
           rs_getOpenedTableStatusAndTname=null;
      
            
            
        }catch(NullPointerException e){
        }catch(Exception e){
            System.out.println("error!!! getOpenedTableStatusAndTname():"+e);
        }
        return o_getOpenedTableStatusAndTname;
    }
    public int getLastOrderForVoid(int tid) throws NullPointerException{
            int cid=0;
            try{
                query = "SELECT MAX(oid) oid FROM R.order WHERE R.order.tid=?";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1,""+tid);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    cid=rs.getInt("oid");
                }
               rs=null;
               ps=null;
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getLastOrderForVoid "+e);
            }

            return cid;
     }
        public boolean isTableEmpty(int tid) throws NullPointerException{
            boolean b = false;
            try{
                query = "SELECT COUNT(*) AS c FROM R.order WHERE R.order.tid= ? ";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1,""+tid);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    if(rs.getInt("c")==0) b=true;
                }
               rs=null;
               ps=null;
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! isTableEmpty "+e);
            }
            return b;
        }
        public Double[] getTipDiscountCouponAtSubtable(int tid, int subtable) throws NullPointerException{
            
            Double[] d= new Double[3];
            d[0] =0.00;
            d[1] =0.00;
            d[2] =0.00;
            try{
                query = "SELECT tip, discount, coupon FROM R.order WHERE R.order.tid= ? AND R.order.subtable= ? ";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1,""+tid);
                ps.setString(2,""+subtable);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                   
                    if(rs.getDouble("tip")!=0.00 ){d[0]=rs.getDouble("tip");}
                    if(rs.getDouble("discount")!=0.00){d[1]=rs.getDouble("discount");}
                    if(rs.getDouble("coupon")!=0.00){d[2]=rs.getDouble("coupon");}
                }
               rs=null;
               ps=null;
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTipDiscountCouponAtSubtable "+e);
            }
            return d;
        }
        public int getSubtableFromOid(int oid) throws NullPointerException{
        
        
            int subtable = 0;
            try {

                query = "SELECT subtable FROM R.order o WHERE o.oid =?";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, oid+"");
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    subtable =rs.getInt("subtable");
                }
                rs.close();
                ps.close();
            } catch (Exception e) {
                System.out.println("error at myQuery getSubtableFromOid:" + e);
            }
            return subtable;

        }
        
        public void updateSubtableTips(Double d,int tid, int sid) throws NullPointerException{
            String sql = 
                "UPDATE R.order " + 
                "  SET tip = ?"+
                "WHERE tid = ? And subtable= ?";
            try{

            PreparedStatement ps = co.prepareStatement(sql);
            ps.setString(1, ""+d);
            ps.setString(2, ""+tid);
            ps.setString(3, ""+sid);
            ps.executeUpdate();

               ps=null;


            }
            catch(NullPointerException e){         }catch(Exception e){
                System.out.println("updateSubtableTips error:"+e);
            }
        }
        public void updateSubtableDiscount(Double d,int tid, int sid) throws NullPointerException{
            String sql = 
                "UPDATE R.order " + 
                "  SET discount = ? "+
                "WHERE tid = ? AND subtable= ?" ;
            try{

            PreparedStatement ps = co.prepareStatement(sql);
            ps.setString(1, ""+d);
            ps.setString(2, ""+tid);
            ps.setString(3, ""+sid);
            ps.executeUpdate();

               ps=null;


            }
            catch(NullPointerException e){         }catch(Exception e){
                System.out.println("updateDiscount error:"+e);
            }
        }
        public Double calculateAllcheckSFinalTotal(int tableID) throws NullPointerException{
            Double value =0.00;
            try{
            
                query = "SELECT * FROM R.order WHERE R.order.tid='"+tableID+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    int quantity = rs.getInt("quantity");
                    Double price =rs.getDouble("price");
                    Double tip =rs.getDouble("tip");
                    Double discount =rs.getDouble("discount");
                    Double coupon =rs.getDouble("coupon");
                   
                    double subtotal =  quantity*price*State_Tax+quantity*price;
                    double tips = subtotal*tip;
                    double discounts = subtotal*discount;
                    double total = subtotal+tips-discounts-coupon;
                    value += total;
                }
               rs=null;
               ps=null;



            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! calculateSubTotal:"+e);
            }

            return value;
        }
        public String[] getAllTableName() throws NullPointerException{
            String[] tname = new String[1000];
            int i=0;
            try{
                
                query = "SELECT tname FROM R.table";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    tname[i]=rs.getString("tname"); 
                    i++;
                    
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getAllTableName:"+e);
            }
            String[] name = new String[i];
            for(int ii=0; ii<i; ii++ ){
                name[ii]=tname[ii];
            }
            
            if(i==0){
                name= new String[1];
                name[0]="No Table";
            }
            
            return name;
        }
        public void moveTable(int tid, int newtid) throws NullPointerException{
            try{
                
                String update ="UPDATE R.table t, R.table t1 "+
                                "SET t.aid = t1.aid, t.activate = t1.activate, t.ppl = t1.ppl, t.since=t1.since, t.status=t1.status, t.total=t1.total, t.userID=t1.userID, t.timelock=t1.timelock, t.checked=t1.checked "+
                                "WHERE t.tid = ? " +
                                "AND t1.tid = ? ";
                PreparedStatement ps = co.prepareStatement(update);
                ps.setString(1, ""+newtid);
                ps.setString(2, ""+tid);
                
                ps.executeUpdate();
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! moveTable():"+e);
            }
        }
        public int getTableID(String tablename) throws NullPointerException{

            int tid =0;
            try{
                
                query = "SELECT tid FROM R.table WHERE R.table.tname='"+tablename+"'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    tid=rs.getInt("tid");
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableID:"+e);
            }
            return tid;
        }
        public void moveTableOrders(int tid, int newtid) throws NullPointerException{
            try{
                
                String update ="UPDATE R.order t "+
                                "SET t.tid = ? "+
                                "WHERE t.tid = ?";
                                
                PreparedStatement ps = co.prepareStatement(update);
                ps.setString(1, ""+newtid);
                ps.setString(2, ""+tid);
                
                ps.executeUpdate();
               ps=null;
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! moveTableOrders():"+e);
            }
        }
        public String[] login(int ssn) throws NullPointerException{
            String[] s = new String[3];
            s[0] ="not found";
            s[1]="not found";
            try{
                
                query = "SELECT * FROM R.account WHERE R.account.ssn=?";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, ""+ssn);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    s[0]=rs.getString("aname");
                    s[1]=rs.getString("type");
                    Integer id =rs.getInt("aid");
                    s[2] = id.toString();
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableID:"+e);
            }
            
            return s;
        }
        public void setTableAID(int aid, int tableID) throws NullPointerException{
            String sql = 
                "UPDATE R.table " + 
                "  SET aid = ? " + 
                "WHERE tid = ?";
            try{

            PreparedStatement ps = co.prepareStatement(sql);
            ps.setString(1, ""+aid);
            ps.setString(2, ""+tableID);
            ps.executeUpdate();

               ps=null;


            }
            catch(NullPointerException e){         }catch(Exception e){
                System.out.println("setTableAID() error:"+e);
            }
        }
        public void setTableUserID(int aid, int tableID) throws NullPointerException{
            String sql = 
                "UPDATE R.table " + 
                "  SET userID = ? " + 
                "WHERE tid = ?";
            try{

            PreparedStatement ps = co.prepareStatement(sql);
            ps.setString(1, ""+aid);
            ps.setString(2, ""+tableID);
            ps.executeUpdate();

               ps=null;


            }
            catch(NullPointerException e){         }catch(Exception e){
                System.out.println("setTableAID() error:"+e);
            }
        }
        public boolean isPasswordAlreadyUsed(int password) throws NullPointerException{
            boolean found = false;
            try{
                
                query = "SELECT * FROM R.account WHERE R.account.ssn=?";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, ""+password);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    found = true;
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableID:"+e);
            }
            
            return found;
        }
        public void insertNewAccount(String aid, String password, String type) throws NullPointerException{
            
            PreparedStatement ps= null;
           
            try{
                
                insert = "INSERT INTO `R`.`account` (aname, ssn, type) ";
                    insert += "VALUE ( ?,?,?)";
                ps = co.prepareStatement(insert);
                ps.setString(1,""+aid);
                ps.setString(2,""+password);
                ps.setString(3,""+type);
                ps.executeUpdate();
                ps.close();
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error occur when insertNewAccount :"+e);
            }
        }
//        public Double calculateOneSubCheckTotal(int tableID, int sid) throws NullPointerException{
//            Double value =0.00;
//            try{
//            
//                query = "SELECT * FROM R.order WHERE R.order.tid=? AND R.order.subtable=?";
//                PreparedStatement ps = co.prepareStatement(query);
//                ps.setString(1,""+tableID);
//                ps.setString(2,""+sid);
//                ResultSet rs = ps.executeQuery();
//
//                while(rs.next()){
//                    int quantity = rs.getInt("quantity");
//                    Double price =rs.getDouble("price");
//                    Double tip =rs.getDouble("tip");
//                    Double discount =rs.getDouble("discount");
//                    Double coupon =rs.getDouble("coupon");
//                   
//                    double subtotal =  quantity*price*State_Tax+quantity*price;
//                    double tips = subtotal*tip;
//                    double discounts = subtotal*discount;
//                    double total = subtotal+tips-discounts-coupon;
//                    value += total;
//                }
//                ps=null;
//                rs=null;
//
//
//
//            }catch(NullPointerException e){         }catch(Exception e){
//                System.out.println("error!!! calculateOneSubCheckTotal:"+e);
//            }
//
//            return value;
//        }
        public Double getOneSubCheckTotal(int tableID, int sid){
            double subTotal = this.calculateSubTotal(tableID,sid);
            double tax = subTotal*State_Tax;
            double discount = this.getTableDiscount(tableID,sid);
            double tip = this.getTableTips(tableID,sid);
            double coupon = this.getTableCoupon(tableID,sid);
            double grouponTotal = this.getGrouponTotal(tableID, sid);

            double discounted_amount= (subTotal+tax)*discount;
            double total = subTotal + tax - discounted_amount;
            double tips = total*tip;
            double tippedTotal = total+tips;
            double couponedTotal = tippedTotal-coupon-grouponTotal;
            return couponedTotal;
        }
        public void deleteCheck(int id)  throws NullPointerException{
            
            PreparedStatement ps = null;
           
            int oid = id;
            try {
                query = "DELETE FROM R.check WHERE R.check.cid='"+oid+"'";
                ps = co.prepareStatement(query);
                ps.executeUpdate();
                //ArrayList<String> a = this.getOneOrderAllInfor(oid);
                //System.out.print(a.get(0).toString());
                ps.close();
            }catch(NullPointerException e){         } catch (Exception e) {
                System.out.println("error at jd deleteCheck():" + e);

            }
        }// end of removeorderwithoid method 
        public void voidEmptyWholeCakeCheck(int id)  throws NullPointerException{
            
            PreparedStatement ps = null;
           
            int wid = id;
            try {
                query = "DELETE FROM R.wholecake WHERE R.wholecake.wid='"+wid+"'";
                ps = co.prepareStatement(query);
                ps.executeUpdate();
                //ArrayList<String> a = this.getOneOrderAllInfor(oid);
                //System.out.print(a.get(0).toString());
                ps.close();
            }catch(NullPointerException e){         } catch (Exception e) {
                System.out.println("error at jd deleteCheck():" + e);

            }
        }
        public int getCidAtWheverMissedNumber() throws NullPointerException{
            ArrayList<Integer> a = new ArrayList<Integer>();
            Collections.sort(a);
            int cid = 0;
            int cid_= 0;
            int re = -1;
            try{
                
                query = "SELECT cid FROM R.check";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                   a.add(rs.getInt("cid"));
                  
                }
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getTableID:"+e);
            }
            
            Collections.sort(a);
            
            for(int i=0; i<a.size(); i++){
                cid=cid_;
                cid_ = a.get(i);
                
                if(cid!=(cid_-1)){
                    re=cid+1;
                }
            }
            
            if(re!=-1){
                return re;
            }else{
                return cid_+1;
            }
            
        }
         /**
         * 
         * @param courseName
         * @param tid
         * @param type   true = print all food order no matter sent or not,  false = print only un-send food orders
         * @return 
         */
        public String getfirstCourseALLPosition(String courseName, int tid, String type){
            String temp="";
            ArrayList<Integer> stackedSeat = new ArrayList<Integer>();
            try{
                if(type.equals("PrintAll")){ //PrintAll = print all food order no matter sent or not,
                    query = "SELECT position FROM R.order WHERE R.order.firstCourse ='"+courseName+"' AND R.order.firstCourse<>'' AND R.order.tid='"+tid+"'";
                }else if(type.equals("PrintNotSend")){ // PrintNotSend = print only un-send food orders
                    query = "SELECT position FROM R.order WHERE R.order.firstCourse ='"+courseName+"' AND R.order.sent='NO' AND R.order.firstCourse<>'' AND R.order.tid='"+tid+"'";
                }
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    int p= rs.getInt("position");
                    
                    if(p>0)
                        stackedSeat.add(p);
                }
                Collections.sort(stackedSeat);
                for(int i=0; i<stackedSeat.size(); i++){
                    temp+= "["+stackedSeat.get(i)+"]"; 
                }        
                
                
               rs=null;
               ps=null;
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getfirstCourseALLPosition():"+e);
            }
            return temp;
        }
        /**
         * 
         * @param courseName
         * @param tid
         * @param type   true = print all food order no matter sent or not,  false = print only un-send food orders
         * @return 
         */
        public String getSecondCourseALLPosition(String courseName, int tid, String type){
            String temp="";
            ArrayList<Integer> stackedSeat = new ArrayList<Integer>();
            try{
                if(type.equals("PrintAll")){//PrintAll = print all food order no matter sent or not,
                    query = "SELECT position FROM R.order WHERE R.order.secondCourse ='"+courseName+"'  AND R.order.secondCourse<>'' AND R.order.tid='"+tid+"'";
                }else if(type.equals("PrintNotSend")){// PrintNotSend = print only un-send food orders
                    query = "SELECT position FROM R.order WHERE R.order.secondCourse ='"+courseName+"'  AND R.order.sent='NO' AND R.order.secondCourse<>'' AND R.order.tid='"+tid+"'";
                }PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    int p= rs.getInt("position");
                    
                    if(p>0)
                        stackedSeat.add(p);
                }
                Collections.sort(stackedSeat);
                for(int i=0; i<stackedSeat.size(); i++){
                    temp+= "["+stackedSeat.get(i)+"]"; 
                }        
                
                
               rs=null;
               ps=null;
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getSecondCourseALLPosition():"+e);
            }
            System.out.println("seat:"+temp);
            return temp;
        }
         /**
         * 
         * @param courseName
         * @param tid
         * @param type   true = print all food order no matter sent or not,  false = print only un-send food orders
         * @return 
         */
        public String getThirdCourseALLPosition(String courseName,int tid, String type){
            String temp="";
            ArrayList<Integer> stackedSeat = new ArrayList<Integer>();
            try{
                if(type.equals("PrintAll")){//PrintAll = print all food order no matter sent or not,
                    query = "SELECT position FROM R.order WHERE R.order.thirdCourse ='"+courseName+"' AND R.order.thirdCourse<>'' AND R.order.tid='"+tid+"'";
                }else if(type.equals("PrintNotSend")){// PrintNotSend = print only un-send food orders
                    query = "SELECT position FROM R.order WHERE R.order.thirdCourse ='"+courseName+"' AND R.order.sent='NO' AND R.order.thirdCourse<>'' AND R.order.tid='"+tid+"'";
                }
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    int p= rs.getInt("position");
                    
                    if(p>0)
                        stackedSeat.add(p);
                }
                Collections.sort(stackedSeat);
                for(int i=0; i<stackedSeat.size(); i++){
                    temp+= "["+stackedSeat.get(i)+"]"; 
                }        
                
                
               rs=null;
               ps=null;
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getThirdCourseALLPosition():"+e);
            }
            
            return temp;
        }
         /**
         * 
         * @param courseName
         * @param tid
         * @param type   true = print all food order no matter sent or not,  false = print only un-send food orders
         * @return 
         */
        public String getSeafoodALLPosition(String courseName, int tid, String type){
            String temp="";
            ArrayList<Integer> stackedSeat = new ArrayList<Integer>();
            try{
                if(type.equals("PrintAll")){//PrintAll = print all food order no matter sent or not,
                    query = "SELECT position FROM R.order WHERE R.order.seafood ='"+courseName+"' AND R.order.seafood<>'' AND R.order.tid='"+tid+"'";
                }else if(type.equals("PrintNotSend")){// PrintNotSend = print only un-send food orders
                    query = "SELECT position FROM R.order WHERE R.order.seafood ='"+courseName+"' AND R.order.sent='NO' AND R.order.seafood<>'' AND R.order.tid='"+tid+"'";
                }
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    int p= rs.getInt("position");
                    
                    if(p>0)
                        stackedSeat.add(p);
                }
                Collections.sort(stackedSeat);
                for(int i=0; i<stackedSeat.size(); i++){
                    temp+= "["+stackedSeat.get(i)+"]"; 
                }        
                
                
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getSeafoodALLPosition():"+e);
            }
            return temp;
        }
        
//        public ArrayList<Object> getNewOrders(int tid){
//            ArrayList<Object>  temp        =  new ArrayList<Object>();
//                    
//                    
//            ArrayList<String>  firstCourse = new ArrayList<String>();
//            ArrayList<String>  secondCourse= new ArrayList<String>();
//            ArrayList<String>  thirdCourse = new ArrayList<String>();
//            ArrayList<String>  seafood     = new ArrayList<String>();
//            ArrayList<Integer> quantity    = new ArrayList<Integer>();
//            ArrayList<Integer> quantity_1  = new ArrayList<Integer>();
//            ArrayList<Integer> quantity_2  = new ArrayList<Integer>();
//            ArrayList<Integer> quantity_3  = new ArrayList<Integer>();
//            ArrayList<String>  seat        = new ArrayList<String>();
//            ArrayList<String>  seat_1      = new ArrayList<String>();
//            ArrayList<String>  seat_2      = new ArrayList<String>();
//            ArrayList<String>  seat_3      = new ArrayList<String>();
//            
//            try{
//                query = "SELECT firstCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.firstCourse<>'' GROUP BY R.order.firstCourse";
//                PreparedStatement ps = co.prepareStatement(query);
//                ResultSet rs = ps.executeQuery();
//
//                while(rs.next()){
//                    String f =rs.getString("firstCourse");
//                    firstCourse.add(f);
//                    quantity.add(rs.getInt("q"));
//                    int countSeat =rs.getInt("p");
//                    if(countSeat>1){
//                        seat.add(getfirstCourseALLPosition(f, tid));
//                    }else{
//                        
//                        seat.add("["+rs.getInt("position")+"]");
//                    }    
//                }
//                
//                String query1 = "SELECT secondCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.secondCourse<>'' GROUP BY R.order.secondCourse";
//                PreparedStatement ps1 = co.prepareStatement(query1);
//                ResultSet rs1 = ps1.executeQuery();
//
//                while(rs1.next()){
//                    String f =rs1.getString("secondCourse");
//                    secondCourse.add(f);
//                    quantity_1.add(rs1.getInt("q"));
//                    int countSeat =rs1.getInt("p");
//                    if(countSeat>1){
//                        seat_1.add(getSecondCourseALLPosition(f, tid));
//                    }else{
//                        
//                        seat_1.add("["+rs1.getInt("position")+"]");
//                    }    
//                }
//                
//                String query2 = "SELECT thirdCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.thirdCourse<>'' GROUP BY R.order.thirdCourse";
//                PreparedStatement ps2 = co.prepareStatement(query2);
//                ResultSet rs2 = ps2.executeQuery();
//
//                while(rs2.next()){
//                    String f =rs2.getString("thirdCourse");
//                    thirdCourse.add(f);
//                    quantity_2.add(rs2.getInt("q"));
//                    int countSeat =rs2.getInt("p");
//                    if(countSeat>1){
//                        seat_2.add(getThirdCourseALLPosition(f, tid));
//                    }else{
//                        
//                        seat_2.add("["+rs2.getInt("position")+"]");
//                    }    
//                }
//                
//                String query3 = "SELECT seafood, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.seafood<>'' GROUP BY R.order.seafood";
//                PreparedStatement ps3 = co.prepareStatement(query3);
//                ResultSet rs3 = ps3.executeQuery();
//
//                while(rs3.next()){
//                    String f =rs3.getString("seafood");
//                    seafood.add(f);
//                    quantity_3.add(rs3.getInt("q"));
//                    int countSeat =rs3.getInt("p");
//                    if(countSeat>1){
//                        seat_3.add(getThirdCourseALLPosition(f, tid));
//                    }else{
//                        
//                        seat_3.add("["+rs3.getInt("position")+"]");
//                    }    
//                }
//                
//               rs=null;
//               ps=null;
//                myconnection.closeRs(rs1);
//                myconnection.closePs(ps1);
//                myconnection.closeRs(rs2);
//                myconnection.closePs(ps2);
//                myconnection.closeRs(rs3);
//                myconnection.closePs(ps3);
//               
//            }catch(NullPointerException e){         }catch(Exception e){
//                System.out.println("error!!! getNewOrders():"+e);
//            }
//            temp.add(firstCourse );
//            temp.add(secondCourse);
//            temp.add(thirdCourse );
//            temp.add(seafood     );
//            temp.add(quantity    );
//            temp.add(quantity_1  );
//            temp.add(quantity_2  );
//            temp.add(quantity_3  );
//            temp.add(seat        );
//            temp.add(seat_1      );
//            temp.add(seat_2      );
//            temp.add(seat_3      );
//            
//            
//            
//            
//            return temp;
//        }
//        public ArrayList<Object> getAllSeafoodAndFoodOrders(int tid){
//            ArrayList<Object>  temp        =  new ArrayList<Object>();
//                    
//                    
//            ArrayList<String>  firstCourse = new ArrayList<String>();
//            ArrayList<String>  secondCourse= new ArrayList<String>();
//            ArrayList<String>  thirdCourse = new ArrayList<String>();
//            ArrayList<String>  seafood     = new ArrayList<String>();
//            ArrayList<Integer> quantity    = new ArrayList<Integer>();
//            ArrayList<Integer> quantity_1  = new ArrayList<Integer>();
//            ArrayList<Integer> quantity_2  = new ArrayList<Integer>();
//            ArrayList<Integer> quantity_3  = new ArrayList<Integer>();
//            ArrayList<String>  seat        = new ArrayList<String>();
//            ArrayList<String>  seat_1      = new ArrayList<String>();
//            ArrayList<String>  seat_2      = new ArrayList<String>();
//            ArrayList<String>  seat_3      = new ArrayList<String>();
//            
//            try{
//                query = "SELECT firstCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.firstCourse<>'' GROUP BY R.order.firstCourse";
//                PreparedStatement ps = co.prepareStatement(query);
//                ResultSet rs = ps.executeQuery();
//
//                while(rs.next()){
//                    String f =rs.getString("firstCourse");
//                    firstCourse.add(f);
//                    quantity.add(rs.getInt("q"));
//                    int countSeat =rs.getInt("p");
//                    if(countSeat>1){
//                        seat.add(getfirstCourseALLPosition(f, tid));
//                    }else{
//                        
//                        seat.add("["+rs.getInt("position")+"]");
//                    }    
//                }
//                
//                String query1 = "SELECT secondCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.secondCourse<>'' GROUP BY R.order.secondCourse";
//                PreparedStatement ps1 = co.prepareStatement(query1);
//                ResultSet rs1 = ps1.executeQuery();
//
//                while(rs1.next()){
//                    String f =rs1.getString("secondCourse");
//                    secondCourse.add(f);
//                    quantity_1.add(rs1.getInt("q"));
//                    int countSeat =rs1.getInt("p");
//                    if(countSeat>1){
//                        seat_1.add(getSecondCourseALLPosition(f, tid));
//                    }else{
//                        
//                        seat_1.add("["+rs1.getInt("position")+"]");
//                    }    
//                }
//                
//                String query2 = "SELECT thirdCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.thirdCourse<>'' GROUP BY R.order.thirdCourse";
//                PreparedStatement ps2 = co.prepareStatement(query2);
//                ResultSet rs2 = ps2.executeQuery();
//
//                while(rs2.next()){
//                    String f =rs2.getString("thirdCourse");
//                    thirdCourse.add(f);
//                    quantity_2.add(rs2.getInt("q"));
//                    int countSeat =rs2.getInt("p");
//                    if(countSeat>1){
//                        seat_2.add(getThirdCourseALLPosition(f, tid));
//                    }else{
//                        
//                        seat_2.add("["+rs2.getInt("position")+"]");
//                    }    
//                }
//                
//                String query3 = "SELECT seafood, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.seafood<>'' GROUP BY R.order.seafood";
//                PreparedStatement ps3 = co.prepareStatement(query3);
//                ResultSet rs3 = ps3.executeQuery();
//
//                while(rs3.next()){
//                    String f =rs3.getString("seafood");
//                    seafood.add(f);
//                    quantity_3.add(rs3.getInt("q"));
//                    int countSeat =rs3.getInt("p");
//                    if(countSeat>1){
//                        seat_3.add(getThirdCourseALLPosition(f, tid));
//                    }else{
//                        
//                        seat_3.add("["+rs3.getInt("position")+"]");
//                    }    
//                }
//                
//                
//               rs=null;
//               ps=null;
//                myconnection.closeRs(rs1);
//                myconnection.closePs(ps1);
//                myconnection.closeRs(rs2);
//                myconnection.closePs(ps2);
//                myconnection.closeRs(rs3);
//                myconnection.closePs(ps3);
//               
//            }catch(NullPointerException e){         }catch(Exception e){
//                System.out.println("error!!! getNewOrders():"+e);
//            }
//            temp.add(firstCourse );
//            temp.add(secondCourse);
//            temp.add(thirdCourse );
//            temp.add(seafood     );
//            temp.add(quantity    );
//            temp.add(quantity_1  );
//            temp.add(quantity_2  );
//            temp.add(quantity_3  );
//            temp.add(seat        );
//            temp.add(seat_1      );
//            temp.add(seat_2      );
//            temp.add(seat_3      );
//            
//            
//            
//            
//            return temp;
//        }
//        public ArrayList<Object> getSeafoodOrders(int tid){
//            ArrayList<Object>  temp        =  new ArrayList<Object>();
//                    
//                    
//            ArrayList<String>  firstCourse = new ArrayList<String>();
//            ArrayList<String>  secondCourse= new ArrayList<String>();
//            ArrayList<String>  thirdCourse = new ArrayList<String>();
//            ArrayList<String>  seafood     = new ArrayList<String>();
//            ArrayList<Integer> quantity    = new ArrayList<Integer>();
//            ArrayList<Integer> quantity_1  = new ArrayList<Integer>();
//            ArrayList<Integer> quantity_2  = new ArrayList<Integer>();
//            ArrayList<Integer> quantity_3  = new ArrayList<Integer>();
//            ArrayList<String>  seat        = new ArrayList<String>();
//            ArrayList<String>  seat_1      = new ArrayList<String>();
//            ArrayList<String>  seat_2      = new ArrayList<String>();
//            ArrayList<String>  seat_3      = new ArrayList<String>();
//            
//            try{
//                
//                
//                String query3 = "SELECT seafood, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.seafood<>'' GROUP BY R.order.seafood";
//                PreparedStatement ps3 = co.prepareStatement(query3);
//                ResultSet rs3 = ps3.executeQuery();
//
//                while(rs3.next()){
//                    String f =rs3.getString("seafood");
//                    seafood.add(f);
//                    quantity_3.add(rs3.getInt("q"));
//                    int countSeat =rs3.getInt("p");
//                    if(countSeat>1){
//                        seat_3.add(getThirdCourseALLPosition(f, tid, printSentOrNotSendOrAll));
//                    }else{
//                        
//                        seat_3.add("["+rs3.getInt("position")+"]");
//                    }    
//                }
//                
//                
//                myconnection.closeRs(rs3);
//                myconnection.closePs(ps3);
//               
//            }catch(NullPointerException e){         }catch(Exception e){
//                System.out.println("error!!! getNewOrders():"+e);
//            }
//            temp.add(firstCourse );
//            temp.add(secondCourse);
//            temp.add(thirdCourse );
//            temp.add(seafood     );
//            temp.add(quantity    );
//            temp.add(quantity_1  );
//            temp.add(quantity_2  );
//            temp.add(quantity_3  );
//            temp.add(seat        );
//            temp.add(seat_1      );
//            temp.add(seat_2      );
//            temp.add(seat_3      );
//            
//            
//            
//            
//            return temp;
//        }
        
        public ArrayList<Object> get1st2rd3thAndSeafoodOrders(int tid, String printSentOrNotSendOrAll){
            ArrayList<Object>  temp        =  new ArrayList<Object>();
                    
                    
            ArrayList<String>  firstCourse = new ArrayList<String>();
            ArrayList<String>  secondCourse= new ArrayList<String>();
            ArrayList<String>  thirdCourse = new ArrayList<String>();
            ArrayList<String>  seafood     = new ArrayList<String>();
            ArrayList<Integer> quantity    = new ArrayList<Integer>();
            ArrayList<Integer> quantity_1  = new ArrayList<Integer>();
            ArrayList<Integer> quantity_2  = new ArrayList<Integer>();
            ArrayList<Integer> quantity_3  = new ArrayList<Integer>();
            ArrayList<String>  seat        = new ArrayList<String>();
            ArrayList<String>  seat_1      = new ArrayList<String>();
            ArrayList<String>  seat_2      = new ArrayList<String>();
            ArrayList<String>  seat_3      = new ArrayList<String>();
            
            try{
                query="";
                
                if(printSentOrNotSendOrAll.equals("PrintNotSend")){
                    query = "SELECT firstCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.firstCourse<>'' GROUP BY R.order.firstCourse";
                }else if(printSentOrNotSendOrAll.equals("PrintAll")){
                    query = "SELECT firstCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.firstCourse<>'' GROUP BY R.order.firstCourse";

                }
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    String f =rs.getString("firstCourse");
                    firstCourse.add(f);
                    quantity.add(rs.getInt("q"));
                    int countSeat =rs.getInt("p");
                    if(countSeat>1){
                        seat.add(getfirstCourseALLPosition(f, tid, printSentOrNotSendOrAll));
                    }else{
                        
                        seat.add("["+rs.getInt("position")+"]");
                    }    
                }
                String query1 ="";
                if(printSentOrNotSendOrAll.equals("PrintNotSend")){
                    query1 = "SELECT secondCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.secondCourse<>'' GROUP BY R.order.secondCourse";
                }
                else if(printSentOrNotSendOrAll.equals("PrintAll")){
                    query1 = "SELECT secondCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.secondCourse<>'' GROUP BY R.order.secondCourse";

                }
                PreparedStatement ps1 = co.prepareStatement(query1);
                ResultSet rs1 = ps1.executeQuery();

                while(rs1.next()){
                    String f =rs1.getString("secondCourse");
                    secondCourse.add(f);
                    quantity_1.add(rs1.getInt("q"));
                    int countSeat =rs1.getInt("p");
                    if(countSeat>1){
                        seat_1.add(getSecondCourseALLPosition(f, tid, printSentOrNotSendOrAll));
                    }else{
                        
                        seat_1.add("["+rs1.getInt("position")+"]");
                    }    
                }
                String query2 ="";
                if(printSentOrNotSendOrAll.equals("PrintNotSend")){ 
                    query2 = "SELECT thirdCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.thirdCourse<>'' GROUP BY R.order.thirdCourse";
                
                }else if (printSentOrNotSendOrAll.equals("PrintAll")){
                    query2 = "SELECT thirdCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.thirdCourse<>'' GROUP BY R.order.thirdCourse";

                }
                
                PreparedStatement ps2 = co.prepareStatement(query2);
                ResultSet rs2 = ps2.executeQuery();

                while(rs2.next()){
                    String f =rs2.getString("thirdCourse");
                    thirdCourse.add(f);
                    quantity_2.add(rs2.getInt("q"));
                    int countSeat =rs2.getInt("p");
                    if(countSeat>1){
                        seat_2.add(getThirdCourseALLPosition(f, tid, printSentOrNotSendOrAll));
                    }else{
                        
                        seat_2.add("["+rs2.getInt("position")+"]");
                    }    
                }
                String query3 ="";
                if(printSentOrNotSendOrAll.equals("PrintNotSend")){
                    query3 = "SELECT seafood, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.seafood<>'' GROUP BY R.order.seafood";
                }else if(printSentOrNotSendOrAll.equals("PrintAll")){
                    query3 = "SELECT seafood, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.seafood<>'' GROUP BY R.order.seafood";

                }
                PreparedStatement ps3 = co.prepareStatement(query3);
                ResultSet rs3 = ps3.executeQuery();

                while(rs3.next()){
                    String f =rs3.getString("seafood");
                    seafood.add(f);
                    quantity_3.add(rs3.getInt("q"));
                    int countSeat =rs3.getInt("p");
                    if(countSeat>1){
                        seat_3.add(getThirdCourseALLPosition(f, tid, printSentOrNotSendOrAll));
                    }else{
                        
                        seat_3.add("["+rs3.getInt("position")+"]");
                    }    
                }
                
               rs=null;
               ps=null;
                myconnection.closeRs(rs1);
                myconnection.closePs(ps1);
                myconnection.closeRs(rs2);
                myconnection.closePs(ps2);
                myconnection.closeRs(rs3);
                myconnection.closePs(ps3);
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! get1st2rd3thAndSeafoodOrders():"+e);
            }
            temp.add(firstCourse );
            temp.add(secondCourse);
            temp.add(thirdCourse );
            temp.add(seafood     );
            temp.add(quantity    );
            temp.add(quantity_1  );
            temp.add(quantity_2  );
            temp.add(quantity_3  );
            temp.add(seat        );
            temp.add(seat_1      );
            temp.add(seat_2      );
            temp.add(seat_3      );
            
            return temp;
        }
        
        
        public ArrayList<Object> get1st2rd3thOrders(int tid, String printSentOrNotSendOrAll){
            ArrayList<Object>  temp        =  new ArrayList<Object>();
                    
                    
            ArrayList<String>  firstCourse = new ArrayList<String>();
            ArrayList<String>  secondCourse= new ArrayList<String>();
            ArrayList<String>  thirdCourse = new ArrayList<String>();
            ArrayList<String>  seafood     = new ArrayList<String>();
            ArrayList<Integer> quantity    = new ArrayList<Integer>();
            ArrayList<Integer> quantity_1  = new ArrayList<Integer>();
            ArrayList<Integer> quantity_2  = new ArrayList<Integer>();
            ArrayList<Integer> quantity_3  = new ArrayList<Integer>();
            ArrayList<String>  seat        = new ArrayList<String>();
            ArrayList<String>  seat_1      = new ArrayList<String>();
            ArrayList<String>  seat_2      = new ArrayList<String>();
            ArrayList<String>  seat_3      = new ArrayList<String>();
            
            try{
                query="";
                
                if(printSentOrNotSendOrAll.equals("PrintNotSend")){
                    query = "SELECT firstCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.firstCourse<>'' GROUP BY R.order.firstCourse";
                }else if(printSentOrNotSendOrAll.equals("PrintAll")){
                    query = "SELECT firstCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.firstCourse<>'' GROUP BY R.order.firstCourse";

                }
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    String f =rs.getString("firstCourse");
                    firstCourse.add(f);
                    quantity.add(rs.getInt("q"));
                    int countSeat =rs.getInt("p");
                    if(countSeat>1){
                        seat.add(getfirstCourseALLPosition(f, tid, printSentOrNotSendOrAll));
                    }else{
                        
                        seat.add("["+rs.getInt("position")+"]");
                    }    
                }
                String query1 ="";
                if(printSentOrNotSendOrAll.equals("PrintNotSend")){
                    query1 = "SELECT secondCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.secondCourse<>'' GROUP BY R.order.secondCourse";
                }
                else if(printSentOrNotSendOrAll.equals("PrintAll")){
                    query1 = "SELECT secondCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.secondCourse<>'' GROUP BY R.order.secondCourse";

                }
                PreparedStatement ps1 = co.prepareStatement(query1);
                ResultSet rs1 = ps1.executeQuery();

                while(rs1.next()){
                    String f =rs1.getString("secondCourse");
                    secondCourse.add(f);
                    quantity_1.add(rs1.getInt("q"));
                    int countSeat =rs1.getInt("p");
                    if(countSeat>1){
                        seat_1.add(getSecondCourseALLPosition(f, tid, printSentOrNotSendOrAll));
                    }else{
                        
                        seat_1.add("["+rs1.getInt("position")+"]");
                    }    
                }
                String query2 ="";
                if(printSentOrNotSendOrAll.equals("PrintNotSend")){ 
                    query2 = "SELECT thirdCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.thirdCourse<>'' GROUP BY R.order.thirdCourse";
                
                }else if (printSentOrNotSendOrAll.equals("PrintAll")){
                    query2 = "SELECT thirdCourse, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.thirdCourse<>'' GROUP BY R.order.thirdCourse";

                }
                
                PreparedStatement ps2 = co.prepareStatement(query2);
                ResultSet rs2 = ps2.executeQuery();

                while(rs2.next()){
                    String f =rs2.getString("thirdCourse");
                    thirdCourse.add(f);
                    quantity_2.add(rs2.getInt("q"));
                    int countSeat =rs2.getInt("p");
                    if(countSeat>1){
                        seat_2.add(getThirdCourseALLPosition(f, tid, printSentOrNotSendOrAll));
                    }else{
                        
                        seat_2.add("["+rs2.getInt("position")+"]");
                    }    
                }
                
                
                
               rs=null;
               ps=null;
                myconnection.closeRs(rs1);
                myconnection.closePs(ps1);
                myconnection.closeRs(rs2);
                myconnection.closePs(ps2);
               
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! get1st2rd3thAndSeafoodOrders():"+e);
            }
            temp.add(firstCourse );
            temp.add(secondCourse);
            temp.add(thirdCourse );
            temp.add(seafood     );
            temp.add(quantity    );
            temp.add(quantity_1  );
            temp.add(quantity_2  );
            temp.add(quantity_3  );
            temp.add(seat        );
            temp.add(seat_1      );
            temp.add(seat_2      );
            temp.add(seat_3      );
            
            return temp;
        }
        
         public ArrayList<Object> getSeafoodOrders(int tid, String printSentOrNotSendOrAll){
            ArrayList<Object>  temp        =  new ArrayList<Object>();
            ArrayList<String>  seafood     = new ArrayList<String>();
            ArrayList<Integer> quantity_3  = new ArrayList<Integer>();
            ArrayList<String>  seat_3      = new ArrayList<String>();
            
            try{
                
                String query3 ="";
                if(printSentOrNotSendOrAll.equals("PrintNotSend")){
                    query3 = "SELECT seafood, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.seafood<>'' GROUP BY R.order.seafood";
                }else if(printSentOrNotSendOrAll.equals("PrintAll")){
                    query3 = "SELECT seafood, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.seafood<>'' GROUP BY R.order.seafood";

                }
                PreparedStatement ps3 = co.prepareStatement(query3);
                ResultSet rs3 = ps3.executeQuery();

                while(rs3.next()){
                    String f =rs3.getString("seafood");
                    seafood.add(f);
                    quantity_3.add(rs3.getInt("q"));
                    int countSeat =rs3.getInt("p");
                    if(countSeat>1){
                        seat_3.add(getThirdCourseALLPosition(f, tid, printSentOrNotSendOrAll));
                    }else{
                        
                        seat_3.add("["+rs3.getInt("position")+"]");
                    }    
                }
                
               
                myconnection.closeRs(rs3);
                myconnection.closePs(ps3);
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getSeafoodOrders():"+e);
            }
            temp.add(seafood );
            temp.add(quantity_3    );
            temp.add(seat_3      );
            
            return temp;
        }
         
        public ArrayList<Object> getDrinkOrders(int tid, String printSentOrNotSendOrAll){
            ArrayList<Object>  temp             =  new ArrayList<Object>();
            
            ArrayList<String>  drink            = new ArrayList<String>();
            ArrayList<Integer> quantity_drink   = new ArrayList<Integer>();
            ArrayList<String>  seat_drink       = new ArrayList<String>();
            
            
            try{
                
                String query3 ="";
                if(printSentOrNotSendOrAll.equals("PrintNotSend")){
                    query3 = "SELECT drink, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.drink<>'' GROUP BY R.order.drink";
                }else if(printSentOrNotSendOrAll.equals("PrintAll")){
                    query3 = "SELECT drink, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.drink<>'' GROUP BY R.order.drink";

                }
                PreparedStatement ps3 = co.prepareStatement(query3);
                ResultSet rs3 = ps3.executeQuery();

                while(rs3.next()){
                    String f =rs3.getString("drink");
                    drink.add(f);
                    quantity_drink.add(rs3.getInt("q"));
                    int countSeat =rs3.getInt("p");
                    if(countSeat>1){
                        seat_drink.add(getThirdCourseALLPosition(f, tid, printSentOrNotSendOrAll));
                    }else{
                        
                        seat_drink.add("["+rs3.getInt("position")+"]");
                    }    
                }
                
               
                myconnection.closeRs(rs3);
                myconnection.closePs(ps3);
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getDrinkOrders():"+e);
            }
            temp.add(drink );
            temp.add(quantity_drink);
            temp.add(seat_drink );
            
            
            return temp;
        }
        public ArrayList<Object> getDessertOrders(int tid, String printSentOrNotSendOrAll){
            ArrayList<Object>  temp               =  new ArrayList<Object>();
            
            ArrayList<String>  dessert            = new ArrayList<String>();
            ArrayList<Integer> quantity_dessert   = new ArrayList<Integer>();
            ArrayList<String>  seat_dessert       = new ArrayList<String>();
            
            
            try{
                
                String query3 ="";
                if(printSentOrNotSendOrAll.equals("PrintNotSend")){
                    query3 = "SELECT dessert, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.sent='NO' AND R.order.dessert<>'' GROUP BY R.order.dessert";
                }else if(printSentOrNotSendOrAll.equals("PrintAll")){
                    query3 = "SELECT dessert, position, SUM(quantity) as q, COUNT(position) as p FROM R.order WHERE R.order.tid='"+tid+"' AND R.order.dessert<>'' GROUP BY R.order.dessert";

                }
                PreparedStatement ps3 = co.prepareStatement(query3);
                ResultSet rs3 = ps3.executeQuery();

                while(rs3.next()){
                    String f =rs3.getString("dessert");
                    dessert.add(f);
                    quantity_dessert.add(rs3.getInt("q"));
                    int countSeat =rs3.getInt("p");
                    if(countSeat>1){
                        seat_dessert.add(getThirdCourseALLPosition(f, tid, printSentOrNotSendOrAll));
                    }else{
                        
                        seat_dessert.add("["+rs3.getInt("position")+"]");
                    }    
                }
                
               
                myconnection.closeRs(rs3);
                myconnection.closePs(ps3);
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getDessertOrders():"+e);
            }
            temp.add(dessert );
            temp.add(quantity_dessert);
            temp.add(seat_dessert );
            
            
            return temp;
        } 
        public boolean hasDrink(int tid,String YesOrNo) throws NullPointerException{
            boolean b = false;
            try{

                query = "SELECT COUNT(*) as drink FROM R.order WHERE R.order.tid=? AND R.order.sent=? AND R.order.drink<>''";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, ""+tid);
                ps.setString(2, ""+YesOrNo);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    if(rs.getInt("Drink")!=0){
                        b=true;
                    }
                }


               rs=null;
               ps=null;



            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! hasDrink"+e);
            }

            return b;
        }
        public boolean hasDrinkWithoutSentCheck(int tid) throws NullPointerException{
            boolean b = false;
            try{

                query = "SELECT COUNT(*) as drink FROM R.order WHERE R.order.tid=? AND R.order.drink<>''";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, ""+tid);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    if(rs.getInt("drink")!=0){
                        b=true;
                    }
                }


               rs=null;
               ps=null;



            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! hasDrink"+e);
            }

            return b;
        }
        public boolean hasDessert(int tid,String YesOrNo) throws NullPointerException{
            boolean b = false;
            try{

                query = "SELECT COUNT(*) as dessert FROM R.order WHERE R.order.tid=? AND R.order.sent=? AND R.order.dessert<>''";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, ""+tid);
                ps.setString(2, ""+YesOrNo);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    if(rs.getInt("dessert")!=0){
                        b=true;
                    }
                }


               rs=null;
               ps=null;



            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! hasDessert"+e);
            }

            return b;
        }
        public boolean hasDessertWithoutSentCheck(int tid) throws NullPointerException{
            boolean b = false;
            try{

                query = "SELECT COUNT(*) as dessert FROM R.order WHERE R.order.tid=? AND R.order.dessert<>''";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1, ""+tid);
                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    if(rs.getInt("dessert")!=0){
                        b=true;
                    }
                }


               rs=null;
               ps=null;



            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! hasDessert"+e);
            }

            return b;
        } 
        private ArrayList<Object>  temp_getFourcrouseCount;
        private ArrayList<String>  tableName_getFourcrouseCount;
        private ArrayList<String>  FourcourseCount_getFourcrouseCount;

  
       
        private String f_getFourcrouseCount;
        private int count_getFourcrouseCount;
        
        public ArrayList<Object> getFourcrouseCount(){
              temp_getFourcrouseCount            =  new ArrayList<Object>();
              tableName_getFourcrouseCount       =  new ArrayList<String>();
              FourcourseCount_getFourcrouseCount =  new ArrayList<String>();
 
     

        try{
                
                
                
                rs3_getFourcrouseCount = ps3_getFourcrouseCount.executeQuery();

                while(rs3_getFourcrouseCount.next()){
                    f_getFourcrouseCount =rs3_getFourcrouseCount.getString("tname");
                    tableName_getFourcrouseCount.add(f_getFourcrouseCount);
                    count_getFourcrouseCount =rs3_getFourcrouseCount.getInt("p");
                    if(count_getFourcrouseCount>0){
                        FourcourseCount_getFourcrouseCount.add(""+count_getFourcrouseCount);
                    }else{
                        
                        FourcourseCount_getFourcrouseCount.add("");
                    }    
                }
                
                
                rs3_getFourcrouseCount=null;
         
               
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getFourcrouseCount():"+e);
            }
            temp_getFourcrouseCount.add(tableName_getFourcrouseCount );
            temp_getFourcrouseCount.add(FourcourseCount_getFourcrouseCount);
            return temp_getFourcrouseCount;
        }
        private ArrayList<String>  distinctName_getdistinctAccountName;
       
        private String a_getdistinctAccountName;
        public ArrayList<String> getdistinctAccountName(){
            distinctName_getdistinctAccountName= new ArrayList<String>();
            try{
                rs_getdistinctAccountName = ps_getdistinctAccountName.executeQuery();
                
            
                while(rs_getdistinctAccountName.next()){
                    
                    a_getdistinctAccountName = rs_getdistinctAccountName.getString("daname");
                    distinctName_getdistinctAccountName.add(a_getdistinctAccountName);
                   
                }


                  rs_getdistinctAccountName=null;
         



            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getdistinctAccountName"+e);
            }
            return distinctName_getdistinctAccountName;
        }
        private    ArrayList<String>  tableName;
        private    ArrayList<String>  accountName;
        private    ArrayList<String>  distinctName;
      
     
        public     String getServerAndTable(){
            
            String finals ="";
             tableName = new ArrayList<String>();
             accountName= new ArrayList<String>();
             distinctName= new ArrayList<String>();
            try{

                
                rs_getServerAndTable = ps_getServerAndTable.executeQuery();
                
                int y =0;
                while(rs_getServerAndTable.next()){
                    String t =rs_getServerAndTable.getString("tname");
                    String a = rs_getServerAndTable.getString("aname");
                    if(a.equals(null)){
                        a="";
                    }
                    if(t.equals(null)){
                        t="";
                    }
                    accountName.add(a);
                    tableName.add(t);
                }


                rs_getServerAndTable=null;
              



            distinctName = this.getdistinctAccountName();
            
            int f =0;
            String text="";
            String tables="";
            
            
            for(int j=0; j<distinctName.size(); j++){
                int count=12;
                for(int i=0; i<accountName.size(); i++){
                    
                    if(distinctName.get(j).toString().equals(accountName.get(i).toString())){
                        tables += tableName.get(i).toString()+" ";
                        if(tables.length()>count){
                           tables+="\n          ";
                           count+=26;     
                            
                        }
                    }
                }
                text=tables;
                tables=distinctName.get(j).toString()+": \n          "+text+"\n \n";
                finals+=tables;
                tables="";
            }
            
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getServerAndTable"+e);
            }
            return finals;
        }
        public Double getSeafood20PercentOffTotal(int tid, int sid)
        {   
            Double total =0.00;
             
           
            
            
            try{

                query = "SELECT price, time_in FROM R.order WHERE R.order.tid= ? AND R.order.subtable=? AND R.order.type = 'seafood' ";
                PreparedStatement ps = co.prepareStatement(query);
                ps.setString(1,""+tid);
                ps.setString(2,""+sid);
           
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    Double t =rs.getDouble("price");
                    String time =rs.getString("time_in");
                    String s = time.substring(11, 13);
                    int hour= Integer.parseInt(s);
                    if(hour>=14 && hour<= 17 ){
                        t=t*0.2;  // get 20% off amount from one order
                        total+=t; // add up those amount to total;
                    }
                    
                    
                }


               rs=null;
               ps=null;



            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getSeafood20PercentOffTotal"+e);
            }
            return total;
        }
        public ArrayList<String> getOneMonthDataOfEntree(int dayBefore){
      
      
           
            ArrayList<String> list= new  ArrayList<String>();
            try{
                
                String query = "SELECT thirdCourse , count(thirdCourse) as cnt FROM R.storage WHERE R.storage.time_in >= date_sub('"+this.getDateDBFormat(0)+" 00:00:00', INTERVAL ? DAY) AND R.storage.time_in <= '"+this.getDateDBFormat(0)+" 23:59:59' GROUP BY thirdCourse ORDER BY cnt desc";
                PreparedStatement ps_1 = co.prepareStatement(query);
                ps_1.setInt(1, dayBefore);
                ResultSet rs_1 = ps_1.executeQuery();

                while(rs_1.next()){
                    if(!rs_1.getString("thirdCourse").equals(""))
                    list.add(rs_1.getInt("cnt")+"x     "+ rs_1.getString("thirdCourse"));
                }
                
                myconnection.closeRs(rs_1);
                myconnection.closePs(ps_1);
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getOneMonthDataOfEntree"+e);
            }

            return list;
            
        
        }
         public ArrayList<String> getOneMonthDataOfDessert(int dayBefore){
      
      
           
            ArrayList<String> list= new  ArrayList<String>();
            try{
                
                String query = "SELECT dessert , count(dessert) as cnt FROM R.storage WHERE R.storage.time_in >= date_sub('"+this.getDateDBFormat(0)+" 00:00:00', INTERVAL ? DAY) AND R.storage.time_in <= '"+this.getDateDBFormat(0)+" 23:59:59' GROUP BY dessert ORDER BY cnt desc";
                PreparedStatement ps_1 = co.prepareStatement(query);
                ps_1.setInt(1,dayBefore);
                ResultSet rs_1 = ps_1.executeQuery();

                while(rs_1.next()){
                    if(!rs_1.getString("dessert").equals(""))
                    list.add(rs_1.getInt("cnt")+"x     "+ rs_1.getString("dessert"));
                }
                
                myconnection.closeRs(rs_1);
                myconnection.closePs(ps_1);
                


            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getOneMonthDataOfDessert"+e);
            }

            return list;
            
        
        }
        public Hashtable<String, String> get4CourseDetailwithOid(int id){

            PreparedStatement ps_1= null;
            ResultSet rs_1= null;
            Hashtable<String,String> data = new Hashtable<String,String>();
            try{
                String query = "Select * FROM R.order O WHERE O.oid=?"; 
                ps_1 = co.prepareStatement(query);
                ps_1.setInt(1,id);
                rs_1 = ps_1.executeQuery();
                while(rs_1.next()){
                    data.put("name", rs_1.getString("name"));
                    data.put("firstCourse", rs_1.getString("firstCourse"));
                    data.put("secondCourse", rs_1.getString("secondCourse"));
                    data.put("thirdCourse", rs_1.getString("thirdCourse"));
                    data.put("price", Double.toString(rs_1.getDouble("price")));
                    data.put("quantity", Integer.toString(rs_1.getInt("quantity")));
                    data.put("subtable", ""+rs_1.getInt("subtable"));
                    data.put("position", Integer.toString(rs_1.getInt("position")));
                    
                   

                }


            }catch(Exception e){
                System.out.println("error at jd romoveOrderWithOID():"+e);
            }finally{

                if (rs_1 != null) {
                try {
                    rs_1.close();
                } catch (SQLException e) { /* ignored */}
                }
                if (ps_1 != null) {
                    try {
                        ps_1.close();
                    } catch (SQLException e) { /* ignored */}
                }

            }
            return data;
        }
        
        public void update4CourseOrderWithTidOidAndHashedData(int tid, int oid, Hashtable<String,String> data){
            PreparedStatement ps_1= null;
            ResultSet rs_1= null;
            try{


                String s =data.get("position");

                int seat = 0;
                
                if(!s.equals(""))
                    seat=Integer.valueOf(s);
                   

                Double[] TDC = this.getTipDiscountCouponAtSubtable(tid, Integer.parseInt(data.get("subtable")));

                String query = "UPDATE `R`.`order` SET `tip`='"+TDC[0]+"', `discount`='"+TDC[1]+"', `coupon`='"+TDC[2]+"', `name`='"+data.get("name")+"', `firstCourse`='"+data.get("firstCourse")+"', `secondCourse`='"+data.get("secondCourse")+"', `thirdCourse`='"+data.get("thirdCourse")+"', `price`='"+Double.parseDouble(data.get("price"))+"', `quantity`='"+Integer.valueOf(data.get("quantity"))+"', `position`='"+seat+"', `subtable`='"+data.get("subtable")+"' WHERE  `oid` = '"+oid+"'"; 

               
                ps_1 = co.prepareStatement(query);
                ps_1.executeUpdate();


            }catch(Exception e){
                System.out.println("error occur when update:"+e);
            }finally{

                if (rs_1 != null) {
                    try {
                        rs_1.close();
                    } catch (SQLException e) { /* ignored */}
                }
                if (ps_1 != null) {
                    try {
                        ps_1.close();
                    } catch (SQLException e) { /* ignored */}
                }
                
            }
        }
        public void recoveryMyCheckFromStorage(){
            ArrayList<Integer> cids = getDistintedCheckIDFromStorageInCertaintDays("2015-01-17", "2015-02-07");
         
            for(int i=0; i<cids.size(); i++){
                int aid = 0;
                String since = "";
                Double tax = 0.00;
                Double subtotal = 0.00;
                int ppl = 0 ;
                int tid =1;
                ArrayList<Hashtable> orders = this.getAllOrdersDetailWithCid(cids.get(i), "2015-01-17", "2015-02-07");
                for(int j=0; j<orders.size(); j++){
                    aid = (Integer)((Hashtable)orders.get(j)).get("aid");
                    since =(String)((Hashtable)orders.get(j)).get("time_in");
                    subtotal += Double.parseDouble((String)((Hashtable)orders.get(j)).get("price"))* (Integer)((Hashtable)orders.get(j)).get("quantity");
                } 
                Double total = subtotal+tax;
                ppl=orders.size()/3;
                System.out.println("VALUE ( '"+i+"','"+aid+"','"+since+"','"+total +"','"+tax +"','"+ppl+"','"+tid+"')");
            }
            System.out.println("recoveryMyCheckFromStorage");
            
        }
        /* startDate and endDate format is YYYY-MM-DD */
        /* startDate and endDate format is YYYY-MM-DD */
        /* startDate and endDate format is YYYY-MM-DD */
        /* startDate and endDate format is YYYY-MM-DD */
        /* startDate and endDate format is YYYY-MM-DD */
        /* startDate and endDate format is YYYY-MM-DD */
        /* startDate and endDate format is YYYY-MM-DD */
        public ArrayList<Integer> getDistintedCheckIDFromStorageInCertaintDays(String startDate, String endDate) throws NullPointerException{

            ArrayList<Integer> a = new ArrayList<Integer>();
            try{
                
                query = "SELECT DISTINCT cid as distinct_cid FROM R.storage WHERE R.storage.time_in BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'";
                PreparedStatement ps = co.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    a.add(rs.getInt("distinct_cid"));
                    
                }
                Collections.sort(a);
               rs=null;
               ps=null;
                
            }catch(NullPointerException e){         }catch(Exception e){
                System.out.println("error!!! getSortedSubCheckNumber():"+e);
            }
            return a;
        }
        public void insertANewCheckWithDetialInput(String aid, String since, String tax, String total, String ppl, String tid) throws NullPointerException{
            
            PreparedStatement ps= null;
           
            try{
                int cid = this.getCidAtWheverMissedNumber();
                System.err.println(cid);
                insert = "INSERT INTO `R`.`check` (cid, aid, since, tax, total, ppl, tid) ";
                    insert += "VALUE ( '"+cid+"','"+aid+"','"+since+"','"+tax +"','"+total +"','"+ppl+"','"+tid+"')";
                ps = co.prepareStatement(insert);
                ps.executeUpdate();
                 ps.close();
            }catch(NullPointerException e){         }catch(Exception e){
            System.out.println("error occur when insertNewCheck:"+e);
            }
        }
        public  ArrayList<Hashtable> getAllOrdersDetailWithCid(int cid, String startDate, String endDate){
            PreparedStatement ps= null;
            ResultSet rs= null;
            Hashtable<String,Object> data = new Hashtable<String,Object>();
            ArrayList<Hashtable> allData = new ArrayList<Hashtable>();
            try{
                String query = "Select * FROM R.storage O WHERE O.cid=? AND O.time_in BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'";
                ps = co.prepareStatement(query);
                ps.setInt(1,cid);
                rs = ps.executeQuery();
                while(rs.next()){

                    data.put("name", ""+rs.getString("name"));
                    data.put("sid", rs.getInt("sid"));
                    data.put("aid", rs.getInt("aid"));
                    data.put("time_in", ""+rs.getString("time_in"));
                    data.put("time_send", ""+rs.getString("time_send"));
                    data.put("note", ""+rs.getString("note"));
                    data.put("firstCourse", ""+rs.getString("firstCourse"));
                    data.put("secondCourse", ""+rs.getString("secondCourse"));
                    data.put("thirdCourse", ""+rs.getString("thirdCourse"));
                    data.put("seafood", ""+rs.getString("seafood"));
                    data.put("other", ""+rs.getString("other"));
                    data.put("drink", ""+rs.getString("drink"));
                    data.put("dessert", ""+rs.getString("dessert"));
                    data.put("cake", ""+rs.getString("cake"));
                    data.put("sent",""+ rs.getString("sent"));
                    data.put("type", ""+rs.getString("type"));
                    data.put("price",Double.toString(rs.getDouble("price")));
                    data.put("quantity",rs.getInt("quantity"));
                    data.put("subtable",""+rs.getInt("subtable"));
                    data.put("position",Integer.toString(rs.getInt("position")));
                    allData.add(data);
                }

            }catch(Exception e){
                
                System.out.println("error at jd getAllOrdersDetailWithCid():");
                e.printStackTrace();
            }finally{

                if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */}
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */}
                }
                
            }
            return allData;
        }// end of 
        
        public  Hashtable<String,String> getSinlgeOrderDetailWithOid(int oid){
            PreparedStatement ps= null;
            ResultSet rs= null;
            Hashtable<String,String> data = new Hashtable<String,String>();
            try{
                String query = "Select * FROM R.order O WHERE O.oid=?"; 
                ps = co.prepareStatement(query);
                ps.setInt(1,oid);
                rs = ps.executeQuery();
                while(rs.next()){
                    data.put("name", rs.getString("name"));
                    data.put("type", rs.getString("type"));
                    String type=rs.getString("type");
                    data.put(type, rs.getString(type));  // use the type to retrive the name.
                    data.put("price",Double.toString(rs.getDouble("price")));
                    data.put("quantity",Integer.toString(rs.getInt("quantity")));
                    data.put("subtable",""+rs.getInt("subtable"));
                    data.put("position",Integer.toString(rs.getInt("position")));
                  
                }

            }catch(Exception e){
                System.out.println("error at jd romoveOrderWithOID():"+e);
            }finally{

                if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */}
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */}
                }
                
            }
            return data;
        }// end of 
        
        
        public void updateSingleOrderWithTidOidHashData(int tid, int oid, Hashtable<String,String> data){
            
            PreparedStatement ps= null;
            ResultSet rs= null;


            String type = data.get("type");
         
            try{
    

                String s =data.get("position");
                int seat=0;
                if(!s.equals("")){
                    seat=Integer.valueOf(s);
                }    

                String first="";
                String second="";
                String third="";
                String seafood="";
                String dessert="";
                String drink="";
                String other="";
                if(type.equals("firstCourse")){first=data.get(type);}   
                else if(type.equals("secondCourse")){second=data.get(type);}  
                else if(type.equals("thirdCourse")){third=data.get(type);} 
                else if(type.equals("seafood")){seafood=data.get(type);} 
                else if(type.equals("dessert")){dessert=data.get(type);} 
                else if(type.equals("drink")){drink=data.get(type);} 
                else if(type.equals("other")){other=data.get(type);} 

                Double[] TDC = this.getTipDiscountCouponAtSubtable(tid, Integer.parseInt(data.get("subtable")));

                String query = "UPDATE `R`.`order` SET `tip`='"+TDC[0]+"', `discount`='"+TDC[1]+"', `coupon`='"+TDC[2]+"', `name`='"+data.get("name")+"', `firstCourse`='"+first+"', `secondCourse`='"+second+"', `thirdCourse`='"+third+"', `seafood`='"+seafood+"', `dessert`='"+dessert+"', `drink`='"+drink+"', `other`='"+other+"', `price`='"+Double.parseDouble(data.get("price"))+"', `quantity`='"+Integer.valueOf(data.get("quantity"))+"', `position`='"+seat+"', `subtable`='"+data.get("subtable")+"', `type`='"+type+"'  WHERE  `oid` = '"+oid+"'"; 



                ps = co.prepareStatement(query);
                ps.executeUpdate();


            }catch(Exception e){
                e.printStackTrace();
            }finally{

                if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */}
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */}
                }
                
            }
        }//end of editselect
        
        
        
        public void insertASimpleOrder(int tid , int aid, Hashtable<String, String> data){
 
            PreparedStatement ps= null;
            ResultSet rs= null;


            String type = "secondCourse";

            try{



                String firstcourse ="";
                String secondcourse ="";
                String thirdcourse ="";
                String seafood ="";
                String other ="";
                String drink="";
                String dessert ="";



                String dateTime = this.getDateTimeDBFormat();//getDateTime();
                Double[] TDC = this.getTipDiscountCouponAtSubtable(tid, Integer.parseInt("1"));

                String query = "INSERT `R`.`order` SET `tip`='"+TDC[0]+"', `discount`='"+TDC[1]+"', `coupon`='"+TDC[2]+"', `name`=?, `firstCourse`='"+firstcourse+"', `secondCourse`=?, `thirdCourse`='"+thirdcourse+"', `seafood`='"+seafood+"', `dessert`='"+dessert+"', `drink`='"+drink+"', `other`=?, `price`=?, `quantity`='1', `position`='1', `note`='', `type`='"+type+"', `tid`='"+tid+"', `aid`='"+aid+"', `subtable`='1', `time_in`='"+dateTime+"'"; 

                ps = co.prepareStatement(query);
                ps.setString(1, data.get("name"));
                ps.setString(2, data.get("name"));
                ps.setString(3, "");
                ps.setString(4, data.get("price"));
                ps.executeUpdate();


            }catch(Exception e){
                System.out.println("error occur when update:"+e);
            }finally{

                if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */}
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */}
                }

            }

        }//end of editselect
        public void updateSubtable(int tid, int subtable, int oid){

            PreparedStatement ps= null;
            ResultSet rs= null;


            try{

                Double[] TDC = this.getTipDiscountCouponAtSubtable(tid, subtable);

                String query = "UPDATE `R`.`order` SET `tip`='"+TDC[0]+"', `discount`='"+TDC[1]+"', `coupon`='"+TDC[2]+"', `subtable`='"+subtable+"' WHERE  `oid` = '"+oid+"'"; 

                ps = co.prepareStatement(query);
                ps.executeUpdate();



            }catch(Exception e){
                System.out.println("error occur when update:"+e);
            }finally{

                if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */}
                }
                if (ps != null) {
                    try {
                        ps.close();
                    } catch (SQLException e) { /* ignored */}
                }

            }
        }

}
