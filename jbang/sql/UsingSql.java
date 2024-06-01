import org.apache.camel.builder.RouteBuilder;

public class UsingSql extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("sql:select * from users?repeatCount=1")
            .log("${body}");
    }
}
