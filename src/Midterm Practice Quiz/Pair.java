
public class Pair<E, F> {
	
	private E first;
	private F second;
	
	public Pair(E first, F second) {
		this.first = first;
		this.second = second;
	}
	
	public void setFirst(E first) {
		this.first = first;
	}
	
	public E getFirst() {
		return first;
	}
	
	public void setSecond(F second) {
		this.second = second;
	}
	
	public F getSecond() {
		return this.second;
	}
	
	@Override 
	public String toString() {
		return "(" + first.toString() + "," + second.toString() + ")";
	}

}

