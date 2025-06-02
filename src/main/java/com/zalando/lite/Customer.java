package com.zalando.lite;

import java.util.List;

/**
 * Represents a customer in the ZalandoLite system.
 *
 * A customer can place orders, leave reviews, and receive discounts if marked as a VIP.
 * This class includes fields for identity, email, VIP status, and shopping preferences.
 *
 * Will be used with annotations (@VIP) and linked to orders and reviews.
 *
 * Concepts reinforced:
 * - Class design & encapsulation
 * - Lists (favorite categories)
 * - Custom annotations
 * - Reflection (via @VIP)
 */
public class Customer {

    // Unique ID to identify the customer
    private int id;

    // Customer's full name
    private String name;

    // Email address (used for contact or login simulation)
    private String email;

    // Whether the customer has VIP status (used for discounts)
    // This will be marked with @VIP annotation
    private boolean isVip;

    // A list of the customer's favorite product categories (e.g., "Shoes", "Accessories")
    private List<String> favoriteCategories;

    /**
     * Constructor to initialize a Customer.
     * Helps with registration and setup of test data.
     **/
    public Customer() {

    }

    // Returns the customer's ID
    public int getId() {
        return this.id;
    }

    // Sets the customer's ID
    public void setId(int id) {
        this.id = id;
    }

    // Returns the name of the customer
    public String getName() {
        return this.name;
    }

    // Sets the customer's name
    public void setName(String name) {
        this.name = name;
    }

    // Returns the customer's email address
    public String getEmail() {
        return this.email;
    }

    // Sets the customer's email
    public void setEmail(String email) {
        this.email = email;
    }

    // Returns whether the customer is a VIP
    public boolean isVip() {
        return this.isVip;
    }

    // Sets VIP status (should match the @VIP field annotation logic)
    public void setVip(boolean vip) {
        this.isVip = vip;
    }

    // Returns the list of favorite categories
    public List<String> getFavoriteCategories() {
        return this.favoriteCategories;
    }

    // Sets the list of favorite categories
    public void setFavoriteCategories(List<String> categories) {
        this.favoriteCategories = categories;
    }

    /**
     * Returns a printable summary of the customer's details.
     *
     * Useful for debugging, console menus, and reporting.
     */
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isVip=" + isVip +
                ", favoriteCategories=" + favoriteCategories +
                '}';
    }
}
