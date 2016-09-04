package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dictionary {
	private String csvFile ="dictionary.csv";
	private Map<String,Set<String>> dictionary = new HashMap<String,Set<String>>();

	public Dictionary() {
		loadDictionaryFromCSVFile();
		
	}
	public Dictionary(String csvFile) {
		this.csvFile = csvFile;
		loadDictionaryFromCSVFile();
		
	}

	public void addNewWord(String word, String definition){
		//This method allows to add a new word in the dictionary.
		//It give as input: the word (which is used as a key in the dictionary)
		//                  the definition as a String 
		// We need to check if the word exist already in the dictionary.
		// If yes ==> we get the set of deinitions that exist already in the dictionary and add the new one.
		// Then we remove the word from the dictionary in order to add it again with the new set of definitions.
		// If the word does not exist, we simply add it with a set of 1 definition
		Set<String> def = new HashSet<String>(); // The set of definitions of a word - currently empty
		def.add(definition); // Add the new definition to the variable def
		
		if (dictionary.containsKey(word)){ // Check if the word exsit in the dictionary
			Set <String> existingDef = dictionary.get(word); // get the existing set of definitions of that word
			def.addAll(existingDef);// add the old definitiona to the new set of definitions
			dictionary.remove(word); // remove the word from the dictionary
		}

		dictionary.put(word, def); // add the new/old word to the dictionary
	}
	
	public Set<String> getDefinitions(String word){
		return dictionary.get(word); 
	}
	
	public void loadDictionaryFromCSVFile(){
		//This method loads a CVS file and creates the dictionary.
		//This method has been adapted from http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			// read the first line of the csv file without creating a new word in the dictionary
			// the first line contains only the header of the csv file
			br.readLine();
			while ((line = br.readLine()) != null){

				String[] entry = line.split(cvsSplitBy);
				//System.out.println(line);
				//System.out.println(entry[0]);
				//System.out.println(entry[1]);
				//System.out.println(entry[2]);

				addNewWord(entry[0].toLowerCase(), entry[1]+" "+entry[2]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Dictionary Loaded with "+dictionary.size()+" entries");
	}
}
