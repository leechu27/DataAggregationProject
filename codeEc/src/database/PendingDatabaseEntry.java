package database;

import database.PendingDatabaseEntryInterface;
import gui.ConflictGUI;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * A class for adding new row(s) to the ICare database
 * Can either take in a csv ICare template or can be manually set with parameters
 */
public class PendingDatabaseEntry implements PendingDatabaseEntryInterface {

  private static final String MAIN_ID_COLUMN_NAME = CSVParser.PRIMARY_ID_COL_NAME;
private static final String DATABASE_NAME = "test.db";
/*
   * Maps each user to all of the entries they need made
   * Each user has a list of insertions that they need to make
   * each insertion contains the table name, Column name,
   * and data to be inserted respectively in that order
   * 
   * userID -> insertions -> {tableName, columnName, data}
   */
  //private HashMap<String, ArrayList<List<String>>> insertions;
  
  private String tableName;
  private HashMap<String, Integer> columnNameToColumnIndex = new HashMap<>();
  private HashMap<String, Integer> userIdToPersonRowIndex = new HashMap<>();
  private ArrayList<String> columnNames = new ArrayList<>();
  
  // This is what stores the actual data. It contains rows, where each row corresponds to each user
  // Each element in each row is the column
  // The way to get the column index for a datum is to use the two hashmaps above
  private ArrayList<ArrayList<String>> userData = new ArrayList<>();

  /**
   * Create a row to add to the database from scratch
   */
  public PendingDatabaseEntry() {
	  columnNameToColumnIndex = new HashMap<>();
	  userIdToPersonRowIndex = new HashMap<>();
	  columnNames = new ArrayList<>();
	  userData = new ArrayList<>();
  }

  
  private String getData(String userId, String columnName) {
	  return userData.get(userIdToPersonRowIndex.get(userId)).get(columnNameToColumnIndex.get(columnName));
  }

  /**
   *  Inserts into the database
   *
   */
  public void addData(String userId, String tableName, String columnName, String data) {
	  if (this.tableName == null) {this.tableName = tableName;}
	  else {assert this.tableName.equalsIgnoreCase(tableName);}
	  
	  columnName = columnName.replace("ï", "").replace("»", "").replace("¿", ""); // get rid of weird artifacts from reading
	  // if we need to add a new column
	  if (!columnNameToColumnIndex.containsKey(columnName)) {
		  columnNameToColumnIndex.put(columnName, columnNames.size());
		  columnNames.add(columnName);
		  // pad out the column with null values
		  for (ArrayList<String> user : userData) {
			  user.add(null);
		  }
	  }
	  // if we need to add a new userId
	  if (!userIdToPersonRowIndex.containsKey(userId)) {
		  userIdToPersonRowIndex.put(userId, userData.size());
		  userData.add(new ArrayList<String>());
		  // pad out the user's row with null values
		  for (String column : columnNames) {
			  userData.get(userData.size() - 1).add(null);
		  }
	  }
	  
	  // the magical one liner. This sets the actual data we have to our data structure
	  userData.get(userIdToPersonRowIndex.get(userId)).set(columnNameToColumnIndex.get(columnName), data);
	  
	  
//    //TODO check whether this data is even in the database in the first place
//    //TODO get a safer method to do this, that checks the type of the data instead of assuming everything is raw text
//    if (!insertions.containsKey(userId)) { // add new user if we didn't yet
//      insertions.putIfAbsent(userId, new ArrayList<>());
//    }
//    List<String> insertion = Arrays.asList(tableName, columnName, data);
//    insertions.get(userId).add(insertion);
  }


  /**
   * Clears all entries to be added to the database, does nothing right now
   */
  public void clear() {
	  //TODO this isn't actually needed right now, to be added
  }


  /**
   *  Once all of the fields you want to add are added, you can run this
   *  method to dump all of the data into the main database
   */
  public String dumpIntoDatabase(String databaseName) {
    database.DatabaseQuery dq = new database.DatabaseQuery(databaseName);
    // Go through each user, and add their data to the database
    try {
    	dq.updateWithSQL(this.getAsSQL(databaseName));
    	return "succesfully added to database";
    } catch (Error e) {
    	return e.getMessage();
    }
  }
  
  private String dumpUserIntoDatabase(String databaseName, String userId) {
	  String sql = "";
	  for (String columnName : columnNameToColumnIndex.keySet()) {
  		if (this.getData(userId, columnName) != null) {
	    		sql += 
				"UPDATE " + this.tableName + "\n" +
			    "Set " + columnName + " = '" + this.getData(userId, columnName) + "'\n" +
				"WHERE " + MAIN_ID_COLUMN_NAME + " = " + userId + ";\n";
  			}
  		}
	  	database.DatabaseQuery dq = new database.DatabaseQuery(databaseName);
	    // Go through each user, and add their data to the database
	    try {
	    	dq.updateWithSQL(this.getAsSQL(databaseName));
	    	return "succesfully added to database";
	    } catch (Error e) {
	    	return e.getMessage();
	    }
  }

  /**
   * Instead of sending directly to the database, gives you the sql that would
   * have been used.
   * @param databaseName the filename of the SQLItedatabase
   * @return
   */
  public String getAsSQL(String databaseName) {
	  String sql = "";
	  for (String userId : userIdToPersonRowIndex.keySet()) {
	    	for (String columnName : columnNameToColumnIndex.keySet()) {
	    		if (this.getData(userId, columnName) != null) {
		    		sql += 
					"UPDATE " + this.tableName + "\n" +
				    "Set " + columnName + " = '" + this.getData(userId, columnName) + "'\n" +
					"WHERE " + MAIN_ID_COLUMN_NAME + " = " + userId + ";\n";
	    		}
	    	}
	    }
    return sql;
  }

/**
 * Verifies with the user that the pending entries are to be added to the database
 * Uses the ConflictGUI to do so
 * 
 * Should only be used on one table at a time, or things will break
 * 
 * @return true if the user wants to overwrite the info in the database
 * 			returns false if the user wants to keep the old data
 * @throws SQLException 
 * TODO this is a bit of a mega-method right now, could use refactoring but at least it works
 */
public String verifyWithGUIIfNeeded(String databaseName) throws SQLException {
	String output = "";
	
	DatabaseQuery q = new DatabaseQuery(DATABASE_NAME);
	if (this.tableName == null || this.userData.isEmpty()) { 
		return "nothing was input into the database"; // if there were no entries made, don't bother querying
	} 
	
	// finding the output from the server
	String sql = "SELECT * FROM " + this.tableName + ";";
	ResultSet rs = q.queryWithSQL(sql);
	ResultSetMetaData rsmd = rs.getMetaData();
	int numColsDatabase = rsmd.getColumnCount();
	
	// get the column names for the database as an array
	String[] columnNames = new String[numColsDatabase];
	for (int i = 1; i < numColsDatabase + 1; i++) {
		columnNames[i - 1] = rs.getMetaData().getColumnName(i);
	}
	
	// get the column names for the new entry as an array
	System.out.println(this.columnNames);
	System.out.println(this.columnNames.toArray());
	String[] columnNamesNew = new String[this.columnNames.size()];
	for (int i = 0; i < this.columnNames.size(); i++)
		columnNamesNew[i] = (String) this.columnNames.get(i);
	
	String[] oldData = new String[numColsDatabase];
	String[] newData = new String[this.columnNames.size()];
	
	// go through the data for each user and check it individually
	for (String user : userIdToPersonRowIndex.keySet()) {
		// get the old data from the server
		sql = "SELECT * FROM " + this.tableName + " WHERE " + MAIN_ID_COLUMN_NAME + " = '" + user + "';";
		System.out.println(sql);
		rs = q.queryWithSQL(sql);
		int i = 0;
		while (rs.next()) {
			oldData[i] = rs.getString(1);
			i++;
		}
		
		// get the new data inside of this object as an array
		for (int j = 0; j < this.columnNames.size(); j++)
			newData[j] = (String) this.userData.get(this.userIdToPersonRowIndex.get(user)).get(j);
		
		ConflictGUI.setNewRows(columnNamesNew, newData);
		ConflictGUI.setOldRows(columnNames, oldData);
		
		// waits for user to pick whether they want to keep or toss the old data
		boolean replaceOldData = ConflictGUI.dataToBeReplaced();
		if (replaceOldData) {
			dumpUserIntoDatabase(databaseName, user);
			output += "replaced data for " + user + "\n";
		} else {
			output += "kept old data for " + user + "\n";
		}
	}
	return output;
}

}
