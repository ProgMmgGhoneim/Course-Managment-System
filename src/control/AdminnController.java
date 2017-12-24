/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Check.validation;
import database.dao.AdminDao;
import database.vo.AdminVo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mustafa
 */
public class AdminnController implements Initializable {

    @FXML
    private TextField fname;
    @FXML
    private TextField sname;
    @FXML
    private TextField lname;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repassword;
    @FXML
    private TextField mobileoremail;
    @FXML
    private Button add_bt;
    @FXML
    private Hyperlink link;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ADD(MouseEvent event) throws Exception {
        int check = 0;

        AdminVo adminVo = new AdminVo();

        adminVo.setFName(fname.getText());
        adminVo.setLName(lname.getText());
        adminVo.setMobilreOrEmail(mobileoremail.getText());
        adminVo.setPassword(password.getText());
        adminVo.setSName(sname.getText());
        adminVo.setUserName(username.getText());

        validation valid = new Check.validation();

        if (valid.isEmpty(fname.getText(), sname.getText(), lname.getText(), mobileoremail.getText(), password.getText(), repassword.getText(), username.getText())) {
            JOptionPane.showMessageDialog(null, "Please ,Enter Value ");
            return;

        } else if (!password.getText().equals(repassword.getText())) {
            JOptionPane.showMessageDialog(null, "password unmatch");
            return;
        }

        int inseret = new AdminDao().inseret(adminVo);
        if (inseret == 1) {
            JOptionPane.showMessageDialog(null, "ADD Successfully");
            Stage stage1 = (Stage) link.getScene().getWindow();
            stage1.close();

            Stage primStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../view/log.fxml"));
            Scene scene = new Scene(root);
            primStage.setScene(scene);
            primStage.setTitle("Log IN");
            primStage.setMaximized(false);
            primStage.setResizable(false);
            primStage.show();

        }

    }

    @FXML
    private void Cancel(MouseEvent event) throws IOException {
        Stage stage1 = (Stage) link.getScene().getWindow();
        stage1.close();

        Stage primStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../view/log.fxml"));
        Scene scene = new Scene(root);
        primStage.setScene(scene);
        primStage.setTitle("Log IN");
        primStage.setMaximized(false);
        primStage.setResizable(false);
        primStage.show();
    }

}
