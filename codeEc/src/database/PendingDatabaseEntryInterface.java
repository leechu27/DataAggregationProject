package database;

public interface PendingDatabaseEntryInterface {

  public void addData(String userId, String tableName, String columnName, String data);
  public void clear();
  public String dumpIntoDatabase(String databasePath);
  public String getAsSQL(String databaseName);


}
