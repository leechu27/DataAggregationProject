package databaseSelector;

@SuppressWarnings("serial")
public class DatabaseNullException extends Exception {
  
  public DatabaseNullException() {
    super("Database not found");
  }  
}
