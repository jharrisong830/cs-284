
public abstract class GameEntity {
	protected GameEntity next; 
	protected GameEntity prev;
	
	public GameEntity() {
		
	}
	public abstract boolean isGamePlayer();
	public abstract int size();
	public abstract String getName();
	
}
