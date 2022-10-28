public class Cat {

	private final String name;

	public Cat(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String say() {
		return name + " mjaus";
	}
}