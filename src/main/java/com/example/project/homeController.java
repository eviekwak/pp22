package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController implements Initializable {

    @FXML
    public TableColumn<Part, String> PartNameColumnHome,PartInventLvlColumnHome, PartPriceColumnHome;
    public TextField IHIDField, IHNameField,IHInvField, IHPriceField, IHCompanyNameField, IHMinField,IHMaxField;
    public RadioButton IHOutsourcedRadioButton,IHInhouseRadioButton;


    @FXML
    private TableView<Part> PartsTableviewHome;
    @FXML
    private TableColumn<Part, Integer> PartIDColumnHome;

    @FXML
    private Text leLabel;

    @FXML
    public void AddPartsButtonHome(ActionEvent actionEvent) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }

    public void ModifyPartsButtonHome(ActionEvent actionEvent) {
    }

    public void DeletePartsButtonHome(ActionEvent actionEvent) {
    }

    public void AddProductsButtonHome(ActionEvent actionEvent) {
    }

    public void ModifyProductsButtonHome(ActionEvent actionEvent) {
    }

    public void DeleteProductsButtonHome(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inventory inventory = new Inventory();
        InHouse testInHousePart = new InHouse(1,"Screw", 5.00, 2, 1,5, 100);
        InHouse test2InHousePart = new InHouse(24,"yep", 5.00, 2, 1,5, 100);
        inventory.addPart(testInHousePart);
        inventory.addPart(test2InHousePart);
        PartIDColumnHome.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartNameColumnHome.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartInventLvlColumnHome.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartInventLvlColumnHome.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsTableviewHome.setItems(inventory.getAllParts());

    }

    public void SaveInhouseButton(ActionEvent actionEvent) {
    }

    public void CancelInhouseButton(ActionEvent actionEvent) {
    }
}