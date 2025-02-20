import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MultiplayerGameTest {

	@Test
	void testMultiplayerGame() {
		boolean errorThrow=false;
		try {
			MultiplayerGame bestGameEver=new MultiplayerGame(0);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
		
		
		errorThrow=false;
		try {
			MultiplayerGame bestGameEver=new MultiplayerGame(-1);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
		
		
		errorThrow=false;
		try {
			MultiplayerGame bestGameEver=new MultiplayerGame(1);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertFalse(errorThrow);
	}
	
	@Test
	void testSize() {
		MultiplayerGame bestGameEver=new MultiplayerGame(5);
		
		assertTrue(bestGameEver.size()==0);
		
		bestGameEver.addGamePiece(0, "StrongestPiece", 999);
		bestGameEver.addGamePiece(4, "WeakestPiece", 1);
		bestGameEver.addGamePiece(0, "Mid", 500);
		assertTrue(bestGameEver.size()==3);
		
		bestGameEver.removeGamePiece(4, "WeakestPiece");
		assertTrue(bestGameEver.size()==2);
		
		bestGameEver.removeAllGamePieces(0);
		assertTrue(bestGameEver.size()==0);
	}
	
	@Test
	void testAddGamePiece() {
		boolean errorThrow=false;
		try {
			MultiplayerGame bestGameEver=new MultiplayerGame(5);
			bestGameEver.addGamePiece(-1, "NoPiece", 10);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
		
		
		errorThrow=false;
		try {
			MultiplayerGame bestGameEver=new MultiplayerGame(5);
			bestGameEver.addGamePiece(0, "NoPiece", 10);
			bestGameEver.addGamePiece(0, "NoPiece", 10);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
		
		
		MultiplayerGame bestGameEver=new MultiplayerGame(5);
		
		bestGameEver.addGamePiece(0, "OnePiece", 1);
		assertTrue(bestGameEver.size()==1);
		
		bestGameEver.addGamePiece(2, "TwoPiece", 2);
		bestGameEver.addGamePiece(2, "SecondPiece", 2);
		assertTrue(bestGameEver.size()==3);
	}
	
	@Test
	void testRemoveGamePiece() {
		MultiplayerGame bestGameEver=new MultiplayerGame(5);
		
		boolean errorThrow=false;
		try {
			bestGameEver.removeGamePiece(-1, "NonexistentPlayer");
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
		
		
		errorThrow=false;
		try {
			bestGameEver.removeGamePiece(0, "NonexistentPiece");
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
		
		
		bestGameEver.addGamePiece(0, "StrongestPiece", 999);
		bestGameEver.addGamePiece(4, "WeakestPiece", 1);
		bestGameEver.addGamePiece(0, "Mid", 500);
		
		bestGameEver.removeGamePiece(0, "StrongestPiece");
		assertTrue(bestGameEver.size()==2);
		
		bestGameEver.removeGamePiece(4, "WeakestPiece");
		bestGameEver.removeGamePiece(0, "Mid");
		assertTrue(bestGameEver.size()==0);
	}
	
	@Test
	void testHasGamePiece() {
		MultiplayerGame bestGameEver=new MultiplayerGame(5);
		
		bestGameEver.addGamePiece(0, "John", 18);
		bestGameEver.addGamePiece(0, "John Jr.", 9);
		bestGameEver.addGamePiece(1, "John the Third", 3);
		
		assertFalse(bestGameEver.hasGamePiece("John^4"));
		assertFalse(bestGameEver.hasGamePiece("Prof. Brennan"));
		
		assertTrue(bestGameEver.hasGamePiece("John"));
		assertTrue(bestGameEver.hasGamePiece("John Jr."));
		assertTrue(bestGameEver.hasGamePiece("John the Third"));
	}
	
	@Test
	void testRemoveAllGamePieces() {
		MultiplayerGame bestGameEver=new MultiplayerGame(5);
		
		boolean errorThrow=false;
		try {
			bestGameEver.removeAllGamePieces(6);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
		
		
		bestGameEver.addGamePiece(0, "null", 1);
		bestGameEver.addGamePiece(0, "nuller", 1);
		bestGameEver.addGamePiece(1, "nullest", 1);
		bestGameEver.addGamePiece(1, "nullified", 1);
		assertTrue(bestGameEver.size()==4);
		
		bestGameEver.removeAllGamePieces(1);
		assertTrue(bestGameEver.size()==2);
		
		bestGameEver.removeAllGamePieces(0);
		assertTrue(bestGameEver.size()==0);
	}
	
	@Test
	void testIncreaseStrength() {
		MultiplayerGame bestGameEver=new MultiplayerGame(5);
		
		boolean errorThrow=false;
		try {
			bestGameEver.increaseStrength(-1, 5);
		}
		catch(IllegalArgumentException e) {
			errorThrow=true;
		}
		assertTrue(errorThrow);
		
		
		String currentPiece=null;
		bestGameEver.addGamePiece(0, "Hi", 1);
		bestGameEver.addGamePiece(0, "Hello", 1);
		bestGameEver.addGamePiece(1, "Hey", 1);
		bestGameEver.addGamePiece(1, "Yo", 1);
		
		bestGameEver.increaseStrength(0, 9);
		bestGameEver.increaseStrength(1, -1);
		
		bestGameEver.initializeTurnTracker();
		bestGameEver.nextEntity();
		currentPiece=bestGameEver.currentEntityToString();
		assertTrue(currentPiece.substring(currentPiece.length()-2, currentPiece.length()).equals("10"));
		
		bestGameEver.nextPlayer();
		bestGameEver.nextEntity();
		currentPiece=bestGameEver.currentEntityToString();
		assertTrue(currentPiece.substring(currentPiece.length()-1).equals("0"));
	}

}
