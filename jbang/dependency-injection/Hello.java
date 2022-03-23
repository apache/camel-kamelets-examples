// camel-k: language=java

import org.apache.camel.builder.RouteBuilder;

public class Hello extends RouteBuilder {

  @Override
  public void configure() throws Exception {

      // Write your routes here, for example:
      from("timer:java?period=1000")
        .routeId("java")
        .process(e -> {
           e.getMessage().setBody("Jack");
        })
        .bean("myEcho", "echo")
        .bean("myLogger", "log(${body})");
  }
}
