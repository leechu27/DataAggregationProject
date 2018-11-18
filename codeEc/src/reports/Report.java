package reports;

public interface Report {
	
	/*
	 * Outputs the current data to the file location
	 */
	public void writeToFile();
	
	/*
	 * adds a new data entry to the graph to be displayed on the file, updates a value if the key exists
	 * @param key      The key to be set
	 * @param value    The value of the new key
	 */
	public void setNewData(String key, double value);
	
	/*
	 * clears the data set of existing values
	 */
	public void clear();
}
