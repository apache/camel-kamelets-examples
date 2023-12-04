//DEPS org.apache.camel:camel-bom:4.3.0-SNAPSHOT@pom
//DEPS org.apache.camel:camel-endpointdsl
//DEPS org.apache.camel:camel-netty-http
//DEPS org.apache.camel:camel-stream

// add more dependencies here

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.component.netty.http.NettyHttpMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class foo extends EndpointRouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(foo.class);

    @Override
    public void configure() {
        from(timer("trigger").period(5000).repeatCount(3))
                .to(nettyHttp("https://random-data-api.com/api/v2/banks").keepAlive(true))
                .process(e -> {
                    // use classes from camel-netty-http dependency in the source code
                    // and have jbang able to generate project with the dependencies ready
                    // to use in your IDE of choice
                    NettyHttpMessage msg = e.getMessage(NettyHttpMessage.class);
                    LOG.info("Netty HTTP response:\n\n\n{}\n\n\n", msg.getHttpResponse());
                })
                .log("Found bank:")
          .to(stream("out"));
    }
}