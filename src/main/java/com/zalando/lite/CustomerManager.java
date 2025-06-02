package com.zalando.lite;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the registration and lookup of customers in the ZalandoLite system.
 *
 * This class acts as a "service layer" that stores customer data, retrieves it efficiently,
 * and ensures that the application can quickly access user information by ID.
 *
 * Internally uses a Map for fast lookup and future extensibility (like updates or filtering).
 *
 * Concepts reinforced:
 * - Maps (for ID → object relationships)
 * - Null safety
 * - Separation of concerns (manager vs model)
 */
public class CustomerManager {

    // Stores customers using their ID as the key for quick access
    private Map<Integer, Customer> customers;

    //Constructor to initialize the customer manager.
    public CustomerManager() {
        customers = new HashMap<>();
    }

    /**
     * Registers a new customer in the system.
     * If the ID already exists, the existing customer is overwritten.
     *
     * @param customer the customer to add
     */
    public void registerCustomer(Customer customer) {
        if(customer != null){
            customers.put(customer.getId(), customer);
        }
    }

    /**
     * Retrieves a customer by their unique ID.
     *
     * @param id the customer’s ID
     * @return the matching Customer, or null if not found
     */
    public Customer getCustomerById(int id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            System.out.println("Customer with ID " + id + " not found.");
        }
        return customer;
    }

    /**
     * Optional: Returns all registered customers.
     * Can be used for listing or reporting.
     *
     * @return map of all customers
     */
    public Map<Integer, Customer> getAllCustomers() {
        return new HashMap<>(customers);  //a copy to prevent modification of the original customers map from outside the class
    }
}
