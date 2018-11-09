package com.c01_project.database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvParserTest {

  private final String TEST_ROOT = "src/test/resources/testICareTemplates/";
  private static c01_project.database.CSVParser c;


  @BeforeAll
  public static void beforeAll() {
    MockPendingDatabaseEntry p = new MockPendingDatabaseEntry();
    p.clear();
    c = new c01_project.database.CSVParser("test.db", p);
  }

  @Test
  @DisplayName("empty normal csv template")
  public void testEmptyICareTemplate() {
    com.c01_project.database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_no_lines.csv");
    assertEquals("", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("normal csv template with one line")
  public void testICareTemplateOneLine() {
    com.c01_project.database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_single_line.csv");
    assertEquals("", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("normal csv template with multiple lines")
  public void testICareTemplateMultipleLines() {
    com.c01_project.database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_multiple_linens.csv");
    assertEquals("", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("one line csv template with missing attributes")
  public void testMissingAttributes() {
    com.c01_project.database.PendingDatabaseEntryInterface p =
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
    com.c01_project.database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "missing_unique_identifier.csv");
    assertEquals("", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test a file where only the first line with the main identifier missing")
  public void testBrokenInfoOnlyOnFirstLine() {
    com.c01_project.database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "missing_info_on_first_line_but_rest_fine.csv");
    assertEquals("", p.getAsSQL("test.db"));
  }


}
