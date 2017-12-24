
package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class test extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("../view/log.fxml"));
        Scene scene =new Scene(root);
        primaryStage.setScene(scene); 
        primaryStage.setTitle("Log IN");
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.show();

     
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
