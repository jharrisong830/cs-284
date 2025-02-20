
public class Circle extends Shape{
	//data fields 
	private double radius; 
	
	//methods
	public Circle(double r, String c) {
		super(c);
		radius = r; 
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}

	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(3, 2, "Blue");
		System.out.println(r1.getHeight());
		//The below does not compile. Why?
		//System.out.println(r1.height);
	}

	@Override
	public double area() {
		//Pi is a constant from Math class
		return Math.PI * (radius * radius);
	}

}
