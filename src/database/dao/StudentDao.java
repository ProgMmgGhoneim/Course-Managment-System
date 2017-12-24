 
package database.dao;

import static database.dao.Dao.getconnection;
import database.vo.StudentVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JOptionPane;


public class StudentDao extends Dao implements Daolist<StudentVo>{

    @Override
    public List<StudentVo> LoadAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int inseret(StudentVo t) throws Exception {
                Connection con;
        int count = 0;
        try {
            con = getconnection();
            String sql = "insert into student (ID,First_Name,Scened_Name,Last_Name,Mobile,Paid_Money,course) values(?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, t.getId());
            ps.setString(2, t.getFName());
            ps.setString(3, t.getSName());
            ps.setString(4, t.getLName());
            ps.setString(5, t.getMobile());
            ps.setInt(6, t.getPaidlMo());
            ps.setString(7, t.getCourses());
            
            count = ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "in inseret");  
            
        } catch (Exception ex) {

            throw ex;
        }
         return count;
}

    @Override
    public int update(StudentVo t) throws Exception {
            Connection con;
        int count = 0;
        try {
            con = getconnection();
            String sql = "UPDATE student set  First_Name =? ,Scened_Name =? ,Last_Name =?,Mobile =? ,Paid_Money =? where ID =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, t.getFName());
            ps.setString(2, t.getSName());
            ps.setString(3, t.getLName());
            ps.setString(4, t.getMobile());
            ps.setInt(5, t.getPaidlMo());
            ps.setInt(6, t.getId());
            
            count = ps.executeUpdate();
        } catch (Exception ex) {

            throw ex;
        }

        return count;
    }

    @Override
    public void delet(StudentVo t) throws Exception {
          Connection con;
        try {
            con = getconnection();
            
            String sql = "DELETE FROM student WHERE ID= ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, t.getID_Search());
            ps.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            throw ex;
        }
    }

    @Override
    public StudentVo getdata(StudentVo t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
