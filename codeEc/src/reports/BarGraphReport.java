package reports;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarGraphReport implements Report{

  private String fileLocation;
  private DefaultCategoryDataset data;
  private int height = 480;
  private int width = 640;
  
  public BarGraphReport(String fileLocation) {
    
  }

  @Override
  public void writeToFile() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setNewData(String key, double value) {
    // TODO Auto-generated method stub
    
  }

}
