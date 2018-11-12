package com.c01_project.database;

import com.c01_project.database.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PendingDatabaseEntryTest {

  String ret;
  static c01_project.database.PendingDatabaseEntry p;

  @BeforeAll
  public static void setup() {
    p = new c01_project.database.PendingDatabaseEntry();
  }

  @BeforeEach
  public void reset() {
    p.clear();
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
            "SET unique_identifier_value = '123456'\n" +
            "WHERE id = ADJU;\n", p.getAsSQL("test.db"), "single query query");
  }

  @Test
  @DisplayName("multiple valid to be added to the database for the same person")
  public void testMulipleEntriesGiven() {
    p.addData("ADJU", "basic_data", "unique_identifier_value", "123456");
    p.addData("ADJU", "basic_data", "date_of_birth", "99-04-23");
    p.addData("ADJU", "basic_data", "start_date_of_service", "18-11-11");
    assertEquals("UPDATE basic_data\n" +
            "SET unique_identifier_value = '123456'\n" +
            "WHERE id = ADJU;\n" +
            "UPDATE basic_data\n" +
            "SET date_of_birth = '99-04-23'\n" +
            "WHERE id = ADJU;\n" +
            "UPDATE basic_data\n" +
            "SET start_date_of_service = '18-11-11'\n" +
            "WHERE id = ADJU;\n", p.getAsSQL("test.db"), "multiple queries for one person");
  }

}
