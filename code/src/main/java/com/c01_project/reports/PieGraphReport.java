package c01_project.reports;

import c01_project.gui.InvalidFileException;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;

public class PieGraphReport implements Report{

  private String fileLocation;
  private DefaultPieDataset data;
  private int height = 480;
  private int width = 640;
  private String title;
  
  /* constructor for PieGraphReport
   * 
   * @param fileLocation    the file path as a string
   * @param title   the title of the report
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

  private JFreeChart createChart() {
    JFreeChart pieChart = ChartFactory.createPieChart(title, data);
    return pieChart;
  }
  
  @Override
  public void clear() {
    data.clear();
  }
}
