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
        for(Part onePart : modifiedProduct.getAllAssociatedParts())
        {
            AssociatedPartsList.add(onePart);
        }



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


    /**
     *Grabs the text from the input fields, including the associated parts and saves the product. Redirects user to home page once complete.  Throws error alerts if min is more than max and vice verse, or if the data types entered are wrong.
     * @param event
     * @throws IOException
     */
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            //stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();


        } catch (NumberFormatException nfe) {
            AlertMethod("The input " + nfe.getMessage() + "was the wrong data type.");
        }
        catch (Exception wrongType) {
            AlertMethod("Something went wrong " + wrongType);
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
        stage.setScene(scene);
        stage.show();
    }

    /**Pops up an alert with a message and an OK button
     * @param x
     */
    public void AlertMethod(String x) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, x, ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    /**Pops up an alert with a message, a YES button and a NO button
     *
     * @param x
     */
    public void AlertMethodYes(String x) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, x, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            alert.close();
        }
    }

    /**
     * Adds the associated part selected from the top table to the bottom table and the temporary list for the product being created.
     * @param actionEvent
     * @throws IOException
     */
    public void AddAssociatedPartButton(ActionEvent actionEvent) throws IOException {
        try{
        if (AddAssociatedPartTableview.getSelectionModel().getSelectedItem().getId() == null) {
                throw new Exception();
        }

                Part selectedPart = AddAssociatedPartTableview.getSelectionModel().getSelectedItem();
                AssociatedPartsList.add(selectedPart);
                RemoveAssociatedPartTableview.setItems(AssociatedPartsList);
        }
        catch(Exception e){
            AlertMethod("You must select a part first.");
        }
        }

    /**
     *Removes the selected associated part after yes is selected from the alert. Cancels if no is selected. Sends error if no associated part is selected.
     * @param actionEvent
     */
    public void RemoveAssociatedPartButton(ActionEvent actionEvent) {
        try {
            if (RemoveAssociatedPartTableview.getSelectionModel().getSelectedItem().getId() == null) {
                throw new Exception();
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to remove this associated part?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                Part selectedPart = RemoveAssociatedPartTableview.getSelectionModel().getSelectedItem();
                AssociatedPartsList.remove(selectedPart);
                RemoveAssociatedPartTableview.setItems(AssociatedPartsList);
                alert.close();
            } else if (alert.getResult() == ButtonType.NO) {
                alert.close();
            }
        }
        catch (Exception e){
            AlertMethod("You must select an associated part first.");
        }
    }

    /**
     * Closes the program.
     * @param event
     * @throws IOException
     */
    public void ExitButton(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     *Grabs text from the search bar and displays matching results(all parts).  Sets GUI text accordingly.
     * @param keyEvent
     */
    public void AddAllPartsSearch(KeyEvent keyEvent) {
        AllPartsSearchMessage.setText("");
        try {

            int intPart = Integer.parseInt(AddAssociatedPartSearch.getText());

            throw null;
        } catch (NullPointerException e) {
            int intPart = Integer.parseInt(AddAssociatedPartSearch.getText());
            ObservableList<Part> newPartsList = FXCollections.observableArrayList();
            newPartsList.add(Model.getInstance().inventory.lookupPart(intPart));


            AllPartsSearchMessage.setText("");
            if (newPartsList.get(0) == null) {
                AllPartsSearchMessage.setText("No results found!");
            }
            AddAssociatedPartTableview.setItems(newPartsList);


        } catch (Exception E) {
            System.out.println("It's probably a string");
            String partsText = AddAssociatedPartSearch.getText();
            ObservableList<Part> newPartsList = Model.getInstance().inventory.lookupPart(partsText);
            if (newPartsList.isEmpty()) {
                AllPartsSearchMessage.setText("No results found!");
            } else {
                AllPartsSearchMessage.setText("");
            }
            AddAssociatedPartTableview.setItems(newPartsList);
        }
    }

    /**Grabs text from the search bar and displays matching results(associated parts).  Sets GUI text accordingly.
     *
     * @param keyEvent
     */
    public void RemoveAssociatedPartsSearch(KeyEvent keyEvent) {
        AssociatedPartsSearchMessage.setText("");
        int intPart = 0;
        try {

            intPart = Integer.parseInt(RemoveAssociatedPartSearch.getText());

            throw null;
        } catch (NullPointerException e) {

            ObservableList<Part> matchingAllPartsList = FXCollections.observableArrayList();
            ObservableList<Part> onlyInRemovePartsList = FXCollections.observableArrayList();

            matchingAllPartsList.add(Model.getInstance().inventory.lookupPart(intPart));


            if (AssociatedPartsList.contains(matchingAllPartsList.get(0))){
                System.out.println("match");
                onlyInRemovePartsList.add(matchingAllPartsList.get(0));
            }

            if (onlyInRemovePartsList.isEmpty()) {
                AssociatedPartsSearchMessage.setText("No results found!");
            } else {
                AssociatedPartsSearchMessage.setText("");
            }
            RemoveAssociatedPartTableview.setItems(onlyInRemovePartsList);
        } catch (Exception E) {
            System.out.println("It's probably a string");

            String partsText = RemoveAssociatedPartSearch.getText();
            System.out.println(partsText);
            ObservableList<Part> matchingAllPartsList = Model.getInstance().inventory.lookupPart(partsText);
            ObservableList<Part> onlyInRemovePartsList = FXCollections.observableArrayList();

            for(Part allPartsMatch : matchingAllPartsList){
                if(AssociatedPartsList.contains(allPartsMatch)){
                    onlyInRemovePartsList.add(allPartsMatch);
                }
            }
            if (onlyInRemovePartsList.isEmpty()) {
                AssociatedPartsSearchMessage.setText("No results found!");
            } else {
                AssociatedPartsSearchMessage.setText("");
            }
            System.out.println("before end");
            RemoveAssociatedPartTableview.setItems(onlyInRemovePartsList);
        }
    }

}

