package c01_project.gui;

@SuppressWarnings("serial")
public class InvalidFileException extends Exception {
	
	public InvalidFileException() {
		super("file is not of .csv");
	}
}
