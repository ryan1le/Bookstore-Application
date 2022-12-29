/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co528_project;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Ryan Le
 */
public class MainDriver extends Application {
    
    Database customerData = CustomerData.getInstance();
    Database booksData = BooksData.getInstance();
    
    Parent root;
    Stage stage;
    Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        
        scene = new Scene(root);
        stage.setTitle("Bookstore App");
        stage.setScene(scene);
        stage.setResizable(false);
        Image image = new Image("/images/icon.png");
        stage.getIcons().add(image);
        stage.show();
        
        stage.setOnCloseRequest(event -> {
            event.consume();
            closeApp(stage);
            });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void logout(ActionEvent event) throws Exception{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void closeApp(Stage stage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to close the application!");
        alert.setContentText("Do you wish to close the application?: ");
        
        if (alert.showAndWait().get() == ButtonType.OK){
            customerData.save();
            booksData.save();
            stage.close();
        }
    }
    
}
