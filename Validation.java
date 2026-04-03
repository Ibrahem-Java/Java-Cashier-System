package com.mycompany.cashier;
public class Validation {
     public static void validatName(String name){
        for(int i = 0 ; i < name.length() ; i++){
            char n = name.charAt(i);
            
            if(!(n >= 'a' && n <= 'z' || n >= 'A' && n <= 'Z') ){
                throw new IllegalArgumentException("Name must contain letters only.");
            }
        }
    }
    public static void validPrice(double price){
        if(price < 0){
            throw new IllegalArgumentException("The price cannot be negative.");
        }if(price == 0 ){
            throw new IllegalArgumentException("The price must be greater than zero.");
        }
    }
    public static void validQuantity(int quantity){
        if(quantity < 0){
            throw new IllegalArgumentException("The Quantity cannot be negative.");
        }if(quantity == 0 ){
            throw new IllegalArgumentException("The Quantity must be greater than zero.");
        }
    }
}
