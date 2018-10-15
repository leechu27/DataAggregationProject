package c01_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

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
		setBounds(100, 100, 528, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel clientProfile = new JPanel();
		contentPane.add(clientProfile, BorderLayout.CENTER);
		
		// adding all the fields for client profile page
		
		JLabel lblUniqueIdentifier = new JLabel("Unique Identifier:");
		clientProfile.add(lblUniqueIdentifier);
		
		uniqueIdentifier = new JTextField();
		clientProfile.add(uniqueIdentifier);
		uniqueIdentifier.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth (YYYY-MM-DD):");
		clientProfile.add(lblDateOfBirth);
		
		dateBirth = new JTextField();
		clientProfile.add(dateBirth);
		dateBirth.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		clientProfile.add(lblPhoneNumber);
		
		phoneNum = new JTextField();
		clientProfile.add(phoneNum);
		phoneNum.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		clientProfile.add(lblEmail);
		
		email = new JTextField();
		clientProfile.add(email);
		email.setColumns(10);
		
		JLabel lblAddressLine = new JLabel("Address Line 1:");
		clientProfile.add(lblAddressLine);
		
		address1 = new JTextField();
		clientProfile.add(address1);
		address1.setColumns(10);
		
		JLabel lblAddressLine_1 = new JLabel("Address Line 2:");
		clientProfile.add(lblAddressLine_1);
		
		address2 = new JTextField();
		clientProfile.add(address2);
		address2.setColumns(10);
		
		String[] officalLang = {"English", "French"};
		
		JLabel lblLanguagePreference = new JLabel("Language Preference:");
		clientProfile.add(lblLanguagePreference);
		JComboBox comboBox = new JComboBox(officalLang);
		clientProfile.add(comboBox);
		
	}

}
