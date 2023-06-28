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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddAnyPartController implements Initializable {
private Parent root;
private Stage stage;
private Scene scene;

    @FXML
    private AnchorPane rootPane;

    @FXML
    public TextField IHIDField, IHNameField,IHInvField, IHPriceField, IHCompanyNameField, IHMinField,IHMaxField;
    @FXML
    public RadioButton IHOutsourcedRadioButton,IHInhouseRadioButton;









    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    System.out.println("Initialized addanypart");

    }

    public void SaveInhouseButton(ActionEvent actionEvent) {

    }

    public void CancelInhouseButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }
}