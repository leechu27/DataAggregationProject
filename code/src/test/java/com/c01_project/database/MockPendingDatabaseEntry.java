package com.c01_project.database;

public class MockPendingDatabaseEntry {

  public String[][] data = {};
  public int numEntries = 0;

  public void addData(String userId, String tableName, String columnName, String data) {
    this.data[numEntries][0] = userId;
    this.data[numEntries][1] = tableName;
    this.data[numEntries][2] = columnName;
    this.data[numEntries][3] = data;
    numEntries++;
  }

  public void clear() {
    for (int i = 0; i < numEntries; i++) {
      this.data[numEntries][0] = null;
      this.data[numEntries][1] = null;
      this.data[numEntries][2] = null;
      this.data[numEntries][3] = null;
    }
  }

  public String dumpIntoDatabase() {
    return "";
  }

}
