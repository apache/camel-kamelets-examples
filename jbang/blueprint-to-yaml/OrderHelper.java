package com.foo;

import com.foo.Address;

public class OrderHelper {

    public static OrderService newOrderService(boolean customer, Address address) {
    	OrderService order = new OrderService();
        order.setCustomer(customer ? "Acme" : "None");
        order.setAddress(address);
        return order;
    }

}