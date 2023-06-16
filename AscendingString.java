/*
 * Ryan Mogauro
 * 4/11/22
 * AscendingString.java
 * Word Frequencies
 * CS231
 */
import java.util.Comparator;


public class AscendingString implements Comparator<String>{
	
	//compare method that compares two strings
	//returns which one is lexicographically greater
	public int compare(String first, String second) {
		return first.compareTo(second); 
	}
}
