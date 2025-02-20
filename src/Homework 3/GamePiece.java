
public class GamePiece extends GameEntity{
	
	private String name;
	private int strength;
	
	public GamePiece(GameEntity prev, GameEntity next, String name, int strength) {
		this.prev = prev; 
		this.next = next; 
		this.name = name;
		this.strength = strength; 
	}
	
	
	public boolean isGamePlayer() {
		return false;
	}
	
	public int size() {
		return 1; 
	}
	
	public int getStrength() {
		return strength;
	}
	
	public void updateStrength(int n) {
		strength += n;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return "GamePiece: " + getName() + " strength: " + getStrength();
	}

}
