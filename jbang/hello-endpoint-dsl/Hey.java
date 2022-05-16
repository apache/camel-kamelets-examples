public class Hey extends org.apache.camel.builder.endpoint.EndpointRouteBuilder {

  @Override
  public void configure() throws Exception {
      // Write your routes here, for example:
      from(timer("java").period(1000))
        .setBody(constant("Hello World"))
        .log("${body}");
  }
}
