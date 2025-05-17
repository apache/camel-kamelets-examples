import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import org.apache.camel.spi.CamelLogger;

@Component("myEcho")
public class Echo {

  @Autowired
  private org.apache.camel.CamelContext context;

  @Value("greeting")
  private String prefix;

  @Bean
  public CamelLogger myLogger() {
    return new CamelLogger("myLogger");
  }

  public String echo(String echo) {
    return prefix + " " + echo + echo + "!! from " + context.getName();
  }


}
