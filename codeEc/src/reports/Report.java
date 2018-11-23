package reports;

import java.sql.SQLException;
import java.util.Map;
import database.DatabaseQuery;

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
	
	/*
	 * Parses the data in the database based on the entries inputed and adds the value alongside the entry for the report
	 * 
	 * @param database    the database to retrieve the data from
	 * @param table       the table in the database to retrieve the database from
	 * @param entries     a map where keys are the label and the values are the conditions for the SQL command
	 */
	public void addDatabaseEntries(DatabaseQuery database, String table, Map<String, String> entries) throws SQLException; 

}
