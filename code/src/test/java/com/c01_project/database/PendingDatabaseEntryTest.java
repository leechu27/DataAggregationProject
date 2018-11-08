package com.c01_project.database;

import com.c01_project.database.*;
import org.junit.jupiter.api.BeforeAll;
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

  @Test
  @DisplayName("no entries to be added to the database")
  public void testNoEntriesGiven() {

    assertEquals("", p.getAsSQL("test.db"), "empty query");
  }

}
