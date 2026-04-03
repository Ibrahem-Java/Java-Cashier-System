
package com.mycompany.cashier;

import java.io.Serializable;

public class Product implements Serializable {
    private static int count = 1;
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.id = count++;
        Validation.validatName(name);
        this.name = name;
        Validation.validPrice(price);
        this.price = price;
        Validation.validQuantity(quantity);
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validation.validatName(name);
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        Validation.validPrice(price);
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        Validation.validQuantity(quantity);
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
    return "Name: " + name +
           " | Qty: " + quantity +
           " | Price: " + price;
    }
    
    
    
    
}
