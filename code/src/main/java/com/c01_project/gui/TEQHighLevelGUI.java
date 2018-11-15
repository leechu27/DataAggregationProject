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
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class TEQHighLevelGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

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
		// 2 high level, 1 mid level, 0 low level
		int level = 2;
		
		setTitle("TEQ Mid level\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeqMidLevel = new JLabel("TEQ Page");
		lblTeqMidLevel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTeqMidLevel.setBounds(126, 27, 167, 42);
		contentPane.add(lblTeqMidLevel);
		
		JComboBox comboBox = new JComboBox();
		if (level < 1) {
			comboBox.setVisible(false);
		}
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Query Option1", "Query Option2", "You can add a query option with add filter"}));
		comboBox.setBounds(105, 94, 188, 23);
		contentPane.add(comboBox);
		
		JButton btnFilter = new JButton("Filter");
		if (level < 1) {
			btnFilter.setVisible(false);
		}
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
		btnGenerateReport.setBounds(105, 415, 135, 23);
		contentPane.add(btnGenerateReport);
		
		textField = new JTextField();
		if (level < 2) {
			textField.setVisible(false);
		}
		textField.setBounds(105, 128, 188, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAddFilter = new JButton("Add Filter");
		if (level < 2) {
			btnAddFilter.setVisible(false);
		}
		btnAddFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Add filter: " + textField.getText());
				comboBox.addItem(textField.getText());
			}
		});
		btnAddFilter.setBounds(312, 128, 89, 23);
		contentPane.add(btnAddFilter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 171, 494, 192);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setOpaque(false);
		table.setSurrendersFocusOnKeystroke(true);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"coloumn1 data", "dsfdsf", "dsfdsf", "xzcxzcxz"},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"coloumn1", "coloumn2", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		scrollPane.setViewportView(table);
	}
}
