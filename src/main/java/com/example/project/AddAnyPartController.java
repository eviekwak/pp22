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
    int predeterminedID = Model.getInstance().returnPartID();
    IHIDField.setText(String.valueOf(predeterminedID));
    }

    /**
     *
     * @param actionEvent is when the save button is clicked.
     * This method grabs the text from the input fields,
     * throws exceptions based on required limits,
     * Then adds either an inhouse or outsourced part.
     */
    public void SaveButton(ActionEvent actionEvent) {

        try {

            //this block grabs the text from the input fields
            System.out.println(IHIDField.getText());
            String inputName = IHNameField.getText();
            Double inputPrice = Double.parseDouble(IHPriceField.getText());
            Integer inputInv = Integer.parseInt(IHInvField.getText());
            Integer inputMin = Integer.parseInt(IHMinField.getText());
            Integer inputMax = Integer.parseInt(IHMaxField.getText());
            String inputMachineID = IHMachineIDField.getText();

            //throws exceptions based on required limits
            if (inputMin > inputMax){
                throw new Exception("Min should be less than Max ");}
            if (inputInv < inputMin || inputInv > inputMax){
                throw new Exception("Inv must be between Min and Max ");
            }

            //adds inhouse part
            if (IHInhouseRadioButton.isSelected()){

            InHouse newPart = new InHouse(Model.getInstance().returnPartID(), inputName, inputPrice, inputInv, inputMin,inputMax, Integer.parseInt(inputMachineID));
            Model.getInstance().getInventory().addPart(newPart);
        }
            //adds outsourced part
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

    /**
     * @param event is when the button is clicked.
     * Returns to home page.
     */
    public void CancelButton(ActionEvent event) throws IOException { //returns to home page

        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Changes the text label based on
     * which radio button is currently selected.
     */
    public void ListenRadio(ActionEvent ev){
        if (IHInhouseRadioButton.isSelected()) {
        RadioTextChange.setText("Machine ID");
        }
        else if (IHOutsourcedRadioButton.isSelected()){
        RadioTextChange.setText("Company Name");
        }
    }

    /**
     *
     * @param x is the specified error message.
     * Pops up a confirmation type alert with error message and OK button.
     */
    public void AlertMethod(String x){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, x, ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }
    public void ExitButton(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}