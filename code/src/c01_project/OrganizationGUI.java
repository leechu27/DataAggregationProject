package c01_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrganizationGUI extends JFrame {

	private JPanel contentPane;

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
			}
		});
		btnNewButton.setBounds(75, 184, 142, 23);
		contentPane.add(btnNewButton);
		
		JButton btnManuallyUploadData = new JButton("Manually Upload Data");
		btnManuallyUploadData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Manuallu Upload Data button pressed");
			}
		});
		btnManuallyUploadData.setBounds(295, 184, 142, 23);
		contentPane.add(btnManuallyUploadData);
	}
}
