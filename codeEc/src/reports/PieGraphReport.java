package reports;

import gui.InvalidFileException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import database.DatabaseQuery;
import databaseSelector.DatabaseSelector;

public class PieGraphReport implements Report{

  private String fileLocation;
  private DefaultPieDataset data;
  private int height = 480;
  private int width = 640;
  private String title;
  
  /* constructor for PieGraphReport
   * 
   * @param fileLocation            the file path as a string
   * @param title                   the title of the report
   * @throws InvalidFileException   If file is not a PNG
   */
  public PieGraphReport(String fileLocation, String title) throws InvalidFileException {
    if (!fileLocation.endsWith(".png")) {
      throw new InvalidFileException();
    }
    this.fileLocation = fileLocation;
    this.title = title;
    this.data = new DefaultPieDataset();
  }
  
  @Override
  public void writeToFile() {
    JFreeChart chart = createChart();
    File pieChart = new File(fileLocation);
    try {
      ChartUtils.saveChartAsPNG(pieChart, chart, width, height);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setNewData(String key, double value) {
    data.setValue(key, value);
  }

  /*
   * creates the chart with the data stored
   */
  private JFreeChart createChart() {
    JFreeChart pieChart = ChartFactory.createPieChart(title, data);
    return pieChart;
  }

  @Override
  public void clear() {
    data.clear();
  }


  @Override
  public void addDatabaseEntries(DatabaseQuery database, String table, Map<String, String> entries) throws SQLException {
    int value;
    String condition;
    String label;
    for (Entry<String, String> entry : entries.entrySet()) {
      label = (String) entry.getKey();
      condition = (String) entry.getValue();
      value = DatabaseSelector.countRows(database, table, condition);
      setNewData(label, value);
    }
  }
  
  /*public static void main(String[] args) {
    try {
      PieGraphReport testReport = new PieGraphReport("testPie.png", "Pie Test");
      ArrayList<String> entries = new ArrayList<String>();
      entries.add("language_of_preference = 'English'");
      entries.add("language_of_preference = 'French'");
      ReportHelper.addEntries(testReport, new DatabaseQuery("test_resources/testDatabases/test.db"), "basic_data", entries);;
      testReport.writeToFile();
    } catch (InvalidFileException e) {
      e.printStackTrace();
    } catch (SQLException sql) {
      System.out.println(sql.getMessage());
    }
  } */
  
}
