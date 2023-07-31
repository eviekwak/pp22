package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Evie Kwak
 */
public class Product {
    private ObservableList<Part> associatedParts  = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     *The constructor for the Product Object.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Gets ID
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets ID.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**Set the name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**Set the Price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**Set the Stock
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**Set the min
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *Adds the part to the associated parts list
     * @param part
     */
    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }

    /**
     *Removes param from associated parts list
     * @param selectedAssociatedPart
     * @return
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        this.associatedParts.remove(selectedAssociatedPart);
        return true;
    }

    /**
     *
     * @return all associated parts
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return this.associatedParts;
    }
}