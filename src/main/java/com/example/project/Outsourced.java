package com.example.project;

public class Outsourced extends Part {

    private String companyName;

    /**
     *The constructor for the outsourced part
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**Sets the company name
     *
     * @param companyName
     */
    public void setCompanyName(String companyName) {this.companyName = companyName; } //sets companyName

    /**
     *
     * @return the company name
     */
    public String getCompanyName() {return companyName; } //gets companyName
}