// camel-k: language=java

import org.apache.camel.builder.RouteBuilder;

public class myapp extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:java")
            .setVariable("global:app", simple("${camelId}"))
            .setVariable("route:second:random.number", simple("${random(1,10)}"));

        from("timer:random?period={{time:1000}}").routeId("second")
            .setBody(simple("${variable.global:greeting}: ${variable.route:random.number}"))
            .log("${body}");

    }
}
