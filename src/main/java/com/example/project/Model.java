package com.example.project;

public class Model {
    private static Model inv;
    Inventory inventory = new Inventory();
    int incrementingPartID = 1000;
    int incrementingProductID = 1000;

    public int returnPartID(){
        incrementingPartID--;
        return incrementingPartID;
    }
    public int returnProductID(){
        incrementingProductID--;
        return incrementingProductID;
    }
    private Model(){

    }
    public static Model getInstance(){
        if(inv == null) {
            inv = new Model();
        }

        // returns the singleton object
        return inv;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory newInv){
        inventory = newInv;
    }

}
