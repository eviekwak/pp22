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
import javafx.scene.input.KeyEvent;
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
        AssociatedPartsList = modifiedProduct.getAllAssociatedParts();

        IDField.setText(String.valueOf(modifiedProduct.getId()));
        NameField.setText(modifiedProduct.getName());
        InvField.setText(String.valueOf(modifiedProduct.getStock()));
        PriceField.setText(String.valueOf(modifiedProduct.getPrice()));
        MaxField.setText(String.valueOf(modifiedProduct.getMax()));
        MinField.setText(String.valueOf(modifiedProduct.getMin()));

        AllPartsPartIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        AllPartsNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        AllPartsInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        AllPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        AddAssociatedPartTableview.setItems(Model.getInstance().getInventory().getAllParts());

        AssociatedPartsPartIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        AssociatedPartsPartNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        AssociatedPartsInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        AssociatedPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        RemoveAssociatedPartTableview.setItems(AssociatedPartsList);


    }



    public void SaveButton(ActionEvent event) throws IOException {
        try {

            //this block grabs the text from the input fields
            String inputName = NameField.getText();
            Double inputPrice = Double.parseDouble(PriceField.getText());
            Integer inputInv = Integer.parseInt(InvField.getText());
            Integer inputMin = Integer.parseInt(MinField.getText());
            Integer inputMax = Integer.parseInt(MaxField.getText());

            Product newProduct = new Product(Integer.parseInt(IDField.getText()), inputName, inputPrice, inputInv, inputMin,inputMax);
            for(Part associatedPart : AssociatedPartsList) {
                newProduct.addAssociatedPart(associatedPart);
            }

            //throws exceptions based on required limits
            if (inputMin > inputMax) {
                throw new Exception("Min should be less than Max ");
            }
            if (inputInv < inputMin || inputInv > inputMax) {
                throw new Exception("Inv must be between Min and Max ");
            }
            Model.getInstance().getInventory().updateProduct(Model.getInstance().getProductIndexToBeModified(), newProduct);

        } catch (NumberFormatException nfe) {
            AlertMethod("The input " + nfe.getMessage() + "was the wrong data type.");
        }
        catch (Exception wrongType) {
            AlertMethod("Something went wrong " + wrongType);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

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
        Part selectedPart = AddAssociatedPartTableview.getSelectionModel().getSelectedItem();
        AssociatedPartsList.add(selectedPart);
        RemoveAssociatedPartTableview.setItems(AssociatedPartsList);

    }

    public void RemoveAssociatedPartButton(ActionEvent actionEvent) {
        Part selectedPart = RemoveAssociatedPartTableview.getSelectionModel().getSelectedItem();
        AssociatedPartsList.remove(selectedPart);
        RemoveAssociatedPartTableview.setItems(AssociatedPartsList);
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

