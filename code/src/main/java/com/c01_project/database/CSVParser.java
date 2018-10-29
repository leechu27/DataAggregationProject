package c01_project.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVParser {

  // Where the primary ID used to identify each user can be found in the csv
  private static final int PRIMARY_ID_COLUMN = 0;
  private String databasePath;

  public CSVParser(String databasePath) {
    this.databasePath = databasePath;
  }

  /**
   * reads the given csv file for
   * @param filename
   * @return the response given by the database
   */
  public String parseCSVBasicICareTemplate(String filename) {

    PendingDatabaseEntry entry = new PendingDatabaseEntry();

    String csvFile = filename;
    BufferedReader br = null;
    String line = "";
    String csvSplitBy = ",";

    String[] columnNames;
    ArrayList<String[]> lines;

    try {

      // getting the names of all the columns
      br = new BufferedReader(new FileReader(csvFile));
      columnNames = br.readLine().split(csvSplitBy);
      lines = new ArrayList<>();

      // reading the csv
      while ((line = br.readLine()) != null) {
        String[] patient = line.split(csvSplitBy);
        lines.add(patient);
      }

      // add the actual data
      for (String[] user: lines) {
        String id = user[PRIMARY_ID_COLUMN];
        for (int i = 0; i < columnNames.length; i++) {
          if (i != PRIMARY_ID_COLUMN)
            entry.addData(id, "basic_data", columnNames[i], user[i]);
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
    return entry.dumpIntoDatabase(databasePath);
  }

}
