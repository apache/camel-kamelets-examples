// camel-k: language=java

import org.apache.camel.builder.RouteBuilder;

public class myapp extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:java")
            .setVariable("global:app", simple("${camelId}"))
            .setVariable("global:random", simple("${random(1,10)}"));

        from("timer:random?period={{time:1000}}")
            .setBody(simple("${variable.global:greeting}: ${variable.global:random}"))
            .log("${body}");

    }
}
