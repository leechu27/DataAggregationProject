package reports;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;

public class PieGraphReport implements Report{

  private String fileLocation;
  private DefaultPieDataset data;
  private int height = 480;
  private int width = 640;
  
  public PieGraphReport(String fileLocation) {
    
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
