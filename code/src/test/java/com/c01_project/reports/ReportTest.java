package c01_project.reports;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import javax.print.attribute.standard.PrinterLocation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import c01_project.database.CSVParser;
import c01_project.database.DatabaseQuery;
import c01_project.database.databaseSetup;
import c01_project.gui.FileExistsException;
import c01_project.gui.InvalidFileException;

public class ReportTest {

  private final static String TEST_ROOT ="src/test/resources/testICareTemplates/";
  private final static String DATABASE_PATH = "testReport.db";
  private static Report report;
  private static CSVParser parser;
  private static DatabaseQuery query;

  @BeforeAll
  public static void beforeAll() throws InvalidFileException, FileExistsException {
    try {
      report = new Report("", "testReport.csv");
      databaseSetup.createNewDatabase(DATABASE_PATH);
      databaseSetup.initializeNewTables(DATABASE_PATH);
      parser = new CSVParser(DATABASE_PATH);
      com.c01_project.database.PendingDatabaseEntryInterface entry = parser.parseCSVBasicICareTemplate(TEST_ROOT + "normal_multiple_linens.csv");
      entry.dumpIntoDatabase(DATABASE_PATH);
      entry.clear();
      query = new DatabaseQuery("test.db");
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
    try {
    report.writeReport(query, "basic_data", columns);
    File expected = new File("src/test/resources/testReports/single_column.csv");
    File result = new File("testReport.csv");
    assertEquals(expected, result);
    } catch (Exception e) {
    	fail("Something unexpected happened");
    }
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
