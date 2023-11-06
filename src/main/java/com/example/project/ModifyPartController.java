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

public class ModifyPartController implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public TextField IDField, NameField, InvField, PriceField, MaxField, MinField, CompanyMachineField;
    @FXML
    public RadioButton OutsourcedRadioButton, InhouseRadioButton;
    @FXML
    public Text RadioTextChange;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Model.getInstance().getPartIndexToBeModified());
        ToggleGroup AddPartToggle = new ToggleGroup();
        InhouseRadioButton.setToggleGroup(AddPartToggle);
        OutsourcedRadioButton.setToggleGroup(AddPartToggle);
        Part modifiedPart = Model.getInstance().getInventory().getAllParts().get(Model.getInstance().getPartIndexToBeModified());
        if (modifiedPart.getClass() == InHouse.class) {
            InhouseRadioButton.setSelected(true);
            RadioTextChange.setText("Machine ID");

            System.out.println(InHouse.class);
            InHouse house = (InHouse) modifiedPart;
            IDField.setText(String.valueOf(modifiedPart.getId()));
            NameField.setText(modifiedPart.getName());
            InvField.setText(String.valueOf(modifiedPart.getStock()));
            PriceField.setText(String.valueOf(modifiedPart.getPrice()));
            MaxField.setText(String.valueOf(modifiedPart.getMax()));
            MinField.setText(String.valueOf(modifiedPart.getMin()));
            CompanyMachineField.setText(String.valueOf(house.getMachineId()));
        } else {
            OutsourcedRadioButton.setSelected(true);
            Outsourced outsource = (Outsourced) modifiedPart;
            IDField.setText(String.valueOf(modifiedPart.getId()));
            NameField.setText(modifiedPart.getName());
            InvField.setText(String.valueOf(modifiedPart.getStock()));
            PriceField.setText(String.valueOf(modifiedPart.getPrice()));
            MaxField.setText(String.valueOf(modifiedPart.getMax()));
            MinField.setText(String.valueOf(modifiedPart.getMin()));
            CompanyMachineField.setText(String.valueOf(outsource.getCompanyName()));

        }

    }

    /**
     *
     * @param event is when the save button is clicked.
     *This method grabs the text from the input fields,
     *throws exceptions based on required limits,
     *Then adds either an inhouse or outsourced part.
     */
    public void SaveButton(ActionEvent event) {

        try {

//            Model.getInstance().inventory.getAllParts().indexOf()

            String inputName = NameField.getText();
            Double inputPrice = Double.parseDouble(PriceField.getText());
            Integer inputInv = Integer.parseInt(InvField.getText());
            Integer inputMin = Integer.parseInt(MinField.getText());
            Integer inputMax = Integer.parseInt(MaxField.getText());
            String inputMachineID = CompanyMachineField.getText();

            if (inputMin > inputMax) {
                throw new Exception("Min should be less than Max ");
            }
            if (inputInv < inputMin || inputInv > inputMax) {
                throw new Exception("Inv must be between Min and Max ");
            }

//        Outsourced testy = new Outsourced()

            if (InhouseRadioButton.isSelected()) {
                String confirmationMsg = "Are you sure you would like to modify this part?";
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, confirmationMsg, ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    InHouse newPart = new InHouse(Integer.parseInt(IDField.getText()), inputName, inputPrice, inputInv, inputMin, inputMax, Integer.parseInt(inputMachineID));
                    Model.getInstance().getInventory().updatePart(Model.getInstance().getPartIndexToBeModified(), newPart);
                    alert.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    root = loader.load();
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    //stage.setTitle("Hello!");
                    stage.setScene(scene);
                    stage.show();

                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }

            } else if (OutsourcedRadioButton.isSelected()) {
                String confirmationMsg = "Are you sure you would like to modify this part?";
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, confirmationMsg, ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    Outsourced newPart = new Outsourced(Integer.parseInt(IDField.getText()), inputName, inputPrice, inputInv, inputMin, inputMax, inputMachineID);
                    Model.getInstance().getInventory().updatePart(Model.getInstance().getPartIndexToBeModified(), newPart);
                    alert.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                    root = loader.load();
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    //stage.setTitle("Hello!");
                    stage.setScene(scene);
                    stage.show();

                }

            }


        }

        catch(NumberFormatException nfe){
            AlertMethod("The input " + nfe.getMessage() + "was the wrong data type.");


        }
        catch(Exception wrongType){
            AlertMethod("Something went wrong" + wrongType);
        }
    }

    /**
     *Returns to home page.
     * @param actionEvent
     * @throws IOException
     */
    public void CancelButton(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the text depending on which radio button is selected.
     * @param ev
     */
    public void ListenRadio(ActionEvent ev) {
        if (InhouseRadioButton.isSelected()) {
            RadioTextChange.setText("Machine ID");
        } else if (OutsourcedRadioButton.isSelected()) {
            RadioTextChange.setText("Company Name");
        }
    }

    /**
     *
     * @param x is the specified error message.
     * Pops up a confirmation type alert with error message and OK button.
     */
    public void AlertMethod(String x) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, x, ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    /**
     *Exits the program
     *@param event
     *@throws IOException
     */
    public void ExitButton(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}

