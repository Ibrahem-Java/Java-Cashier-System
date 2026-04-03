

package com.mycompany.cashier;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Total{
    private List<CartItem> items =new ArrayList<>() ;
    private Repository<CartItem> repo;

    public Cart(Repository<CartItem> repo) {
        this.repo = repo;
        this.items = repo.load();
    }
    
    

    public void addCart(Product product , int quantity){
        if(product == null)System.out.println("Product is null");
        if(product.getQuantity() < quantity)throw new IllegalArgumentException("Quantity in Inventory Less Then Required Quantity ");
        for(CartItem ci : items){
            if(ci.getProduct().getId() == product.getId()){
                ci.setQuantity( ci.getQuantity() +  quantity);
                product.setQuantity( product.getQuantity() -   quantity);
                return;
            }
        }
        items.add(new CartItem(product ,quantity));
        product.setQuantity( product.getQuantity() - quantity);
        repo.save(items);
        System.out.println("Product Add To Cart is Successfully.");
    }
    
    public CartItem searchProduct(int id){
        for(CartItem ci : items){
            if(ci.getProduct().getId() == id){
                return ci;
            }
        }
        return null;
    }     
    
    public void removeProduct(int id){
        for(int i = 0 ; i < items.size() ; i++){
            CartItem ci = items.get(i);
            if(ci.getProduct().getId() == id){
                ci.getProduct().setQuantity(ci.getProduct().getQuantity() + ci.getQuantity() );
                items.remove(i);
                repo.save(items);
                System.out.println("Product removed from cart.");
                return;
            }
        }
    }
    
    public void showProducts(){
        if(items.isEmpty()){
            System.out.println("Carts is Empety.");
            return;
        }
        System.out.println("Your carts.");
        for(CartItem ci : items){
            System.out.println(ci);
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

    public List<CartItem> getItems() {
        return items;
    }
    
}
