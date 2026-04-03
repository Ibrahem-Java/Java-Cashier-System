package com.mycompany.cashier;

import java.util.List;
import java.util.Scanner;

public class Cashier {

    public static void main(String[] args) {

        JsonRepository<Product> productRepo = new JsonRepository<>("data/products.json", Product[].class);
        JsonRepository<CartItem> cartRepo = new JsonRepository<>("data/cart.json", CartItem[].class);

        Inventory inventory = new Inventory(productRepo);
        Cart cart = new Cart(cartRepo);

        Scanner input = new Scanner(System.in);
        boolean flag = true;

        System.out.println("====== Welcome to Cashier System ======");

        while (flag) {
            System.out.println("\nMenu:");
            System.out.println("1. Show Inventory");
            System.out.println("2. Add Product to Inventory");
            System.out.println("3. Remove Product from Inventory");
            System.out.println("4. Show Cart");
            System.out.println("5. Add Product to Cart");
            System.out.println("6. Remove Product from Cart");
            System.out.println("7. Checkout & Print Invoice");
            System.out.println("0. Exit");

            int choice = -1;
            while (true) {
                System.out.print("Choose option: ");
                String in = input.nextLine();
                try {
                    choice = Integer.parseInt(in);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number.");
                }
            }

            try {
                switch (choice) {
                    case 1 -> inventory.showProduct();
                    case 2 -> {
                        System.out.print("Product Name: ");
                        String name = input.nextLine();
                        System.out.print("Price: ");
                        double price = input.nextDouble();
                        System.out.print("Quantity: ");
                        int qty = input.nextInt();
                        input.nextLine();
                        inventory.addProduct(new Product(name, price, qty));
                    }
                    case 3 -> {
                        System.out.print("Product ID to remove: ");
                        int id = input.nextInt();
                        input.nextLine();
                        inventory.removeProduct(id);
                    }
                    case 4 -> cart.showProducts();
                    case 5 -> {
                        System.out.print("Product ID to add to cart: ");
                        int id = input.nextInt();
                        System.out.print("Quantity: ");
                        int qty = input.nextInt();
                        input.nextLine();
                        Product p = inventory.searchProduct(id);
                        if (p != null) cart.addCart(p, qty);
                        else System.out.println("Product not found in inventory.");
                    }
                    case 6 -> {
                        System.out.print("Product ID to remove from cart: ");
                        int id = input.nextInt();
                        input.nextLine();
                        cart.removeProduct(id);
                    }
                    case 7 -> {
                        List<CartItem> items = cart.getItems();
                        if (items.isEmpty()) {
                            System.out.println("Cart is empty. Nothing to checkout.");
                        } else {
                            Invoice invoice = new Invoice(items);
                            invoice.printData();
                            cart.getItems().clear();
                            cartRepo.save(cart.getItems());
                        }
                    }
                    case 0 -> {
                        flag = false;
                        System.out.println("Exiting... Goodbye!");
                    }
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        input.close();
    }
}