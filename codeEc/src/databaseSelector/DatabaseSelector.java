package databaseSelector;

import java.sql.ResultSet;
import java.sql.SQLException;
import database.DatabaseQuery;

public class DatabaseSelector {
  
  /*
   * Given a condition, returns the number of rows satisfying the condition
   * 
   * @param database    the database to take the data from
   * @param table       the table in the database to take the data from
   * @param entry       the WHERE condition; the condition to count the number of row
   */
  public static int countRows(DatabaseQuery database, String table, String entry) throws SQLException {
    int value;
    String cmd = "SELECT COUNT(*) AS rowcount FROM " + table + " WHERE " + entry;
    ResultSet result = database.queryWithSQL(cmd);
    result.next();
    value = result.getInt("rowcount");
    result.close();
    return value;
  }

}
