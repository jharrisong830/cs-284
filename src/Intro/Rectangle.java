
public class Rectangle extends Shape {
	//data fields 
	private double width;
	private double height;
	
	private static int numOfRectangles = 0;
	
	//methods 
	public Rectangle(double x, double y, String c) {
		super(c);
		width = x;
		height = y;
		numOfRectangles++; 
	}

	/**
	 * This is an example of a Javadoc comment.
	 * @return double of area
	 */
	public double area() {
		return width*height;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
	
	public static int getNumOfRectangles() {
		return numOfRectangles;
	}
	
	public String toString() {
		return super.toString() + ". I am also a Rectangle. " +
				"Height: " + height + " Width: " + width;
	}
	
	public PairDoubleString getHeightColor() {
		return new PairDoubleString(height, getColor());
	}
	
	public Pair<Double, Double> getHeightWidth() {
		return new Pair<Double, Double>(height,width);
	}
	
		
	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(3.0, 4.0, "Yellow");
		Rectangle r2 = new Rectangle(1.5, 2.2, "Blue");
		Rectangle r3 = new Rectangle(1.2, 7.4, "Blue");

		r1.setHeight(5.0);
		System.out.println("Height is a private variable"
				+ ", but I'm in the same class so I can access it."+
				" The height of Rectangle r1 is " +
				r1.getHeight());
		
		double a = r1.area();
		System.out.println("The area of Rectangle r1 is " 
				+ a);
		
		int n = Rectangle.getNumOfRectangles();
		System.out.println("The number of Rectangles created is "
				+n);
		
		System.out.println("I can call methods from Shape. " +
				"Color: " + r1.getColor());
		System.out.println("I can override methods from Shape! " 
				+ r1.toString());
		
		/*polymorphism
		Now that shape is abstract, we cannot create a 
		new instance of it.
		Shape s1;
		s1 = new Shape("Purple");
		System.out.println(s1.toString());*/
		
		Shape s2; 
		s2 = new Rectangle(1, 2, "Orange");
		
		//Which toString method will be called?
		System.out.println(s2.toString());
		//System.out.println(s2.getHeight());
		
		// printColor can take any Colorable as an argument
		Colorable.printColor(r1);
		Colorable.printColor(s2);
		
		Car c1 = new Car(4000, "Gray");
		Colorable.printColor(c1);
		
		Circle circ = new Circle(4000, "Purple");
		Colorable.printColor(circ);

	
	}

}
