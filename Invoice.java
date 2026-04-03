
package com.mycompany.cashier;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Invoice implements Total {
    private static int count = 1;
    private int Invoiceid;
    private LocalDateTime date;
    private List <CartItem> items ;

    public Invoice(List<CartItem> items) {
        this.Invoiceid = count++;
        this.date =LocalDateTime.now();
        this.items = new ArrayList<>();
        for(CartItem ci : items){
            Product p = ci.getProduct();
            Product copyProduct = new Product(p.getName(), p.getPrice(), p.getQuantity());
            this.items.add(new CartItem(copyProduct, ci.getQuantity()));
        }
    }
    
    @Override
    public double calculateTotal(){
        double total = 0 ;
        for(CartItem ci : items){
            total += ci.getProduct().getPrice() * ci.getQuantity();
        }
        return total;
    }
    public void printData(){
        System.out.println("Invoice ID: " + Invoiceid);
        System.out.println("Date: " + date);
        for(CartItem ci : items){
        double  itemstotal = ci.getProduct().getPrice() * ci.getQuantity();
       System.out.printf("%s | Items Total: %.2f\n", ci, ci.getProduct().getPrice() * ci.getQuantity());
        }
        System.out.println("Total: " + calculateTotal());
        
    }
}
