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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class homeController implements Initializable {

    public Text CompanyNameText;

    private Parent root;
    private Scene scene;
    private Stage stage = new Stage();

    @FXML
    public TableColumn<Part, String> PartNameColumnHome,PartInventLvlColumnHome, PartPriceColumnHome;




    @FXML
    private TableView<Part> PartsTableviewHome;
    @FXML
    private TableColumn<Part, Integer> PartIDColumnHome;

    @FXML
    public void AddPartsButtonHome(ActionEvent event) throws IOException {

//        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddAnyPart.fxml"));
//        Scene tableViewScene = new Scene(tableViewParent);
//        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//        window.setScene(tableViewScene);
//        window.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAnyPart.fxml"));
        root = loader.load();
        AddAnyPartController addAnyPartController = loader.getController();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");

        stage.setScene(scene);
        stage.show();
        addAnyPartController.IHNameField.setText("dd");

    }

    public void ModifyPartsButtonHome(ActionEvent actionEvent) {

//
//        System.out.println(randomNumber);
//        System.out.println("test");

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
        System.out.println("Initialized home");
        PartIDColumnHome.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartNameColumnHome.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartInventLvlColumnHome.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartInventLvlColumnHome.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartsTableviewHome.setItems(Model.getInstance().getInventory().getAllParts());

    }

}