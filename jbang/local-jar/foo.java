// camel-k: language=java

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import org.apache.commons.lang3.SystemUtils;

public class foo extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Write your routes here, for example:
        from("timer:java?period={{time:1000}}").routeId("java")
            .process(new Processor() {

                @Override
                public void process(Exchange exchange) throws Exception {
                    // use code from local-jar (commons-lang)
                    String result = SystemUtils.getUserHome().toString();
                    exchange.getMessage().setBody(result);
                }               
            })
            .log("${body}");
    }
}
