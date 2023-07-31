package com.example.project;
import com.example.project.Part;
import com.example.project.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * The constructor for the Inventory Object.
     */
    public Inventory() {
        this.allParts = allParts;
    }

    /**
     *adds a new part at index 0
     * @param newPart
     */
    public void addPart(Part newPart) { //adds a new part at index 0

        allParts.add(0, newPart);
    }

    /**
     *adds a new product at index 0
     * @param newProduct
     */
    public void addProduct(Product newProduct) { //adds a new product at index 0
        allProducts.add(0, newProduct);
    }

    /**
     *Looks up parts using an int.
     * @param partId
     * @return lookedupPartId up to a single product that matches the int
     */
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

    /**
     *Looks up parts using a string.
     * @param partName
     * @return newPartsList a list containing all the parts which contain @param partName
     */
    public ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> newPartsList = FXCollections.observableArrayList();

        for (Part searchedPart : getAllParts()){
            if (searchedPart.getName().toLowerCase().contains(partName.toLowerCase())){

                newPartsList.add(searchedPart);
            }

        }
        return newPartsList;
    }

    /**
     *Looks up products using an int.
     * @param productId
     * @return lookedup ProductId up to a single product that matches the int
     */
    public Product lookupProduct(int productId) {

        Product lookedupProductId = null;
        for (Product searchedProduct : getAllProducts()){
            if (searchedProduct.getId() == productId){


                lookedupProductId = searchedProduct;
                return lookedupProductId;
            }

        }

        return lookedupProductId;
    }

    /**
     *Looks up products using a string.
     * @param productName
     * @return newProductsList a list containing all the products which contain @param productName
     */
    public ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> newProductsList = FXCollections.observableArrayList();

        for (Product searchedProduct : getAllProducts()){
            if (searchedProduct.getName().toLowerCase().contains(productName.toLowerCase())){

                newProductsList.add(searchedProduct);
            }

        }
        return newProductsList;

    }

    /**
     *Updates the part with modifications
     * @param index
     * @param selectedPart
     */
        public void updatePart ( int index, Part selectedPart){ //updates the part with modifications

            this.allParts.set(index, selectedPart);
        }

    /**
     *Updates the product with modifications
     * @param index
     * @param newProduct
     */
    public void updateProduct ( int index, Product newProduct){ //updates the product with modifications

            this.allProducts.set(index, newProduct);
        }

    /**
     *Deletes the part
     * @param selectedPart
     * @return
     */
    public boolean deletePart (Part selectedPart){ //deletes the specified part
            this.allParts.remove(selectedPart);
            return true;
        }

    /**Deletes the product
     *
     * @param selectedProduct
     * @return
     */
        public boolean deleteProduct (Product selectedProduct){ //deletes the specified product

            this.allProducts.remove(selectedProduct);
            return true;
        }

    /**
     *
     * @return getAllProducts list
     */
        public ObservableList<Product> getAllProducts () { //returns the ObservableList allProducts

            return this.allProducts;
        }

    /**
     *
     * @return getAllParts list
     */
        public ObservableList<Part> getAllParts () {//returns the ObservableList allParts

            return this.allParts;
        }

}
