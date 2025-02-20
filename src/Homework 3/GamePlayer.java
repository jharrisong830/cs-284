
public class GamePlayer extends GameEntity{
	
	private int playerId;
	
	public GamePlayer(GameEntity prev, GameEntity next, int playerId) {
		this.prev = prev; 
		this.next = next; 
		this.playerId = playerId; 
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public boolean isGamePlayer() {
		return true; 
	}
	
	public int size() {
		return 0; 
	}
	
	public String getName() {
		return "Player" + playerId; 
	}
	
	public String toString() {
		return getName();	
	}

}
