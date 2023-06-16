/*
 * Ryan Mogauro
 * 4/11/22
 * WordCounter.java
 * Word Frequencies
 * CS231
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter; 
import java.util.ArrayList; 

public class WordCounter {
	private int totalWords; 
	private BSTMap<String, Integer> map;
	
	//constructor
	public WordCounter(){
		this.totalWords = 0;
		this.map = new BSTMap<String, Integer>(new AscendingString()); 
		
	}
	
	//generates the word counts from a file of words
	public void analyze(String filename) {
		try {
			FileReader fileRead = new FileReader(filename); 
			BufferedReader buffRead = new BufferedReader(fileRead); 
			String line = buffRead.readLine();
			
			
			while(line != null) {
				
				
				String[] words = line.split("[^a-zA-Z0-9']");
				for(int i = 0; i < words.length; i++) {
					String word = words[i].trim().toLowerCase(); 
					if(word.length() > 0){
						boolean inMap = map.containsKey(word); 
						this.totalWords++; 
						if(inMap) {
							map.put(word,map.get(word)+1); 
						} else {
							map.put(word, 1); 
						}
					}
				}
				line = buffRead.readLine(); 
			}
			buffRead.close(); 
		}catch(FileNotFoundException ex) {
			System.out.println("Board.read():: unable to open file" + filename); 
		} catch(IOException ex) {
		System.out.println("Board.read():: error reading file" + filename); 
		}
	}
	
	//returns total word count of body of text
	public int getTotalWordCount() {
		return this.totalWords; 
	}
	
	//returns number of unique words in a body of text
	public int getUniqueWordCount() {
		return this.map.size(); 
	}

	//returns number of time given word parameter appears in text
	public int getCount( String word ) {
		return this.map.get(word); 
	}
	
	//returns the count of a word in a body of text divided by the total number of words in text
	public double getFrequency(String word) {
		return this.map.get(word) / (double)this.totalWords; 
	}
	
	
	//writes to a file
	//first line is total word count
	//following lines are each unique word in text and its frequency 
	public void writeWordCountFile(String filename) {
		try {
		FileWriter fileWrite = new FileWriter(filename); 
		fileWrite.write("TotalWordCount : " + getTotalWordCount());
		ArrayList<KeyValuePair<String, Integer>> arr = this.map.entrySet(); 
		for(KeyValuePair<String, Integer> pair: arr) {
			fileWrite.write("\n" + pair.getKey() + " " + pair.getValue()); 
		}
		fileWrite.close(); 
		}
		catch(IOException e) {
			System.out.println("An error occured.");
			e.printStackTrace();
		}
	}
	
	//reads the contents of a word count file
	//and reconstructs the fields of the WordCount object, including the BSTMap.
	public void readWordCountFile(String filename) {
		try {
			this.map.clear(); 
			FileReader fileRead = new FileReader(filename); 
			BufferedReader buffRead = new BufferedReader(fileRead); 
			String line = buffRead.readLine(); 
			String[] words = line.split(" : ");
			this.totalWords = Integer.parseInt(words[1]); 
			line = buffRead.readLine(); 
				words = line.split(" "); 
			while(line != null) {
				this.map.put(words[0], Integer.parseInt(words[1])); 
				line = buffRead.readLine(); 
			}
			buffRead.close(); 
		} catch(FileNotFoundException ex) {
			System.out.println("Board.read():: unable to open file " + filename); 
		} catch(IOException ex) {
			System.out.println("Board.rread():: error reading file " + filename); 
		}
	}
	
	//returns string representing map
	public String toString() {
		return this.map.toString(); 
	}

	//tests methods to ensure they're working as intended
	public static void main( String[] args ) {
		// WordCounter newCounter = new WordCounter();
		// newCounter.analyze("counttest.txt"); 
		// System.out.println(newCounter.getTotalWordCount());
		// System.out.println(newCounter.getUniqueWordCount());
		// System.out.println(newCounter.getCount("it"));
		// System.out.println(newCounter.getFrequency("it"));
		// newCounter.writeWordCountFile("counttest.txt");
		// System.out.println(newCounter);

		for(int i = 0; i < args.length; i++){
			WordCounter w1 = new WordCounter(); 
			double start = System.currentTimeMillis(); 
			w1.analyze(args[i]); 
			w1.writeWordCountFile("counts_" + args[i]); 
			double end = System.currentTimeMillis();
			System.out.println("Time to process: " + args[i] + " is " + (end-start)); 
			System.out.println("Total word count: " + w1.getTotalWordCount());
			System.out.println("Unique word count: " + w1.getUniqueWordCount());  

		}
	}
	
}
