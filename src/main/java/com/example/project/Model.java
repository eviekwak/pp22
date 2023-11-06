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

public class Model {

    private static Model globalControls;
    Inventory inventory = new Inventory();
    int incrementingPartID = 1000;
    int incrementingProductID = 1000;

    int partIndexToBeModified;
    int productIndexToBeModified;

    /**
     *
     * @return the index of the part to be modified
     */
    public int getPartIndexToBeModified (){
        return this.partIndexToBeModified;
    }

    /**Sets the index of the part to be modified.
     * @param setPartIndex
     */
    public void setPartIndexToBeModified(int setPartIndex) {
        this.partIndexToBeModified = setPartIndex;
    }

    /**
     *Gets the index of a product to be modified.
     * @return an int/index.
     */
    public int getProductIndexToBeModified (){
        return this.productIndexToBeModified;
    }

    /**
     *Sets the index of a product to be modified.
     * @param setProductIndex The new product index.
     */
    public void setProductIndexToBeModified(int setProductIndex) {
        this.productIndexToBeModified = setProductIndex;
    }

    /**
     *Decrements the part ID
     * @return part ID
     */
    public int returnPartID(){
        incrementingPartID--;
        return incrementingPartID;
    } //returns Part ID

    /**Decrements the product ID
     * Returns Product ID
     * @return
    */

    public int returnProductID(){
        incrementingProductID--;
        return incrementingProductID;
    }
    private Model(){

    }

    /**
     *Gets the instance of the singleton object Model.
     * @return new Model.
     */
    public static Model getInstance(){
        if(globalControls == null) {
            globalControls = new Model();
        }
        return globalControls;
    }

    /**
     * returns Inventory.
     * @return inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * sets Inventory with @param newInv
     */
    public void setInventory(Inventory newInv){
        inventory = newInv;
    }


}
