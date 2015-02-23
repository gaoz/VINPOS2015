package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import javax.swing.JTextArea;
/**
 *
 * @author vinson
 */
public class SortOrder extends PrintTest2 {
    JTextArea jTextArea = new JTextArea();
    JTextArea notejTextArea = new JTextArea();
    ArrayList<String> sortedList = new ArrayList<String>();
    NameSeatList first = new NameSeatList();
    NameSeatList second = new NameSeatList();
    NameSeatList third = new NameSeatList();
    NameSeatList forth = new NameSeatList();
    ArrayList<String> firstCourse = new ArrayList<String>();
    ArrayList<String> secondCourse = new ArrayList<String>();
    ArrayList<String> thirdCourse = new ArrayList<String>();
    ArrayList<String> noteList = new ArrayList<String>();
    
    public void sortTheOrder(String tableNum, String billheader, ArrayList<Storage1> myList,boolean addon, int p, String t){
        sortedList=sortHere(myList);
        for(int i =0; i< sortedList.size();i++){
                jTextArea.append(sortedList.get(i)+"\n");
        }
        for(int i =0; i< noteList.size();i++){
                notejTextArea.append(noteList.get(i)+"\n");
        }
        printCheck2(tableNum,billheader,jTextArea.getText(),notejTextArea.getText(), addon, p, t);
        
        
    }
    public ArrayList<String> sortHere(ArrayList<Storage1> newList){
        ArrayList<String> arrayList = new ArrayList<String>();
        
        // extract all the order in to my arraylist then sort them after
        
        
        for(int i = 0; i<newList.size();i++){
            NameSeat ns = new NameSeat("","");
            if(!newList.get(i).getSoupSalad().equals("")){
 
                ns.setName(newList.get(i).getSoupSalad());
                ns.setSeat(newList.get(i).getSeat());
                first.add(ns);
                
            }
        }
        
        
        for(int i = 0; i<newList.size();i++){
            NameSeat ns = new NameSeat("","");
            if(!newList.get(i).getAppetizer().equals("")){
                ns.setName(newList.get(i).getAppetizer());
                ns.setSeat(newList.get(i).getSeat());
                second.add(ns);
            }
        }
        for(int i = 0; i<newList.size();i++){
            NameSeat ns = new NameSeat("","");
            if(!newList.get(i).getEntree().equals("")){
                ns.setName(newList.get(i).getEntree());
                ns.setSeat(newList.get(i).getSeat());
                third.add(ns);
            }
        }
        for(int i = 0; i<newList.size();i++){
            NameSeat ns = new NameSeat("","");
            if(!newList.get(i).getNote().equals("")){
                ns.setName(newList.get(i).getNote());
                System.out.println(newList.get(i).getNote());
                //ns.setSeat(newList.get(i).getSeat());
                forth.add(ns);
            }
        }
        // sorting array list
        
        //if(first.size()!=0){ arrayList.add(" ");}
        while(first.size()!=0){
           
            int orderTotal =1;
            String seats=" ";
            String orderTotalStr="    ";
            for(int i = 0; i<first.size()-1;i++){
                if(first.get(0).getName().equals(first.get(i+1).getName())){
                    seats+=first.get(i+1).getSeat();
                    first.remove(i+1); 
                    orderTotal=orderTotal+1;
                    i--;
                }
                
            } // end of for
            seats+=first.get(0).getSeat();
            if (orderTotal!=1){
                orderTotalStr=orderTotal+"x  ";
            }
            String seatsWithSymbal=" ";
            if(!seats.equals(" ")){
                seatsWithSymbal+= "~"+seats;
            }
            firstCourse.add(orderTotalStr+first.get(0).getName()+seatsWithSymbal);
            arrayList.add(orderTotalStr+first.get(0).getName()+seatsWithSymbal);
            first.remove(0);
            
         }// end of for 
        
            
         
        
         
        //if(second.size()!=0){arrayList.add(" ");} 
        while(second.size()!=0){
            
            int orderTotal =1;
            String seats=" ";
            String orderTotalStr="  ";
            for(int i = 0; i<second.size()-1;i++){
                if(second.get(0).getName().equals(second.get(i+1).getName())){
                    seats+=second.get(i+1).getSeat();
                    second.remove(i+1); 
                    orderTotal=orderTotal+1;
                    i--;
                }
                
            } // end of for
            seats+=second.get(0).getSeat();
            if (orderTotal!=1){
                orderTotalStr=orderTotal+"x";
            }
            String seatsWithSymbal=" ";
            if(!seats.equals(" ")){
                seatsWithSymbal+= "~"+seats;
            }
            secondCourse.add(orderTotalStr+second.get(0).getName()+seatsWithSymbal);
            arrayList.add(orderTotalStr+second.get(0).getName()+seatsWithSymbal);
            second.remove(0);
            
         }  
         // end of for 

         
         
         //if(third.size()!=0){arrayList.add(" ");}
         while(third.size()!=0){
            
            int orderTotal =1;
            String seats=" ";
            String orderTotalStr=" ";
            for(int i = 0; i<third.size()-1;i++){
                if(third.get(0).getName().equals(third.get(i+1).getName())){
                    seats+=third.get(i+1).getSeat();
                    third.remove(i+1); 
                    orderTotal=orderTotal+1;
                    i--;
                }
                
            } // end of for
            seats+=third.get(0).getSeat();
            if (orderTotal!=1){
                orderTotalStr=orderTotal+"x";
            }
            String seatsWithSymbal=" ";
            if(!seats.equals(" ")){
                seatsWithSymbal+= "~"+seats;
            }
            thirdCourse.add(orderTotalStr+third.get(0).getName()+seatsWithSymbal);
            arrayList.add(orderTotalStr+third.get(0).getName()+seatsWithSymbal);
            third.remove(0);
           
         }// end of for 
         
        /*
         if(forth.size()!=0){
             arrayList.add(" ");
             arrayList.add(" ");
             arrayList.add(" ");
             noteList.add("**************Notes**************");}
         while(forth.size()!=0){
            
            int orderTotal =1;
            String seats="";
            String orderTotalStr="";
            for(int i = 0; i<forth.size()-1;i++){
                if(forth.get(0).getName().equals(forth.get(i+1).getName())){
                    seats+=forth.get(i+1);//.getSeat();
                    forth.remove(i+1); 
                    orderTotal=orderTotal+1;
                    i--;
                }
                
            } // end of for
            //seats+=forth.get(0).getSeat();
            if (orderTotal!=1){
                orderTotalStr=orderTotal+"x";
            }
            noteList.add(orderTotalStr+forth.get(0).getName());//+" "+seats);
            System.out.println(forth.get(0).getName());
            forth.remove(0);
            
         }
         if(forth.size()!=0){
                noteList.add("******************Notes*********************");
         }*/
         return arrayList;
    }
    public ArrayList<String> getNoteList() //not used yet.
    {
        return noteList;
    }
}
            

class NameSeat{
    private String name;
    private String seat;
    public NameSeat(String n, String s){
        name =n;
        seat=s;
    }
    public void setName(String n){
        name=n;
    }
    public String getName(){
        return name;
    }
    public void setSeat(String s){
        seat=s;
    }
    public String getSeat(){
        return seat;
    }
  
}

class NameSeatList{
    ArrayList<NameSeat> list;
    public NameSeatList(){
        list = new ArrayList<NameSeat>();
    }
    public void add(NameSeat ns){
        list.add(ns);
    }
    public NameSeat get(Integer i){
        return list.get(i);
    }
    public Integer size(){
        return list.size();
    }
    public void remove(int i){
        list.remove(i);
    }
    public String toString(){
        String temp="";
        for(int i=0; i<list.size();i++){
            temp+=list.get(i).getName()+list.get(i).getSeat()+"\n";
        }
        return temp;
    }
}
