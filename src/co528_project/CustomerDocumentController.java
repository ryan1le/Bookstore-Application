/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co528_project;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.control.*;


/**
 *
 * @author Harish
 */
public class CustomerDocumentController implements Initializable {
    
    Database books = BooksData.getInstance();
    
    Purchase buy = new Purchase();
    Modifier mod = new Modifier();
    
    static double priceSum; 
    
    private Parent root;
    private Scene scene;
    private Stage stage;
    
    @FXML
    private Label lblIntro;
    
    @FXML
    private TableColumn<TableBookInfo, String> listName;

    @FXML
    private TableColumn<TableBookInfo, Double> listPrice;

    @FXML
    private TableColumn<TableBookInfo, CheckBox> select;
    
    @FXML
    private TableView<TableBookInfo> table;
    
    ObservableList<TableBookInfo> tableData = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Double> pointsArr = CustomerData.getInstance().getNumericValue();
        ArrayList<String> bookNames = books.getName();
        ArrayList<Double> bookPrices = books.getNumericValue();
        
        //(points/username inherited)
                           
        lblIntro.setText("Welcome " + User.username + ". You have " + (int)User.points + " points. Your status is " + getStatus(User.points));
        
        //Populate table with data     
        for(int i = 0; i < bookNames.size(); i++)
        {
            CheckBox checkBox = new CheckBox();
                                                               
            tableData.add(new TableBookInfo(bookNames.get(i), bookPrices.get(i), checkBox));
        }
        
        listName.setCellValueFactory(new PropertyValueFactory<>("listName"));
        listPrice.setCellValueFactory(new PropertyValueFactory<>("listPrice"));
        select.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        table.setItems(tableData);
    }   
    
    @FXML
    void buySelected(ActionEvent event) throws IOException
    {
        priceSum = 0;              
        for (TableBookInfo i : tableData) 
        {
            if(i.getSelect().isSelected())
            {      
                priceSum += buy.calculate(i.getListName());
                mod.delete(books, i.getListName());
            }
        }
        buy.pointsUpdate(priceSum, User.username, 0);
        
        //Open cost screen
        root = FXMLLoader.load(getClass().getResource("CustomerCost.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
    }
    
    @FXML
    void buySelectedPoints(ActionEvent event) throws IOException
    {
        if (User.points > 0.0){
            priceSum = 0; 
            for (TableBookInfo i : tableData) 
            {
                if(i.getSelect().isSelected())
                {      
                    priceSum += buy.calculate(i.getListName());
                    mod.delete(books, i.getListName());
                }
            }
            priceSum = buy.calculatewithPoints(User.points, priceSum, User.username);

            root = FXMLLoader.load(getClass().getResource("CustomerCost.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("INVALID");
            alert.setHeaderText("Insufficient Points");
            alert.setContentText("You have 0 points!");
            
            if (alert.showAndWait().get() == ButtonType.OK){
            stage.close();
        }
            
        }
        
    }
    
    @FXML
    private void logoutbutton(ActionEvent event) throws IOException, Exception{    
        MainDriver logout = new MainDriver();
        logout.logout(event);
    }
    
    protected String getStatus(double points)
    {
        if(points < 1000)
            return "Silver";
        
        return "Gold";
    }
    
}
