package c01_project.database.selector;

@SuppressWarnings("serial")
public class NullColumnsException extends Exception {

  public NullColumnsException() {
    super("Columns not found");
  }  
}
