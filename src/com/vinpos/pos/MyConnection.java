package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinson
 */
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JLabel;
import com.vinpos.connection.PrinterPrintingScreen;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MyConnection {
    /**
     * return setting file about database ip and printer ip
     * @param js is setting file retrival class 
     * @return 
     */
    public static Connection getConnection(final JsonSimple js) {
        final DummyThread du = new DummyThread() ;
        du.setJS(js);
        du.start();   // use a new thread to do the connection
        
            /**
             *  customized timeout method.
             *  use JDialog to count down few seconds, whether connection success or not, operation will return the connection whether it is null or has connection object.
             */         
            final JDialog d = new JDialog();  
            final JPanel p1 = new JPanel(new GridBagLayout());  
            
            final JLabel jl=new JLabel("Please Wait...");
            p1.add(jl,new GridBagConstraints());             
            d.getContentPane().add(p1);  
            d.setSize(300,100);  
            d.setUndecorated(true);
            d.setAlwaysOnTop(true);
            d.setLocationRelativeTo(null);  
            d.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);  
            d.setModal(true);  
            //final NewJDialog animation = new NewJDialog(new java.awt.Frame(), true);// customed jdialog
            Thread t = new Thread(){  
                public void run(){  
                    for (int x = 0; x <= 100; x+=10)  
                    {  
                        d.setOpacity(0.9f);
                        d.getRootPane().setOpaque(false);
                        p1.setOpaque(false);
                        final int selection = x;  
                        SwingUtilities.invokeLater(new Runnable(){//do swing work on EDT  
                            public void run(){  
                                System.out.println("JJPOS Connecting...  "+selection+"%");
                                jl.setText("JJ POS Connecting... "+selection+"%");
                       
                            }  
                        });  
                        try{  
                            Thread.sleep(250);  
                        }  
                        catch (InterruptedException e) {e.printStackTrace();}  
                    } 
                    
                    SwingUtilities.invokeLater(new Runnable(){//do swing work on EDT  
                        public void run(){  
                            d.dispose(); 
                           

                        }  
                    });  
                }  
            };  
            t.start();  
            d.setVisible(true);
            
            
            
        System.out.println("returning connection");
        return du.connection ;
       
    }
    
    public static void closeConnection(Connection connection) throws SQLException {
        //if (connection != null && !connection.isClosed()) {         
            connection.close();
        //}
    }
    public  void closeRs(ResultSet resultSet) throws SQLException{
        //if (resultSet != null) {         
            resultSet.close();
        //}
    }
    public  void closePs(PreparedStatement preparedStatement) throws SQLException{
        //if (preparedStatement != null) {         
            preparedStatement.close();
        //}
    }
}

class DummyThread extends Thread {
    public JsonSimple js;
    public volatile Connection connection = null;
     
    public void setJS(JsonSimple j){
        js=j;
    }
   
    @Override
    public void run() {
            String ip ="";
            String port="";
            String database="";
            String username="";
            String password="";
            ArrayList<String> setting =js.getAllSetting();
            ip=setting.get(0);  
            port=setting.get(1);
            database=setting.get(2);
            username=setting.get(3);
            password=setting.get(4);

            String url = "jdbc:mysql://"+ip+":"+port+"/"+database;

            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection(url, username, password);
            }catch (SQLException ex) {
                ex.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}