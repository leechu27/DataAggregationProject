package c01_project.reports;

import java.io.File;
import javax.print.attribute.standard.PrinterLocation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import c01_project.database.CSVParser;
import c01_project.database.DatabaseQuery;
import c01_project.gui.FileExistsException;
import c01_project.gui.InvalidFileException;

public class ReportTest {

  private final String TEST_ROOT ="src/test/resources/testICareTemplates/";
  private static Report report;
  private static CSVParser parser;
  private static DatabaseQuery query;

  @BeforeAll
  public void beforeAll() throws InvalidFileException, FileExistsException {
    try {
      report = new Report("", "testReport.csv");
      parser = new CSVParser("test.db");
      query = new DatabaseQuery("test.db");
      parser.parseCSVBasicICareTemplate("src/test/resources/testICareTemplates/" + "normal_multiple_linens.csv");
    } catch (InvalidFileException i) {
      System.out.println(i);
    } catch (FileExistsException f) {
      System.out.println(f);
    }
  }

  @Test
  @DisplayName("a single column")
  public void testOneColumn() {
    String[] columns = {"name"};
    report.writeReport(query, "basic_data", columns);
    File expected = new File("src/test/resources/testReports/single_column.csv");
    File result = new File("testReport.csv");
    assertEquals(expected, result);
  }

  @Test
  @DisplayName("multiple columns")
  public void testMultipleColumns() {

  }
  
  @Test
  @DisplayName("report a table")
  public void testFullTable() {

  }
  
  @Test
  @DisplayName("")
  public void test() {

  }
}
