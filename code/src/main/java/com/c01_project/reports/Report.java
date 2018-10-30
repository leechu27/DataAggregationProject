package c01_project.reports;

import c01_project.gui.FileExistsException;
import c01_project.gui.InvalidFileException;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class Report {

	private String location;
	private String name;
	private final String fileRegex = ".+[.]csv";

	/* Constructor for report, creates a file to put the report in given that the file does not exist yet
	 * 
	 * @param report name that includes .csv at the end
	 * @param file path from root directory to destination (include "\" at the end)
	 * @throws InvalidFileException if file name is not of .csv format
	 * @throws FileExistsException if file name is linked to an existing file
	 */
	public Report(String location, String name) throws c01_project.gui.InvalidFileException, c01_project.gui.FileExistsException {
		if (name.matches(fileRegex)) {
			
			File file = new File(location + name);
			if (file.exists()) {
				throw new FileExistsException();
			}
			try {
				if (file.createNewFile()) {
					this.location = location;
					this.name = name;
				} else {
					throw new FileExistsException();
				}
			} catch (IOException e ) {
				throw new FileExistsException();
			}
			
		} else {
			throw new InvalidFileException();
		}
	}
	
	/* updates the file with the data from the database
	*
	* @throws FileNotFoundException if the original file is missing
	*/
	public void update() throws FileNotFoundException{
		
	}
	
	/* writes the given String into the report file, returns true if successful
	*
	* @throws FileNotFoundException if the original file is missing
	*/
	private boolean writeToFile(String data) throws FileNotFoundException{
		try {
			File file = new File(location + name);
			if (file.exists()) {
				FileWriter writer = new FileWriter(file, false);
				writer.write(data);
				writer.close();
			} else {
				throw new FileNotFoundException();
			}
		}
		catch (IOException e) {
			return false;
		}
		return true;
	}
	
	/*
	public static void main(String[] args) {
		try {
			Report test = new Report("D:\\Users\\tophe\\Team26\\code\\src\\main\\java\\com\\c01_project\\reports\\", "test.csv");
			test.writeToFile("WILL YOU SURVIVE?");
			System.out.println("done :)");
		} catch (Exception e) {
			System.out.println("OOPSIE WOOPSIE!! Uwu We made a fucky wucky!! A wittle fucko boingo! The code monkeys at our headquarters are working VEWY HAWD to fix this!");
		}
	}*/
}
