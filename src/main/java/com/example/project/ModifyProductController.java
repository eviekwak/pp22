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

public class ModifyProductController implements Initializable {
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    public TextField IDField, NameField, InvField, PriceField, MaxField, MinField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Model.getInstance().getProductIndexToBeModified());
        Product modifiedProduct = Model.getInstance().getInventory().getAllProducts().get(Model.getInstance().getProductIndexToBeModified());


            IDField.setText(String.valueOf(modifiedProduct.getId()));
            NameField.setText(modifiedProduct.getName());
            InvField.setText(String.valueOf(modifiedProduct.getStock()));
            PriceField.setText(String.valueOf(modifiedProduct.getPrice()));
            MaxField.setText(String.valueOf(modifiedProduct.getMax()));
            MinField.setText(String.valueOf(modifiedProduct.getMin()));
        }



    public void SaveButton(ActionEvent event) {}
//
//        try {
//
////            Model.getInstance().inventory.getAllParts().indexOf()
//
//            String inputName = NameField.getText();
//            Double inputPrice = Double.parseDouble(PriceField.getText());
//            Integer inputInv = Integer.parseInt(InvField.getText());
//            Integer inputMin = Integer.parseInt(MinField.getText());
//            Integer inputMax = Integer.parseInt(MaxField.getText());
//
//            if (inputMin > inputMax) {
//                throw new Exception("Min should be less than Max ");
//            }
//            if (inputInv < inputMin || inputInv > inputMax) {
//                throw new Exception("Inv must be between Min and Max ");
//            }
//
////        Outsourced testy = new Outsourced()
//
//            if (InhouseRadioButton.isSelected()) {
//                String confirmationMsg = "Are you sure you would like to modify this part?";
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, confirmationMsg, ButtonType.YES, ButtonType.NO);
//                alert.showAndWait();
//
//                if (alert.getResult() == ButtonType.YES) {
//                    InHouse newPart = new InHouse(Model.getInstance().returnPartID(), inputName, inputPrice, inputInv, inputMin, inputMax);
//                    Model.getInstance().getInventory().updatePart(Model.getInstance().getPartIndexToBeModified(), newPart);
//                    alert.close();
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
//                    root = loader.load();
//                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//                    scene = new Scene(root);
//                    //stage.setTitle("Hello!");
//                    stage.setScene(scene);
//                    stage.show();
//
//                } else if (alert.getResult() == ButtonType.NO) {
//                    alert.close();
//                }
//
//            } else if (OutsourcedRadioButton.isSelected()) {
//                String confirmationMsg = "Are you sure you would like to modify this part?";
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, confirmationMsg, ButtonType.YES, ButtonType.NO);
//                alert.showAndWait();
//
//                if (alert.getResult() == ButtonType.YES) {
//                    Outsourced newPart = new Outsourced(Model.getInstance().returnPartID(), inputName, inputPrice, inputInv, inputMin, inputMax, inputMachineID);
//                    Model.getInstance().getInventory().updatePart(Model.getInstance().getPartIndexToBeModified(), newPart);
//                    alert.close();
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
//                    root = loader.load();
//                    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//                    scene = new Scene(root);
//                    //stage.setTitle("Hello!");
//                    stage.setScene(scene);
//                    stage.show();
//
//                }
//
//            }
//
//
//        }
//
//        catch(NumberFormatException nfe){
//            AlertMethod("The input " + nfe.getMessage() + "was the wrong data type.");
//
//
//        }
//        catch(Exception wrongType){
//            AlertMethod("Something went wrong" + wrongType);
//        }
//    }


    public void CancelButton(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//
//    public void ListenRadio(ActionEvent ev) {
//        if (InhouseRadioButton.isSelected()) {
//            RadioTextChange.setText("Machine ID");
//        } else if (OutsourcedRadioButton.isSelected()) {
//            RadioTextChange.setText("Company Name");
//        }
//    }

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

