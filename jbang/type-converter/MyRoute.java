// camel-k: language=java

import org.apache.camel.builder.RouteBuilder;

public class MyRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Write your routes here, for example:
        from("timer:cat2dog?period={{time:5000}}").routeId("cat2dog")
            .setBody(e -> new Cat("Snoopy"))
            .log("${body.say()}")
            .convertBodyTo(Dog.class)
            .log("${body.say()}");
    }
}
