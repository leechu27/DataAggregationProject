package c01_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JCheckBox;


public class baseGui extends JFrame {

	private JPanel contentPane;
	private JTextField uniqueIdentifier;
	private JTextField dateBirth;
	private JTextField postalCode;
	private JTextField organization;
	private JTextField refer;
	private JTextField serviceRecieved;
	private JTextField numMinutes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					baseGui frame = new baseGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public baseGui() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 873, 1004);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	    
		JPanel clientProfilePanel = infoOrient();
	    JScrollPane scrollPane = new JScrollPane(clientProfilePanel);
	    
	    
	    contentPane.add(scrollPane, BorderLayout.CENTER);
		
	    //contentPane.add(clientProfilePanel, BorderLayout.NORTH);
		
		
	}
	
	// initiates our client profile page
	public JPanel clientProfile(JPanel base){
		// adding all the fields for client profile page
		JPanel clientProfile = new JPanel();
		
		JLabel lblUniqueIdentifier = new JLabel("Unique Identifier:");
		
		JTextField uniqueIdentifier = new JTextField();
		uniqueIdentifier.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth (YYYY-MM-DD):");
		
		JTextField dateBirth = new JTextField();
		dateBirth.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		
		JTextField phoneNum = new JTextField();
		phoneNum.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		
		JTextField email = new JTextField();
		email.setColumns(10);
		
		JLabel lblAddressLine = new JLabel("Address Line 1:");
		
		JTextField address1 = new JTextField();
		address1.setColumns(10);
		
		JLabel lblAddressLine_1 = new JLabel("Address Line 2:");
		
		JTextField address2 = new JTextField();
		address2.setColumns(10);
		
		String[] officalLang = {"English", "French"};
		
		JLabel lblLanguagePreference = new JLabel("Language Preference:");
		JComboBox comboBox = new JComboBox(officalLang);
		
		JButton btnSubmit = new JButton("Submit");
		
		JButton btnCancel = new JButton("Cancel");
		
		JLabel lblClientProfile = new JLabel("Client Profile");
		GroupLayout gl_clientProfile = new GroupLayout(clientProfile);
		gl_clientProfile.setHorizontalGroup(
			gl_clientProfile.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_clientProfile.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_clientProfile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_clientProfile.createSequentialGroup()
							.addComponent(lblAddressLine_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(address2, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_clientProfile.createSequentialGroup()
							.addComponent(lblPhoneNumber)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(phoneNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblEmail)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(268, Short.MAX_VALUE))
						.addGroup(gl_clientProfile.createSequentialGroup()
							.addComponent(lblLanguagePreference)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(391, Short.MAX_VALUE))
						.addGroup(gl_clientProfile.createSequentialGroup()
							.addComponent(btnSubmit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel)
							.addContainerGap(424, Short.MAX_VALUE))
						.addGroup(gl_clientProfile.createSequentialGroup()
							.addComponent(lblUniqueIdentifier)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(uniqueIdentifier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblDateOfBirth)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateBirth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(249))
						.addGroup(gl_clientProfile.createSequentialGroup()
							.addComponent(lblClientProfile, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(514, Short.MAX_VALUE))
						.addGroup(gl_clientProfile.createSequentialGroup()
							.addComponent(lblAddressLine)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(address1, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_clientProfile.setVerticalGroup(
			gl_clientProfile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_clientProfile.createSequentialGroup()
					.addComponent(lblClientProfile)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_clientProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUniqueIdentifier)
						.addComponent(uniqueIdentifier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDateOfBirth)
						.addComponent(dateBirth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_clientProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPhoneNumber)
						.addComponent(phoneNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail)
						.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_clientProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddressLine)
						.addComponent(address1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_clientProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddressLine_1)
						.addComponent(address2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_clientProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLanguagePreference)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_clientProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmit)
						.addComponent(btnCancel))
					.addGap(134))
		);
		clientProfile.setLayout(gl_clientProfile);
		return clientProfile;
	}
	
	public JPanel infoOrient(){
		JPanel infoOrient = new JPanel();
		// adding all the fields for client profile page
		JLabel lblUniqueIdentifier = new JLabel("Unique Identifier:");
		
		uniqueIdentifier = new JTextField();
		uniqueIdentifier.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth (YYYY-MM-DD):");
		
		dateBirth = new JTextField();
		dateBirth.setColumns(10);
		
		String[] officalLang = {"English", "French"};
		
		JLabel lblLanguagePreference = new JLabel("Language Preference:");
		JComboBox langPref = new JComboBox(officalLang);
		
		JButton btnSubmit = new JButton("Submit");
		
		JButton btnCancel = new JButton("Cancel");
		
		JLabel lblClientProfile = new JLabel("Information and Orientation");
		
		JComboBox langService = new JComboBox(officalLang);
		
		JLabel lblNewLabel = new JLabel("Language of Service:");
		
		postalCode = new JTextField();
		postalCode.setColumns(10);
		
		JLabel lblPostalCodeWhere = new JLabel("Postal Code of service received:");
		
		organization = new JTextField();
		organization.setColumns(10);
		
		JLabel lblTypeOfInstitutionorganization = new JLabel("Type of Institution/Organization:");
		
		refer = new JTextField();
		refer.setColumns(10);
		
		JLabel lblReferred = new JLabel("Referred By:");
		
		serviceRecieved = new JTextField();
		serviceRecieved.setColumns(10);
		
		JLabel lblServicesReceived = new JLabel("Services Received:");
		
		numMinutes = new JTextField();
		numMinutes.setColumns(10);
		
		JLabel lblLengthOfService = new JLabel("Length of Service (Minutes):");
		
		JLabel lblTargetGroup = new JLabel("Target Group:");
		
		JCheckBox chckbxChildren = new JCheckBox("Children (0-14)");
		
		JCheckBox chckbxYouth = new JCheckBox("Youth (15-24)");
		
		JCheckBox chckbxSeniors = new JCheckBox("Seniors");
		
		JCheckBox chckbxGenderspecific = new JCheckBox("Gender-specific");
		
		JCheckBox chckbxRefugees = new JCheckBox("Refugees");
		
		JCheckBox chckbxEthnicculturallinguisticGroup = new JCheckBox(" Ethnic/Cultural/Linguistic Group");
		
		JCheckBox chckbxDeaf = new JCheckBox("Deaf or Hard of Hearing");
		
		JCheckBox chckbxBlind = new JCheckBox("Blind or Partially Sighted");
		
		JCheckBox chckbxLgbtq = new JCheckBox("LGBTQ");
		
		JCheckBox chckbxFam = new JCheckBox("Families/Parents");
		
		JCheckBox chckbxOtherImpairments = new JCheckBox("Other Impairments");
		
		JCheckBox chckbxClientsWithInternational = new JCheckBox("Clients with International Training in a Regulated Profession");
		
		JCheckBox chckbxClientsWithInternational_1 = new JCheckBox("Clients with International Training in a Regulated Trade");
		
		JCheckBox chckbxOfficialLanguageMinorities = new JCheckBox("Official Language minorities");
		
		JCheckBox chckbxOverviewOfCanada = new JCheckBox("Overview of Canada");
		
		JCheckBox chckbxOverviewOfCanada_1 = new JCheckBox("Overview of Canada Referrals");
		
		JLabel lblMaterialsCovered = new JLabel("Materials Covered:");
		
		JCheckBox chckbxSourcesOfInformation = new JCheckBox("Sources of Information");
		
		JCheckBox chckbxSourcesOfInformation_1 = new JCheckBox("Sources of Information Referrals");
		
		JCheckBox chckbxRightsAndFreedoms = new JCheckBox("Rights and Freedoms");
		
		JCheckBox chckbxRightsAndFreedoms_1 = new JCheckBox("Rights and Freedoms Referrals");
		
		JCheckBox chckbxCanadianLawAnd = new JCheckBox("Canadian Law and Justice");
		
		JCheckBox chckbxCanadianLawAnd_1 = new JCheckBox("Canadian Law and Justice Referrals");
		
		JCheckBox chckbxImportantDocuments = new JCheckBox("Important Documents");
		
		JCheckBox chckbxEducation = new JCheckBox("Education");
		
		JCheckBox chckbxEducationReferrals = new JCheckBox("Education Referrals");
		
		JCheckBox chckbxHousing = new JCheckBox("Housing");
		
		JCheckBox chckbxHousingReferrals = new JCheckBox("Housing Referrals");
		
		JCheckBox chckbxHealth = new JCheckBox("Health");
		
		JCheckBox chckbxHealthReferrals = new JCheckBox("Health Referrals");
		
		JCheckBox chckbxMoneyAndFinances = new JCheckBox("Money and Finances");
		
		JCheckBox chckbxMoneyAndFinances_1 = new JCheckBox("Money and Finances Referrals");
		
		JCheckBox chckbxTransportation = new JCheckBox("Transportation");
		
		JCheckBox chckbxTransportationReferrals = new JCheckBox("Transportation Referrals");
		
		JCheckBox chckbxCommunicationsAndMedia = new JCheckBox("Communications and Media");
		
		JCheckBox chckbxCommunicationsAndMedia_1 = new JCheckBox("Communications and Media Referals");
		
		JCheckBox chckbxCommunityEngagement = new JCheckBox("Community Engagement");
		
		JCheckBox chckbxCommunityEngagementReferrals = new JCheckBox("Community Engagement Referrals");
		
		JCheckBox chckbxBecomingACanadian = new JCheckBox("Becoming a Canadian Citizen");
		
		JCheckBox chckbxBecomingACanadian_1 = new JCheckBox("Becoming a Canadian Citizen Referrals");
		
		JCheckBox chckbxInterpersonalConflict = new JCheckBox("Interpersonal Conflict");
		
		JCheckBox chckbxInterpersonalConflictReferrals = new JCheckBox("Interpersonal Conflict Referrals");
		
		JLabel lblWasEssentialSkills = new JLabel("Was Essential Skills and Aptitude Training Received as Part of this Service?");
		
		String[] yesOrNo = {"Yes", "No"};
		JComboBox comboBoxEssentialSkills = new JComboBox(yesOrNo);
		
		JCheckBox chckbxComputerSkills = new JCheckBox("Computer skills");
		
		JCheckBox chckbxDocumentUse = new JCheckBox("Document Use");
		
		JCheckBox chckbxInterpersonalSkillsAnd = new JCheckBox("Interpersonal Skills and Workplace Culture");
		
		JCheckBox chckbxLeadershipTraining = new JCheckBox("Leadership Training");
		
		JCheckBox chckbxNumeracy = new JCheckBox("Numeracy");
		
		JLabel lblWasLifeSkills = new JLabel("Was Life Skills or Responsibilities of Citizenship Information Received as Part of this Service?");
		
		JComboBox comboBoxLifeSkills = new JComboBox(yesOrNo);
		
		JCheckBox chckbxLifeSkills = new JCheckBox("Life Skills");
		
		JCheckBox chckbxRightsAndResponsibilities = new JCheckBox("Rights and Responsibilities of Citizenship (based on discover Canada)");
		
		JLabel lblSupportServicesReceived = new JLabel("Support Services Received");
		
		JComboBox comboBoxSupportServices = new JComboBox(yesOrNo);
		
		JLabel lblCareForNewcomer = new JLabel("Care for Newcomer Children?");
		
		JComboBox comboBoxNumChildren = new JComboBox();
		
		GroupLayout gl_inforOrient = new GroupLayout(infoOrient);
		gl_inforOrient.setHorizontalGroup(
			gl_inforOrient.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inforOrient.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_inforOrient.createSequentialGroup()
							.addComponent(chckbxComputerSkills)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxDocumentUse)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxInterpersonalSkillsAnd))
						.addGroup(gl_inforOrient.createSequentialGroup()
							.addGroup(gl_inforOrient.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_inforOrient.createSequentialGroup()
									.addGroup(gl_inforOrient.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPostalCodeWhere)
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(lblReferred)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(refer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(7)
									.addGroup(gl_inforOrient.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(lblServicesReceived)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(serviceRecieved, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblLengthOfService)
											.addGap(5)
											.addComponent(numMinutes, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(postalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblTypeOfInstitutionorganization)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(organization, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
								.addComponent(lblClientProfile, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_inforOrient.createSequentialGroup()
									.addComponent(lblUniqueIdentifier)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(uniqueIdentifier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblDateOfBirth)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(dateBirth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_inforOrient.createSequentialGroup()
									.addComponent(lblTargetGroup)
									.addGap(23)
									.addGroup(gl_inforOrient.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxEthnicculturallinguisticGroup)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxDeaf))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxChildren)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxYouth)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxSeniors)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxGenderspecific)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxRefugees))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxBlind)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxLgbtq)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxFam)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxOtherImpairments))
										.addComponent(chckbxClientsWithInternational)
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxClientsWithInternational_1)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxOfficialLanguageMinorities))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxOverviewOfCanada)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxOverviewOfCanada_1))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addGroup(gl_inforOrient.createParallelGroup(Alignment.LEADING)
												.addComponent(chckbxSourcesOfInformation)
												.addComponent(chckbxRightsAndFreedoms))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_inforOrient.createParallelGroup(Alignment.LEADING)
												.addComponent(chckbxRightsAndFreedoms_1)
												.addGroup(gl_inforOrient.createSequentialGroup()
													.addComponent(chckbxSourcesOfInformation_1)
													.addPreferredGap(ComponentPlacement.UNRELATED)
													.addComponent(chckbxImportantDocuments))))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxCanadianLawAnd)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxCanadianLawAnd_1))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxEducation)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxEducationReferrals))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxHousing)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxHousingReferrals))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxHealth)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxHealthReferrals))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxMoneyAndFinances)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxMoneyAndFinances_1))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxTransportation)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxTransportationReferrals))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxCommunicationsAndMedia)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxCommunicationsAndMedia_1))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxCommunityEngagement)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxCommunityEngagementReferrals))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxBecomingACanadian)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxBecomingACanadian_1))
										.addGroup(gl_inforOrient.createSequentialGroup()
											.addComponent(chckbxInterpersonalConflict)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(chckbxInterpersonalConflictReferrals)))))
							.addGap(272))
						.addGroup(gl_inforOrient.createSequentialGroup()
							.addComponent(lblMaterialsCovered, GroupLayout.DEFAULT_SIZE, 1596, Short.MAX_VALUE)
							.addGap(476))
						.addGroup(gl_inforOrient.createSequentialGroup()
							.addComponent(lblWasEssentialSkills)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxEssentialSkills, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1669, Short.MAX_VALUE))
						.addGroup(gl_inforOrient.createSequentialGroup()
							.addComponent(btnSubmit)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancel)
							.addContainerGap(1936, Short.MAX_VALUE))
						.addGroup(gl_inforOrient.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(langService, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1908, Short.MAX_VALUE))
						.addGroup(gl_inforOrient.createSequentialGroup()
							.addComponent(lblLanguagePreference)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(langPref, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1903, Short.MAX_VALUE))
						.addGroup(gl_inforOrient.createSequentialGroup()
							.addGroup(gl_inforOrient.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_inforOrient.createSequentialGroup()
									.addComponent(chckbxLeadershipTraining)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxNumeracy))
								.addComponent(lblWasLifeSkills))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxLifeSkills, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(1586, Short.MAX_VALUE))
						.addGroup(gl_inforOrient.createSequentialGroup()
							.addComponent(lblSupportServicesReceived)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxSupportServices, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCareForNewcomer)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxNumChildren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(1708))
						.addGroup(gl_inforOrient.createSequentialGroup()
							.addComponent(chckbxLifeSkills)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxRightsAndResponsibilities)
							.addContainerGap())))
		);
		gl_inforOrient.setVerticalGroup(
			gl_inforOrient.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inforOrient.createSequentialGroup()
					.addComponent(lblClientProfile)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUniqueIdentifier)
						.addComponent(lblDateOfBirth)
						.addComponent(dateBirth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(uniqueIdentifier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPostalCodeWhere)
						.addComponent(postalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTypeOfInstitutionorganization)
						.addComponent(organization, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReferred)
						.addComponent(refer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblServicesReceived)
						.addComponent(serviceRecieved, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(numMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLengthOfService))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTargetGroup)
						.addComponent(chckbxChildren)
						.addComponent(chckbxYouth)
						.addComponent(chckbxSeniors)
						.addComponent(chckbxGenderspecific)
						.addComponent(chckbxRefugees))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxEthnicculturallinguisticGroup)
						.addComponent(chckbxDeaf))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxBlind)
						.addComponent(chckbxLgbtq)
						.addComponent(chckbxFam)
						.addComponent(chckbxOtherImpairments))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chckbxClientsWithInternational)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxClientsWithInternational_1)
						.addComponent(chckbxOfficialLanguageMinorities))
					.addGap(4)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMaterialsCovered)
						.addComponent(chckbxOverviewOfCanada)
						.addComponent(chckbxOverviewOfCanada_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxSourcesOfInformation_1)
						.addComponent(chckbxSourcesOfInformation)
						.addComponent(chckbxImportantDocuments))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxRightsAndFreedoms_1)
						.addComponent(chckbxRightsAndFreedoms))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxCanadianLawAnd)
						.addComponent(chckbxCanadianLawAnd_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxEducation)
						.addComponent(chckbxEducationReferrals))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxHousing)
						.addComponent(chckbxHousingReferrals))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxHealth)
						.addComponent(chckbxHealthReferrals))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxMoneyAndFinances)
						.addComponent(chckbxMoneyAndFinances_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxTransportation)
						.addComponent(chckbxTransportationReferrals))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxCommunicationsAndMedia)
						.addComponent(chckbxCommunicationsAndMedia_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxCommunityEngagement)
						.addComponent(chckbxCommunityEngagementReferrals))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxBecomingACanadian)
						.addComponent(chckbxBecomingACanadian_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxInterpersonalConflict)
						.addComponent(chckbxInterpersonalConflictReferrals))
					.addGap(17)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWasEssentialSkills)
						.addComponent(comboBoxEssentialSkills, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxComputerSkills)
						.addComponent(chckbxDocumentUse)
						.addComponent(chckbxInterpersonalSkillsAnd))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxLeadershipTraining)
						.addComponent(chckbxNumeracy))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWasLifeSkills)
						.addComponent(comboBoxLifeSkills, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxLifeSkills)
						.addComponent(chckbxRightsAndResponsibilities))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSupportServicesReceived)
						.addComponent(comboBoxSupportServices, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCareForNewcomer)
						.addComponent(comboBoxNumChildren, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLanguagePreference)
						.addComponent(langPref, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(langService, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inforOrient.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSubmit)
						.addComponent(btnCancel))
					.addContainerGap())
		);
		infoOrient.setLayout(gl_inforOrient);
		return infoOrient;
	}
}
