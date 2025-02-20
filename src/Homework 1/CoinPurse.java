import java.util.Random;
import java.util.Arrays;

public class CoinPurse {
	//data fields
	private int numGalleons;
	private int numSickles;
	private int numKnuts;
	public static final int CAPACITY=256;
	
	
	//constructors
	public CoinPurse() { //default, empty constructor, makes an empty coin purse
		numGalleons=0;
		numSickles=0;
		numKnuts=0;
	}
	public CoinPurse(int g, int s, int k) {
		if((g+s+k)>CAPACITY) {
			throw new IllegalArgumentException("Error: The total number of coins "
					+ "in the purse cannot exceed 256 coins.");
		}
		else if((g<0) || (s<0) || (k<0)) {
			throw new IllegalArgumentException("Error: You cannot have a negative amount of coins.");
		}
		numGalleons=g;
		numSickles=s;
		numKnuts=k;
	}
	
	
	//functions for adding coins
	/**adds n galleons to the purse*/
	public void depositGalleons(int n) {
		if(((numGalleons+n)+numSickles+numKnuts)>CAPACITY) {
			throw new IllegalArgumentException("Error: The total number of coins "
					+ "in the purse cannot exceed 256 coins.");
		}
		else if(n<0) {
			throw new IllegalArgumentException("You cannot depsoit a negative amount of coins.");
		}
		this.numGalleons=numGalleons+n;
	}
	/**adds n sickles to the purse*/
	public void depositSickles(int n) {
		if((numGalleons+(numSickles+n)+numKnuts)>CAPACITY) {
			throw new IllegalArgumentException("Error: The total number of coins "
					+ "in the purse cannot exceed 256 coins.");
		}
		else if(n<0) {
			throw new IllegalArgumentException("You cannot depsoit a negative amount of coins.");
		}
		this.numSickles=numSickles+n;
	}
	
	/**adds n knuts to the purse*/
	public void depositKnuts(int n) {
		if((numGalleons+numSickles+(numKnuts+n))>CAPACITY) {
			throw new IllegalArgumentException("Error: The total number of coins "
					+ "in the purse cannot exceed 256 coins.");
		}
		else if(n<0) {
			throw new IllegalArgumentException("You cannot depsoit a negative amount of coins.");
		}
		this.numKnuts=numKnuts+n;
	}
	
	
	//functions for withdrawing coins
	/**removes n galleons from the purse*/
	public void withdrawGalleons(int n) {
		if((numGalleons-n<0) || (numSickles<0) || (numKnuts<0)) {
			throw new IllegalArgumentException("Error: You cannot have a negative amount of coins.");
		}
		else if(n<0) {
			throw new IllegalArgumentException("You cannot withdraw a negative amount of coins.");
		}
		this.numGalleons=numGalleons-n;
	}
	
	/**removes n sickles from the purse*/
	public void withdrawSickles(int n) {
		if((numGalleons<0) || (numSickles-n<0) || (numKnuts<0)) {
			throw new IllegalArgumentException("Error: You cannot have a negative amount of coins.");
		}
		else if(n<0) {
			throw new IllegalArgumentException("You cannot withdraw a negative amount of coins.");
		}
		this.numSickles=numSickles-n;
	}
	
	/**removes n knuts from the purse*/
	public void withdrawKnuts(int n) {
		if((numGalleons<0) || (numSickles<0) || (numKnuts-n<0)) {
			throw new IllegalArgumentException("Error: You cannot have a negative amount of coins.");
		}
		else if(n<0) {
			throw new IllegalArgumentException("You cannot withdraw a negative amount of coins.");
		}
		this.numKnuts=numKnuts-n;
	}
	
	
	//cumulative operations
	/**@return integer, the total number of coins in the purse*/
	public int numCoins() {
		return numGalleons+numSickles+numKnuts;
	}
	
	/**@return integer, the total value of all coins in the purse*/
	public int totalValue() {
		return (numGalleons*17*29)+(numSickles*29)+(numKnuts);
	}
	
	/**@return string, string representation of the toal of each type of coin*/
	public String toString() {
		return "Galleons: "+numGalleons+" Sickles: "+numSickles+" Knuts: "+numKnuts;
	}
	
	
	//exact change
	/**@return boolean, whether the coins in the purse can make make up a value of exactly n*/
	public boolean exactChange(int n) {
		for(int g=0; g<=numGalleons; g++) {
			for(int s=0; s<=numSickles; s++) {
				for(int k=0; k<=numKnuts; k++) {
					int totalVal=(g*17*29)+(s*29)+(k);
					if(totalVal==n) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**@return integer array, withdraws a value of n (or the next smallest value that is larger than n) by removing a certain number of each coin*/
	public int[] withdraw(int n) {
		int[] list={0,0,0};
		if(n>this.totalValue()) {
			throw new IllegalArgumentException("Error: You cannot have a negative amount of coins.");
		}
		else if(this.exactChange(n) == true) {
			for(int g=0; g<=numGalleons; g++) {
				for(int s=0; s<=numSickles; s++) {
					for(int k=0; k<=numKnuts; k++) {
						int totalVal=(g*17*29)+(s*29)+(k);
						if(totalVal==n) {
							this.withdrawGalleons(g);
							this.withdrawSickles(s);
							this.withdrawKnuts(k);
							list[0]=g;
							list[1]=s;
							list[2]=k;
							return list;
						}
					}
				}
			}
		}
		else if(this.exactChange(n) == false) {
			for(int g=0; g<=numGalleons; g++) {
				for(int s=0; s<=numSickles; s++) {
					for(int k=0; k<=numKnuts; k++) {
						int totalVal=(g*17*29)+(s*29)+(k);
						if(totalVal>n) {
							this.withdrawGalleons(g);
							this.withdrawSickles(s);
							this.withdrawKnuts(k);
							list[0]=g;
							list[1]=s;
							list[2]=k;
							return list;
						}
					}
				}
			}
		}
		return list;
	}
	
	
	//a game of chance
	/**@return integer, draws a random coin from the purse*/
	public int drawRandCoin() { //0=galleon  1=sickle  2=knut
		if(this.numCoins()==0) {
			throw new IllegalArgumentException("Error: Cannot draw a random coin "
					+ "from a purse with no coins.");
		}
		
		Random random = new Random();
		double probG=numGalleons/this.numCoins();
		double probS=numSickles/this.numCoins();
		//double probK=numKnuts/this.numCoins();
		
		double randProb=random.nextDouble();
		
		if(randProb<probG) {
			return 0;
		}
		else if(randProb>=probG && randProb<(probG+probS)) {
			return 1;
		}
		else {
			return 2;
		}
		
	}
	
	/**@return integer array, draws a random sequence of arrays (with replacement)*/
	public int[] drawRandSequence(int n) {
		int[] list=new int[n];

		for(int i=0; i<n; i++) {
			list[i]=this.drawRandCoin();
		}

		return list;
	}
	
	/**@return integer, compares two sequences of equal length, determines a winner*/
	public static int compareSequences(int[] coinSeq1, int[] coinSeq2) {
		if(coinSeq1.length!=coinSeq2.length) {
			throw new IllegalArgumentException("You cannot compare sequences of unequal lengths.");
		}
		else {
			int score1=0;
			int score2=0;
			for(int i=0; i<coinSeq1.length; i++) {
				if(coinSeq1[i]-coinSeq2[i] > 0) {
					score1++;
				}
				else if(coinSeq1[i]-coinSeq2[i] < 0) {
					score2++;
				}
			}

			if(score1>score2) {
				return 1;
			}
			else if(score2>score1) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CoinPurse c = new CoinPurse(2,5,10);
		System.out.println(c.numGalleons);
		System.out.println(c.numSickles);
		System.out.println(c.numKnuts);
		System.out.println();
		c.depositGalleons(5);
		System.out.println(c.numGalleons);
		System.out.println(c.numSickles);
		System.out.println(c.numKnuts);
		System.out.println();



		CoinPurse c2= new CoinPurse(2,5,10);
		CoinPurse c3= new CoinPurse(2,5,10);
		System.out.println();
		System.out.println(c.totalValue());
		System.out.println(c.exactChange(559));
		System.out.println(c.exactChange(564));
		System.out.println();
		System.out.println(Arrays.toString(c.withdraw(559)));
		System.out.println(c.numGalleons);
		System.out.println(c.numSickles);
		System.out.println(c.numKnuts);
		System.out.println();
		System.out.println(c2.withdraw(564));
		System.out.println(c3.withdraw(563));
		System.out.println();
		System.out.println(c.drawRandCoin());
		int[] seq1=c.drawRandSequence(5);
		int[] seq2=c.drawRandSequence(5);
		System.out.println(Arrays.toString(seq1));
		System.out.println(Arrays.toString(seq2));
		System.out.println(compareSequences(seq1,seq2));
	}
}
