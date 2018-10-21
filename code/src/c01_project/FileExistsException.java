package c01_project;

@SuppressWarnings("serial")
public class FileExistsException extends Exception {
	
	public FileExistsException() {
		super("file already exists");
	}
}
