package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized addanyproduct");
        int predeterminedID = Model.getInstance().returnProductID();
        IHIDField.setText(String.valueOf(predeterminedID));
    }

    public void SaveButton(ActionEvent actionEvent) {


    }

    /**
     * @param event is when the button is clicked.
     * Returns to home page.
     */
    public void CancelButton(ActionEvent event) throws IOException { //returns to home page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }
    public void AddAssociatedPartButton(ActionEvent actionEvent){

    }
    public void RemoveAssociatedPartButton(ActionEvent actionEvent){

    }
    public void ExitButton(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void AddAllPartsSearch(KeyEvent keyEvent) {
    }

    public void AddAssociatedPartsSearch(KeyEvent keyEvent) {
    }
}
