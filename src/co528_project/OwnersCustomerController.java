package co528_project;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class OwnersCustomerController implements Initializable {

    Database customerData = CustomerData.getInstance();
    Modifier mod = new Modifier();
    
    Parent root;
    Stage stage;
    
    //Table
    @FXML
    private TableView<OwnerCust> tableView;

    //Columns
    @FXML
    private TableColumn<OwnerCust, String> nameColumn;

    @FXML
    private TableColumn<OwnerCust, String> passColumn;

    @FXML
    private TableColumn<OwnerCust, Integer> pointsColumn;

    //Text input
    @FXML
    private TextField nameInput;

    @FXML
    private TextField passInput;

    ObservableList<OwnerCust> customers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<OwnerCust, String>("name"));
        passColumn.setCellValueFactory(new PropertyValueFactory<OwnerCust, String>("pass"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<OwnerCust, Integer>("points"));
        
        
        ArrayList<Double> pointsArr = customerData.getNumericValue();
        ArrayList<String> username = customerData.getName();
        ArrayList<String> password = customerData.getPassword();
        
        for (int i = 0; i < username.size(); i++){
            customers.add(new OwnerCust(username.get(i), password.get(i), pointsArr.get(i).intValue()));
        }
        tableView.setItems(customers);
    }
    
    //Add button
    @FXML
    void add(ActionEvent event) {
        if (customerData.getName().indexOf(nameInput.getText()) == -1 && !(passInput.getText().isEmpty()) && !(nameInput.getText().isEmpty())){
            OwnerCust customer = new OwnerCust(nameInput.getText(),
                (passInput.getText()), 0);
            mod.add(customerData, nameInput.getText(),passInput.getText(), 0.0);
            customers.add(customer);
            tableView.setItems(customers);
            nameInput.setText("");
            passInput.setText("");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("INVALID");
            alert.setHeaderText("Invalid entry!");
            alert.setContentText("Please enter a valid Username/Password!");
            
            if (alert.showAndWait().get() == ButtonType.OK){
                stage.close();
            }
        }
    }

   @FXML
    void deleteC(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        mod.delete(customerData, customers.get(selectedID).getName());
        tableView.getItems().remove(selectedID);
    }
    
    @FXML
    private void backsButton(ActionEvent event) throws IOException{    
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("ownerstartscreen.fxml"));
        stage.setScene(new Scene(root)); 
        stage.show();
    }
}
