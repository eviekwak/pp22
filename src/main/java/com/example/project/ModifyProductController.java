package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    public TableView<Part> AddAssociatedPartTableview, RemoveAssociatedPartTableview;
    @FXML
    private Text AllPartsSearchMessage, AssociatedPartsSearchMessage;
    @FXML
    public TableColumn<Part, String> AllPartsNameColumn, AllPartsPriceColumn, AssociatedPartsPartNameColumn, AssociatedPartsPriceColumn;
    @FXML
    private TableColumn<Part, Integer> AllPartsPartIDColumn, AllPartsInvColumn, AssociatedPartsPartIDColumn, AssociatedPartsInvColumn;
    @FXML
    private TextField AddAssociatedPartSearch, RemoveAssociatedPartSearch;
    @FXML
    public TextField IDField, NameField, InvField, PriceField, MinField, MaxField;
    ObservableList<Part> AssociatedPartsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Product modifiedProduct = Model.getInstance().getInventory().getAllProducts().get(Model.getInstance().getProductIndexToBeModified());

        IDField.setText(String.valueOf(modifiedProduct.getId()));
        NameField.setText(modifiedProduct.getName());
        InvField.setText(String.valueOf(modifiedProduct.getStock()));
        PriceField.setText(String.valueOf(modifiedProduct.getPrice()));
        MaxField.setText(String.valueOf(modifiedProduct.getMax()));
        MinField.setText(String.valueOf(modifiedProduct.getMin()));
        }



    public void SaveButton(ActionEvent event) {

    }

    public void CancelButton(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void AlertMethod(String x) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, x, ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public void AddAssociatedPartButton(ActionEvent actionEvent) {

    }

    public void RemoveAssociatedPartButton(ActionEvent actionEvent) {

    }
    public void ExitButton(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}

