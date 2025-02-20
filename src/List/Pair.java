
public class Pair<E,F> {
	
	private E first;
	private F second;
	
	public Pair(E frst, F sec) {
		this.first=frst;
		this.second=sec;
	}
	
	public E getFirst() {
		return first;
	}
	
	public F getSecond() {
		return second;
	}
}
