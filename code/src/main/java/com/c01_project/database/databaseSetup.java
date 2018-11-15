package com.c01_project.database;

import com.c01_project.database.*;

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
    c01_project.database.CSVParser t1=new c01_project.database.CSVParser("/src/c01_project.database/test.db");
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

    String sqlLTClientEnrollNew = "CREATE TABLE if not exists LT_Client_Enroll_New (\r\n" + 
    		"    processing_details,\r\n" + 
    		"    update_record_id,\r\n" + 
    		"    client_validation_type_id,\r\n" + 
    		"    client_validation_id,\r\n" + 
    		"    client_birth_dt,\r\n" + 
    		"    postal_cd,\r\n" + 
    		"    course_cd,\r\n" + 
    		"    first_class_dt,\r\n" + 
    		"    preferred_official_lang_id,\r\n" + 
    		"    support_received_ind,\r\n" + 
    		"    childminding_ind,\r\n" + 
    		"    \"childminding_NewcomerChildren[1]childminding_age_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[1]childminding_type_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[2]childminding_age_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[2]childminding_type_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[3]childminding_age_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[3]childminding_type_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[4]childminding_age_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[4]childminding_type_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[5]childminding_age_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[5]childminding_type_id\",\r\n" + 
    		"    transportation_ind,\r\n" + 
    		"    support_disability_ind,\r\n" + 
    		"    translation_ind,\r\n" + 
    		"    translation_language_from_id,\r\n" + 
    		"    translation_language_to_id,\r\n" + 
    		"    interpretation_ind,\r\n" + 
    		"    interpretation_language_from_id,\r\n" + 
    		"    interpretation_language_to_id,\r\n" + 
    		"    counselling_ind,\r\n" + 
    		"    assessment_update_reason_id,\r\n" + 
    		"    valid                                                  BOOLEAN\r\n" + 
    		");\r\n" + 
    		""; // TODO the rest of the fields

    String sqlLTClientExitNew = "CREATE TABLE if not exists LT_Client_Exit_New (\r\n" + 
    		"    processing_details,\r\n" + 
    		"    update_record_id,\r\n" + 
    		"    client_validation_type_id,\r\n" + 
    		"    client_validation_id,\r\n" + 
    		"    client_birth_dt,\r\n" + 
    		"    course_cd,\r\n" + 
    		"    training_status_id,\r\n" + 
    		"    exit_course_dt,\r\n" + 
    		"    exit_course_reason_id,\r\n" + 
    		"    listening_CLB_level_id,\r\n" + 
    		"    speaking_CLB_level_id,\r\n" + 
    		"    reading_CLB_level_id,\r\n" + 
    		"    writing_CLB_level_id,\r\n" + 
    		"    certificate_issued_ind,\r\n" + 
    		"    certificate_listening_level_id,\r\n" + 
    		"    certificate_speaking_level_id,\r\n" + 
    		"    support_received_ind,\r\n" + 
    		"    childminding_ind,\r\n" + 
    		"    \"childminding_NewcomerChildren[1]childminding_age_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[1]childminding_type_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[2]childminding_age_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[2]childminding_type_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[3]childminding_age_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[3]childminding_type_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[4]childminding_age_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[4]childminding_type_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[5]childminding_age_id\",\r\n" + 
    		"    \"childminding_NewcomerChildren[5]childminding_type_id\",\r\n" + 
    		"    transportation_ind,\r\n" + 
    		"    support_disability_ind,\r\n" + 
    		"    translation_ind,\r\n" + 
    		"    translation_language_from_id,\r\n" + 
    		"    translation_language_to_id,\r\n" + 
    		"    interpretation_ind,\r\n" + 
    		"    interpretation_language_from_id,\r\n" + 
    		"    interpretation_language_to_id,\r\n" + 
    		"    counselling_ind,\r\n" + 
    		"    assessment_update_reason_id,\r\n" + 
    		"    valid                                                  BOOLEAN NOT NULL\r\n" + 
    		"                                                                   DEFAULT (false) \r\n" + 
    		");\r\n" + 
    		""; // TODO add non mandatory fields

    String sqlLT_Course_Setup_New = "CREATE TABLE if not exists LT_Course_Setup_New (\r\n" + 
    		"    processing_details,\r\n" + 
    		"    update_record_id,\r\n" + 
    		"    course_cd,\r\n" + 
    		"    course_note,\r\n" + 
    		"    held_on_ongoing_basis_ind,\r\n" + 
    		"    official_language_id,\r\n" + 
    		"    training_format_id,\r\n" + 
    		"    training_location_id,\r\n" + 
    		"    inperson_instruction_percentage,\r\n" + 
    		"    online_distance_percentage,\r\n" + 
    		"    total_spots_num,\r\n" + 
    		"    total_cic_funded_spots_num,\r\n" + 
    		"    course_enrolment_id,\r\n" + 
    		"    support_available_ind,\r\n" + 
    		"    available_childminding_ind,\r\n" + 
    		"    available_transportation_ind,\r\n" + 
    		"    available_support_disability_ind,\r\n" + 
    		"    start_dt,\r\n" + 
    		"    end_dt,\r\n" + 
    		"    course_schedule_morning,\r\n" + 
    		"    course_schedule_afternoon,\r\n" + 
    		"    course_schedule_evening,\r\n" + 
    		"    course_schedule_weekends,\r\n" + 
    		"    course_schedule_anytime,\r\n" + 
    		"    course_schedule_online,\r\n" + 
    		"    instructional_hours_per_class,\r\n" + 
    		"    classes_per_week,\r\n" + 
    		"    weeks_of_instruction,\r\n" + 
    		"    weeks_of_instruction_per_year,\r\n" + 
    		"    course_dominant_focus_id,\r\n" + 
    		"    target_group_ind,\r\n" + 
    		"    target_group_children,\r\n" + 
    		"    target_group_youth,\r\n" + 
    		"    target_group_senior,\r\n" + 
    		"    target_group_gender_specific,\r\n" + 
    		"    target_group_refugees,\r\n" + 
    		"    target_group_official_language_minorities,\r\n" + 
    		"    target_group_ethnic,\r\n" + 
    		"    target_group_deaf,\r\n" + 
    		"    target_group_blind,\r\n" + 
    		"    target_group_other_impairments,\r\n" + 
    		"    target_group_LGBTQ,\r\n" + 
    		"    target_group_families,\r\n" + 
    		"    target_group_training_in_regulated_profession,\r\n" + 
    		"    target_group_training_in_regulated_trade,\r\n" + 
    		"    course_material_ind,\r\n" + 
    		"    course_material_citizenship_resource,\r\n" + 
    		"    course_material_PBLA_language_companion,\r\n" + 
    		"    contact_nme,\r\n" + 
    		"    street_no,\r\n" + 
    		"    street_nme,\r\n" + 
    		"    street_type_id,\r\n" + 
    		"    street_direction_id,\r\n" + 
    		"    unit_txt,\r\n" + 
    		"    province_id,\r\n" + 
    		"    city_id,\r\n" + 
    		"    postal_cd,\r\n" + 
    		"    phone_no,\r\n" + 
    		"    phone_ext_no,\r\n" + 
    		"    email_txt,\r\n" + 
    		"    language_CLB_skill_listening_1,\r\n" + 
    		"    language_CLB_skill_listening_2,\r\n" + 
    		"    language_CLB_skill_listening_3,\r\n" + 
    		"    language_CLB_skill_listening_4,\r\n" + 
    		"    language_CLB_skill_listening_5,\r\n" + 
    		"    language_CLB_skill_listening_6,\r\n" + 
    		"    language_CLB_skill_listening_7,\r\n" + 
    		"    language_CLB_skill_listening_8,\r\n" + 
    		"    language_CLB_skill_listening_9,\r\n" + 
    		"    language_CLB_skill_listening_10,\r\n" + 
    		"    language_CLB_skill_listening_11,\r\n" + 
    		"    language_CLB_skill_listening_12,\r\n" + 
    		"    language_CLB_skill_speaking_1,\r\n" + 
    		"    language_CLB_skill_speaking_2,\r\n" + 
    		"    language_CLB_skill_speaking_3,\r\n" + 
    		"    language_CLB_skill_speaking_4,\r\n" + 
    		"    language_CLB_skill_speaking_5,\r\n" + 
    		"    language_CLB_skill_speaking_6,\r\n" + 
    		"    language_CLB_skill_speaking_7,\r\n" + 
    		"    language_CLB_skill_speaking_8,\r\n" + 
    		"    language_CLB_skill_speaking_9,\r\n" + 
    		"    language_CLB_skill_speaking_10,\r\n" + 
    		"    language_CLB_skill_speaking_11,\r\n" + 
    		"    language_CLB_skill_speaking_12,\r\n" + 
    		"    language_CLB_skill_reading_1,\r\n" + 
    		"    language_CLB_skill_reading_2,\r\n" + 
    		"    language_CLB_skill_reading_3,\r\n" + 
    		"    language_CLB_skill_reading_4,\r\n" + 
    		"    language_CLB_skill_reading_5,\r\n" + 
    		"    language_CLB_skill_reading_6,\r\n" + 
    		"    language_CLB_skill_reading_7,\r\n" + 
    		"    language_CLB_skill_reading_8,\r\n" + 
    		"    language_CLB_skill_reading_9,\r\n" + 
    		"    language_CLB_skill_reading_10,\r\n" + 
    		"    language_CLB_skill_reading_11,\r\n" + 
    		"    language_CLB_skill_reading_12,\r\n" + 
    		"    language_CLB_skill_reading_13,\r\n" + 
    		"    language_CLB_skill_reading_14,\r\n" + 
    		"    language_CLB_skill_reading_15,\r\n" + 
    		"    language_CLB_skill_reading_16,\r\n" + 
    		"    language_CLB_skill_reading_17,\r\n" + 
    		"    language_CLB_skill_writing_1,\r\n" + 
    		"    language_CLB_skill_writing_2,\r\n" + 
    		"    language_CLB_skill_writing_3,\r\n" + 
    		"    language_CLB_skill_writing_4,\r\n" + 
    		"    language_CLB_skill_writing_5,\r\n" + 
    		"    language_CLB_skill_writing_6,\r\n" + 
    		"    language_CLB_skill_writing_7,\r\n" + 
    		"    language_CLB_skill_writing_8,\r\n" + 
    		"    language_CLB_skill_writing_9,\r\n" + 
    		"    language_CLB_skill_writing_10,\r\n" + 
    		"    language_CLB_skill_writing_11,\r\n" + 
    		"    language_CLB_skill_writing_12,\r\n" + 
    		"    language_CLB_skill_writing_13,\r\n" + 
    		"    language_CLB_skill_writing_14,\r\n" + 
    		"    language_CLB_skill_writing_15,\r\n" + 
    		"    language_CLB_skill_writing_16,\r\n" + 
    		"    language_CLB_skill_writing_17,\r\n" + 
    		"    valid                                         BOOLEAN\r\n" + 
    		");"; // TODO add non mandatory fields

    String sqlChildren = "CREATE TABLE IF NOT EXISTS children (\n"
            + "	parent_id integer PRIMARY KEY,\n"
            + " child_id integer NOT NULL \n"
            + ");"; // NOTE each child of a person is a separate row of the table

    try (Connection conn = DriverManager.getConnection(url);
         Statement stmt = conn.createStatement()) {
      // create a new table
      System.out.println("connected to database");
      stmt.execute(sqlClientProfile);
      System.out.println("created Client Profile table");
      stmt.execute(sqlLTClientEnrollNew);
      System.out.println("created unconfirm Client Enroll table");
      stmt.execute(sqlLTClientExitNew);
      System.out.println("created unconfirm Client Exit table");
      stmt.execute(sqlLT_Course_Setup_New);
      System.out.println("created Course setup table");
      stmt.execute(sqlChildren);
      System.out.println("created children table");
      stmt.execute(sqlUserTable);
      System.out.println("created user table");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
