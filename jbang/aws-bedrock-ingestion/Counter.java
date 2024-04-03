import org.apache.camel.BindToRegistry;

@BindToRegistry("counter")
public class Counter {

    private int count;

    public int getCount() {
        return ++count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}
