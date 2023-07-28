package com.example.project;
import com.example.project.Part;
import com.example.project.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public Inventory() {
        this.allParts = allParts;
    }

    public void addPart(Part newPart) { //adds a new part at index 0

        allParts.add(0, newPart);
    }

    public void addProduct(Product newProduct) { //adds a new product at index 0
        allProducts.add(0, newProduct);
    }

    public Part lookupPart(int partId) {
        
        Part lookedupPartId = null;
        for (Part searchedPart : getAllParts()){
            if (searchedPart.getId() == partId){

                lookedupPartId = searchedPart;
                return lookedupPartId;
            }

        }
        return lookedupPartId;
    }

    public ObservableList<Part> lookupPart(String partName) {

//allParts.indexOf()
        ObservableList<Part> newPartsList = FXCollections.observableArrayList();

        for (Part searchedPart : getAllParts()){
            if (searchedPart.getName().toLowerCase().contains(partName.toLowerCase())){


                newPartsList.add(searchedPart);
            }

        }
        return newPartsList;
    }

    public Product lookupProduct(int partId) {

        return null;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return null;

    }
        public void updatePart ( int index, Part selectedPart){ //updates the part with modifications

            this.allParts.set(index, selectedPart);
        }
        public void updateProduct ( int index, Product newProduct){ //updates the product with modifications

            this.allProducts.set(index, newProduct);
        }
        public boolean deletePart (Part selectedPart){ //deletes the specified part
            this.allParts.remove(selectedPart);
            return true;
        }


        public boolean deleteProduct (Product selectedProduct){ //deletes the specified product

            this.allProducts.remove(selectedProduct);
            return true;
        }

        public ObservableList<Product> getAllProducts () { //returns the ObservableList allProducts

            return this.allProducts;
        }

        public ObservableList<Part> getAllParts () {//returns the ObservableList allParts

            return this.allParts;
        }

}
