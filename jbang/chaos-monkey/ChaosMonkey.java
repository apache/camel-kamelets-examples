package chaos;

import java.util.Map;
import java.util.Random;
import org.apache.camel.BindToRegistry;
import org.apache.camel.impl.health.AbstractHealthCheck;
import org.apache.camel.health.HealthCheckResultBuilder;

@BindToRegistry("chaosMonkeyCheck")
public class ChaosMonkey extends AbstractHealthCheck {

    private int num;

    public ChaosMonkey() {
        super("acme", "chaos");
        System.out.println("Installing Chaos Monkey");
    }

    public String status(String body) {
        num = new Random().nextInt(10);
         if (num < 4) {
            return "Chaos Moneky is here";
        } else {
            return body;    
        }
    }

    @Override
    public void doCall(HealthCheckResultBuilder builder, Map<String, Object> options) {
        if (num < 4) {
            builder.detail("chaos-number", num).message("Chaos is here!!!").down();
        } else {
            builder.detail("chaos-number", num).up();    
        }
    }


}
