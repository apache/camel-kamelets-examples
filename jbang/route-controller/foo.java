import org.apache.camel.builder.RouteBuilder;

public class foo extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:java?period={{time:5000}}").routeId("foo")
            .setBody()
                .simple("Hello Camel from ${routeId}")
            .log("${body}");

        from("direct:bar").routeId("bar")
            .to("kafka:cheese"); // fails as we have not configured kafka
    }

}