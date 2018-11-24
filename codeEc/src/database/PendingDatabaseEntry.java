package database;

import database.PendingDatabaseEntryInterface;
import java.util.*;
import java.util.List;

/**
 * A class for adding new row(s) to the ICare database
 * Can either take in a csv ICare template or can be manually set with parameters
 */
public class PendingDatabaseEntry implements PendingDatabaseEntryInterface {

  private static final String MAIN_ID_COLUMN_NAME = CSVParser.PRIMARY_ID_COL_NAME;
/*
   * Maps each user to all of the entries they need made
   * Each user has a list of insertions that they need to make
   * each insertion contains the table name, Column name,
   * and data to be inserted respectively in that order
   */
  private final static int TABLE_NAME_INDEX = 0;
  private final static int COLUMN_NAME_INDEX = 1;
  private final static int DATA_NAME_INDEX = 2;
  
  private HashMap<String, ArrayList<List<String>>> insertions;

  /**
   * Create a row to add to the database from scratch
   */
  public PendingDatabaseEntry() {
    insertions = new HashMap<>();
  }


  /**
   *  Inserts into the database
   *
   */
  public void addData(String userId, String tableName, String columnName, String data) {
    //TODO check whether this data is even in the database in the first place
    //TODO get a safer method to do this, that checks the type of the data instead of assuming everything is raw text
    if (!insertions.containsKey(userId)) { // add new user if we didn't yet
      insertions.putIfAbsent(userId, new ArrayList<>());
    }
    List<String> insertion = Arrays.asList(tableName, columnName, data);
    insertions.get(userId).add(insertion);

  }


  /**
   * Clears all entries to be added to the database
   */
  public void clear() {
    insertions.clear();
  }


  
  
  /**
   *  Once all of the fields you want to add are added, you can run this
   *  method to dump all of the data into the main database
   */
  public String dumpIntoDatabase(String databaseName) {
    database.DatabaseQuery dq = new database.DatabaseQuery(databaseName);
    String ret = "updated database succesfully!";
    // Go through each user, and add their data to the database
    for (String userId : insertions.keySet()) {
      for (List<String> data : insertions.get(userId)) {
        String tableName = data.get(TABLE_NAME_INDEX);
        String columnName = data.get(COLUMN_NAME_INDEX).replace("ï", "").replace("»", "").replace("¿", ""); // pretend you don't see this
        String insertable = data.get(2);

        String sql =
        "UPDATE " + tableName + "\n" +
        "SET " + columnName + " = '" + insertable + "'\n" +
        "WHERE " + MAIN_ID_COLUMN_NAME + " = " + userId.toString() + ";";

        dq.updateWithSQL(sql);
      }
    }
    return ret;
  }

  /**
   * Instead of sending directly to the database, gives you the sql that would
   * have been used.
   * @param databaseName the filename of the SQLItedatabase
   * @return
   */
  public String getAsSQL(String databaseName) {
    database.DatabaseQuery dq = new database.DatabaseQuery(databaseName);
    String returnVal = "";
    // Go through each user, and add their data to the database
    for (String userId : insertions.keySet()) {
      for (List<String> data : insertions.get(userId)) {
        String tableName = data.get(TABLE_NAME_INDEX);
        String columnName = data.get(COLUMN_NAME_INDEX);
        String insertable = data.get(DATA_NAME_INDEX);
        
        
        String sql =
                "UPDATE " + tableName + "\n" +
                "SET " + columnName + " = '" + insertable + "'\n" +
                "WHERE client_validation_id = " + userId + ";";

        returnVal += sql + "\n";
      }
    }
    return returnVal;
  }

}
