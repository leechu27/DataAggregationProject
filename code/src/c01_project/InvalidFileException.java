package c01_project;

@SuppressWarnings("serial")
public class InvalidFileException extends Exception {
	
	public InvalidFileException() {
		super("file is not of .csv");
	}
}
