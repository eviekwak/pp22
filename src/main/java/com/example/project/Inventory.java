package com.example.project;
import com.example.project.Part;
import com.example.project.Product;
import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    public static Part lookupPart(int partId){
        for (Part part : allParts){
            if (part.getId() == partId){
                return part;
            }
        }
        return null;
    }
    public Product lookupProduct(int partId){
        for (Product product : allProducts){
            if (product.getId() == partId){
                return product;
            }
        }
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

    }
    public ObservableList<Part> getAllParts(){

    }
    public ObservableList<Product> getAllProducts(){

    }
}



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
