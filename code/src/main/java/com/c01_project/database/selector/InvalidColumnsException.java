package c01_project.database.selector;

@SuppressWarnings("serial")
public class InvalidColumnsException extends Exception {

  public InvalidColumnsException() {
    super("Columns not found");
  }  
}
