package databaseSelector;

@SuppressWarnings("serial")
public class InvalidTableException extends Exception {

  public InvalidTableException() {
    super("Table not found");
  }  
}
