package c01_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class baseGui extends JFrame {

	private JPanel contentPane;
	private JTextField uniqueIdentifier;
	private JTextField dateBirth;
	private JTextField phoneNum;
	private JTextField email;
	private JTextField address1;
	private JTextField address2;

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
		setBounds(100, 100, 596, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		

		JPanel clientProfilePanel = clientProfile();
		contentPane.add(clientProfilePanel, BorderLayout.CENTER);
		
	}
	
	// initiates our client profile page
	public JPanel clientProfile(){
		// adding all the fields for client profile page
		JPanel clientProfile = new JPanel();
		contentPane.add(clientProfile, BorderLayout.CENTER);
		JLabel lblUniqueIdentifier = new JLabel("Unique Identifier:");
		
		uniqueIdentifier = new JTextField();
		uniqueIdentifier.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth (YYYY-MM-DD):");
		
		dateBirth = new JTextField();
		dateBirth.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		
		phoneNum = new JTextField();
		phoneNum.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		
		email = new JTextField();
		email.setColumns(10);
		
		JLabel lblAddressLine = new JLabel("Address Line 1:");
		
		address1 = new JTextField();
		address1.setColumns(10);
		
		JLabel lblAddressLine_1 = new JLabel("Address Line 2:");
		
		address2 = new JTextField();
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
}
