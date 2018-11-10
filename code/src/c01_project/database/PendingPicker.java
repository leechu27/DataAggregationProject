package c01_project.database;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.Object;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class PendingPicker {
	public static void main(String[] args) throws IOException {
		// ignore this, I used this to test my function
		// System.getProperty("user.dir") gets the current working directory
		String fromlocation = System.getProperty("user.dir") + "/src/team26";
		String tolocation = System.getProperty("user.dir") + "/src/team26/test";
		String filename = "test2.csv";
		
		
		picker(fromlocation, tolocation, filename);
	}
	/* This method copies a file with filename in fromlocation to tolocation
	 * 
	 * @param fromlocation - the location of the file to be copied
	 * @param tolocation - the location of duplicate file
	 * @param filename - the name of the file to be copied
	 * @throws IOException - required for IO operations
	 */
	public static void picker(String fromlocation, String tolocation,String filename) throws IOException {
		if (filename.endsWith(".csv")) {
			// initialize folder and location variables
			File folder = new File(fromlocation);
			Path from = Paths.get(fromlocation + "/" + filename);
			Path to = Paths.get(tolocation + "/" + filename);
			
			// Looks for file and moves it if found otherwise does nothing
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
			  if (listOfFiles[i].isFile()) {
			    if (listOfFiles[i].getName().equals(filename)) {
			    	Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
			    }
			  }
			}
		}
	}
}
