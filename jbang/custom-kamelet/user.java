// camel-k: language=java

import org.apache.camel.builder.RouteBuilder;

public class user extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("kamelet:user-source")
            .log("${body}");
    }
}
