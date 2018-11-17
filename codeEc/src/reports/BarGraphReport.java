package reports;

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import gui.InvalidFileException;

public class BarGraphReport implements Report{

  private String fileLocation;
  private DefaultCategoryDataset data;
  private int height = 480;
  private int width = 640;
  private String title;
  private String category;
  private String value;
  
  /*
   * 
   */
  public BarGraphReport(String fileLocation, String title, String category, String value) throws InvalidFileException {
    if (!fileLocation.endsWith(".png")) {
      throw new InvalidFileException();
    }
    this.fileLocation = fileLocation;
    this.title = title;
    this.category = category;
    this.value = value;
    this.data = new DefaultCategoryDataset();
  }

  @Override
  public void writeToFile() {
    JFreeChart chart = createChart();
    File barChart = new File(fileLocation);
    try {
      ChartUtils.saveChartAsPNG(barChart, chart, width, height);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setNewData(String key, double value) {
    data.setValue(value, this.value, key);
  }

  private JFreeChart createChart() {
    JFreeChart barChart = ChartFactory.createBarChart(title, category, value, data);
    return barChart;
  }

  @Override
  public void clear() {
    data.clear();
  }
  
  /*
  public static void main(String[] args) {
    try {
      BarGraphReport testReport = new BarGraphReport("testBar.png", "Bar Test", "Age Ranges", "# of people");
      testReport.setNewData("7", new Double(3));
      testReport.setNewData("not 7", new Double(27));
      testReport.writeToFile();
    } catch (InvalidFileException e) {
      e.printStackTrace();
    }
  }
  */
}
