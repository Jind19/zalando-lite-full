package com.zalando.lite;

import com.zalando.lite.annotations.VIP;

import java.lang.reflect.Field;

public class VipDiscount extends Discount{

    @Override
    public double applyDiscount(Customer customer, Product product, double currentPrice) {
        if (isVip(customer)) {
            return currentPrice * 0.90; // 10% off
        }
        return currentPrice;
    }


    private boolean isVip(Customer customer) {
        for (Field field : customer.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(VIP.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(customer);
                    if (value instanceof Boolean && (Boolean) value) {
                        return true;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
