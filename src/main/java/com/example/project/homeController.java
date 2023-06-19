package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class homeController {


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
}