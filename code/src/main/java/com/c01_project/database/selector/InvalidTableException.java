package c01_project.database.selector;

@SuppressWarnings("serial")
public class InvalidTableException extends Exception {

  public InvalidTableException() {
    super("Table not found");
  }  
}
