package database;

import database.PendingDatabaseEntryInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

  // Where the primary ID used to identify each user can be found in the csv
<<<<<<< HEAD
  private int PRIMARY_ID_COLUMN = 0;
=======
  private static int PRIMARY_ID_COLUMN = 0;
>>>>>>> master
  private static final String PRIMARY_ID_COL_NAME = "client_validation_id";
  private static final String DEFAULT_TABLE = "client_profile";
  private PendingDatabaseEntryInterface pde;

  public CSVParser() {
    this.pde = new database.PendingDatabaseEntry();
  }

  public CSVParser(PendingDatabaseEntryInterface i) {
    this.pde = i;
  }
  
  
  /**
   * Reads the csv file with the Team26 format ICare template in it.
   * 
   * In order for this to work, the column names must match exactly to
   * the database schema, or else this will return errors. The following is the format of csvFiles
   * 
   * First line must be the name of the table, refer to the schema for this.
   * Second line must be the column names that are to be added. The first column must contain be the user's
   * unique id, though the rest of the column names only need to contain the ones that were actually changed
   * 
   * The following lines contain one user's information in it.
   * 
   * @param csvFile the path to the csvFile
   * @return the databaseEntryInterface that needs to be checked for conflicts
   */
  public PendingDatabaseEntryInterface parseCSVBasicICareTemplate(String csvFile) {
	  
	  PendingDatabaseEntryInterface entry = pde;

	    BufferedReader br = null;
	    String line = "";
	    String csvSplitBy = ",";

	    String tableName;
	    String[] columnNames;
	    ArrayList<String[]> lines;
<<<<<<< HEAD
=======
	    
>>>>>>> master

	    try {

	      // getting the names of all the columns
	      br = new BufferedReader(new FileReader(csvFile));
	      tableName = br.readLine();
	      columnNames = br.readLine().split(csvSplitBy);
	      
	      // get the location of the id column that is to be matched with
	      for (int i = 0; i < columnNames.length; i++) {
	    	  if (columnNames[i].equals(PRIMARY_ID_COL_NAME)) {
	    		  PRIMARY_ID_COLUMN = i;
	    		  break;
	    	  }
	      }
	    		  
	      lines = new ArrayList<>();

	      // reading the csv
	      while ((line = br.readLine()) != null) {
<<<<<<< HEAD
	    	  if (!line.equals("") && (line.split(csvSplitBy).length == columnNames.length)) {
	    		  String[] patient = line.split(csvSplitBy);  
	    		  lines.add(patient);
	    	  }
	        // TODO need to add escape sequences. This is probably easier done with a library another time
=======
	        String[] patient = line.split(csvSplitBy);
	        
	        // TODO need to add escape sequences. This is probably easier done with a library another time
	        
	        lines.add(patient);
>>>>>>> master
	      }

	      // add the actual data
	      for (String[] user: lines) {
	        String id = user[PRIMARY_ID_COLUMN];
	        for (int i = 0; i < columnNames.length; i++) {
	          if (i != PRIMARY_ID_COLUMN)
	            entry.addData(id, tableName, columnNames[i], user[i]);
	        }
	      }

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally { // make sure to close the file Regardless of whether there was an error
	      if (br != null) {
	        try {
	          br.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    return entry;
  }
}
