///usr/bin/env jbang --quiet camel@apache/camel pipe "$0" "$@" ; exit $?
// camel-k: language=java

import org.apache.camel.builder.AggregationStrategies;
import org.apache.camel.builder.RouteBuilder;

public class to_csv extends RouteBuilder {

    @org.apache.camel.PropertyInject(value = "separator", defaultValue = ",")
    private String separator;

    @Override
    public void configure() throws Exception {
        // read from system:in
        from("stream:in")
            // aggregate all lines together using comma as separator
            .aggregate(constant("true")).completionTimeout(100)
              .aggregationStrategy(AggregationStrategies.string(separator))
              // append new line to mark end of line
              .setBody().simple("${body}\n")
              // write to system:out
              .to("stream:out");
    }
}
