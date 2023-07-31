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

    /**
     * A FUTURE ENHANCEMENT TO EXTEND THE PROGRAM'S FUNCTIONALITY
     *  The enhancement would be considering the search function for all tables.
     *  Currently, when an integer is searched for, you must type the exact ID of a part/product to get results AND
     *  the GUI message next to the search bar says "No results found!" until the exact ID is searched for.
     *
     *  The enhancement could be that the GUI message displays a message like "a part ID contains search input" so that the
     *  * user understands that there are potential results or no potential results.
     *  * This can save the user time searching numbers, and give more overall awareness of the lists' contents to the user.
     *  */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    /**Opens the add part form
     * @param event
     * @throws IOException
     */
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

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");

        stage.setScene(scene);
        stage.show();
//        addAnyPartController.IHNameField.setText("dd");

    }

    /**Opens the modify parts form for a specific part when said part is selected from the tableview
     * @param actionEvent
     * @throws IOException
     */
    public void ModifyPartsButtonHome(ActionEvent actionEvent) throws IOException {
        try {
            if (PartsTableviewHome.getSelectionModel().getSelectedItem() == null) {
                throw new Exception();
            }
            Model.getInstance().setPartIndexToBeModified(
                    Model.getInstance().inventory.getAllParts().indexOf(
                            PartsTableviewHome.getSelectionModel().getSelectedItem()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyAnyPart.fxml"));
            root = loader.load();
            ModifyPartController modifyPartController = loader.getController();

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            AlertMethodOk("You must select a part first.");
        }
    }

    /**Deletes the selected part after yes is selected from the alert.  Cancels if no is selected.
     * @param actionEvent
     */
    public void DeletePartsButtonHome(ActionEvent actionEvent) {
        try {
            if (PartsTableviewHome.getSelectionModel().getSelectedItem() == null) {
                throw null;
            }
            String confirmationMsg = "Are you sure you would like to delete this part?";
            Part partt = PartsTableviewHome.getSelectionModel().getSelectedItem();
            if (partt != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, confirmationMsg, ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    Model.getInstance().inventory.deletePart(partt);
                    alert.close();
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }

            }

        } catch (NullPointerException e) {
            AlertMethodOk("You must select a part first.");
        } catch (Exception Confirm) {
            AlertMethod("Are you sure you would like to delete this part?");
        }

    }

    /**Opens the add products form
     * @param actionEvent
     * @throws IOException
     */
    public void AddProductsButtonHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        root = loader.load();
        AddProductController addProductController = loader.getController();

        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        //stage.setTitle("Hello!");

        stage.setScene(scene);
        stage.show();
    }

    /**Opens the modify products form for a specific product when said product is selected from the tableview
     * @param actionEvent
     * @throws IOException
     */
    public void ModifyProductsButtonHome(ActionEvent actionEvent) throws IOException {
        try {
            if (ProductsTableviewHome.getSelectionModel().getSelectedItem() == null) {
                throw new Exception();
            }
            Model.getInstance().setProductIndexToBeModified(
                    Model.getInstance().inventory.getAllProducts().indexOf(
                            ProductsTableviewHome.getSelectionModel().getSelectedItem()));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
            root = loader.load();
            ModifyProductController modifyProductController = loader.getController();

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            //stage.setTitle("Hello!");

            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            AlertMethodOk("You must select a product first.");
        }
    }

    /**Deletes the selected product after yes is selected from the alert.  Sends error message if there are parts associated with the selected product.  Cancels if no is selected.
     * @param actionEvent
     */
    public void DeleteProductsButtonHome(ActionEvent actionEvent) throws IOException{
        try {
            if (ProductsTableviewHome.getSelectionModel().getSelectedItem() == null) {
                throw null;
            }
            if (ProductsTableviewHome.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty() == false) {
                throw new Exception();
            }
                Product producto = ProductsTableviewHome.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to delete this product?", ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if(alert.getResult() == ButtonType.YES) {
                    Model.getInstance().inventory.deleteProduct(producto);
                    alert.close();
                } else if (alert.getResult() == ButtonType.NO) {
                    alert.close();
                }
        }
        catch (NullPointerException n) {
            AlertMethodOk("You must select a product first.");
        }
        catch (Exception e){
            AlertMethodOk("The selected product must not have any parts associated with it to be deleted.");
        }
    }

    /**Pops up an alert with a message, a yes and a no button
     * @param x a message for the alert
     */
    public void AlertMethod(String x) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, x, ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.NO) {
            alert.close();
        } else if (alert.getResult() == ButtonType.YES) {
            alert.close();
        }
    }

    /**Pops up an alert with a message and an ok button
     * @param x a message for the alert
     */
    public void AlertMethodOk(String x) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, x, ButtonType.OK);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    /**Exits application
     * @param event
     * @throws IOException
     */
    public void ExitButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**Grabs text from the search bar and displays matching results(all parts).  Sets GUI text accordingly.
     * @param keyEvent
     * @throws IOException
     */
    public void PartsTableViewSearch(KeyEvent keyEvent) throws IOException {

        PartsTableSearchMessage.setText("");
        try {

            int intPart = Integer.parseInt(PartsTableviewSearchHome.getText());

            throw null;
        } catch (NullPointerException e) {
            int intPart = Integer.parseInt(PartsTableviewSearchHome.getText());
            ObservableList<Part> newPartsList = FXCollections.observableArrayList();
            newPartsList.add(Model.getInstance().inventory.lookupPart(intPart));

            PartsTableSearchMessage.setText("");
            if (newPartsList.get(0) == null) {
                PartsTableSearchMessage.setText("No results found!");
            }
            PartsTableviewHome.setItems(newPartsList);
        } catch (Exception E) {
            System.out.println("It's probably a string");
            String partsText = PartsTableviewSearchHome.getText();
            ObservableList<Part> newPartsList = Model.getInstance().inventory.lookupPart(partsText);
            if (newPartsList.isEmpty()) {
                PartsTableSearchMessage.setText("No results found!");
            } else {
                PartsTableSearchMessage.setText("");
            }
            PartsTableviewHome.setItems(newPartsList);
        }
    }

    /**Grabs text from the search bar and displays matching results(products).  Sets GUI text accordingly.
     * @param keyEvent
     * @throws IOException
     */
    public void ProductsTableViewSearch(KeyEvent keyEvent) throws IOException {

        ProductsTableSearchMessage.setText("");
        try {

            int intProduct = Integer.parseInt(ProductsTableviewSearchHome.getText());

            throw null;

        } catch (NullPointerException e) {
            int intProduct = Integer.parseInt(ProductsTableviewSearchHome.getText());
            ObservableList<Product> newProductsList = FXCollections.observableArrayList();
            newProductsList.add(Model.getInstance().inventory.lookupProduct(intProduct));

            ProductsTableSearchMessage.setText("");
            if (newProductsList.get(0) == null) {
                ProductsTableSearchMessage.setText("No results found!");
            }
            ProductsTableviewHome.setItems(newProductsList);
        } catch (Exception E) {
            String productsText = ProductsTableviewSearchHome.getText();
            ObservableList<Product> newProductsList = Model.getInstance().inventory.lookupProduct(productsText);
            if (newProductsList.isEmpty()) {
                ProductsTableSearchMessage.setText("No results found!");
            } else {
                ProductsTableSearchMessage.setText("");
            }
            ProductsTableviewHome.setItems(newProductsList);
        }
    }
}

