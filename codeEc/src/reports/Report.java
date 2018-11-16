package reports;

import java.io.File;

public class Report {

	private String location;
	private String name;
	private String fileRegex = ".+[.]csv";

	/* Constructor for report 
	 * 
	 * @param report name as a .csv file
	 * @param file path from root directory to destination
	 * @throws InvalidFileException if file name is not of .csv format
	 * @throws FileExistsException if file name is linked to an existing file
	 */
	public Report(String name, String location) throws gui.InvalidFileException, gui.FileExistsException {
		if (name.matches(fileRegex)) {
			
			File file = new File(location + name);
			if (file.exists()) {
				throw new gui.FileExistsException();
			}
			this.location = location;
			this.name = name;
		}
		throw new gui.InvalidFileException();
	}
	
	/*public static void main(String[] args) {
	}*/
}
