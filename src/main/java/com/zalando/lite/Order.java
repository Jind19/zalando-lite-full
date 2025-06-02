package com.zalando.lite;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a customer's order in the ZalandoLite system.
 *
 * Each order includes:
 * - A unique order ID
 * - A reference to the customer who placed the order
 * - A list of line items (products and their quantities)
 * - A timestamp of when the order was placed
 *
 * This class is central to the business logic and ties together customers,
 * products, and delivery.
 *
 * Concepts reinforced:
 * - Object composition
 * - Use of lists to store related data
 * - Timestamps using LocalDateTime
 */
public class Order {

    // Unique identifier for the order
    private int orderId;

    // The customer who placed the order (reference, not a copy)
    private Customer customer;

    // A list of items included in the order
    private List<OrderItem> items;

    // The date and time the order was created
    private LocalDateTime orderDate;

    /**
     * Constructor to initialize an order with a customer and list of items.
     *
     * Automatically sets the order date to the current timestamp.
     */
    public Order(int orderId, Customer customer, List<OrderItem> items) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.orderDate = LocalDateTime.now();
    }


    // Returns the order ID
    public int getOrderId() {
        return this.orderId;
    }

    // Sets the order ID (maybe used when generating orders manually)
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Returns the customer who placed the order
    public Customer getCustomer() {
        return this.customer;
    }

    // Sets the customer for the order
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Returns the list of order items
    public List<OrderItem> getItems() {
        return this.items;
    }

    // Sets the list of order items
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    // Returns the timestamp of when the order was placed
    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    // Sets the order timestamp (usually auto-generated)
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Calculates the total cost of the order by summing
     * (product price × quantity) for each line item.
     *
     * @return the total order cost
     */
    public double calculateTotal() {
        double subTotal = 0.0;
        for(OrderItem item : items){
            subTotal += item.getSubtotal() ; // Add each item's subtotal to total
        }
        return subTotal;
    }

    @Override
    public String toString() {
        return "Order#" + orderId + " by " + customer.getName() +
                " on " + orderDate.toLocalDate() +
                " | Total: €" + calculateTotal();
    }

}
