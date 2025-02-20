
public class Box {
	//data fields 
	private double height;
	private double width;
	private double depth; 
	
	private String color;
	
		
	//methods 
	public Box(double x, double y, double z, String c){
		height = x;
		width = y;
		depth = z; 
		color = c;
	}

	public void setHeight(double height) {
		this.height = height; 
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setWidth(double width) {
		this.width = width; 
	}
	
	public double getWidth() {
		return width;
	}
	
	public void setDepth(double depth) {
		this.depth = depth; 
	}
	
	public double getDepth() {
		return depth;
	}
	
	public void setColor(String color) {
		this.color = color; 
	}
	
	public String getColor() {
		return color;
	}
	
	public double getVolume() {
		return width*height*depth;
	}
	
	/** 
	 * Complete this method! Compares this box to Object o. Returns true if o is a Box and has
	 * the same width, height, depth and color as this box. Returns false otherwise. 
	 * See main method for examples and expected output. 
	 */
	public boolean equals(Object o) {
		if (this == o) { //self check 
			return true;
		}
		if (o == null) { //null check
			return false;
		}
		if (!(o instanceof Box)) { //type check
			return false;
		}
		if(this.width==((Box) o).width && this.height==((Box) o).height && 
				this.depth==((Box) o).depth && this.color.equals(((Box) o).color)) {
			return true;
		}
		else {
			return false;
		}
		//compare the fields of o to those of this box
		//return false; //update this!
	}
	
	/** 
	 * Complete this method! Compares this box to Object o. Returns true if o is a Box and has
	 * the same width, height, depth and color (excepting capitalization of the String) as this box.
	 * Returns false otherwise. 
	 * See main method for examples and expected output. 
	 */
	public boolean equalsIgnoreCase(Object o) {
		if (this == o) { //self check 
			return true;
		}
		if (o == null) { //null check 
			return false;
		}
		if (!(o instanceof Box)) { //type check 
			return false;
		}
		if(this.width==((Box) o).width && this.height==((Box) o).height && 
				this.depth==((Box) o).depth && this.color.equalsIgnoreCase(((Box) o).color)) {
			return true;
		}
		else {
			return false;
		}
		//compare the fields of o to those of this box
		//return false; //update this!
	}
	
	
	public static void main(String[] args) {
		Box b1 = new Box(9.5, 3, 7, "Blue");
		Box b2 = new Box(9.5, 3, 7, "Blue");
		Box b3 = new Box(9.5, 3, 7, "blue");

		
		System.out.println(b1.equals(b2)); //should print true 
		System.out.println(b1.equals(b3)); //should print false 

		
		System.out.println(b1.equalsIgnoreCase(b3)); //should print true 
	}
}
