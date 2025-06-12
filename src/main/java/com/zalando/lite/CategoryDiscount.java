package com.zalando.lite;

public class CategoryDiscount extends Discount{


    @Override
    public double applyDiscount(Customer customer, Product product, double currentPrice) {

        String category = product.getCategory();
        if(category != null && "Shoes".equalsIgnoreCase(category)) {
            return currentPrice * 0.80; // 20% off on shoes
        }
        return currentPrice;
    }
}
