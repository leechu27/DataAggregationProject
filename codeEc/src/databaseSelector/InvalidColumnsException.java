package databaseSelector;

@SuppressWarnings("serial")
public class InvalidColumnsException extends Exception {

  public InvalidColumnsException() {
    super("Columns not found");
  }  
}
