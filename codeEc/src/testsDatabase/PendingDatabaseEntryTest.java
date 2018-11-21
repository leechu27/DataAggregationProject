package testsDatabase;

import database.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PendingDatabaseEntryTest {

  String ret;
  static database.PendingDatabaseEntry p;

  @BeforeAll
  public static void setup() {
    p = new database.PendingDatabaseEntry();
  }

  @BeforeEach
  public void reset() {
    p = new database.PendingDatabaseEntry();
  }

  @Test
  @DisplayName("no entries to be added to the database")
  public void testNoEntriesGiven() {
    assertEquals("", p.getAsSQL("test.db"), "empty query");
  }

  @Test
  @DisplayName("one valid entry to be added")
  public void testSingleValidData() {
    p.addData("ADJU", "basic_data", "unique_identifier_value", "123456");
    assertEquals("UPDATE basic_data\n" + 
    		"Set unique_identifier_value = '123456'\n" + 
    		"WHERE client_validation_id = ADJU;\n" + 
    		"", p.getAsSQL("test.db"), "single query");
  }

  @Test
  @DisplayName("multiple valid to be added to the database for the same person")
  public void testMulipleEntriesGiven() {
    p.addData("ADJU", "basic_data", "unique_identifier_value", "123456");
    p.addData("ADJU", "basic_data", "date_of_birth", "99-04-23");
    p.addData("ADJU", "basic_data", "start_date_of_service", "18-11-11");
    assertEquals("UPDATE basic_data\n" + 
    		"Set date_of_birth = '99-04-23'\n" + 
    		"WHERE client_validation_id = ADJU;\n" + 
    		"UPDATE basic_data\n" + 
    		"Set unique_identifier_value = '123456'\n" + 
    		"WHERE client_validation_id = ADJU;\n" + 
    		"UPDATE basic_data\n" + 
    		"Set start_date_of_service = '18-11-11'\n" + 
    		"WHERE client_validation_id = ADJU;\n" + 
    		"", p.getAsSQL("test.db"), "multiple queries for one person");
  }

  @Test
  @DisplayName("multiple valid to be added to the database for the different person")
  public void testMulipleEntriesGiven1() {
    p.addData("ADJU", "basic_data", "unique_identifier_value", "123456");
    p.addData("ADJU", "basic_data", "date_of_birth", "99-04-23");
    p.addData("BLAS", "basic_data", "date_of_birth", "99-04-23");
    p.addData("BLAS", "basic_data", "start_date_of_service", "18-11-11");
    assertEquals("UPDATE basic_data\n" + 
    		"Set date_of_birth = '99-04-23'\n" + 
    		"WHERE client_validation_id = ADJU;\n" + 
    		"UPDATE basic_data\n" + 
    		"Set unique_identifier_value = '123456'\n" + 
    		"WHERE client_validation_id = ADJU;\n" + 
    		"UPDATE basic_data\n" + 
    		"Set date_of_birth = '99-04-23'\n" + 
    		"WHERE client_validation_id = BLAS;\n" + 
    		"UPDATE basic_data\n" + 
    		"Set start_date_of_service = '18-11-11'\n" + 
    		"WHERE client_validation_id = BLAS;\n" + 
    		"", p.getAsSQL("test.db"), "multiple queries for one person");
  }

}
