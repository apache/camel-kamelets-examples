package com.foo;

public class OrderService {

   private String customer;

   public void setCustomer(String customer) {
       this.customer = customer;
   }

   public String getCustomer() {
   	   return customer;
   }

   public String processorOrder(String id) {
       return "Order " + id + " ordered by " + customer;
   }

}