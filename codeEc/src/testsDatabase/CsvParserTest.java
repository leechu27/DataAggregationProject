package testsDatabase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvParserTest {

  private final String TEST_ROOT = "src/test/resources/testICareTemplates/";
  private static database.CSVParser c;
  private static MockPendingDatabaseEntry p;


  @BeforeAll
  public static void beforeAll() {
    p = new MockPendingDatabaseEntry();
    c = new database.CSVParser("test.db", p);
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
    assertEquals("MALI basic_data name Mohammed Ali\n" +
            "MALI basic_data unique_identifier_value 12345\n" +
            "MALI basic_data date_of_birth 1995-02-31\n" +
            "MALI basic_data start_date_of_service 2018-05-20\n" +
            "MALI basic_data language_of_service English\n" +
            "MALI basic_data language_of_preference English\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("normal csv template with multiple lines")
  public void testICareTemplateMultipleLines() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_multiple_linens.csv");
    assertEquals("MALI basic_data name Mohammed Ali\n" +
            "MALI basic_data unique_identifier_value 12345\n" +
            "MALI basic_data date_of_birth 1995-02-31\n" +
            "MALI basic_data start_date_of_service 2018-05-20\n" +
            "MALI basic_data language_of_service English\n" +
            "MALI basic_data language_of_preference English\n" +
            "TSUN basic_data name Terry Suns\n" +
            "TSUN basic_data unique_identifier_value 12346\n" +
            "TSUN basic_data date_of_birth 1990-05-04\n" +
            "TSUN basic_data start_date_of_service 2018-03-21\n" +
            "TSUN basic_data language_of_service English\n" +
            "TSUN basic_data language_of_preference French\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("one line csv template with missing attributes")
  public void testMissingAttributes() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_one_line_empty_fields.csv");
    assertEquals("MALI basic_data name Mohammed Ali\n" +
            "MALI basic_data unique_identifier_value 12345\n" +
            "MALI basic_data date_of_birth \n" +
            "MALI basic_data start_date_of_service 2018-05-20\n" +
            "MALI basic_data language_of_service \n" +
            "MALI basic_data language_of_preference English\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test a line with the main identifier missing")
  public void testBrokenInfo() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "missing_unique_identifier.csv");
    assertEquals(" basic_data name Mohammed Ali\n" +
            " basic_data unique_identifier_value 12345\n" +
            " basic_data date_of_birth 1995-02-31\n" +
            " basic_data start_date_of_service 2018-05-20\n" +
            " basic_data language_of_service English\n" +
            " basic_data language_of_preference English\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test a file where only the first line with the main identifier missing")
  public void testBrokenInfoOnlyOnFirstLine() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "missing_info_on_first_line_but_rest_fine.csv");
    assertEquals(" basic_data name Mohammed Ali\n" +
            " basic_data unique_identifier_value 12345\n" +
            " basic_data date_of_birth 1995-02-31\n" +
            " basic_data start_date_of_service 2018-05-20\n" +
            " basic_data language_of_service English\n" +
            " basic_data language_of_preference English\n" +
            "TSUN basic_data name Terry Suns\n" +
            "TSUN basic_data unique_identifier_value 12346\n" +
            "TSUN basic_data date_of_birth 1990-05-04\n" +
            "TSUN basic_data start_date_of_service 2018-03-21\n" +
            "TSUN basic_data language_of_service English\n" +
            "TSUN basic_data language_of_preference French\n", p.getAsSQL("test.db"));
  }


}
