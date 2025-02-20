//John Graham
//I pledge my honor that I have abided by the Stevens Honor System.
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Anagrams {
	//data fields
	final Integer[] primes=new Integer[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
										43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	public Anagrams() { //constructor
		this.letterTable=new HashMap<Character,Integer>();
		buildLetterTable();
		this.anagramTable=new HashMap<Long,ArrayList<String>>();
	}
	
	
	
	/**Initializes the letterTable with each letter being a key associated with the nth prime number.*/
	public void buildLetterTable() {
		Character[] letters=new Character[]{'a','b','c','d','e','f','g','h','i','j','k','l','m',
											'n','o','p','q','r','s','t','u','v','w','x','y','z'};
		for(int i=0; i<26; i++) {
			letterTable.put(letters[i], primes[i]);
		}
	}
	
	/**Adds a word to the anagramTable based on its hashcode.
	 * @param s, the word to be added to anagramTable*/
	public void addWord(String s) {
		long code=myHashCode(s);
		if(anagramTable.containsKey(code)) {
			ArrayList<String> list=anagramTable.get(code);
			if(list.contains(s)) {
				throw new IllegalArgumentException("addWord: duplicate value");
			}
			list.add(s);
			anagramTable.put(code, list);
		}
		else {
			ArrayList<String> list=new ArrayList<String>();
			list.add(s);
			anagramTable.put(code, list);
		}
	}
	
	/**Computes the hashcode for a word based on the values associated with each letter in letterTable.
	 * @param s, the word whose hashcode is being computed
	 * @return long, the hashcode of the word*/
	public long myHashCode(String s) {
		if(s.equals("")) {
			throw new IllegalArgumentException("myHashCode: input cannot be empty");
		}
		long code=letterTable.get(s.charAt(0));
		for(int i=1; i<s.length(); i++) {
			code=code*letterTable.get(s.charAt(i));
		}
		return code;
	}
	
	/**Processes a file for use in Anagrams.
	 * @param s, the file name
	 * @throws IOException*/
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream ));
		String strLine;
		while (( strLine = br.readLine ()) != null) {
			this.addWord(strLine );
		}
		br.close ();
	}
	
	/**Finds the entry (or entries) in anagramTable that have the most number of anagrams.
	 * @return ArrayList<Map.Entry<Long,ArrayList<String>>>, a list containing the entries with the max number of anagrams
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long,ArrayList<String>>> currentMax=new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		for(Map.Entry<Long,ArrayList<String>> i:anagramTable.entrySet()) {
			if(currentMax.isEmpty() || i.getValue().size()==currentMax.get(0).getValue().size()) {
				currentMax.add(i);
			}
			else if(i.getValue().size() > currentMax.get(0).getValue().size()) {
				currentMax.clear();
				currentMax.add(i);
			}
		}
		return currentMax;
	}
	
	
	
	public static void main(String [] args) { //main function, where file is read and processed
		Anagrams a = new Anagrams ();
		final long startTime = System.nanoTime ();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace ();
		}
		ArrayList <Map.Entry <Long ,ArrayList <String >>> maxEntries = a.getMaxEntries ();
		final long estimatedTime = System.nanoTime () - startTime;
		final double seconds = (( double) estimatedTime /1000000000);
		System.out.println("Time: "+ seconds );
		System.out.println("List of max anagrams: "+ maxEntries );
		System.out.println(a.myHashCode("lemon"));
	}
}
