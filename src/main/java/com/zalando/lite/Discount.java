package com.zalando.lite;

public abstract class Discount {

    /**
     * Applies discount and returns discounted price.
     */
    public abstract double applyDiscount(Customer customer, Product product, double currentPrice);


}
