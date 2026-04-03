
package com.mycompany.cashier;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List <Product> products = new ArrayList<>();
    private Repository<Product> repo ;

    public Inventory(Repository<Product> repo) {
        this.repo = repo;
         this.products = repo.load();
    }
    
    
    
    
    public void addProduct(Product product){
        if(product == null)throw new IllegalArgumentException("Product is null");
        if(searchProduct(product.getId())!= null )throw new IllegalArgumentException("Product is null");
        products.add(product);
        repo.save(products);
        System.out.println("Added Product is Successfully ");
    }
    
    public Product searchProduct(int id){
        for(Product p : products){
            if(p.getId() == id)return p;
        }
        return null;
    }
    
    public void removeProduct(int id){
        Product p = searchProduct(id);
        products.remove(p);
        repo.save(products);
        System.out.println("Remove Product is Successfully ");
    }
    
    public void showProduct(){
        if(products.isEmpty())System.out.println("Product is empty");
        for(Product p : products){
            System.out.println(p);
        }
    }

    public List<Product> getProducts() {
        return products;
    }
    
    
}
