package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author sd
 */

import java.util.Date;

 
public class CurrentTime{
   public String getTime(){
        String temp="";
        Date date = new Date();
        date.getTime();
        temp+=" "+date;
        return temp;
   }
 }
