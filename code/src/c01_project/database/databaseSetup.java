package c01_project.database;

import java.sql.*;

public class databaseSetup {

  final public static String[] tableNames = {
          "ClientProfile",
          "information_and_orientation",
          "employment",
          "client_enrollment",
          "children",
          "community_connection",
          "client_setup",
          "client_exit"
  };

  public static void main(String[] args) throws SQLException {
    createNewDatabase("test.db");
    initializeNewTables("test.db");
    CSVParser t1=new CSVParser("/src/c01_project.database/test.db");
    //t1.parseCSVBasicICareTemplate("sample_data.csv");
    UserQuery.addUser("Alice","123",1);
    UserQuery.addUser("Bob","123",2);
    UserQuery.addUser("Cody","123",3);
    UserQuery.addUser("Jeff","123",4);
    System.out.println(UserQuery.login("Alice","123"));
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
      output = stmt.executeQuery(sql);
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
    String sqlUserTable = "Create Table if not exists Users(username String,password String,type int);";
    
    String sqlClientProfile = "CREATE TABLE if not exists Client_Profile (\r\n" + 
    		"    processing_details        STRING,\r\n" + 
    		"    client_validation_type_id STRING,\r\n" + 
    		"    client_validation_id      STRING,\r\n" + 
    		"    client_birth_dt           DATE,\r\n" + 
    		"    phone_no                  INTEGER,\r\n" + 
    		"    email_txt_ind             BOOLEAN,\r\n" + 
    		"    email_txt                 STRING,\r\n" + 
    		"    street_no                 INTEGER,\r\n" + 
    		"    street_nme                STRING,\r\n" + 
    		"    street_type_id            STRING,\r\n" + 
    		"    street_direction_id       STRING,\r\n" + 
    		"    unit_txt                  STRING,\r\n" + 
    		"    city_txt                  STRING,\r\n" + 
    		"    province_id               STRING,\r\n" + 
    		"    postal_txt                STRING,\r\n" + 
    		"    official_language_id      STRING,\r\n" + 
    		"    consent_ind               BOOLEAN\r\n" + 
    		");";

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
      stmt.execute(sqlClientProfile);
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
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
