package testsDatabaseSelector;

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

import database.CSVParser;
import database.DatabaseQuery;
import database.databaseSetup;
import databaseSelector.DatabaseNullException;
import databaseSelector.DatabaseSelector;
import databaseSelector.InvalidColumnsException;
import databaseSelector.InvalidFilterException;
import databaseSelector.InvalidTableException;


public class DatabaseSelectorTest {
  
  private final static String DATABASE_PATH = "test_resources/testDatabases/test.db";
  private static DatabaseQuery query;

  @BeforeAll
  public static void beforeAll() {
    query = new DatabaseQuery(DATABASE_PATH);
  }
  
  @Test
  @DisplayName("select one row")
  public void testOneRow() {
    String condition = "unique_identifier_value = 12345";
    try {
      int result = DatabaseSelector.countRows(query, "basic_data", condition);
      assertEquals(1, result);
    } catch (SQLException sql) {
      fail(sql.getMessage());
    }
  }
  
  @Test
  @DisplayName("select no rowd")
  public void testNoRows() {
    String condition = "unique_identifier_value < 10000 OR unique_identifier_value > 20000";
    try {
      int result = DatabaseSelector.countRows(query, "basic_data", condition);
      assertEquals(0, result);
    } catch (SQLException sql) {
      fail(sql.getMessage());
    }
  }
  
  @Test
  @DisplayName("select more than one row")
  public void testSeveralRows() {
    String condition = "unique_identifier_value > 10000 AND unique_identifier_value < 20000";
    try {
      int result = DatabaseSelector.countRows(query, "basic_data", condition);
      // only 2 entries in test database
      assertEquals(2, result);
    } catch (SQLException sql) {
      fail(sql.getMessage());
    }
  }
}
