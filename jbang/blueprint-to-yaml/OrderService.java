package com.foo;

import com.foo.Address;

public class OrderService {

   private String customer;
   private Address address;

   public OrderService(boolean customer, Address address) {
       this.customer = customer ? "Acme" : "None";
       this.address = address;
   }

   public void setCustomer(String customer) {
       this.customer = customer;
   }

   public String getCustomer() {
   	   return customer;
   }

   public void setAddress(Address address) {
       this.address = address;
   }

   public Address getAddress() {
       return address;
   }   

   public String processorOrder(String id) {
       return "Order2 " + id + " ordered by " + customer + " to " + address.getStreet();
   }

}