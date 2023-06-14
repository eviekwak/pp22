package com.example.project;

public class Outsourced extends Part {

    private string companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, string companyName) {
        super(id, name, price, stock, min, max);
    }

    public void setCompanyName(string companyName) {this.companyName = companyName; }
    public string getCompanyName() {return companyName; }
}