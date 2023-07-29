package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public TextField IDField, NameField,InvField, PriceField, MinField,MaxField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized addanyproduct");
        int predeterminedID = Model.getInstance().returnProductID();
        IDField.setText(String.valueOf(predeterminedID));

        System.out.println("Initialized home");
        PartIDColumnHome.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartNameColumnHome.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartInventLvlColumnHome.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPriceColumnHome.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartsTableviewHome.setItems(Model.getInstance().getInventory().getAllParts());

        ProductIDColumnHome.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        ProductNameColumnHome.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        ProductInventLvlColumnHome.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        ProductPriceColumnHome.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProductsTableviewHome.setItems(Model.getInstance().getInventory().getAllProducts());
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
