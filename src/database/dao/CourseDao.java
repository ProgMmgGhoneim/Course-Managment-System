/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database.dao;

import static database.dao.Dao.getconnection;
import database.vo.CourseVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mustafa
 */
public class CourseDao extends Dao implements Daolist<CourseVo>{

    @Override
    public List<CourseVo> LoadAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int inseret(CourseVo t) throws Exception {
          Connection con;
        int count = 0;
        try {
            con = getconnection();
            String sql = "insert into course (Course_Name,Hour,price,Doc_ID) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            
            ps.setString(1, t.getCourse_Name());
            ps.setInt(2, t.getCourse_Hour());
            ps.setInt(3, t.getCourse_Price());
            ps.setInt(4, t.getDoc_id());
            
            count = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "in inseret");  
            
        } catch (Exception ex) {

            throw ex;
        }
         return count;

    }

    @Override
    public int update(CourseVo t) throws Exception {
           Connection con;
        int count = 0;
        try {
            con = getconnection();
            String sql = "UPDATE course set  Hour =? ,price =? ,Doc_ID =? where Course_Name =?";
            PreparedStatement ps = con.prepareStatement(sql);
           
            ps.setInt(1, t.getCourse_Hour());
            ps.setInt(2, t.getCourse_Price());
            ps.setInt(3, t.getDoc_id());
            ps.setString(4, t.getCourse_Name());
            
            count = ps.executeUpdate();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "in update"+ex.getMessage());

            throw ex;
        }

        return count;
    }

    @Override
    public void delet(CourseVo t) throws Exception {
        
             Object[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(null, "Do you like to delete the record for Student ID: " + t.getCourseSearch() + " ?", "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
            if(n == JOptionPane.YES_OPTION) {
           Connection con;
        try {
            con = getconnection();
            String sql = "DELETE FROM course WHERE Course_Name= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getCourseSearch());
            ps.execute();
            JOptionPane.showMessageDialog(null, "The record has been deleted successfully.");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "in delet "+ex.getMessage());
            throw ex;
        }
             }
     }

    @Override
    public CourseVo getdata(CourseVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
