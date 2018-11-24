package testsDatabase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import testsDatabase.MockPendingDatabaseEntry;

public class CsvParserTest {

  private final String TEST_ROOT = "test_resources/testICareTemplates/";
  private final String REAL_SAMPLES_ROOT = "test_resources/csv/";
  
  private static database.CSVParser c;
  private static MockPendingDatabaseEntry p;


  @BeforeAll
  public static void beforeAll() {
    p = new MockPendingDatabaseEntry();
    c = new database.CSVParser(p);
  }

  @BeforeEach
  public void reset() {
    p.clear();
  }

  @Test
  @DisplayName("empty normal csv template")
  public void testEmptyICareTemplate() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_no_lines.csv");
    assertEquals("", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("normal csv template with one line")
  public void testICareTemplateOneLine() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_single_line.csv");
    assertEquals("MALI Client_Profile name Mohammed Ali\n" +
            "MALI Client_Profile unique_identifier_value 12345\n" +
            "MALI Client_Profile date_of_birth 1995-02-31\n" +
            "MALI Client_Profile start_date_of_service 2018-05-20\n" +
            "MALI Client_Profile language_of_service English\n" +
            "MALI Client_Profile language_of_preference English\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("normal csv template with multiple lines")
  public void testICareTemplateMultipleLines() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_multiple_linens.csv");
    assertEquals("MALI Client_Profile name Mohammed Ali\n" +
            "MALI Client_Profile unique_identifier_value 12345\n" +
            "MALI Client_Profile date_of_birth 1995-02-31\n" +
            "MALI Client_Profile start_date_of_service 2018-05-20\n" +
            "MALI Client_Profile language_of_service English\n" +
            "MALI Client_Profile language_of_preference English\n" +
            "TSUN Client_Profile name Terry Suns\n" +
            "TSUN Client_Profile unique_identifier_value 12346\n" +
            "TSUN Client_Profile date_of_birth 1990-05-04\n" +
            "TSUN Client_Profile start_date_of_service 2018-03-21\n" +
            "TSUN Client_Profile language_of_service English\n" +
            "TSUN Client_Profile language_of_preference French\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("one line csv template with missing attributes")
  public void testMissingAttributes() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "normal_one_line_empty_fields.csv");
    assertEquals("MALI Client_Profile name Mohammed Ali\n" +
            "MALI Client_Profile unique_identifier_value 12345\n" +
            "MALI Client_Profile date_of_birth \n" +
            "MALI Client_Profile start_date_of_service 2018-05-20\n" +
            "MALI Client_Profile language_of_service \n" +
            "MALI Client_Profile language_of_preference English\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test a line with the main identifier missing")
  public void testBrokenInfo() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "missing_unique_identifier.csv");
    assertEquals(" Client_Profile name Mohammed Ali\n" +
            " Client_Profile unique_identifier_value 12345\n" +
            " Client_Profile date_of_birth 1995-02-31\n" +
            " Client_Profile start_date_of_service 2018-05-20\n" +
            " Client_Profile language_of_service English\n" +
            " Client_Profile language_of_preference English\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test a file where only the first line with the main identifier missing")
  public void testBrokenInfoOnlyOnFirstLine() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(TEST_ROOT + "missing_info_on_first_line_but_rest_fine.csv");
    assertEquals(" Client_Profile name Mohammed Ali\n" +
            " Client_Profile unique_identifier_value 12345\n" +
            " Client_Profile date_of_birth 1995-02-31\n" +
            " Client_Profile start_date_of_service 2018-05-20\n" +
            " Client_Profile language_of_service English\n" +
            " Client_Profile language_of_preference English\n" +
            "TSUN Client_Profile name Terry Suns\n" +
            "TSUN Client_Profile unique_identifier_value 12346\n" +
            "TSUN Client_Profile date_of_birth 1990-05-04\n" +
            "TSUN Client_Profile start_date_of_service 2018-03-21\n" +
            "TSUN Client_Profile language_of_service English\n" +
            "TSUN Client_Profile language_of_preference French\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test using a real ICare template (client profile)")
  public void testRealCSVTemplateClientProfile() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(REAL_SAMPLES_ROOT + "clientProfile.csv");
    assertEquals("12345678 client_Profile ﻿processing_details \"\"\n" + 
    		"12345678 client_Profile client_validation_type_id FOSS/GCMS Client ID\n" + 
    		"12345678 client_Profile client_birth_dt 1978-05-20\n" + 
    		"12345678 client_Profile phone_no 902-628-1285\n" + 
    		"12345678 client_Profile email_txt_ind Yes\n" + 
    		"12345678 client_Profile email_txt hnestor@cathcrosscultural.org\n" + 
    		"12345678 client_Profile street_no 1256\n" + 
    		"12345678 client_Profile street_nme College\n" + 
    		"12345678 client_Profile street_type_id Abbey\n" + 
    		"12345678 client_Profile street_direction_id E - East\n" + 
    		"12345678 client_Profile unit_txt 205\n" + 
    		"12345678 client_Profile city_txt Toronto\n" + 
    		"12345678 client_Profile province_id Ontario\n" + 
    		"12345678 client_Profile postal_txt M6G3A4\n" + 
    		"12345678 client_Profile official_language_id English\n" + 
    		"12345678 client_Profile consent_ind Yes\n", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test using a real ICare template (Community connections)")
  public void testRealCSVTemplateCommunityConnections() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(REAL_SAMPLES_ROOT + "Community_Connections.csv");
    assertEquals("12345678 Community_Connections ﻿processing_details \"\"\n" + 
    		"12345678 Community_Connections update_record_id 10387104\n" + 
    		"12345678 Community_Connections client_validation_type_id FOSS/GCMS Client ID\n" + 
    		"12345678 Community_Connections client_birth_dt 1978-05-20\n" + 
    		"12345678 Community_Connections postal_cd M6G3A4\n" + 
    		"12345678 Community_Connections service_lang_id English\n" + 
    		"12345678 Community_Connections preferred_official_lang_id English\n" + 
    		"12345678 Community_Connections assessment_referral_id Community centre / library\n" + 
    		"12345678 Community_Connections community_activity_id Community-based group events and activities\n" + 
    		"12345678 Community_Connections institution_type_id Settlement service provider\n" + 
    		"12345678 Community_Connections events_type_attended_id Events/visits pertaining to culture or history\n" + 
    		"12345678 Community_Connections service_type_id Conversation circle\n" + 
    		"12345678 Community_Connections topics_service_id Access to local community services\n" + 
    		"12345678 Community_Connections community_service_id Community-based group events and activities: Group session (e.g. conversation circles)\n" + 
    		"12345678 Community_Connections group_clients_no_id Less than 10 people\n" + 
    		"12345678 Community_Connections volunteers_participate_ind Yes\n" + 
    		"12345678 Community_Connections target_group_specific_ind Yes\n" + 
    		"12345678 Community_Connections target_group_children_ind Yes\n" + 
    		"12345678 Community_Connections target_group_youth_ind Yes\n" + 
    		"12345678 Community_Connections target_group_senior_ind Yes\n" + 
    		"12345678 Community_Connections target_group_gender_ind Yes\n" + 
    		"12345678 Community_Connections target_group_refugee_ind Yes\n" + 
    		"12345678 Community_Connections target_group_ethnic_ind Yes\n" + 
    		"12345678 Community_Connections target_group_hearing_ind Yes\n" + 
    		"12345678 Community_Connections target_group_visual_ind Yes\n" + 
    		"12345678 Community_Connections target_group_LGBTQ_ind Yes\n" + 
    		"12345678 Community_Connections target_group_families_parents_ind Yes\n" + 
    		"12345678 Community_Connections target_group_other_impairments_ind Yes\n" + 
    		"12345678 Community_Connections target_group_CWIT_in_regulated_profession_ind Yes\n" + 
    		"12345678 Community_Connections target_group_CWIT_in_regulated_trade_ind Yes\n" + 
    		"12345678 Community_Connections target_group_official_language_minorities_ind Yes\n" + 
    		"12345678 Community_Connections service_status_id Service ended early (i.e. client ended participation)\n" + 
    		"12345678 Community_Connections community_leave_reason_id Client felt the service was not meeting current needs\n" + 
    		"12345678 Community_Connections service_start_dt 2018-05-20\n" + 
    		"12345678 Community_Connections service_end_dt 2018-05-20\n" + 
    		"12345678 Community_Connections service_projected_end_dt 2018-05-20\n" + 
    		"12345678 Community_Connections training_received_service_ind Yes\n" + 
    		"12345678 Community_Connections training_received_computer_ind Yes\n" + 
    		"12345678 Community_Connections training_received_document_ind Yes\n" + 
    		"12345678 Community_Connections training_received_interpersonal_ind Yes\n" + 
    		"12345678 Community_Connections training_received_leadership_ind Yes\n" + 
    		"12345678 Community_Connections training_received_life_skills_ind Yes\n" + 
    		"12345678 Community_Connections training_received_numeracy_ind Yes\n" + 
    		"12345678 Community_Connections support_received_ind Yes\n" + 
    		"12345678 Community_Connections childminding_ind Yes\n" + 
    		"12345678 Community_Connections childminding_NewcomerChildren[1]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Community_Connections childminding_NewcomerChildren[1]childminding_type_id Short term\n" + 
    		"12345678 Community_Connections childminding_NewcomerChildren[2]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Community_Connections childminding_NewcomerChildren[2]childminding_type_id Short term\n" + 
    		"12345678 Community_Connections childminding_NewcomerChildren[3]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Community_Connections childminding_NewcomerChildren[3]childminding_type_id Short term\n" + 
    		"12345678 Community_Connections childminding_NewcomerChildren[4]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Community_Connections childminding_NewcomerChildren[4]childminding_type_id Short term\n" + 
    		"12345678 Community_Connections childminding_NewcomerChildren[5]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Community_Connections childminding_NewcomerChildren[5]childminding_type_id Short term\n" + 
    		"12345678 Community_Connections transportation_ind Yes\n" + 
    		"12345678 Community_Connections support_disability_ind Yes\n" + 
    		"12345678 Community_Connections translation_ind Yes\n" + 
    		"12345678 Community_Connections translation_language_from_id English\n" + 
    		"12345678 Community_Connections translation_language_to_id English\n" + 
    		"12345678 Community_Connections interpretation_ind Yes\n" + 
    		"12345678 Community_Connections interpretation_language_from_id English\n" + 
    		"12345678 Community_Connections interpretation_language_to_id English\n" + 
    		"12345678 Community_Connections counselling_ind Yes\n" + 
    		"12345678 Community_Connections service_length_hours_no 1\n" + 
    		"12345678 Community_Connections service_length_minutes_no 50\n" + 
    		"12345678 Community_Connections assessment_update_reason_id Amend record\n" + 
    		"", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test using a real ICare template (Employment)")
  public void testRealCSVTemplateEmployment() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(REAL_SAMPLES_ROOT + "Employment.csv");
    assertEquals("12345678 Employment processing_details \"\"\n" + 
    		"12345678 Employment update_record_id 10387104\n" + 
    		"12345678 Employment client_validation_type_id FOSS/GCMS Client ID\n" + 
    		"12345678 Employment client_birth_dt 1978-05-20\n" + 
    		"12345678 Employment postal_cd M6G4A3\n" + 
    		"12345678 Employment session_result_intn_ind Yes\n" + 
    		"12345678 Employment session_referral_id Another IRCC-funded service\n" + 
    		"12345678 Employment session_service_lang_id English\n" + 
    		"12345678 Employment session_official_lang_id English\n" + 
    		"12345678 Employment institution_type_id Settlement service provider\n" + 
    		"12345678 Employment assessment_referral_id Community centre / library\n" + 
    		"12345678 Employment session_referral_dt \n" + 
    		"12345678 Employment session_employment_status_id Unemployed\n" + 
    		"12345678 Employment session_education_status_id Full-time student\n" + 
    		"12345678 Employment session_current_occupation_id 00 Senior management occupations\n" + 
    		"12345678 Employment session_intended_occupation_id 00 Senior management occupations\n" + 
    		"12345678 Employment intervention_type_id Long Term\n" + 
    		"12345678 Employment intervention_received_id Work placement\n" + 
    		"12345678 Employment intervention_status_id Intervention ended early (i.e. client ended participation)\n" + 
    		"12345678 Employment intervention_leave_reason_id Found Employment\n" + 
    		"12345678 Employment intervention_start_dt 201-05-20\n" + 
    		"12345678 Employment intervention_end_dt 2018-0520\n" + 
    		"12345678 Employment employer_size_id Small-medium enterprise - fewer than 500 employees\n" + 
    		"12345678 Employment placement_type_id Paid\n" + 
    		"12345678 Employment working_hours_id Part-time - less than 30 hours at main or only job\n" + 
    		"12345678 Employment mentoring_location_id Mentor's place of work during work hours\n" + 
    		"12345678 Employment mentoring_hours_id 1\n" + 
    		"12345678 Employment intervention_profession_id Acupuncturists\n" + 
    		"12345678 Employment training_received_service_ind Yes\n" + 
    		"12345678 Employment training_received_computer_ind Yes\n" + 
    		"12345678 Employment training_received_document_ind Yes\n" + 
    		"12345678 Employment training_received_interpersonal_ind Yes\n" + 
    		"12345678 Employment training_received_leadership_ind Yes\n" + 
    		"12345678 Employment training_received_numeracy_ind Yes\n" + 
    		"12345678 Employment short_term_service[1]service_received_id Networking opportunities\n" + 
    		"12345678 Employment short_term_service[1]entry_dt 2018-05-20\n" + 
    		"12345678 Employment short_term_service[2]service_received_id Networking opportunities\n" + 
    		"12345678 Employment short_term_service[2]entry_dt 2018-05-20\n" + 
    		"12345678 Employment short_term_service[3]service_received_id Networking opportunities\n" + 
    		"12345678 Employment short_term_service[3]entry_dt 2018-05-20\n" + 
    		"12345678 Employment short_term_service[4]service_received_id Networking opportunities\n" + 
    		"12345678 Employment short_term_service[4]entry_dt 2018-05-20\n" + 
    		"12345678 Employment short_term_service[5]service_received_id Networking opportunities\n" + 
    		"12345678 Employment short_term_service[5]entry_dt 2018-05-20\n" + 
    		"12345678 Employment support_received_ind Yes\n" + 
    		"12345678 Employment childminding_ind Yes\n" + 
    		"12345678 Employment childminding_NewcomerChildren[1]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Employment childminding_NewcomerChildren[1]childminding_type_id Short term\n" + 
    		"12345678 Employment childminding_NewcomerChildren[2]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Employment childminding_NewcomerChildren[2]childminding_type_id Short term\n" + 
    		"12345678 Employment childminding_NewcomerChildren[3]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Employment childminding_NewcomerChildren[3]childminding_type_id Short term\n" + 
    		"12345678 Employment childminding_NewcomerChildren[4]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Employment childminding_NewcomerChildren[4]childminding_type_id Short term\n" + 
    		"12345678 Employment childminding_NewcomerChildren[5]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Employment childminding_NewcomerChildren[5]childminding_type_id Short term\n" + 
    		"12345678 Employment transportation_ind Yes\n" + 
    		"12345678 Employment support_disability_ind Yes\n" + 
    		"12345678 Employment translation_ind Yes\n" + 
    		"12345678 Employment translation_language_from_id English\n" + 
    		"12345678 Employment translation_language_to_id English\n" + 
    		"12345678 Employment interpretation_ind Yes\n" + 
    		"12345678 Employment interpretation_language_from_id English\n" + 
    		"12345678 Employment interpretation_language_to_id English\n" + 
    		"12345678 Employment counselling_ind Yes\n" + 
    		"12345678 Employment complete_hours_spent_no 1\n" + 
    		"12345678 Employment complete_minutes_spent_no 50\n" + 
    		"12345678 Employment assessment_update_reason_id Amend record\n" + 
    		"", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test using a real ICare template (Info and Orientation)")
  public void testRealCSVTemplateInfoOrientation() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(REAL_SAMPLES_ROOT + "Info_Orien.csv");
    assertEquals("12345678 Info_Orien ﻿processing_details \"\"\n" + 
    		"12345678 Info_Orien update_record_id 10387104\n" + 
    		"12345678 Info_Orien client_validation_type_id FOSS/GCMS Client ID\n" + 
    		"12345678 Info_Orien client_birth_dt 1978-05-20\n" + 
    		"12345678 Info_Orien postal_cd M6G3A4\n" + 
    		"12345678 Info_Orien start_dttm 2018-05-20\n" + 
    		"12345678 Info_Orien service_language_id English\n" + 
    		"12345678 Info_Orien service_official_language_id English\n" + 
    		"12345678 Info_Orien institution_type_id Settlement service provider\n" + 
    		"12345678 Info_Orien service_referred_by_id Community centre / library\n" + 
    		"12345678 Info_Orien orientation_service_id One-on-one orientation\n" + 
    		"12345678 Info_Orien orientation_length_id 5 minutes or less\n" + 
    		"12345678 Info_Orien orientation_length_hours_no 1\n" + 
    		"12345678 Info_Orien orientation_length_minutes_no 50\n" + 
    		"12345678 Info_Orien group_clients_no_id Less than 10 people\n" + 
    		"12345678 Info_Orien target_group_specific_ind Yes\n" + 
    		"12345678 Info_Orien target_group_children_ind Yes\n" + 
    		"12345678 Info_Orien target_group_youth_ind Yes\n" + 
    		"12345678 Info_Orien target_group_senior_ind Yes\n" + 
    		"12345678 Info_Orien target_group_gender_ind Yes\n" + 
    		"12345678 Info_Orien target_group_refugee_ind Yes\n" + 
    		"12345678 Info_Orien target_group_ethnic_ind Yes\n" + 
    		"12345678 Info_Orien target_group_hearing_ind Yes\n" + 
    		"12345678 Info_Orien target_group_visual_ind Yes\n" + 
    		"12345678 Info_Orien target_group_LGBTQ_ind Yes\n" + 
    		"12345678 Info_Orien target_group_families_parents_ind Yes\n" + 
    		"12345678 Info_Orien target_group_other_impairments_ind Yes\n" + 
    		"12345678 Info_Orien target_group_CWIT_in_regulated_profession_ind Yes\n" + 
    		"12345678 Info_Orien target_group_CWIT_in_regulated_trade_ind Yes\n" + 
    		"12345678 Info_Orien target_group_official_language_minorities_ind Yes\n" + 
    		"12345678 Info_Orien topic_overview_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_overview_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_information_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_information_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_rights_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_rights_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_law_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_law_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_documents_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_documents_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_language_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_language_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_income_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_income_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_education_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_education_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_housing_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_housing_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_health_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_health_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_money_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_money_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_transportation_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_transportation_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_media_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_media_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_community_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_community_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_citizenship_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_citizenship_referral_ind Yes\n" + 
    		"12345678 Info_Orien topic_conflict_given_ind Yes\n" + 
    		"12345678 Info_Orien topic_conflict_referral_ind Yes\n" + 
    		"12345678 Info_Orien training_received_service_ind Yes\n" + 
    		"12345678 Info_Orien training_received_computer_ind Yes\n" + 
    		"12345678 Info_Orien training_received_document_ind Yes\n" + 
    		"12345678 Info_Orien training_received_interpersonal_ind Yes\n" + 
    		"12345678 Info_Orien training_received_leadership_ind Yes\n" + 
    		"12345678 Info_Orien training_received_numeracy_ind Yes\n" + 
    		"12345678 Info_Orien skill_received_service_ind Yes\n" + 
    		"12345678 Info_Orien essential_skill_life_ind Yes\n" + 
    		"12345678 Info_Orien essential_skill_responsabilities_ind Yes\n" + 
    		"12345678 Info_Orien support_received_ind Yes\n" + 
    		"12345678 Info_Orien childminding_ind Yes\n" + 
    		"12345678 Info_Orien childminding_NewcomerChildren[1]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Info_Orien childminding_NewcomerChildren[1]childminding_type_id Short term\n" + 
    		"12345678 Info_Orien childminding_NewcomerChildren[2]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Info_Orien childminding_NewcomerChildren[2]childminding_type_id Short term\n" + 
    		"12345678 Info_Orien childminding_NewcomerChildren[3]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Info_Orien childminding_NewcomerChildren[3]childminding_type_id Short term\n" + 
    		"12345678 Info_Orien childminding_NewcomerChildren[4]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Info_Orien childminding_NewcomerChildren[4]childminding_type_id Short term\n" + 
    		"12345678 Info_Orien childminding_NewcomerChildren[5]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Info_Orien childminding_NewcomerChildren[5]childminding_type_id Short term\n" + 
    		"12345678 Info_Orien transportation_ind Yes\n" + 
    		"12345678 Info_Orien support_disability_ind Yes\n" + 
    		"12345678 Info_Orien translation_ind Yes\n" + 
    		"12345678 Info_Orien translation_language_from_id English\n" + 
    		"12345678 Info_Orien translation_language_to_id English\n" + 
    		"12345678 Info_Orien interpretation_ind Yes\n" + 
    		"12345678 Info_Orien interpretation_language_from_id English\n" + 
    		"12345678 Info_Orien interpretation_language_to_id English\n" + 
    		"12345678 Info_Orien counselling_ind Yes\n" + 
    		"12345678 Info_Orien end_dttm 2018-05-20\n" + 
    		"12345678 Info_Orien assessment_update_reason_id Amend record\n" + 
    		"", p.getAsSQL("test.db"));
  }

  @Test
  @DisplayName("test using a real ICare template (Needs Assessment and referrals)")
  public void testRealCSVTemplateNeedsAssessment() {
    database.PendingDatabaseEntryInterface p =
            c.parseCSVBasicICareTemplate(REAL_SAMPLES_ROOT + "Needs_Assessment&Referrals.csv");
    assertEquals("12345678 Needs_Assessment_and_Referrals processing_details \"\"\n" + 
    		"12345678 Needs_Assessment_and_Referrals update_record_id 10387104\n" + 
    		"12345678 Needs_Assessment_and_Referrals client_validation_type_id FOSS/GCMS Client ID\n" + 
    		"12345678 Needs_Assessment_and_Referrals client_birth_dt \n" + 
    		"12345678 Needs_Assessment_and_Referrals postal_cd \n" + 
    		"12345678 Needs_Assessment_and_Referrals assessment_start_dt \n" + 
    		"12345678 Needs_Assessment_and_Referrals assessment_language_id English\n" + 
    		"12345678 Needs_Assessment_and_Referrals preferred_official_language_id English\n" + 
    		"12345678 Needs_Assessment_and_Referrals institution_type_id Settlement service provider\n" + 
    		"12345678 Needs_Assessment_and_Referrals assessment_referral_id Community centre / library\n" + 
    		"12345678 Needs_Assessment_and_Referrals knwl_life_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals knwl_life_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals knwl_comm_gov_svcs_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals knwl_comm_gov_svcs_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals knwl_working_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals knwl_working_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals knwl_education_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals knwl_education_needs_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals social_networks_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals social_networks_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals professional_networks_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals professional_networks_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals local_community_svcs_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals local_community_svcs_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals community_involvement_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals community_involvement_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals language_skills_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals language_skills_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals language_skills_needs_reason_id Find employment\n" + 
    		"12345678 Needs_Assessment_and_Referrals other_skills_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals other_skills_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals other_skills_needs_reason_id Find employment\n" + 
    		"12345678 Needs_Assessment_and_Referrals find_employment_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals find_employment_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals find_employment_period_id Within one year\n" + 
    		"12345678 Needs_Assessment_and_Referrals canadian_employment_experience_id No\n" + 
    		"12345678 Needs_Assessment_and_Referrals intended_work_noc_group_id O - Management occupations\n" + 
    		"12345678 Needs_Assessment_and_Referrals intention_credentials_licence_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals intention_canadian_citizen_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals support_required_services_required Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_required_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals transportation_required_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals support_disability_required_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals translation_required_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals interpretation_required_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals counselling_required_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals non_cic_program_needs_services_required Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals food_cloth_material_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals food_cloth_material_refs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals housing_accommodation_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals housing_accommodation_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals hlth_mental_wellness_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals hlth_mental_wellness_refs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals financial_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals financial_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals family_support_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals family_support_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals language_non_CIC_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals language_non_CIC_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals edu_skills_devt_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals edu_skills_devt_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals employment_related_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals employment_related_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals legal_info_services_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals legal_info_services_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals community_services_needs_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals community_services_referrals_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals support_received_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_NewcomerChildren[1]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_NewcomerChildren[1]childminding_type_id Short term\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_NewcomerChildren[2]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_NewcomerChildren[2]childminding_type_id Short term\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_NewcomerChildren[3]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_NewcomerChildren[3]childminding_type_id Short term\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_NewcomerChildren[4]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_NewcomerChildren[4]childminding_type_id Short term\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_NewcomerChildren[5]childminding_age_id Infant (6-18 months)\n" + 
    		"12345678 Needs_Assessment_and_Referrals childminding_NewcomerChildren[5]childminding_type_id Short term\n" + 
    		"12345678 Needs_Assessment_and_Referrals transportation_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals support_disability_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals translation_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals translation_language_from_id English\n" + 
    		"12345678 Needs_Assessment_and_Referrals translation_language_to_id English\n" + 
    		"12345678 Needs_Assessment_and_Referrals interpretation_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals interpretation_language_from_id English\n" + 
    		"12345678 Needs_Assessment_and_Referrals interpretation_language_to_id English\n" + 
    		"12345678 Needs_Assessment_and_Referrals counselling_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals settlement_plan_ind Yes\n" + 
    		"12345678 Needs_Assessment_and_Referrals assessment_completed_dt 2018-05-20\n" + 
    		"12345678 Needs_Assessment_and_Referrals assessment_update_reason_id Amend record\n" + 
    		"", p.getAsSQL("test.db"));
  }


}
