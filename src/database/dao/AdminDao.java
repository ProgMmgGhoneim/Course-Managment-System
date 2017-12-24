/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.dao;

import database.vo.AdminVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mustafa
 */
public class AdminDao extends Dao implements Daolist<AdminVo>{
     private static AdminDao userDao;
       public AdminDao()
       {
       
       }
      public static AdminDao getInstance()
       {
         if(userDao == null){
             userDao=new AdminDao();
                  }
         
             return userDao; 
       }


    @Override
    public List<AdminVo> LoadAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int inseret(AdminVo t) throws Exception {
          Connection con;
     int count=0;
        try{
            
         con=getconnection();
           String sql="insert into admin (First_Name,Last_Name,Scened_Name,User_Name,Mobile,password) values(?,?,?,?,?,?)";
           PreparedStatement ps=con.prepareStatement(sql);
           ps.setString(1, t.getFName());
           ps.setString(2, t.getLName());
           ps.setString(3, t.getSName());
           ps.setString(4,t.getUserName());
           ps.setString(5, t.getMobilreOrEmail());
           ps.setString(6, t.getPassword());
           count=ps.executeUpdate();
        }
        
        
        catch(Exception ex){
        
            JOptionPane.showMessageDialog(null, ex.getMessage());
        
        }
        
        return   count; 

    }

    @Override
    public int update(AdminVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delet(AdminVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdminVo getdata(AdminVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }
    
}
