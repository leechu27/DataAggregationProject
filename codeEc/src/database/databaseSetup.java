package database;

import database.*;

import java.sql.*;

public class databaseSetup {

  public static void main(String[] args) throws SQLException {
    createNewDatabase("test.db");
    initializeNewTables("test.db");
    //database.CSVParser t1=new database.CSVParser();
    //t1.parseCSVBasicICareTemplate("sample_data.csv");
    //default users added
    UserQuery.addUser("Alice","123",1);
    UserQuery.addUser("Bob","123",2);
    UserQuery.addUser("Cody","123",3);
    UserQuery.addUser("Jeff","123",4);
    //sample login usage
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
    		"    client_validation_id      STRING  UNIQUE,\r\n" + 
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
    		");\r\n" + 
    		"";

    String sqlLTClientEnrollNew = "CREATE TABLE if not exists LT_Client_Enroll_New (\r\n" + 
    		"    processing_details,\r\n" + 
    		"    update_record_id,\r\n" + 
    		"    client_validation_type_id,\r\n" + 
    		"    client_validation_id   PRIMARY KEY,\r\n" + 
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
    		"    client_validation_id   PRIMARY KEY,\r\n" + 
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
    		""; 

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
    String sqlCommunityConnections="CREATE TABLE if not exists Community_Connections (\r\n" + 
    		"    processing_details,\r\n" + 
    		"    update_record_id,\r\n" + 
    		"    client_validation_type_id,\r\n" + 
    		"    client_validation_id   PRIMARY KEY,\r\n" + 
    		"    client_birth_dt,\r\n" + 
    		"    postal_cd,\r\n" + 
    		"    service_lang_id,\r\n" + 
    		"    preferred_official_lang_id,\r\n" + 
    		"    assessment_referral_id,\r\n" + 
    		"    community_activity_id,\r\n" + 
    		"    institution_type_id,\r\n" + 
    		"    events_type_attended_id,\r\n" + 
    		"    service_type_id,\r\n" + 
    		"    topics_service_id,\r\n" + 
    		"    community_service_id,\r\n" + 
    		"    group_clients_no_id,\r\n" + 
    		"    volunteers_participate_ind,\r\n" + 
    		"    target_group_specific_ind,\r\n" + 
    		"    target_group_children_ind,\r\n" + 
    		"    target_group_youth_ind,\r\n" + 
    		"    target_group_senior_ind,\r\n" + 
    		"    target_group_gender_ind,\r\n" + 
    		"    target_group_refugee_ind,\r\n" + 
    		"    target_group_ethnic_ind,\r\n" + 
    		"    target_group_hearing_ind,\r\n" + 
    		"    target_group_visual_ind,\r\n" + 
    		"    target_group_LGBTQ_ind,\r\n" + 
    		"    target_group_families_parents_ind,\r\n" + 
    		"    target_group_other_impairments_ind,\r\n" + 
    		"    target_group_CWIT_in_regulated_profession_ind,\r\n" + 
    		"    target_group_CWIT_in_regulated_trade_ind,\r\n" + 
    		"    target_group_official_language_minorities_ind,\r\n" + 
    		"    service_status_id,\r\n" + 
    		"    community_leave_reason_id,\r\n" + 
    		"    service_start_dt,\r\n" + 
    		"    service_end_dt,\r\n" + 
    		"    service_projected_end_dt,\r\n" + 
    		"    training_received_service_ind,\r\n" + 
    		"    training_received_computer_ind,\r\n" + 
    		"    training_received_document_ind,\r\n" + 
    		"    training_received_interpersonal_ind,\r\n" + 
    		"    training_received_leadership_ind,\r\n" + 
    		"    training_received_life_skills_ind,\r\n" + 
    		"    training_received_numeracy_ind,\r\n" + 
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
    		"    service_length_hours_no,\r\n" + 
    		"    service_length_minutes_no,\r\n" + 
    		"    assessment_update_reason_id\r\n" + 
    		");\r\n" + 
    		"";
    String sqlEmployment="CREATE TABLE if not exists Employment (\r\n" + 
    		"    processing_details,\r\n" + 
    		"    update_record_id,\r\n" + 
    		"    client_validation_type_id,\r\n" + 
    		"    client_validation_id,\r\n" + 
    		"    client_birth_dt,\r\n" + 
    		"    postal_cd,\r\n" + 
    		"    session_result_intn_ind,\r\n" + 
    		"    session_referral_id,\r\n" + 
    		"    session_service_lang_id,\r\n" + 
    		"    session_official_lang_id,\r\n" + 
    		"    institution_type_id,\r\n" + 
    		"    assessment_referral_id,\r\n" + 
    		"    session_referral_dt,\r\n" + 
    		"    session_employment_status_id,\r\n" + 
    		"    session_education_status_id,\r\n" + 
    		"    session_current_occupation_id,\r\n" + 
    		"    session_intended_occupation_id,\r\n" + 
    		"    intervention_type_id,\r\n" + 
    		"    intervention_received_id,\r\n" + 
    		"    intervention_status_id,\r\n" + 
    		"    intervention_leave_reason_id,\r\n" + 
    		"    intervention_start_dt,\r\n" + 
    		"    intervention_end_dt,\r\n" + 
    		"    employer_size_id,\r\n" + 
    		"    placement_type_id,\r\n" + 
    		"    working_hours_id,\r\n" + 
    		"    mentoring_location_id,\r\n" + 
    		"    mentoring_hours_id,\r\n" + 
    		"    intervention_profession_id,\r\n" + 
    		"    training_received_service_ind,\r\n" + 
    		"    training_received_computer_ind,\r\n" + 
    		"    training_received_document_ind,\r\n" + 
    		"    training_received_interpersonal_ind,\r\n" + 
    		"    training_received_leadership_ind,\r\n" + 
    		"    training_received_numeracy_ind,\r\n" + 
    		"    \"short_term_service[1]service_received_id\",\r\n" + 
    		"    \"short_term_service[1]entry_dt\",\r\n" + 
    		"    \"short_term_service[2]service_received_id\",\r\n" + 
    		"    \"short_term_service[2]entry_dt\",\r\n" + 
    		"    \"short_term_service[3]service_received_id\",\r\n" + 
    		"    \"short_term_service[3]entry_dt\",\r\n" + 
    		"    \"short_term_service[4]service_received_id\",\r\n" + 
    		"    \"short_term_service[4]entry_dt\",\r\n" + 
    		"    \"short_term_service[5]service_received_id\",\r\n" + 
    		"    \"short_term_service[5]entry_dt\",\r\n" + 
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
    		"    complete_hours_spent_no,\r\n" + 
    		"    complete_minutes_spent_no,\r\n" + 
    		"    assessment_update_reason_id\r\n" + 
    		");\r\n" + 
    		"";
    String sqlInfoOrien="CREATE TABLE if not exists Info_Orien (\r\n" + 
    		"    processing_details,\r\n" + 
    		"    update_record_id,\r\n" + 
    		"    client_validation_type_id,\r\n" + 
    		"    client_validation_id,\r\n" + 
    		"    client_birth_dt,\r\n" + 
    		"    postal_cd,\r\n" + 
    		"    start_dttm,\r\n" + 
    		"    service_language_id,\r\n" + 
    		"    service_official_language_id,\r\n" + 
    		"    institution_type_id,\r\n" + 
    		"    service_referred_by_id,\r\n" + 
    		"    orientation_service_id,\r\n" + 
    		"    orientation_length_id,\r\n" + 
    		"    orientation_length_hours_no,\r\n" + 
    		"    orientation_length_minutes_no,\r\n" + 
    		"    group_clients_no_id,\r\n" + 
    		"    target_group_specific_ind,\r\n" + 
    		"    target_group_children_ind,\r\n" + 
    		"    target_group_youth_ind,\r\n" + 
    		"    target_group_senior_ind,\r\n" + 
    		"    target_group_gender_ind,\r\n" + 
    		"    target_group_refugee_ind,\r\n" + 
    		"    target_group_ethnic_ind,\r\n" + 
    		"    target_group_hearing_ind,\r\n" + 
    		"    target_group_visual_ind,\r\n" + 
    		"    target_group_LGBTQ_ind,\r\n" + 
    		"    target_group_families_parents_ind,\r\n" + 
    		"    target_group_other_impairments_ind,\r\n" + 
    		"    target_group_CWIT_in_regulated_profession_ind,\r\n" + 
    		"    target_group_CWIT_in_regulated_trade_ind,\r\n" + 
    		"    target_group_official_language_minorities_ind,\r\n" + 
    		"    topic_overview_given_ind,\r\n" + 
    		"    topic_overview_referral_ind,\r\n" + 
    		"    topic_information_given_ind,\r\n" + 
    		"    topic_information_referral_ind,\r\n" + 
    		"    topic_rights_given_ind,\r\n" + 
    		"    topic_rights_referral_ind,\r\n" + 
    		"    topic_law_given_ind,\r\n" + 
    		"    topic_law_referral_ind,\r\n" + 
    		"    topic_documents_given_ind,\r\n" + 
    		"    topic_documents_referral_ind,\r\n" + 
    		"    topic_language_given_ind,\r\n" + 
    		"    topic_language_referral_ind,\r\n" + 
    		"    topic_income_given_ind,\r\n" + 
    		"    topic_income_referral_ind,\r\n" + 
    		"    topic_education_given_ind,\r\n" + 
    		"    topic_education_referral_ind,\r\n" + 
    		"    topic_housing_given_ind,\r\n" + 
    		"    topic_housing_referral_ind,\r\n" + 
    		"    topic_health_given_ind,\r\n" + 
    		"    topic_health_referral_ind,\r\n" + 
    		"    topic_money_given_ind,\r\n" + 
    		"    topic_money_referral_ind,\r\n" + 
    		"    topic_transportation_given_ind,\r\n" + 
    		"    topic_transportation_referral_ind,\r\n" + 
    		"    topic_media_given_ind,\r\n" + 
    		"    topic_media_referral_ind,\r\n" + 
    		"    topic_community_given_ind,\r\n" + 
    		"    topic_community_referral_ind,\r\n" + 
    		"    topic_citizenship_given_ind,\r\n" + 
    		"    topic_citizenship_referral_ind,\r\n" + 
    		"    topic_conflict_given_ind,\r\n" + 
    		"    topic_conflict_referral_ind,\r\n" + 
    		"    training_received_service_ind,\r\n" + 
    		"    training_received_computer_ind,\r\n" + 
    		"    training_received_document_ind,\r\n" + 
    		"    training_received_interpersonal_ind,\r\n" + 
    		"    training_received_leadership_ind,\r\n" + 
    		"    training_received_numeracy_ind,\r\n" + 
    		"    skill_received_service_ind,\r\n" + 
    		"    essential_skill_life_ind,\r\n" + 
    		"    essential_skill_responsabilities_ind,\r\n" + 
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
    		"    end_dttm,\r\n" + 
    		"    assessment_update_reason_id\r\n" + 
    		");\r\n" + 
    		"";
    String sqlNeedsAssessmentAndReferrals="CREATE TABLE if not exists Needs_Assessment_and_Referrals (\r\n" + 
    		"    processing_details,\r\n" + 
    		"    update_record_id,\r\n" + 
    		"    client_validation_type_id,\r\n" + 
    		"    client_validation_id,\r\n" + 
    		"    client_birth_dt,\r\n" + 
    		"    postal_cd,\r\n" + 
    		"    assessment_start_dt,\r\n" + 
    		"    assessment_language_id,\r\n" + 
    		"    preferred_official_language_id,\r\n" + 
    		"    institution_type_id,\r\n" + 
    		"    assessment_referral_id,\r\n" + 
    		"    knwl_life_needs_ind,\r\n" + 
    		"    knwl_life_referrals_ind,\r\n" + 
    		"    knwl_comm_gov_svcs_needs_ind,\r\n" + 
    		"    knwl_comm_gov_svcs_referrals_ind,\r\n" + 
    		"    knwl_working_needs_ind,\r\n" + 
    		"    knwl_working_referrals_ind,\r\n" + 
    		"    knwl_education_needs_ind,\r\n" + 
    		"    knwl_education_needs_referrals_ind,\r\n" + 
    		"    social_networks_needs_ind,\r\n" + 
    		"    social_networks_referrals_ind,\r\n" + 
    		"    professional_networks_needs_ind,\r\n" + 
    		"    professional_networks_referrals_ind,\r\n" + 
    		"    local_community_svcs_needs_ind,\r\n" + 
    		"    local_community_svcs_referrals_ind,\r\n" + 
    		"    community_involvement_needs_ind,\r\n" + 
    		"    community_involvement_referrals_ind,\r\n" + 
    		"    language_skills_needs_ind,\r\n" + 
    		"    language_skills_referrals_ind,\r\n" + 
    		"    language_skills_needs_reason_id,\r\n" + 
    		"    other_skills_needs_ind,\r\n" + 
    		"    other_skills_referrals_ind,\r\n" + 
    		"    other_skills_needs_reason_id,\r\n" + 
    		"    find_employment_needs_ind,\r\n" + 
    		"    find_employment_referrals_ind,\r\n" + 
    		"    find_employment_period_id,\r\n" + 
    		"    canadian_employment_experience_id,\r\n" + 
    		"    intended_work_noc_group_id,\r\n" + 
    		"    intention_credentials_licence_ind,\r\n" + 
    		"    intention_canadian_citizen_ind,\r\n" + 
    		"    support_required_services_required,\r\n" + 
    		"    childminding_required_ind,\r\n" + 
    		"    transportation_required_ind,\r\n" + 
    		"    support_disability_required_ind,\r\n" + 
    		"    translation_required_ind,\r\n" + 
    		"    interpretation_required_ind,\r\n" + 
    		"    counselling_required_ind,\r\n" + 
    		"    non_cic_program_needs_services_required,\r\n" + 
    		"    food_cloth_material_needs_ind,\r\n" + 
    		"    food_cloth_material_refs_ind,\r\n" + 
    		"    housing_accommodation_needs_ind,\r\n" + 
    		"    housing_accommodation_referrals_ind,\r\n" + 
    		"    hlth_mental_wellness_needs_ind,\r\n" + 
    		"    hlth_mental_wellness_refs_ind,\r\n" + 
    		"    financial_needs_ind,\r\n" + 
    		"    financial_referrals_ind,\r\n" + 
    		"    family_support_needs_ind,\r\n" + 
    		"    family_support_referrals_ind,\r\n" + 
    		"    language_non_CIC_needs_ind,\r\n" + 
    		"    language_non_CIC_referrals_ind,\r\n" + 
    		"    edu_skills_devt_needs_ind,\r\n" + 
    		"    edu_skills_devt_referrals_ind,\r\n" + 
    		"    employment_related_needs_ind,\r\n" + 
    		"    employment_related_referrals_ind,\r\n" + 
    		"    legal_info_services_needs_ind,\r\n" + 
    		"    legal_info_services_referrals_ind,\r\n" + 
    		"    community_services_needs_ind,\r\n" + 
    		"    community_services_referrals_ind,\r\n" + 
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
    		"    settlement_plan_ind,\r\n" + 
    		"    assessment_completed_dt,\r\n" + 
    		"    assessment_update_reason_id\r\n" + 
    		");";

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
      stmt.execute(sqlCommunityConnections);
      System.out.println("created commnunity connections");
      stmt.execute(sqlLT_Course_Setup_New);
      System.out.println("created Course setup table");
      stmt.execute(sqlNeedsAssessmentAndReferrals);
      System.out.println("created Needs Assesment and Referrals table");
      stmt.execute(sqlEmployment);
      System.out.println("created Employment table");
      stmt.execute(sqlInfoOrien);
      System.out.println("created InfoOrien table");
      stmt.execute(sqlUserTable);
      System.out.println("created user table");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
