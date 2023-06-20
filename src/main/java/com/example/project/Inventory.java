package com.example.project;
import com.example.project.Part;
import com.example.project.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Part> allParts =  FXCollections.observableArrayList();
    private ObservableList<Product> allProducts =  FXCollections.observableArrayList();

    public Inventory() {
        this.allParts =  allParts;
    }

    public void addPart(Part newPart) {
        allParts.add(0, newPart);
    }
    public void addProduct(Product newProduct) {

    }
    public Part lookupPart(int partId){

        return null;
    }
    public Product lookupProduct(int partId){

        return null;
    }
    public void updatePart(int index, Part selectedPart){

    }
    public void updateProduct(int index, Product newProduct){

    }
    public boolean deletePart(Part selectedPart){
        return true;
    }
    public boolean deleteProduct(Product selectedProduct){

        //change this later
        return true;
    }

    public ObservableList<Product> getAllProducts() {
        return this.allProducts;
    }
public ObservableList<Part> getAllParts(){
        return this.allParts;
    }


//    public ObservableList<Product> getAllProducts(){
//
//    }
}


/*
    addPart(newPart:Part):void
        + addProduct(newProduct:Product):void
        + lookupPart(partId:int):Part
        + lookupProduct(productId:int):Product
        + lookupPart(partName:String):ObservableList<Part>
+ lookupProduct(productName:String):ObservableList<Product>
+ updatePart(index:int, selectedPart:Part):void
        + updateProduct(index:int, newProduct:Product):void
        + deletePart(selectedPart:Part):boolean
        + deleteProduct(selectedProduct:Product):boolean
        + getAllParts():ObservableList<Part>
+ getAllProducts():ObservableList<Product>

 */