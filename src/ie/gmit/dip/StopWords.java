package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class StopWords {
	private String file ="stopwords.txt";
	private Set<String> stopWords = new HashSet<String>();
	
	public StopWords(String file) {
		this.file = file;
		loadStopsWordsFromFile();
		
	}
	public StopWords() {
		loadStopsWordsFromFile();
		
	}
	private void loadStopsWordsFromFile() {
		//This method loads a file of stop words into a the stopWords set.
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(file)); // open the file
			while ((line = br.readLine()) != null){
				//As long we did not reach then end of the file,
				//we keep reading each line and save it in the list of stop words
				stopWords.add(line.toLowerCase());
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("List of Stop Words Loaded with "+stopWords.size()+" entries");
	}
	
	public boolean exist(String word){
		return stopWords.contains(word.toLowerCase());
	}

}
