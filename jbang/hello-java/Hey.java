public class Hey extends org.apache.camel.builder.RouteBuilder {

  @Override
  public void configure() throws Exception {
      // Write your routes here, for example:
      from("timer:java?period=1000")
        .routeId("java")
        .process(e -> {
           e.getMessage().setBody("Hello from Camel");
        })
        .log("${body}");
  }
}
