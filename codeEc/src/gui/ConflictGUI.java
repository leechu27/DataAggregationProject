package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class ConflictGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConflictGUI frame = new ConflictGUI();
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
	public ConflictGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConflict = new JLabel("Conflicts");
		lblConflict.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblConflict.setBounds(270, 47, 167, 41);
		contentPane.add(lblConflict);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(144, 189, 479, 86);
		contentPane.add(scrollPane);
		
		// old data table
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFocusable(false);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				// row 1 of old table
				{"1", "2", "3", null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				// row 2 of old table
				"New column", "New column", "col 3", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "last column"
			}
		) );
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(144, 321, 479, 86);
		contentPane.add(scrollPane_1);
		// new data table
		table_1 = new JTable();
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_1.setFocusable(false);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				// row 1 of new table
				{"1", "2", "3", null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				// row 2 of new table
				"col 1", "col 2", "col 3", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JRadioButton rdbtnOldData = new JRadioButton("Old data");
		
		rdbtnOldData.setSelected(true);
		rdbtnOldData.setBounds(53, 192, 81, 23);
		contentPane.add(rdbtnOldData);
		
		JRadioButton rdbtnNewData = new JRadioButton("New data");
		rdbtnNewData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rdbtnOldData.setSelected(false);
				rdbtnNewData.setSelected(true);
			}
		});
		rdbtnOldData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rdbtnOldData.setSelected(true);
				rdbtnNewData.setSelected(false);
				
			}
		});
		rdbtnNewData.setBounds(53, 324, 81, 23);
		contentPane.add(rdbtnNewData);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnOldData.isSelected()) {
					System.out.println("old data selected");
				}
				else {
					System.out.println("new data selected");
					
				}
			}
		});
		btnSelect.setBounds(593, 448, 89, 23);
		contentPane.add(btnSelect);
	}
}
