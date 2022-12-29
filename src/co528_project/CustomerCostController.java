/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co528_project;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Harish
 */
public class CustomerCostController extends CustomerDocumentController implements Initializable {

    Database data = CustomerData.getInstance();
    
    @FXML
    private Label lblCost;
    @FXML
    private Label lblInfo;
    
    private Parent root;
    private Scene scene;
    private Stage stage;
    
    @FXML
    private void logoutbutton(ActionEvent event) throws IOException, Exception{    
        MainDriver logout = new MainDriver();
        logout.logout(event);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        int index = data.getName().indexOf(User.username);
        double points = data.getNumericValue().get(index);
        lblCost.setText("Total Cost: " + priceSum);    //ADD ON
        lblInfo.setText("Points: " + (int)points + ", Status: " + getStatus(points));
    }    
    
}
