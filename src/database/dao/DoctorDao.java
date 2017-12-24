package database.dao;

import control.DoctorController;
import static database.dao.Dao.getconnection;
import database.vo.DoctorVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JOptionPane;

public class DoctorDao extends Dao implements Daolist<DoctorVo> {

    @Override
    public List<DoctorVo> LoadAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int inseret(DoctorVo t) throws Exception {
        Connection con;
        int count = 0;
        try {
            con = getconnection();
            String sql = "insert into doctor (ID,First_Name,Scened_Name,Last_Name,mobile,Paid_Money) values(?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getId());
            ps.setString(2, t.getFName());
            ps.setString(3, t.getSName());
            ps.setString(4, t.getLName());
            ps.setString(5, t.getMobile());
            ps.setInt(6, t.getDoc_pay());
            count = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "in inseret");  
            
        } catch (Exception ex) {

            throw ex;
        }

        return count;
    }

    @Override
    public int update(DoctorVo t) throws Exception {
        Connection con;
        int count = 0;
        try {
            con = getconnection();
            String sql = "UPDATE doctor set  First_Name =? ,Scened_Name =? ,Last_Name =?,mobile =? ,Paid_Money =? where ID =?";
            PreparedStatement ps = con.prepareStatement(sql);
           
            ps.setString(1, t.getFName());
            ps.setString(2, t.getSName());
            ps.setString(3, t.getLName());
            ps.setString(4, t.getMobile());
            ps.setInt(5, t.getDoc_pay());
             ps.setInt(6, t.getId());
            
            count = ps.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "in updata");
            throw ex;
        }

        return count;
    }

    @Override
    public void delet(DoctorVo t) throws Exception {
            Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(null, "Do you like to delete the record for Student ID: " + t.getSearch() + " ?", "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
            if(n == JOptionPane.YES_OPTION) {
            
              Connection con;
        try {
            con = getconnection();
            
            String sql = "DELETE FROM doctor WHERE ID= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getSearch());
            ps.execute();
            JOptionPane.showMessageDialog(null, "The record has been deleted successfully.");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "check that you delete the doctor from courses ");
            throw ex;
        }

            }

      
         
    }

    @Override
    public DoctorVo getdata(DoctorVo t) throws Exception {
        Connection con=null;
        DoctorVo doctorvo = null;
        ResultSet rs=null;
        
         try{
         con=getconnection();
         String sql="select * from doctor where ID=?";
          PreparedStatement ps=con.prepareCall(sql);
          ps.setInt(1, t.getSearch());
             rs=ps.executeQuery();
                     while(rs.next()){
                System.out.println(rs.getString(1));
              
              
              
            } 
         }
           catch(Exception ex){
           
           
            } 
         return doctorvo;
                
    }

}
