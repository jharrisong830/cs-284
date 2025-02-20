
public interface Colorable {
		
	public String getColor();
	public void setColor(String c);
	
	public static void printColor(Colorable c) {
		System.out.println(c.getColor());
	}

}
