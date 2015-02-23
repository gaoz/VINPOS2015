package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.text.DecimalFormat;
 
/**
 *
 * @author sd
 */
public class Storage2 {
    protected ArrayList<Storage1> items;
    private Double discount=0.00;
    private Double tip=0.00;
    private NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
    public Storage2(){
        items = new ArrayList<Storage1>();
    }
    public void removeLastOrder(){
        items.remove(items.size()-1);
    }
    public void editLastOrder(String s){
        Storage1 s1 = new Storage1();
        s1=items.get(items.size()-2);
        String string = s1.getFirst3courseString()+s;
        //System.out.println(string);
        s1.setNote(string);
        items.set(items.size()-2,s1);
    }
    public void removeNum(int i){
        items.remove(i-1);
    }
    public int getStorage2Size(){
        int size = items.size();
        return size;
    }
    public void setItems(Storage1 s){
        items.add(s);
    }
    public ArrayList<Storage1> getOrders()
    {
        return items;
    }
    public String toString(){
        String temp ="";
        NumberFormat myFormat = new DecimalFormat("#00");
        for (int i =0; i <items.size(); i++){
            //temp += "    Num"+myFormat.format(i+1)+"  ";
            temp +=items.get(i).toString();
            
        }
        return temp;
    }
    public String toTotal(){
        Double subTotal=0.00;
        String temp="";
        for (int i =0; i <items.size(); i++){
            subTotal += items.get(i).getPrice();
        }
        temp="\n";
         
         
         
        temp+=" SubTotal:  "+n.format(subTotal)+"\n";
        if(discount!=0.00){
            DecimalFormat df = new DecimalFormat("#%");
            Double howmuchoff = subTotal*discount;
            subTotal -= howmuchoff;
            temp+= "Discount:- "+n.format(howmuchoff)+"  "+df.format(discount)+"OFF"+"\n";           
        }
        Double tax=subTotal*0.04712;
        temp+="          Tax:  "+n.format(tax)+"\n";
        if(tip!=0.00){
            DecimalFormat df = new DecimalFormat("#%");
            Double tipCharge = subTotal*tip;
            subTotal += tipCharge;
            temp+= "   Gratuity:  "+n.format(tipCharge)+"  "+df.format(tip)+" Service charge"+"\n";           
        }
        
        temp+="      ----------------------"+"\n";
        temp+="       Total:  "+n.format(tax+subTotal);
        temp+="\n";
        
        

        
          temp+="--------------------------------------------------------------"+"\n";
        temp+="                 Thank You So Much.        "+"\n";
        temp+="--------------------------------------------------------------"+"\n";
        temp+="                     TIP ASSIST"+"\n";
        temp+="                      15%"+n.format((tax+subTotal)*0.15)+"\n";
        temp+="                      18%"+n.format((tax+subTotal)*0.18)+"\n";
        temp+="                      20%"+n.format((tax+subTotal)*0.20)+"\n";
        
        return temp;
    }
    public Double getTotal(){
        Double subTotal=0.00;
        String temp="";
        NumberFormat myFormat = new DecimalFormat("#00");
        for (int i =0; i <items.size(); i++){
            subTotal += items.get(i).getPrice();
        }
        Double tax=subTotal*0.04712;
        subTotal=tax+subTotal;
        return Double.parseDouble(myFormat.format(subTotal));
    } 

    public JSONObject toJSON2(){
        JSONObject obj = new JSONObject();
        JSONArray nameList = new JSONArray();
        JSONArray priceList = new JSONArray();
        JSONArray appList = new JSONArray();
        JSONArray entreeList = new JSONArray();
        JSONArray seatList = new JSONArray();
        JSONArray soupList = new JSONArray();
        JSONArray noteList = new JSONArray();
        
        for(int i=0; i<items.size(); i++){
            nameList.add(items.get(i).getName());
            priceList.add(items.get(i).getPrice());
            soupList.add(items.get(i).getSoupSalad());
            appList.add(items.get(i).getAppetizer());
            entreeList.add(items.get(i).getEntree());
            seatList.add(items.get(i).getSeat());
            noteList.add(items.get(i).getSeat());
        }
        obj.put("Soup",soupList);
        obj.put("Appetizer",appList);
        obj.put("Entree",entreeList);
        obj.put("Seat",seatList);
        obj.put("Note",noteList);
        obj.put("Order",nameList);
        obj.put("Price",priceList);
        return obj;

    }
    public ArrayList<String> getNameList(){
        ArrayList<String> arraylist = new ArrayList<String>();
        for (int i =0; i <items.size(); i++){
            arraylist.add(((Storage1)this.items.get(i)).getName());
        }
        return arraylist;
    }
    //public ArrayList<String> getJustAddedNameList(int ini){
    //    ArrayList<String> arraylist = new ArrayList<String>();
    //    for (int i =ini; i <items.size(); i++){
    //        arraylist.add(((Storage1)this.items.get(i)).getName());
    //    }
    //    return arraylist;
    //}
    //public ArrayList<Storage1> getJustAddedNameList(int ini){
    //    ArrayList<Storage1> arraylist = new ArrayList<Storage1>();
    //    arraylist=items;
    //    for (int i =ini-1; i>-1; i--){
    //        arraylist.remove(i); 
    //    }
    //    return arraylist;
    //}
    public ArrayList<Storage1> getJustAddedNameList(int oldOrderSize){
        ArrayList<Storage1> arraylist = new ArrayList<Storage1>();
        arraylist=items;
        for (int i =0; i<oldOrderSize; i++){
            arraylist.remove(0); 
        }
        return arraylist;
    }
    public String getName(int i){
        String s =items.get(i-1).getName();
        return s;
    }
    public void setDiscount(Double d){
        discount=d;
    }
    public void setTip(Double d){
        tip=d;
    }
}
