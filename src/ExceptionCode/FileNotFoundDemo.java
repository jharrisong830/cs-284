import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class FileNotFoundDemo {
	
	/* FileNotFoundException is a checked exception
	Slides and book have more detail about what this means 
	Try removing the throws from the main method */
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("file.txt");
		BufferedReader reader = new BufferedReader(
				new FileReader(file));
	}
}