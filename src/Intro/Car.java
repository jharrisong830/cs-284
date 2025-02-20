
public class Car implements Colorable {
	private double miles;
	private String color;
	
	private static final double CONVERSION = 1.609;
	
	public Car(double x, String c) {
		miles = x; 
		color = c;
	}
	
	public double getOdometerMiles() {
		return miles;
	}
		
	public static double km2Miles(double km) {
		return km/CONVERSION;
	}
		
	public void setColor(String c) {
		color = c;
	}
		
	public String getColor() {
		return color;
	}

}
