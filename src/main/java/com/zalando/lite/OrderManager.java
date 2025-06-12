package com.zalando.lite;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles the creation, validation, and storage of orders in the ZalandoLite system.
 *
 * This manager:
 * - Accepts a list of items and a customer to create a new {@link Order}
 * - Checks inventory levels before processing the order
 * - Updates stock accordingly
 * - Stores and retrieves orders per customer
 *
 * Serves as the glue between the inventory and customer layers.
 *
 * Concepts reinforced:
 * - Aggregation and system coordination
 * - Control flow with validation
 * - Data structure usage (Map for customer orders)
 */
public class OrderManager {

    // Stores orders for each customer (keyed by customer ID)
    private Map<Integer, List<Order>> customerOrders = new HashMap<>();

    // Used to update inventory after order placement
    private InventoryManager inventoryManager;

    // Constructor to initialize with an InventoryManager
    public OrderManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    /**
     * Creates a new order for the given customer and list of order items.
     *
     * Validates product availability before allowing the order to proceed.
     *
     * @param customer the customer placing the order
     * @param items a list of OrderItem entries to be purchased
     * @return the created Order, or null if validation fails
     */
    public Order createOrder(Customer customer, List<OrderItem> items) {
        // Validate stock
        if (!validateStock(items)) {
            return null; // Return null if stock is insufficient
        }

        // Update inventory
        updateInventory(items);

        // Create new order
        Order order = new Order(customer, items);
        order.setOrderDate(LocalDateTime.now());

        // Store order in customerOrders map
        int customerId = customer.getId();
        customerOrders.putIfAbsent(customerId, new ArrayList<>());
        customerOrders.get(customerId).add(order);

        return order;
    }

    /**
     * Retrieves all orders placed by a specific customer.
     *
     * @param customerId the ID of the customer
     * @return a list of their orders, or an empty list if none exist
     */
    public List<Order> getOrdersForCustomer(int customerId) {
        return customerOrders.getOrDefault(customerId, new ArrayList<>());
    }

    /**
     * Optional helper: Validates item quantities before processing.
     *
     * @param items the list of items to validate
     * @return true if all items are in stock
     */
    private boolean validateStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            int requiredQuantity = item.getQuantity();
            if (!inventoryManager.isProductAvailable(product.getId()) ||
                    inventoryManager.findProductById(product.getId()).getStock() < requiredQuantity) {
                return false; // Stock insufficient or product not available
            }
        }
        return true; // All items have sufficient stock
    }

    /**
     * Optional helper: Updates inventory after successful order.
     *
     * @param items the list of items whose stock should be reduced
     */
    private void updateInventory(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            inventoryManager.reduceStock(product.getId(), quantity);
        }
    }

    /**
     * Calculates the total revenue from all orders.
     *
     * @return the total revenue as a double
     */
    public double getTotalRevenue() {
        double totalRevenue = 0.0;

        for (List<Order> orders : customerOrders.values()) {
            for (Order order : orders) {
                totalRevenue += order.calculateTotal(); // Add the total for each order
            }
        }

        return totalRevenue;
    }

    /**
     * Calculates the average order value across all customers.
     *
     * @return the average order value, or 0.0 if no orders exist
     */
    public double getAverageOrderValue() {
        int totalOrders = 0;

        for (List<Order> orders : customerOrders.values()) {
            totalOrders += orders.size();
        }

        if (totalOrders == 0) return 0.0;

        return getTotalRevenue() / totalOrders;
    }

    /**
     * Finds the order with the highest total price.
     *
     * @return the highest-value order, or null if no orders exist
     */
    public Order getHighestValueOrder() {
        Order highestOrder = null;           // Variable to store the current highest value order
        double highestValue = 0.0;           // Variable to track the highest value seen so far

        for (List<Order> orders : customerOrders.values()) {
            // Loop through the list of orders for each customer
            for (Order order : orders) {
                double total = order.calculateTotal();   // Calculate the total for this order

                // If this order's total is higher than the current highest, update the highest
                if (highestOrder == null || total > highestValue) {
                    highestOrder = order;
                    highestValue = total;
                }
            }
        }

        return highestOrder; // Return the order with the highest total value, or null if no orders exist
    }
}
