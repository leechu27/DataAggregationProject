package c01_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TEQHighLevelGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TEQHighLevelGUI frame = new TEQHighLevelGUI();
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
	public TEQHighLevelGUI() {
		setTitle("TEQ Mid level\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeqMidLevel = new JLabel("TEQ High Level");
		lblTeqMidLevel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTeqMidLevel.setBounds(126, 27, 167, 42);
		contentPane.add(lblTeqMidLevel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Query Option1", "Query Option2", "You can add a query option with add filter"}));
		comboBox.setBounds(105, 94, 188, 23);
		contentPane.add(comboBox);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Filter :" + comboBox.getSelectedItem());
				
			}
		});
		btnFilter.setBounds(312, 94, 89, 23);
		contentPane.add(btnFilter);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Generate report button");
			}
		});
		btnGenerateReport.setBounds(140, 171, 135, 23);
		contentPane.add(btnGenerateReport);
		
		textField = new JTextField();
		textField.setBounds(105, 128, 188, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAddFilter = new JButton("Add Filter");
		btnAddFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Add filter: " + textField.getText());
				comboBox.addItem(textField.getText());
			}
		});
		btnAddFilter.setBounds(312, 128, 89, 23);
		contentPane.add(btnAddFilter);
	}

}
