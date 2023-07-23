package com.example.project;

public class InHouse extends Part {

    private int machineId;


    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    public void setMachineId(int machineId) {this.machineId = machineId; } //sets machineID
    public int getMachineId() {return machineId; } //gets machineID
}