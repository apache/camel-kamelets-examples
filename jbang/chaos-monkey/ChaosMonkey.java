@org.apache.camel.BindToRegistry("chaosMonkeyCheck")
public class ChaosMonkey extends org.apache.camel.impl.health.AbstractHealthCheck {

    private int num;

    public ChaosMonkey() {
        super("acme", "chaos");
        System.out.println("Installing Chaos Monkey");
    }

    public String status(String body) {
        num = new java.util.Random().nextInt(10);
         if (num < 4) {
            return "Chaos Moneky is here";
        } else {
            return body;    
        }
    }

    @Override
    public void doCall(org.apache.camel.health.HealthCheckResultBuilder builder, java.util.Map<String, Object> options) {
        if (num < 4) {
            builder.detail("chaos-number", num).message("Chaos is here!!!").down();
        } else {
            builder.detail("chaos-number", num).up();    
        }
    }


}
