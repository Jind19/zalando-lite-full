package com.zalando.lite;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the store's inventory by storing and modifying products.
 *
 * This class provides functionality to:
 * - Add products to the inventory
 * - Retrieve a product by its ID
 * - List all products
 * - Update product stock when purchases are made
 *
 * It acts as a middle layer between the product data and other services
 * such as order creation or delivery processing.
 *
 * Concepts reinforced:
 * - Collection handling
 * - Looping and search logic
 * - Data mutation (stock updates)
 */
public class InventoryManager {

    // Stores all products currently available in the inventory
    private List<Product> products;

    // Constructor to initialize the product list
    public InventoryManager() {
        this.products  = new ArrayList<>();
    }

    /**
     * Adds a product to the inventory list.
     *
     * This simulates product registration or stock restocking.
     *
     * @param product the product to add to the inventory
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Finds a product using its ID.
     *
     * Performs a linear search through the list to locate the product.
     *
     * @param id the ID of the product to find
     * @return the matching product, or null if not found
     */
    public Product findProductById(int id) {
        for(Product product : products){
            if(product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    /**
     * Lists all products in the inventory.
     *
     * Useful for browsing, displaying, or admin use cases.
     *
     * @return list of all products
     */
    public List<Product> listAllProducts() {
        return new ArrayList<>(products);    //returning a copy of list of products
    }

    /**
     * Reduces the stock of a product after a purchase.
     *
     * Ensures that stock does not fall below zero.
     *
     * @param productId ID of the product to reduce
     * @param quantity amount to subtract
     * @return true if successful, false if insufficient stock or not found
     */
    public boolean reduceStock(int productId, int quantity) {
        Product product = findProductById(productId);

        if (!isProductAvailable(productId)) {
            return false; // Product doesn't exist or has zero stock
        }

        if (product.getStock() >= quantity) {
            product.setStock(product.getStock() - quantity); // Reduce stock
            return true;
        } else {
            return false; // Not enough stock
        }
    }

    /**
     * Optional: Check if product exists and is in stock.
     *
     * @param productId the ID to check
     * @return true if product exists and has stock
     */
    public boolean isProductAvailable(int productId) {
        Product product = findProductById(productId);
        return (product != null && product.getStock() > 0);
    }
}
