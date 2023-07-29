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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {
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
        System.out.println("Initialized addanyproduct");
        int predeterminedID = Model.getInstance().returnProductID();
        IDField.setText(String.valueOf(predeterminedID));

        System.out.println("Initialized home");
        AllPartsPartIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        AllPartsNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        AllPartsInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        AllPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        AddAssociatedPartTableview.setItems(Model.getInstance().getInventory().getAllParts());

        AssociatedPartsPartIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        AssociatedPartsPartNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        AssociatedPartsInvColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        AssociatedPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

    public void SaveButton(ActionEvent actionEvent) {


    }

    /**
     * @param event is when the button is clicked.
     *              Returns to home page.
     */
    public void CancelButton(ActionEvent event) throws IOException { //returns to home page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void AddAllPartsSearch(KeyEvent keyEvent) {
    }

    public void AddAssociatedPartsSearch(KeyEvent keyEvent) {
//        PartsTableSearchMessage.setText("");
//        try {
//
//            int intPart = Integer.parseInt(PartsTableviewSearchHome.getText());
//
//            throw null;
////
////            if(howManyDigits(intPart)<4) {
//
////                ObservableList<Part> newPartsList = FXCollections.observableArrayList();
////                newPartsList.add(Model.getInstance().inventory.lookupPart(intPart));
////
////                PartsTableSearchMessage.setText("");
////                if (newPartsList.get(0).getId() == null){
////                    PartsTableSearchMessage.setText("No results found!");
////                }
////                PartsTableviewHome.setItems(newPartsList);
////            }
////            else{
////                PartsTableSearchMessage.setText("ID is too long, did not find");
////            }
//
//
////            System.out.println(PartsTableviewHome.getItems().toArray().length);
////            if(PartsTableviewHome.getItems().){
////                System.out.println("NEWPARTLIST IS NULL!");
////            }
////            else{System.out.println("NEWPARTLIST ISnt NULL!"); }
//
//        } catch (NullPointerException e) {
//            int intPart = Integer.parseInt(PartsTableviewSearchHome.getText());
//            ObservableList<Part> newPartsList = FXCollections.observableArrayList();
//            newPartsList.add(Model.getInstance().inventory.lookupPart(intPart));
//
//            PartsTableSearchMessage.setText("");
//            if (newPartsList.get(0) == null) {
//                PartsTableSearchMessage.setText("No results found!");
//            }
//            PartsTableviewHome.setItems(newPartsList);
//        } catch (Exception E) {
//            System.out.println("It's probably a string");
//            String partsText = PartsTableviewSearchHome.getText();
//            ObservableList<Part> newPartsList = Model.getInstance().inventory.lookupPart(partsText);
//            if (newPartsList.isEmpty()) {
//                PartsTableSearchMessage.setText("No results found!");
//            } else {
//                PartsTableSearchMessage.setText("");
//            }
//            PartsTableviewHome.setItems(newPartsList);
//        }
//    }
    }
}
