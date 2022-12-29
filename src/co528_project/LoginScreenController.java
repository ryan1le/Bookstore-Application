/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co528_project;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 *
 * @author Ryan Le
 */
public class LoginScreenController implements Initializable {
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button backbt;
    @FXML
    private TextField userinput;
    @FXML
    private PasswordField passinput;
    @FXML
    private Label useremptyfield;
    @FXML
    private Label passemptyfield;
    
    
    @FXML
    private void loginButton(ActionEvent event) throws IOException{
        useremptyfield.setText("");
        passemptyfield.setText("");
        if (User.verify(userinput.getText(), passinput.getText()) == -1){
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ownerstartscreen.fxml"));
            stage.setScene(new Scene(root)); 
            stage.show();
        }
        else if (User.verify(userinput.getText(), passinput.getText()) == 1){
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("CustomerDocument.fxml"));
            stage.setScene(new Scene(root)); 
            stage.show();
        }
        else if (userinput.getText().isEmpty() || passinput.getText().isEmpty()){
            if (userinput.getText().isEmpty()){
                useremptyfield.setText("Missing");
            }
            if (passinput.getText().isEmpty()){
                passemptyfield.setText("Missing");
            }
        }
        else if (User.verify(userinput.getText(), passinput.getText()) == 0){
            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("ErrorScreen.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();   
        }
    }
        
    @FXML
    private void backButton(ActionEvent event) throws IOException{
        stage = new Stage();
        stage = (Stage) backbt.getScene().getWindow();
        stage.close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
