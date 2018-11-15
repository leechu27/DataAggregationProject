package c01_project.database.selector;

@SuppressWarnings("serial")
public class DatabaseNullException extends Exception {
  
  public DatabaseNullException() {
    super("Database not found");
  }  
}
