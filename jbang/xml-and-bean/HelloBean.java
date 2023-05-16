import org.apache.camel.BindToRegistry;

@BindToRegistry("myBean")
public class HelloBean {

	public String hello() {
		return "Hello from Java bean";
	}
}
