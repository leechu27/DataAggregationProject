package gui;

import database.CSVParser;
import database.PendingDatabaseEntry;
import database.PendingDatabaseEntryInterface;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class OrganizationGUI extends JFrame {

	private JPanel contentPane;

	// Change this to the name of the main database later
	private static String databaseName = "test.db";

	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrganizationGUI frame = new OrganizationGUI();
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
	public OrganizationGUI() {
		setTitle("Organization Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeqOrganizationsPage = new JLabel("Organization Page\r\n");
		lblTeqOrganizationsPage.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTeqOrganizationsPage.setBounds(138, 11, 279, 73);
		contentPane.add(lblTeqOrganizationsPage);
		
		JButton btnNewButton = new JButton("Upload Template\r\n");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Upload Template Button pressed");
				// Gets the file from user 
				JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "CSV Files", "csv");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(null);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getName());
						String filepath = chooser.getSelectedFile().getAbsolutePath();
						try {
							dumpIntoDatabase(filepath);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

			    }
				
			}
		});
		btnNewButton.setBounds(75, 184, 142, 23);
		contentPane.add(btnNewButton);
		
		JButton btnManuallyUploadData = new JButton("Manually Upload Data");
		btnManuallyUploadData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Manual Upload Data button pressed");
			}
		});
		btnManuallyUploadData.setBounds(295, 184, 142, 23);
		contentPane.add(btnManuallyUploadData);
	}
	
	/**
	 * Dumps the csv file at the given location and uses the conflict management system
	 * to ask the user for input as to whether to replace the content or not
	 * 
	 * @param filepath
	 * @throws SQLException
	 */
	public static void dumpIntoDatabase(String filepath) throws SQLException {
		database.CSVParser csvp = new CSVParser();
		PendingDatabaseEntry entry = (PendingDatabaseEntry) csvp.parseCSVBasicICareTemplate(filepath);
		entry.verifyWithGUIIfNeeded(databaseName);
		System.out.println(entry.dumpIntoDatabase(databaseName));
		System.out.println("dumped from " + filepath);
		System.out.println("into " + databaseName);
	}
}
