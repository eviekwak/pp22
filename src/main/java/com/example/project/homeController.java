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
    public TableColumn<Part, String> PartNameColumnHome, PartPriceColumnHome;

    @FXML
    private TextField PartsTableviewSearchHome, ProductsTableviewSearchHome;

    @FXML
    private TableView<Part> PartsTableviewHome;
    @FXML
    private TableColumn<Part, Integer> PartIDColumnHome, PartInventLvlColumnHome;

    @FXML
    public TableColumn<Product, String> ProductNameColumnHome, ProductPriceColumnHome;
    @FXML
    private TableView<Product> ProductsTableviewHome;
    @FXML
    private Text PartsTableSearchMessage, ProductsTableSearchMessage;
    @FXML
    private TableColumn<Product, Integer> ProductIDColumnHome, ProductInventLvlColumnHome;


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
//        addAnyPartController.IHNameField.setText("dd");

    }

    public void ModifyPartsButtonHome(ActionEvent actionEvent) throws IOException {
        Model.getInstance().setPartIndexToBeModified(
                Model.getInstance().inventory.getAllParts().indexOf(
                        PartsTableviewHome.getSelectionModel().getSelectedItem()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyAnyPart.fxml"));
        root = loader.load();
        ModifyPartController modifyPartController = loader.getController();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }


    public void DeletePartsButtonHome(ActionEvent actionEvent) {
        try {
            String confirmationMsg = "Are you sure you would like to delete this part?";
            Part partt = PartsTableviewHome.getSelectionModel().getSelectedItem();
            if(partt != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,confirmationMsg, ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    Model.getInstance().inventory.deletePart(partt);
                    alert.close();
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }

            }

        }
        catch (Exception Confirm){
            AlertMethod("Are you sure you would like to delete this part?");
        }
    }

    public void AddProductsButtonHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        root = loader.load();
        AddProductController addProductController = loader.getController();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");

        stage.setScene(scene);
        stage.show();
    }

    public void ModifyProductsButtonHome(ActionEvent actionEvent) throws IOException {
        Model.getInstance().setProductIndexToBeModified(
                Model.getInstance().inventory.getAllProducts().indexOf(
                        ProductsTableviewHome.getSelectionModel().getSelectedItem()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
        root = loader.load();
        ModifyProductController modifyProductController = loader.getController();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");

        stage.setScene(scene);
        stage.show();
    }

    public void DeleteProductsButtonHome(ActionEvent actionEvent) {
        try {
        String confirmationMsg = "Are you sure you would like to delete this part?";
        Product producto = ProductsTableviewHome.getSelectionModel().getSelectedItem();
        if(producto != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,confirmationMsg, ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                Model.getInstance().inventory.deleteProduct(producto);
                alert.close();
            } else if (alert.getResult() == ButtonType.NO) {
                alert.close();
            }

        }

    }
    catch (Exception Confirm){
        AlertMethod("Are you sure you would like to delete this part?");
    }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized home");
        PartIDColumnHome.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        PartNameColumnHome.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartInventLvlColumnHome.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        PartPriceColumnHome.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartsTableviewHome.setItems(Model.getInstance().getInventory().getAllParts());

        ProductIDColumnHome.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        ProductNameColumnHome.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        ProductInventLvlColumnHome.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        ProductPriceColumnHome.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProductsTableviewHome.setItems(Model.getInstance().getInventory().getAllProducts());

    }

    public void AlertMethod(String x){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, x, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.NO) {
            alert.close();
        }
        else if (alert.getResult() == ButtonType.YES) {
            alert.close();
            }
        }
    public void ExitButton(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void PartsTableViewSearch(KeyEvent keyEvent) throws IOException {

        PartsTableSearchMessage.setText("");
        try{

            int intPart = Integer.parseInt(PartsTableviewSearchHome.getText());

            throw null;
//
//            if(howManyDigits(intPart)<4) {

//                ObservableList<Part> newPartsList = FXCollections.observableArrayList();
//                newPartsList.add(Model.getInstance().inventory.lookupPart(intPart));
//
//                PartsTableSearchMessage.setText("");
//                if (newPartsList.get(0).getId() == null){
//                    PartsTableSearchMessage.setText("No results found!");
//                }
//                PartsTableviewHome.setItems(newPartsList);
//            }
//            else{
//                PartsTableSearchMessage.setText("ID is too long, did not find");
//            }


//            System.out.println(PartsTableviewHome.getItems().toArray().length);
//            if(PartsTableviewHome.getItems().){
//                System.out.println("NEWPARTLIST IS NULL!");
//            }
//            else{System.out.println("NEWPARTLIST ISnt NULL!"); }

        }
        catch(NullPointerException e){
            int intPart = Integer.parseInt(PartsTableviewSearchHome.getText());
            ObservableList<Part> newPartsList = FXCollections.observableArrayList();
            newPartsList.add(Model.getInstance().inventory.lookupPart(intPart));

            PartsTableSearchMessage.setText("");
            if (newPartsList.get(0) == null){
                PartsTableSearchMessage.setText("No results found!");
            }
            PartsTableviewHome.setItems(newPartsList);
        }
        catch(Exception E){
            System.out.println("It's probably a string");
            String partsText = PartsTableviewSearchHome.getText();
            ObservableList<Part> newPartsList = Model.getInstance().inventory.lookupPart(partsText);
            if (newPartsList.isEmpty()){
                PartsTableSearchMessage.setText("No results found!");}
            else{
                PartsTableSearchMessage.setText("");}
            PartsTableviewHome.setItems(newPartsList);
        }
        }

    public void ProductsTableViewSearch(KeyEvent keyEvent) throws IOException {

        ProductsTableSearchMessage.setText("");
        try{

            int intProduct = Integer.parseInt(ProductsTableviewSearchHome.getText());

            throw null;

        }
        catch(NullPointerException e){
            int intProduct = Integer.parseInt(ProductsTableviewSearchHome.getText());
            ObservableList<Product> newProductsList = FXCollections.observableArrayList();
            newProductsList.add(Model.getInstance().inventory.lookupProduct(intProduct));

            ProductsTableSearchMessage.setText("");
            if (newProductsList.get(0) == null){
                ProductsTableSearchMessage.setText("No results found!");
            }
            ProductsTableviewHome.setItems(newProductsList);
        }
        catch(Exception E){
            String productsText = ProductsTableviewSearchHome.getText();
            ObservableList<Product> newProductsList = Model.getInstance().inventory.lookupProduct(productsText);
            if (newProductsList.isEmpty()){
                ProductsTableSearchMessage.setText("No results found!");}
            else{
                ProductsTableSearchMessage.setText("");}
            ProductsTableviewHome.setItems(newProductsList);
        }
    }


    int howManyDigits(int x) {
        int digits = 0;
        while(x!=0) {
            x=x/10;
            digits++;
        }
        return digits;
    }
    }
