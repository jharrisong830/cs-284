//John Graham
//I pledge my honor that I have abided by the Stevens Honor System.

public class MultiplayerGame {

	private GameEntity turnTracker;
	private GameEntity[] index;

	//Basic Operations
	
	/**Constructor for the MultiplayerGame object.
	 * @param n number of players*/
	public MultiplayerGame(int n) { //FINISHED
		if(n<1) {
			throw new IllegalArgumentException("MultiplayerGame: cannot have less than 1 player");
		}
		
		index=new GameEntity[n];
		for(int i=0; i<n; i++) {
			index[i]=new GamePlayer(null, null, i);
			if(i>0) {
				index[i].prev=index[i-1];
				index[i-1].next=index[i];
			}
		}
		index[0].prev=index[n-1];
		index[n-1].next=index[0];
	}
	
	
	/**Computes the size of the MultiplayerGame (the number of all pieces in play).
	 * @return int, number of all pieces currently in play (does not include players)*/
	public int size() {
		int n=0;
		GameEntity firstEntity=index[0];
		GameEntity currentEntity=firstEntity.next;
		while(!currentEntity.equals(firstEntity)) {
			if(!currentEntity.isGamePlayer()) {
				n++;
			}
			currentEntity=currentEntity.next;
		}
		return n;
	}
	
	
	/**Adds a piece to the game.
	 * @param playerId, the player who will own the new piece
	 * @param name, the name of the piece
	 * @param strength, the strength of the piece*/
	public void addGamePiece(int playerId, String name, int strength) {
		if(playerId>index.length-1 || playerId<0) {
			throw new IllegalArgumentException("addGamePiece: no such player");
		}
		GameEntity currentEntity=index[playerId];
		currentEntity=currentEntity.next;
		while(!currentEntity.isGamePlayer()) {
			if(currentEntity.getName().equals(name)) {
				throw new IllegalArgumentException("addGamePiece: duplicate entity");
			}
			currentEntity=currentEntity.next;
		}
		
		currentEntity=index[playerId];
		currentEntity.next=new GamePiece(currentEntity, currentEntity.next, name, strength);
		currentEntity.next.next.prev=currentEntity.next;
	}
	
	
	/**Removes a piece from the game.
	 * @param playerId, the player from whom the piece will be removed
	 * @param name, the name of the piece*/
	public void removeGamePiece(int playerId, String name) {
		if(playerId>index.length-1 || playerId<0) {
			throw new IllegalArgumentException("removeGamePiece: no such player");
		}
		
		GameEntity currentEntity=index[playerId];
		currentEntity=currentEntity.next;
		while(!currentEntity.getName().equals(name)) {
			if(currentEntity.isGamePlayer()) {
				throw new IllegalArgumentException("removeGamePiece: entity does not exist");
			}
			currentEntity=currentEntity.next;
		}
		currentEntity.prev.next=currentEntity.next;
		currentEntity.next.prev=currentEntity.prev;
	}
	
	
	/**Checks if a certain piece is currently in play (can be owned by any player).
	 * @param name, the name of the piece
	 * @return boolean, true or false depending on if the piece is in the game*/
	public boolean hasGamePiece(String name) {
		GameEntity firstEntity=index[0];
		GameEntity currentEntity=firstEntity.next;
		while(!currentEntity.equals(firstEntity)) {
			if(currentEntity.getName().equals(name)) {
				return true;
			}
			currentEntity=currentEntity.next;
		}
		return false;
	}
	
	
	/**Removes all of the pieces owned by a certain player.
	 * @param playerId, the player whose pieces will be removed*/
	public void removeAllGamePieces(int playerId) {
		if(playerId>index.length-1 || playerId<0) {
			throw new IllegalArgumentException("removeAllGamePieces: no such player");
		}
		
		GameEntity currentEntity=index[playerId];
		if(playerId==index.length-1) {
			currentEntity.next=index[0];
		}
		else {
			currentEntity.next=index[playerId+1];
		}
	}
	
	
	/**Increases the strength of all of a players pieces.
	 * @param playerId, the player whose pieces' strengths will be increased
	 * @param n, the number by which the pieces' strengths will be increased*/
	public void increaseStrength(int playerId, int n) {
		if(playerId>index.length-1 || playerId<0) {
			throw new IllegalArgumentException("increaseStrength: no such player");
		}
		
		GameEntity currentEntity=index[playerId];
		currentEntity=currentEntity.next;
		while(!currentEntity.isGamePlayer()) {
			((GamePiece) currentEntity).updateStrength(n);
			currentEntity=currentEntity.next;
		}
	}
	
	
	/**Creates a string representation of the MultiplayerGame.
	 * @return String, a string representation of the MultiplayerGame*/
	public String toString() {
		StringBuilder sb=new StringBuilder();
		GameEntity firstEntity=index[0];
		sb.append(firstEntity.toString()+"\n");
		GameEntity currentEntity=firstEntity.next;
		while(!currentEntity.equals(firstEntity)) {
			sb.append(currentEntity.toString()+"\n");
			currentEntity=currentEntity.next;
		}
		return sb.toString();
	}
	
	
	//Processing Turns

	/**Sets turnTracker to point towards the first player.*/
	public void initializeTurnTracker() {
		turnTracker=index[0];
	}
	
	
	/**Sets turnTracker to point towards the next player.*/
	public void nextPlayer() {
		turnTracker=turnTracker.next;
		while(!turnTracker.isGamePlayer()) {
			turnTracker=turnTracker.next;
		}
	}
	
	
	/**Sets turnTracker to point towards the next entity (can be a player or a piece).*/
	public void nextEntity() {
		turnTracker=turnTracker.next;
	}
	
	
	/**Sets turnTracker to point towards the previous player.*/
	public void prevPlayer() {
		turnTracker=turnTracker.prev;
		while(!turnTracker.isGamePlayer()) {
			turnTracker=turnTracker.prev;
		}
	}
	
	
	/**Creates a string representation of the current entity that turnTracker points to.
	 * @return String, a string representation of the current entity.*/
	public String currentEntityToString() {
		return turnTracker.toString();
	}







	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
