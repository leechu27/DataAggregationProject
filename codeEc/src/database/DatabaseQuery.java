package database;

import java.sql.*;

public class DatabaseQuery {

  private Connection connection;
  private String path;

  public DatabaseQuery(String pathToDatabase) {
    path = pathToDatabase;
    connection = connect(pathToDatabase);
  }


  /**
   * Connects to the sqlite server
   * @return a connection to the server
   */
  private Connection connect(String path) {
    // SQLite connection string
    String url = "jdbc:sqlite:" + path;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  /**
   * queries the database
   * @param pathToDatabase the path to the SQLite database
   */
  public ResultSet queryWithSQL(String rawSQL) {

    ResultSet output = null;
    // make sure the programmer is not trying to make anything dangerous
    // select will never
    assert rawSQL.startsWith("SELECT") || rawSQL.startsWith("Select") ||
            rawSQL.startsWith("select");

    // runs the actual sql command
    String url = "jdbc:sqlite:" + this.path;
    try {
      Statement stmt = connection.createStatement();
      output = stmt.executeQuery(rawSQL);
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return output;

  }

}
