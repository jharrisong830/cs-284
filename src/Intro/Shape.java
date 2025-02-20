
public abstract class Shape implements Colorable {
	//data field 
	private String color;
	private static final String DEFAULT_COLOR = "Red";
	
	//methods 
	public Shape(String c) {
		color = c;
	}
	
	public Shape() {
		this(DEFAULT_COLOR);
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	public String toString() {
		return "I am a Shape. Color : " + color;
	}
	
	public abstract double area();
	
}
