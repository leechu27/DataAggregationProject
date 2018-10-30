package c01_project.database;

import java.sql.*;

public class databaseSetup {

  final public static String[] tableNames = {
          "basic_data",
          "information_and_orientation",
          "employment",
          "client_enrollment",
          "children",
          "community_connection",
          "client_setup",
          "client_exit"
  };

  public static void main(String[] args) {
    createNewDatabase("test.db");
    initializeNewTables("test.db");
    CSVParser t1=new CSVParser("/src/c01_project.database/test.db");
    t1.parseCSVBasicICareTemplate("sample_data.csv");
  }

  /**
   * Runs raw sql on the server
   * this method is package private
   * @param sql
   * @return
   */
  static ResultSet runRawSQL(String sql, String databasePath) {
    ResultSet output = null;

    // runs the actual sql command
    String url = "jdbc:sqlite:" + databasePath;
    try {Connection conn = DriverManager.getConnection(url);
      Statement stmt = conn.createStatement();
      stmt.execute(sql);
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return output;
  }


  public static void createNewDatabase(String databasePath) {

    String url = "jdbc:sqlite:" + databasePath;

    try (Connection conn = DriverManager.getConnection(url)) {
      if (conn != null) {
        DatabaseMetaData meta = conn.getMetaData();
        System.out.println("The driver name is " + meta.getDriverName());
        System.out.println("A new database has been created.");
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public static void initializeNewTables(String databasePath) {
    String url = "jdbc:sqlite:" + databasePath;
    String sqlUserTable = "Create Table Users(\r\n" + 
    		"    username String,\r\n" + 
    		"    password String,\r\n" + 
    		"    type int\r\n" + 
    		");\r\n" + 
    		"\r\n" + 
    		"Insert INTO Users (username,password,type) values ('Alice','123',1);\r\n" + 
    		"Insert INTO Users (username,password,type) values ('Bob','123',2);\r\n" + 
    		"Insert INTO Users (username,password,type) values ('Cody','123',3);\r\n" + 
    		"Insert INTO Users (username,password,type) values ('Jeff','123',4);";
    
    String sqlUserDefaults = "Insert INTO Users (username,password,type) values ('Alice','123',1);\r\n" + 
    		"Insert INTO Users (username,password,type) values ('Bob','123',2);\r\n" + 
    		"Insert INTO Users (username,password,type) values ('Cody','123',3);\r\n" + 
    		"Insert INTO Users (username,password,type) values ('Jeff','123',4);";
    
    String sqlBasicTable = "CREATE TABLE IF NOT EXISTS basic_data (\n"
            + "	id integer PRIMARY KEY,\n"
            + "	name text NOT NULL,\n"
            + "	unique_identifier text NOT NULL,\n"
            + "	unique_identifier_value text NOT NULL,\n"
            + "	date_of_birth TEXT NOT NULL,\n" // in the form YYYY-MM-DD
            + "	start_date_of_service text,\n"  // in the form YYYY-MM-DD
            + "	language_of_service text,\n"
            + "	language_of_preference text\n"
            + ");";

    String sqlOrientation = "CREATE TABLE IF NOT EXISTS information_and_orientation (\n"
            + "	id integer PRIMARY KEY,\n"
            + " orientation_length TEXT, \n"
            + " orientation_length_hours INTEGER, \n"
            + " orientation_length_minutes INTEGER, \n"
            + " number_of_clients TEXT, \n"
            + " directed_at_specific_group TEXT, \n"
            + " directed_at_children TEXT, \n"
            + " directed_at_youth TEXT, \n"
            + " directed_at_seniors TEXT, \n"
            + " directed_at_gender TEXT, \n"
            + " directed_at_refugees TEXT, \n"
            + " directed_at_ethnic_cultural_group TEXT, \n"
            + " directed_at_hearing_impaired TEXT, \n"
            + " directed_at_sight_impaired TEXT, \n"
            + " directed_at_lgbt TEXT, \n"
            + " directed_at_families_parents TEXT, \n"
            + " directed_at_other TEXT, \n"
            + " directed_at_international_professionals TEXT, \n"
            + " directed_at_international_tradespeople TEXT, \n"
            + " directed_at_official_language_minority TEXT, \n"
            + " overview_of_canada TEXT, \n"
            + " overview_of_canada_referrals TEXT \n"
            + ");"; // TODO the rest of the fields

    String sqlEmployment = "CREATE TABLE IF NOT EXISTS employment (\n"
            + "	id integer PRIMARY KEY,\n"
            + " postal_code_of_service TEXT, \n"
            + " registration_in_employment_intervention TEXT, \n"
            + " service_institution_type TEXT, \n"
            + " referred_by TEXT \n"
            + ");"; // TODO add non mandatory fields

    String sqlEnrollment = "CREATE TABLE IF NOT EXISTS client_enrollment (\n"
            + "	id integer PRIMARY KEY,\n"
            + " postal_code_of_service TEXT, \n"
            + " course_code TEXT, \n"
            + " date_of_first_class TEXT, \n"
            + " support_services_received TEXT, \n"
            + " care_for_newcomer_children TEXT \n"
            + ");"; // TODO add non mandatory fields

    String sqlChildren = "CREATE TABLE IF NOT EXISTS children (\n"
            + "	parent_id integer PRIMARY KEY,\n"
            + " child_id integer NOT NULL \n"
            + ");"; // NOTE each child of a person is a separate row of the table

    try (Connection conn = DriverManager.getConnection(url);
         Statement stmt = conn.createStatement()) {
      // create a new table
      System.out.println("connected to database");
      stmt.execute(sqlBasicTable);
      System.out.println("created complete basic table");
      stmt.execute(sqlOrientation);
      System.out.println("created incomplete orientation table");
      stmt.execute(sqlEmployment);
      System.out.println("created incomplete employment table");
      stmt.execute(sqlEnrollment);
      System.out.println("created incomplete enrollment table");
      stmt.execute(sqlChildren);
      System.out.println("created children table");
      stmt.execute(sqlUserTable);
      System.out.println("created user table");
      stmt.execute(sqlUserDefaults);
      System.out.println("added default users Alice Bob Cody Jeff");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
