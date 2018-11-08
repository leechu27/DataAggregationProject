package com.c01_project.database;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.CsvParser;

public class CsvParserTest {

  static c01_project.database.CsvParser c;


  @BeforeAll
  public void setup() {
    c = new c01_project.database.CsvParser("test.db");
  }

  @Test
  @DisplayName("empty normal csv template")
  public void testEmptyICareTemplate() {

  }

  @Test
  @DisplayName("normal csv template with one line")
  public void testICareTemplateOneLine() {

  }

  @Test
  @DisplayName("normal csv template with multiple lines")
  public void testICareTemplateMultipleLines() {

  }

  @Test
  @DisplayName("one line csv template with missing attributes")
  public void testMissingAttributes() {

  }

  @Test
  @DisplayName("multiple line csv with missing attributes")
  public void testMultipleMissingAttributes() {

  }



}
