package com.mycompany;

import org.apache.camel.BindToRegistry;
import org.apache.camel.Exchange;
import org.apache.camel.spi.SimpleFunction;

import java.util.Random;

@BindToRegistry("stock-function")
public class StockFunction implements SimpleFunction {

	@Override
	public String getName() {
		return "stock";
	}

	@Override
	public boolean allowNull() {
		return true; // true = functions that does not accept any input parameter
	}

	@Override
	public Object apply(Exchange exchange, Object input) throws Exception {
		String name = input == null ? "SP500" : "" + input;
		return name + " at $" + (200 + new Random().nextInt(1000));
	}

}