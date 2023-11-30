package com.mycompany;

public final class CustomerBuilder {

	private String name;
	private String street;
	private int zip;
	private boolean gold;

	public CustomerBuilder name(String name) {
		this.name = name;
		return this;
	}

	public CustomerBuilder street(String street) {
		this.street = street;
		return this;
	}

	public CustomerBuilder zip(int zip) {
		this.zip = zip;
		return this;
	}

	public CustomerBuilder gold(boolean gold) {
		this.gold = gold;
		return this;
	}	

	public Customer build() {
        	return new Customer(name, street, zip, gold);
	}	

}
