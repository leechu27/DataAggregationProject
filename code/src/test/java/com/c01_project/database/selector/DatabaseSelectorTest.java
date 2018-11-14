package c01_project.database.selector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import c01_project.database.CSVParser;
import c01_project.database.DatabaseQuery;
import c01_project.database.databaseSetup;


public class DatabaseSelectorTest {
  
  private final static String DATABASE_PATH = "src/test/resources/testDatabases/test.db";
  private static DatabaseQuery query;

  @BeforeAll
  public static void beforeAll() {
    query = new DatabaseQuery(DATABASE_PATH);
  }

  @Test
  @DisplayName("select one column")
  public void testOneColumn() {
    List<String> columns = new ArrayList<String>();
    columns.add("name");
    List<String> expected = new ArrayList<String>();
    List<String> outcome = new ArrayList<String>();
    expected.add("Mohammed Ali");
    expected.add("Terry Suns");
	try {
	  ResultSet result = DatabaseSelector.selectColumns(query, "basic_data", columns);
	  while (result.next()) {
	    outcome.add(result.getString("name"));
		}
	  result.close();
	  assertEquals(expected, outcome);
	} catch (SQLException sql) {
	  fail("Unexpected SQLException has been thrown");
	} catch (DatabaseNullException dne) {
	  fail("Database is null");
	} catch (InvalidColumnsException nce) {
	  fail("Columns are invalid");
	} catch (InvalidTableException nte) {
	  fail("table is invalid");
	}
  }

  @Test
  @DisplayName("select two columns")
  public void testTwoColumns() {
    List<String> columns = new ArrayList<String>();
    columns.add("name");
    columns.add("date_of_birth");
    List<List<String>> expected = new ArrayList<List<String>>();
    List<List<String>> outcome = new ArrayList<List<String>>();
    List<String> expectedRow1 = new ArrayList<String>();
    List<String> expectedRow2 = new ArrayList<String>();
    List<String> outputRow1 = new ArrayList<String>();
    List<String> outputRow2 = new ArrayList<String>();
    expectedRow1.add("Mohammed Ali");
    expectedRow1.add("1995-02-31");
    expectedRow2.add("Terry Suns");
    expectedRow2.add("1990-05-04");
    expected.add(expectedRow1);
    expected.add(expectedRow2);
    try {
      ResultSet result = DatabaseSelector.selectColumns(query, "basic_data", columns);
      if (result == null) {
        fail("no resultset found");
      }
      int i = 0;
      while (result.next()) {
        if (i == 0) {
          outputRow1.add(result.getString("name"));
          outputRow1.add(result.getString("date_of_birth"));
          outcome.add(outputRow1);
          i++;
        } else if (i == 1) {
          outputRow2.add(result.getString("name"));
          outputRow2.add(result.getString("date_of_birth"));
          outcome.add(outputRow2);
          i++;
        }
      }
      result.close();
      assertEquals(expected, outcome);
    } catch (SQLException sql) {
      fail("Unexpected SQLException has been thrown");
    } catch (DatabaseNullException dne) {
      fail("Database is null");
    } catch (InvalidColumnsException nce) {
      fail("Columns are invalid");
    } catch (InvalidTableException nte) {
      fail("table is invalid");
    }
  }
  
  @Test
  @DisplayName("select no columns")
  public void testNoColumns() {
    List<String> columns = new ArrayList<String>();
    assertThrows(InvalidColumnsException.class, ()->{DatabaseSelector.selectColumns(query, "basic_data", columns);});
  }
  
  /*@Test
  @DisplayName("select * from a table")
  public void testAllColumns() {
    
  }*/
  
  /*
  @Test
  @DisplayName("select an incorrect column")
  public void test() {

  }
  */
}
