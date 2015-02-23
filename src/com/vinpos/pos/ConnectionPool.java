package com.vinpos.pos;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.*;


public class ConnectionPool {
    public static ComboPooledDataSource getConnection() throws Exception {
       
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver            
        cpds.setJdbcUrl( "jdbc:mysql://192.168.1.2:3306/r" );
        cpds.setUser("root");                                  
        cpds.setPassword("");                                  

        // the settings below are optional -- c3p0 can work with defaults
        cpds.setMinPoolSize(5);                                     
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        
        
        return cpds;
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
