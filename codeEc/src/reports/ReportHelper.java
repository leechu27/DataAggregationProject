package reports;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import database.DatabaseQuery;

public class ReportHelper {

  private Report report;
  private DatabaseQuery database;
  
  /*
   * @param 
   * @param
   */
  public ReportHelper(Report report, DatabaseQuery database) {
    this.report = report;
    this.database = database;
  }
  
  /*
   * Parses the data in the database based on the entries inputed and adds the value alongside the entry for the report
   * 
   * @param entries the entries to be added into the report, formatted in a way that it can be put into SQL as a condition
   */
  public void addEntries(String table, List<String> entries) throws SQLException {
    String base = "SELECT COUNT(*) AS rowcount FROM " + table + " WHERE ";
    String temp;
    int value;
    for (String entry : entries) {
      temp = base + entries;
      ResultSet result = database.queryWithSQL(temp);
      result.next();
      value = result.getInt("rowcount");
    }
  }
}
