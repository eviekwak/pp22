package com.example.project;
import com.example.project.Part;
import com.example.project.Product;
import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Part> allParts, allProducts;

    public void addPart(Part newPart) {

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
   /* public ObservableList<Part> getAllParts(){

    }
    */

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