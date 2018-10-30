package c01_project.reports;

@SuppressWarnings("serial")
public class FileNotFoundException extends Exception {
	
	public FileNotFoundException() {
		super("file does not exist at location");
	}
}
