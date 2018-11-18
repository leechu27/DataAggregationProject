package c01_project.reports;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import c01_project.database.DatabaseQuery;
import c01_project.database.selector.DatabaseSelector;

public class ReportHelper {
  
  /*
   * Parses the data in the database based on the entries inputed and adds the value alongside the entry for the report
   * 
   * @param report      the report to add the data to
   * @param database    the database to retrieve the data from
   * @param table       the table in the database to retrieve the database from
   * @param entries     the entries to be added into the report, formatted in a way that it can be put into SQL as a condition
   */
  public void addEntries(Report report, DatabaseQuery database, String table, List<String> entries) throws SQLException {
    int value;
    for (String entry : entries) {
      value = DatabaseSelector.countRows(database, table, entry);
      report.setNewData(entry, value);
    }
  }
}
