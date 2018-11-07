package c01_project.database.selector;

@SuppressWarnings("serial")
public class NullTableException extends Exception {

  public NullTableException() {
    super("Table not found");
  }  
}
