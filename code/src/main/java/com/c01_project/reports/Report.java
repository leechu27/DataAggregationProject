package c01_project.reports;

import c01_project.database.DatabaseQuery;
import c01_project.gui.FileExistsException;
import c01_project.gui.InvalidFileException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	/* writes the requested data from a table in the database to the report file
	*
	* @param database the database file to gather the data from
	* @param table the table name from the database
	* @param columns of the table
	* @throws FileNotFoundException if the report file is missing
	*/
	public void writeReport(DatabaseQuery database, String table, String[] columns) throws FileNotFoundException{
		File file = new File(location + name);
		if (!(file.exists())) {
		    throw new FileNotFoundException();
		}
		String command;
		command = "SELECT * FROM " + table + ";";
		ResultSet data = database.queryWithSQL(command);
		writeToFile(data, columns);
		try {
		    data.close();
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/* writes the given String into the report file, returns true if successful
	*
	* @throws FileNotFoundException if the original file is missing
	*/
	private boolean writeToFile(ResultSet data, String[] columns) throws FileNotFoundException{
		try {
			File file = new File(location + name);
			if (!file.exists()) {
				throw new FileNotFoundException();
			}
			
			FileWriter writer = new FileWriter(file, false);
			while (data.next()) {
			    for (String column : columns) {
			        String entry = data.getString(column);
			        writer.write( entry + ",");
			    }
                writer.write("\n");;
			}
            writer.close();
		} catch (IOException e) {
			return false;
		} catch (SQLException sql) {
		    sql.printStackTrace();
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
