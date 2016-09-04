package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BookIndex implements Index {
	private String file;
	private Map<String,WordEntry> index = new HashMap<String, WordEntry>();
	private Dictionary dico ;
	private StopWords stopWords;
	
	public BookIndex(String file){
		this.file=file;
		this.dico = new Dictionary();
		this.stopWords = new StopWords();
		generateIndex();
	}

	private void generateIndex() {
		//This method loads a book and generate its index
				BufferedReader br = null;
				String line = "";
				int lineCounter=0;
				int pageCounter = 1;
				try {
					br = new BufferedReader(new FileReader(file)); // open the file
					while ((line = br.readLine()) != null){
						//As long as we did not reach then end of the file,
						//we keep reading each line and update the index
						lineCounter ++;
						if (lineCounter % 40 == 0){
							pageCounter ++;
						}
						String[] words = line.split(" ");
						for (int i = 0 ; i < words.length; i++){
							String word = words[i];
							if(!word.equals(null)) {
								updateIndex(word.toLowerCase().replaceAll("[^a-zA-Z]+", ""),pageCounter);
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Index of the book generated with "+index.size()+" entries");
		
	}

	private void updateIndex(String word, int page) {
		//Check if the word exists in the list of stop words
		// skip the word if it is in the list of stop words
		if(!stopWords.exist(word)){ 
			//check if the word already exists in the index map
			if (index.containsKey(word)){
				//update the index of the word in the map
				index.get(word).addIndex(page);
			}
			else{
				// create a new entry in the map
				WordEntry we = new WordEntry();
				we.addIndex(page);
				String definition;
				try{
					 definition = dico.getDefinitions(word).toString();
					if (definition.equals(null) || definition.length()==0){
						definition = "UNDEFINED";
					}

				}catch(Exception e){
					definition = "UNDEFINED";
				}
				
				we.setDefnition(definition);
				index.put(word, we);
			}
			
		}
		
	}
	
	public void displayIndex(){
		for (String s : index.keySet()){
			System.out.println("Word: "+ s);
			System.out.println("Definition: " + index.get(s).getDefinition());
			System.out.println("Pages: "+ index.get(s).getIndeces());;
			
		}
	}
	
	public void displayIndexEntry(String word){
		if(index.containsKey(word.toLowerCase())){
			System.out.println("Word: "+ word);
			System.out.println("Definition: " + index.get(word.toLowerCase()).getDefinition());
			System.out.println("Pages: "+ index.get(word.toLowerCase()).getIndeces());;
		}
		
	}
	
	public WordEntry findInIndex(String word){
		return index.get(word);
	}

	
	public void removeFromIndex(String word) {
		if (index.containsKey(word.toLowerCase())){
			index.remove(word.toLowerCase());
		}
	}
}
