package com.zalando.lite;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the assignment of couriers to orders and manages delivery statuses.
 *
 * This service class is responsible for:
 * - Finding available couriers
 * - Creating and returning Delivery objects
 * - Updating the delivery status as the order progresses
 *
 * It simulates a basic logistics workflow and supports logic that could later
 * be extended to support parallel delivery or tracking features.
 *
 * Concepts reinforced:
 * - Search & filtering logic
 * - Business rules
 * - Control flow and state transitions
 */
public class DeliveryService {

    // List of all couriers available to the system
    private List<Courier> couriers;

    public DeliveryService() {
        this.couriers = new ArrayList<>();
    }

    /**
     * Attempts to assign an available courier to the given order.
     *
     * If a courier is found, a Delivery is created and returned.
     * If no couriers are available, returns null or throws an exception (based on design choice).
     *
     * @param order the order that needs to be delivered
     * @return the resulting Delivery object, or null if no couriers available
     */
    public Delivery assignCourier(Order order) {
        for (Courier courier : couriers) {
            if (courier.isAvailable()) {
                courier.setAvailable(false); // mark as busy
                return new Delivery(order, courier);
            }
        }
        return null; // No available courier
    }

    /**
     * Updates the status of an existing delivery.
     *
     * Could be "Pending" → "In Transit" → "Delivered"
     *
     * @param delivery the delivery object to update
     * @param newStatus the new status string
     */
    public void updateDeliveryStatus(Delivery delivery, String newStatus) {
        if (delivery == null) {
            throw new IllegalArgumentException("Delivery cannot be null.");
        }
        if (newStatus == null || newStatus.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty.");
        }
        delivery.setStatus(newStatus);
    }

    /**
     * Optional helper method to get all available couriers.
     *
     * Useful for debugging or CLI menus.
     *
     * @return a list of currently available couriers
     */
    public List<Courier> getAvailableCouriers() {
        List<Courier> available = new ArrayList<>();
        for(Courier courier : couriers) {
            if(courier.isAvailable()){
                available.add(courier);
            }
        }
        return available;
    }
}
