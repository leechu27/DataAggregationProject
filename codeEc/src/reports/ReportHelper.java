package reports;


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
  public void addEntries(List<String> entries) {
    String base = "";
    
  }
}
