package camel.example;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BatchLog implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(BatchLog.class);

    @Override
    public void process(Exchange e) throws Exception {
        final List<?> exchanges = e.getMessage().getBody(List.class);

        // Ensure we are actually receiving what we are asking for
        if (exchanges == null || exchanges.isEmpty()) {
            return;
        }

        // The records from the batch are stored in a list of exchanges in the original exchange. To process, we iterate over that list
        for (Object obj : exchanges) {
            if (obj instanceof Exchange) {
                LOG.info("Processing exchange with body {}", ((Exchange)obj).getMessage().getBody(String.class));
            }
        }
    }

}
