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

public class OwnersBooksController implements Initializable {

    Database bookData = BooksData.getInstance();
    Modifier mod = new Modifier();
    
    Parent root;
    Stage stage;
    
    //Table
    @FXML
    private TableView<ownerBooks> tableView;

    //Columns
    @FXML
    private TableColumn<ownerBooks, String> nameColumn;

    @FXML
    private TableColumn<ownerBooks, Double> priceColumn;

    //Text input
    @FXML
    private TextField nameInput;

    @FXML
    private TextField priceInput;
    
    ObservableList<ownerBooks> books = FXCollections.observableArrayList();
    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        ArrayList<Double> booksPrice = bookData.getNumericValue();
        ArrayList<String> booksName = bookData.getName();
        
        for (int i = 0; i < booksName.size(); i++){
            books.add(new ownerBooks(booksName.get(i), booksPrice.get(i)));
        }
        tableView.setItems(books);
    }

    //Add button
    @FXML
    void add(ActionEvent event) {
        double price;
        try{
            price = Double.parseDouble(priceInput.getText());
            if (bookData.getName().indexOf(nameInput.getText()) == -1){
                ownerBooks customer = new ownerBooks(nameInput.getText(),
                        price);
                mod.add(bookData, nameInput.getText(), Double.parseDouble(priceInput.getText()));
                books.add(customer);
                tableView.setItems(books);
                nameInput.setText("");
                priceInput.setText("");
            }
            else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("INVALID");
                alert.setHeaderText("Copy of that book already exists!");
                alert.setContentText("You must enter new book!");

                if (alert.showAndWait().get() == ButtonType.OK){
                    stage.close();
                }                
            }
        }
        catch (IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("INVALID");
            alert.setHeaderText("Incorrect Input");
            alert.setContentText("You must enter a number");
            
            if (alert.showAndWait().get() == ButtonType.OK){
                stage.close();
            }
        }
    }

    @FXML
    void deleteC(ActionEvent event) {
        int selectedID = tableView.getSelectionModel().getSelectedIndex();
        mod.delete(bookData, books.get(selectedID).getName());
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