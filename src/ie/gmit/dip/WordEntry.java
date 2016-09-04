package ie.gmit.dip;

import java.util.TreeSet;

public class WordEntry {
	private String definition;
	private TreeSet<Integer> indeces = new TreeSet<Integer>();
	
	public String getDefinition(){
		return definition;
	}
	public void setDefnition(String definition){
		this.definition = definition;
	}
	public void addIndex(int i){
		indeces.add(i);
	}
	public TreeSet<Integer> getIndeces() {
		return indeces;
	}
	public void setIndeces(TreeSet<Integer> indeces) {
		this.indeces = indeces;
	}
	@Override
	public String toString() {
		return "Word Entry [definition=" + definition + ",\n pages = " + indeces + "]";
	}
	

}
