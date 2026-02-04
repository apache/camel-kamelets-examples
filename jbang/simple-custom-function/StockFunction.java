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
		return true;
	}

	@Override
	public Object apply(Exchange exchange, Object input) throws Exception {
		return 200 + new Random().nextInt(1000);
	}

}