package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TEQ extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JLabel lblTeq;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TEQ frame = new TEQ();
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
	public TEQ() {
		setTitle("TEQ\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setBounds(43, 91, 66, 28);
		contentPane.add(lblTable);
		
		JLabel lblCondition = new JLabel("Condition");
		lblCondition.setBounds(43, 133, 66, 14);
		contentPane.add(lblCondition);
		
		JLabel lblNewLabel = new JLabel("Label\r\n");
		lblNewLabel.setBounds(43, 173, 66, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(119, 130, 238, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(119, 95, 238, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(119, 170, 238, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Create button clicked");
			}
		});
		btnCreate.setBounds(245, 365, 89, 23);
		contentPane.add(btnCreate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(167, 227, 226, 127);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		lblTeq = new JLabel("TEQ");
		lblTeq.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTeq.setBounds(245, 37, 107, 40);
		contentPane.add(lblTeq);
		
		btnNewButton = new JButton("Filter\r\n");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Filter button clicked");
				System.out.println("Table: " + textField.getText());
				System.out.println("Condition: " + textField_1.getText());
				System.out.println("Label: " + textField_2.getText());
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnNewButton.setBounds(382, 94, 131, 93);
		contentPane.add(btnNewButton);
	}
}
