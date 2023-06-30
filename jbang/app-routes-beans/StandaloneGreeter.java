package camel.example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.StringHelper;

public class StandaloneGreeter implements Processor {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        String msg = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(message + " " + StringHelper.after(msg, "I'm "));
    }

}
