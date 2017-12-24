
package control;

import Check.validation;
import database.dao.Dao;
import static database.dao.Dao.getconnection;
import database.vo.AdminVo;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LogController extends Dao implements Initializable {

    @FXML
    private TextField username_txt;
    @FXML
    private PasswordField pass_txt;
    @FXML
    private Button log_bt;
    @FXML
    private Button cancel_bt;
    @FXML
    private Hyperlink register;
    
    
    
    @FXML
    void log(){
            
        
          validation valid = new Check.validation();
            if(valid.isEmpty(username_txt.getText(),pass_txt.getText())){
                JOptionPane.showMessageDialog(null, "Please ,Enter Value ");
                return;
            } else
            {
                
                 Connection con=null;
                 ResultSet rs=null;
        
            try{
            con=getconnection();
            String sql="select * from admin where User_Name='"+
                    username_txt.getText()+
                    "'  AND password='" +
                    pass_txt.getText()+   "'  ";

                PreparedStatement ps=con.prepareCall(sql);
                rs=ps.executeQuery();

               if(rs.next()){
                   Stage stage1 = (Stage) log_bt.getScene().getWindow();
                   stage1.close();
                   Parent root = null;
                   try {
                       root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
                   } catch (IOException ex) {
                       Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   Scene Scene =new Scene(root);
                   Stage stage =new Stage();
                   stage.setScene(Scene);
                   stage.setResizable(true);
                   stage.setTitle("Home");
                   stage.setHeight(700);
                   stage.setWidth(1000);
                   stage.show();

            } else{
               
               JOptionPane.showMessageDialog(null, "OOooooops");
               username_txt.setText("");
               pass_txt.setText("");
               
               
               }
         }
           catch(Exception ex){
           
           }
            
        
            }
       
        
    }
    
    @FXML
    void cancel(){
        System.exit(0);
    }
    @FXML
    void add_admin() {
         Stage stage1 = (Stage) log_bt.getScene().getWindow();
                stage1.close();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../view/adminn.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(LogController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene Scene =new Scene(root);
                Stage stage =new Stage();
                stage.setScene(Scene);
                stage.setResizable(false);
                stage.setTitle("Admin");
                stage.show();
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
