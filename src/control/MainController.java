/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mustafa
 */
public class MainController implements Initializable {
    
    @FXML
    private Circle circleImgUsr;

    @FXML
    private MenuItem miPopOver;

    @FXML
    private Button btnLogOut;

    @FXML
    private ImageView imgMenuBtn;

    @FXML
    private StackPane acContent;

    @FXML
    private ToggleButton sideMenuToogleBtn;

    @FXML
    private Label lblUsrNamePopOver;

    @FXML
    private Button Doctor;

    @FXML
    private Button Student;

    @FXML
    private Circle imgUsrTop;

    @FXML
    private Label lblUsrName;

    @FXML
    private BorderPane appContent;

    @FXML
    private ImageView imgHomeBtn;

    @FXML
    private ImageView imgStoreBtn;

    @FXML
    private Label lblRoleAs;

    @FXML
    private AnchorPane Main_Anchor;

    @FXML
    private Label lblFullName;

    @FXML
    private MenuButton mbtnUsrInfoBox;

    @FXML
    private AnchorPane acHead;

    @FXML
    private ImageView imgSellBtn;

    @FXML
    private Button Course;

    @FXML
    private Label lblUserId;
    
    @FXML
    void Student_bt(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/Student.fxml").openStream());
        } catch (IOException e) {
            
        }
        AnchorPane root = fxmlLoader.getRoot();
        Main_Anchor.getChildren().clear();
        Main_Anchor.getChildren().add(root);
    }
    
    @FXML
    void Doctor_bt(){
                FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/Doctor.fxml").openStream());
        } catch (IOException e) {
            
        }
        AnchorPane root = fxmlLoader.getRoot();
        Main_Anchor.getChildren().clear();
        Main_Anchor.getChildren().add(root);
   
    }
    
    
    @FXML
    void Course_bt(){
                FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.load(getClass().getResource("/view/Course.fxml").openStream());
        } catch (IOException e) {
            
        }
        AnchorPane root = fxmlLoader.getRoot();
        Main_Anchor.getChildren().clear();
        Main_Anchor.getChildren().add(root);
   
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
