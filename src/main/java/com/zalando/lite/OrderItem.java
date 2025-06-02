package com.zalando.lite;

/**
 * Represents a single line item in an {@link Order}.
 *
 * Each OrderItem connects a specific {@link Product} with the quantity purchased.
 * This class helps calculate the total price for the order and allows the system
 * to break down orders into granular, trackable components.
 *
 * Concepts reinforced:
 * - Composition: combining two classes (Product + quantity)
 * - Data modeling: simple container object
 */
public class OrderItem {

    // The product that was ordered
    private Product product;

    // Quantity of the product ordered
    private int quantity;

    /**
     * Constructor to create an OrderItem.
     *
     * Typically used by the OrderManager when processing a shopping cart.
     */
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Returns the product associated with this item
    public Product getProduct() {
        return this.product;
    }

    // Sets the product for this item
    public void setProduct(Product product) {
        this.product = product;
    }

    // Returns the quantity ordered
    public int getQuantity() {
        return this.quantity;
    }

    // Sets the quantity ordered
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the subtotal (product price × quantity).
     *
     * Useful for summing up totals in Order class.
     *
     * @return total cost of this line item
     */
    public double getSubtotal() {
        if (product == null) {
            throw new IllegalStateException("OrderItem must have a valid Product.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        return product.getPrice() * quantity;
    }

    /**
     * Returns a string summary of this order item.
     */
    @Override
    public String toString() {
        return product.getName() + " x " + quantity + " = " + getSubtotal();
    }
}
