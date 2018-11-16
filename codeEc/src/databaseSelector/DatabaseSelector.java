package databaseSelector;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import database.DatabaseQuery;
import database.databaseSetup;

public class DatabaseSelector {

  /* Returns the data of the requested columns from the table
   * 
   * @param database the database to take the data from
   * @param table a table in the database 
   * @param columns the columns, of the table, that are the desired
   */
  public static ResultSet selectColumns(DatabaseQuery database, String table, List<String> columns) throws DatabaseNullException, InvalidTableException, InvalidColumnsException {
    if (database == null) {
      throw new DatabaseNullException();
    } else if (table == null) {
      throw new InvalidTableException();
    } else if (columns == null || columns.size() == 0) {
      throw new InvalidColumnsException();
    } else if (columns.size() > 1 && columns.contains("*")) {
      throw new InvalidColumnsException();
    }
    
    String rawSQL = formSQL(table, columns);
    ResultSet data = database.queryWithSQL(rawSQL);
    return data;
    
  }
  
  /* Returns the data of the requested columns from the table
   * 
   * @param database the database to take the data from
   * @param table a table in the database 
   * @param columns the columns, of the table, that are the desired
   */
  public static ResultSet selectColumns(DatabaseQuery database, String table, List<String> columns, String condition) throws DatabaseNullException, InvalidTableException, InvalidColumnsException, InvalidFilterException {
    if (database == null) {
      throw new DatabaseNullException();
    } else if (table == null) {
      throw new InvalidTableException();
    } else if (columns == null || columns.size() == 0) {
      throw new InvalidColumnsException();
    } else if (columns.size() > 1 && columns.contains("*")) {
      throw new InvalidColumnsException();
    } else if (condition == null) {
      throw new InvalidFilterException();
    }
    
    String rawSQL = formSQL(table, columns, condition);
    ResultSet data = database.queryWithSQL(rawSQL);
    return data;
    
  }
  
  /*
   * Forms the raw SQL command given the table and columns requested
   */
  private static String formSQL(String table, List<String> columns) {
    int format = 1;
    String rawSQL = "SELECT ";
    for (String column: columns) {
      if (format == 1) {
        format = 0;
      } else {
        rawSQL += ", ";
      }
      rawSQL += column;
    }
    rawSQL += " FROM " + table;
    return rawSQL;
  }
  
  /*
   * Forms the raw SQL command given the table, columns requested, and a condition to pass
   */
  private static String formSQL(String table, List<String> columns, String condition) {
    String rawSQL = formSQL(table, columns);
    rawSQL += " WHERE " + condition;
    return rawSQL;
  }

}
