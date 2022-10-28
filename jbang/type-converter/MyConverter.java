import org.apache.camel.Converter;

@Converter
public class MyConverter {

    @Converter
    public static Dog catToDog(Cat cat) {
    	return new Dog(cat.getName());
    }  
}