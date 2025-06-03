package com.zalando.lite;

/**
 * Represents a courier who delivers customer orders.
 *
 * This class is part of the delivery system in ZalandoLite. Couriers are
 * assigned to orders based on their availability status. Each courier has a
 * vehicle and a toggleable state to simulate real-world assignment.
 *
 * Useful in logistics simulation and multithreading (optional bonus).
 *
 * Concepts reinforced:
 * - POJO modeling
 * - Encapsulation
 * - State toggling with booleans
 */
public class Courier {

    // Unique identifier for the courier
    private int id;

    // Full name of the courier
    private String name;

    // Type of vehicle (e.g., Bike, Van, Drone)
    private String vehicleType;

    // True if courier is available for assignment
    private boolean available;

    /**
     * Constructor to initialize a Courier.
     * Typically used when creating a list of couriers at app startup.
     */
    public Courier(int id, String name, String vehicleType, boolean available) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
        this.available = available;
    }

    // Returns the courier ID
    public int getId() {
        return id;
    }

    // Sets the courier ID
    public void setId(int id) {
        this.id = id;
    }

    // Returns the name of the courier
    public String getName() {
        return name;
    }

    // Sets the courier's name
    public void setName(String name) {
        this.name = name;
    }

    // Returns the type of vehicle used for delivery
    public String getVehicleType() {
        return vehicleType;
    }

    // Sets the vehicle type (used for logistics filtering)
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    // Returns true if courier is available for delivery
    public boolean isAvailable() {
        return available;
    }

    // Updates the courier's availability status
    public void setAvailable(boolean available) {
        this.available = available;
    }


    /**
     * Toggles the courier's availability.
     *
     * Used to simulate assignment (available → busy) or completion (busy → available).
     * A simple, clean way to change internal boolean state.
     */
    public void toggleAvailability() {
        this.available = !this.available;
    }

    /**
     * Returns a formatted string of courier details.
     * Useful for logging or menu displays.
     */
    @Override
    public String toString() {
        return "Courier ID: " + id +
                ", Name: " + name +
                ", Vehicle: " + vehicleType +
                ", Available: " + available;
    }

}
