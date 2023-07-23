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

    public int getPartIndexToBeModified (){
        return this.partIndexToBeModified;
    }
    public void setPartIndexToBeModified(int setPartIndex) {
        this.partIndexToBeModified = setPartIndex;
    }
    public int getProductIndexToBeModified (){
        return this.productIndexToBeModified;
    }
    public void setProductIndexToBeModified(int setProductIndex) {
        this.productIndexToBeModified = setProductIndex;
    }

    public int returnPartID(){
        incrementingPartID--;
        return incrementingPartID;
    } //returns Part ID

    public int returnProductID(){
        incrementingProductID--;
        return incrementingProductID;
    } //returns Product ID
    private Model(){

    }
    public static Model getInstance(){
        if(globalControls == null) {
            globalControls = new Model();
        }

        // returns the singleton object
        return globalControls;
    }
    public Inventory getInventory() {
        return inventory;
    } //returns Inventory
    public void setInventory(Inventory newInv){
        inventory = newInv;
    } //sets Inventory


}
