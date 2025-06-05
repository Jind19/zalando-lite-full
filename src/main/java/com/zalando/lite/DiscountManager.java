package com.zalando.lite;

import com.zalando.lite.annotations.VIP;
import java.lang.reflect.Field;

/**
 * Applies discount logic for customers and products in the ZalandoLite system.
 *
 * This class uses Java Reflection to check if a customer qualifies for a discount.
 * It also supports category-specific discounts (like for "Shoes").
 *
 * Discounts:
 * - VIP customers: 10% off
 * - Products in "Shoes" category: 20% off
 *
 * Concepts reinforced:
 * - Reflection
 * - Conditional logic
 * - Method extraction & code reuse
 */
public class DiscountManager {

    /**
     * Applies applicable discounts based on customer VIP status and product category.
     * <p>
     * Reflection is used to detect the @VIP annotation on the Customer object.
     *
     * @param customer the customer making the purchase
     * @param product  the product being purchased
     * @return the final price after discount
     */
    public double applyDiscount(Customer customer, Product product) {
        double price = product.getPrice();

        if (isCategoryDiscounted(product)) {
            price *= 0.80; // 20% off for Shoes
        }

        if (isVipUsingReflection(customer)) {
            price *= 0.90; // Additional 10% off for VIPs
        }

        return price;
    }

    /**
     * Checks if a customer has a @VIP annotation.
     * <p>
     * This method uses reflection to access fields marked as VIP and check their values.
     *
     * @param customer the customer to inspect
     * @return true if VIP, false otherwise
     */
    private boolean isVipUsingReflection(Customer customer) {
        Class<?> clazz = customer.getClass(); // Get the Customer class

        for (Field field : clazz.getDeclaredFields()) { // Loop through all fields
            field.setAccessible(true); // Allow access to private fields

            if (field.isAnnotationPresent(VIP.class)) { // Check if @VIP is present
                try {
                    Object value = field.get(customer); // Get the value of the field
                    if (value instanceof Boolean && (Boolean) value) {
                        return true; // Found a true VIP flag
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return false; // No VIP annotation with true value found
    }

    /**
     * Checks if the product qualifies for a category-based discount.
     *
     * @param product the product to check
     * @return true if the category matches discount rules
     */
    private boolean isCategoryDiscounted(Product product) {
        String category = product.getCategory();
        return category != null && "Shoes".equalsIgnoreCase(category);
    }

}