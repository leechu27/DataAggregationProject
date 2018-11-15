package gui;

@SuppressWarnings("serial")
public class FileExistsException extends Exception {
	
	public FileExistsException() {
		super("file already exists");
	}
}
