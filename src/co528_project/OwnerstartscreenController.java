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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ryan Le
 */
public class OwnerstartscreenController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private void booksbutton(ActionEvent event) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OwnersBooksScreen.fxml"));
        stage.setScene(new Scene(root)); 
        stage.show();
    }
    
    @FXML
    private void customerbutton(ActionEvent event) throws IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("OwnersCustomerScreen.fxml"));
        stage.setScene(new Scene(root)); 
        stage.show();
    }
    
    @FXML
    private void logoutbutton(ActionEvent event) throws IOException, Exception{    
        MainDriver logout = new MainDriver();
        logout.logout(event);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
