package c01_project.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * A class for adding new row(s) to the ICare database
 */
public class PendingDatabaseEntry {

  private List<String> lines;
  private String fileType;

  /**
   * Create new entries using an ICare template
   * currently accepts: .csv
   * TODO need to accept: .xls, .xlsx
   * @param filePath
   */
  public PendingDatabaseEntry(String filePath) {
    // get the lines of the file
    try {
      lines = Files.readAllLines(Paths.get(filePath));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // get the file extension
    int i = filePath.lastIndexOf('.');
    if (i > 0) {
      fileType = filePath.substring(i+1);
    }
  }

  /**
   *  Once all of the fields you want to add are added, you can run this
   *  method to dump all of the data into the main database
   */
  public void dumpIntoDatabase() {
    if (fileType.equalsIgnoreCase("csv")) {
      String sql = "INSERT INTO " + "" +
              " (" +
                "" +
              ") VALUES " +
              "(" +
                "" +
              ")";
    }
  }

}
