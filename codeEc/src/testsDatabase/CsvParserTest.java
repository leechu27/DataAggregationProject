package testsDatabase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvParserTest {

  private final String TEST_ROOT = "test_resources/testICareTemplates/";
  private final String REAL_SAMPLES_ROOT = "test_resources/csv";
  
  private static database.CSVParser c;
  private static MockPendingDatabaseEntry p;


  @BeforeAll
  public static void beforeAll() {
    p = new MockPendingDatabaseEntry();
    c = new database.CSVParser(p);
  }

  @BeforeEach
  public void reset() {
    p.clear();
  }

  @Test
  @DisplayName("empty normal csv template")
  public void testEmptyICareTemplate() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_no_lines.csv");
    assertEquals("", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("normal csv template with one line")
  public void testICareTemplateOneLine() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_single_line.csv");
    assertEquals("MALI Client_Profile name Mohammed Ali\n" +
            "MALI Client_Profile unique_identifier_value 12345\n" +
            "MALI Client_Profile date_of_birth 1995-02-31\n" +
            "MALI Client_Profile start_date_of_service 2018-05-20\n" +
            "MALI Client_Profile language_of_service English\n" +
            "MALI Client_Profile language_of_preference English\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("normal csv template with multiple lines")
  public void testICareTemplateMultipleLines() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_multiple_linens.csv");
    assertEquals("MALI Client_Profile name Mohammed Ali\n" +
            "MALI Client_Profile unique_identifier_value 12345\n" +
            "MALI Client_Profile date_of_birth 1995-02-31\n" +
            "MALI Client_Profile start_date_of_service 2018-05-20\n" +
            "MALI Client_Profile language_of_service English\n" +
            "MALI Client_Profile language_of_preference English\n" +
            "TSUN Client_Profile name Terry Suns\n" +
            "TSUN Client_Profile unique_identifier_value 12346\n" +
            "TSUN Client_Profile date_of_birth 1990-05-04\n" +
            "TSUN Client_Profile start_date_of_service 2018-03-21\n" +
            "TSUN Client_Profile language_of_service English\n" +
            "TSUN Client_Profile language_of_preference French\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("one line csv template with missing attributes")
  public void testMissingAttributes() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_one_line_empty_fields.csv");
    assertEquals("MALI Client_Profile name Mohammed Ali\n" +
            "MALI Client_Profile unique_identifier_value 12345\n" +
            "MALI Client_Profile date_of_birth \n" +
            "MALI Client_Profile start_date_of_service 2018-05-20\n" +
            "MALI Client_Profile language_of_service \n" +
            "MALI Client_Profile language_of_preference English\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test a line with the main identifier missing")
  public void testBrokenInfo() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "missing_unique_identifier.csv");
    assertEquals(" Client_Profile name Mohammed Ali\n" +
            " Client_Profile unique_identifier_value 12345\n" +
            " Client_Profile date_of_birth 1995-02-31\n" +
            " Client_Profile start_date_of_service 2018-05-20\n" +
            " Client_Profile language_of_service English\n" +
            " Client_Profile language_of_preference English\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test a file where only the first line with the main identifier missing")
  public void testBrokenInfoOnlyOnFirstLine() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "missing_info_on_first_line_but_rest_fine.csv");
    assertEquals(" Client_Profile name Mohammed Ali\n" +
            " Client_Profile unique_identifier_value 12345\n" +
            " Client_Profile date_of_birth 1995-02-31\n" +
            " Client_Profile start_date_of_service 2018-05-20\n" +
            " Client_Profile language_of_service English\n" +
            " Client_Profile language_of_preference English\n" +
            "TSUN Client_Profile name Terry Suns\n" +
            "TSUN Client_Profile unique_identifier_value 12346\n" +
            "TSUN Client_Profile date_of_birth 1990-05-04\n" +
            "TSUN Client_Profile start_date_of_service 2018-03-21\n" +
            "TSUN Client_Profile language_of_service English\n" +
            "TSUN Client_Profile language_of_preference French\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test a file where only the first line with the main identifier missing")
  public void testRealCSVTemplate() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "missing_info_on_first_line_but_rest_fine.csv");
    assertEquals(" Client_Profile name Mohammed Ali\n" +
            " Client_Profile unique_identifier_value 12345\n" +
            " Client_Profile date_of_birth 1995-02-31\n" +
            " Client_Profile start_date_of_service 2018-05-20\n" +
            " Client_Profile language_of_service English\n" +
            " Client_Profile language_of_preference English\n" +
            "TSUN Client_Profile name Terry Suns\n" +
            "TSUN Client_Profile unique_identifier_value 12346\n" +
            "TSUN Client_Profile date_of_birth 1990-05-04\n" +
            "TSUN Client_Profile start_date_of_service 2018-03-21\n" +
            "TSUN Client_Profile language_of_service English\n" +
            "TSUN Client_Profile language_of_preference French\ndaq", p.getAsSQL("test.db"));
  }


}
