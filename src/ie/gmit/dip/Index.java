package ie.gmit.dip;

public interface Index {
	public void displayIndex();
	public WordEntry findInIndex(String word);
	public void displayIndexEntry(String word);
	public void removeFromIndex(String word);

}
