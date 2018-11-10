package c01_project.database.selector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import c01_project.database.CSVParser;
import c01_project.database.DatabaseQuery;
import c01_project.database.PendingDatabaseEntry;


public class DatabaseSelectorTest {
  
  private final String TEST_ROOT = "src/test/resources/testICareTemplates/";
  private static CSVParser parser;
  private static DatabaseQuery query;

  @BeforeAll
  public void setUp() {
    parser = new CSVParser("test.db");
    query = new DatabaseQuery("test.db");
    PendingDatabaseEntry entry = parser.parseCSVBasicICareTemplate(TEST_ROOT + "normal_mulitple_linens.csv");
  }

  @Test
  @DisplayName("select one column")
  public void testOneColumn() {
    String[] columns = {"name"};
    List<String> expected = new ArrayList<String>();
    List<String> outcome = new ArrayList<String>();
    expected.add("Mohammed Ali");
    expected.add("Terry Suns");
    ResultSet result = DatabaseSelector.selectColumnms(query, "basic_data", columns);
    while (result.next()) {
      outcome.add(result.getString("name"));
    }
    result.close();
    assertEquals(expected, outcome);
  }

  @Test
  @DisplayName("select two columns")
  public void testTwoColumns() {

  }
  
  @Test
  @DisplayName("select * from a table")
  public void testAllColumns() {

  }
  
  @Test
  @DisplayName("select no columns")
  public void testNoColumns() {

  }
  
  @Test
  @DisplayName("select an incorrect column")
  public void test() {

  }
}
