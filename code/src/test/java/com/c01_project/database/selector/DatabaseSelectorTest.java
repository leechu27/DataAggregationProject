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
	  ResultSet result = c01_project.database.selector.DatabaseSelector.selectColumns(query, "basic_data", columns);
	  while (result.next()) {
	    outcome.add(result.getString("name"));
		}
	  result.close();
	  assertEquals(expected, outcome);
	} catch (SQLException sql) {
	  fail(sql.getMessage());
	} catch (c01_project.database.selector.DatabaseNullException dne) {
	  fail("Database is null");
	} catch (c01_project.database.selector.InvalidColumnsException nce) {
	  fail("Columns are invalid");
	} catch (c01_project.database.selector.InvalidTableException nte) {
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
      fail(sql.getMessage());
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
  
  @Test
  @DisplayName("select * and other columns (syntax error)")
  public void testStarColumnsError() {
    List<String> columns = new ArrayList<String>();
    columns.add("*");
    columns.add("name");
    assertThrows(InvalidColumnsException.class, ()->{DatabaseSelector.selectColumns(query, "basic_data", columns);});
  }
  
  @Test
  @DisplayName("select * from a table")
  public void testAllColumns() {
    List<String> columns = new ArrayList<String>();
    columns.add("*");
    List<List<String>> expected = new ArrayList<List<String>>();
    List<List<String>> outcome = new ArrayList<List<String>>();
    List<String> expectedRow1 = new ArrayList<String>();
    List<String> expectedRow2 = new ArrayList<String>();
    List<String> outputRow1 = new ArrayList<String>();
    List<String> outputRow2 = new ArrayList<String>();
    expectedRow1.add("1");
    expectedRow1.add("Mohammed Ali");
    expectedRow1.add("MALI");
    expectedRow1.add("12345");
    expectedRow1.add("1995-02-31");
    expectedRow1.add("2018-05-20");
    expectedRow1.add("English");
    expectedRow1.add("English");
    expectedRow2.add("2");
    expectedRow2.add("Terry Suns");
    expectedRow2.add("TSUN");
    expectedRow2.add("12346");
    expectedRow2.add("1990-05-04");
    expectedRow2.add("2018-03-21");
    expectedRow2.add("English");
    expectedRow2.add("French");
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
          outputRow1.add(Integer.toString(result.getInt("id")));
          outputRow1.add(result.getString("name"));
          outputRow1.add(result.getString("unique_identifier"));
          outputRow1.add(result.getString("unique_identifier_value"));
          outputRow1.add(result.getString("date_of_birth"));
          outputRow1.add(result.getString("start_date_of_service"));
          outputRow1.add(result.getString("language_of_service"));
          outputRow1.add(result.getString("language_of_preference"));
          outcome.add(outputRow1);
          i++;
        } else if (i == 1) {
          outputRow2.add(Integer.toString(result.getInt("id")));
          outputRow2.add(result.getString("name"));
          outputRow2.add(result.getString("unique_identifier"));
          outputRow2.add(result.getString("unique_identifier_value"));
          outputRow2.add(result.getString("date_of_birth"));
          outputRow2.add(result.getString("start_date_of_service"));
          outputRow2.add(result.getString("language_of_service"));
          outputRow2.add(result.getString("language_of_preference"));
          outcome.add(outputRow2);
          i++;
        }
      }
      result.close();
      assertEquals(expected, outcome);
    } catch (SQLException sql) {
      fail(sql.getMessage());
    } catch (DatabaseNullException dne) {
      fail("Database is null");
    } catch (InvalidColumnsException nce) {
      fail("Columns are invalid");
    } catch (InvalidTableException nte) {
      fail("table is invalid");
    }
  }
  
  @Test
  @DisplayName("select one column with a condition, version 1")
  public void testOneColumnFilter1() {
    List<String> columns = new ArrayList<String>();
    columns.add("name");
    List<String> expected = new ArrayList<String>();
    List<String> outcome = new ArrayList<String>();
    expected.add("Mohammed Ali");
    expected.add("Terry Suns");
    try {
      ResultSet result = DatabaseSelector.selectColumns(query, "basic_data", columns, "language_of_service = 'English'");
      while (result.next()) {
        outcome.add(result.getString("name"));
        }
      result.close();
      assertEquals(expected, outcome);
    } catch (SQLException sql) {
      fail(sql.getMessage());
    } catch (DatabaseNullException dne) {
      fail("Database is null");
    } catch (InvalidColumnsException nce) {
      fail("Columns are invalid");
    } catch (InvalidTableException nte) {
      fail("table is invalid");
    }  catch (InvalidFilterException nfe) {
      fail("filter is invalid");
    }
  }
  
  @Test
  @DisplayName("select one column with a condition, version 2")
  public void testOneColumnFilter2() {
    List<String> columns = new ArrayList<String>();
    columns.add("name");
    List<String> expected = new ArrayList<String>();
    List<String> outcome = new ArrayList<String>();
    expected.add("Mohammed Ali");
    try {
      ResultSet result = DatabaseSelector.selectColumns(query, "basic_data", columns, "language_of_preference = 'English'");
      while (result.next()) {
        outcome.add(result.getString("name"));
        }
      result.close();
      assertEquals(expected, outcome);
    } catch (SQLException sql) {
      fail(sql.getMessage());
    } catch (DatabaseNullException dne) {
      fail("Database is null");
    } catch (InvalidColumnsException nce) {
      fail("Columns are invalid");
    } catch (InvalidTableException nte) {
      fail("table is invalid");
    }  catch (InvalidFilterException nfe) {
      fail("filter is invalid");
    }
  }
  
  @Test
  @DisplayName("select two columns with filter, version 1")
  public void testTwoColumnsWithFilter1() {
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
      ResultSet result = DatabaseSelector.selectColumns(query, "basic_data", columns, "language_of_service = 'English'");
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
      fail(sql.getMessage());
    } catch (DatabaseNullException dne) {
      fail("Database is null");
    } catch (InvalidColumnsException nce) {
      fail("Columns are invalid");
    } catch (InvalidTableException nte) {
      fail("table is invalid");
    } catch (InvalidFilterException nfe) {
      fail("filter is invalid");
    }
  }
  
  @Test
  @DisplayName("select two columns with filter, version 2")
  public void testTwoColumnsWithFilter2() {
    List<String> columns = new ArrayList<String>();
    columns.add("name");
    columns.add("date_of_birth");
    List<List<String>> expected = new ArrayList<List<String>>();
    List<List<String>> outcome = new ArrayList<List<String>>();
    List<String> expectedRow = new ArrayList<String>();
    List<String> outputRow = new ArrayList<String>();
    expectedRow.add("Mohammed Ali");
    expectedRow.add("1995-02-31");
    expected.add(expectedRow);
    try {
      ResultSet result = DatabaseSelector.selectColumns(query, "basic_data", columns, "language_of_preference = 'English'");
      if (result == null) {
        fail("no resultset found");
      }
      int i = 0;
      while (result.next()) {
        outputRow.add(result.getString("name"));
        outputRow.add(result.getString("date_of_birth"));
        outcome.add(outputRow);
      }
      result.close();
      assertEquals(expected, outcome);
    } catch (SQLException sql) {
      fail(sql.getMessage());
    } catch (DatabaseNullException dne) {
      fail("Database is null");
    } catch (InvalidColumnsException nce) {
      fail("Columns are invalid");
    } catch (InvalidTableException nte) {
      fail("table is invalid");
    } catch (InvalidFilterException nfe) {
      fail("filter is invalid");
    }
  }
}
