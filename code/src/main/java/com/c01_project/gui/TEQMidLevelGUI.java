package c01_project.gui;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TEQMidLevelGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TEQMidLevelGUI frame = new TEQMidLevelGUI();
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
	public TEQMidLevelGUI() {
		setTitle("TEQ Mid level\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeqMidLevel = new JLabel("TEQ Mid Level");
		lblTeqMidLevel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTeqMidLevel.setBounds(126, 27, 167, 42);
		contentPane.add(lblTeqMidLevel);
		
		JComboBox comboBox = new JComboBox();
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Query Option1", "Query Option2", "Query Option3", "Query Option4", "Query Option5", "Query Option6", "Query Option7", "Query Option8", "Query Option9"}));
		comboBox.setBounds(105, 94, 188, 23);
		contentPane.add(comboBox);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Filter option :" + comboBox.getSelectedItem());
			}
		});
		btnFilter.setBounds(312, 94, 89, 23);
		contentPane.add(btnFilter);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Generate Report pressed");
			}
		
		});
		btnGenerateReport.setBounds(126, 152, 135, 23);
		contentPane.add(btnGenerateReport);
	}

}
