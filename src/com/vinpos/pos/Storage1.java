package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.text.NumberFormat;
import java.util.Locale;



/**
 *
 * @author sd
 */
public class Storage1 {
    
    private String soup;
    private String appetizer;
    private String entree;
    private String seat;
    private String note;
    private String name;
    private Double price;

    private NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
    public Storage1(String n0, String n1,String n2,String n3,String n4, String n5,Double p){
        this.soup=n0;
        this.appetizer=n1;
        this.entree = n2;
        this.seat = n3;
        this.note = n4;
        this.name = n5;
        this.price = p;

    }
    public Storage1(){
        this("","","","","","",0.00);
    }
    public void setSoupSalad(String n){
        this.soup=n;
    }
    public String getSoupSalad(){
        return soup;
    }
    public void setAppetizer(String s){
        this.appetizer=s;
    }
    public String getAppetizer(){
        return appetizer;
    }
    public void setEntree(String s){
        this.entree=s;
    }
    public String getEntree(){
        return entree;
    }
    
    public void setSeat(String s){
        this.seat=s;
    }
    public String getSeat(){
        return seat;
    }
    public void setNote(String s){
        this.note=s;
    }
    public String getNote(){
        return note;
    }
     public void setName(String t)
    {
        this.name = t;
    }
    public String getName()
    {
        return this.name;
    }
    public void setPrice(Double s) {
        this.price = s;
    }
    public Double getPrice()
    {
        return this.price;
    }
    public String getFirst3courseString(){
        String space1="";
        String space2="";
        if(!soup.equals("")){
            space1=" ";
        }
        if(!appetizer.equals("")){
            space2=" ";
        }
        
        String temp="";
        temp="("+soup+space1+appetizer+space2+entree+")";
        //temp="("+name+")";
        return temp;
    }

    public String toString(){
        String temp="";
         if(price!=0.00){
                temp += " "+n.format(price);
                temp += " "+name+"\n";
         }
         else{
                    
                    temp += " "+name+"\n";
                }
        return temp;
    }
    public String toJSON() {
        String temp = "      {"+"\n";
        temp = temp + "      \"Soup\":\"" + this.soup + "\",\n";
        temp = temp + "      \"Appetizer\":\"" + this.appetizer + "\",\n";
        temp = temp + "      \"Entree\":\"" + this.entree + "\",\n";
        temp = temp + "      \"Seat\":\"" + this.seat + "\",\n";
        temp = temp + "      \"Note\":\"" + this.note + "\",\n";
        temp = temp + "      \"Order\":\"" + this.name + "\",\n"; 
        temp = temp + "      \"Price\":" + this.price + "\n";
        temp = temp + "      }";
        return temp;
    }
    public String toCheck(){
        String temp="";
        temp+=name+price;
        return temp;
    }
  
}
/*
 *     public String toString(){
        String temp="";
        if(price!=0.00){
            if(price>15.00&&price!=18.95){
                temp += " "+n.format(price);
                temp += "          "+name+"\n";
            }
            else{
                if(price<10.00){temp+="  ";}
                if(name.contains("Soup")||name.contains("Khang Lao")){
                    temp += " "+n.format(price);
                    temp +="            "+name+"\n";
                }
                else{
                    temp += " "+n.format(price);
                    temp += "                    "+"          "+name+"\n";
                }
            }
        }
        else{
            temp += "                     ";
            temp += "                    "+name+"\n";
        }
        return temp;
    }
 */