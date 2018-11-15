package c01_project.database.selector;

public class InvalidFilterException extends Exception {

  public InvalidFilterException() {
    super("condition is of the wrong form");
  }  
}
