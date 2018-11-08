package c01_project.database.selector;

import java.sql.ResultSet;
import c01_project.database.DatabaseQuery;;

public class DatabaseSelector {

  /* Returns the data of the request columns from the table
   * 
   * @param database the database to take the data from
   * @param table a table in the database 
   * @param columns the columns, of the table, that are the desired 
   */
  public static ResultSet selectColumnms(DatabaseQuery database, String table, String[] columns) throws DatabaseNullException, NullTableException, NullColumnsException {
    if (database == null) {
      throw new DatabaseNullException();
    } else if (table == null) {
      throw new NullTableException();
    } else if (columns == null) {
      throw new NullColumnsException();
    }
    
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
    rawSQL += "FROM " + table;
    ResultSet data = database.queryWithSQL(rawSQL);
    return data;
    
  }
  
  /*public static void main(String[] args) {

  }*/

}
