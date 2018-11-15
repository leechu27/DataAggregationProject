package testsDatabase;

import database.PendingDatabaseEntryInterface;

public class MockPendingDatabaseEntry implements PendingDatabaseEntryInterface{

  public String[][] data = new String[100][4];
  public int numEntries = 0;

  public void addData(String userId, String tableName, String columnName, String data) {
    this.data[numEntries][0] = userId;
    this.data[numEntries][1] = tableName;
    this.data[numEntries][2] = columnName;
    this.data[numEntries][3] = data;
    numEntries++;
  }

  public void clear() {
    numEntries = 0;
  }

  @Override
  public String dumpIntoDatabase(String databasePath) {
    return null;
  }

  /**
   * For the mock testing, does not actually convert to sql,
   * it just dump out the raw data given to it, that is meant to be
   * formatted by the real class
   * @param databaseName
   * @return
   */
  @Override
  public String getAsSQL(String databaseName) {
    String ret = "";
    for (int i = 0; i < numEntries; i++) {
      String temp = data[i][0];
      for (int j = 1; j < 4; j++) {
        temp += " " + data[i][j];
      }
      ret += temp + "\n";
    }
    return ret;
  }

}
