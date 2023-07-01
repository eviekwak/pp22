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
    public TextField IHIDField, IHNameField,IHInvField, IHPriceField, IHMachineIDField, IHMinField,IHMaxField;
    @FXML
    public RadioButton IHOutsourcedRadioButton,IHInhouseRadioButton;

    @FXML
    public Text RadioTextChange;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    System.out.println("Initialized addanypart");
    ToggleGroup AddPartToggle = new ToggleGroup();
    IHInhouseRadioButton.setToggleGroup(AddPartToggle);
    IHOutsourcedRadioButton.setToggleGroup(AddPartToggle);
    }

    public void SaveInhouseButton(ActionEvent actionEvent) {

        try {

            Integer x = 5;

            System.out.println(IHIDField.getText());
            String inputName = IHNameField.getText();
            Double inputPrice = Double.parseDouble(IHPriceField.getText());
            Integer inputInv = Integer.parseInt(IHInvField.getText());
            Integer inputMin = Integer.parseInt(IHMinField.getText());
            Integer inputMax = Integer.parseInt(IHMaxField.getText());
            String inputMachineID = IHMachineIDField.getText();

            if (inputMin > inputMax){
                throw new Exception("Min should be less than Max ");}
            if (inputInv < inputMin || inputInv > inputMax){
                throw new Exception("Inv must be between Min and Max ");
            }

//        Outsourced testy = new Outsourced()

            if (IHInhouseRadioButton.isSelected()){

            InHouse newPart = new InHouse(Model.getInstance().returnPartID(), inputName, inputPrice, inputInv, inputMin,inputMax, Integer.parseInt(inputMachineID));
            Model.getInstance().getInventory().addPart(newPart);
        }
        else if (IHOutsourcedRadioButton.isSelected()){
            Outsourced newPart = new Outsourced(Model.getInstance().returnPartID(), inputName, inputPrice, inputInv, inputMin,inputMax, inputMachineID);
            Model.getInstance().getInventory().addPart(newPart);
        }

    }
        catch (NumberFormatException nfe) {
            AlertMethod("The input " + nfe.getMessage() + "was the wrong data type.");


        }
        catch (Exception wrongType) {
        AlertMethod("Something went wrong" + wrongType);
    }


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

    public void ListenRadio(ActionEvent ev){
        if (IHInhouseRadioButton.isSelected()) {
        RadioTextChange.setText("Machine ID");
        }
        else if (IHOutsourcedRadioButton.isSelected()){
        RadioTextChange.setText("Company Name");
        }
    }
    public void AlertMethod(String x){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, x, ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }
}