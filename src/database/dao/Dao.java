package database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
    public static Connection getconnection() throws Exception{
      
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost/course_management","mmg","123");
        if(con!=null){
            System.out.println("connection seccessful");
            return con;
        }
            return null;
    }
    public static void main(String[] args) throws Exception {
        getconnection();
    }
}
